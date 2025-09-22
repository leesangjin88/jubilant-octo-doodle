package kr.or.ddit.pfcp.professor.grade.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.tracing.ConditionalOnEnabledTracing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.GradeVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.pfcp.professor.attendance.service.ProfessorAttendanceService;
import kr.or.ddit.pfcp.professor.attendclass.service.ProfessorAttendClassService;
import kr.or.ddit.pfcp.professor.grade.service.ProfessorGradeService;
import kr.or.ddit.pfcp.professor.lecture.service.ProfessorLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김태수
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	김태수	|	최초 생성
 */
@ConditionalOnEnabledTracing
@RequestMapping("/professor/grade")
@RequiredArgsConstructor
@Controller
@Slf4j
public class ProfessorGradeController {
	
	private final ProfessorLectureService professorLectureService;
	private final ProfessorGradeService professorGradeService;
	
	@Autowired
	private ProfessorAttendanceService professorAttendanceService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;
	
	@Autowired
	private ProfessorAttendClassService attendClassService;
	
	@GetMapping("attendclassList.do")
	public String attendclassList(
			Principal principal,
			Model model, 
			@RequestParam(defaultValue = "1") int pageNo
	) {
		String currentUserNo = null;
		if (principal != null) {
			currentUserNo = principal.getName();
			model.addAttribute("userNo", currentUserNo);
		}

		int pageSize = 100;
		int offset = (pageNo - 1) * pageSize;

		List<LectureReqVO> lectureList = null;
		int totalCount = 0;

		if (currentUserNo != null) {
			totalCount = professorLectureService.readTotalLectureCntByPaging(currentUserNo);
			lectureList = professorLectureService.readLectureListByPaging(currentUserNo, offset, pageSize);
		} else {
			totalCount = professorLectureService.readTotalLectureCnt();
			lectureList = professorLectureService.readLectureList(offset, pageSize);
		}

		int totalPage = (int) Math.ceil((double) totalCount / pageSize);

		model.addAttribute("count", totalCount);
		model.addAttribute("lecture", lectureList);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totalPage", totalPage);

		return "pfcp/professor/grade/attendclassList";
	}
	
	@GetMapping("attendclassDetail.do")
    public String attendclassDetail(
        @RequestParam(value = "no", required = false) String reqNo, 
        Model model,
        @RequestParam(defaultValue = "1") int studentPageNo 
    ) {
	 
	 LectureReqVO lectureReq = professorLectureService.readLectureDetail(reqNo); 
        if (lectureReq == null) {
            model.addAttribute("studentList", null);
            model.addAttribute("studentCount", 0);
            return "pfcp/professor/grade/attendclassList";
        	
           
        }
        FileRefVO fileRef = fileRefService.readFileRef(lectureReq.getFileRefNo());
        if (fileRef != null) {
         AtchFileVO atchFile = atchFileService.readAtchFile(fileRef.getAtchId());
         lectureReq.setAtchFile(atchFile);
     }
        model.addAttribute("lectureReq", lectureReq);

        String lecNo = null;
        if (lectureReq != null) {
            lecNo = lectureReq.getLecNo();
        }

        if (lecNo != null && !lecNo.isEmpty()) {
            int studentPageSize = 10;
            int studentOffset = (studentPageNo - 1) * studentPageSize;

            int totalStudentCount = attendClassService.countEnrStd(lecNo); 
            List<LectureEnrVO> studentList = attendClassService.retrieveEnrStdPaging(lecNo, studentOffset, studentPageSize); 
            int totalStudentPages = (int) Math.ceil((double) totalStudentCount / studentPageSize);

            model.addAttribute("studentList", studentList);
            model.addAttribute("studentCount", totalStudentCount);
            model.addAttribute("studentPageNo", studentPageNo);
            model.addAttribute("studentPageSize", studentPageSize);
            model.addAttribute("totalStudentPages", totalStudentPages);
        } else {
            model.addAttribute("studentList", null);
            model.addAttribute("studentCount", 0);
            model.addAttribute("studentPageNo", 1);
            model.addAttribute("studentPageSize", 10);
            model.addAttribute("totalStudentPages", 1);
        }

        return "pfcp/professor/grade/attendclassDetail";
    }
	
	@GetMapping("gradeStdList.do")
    public String gradeStdList(
            @RequestParam("lecNo") String lecNo,
            @RequestParam("enrNo") String enrNo, 
            @RequestParam("userNo") String userNo,
            Model model
    ) {
		 GradeVO studentGrade = professorGradeService.selStuGrade(lecNo, userNo);

	        List<GradeVO> studentGrades = new ArrayList<>();
	        if (studentGrade != null) {
	            studentGrades.add(studentGrade); 
	        }

	        model.addAttribute("studentGrades", studentGrades);
	        model.addAttribute("lecNo", lecNo);
	        model.addAttribute("enrNo", enrNo);
	        model.addAttribute("userNo", userNo);
	        
	        String lectureName = "강의명 정보 없음";
	        String userName = "학생명 정보 없음";
	        try {
	            UserVO studentInfo = professorAttendanceService.selectUser(userNo); 
	            if (studentInfo != null) {
	                userName = studentInfo.getUserName();
	            } else {
	                log.warn("사용자 정보를 찾을 수 없습니다. 사용자번호: {}", userNo);
	            }

	            LectureReqVO lectureInfo = professorAttendanceService.selectLecNo(lecNo); 
	            if (lectureInfo != null) {
	                lectureName = lectureInfo.getLecName();
	            } else {
	                log.warn("강의 정보를 찾을 수 없습니다. 강의번호: {}", lecNo);
	            }
	        } catch (Exception e) {
	            log.error("학생 또는 강의 정보를 가져오는 중 오류 발생: {}", e.getMessage(), e);
	        }

	        model.addAttribute("lectureName", lectureName);
	        model.addAttribute("userName", userName);

	        return "pfcp/professor/grade/gradeStdList";
    }

	@PostMapping("updateGrades.do")
	public String updateGrades(
			@RequestParam("lecNo") String lecNo,
			@RequestParam("enrollNoList") List<String> enrollNoList, 
			@RequestParam("midtermScoreList") List<Integer> midtermScoreList,
			@RequestParam("finalScoreList") List<Integer> finalScoreList,
			@RequestParam(value = "assignmentScoreList", required = false) List<Integer> assignmentScoreList,
			@RequestParam("attendanceScoreList") List<Integer> attendanceScoreList,
			@RequestParam(value = "userNo", required = false) String requestUserNo, 
			@RequestParam(value = "enrNo", required = false) String requestEnrNo,   
			RedirectAttributes ra
	) {
		String finalRedirectUserNo = null;
		String finalRedirectEnrNo = null;

		if (assignmentScoreList == null) {
			assignmentScoreList = new ArrayList<>();
		}

		if (enrollNoList != null && !enrollNoList.isEmpty()) {
			for (int i = 0; i < enrollNoList.size(); i++) {
				String currentEnrNo = enrollNoList.get(i);
				Integer midScr = midtermScoreList.get(i);
				Integer finScr = finalScoreList.get(i);
				Integer attScr = attendanceScoreList.get(i);
				Integer assScr = (assignmentScoreList.size() > i) ? assignmentScoreList.get(i) : 0;

				try {
					GradeVO gradeInfo = professorGradeService.selStuScr(currentEnrNo);
					if (gradeInfo == null || gradeInfo.getUserNo() == null) {
						log.warn("enrollNo [{}]에 해당하는 사용자 번호를 찾을 수 없습니다.", currentEnrNo);
						continue;
					}
					String userNo = gradeInfo.getUserNo(); // 조회된 userNo

					if (requestUserNo != null && requestEnrNo != null) {
						finalRedirectUserNo = requestUserNo;
						finalRedirectEnrNo = requestEnrNo;
					} else {
						if (finalRedirectUserNo == null) finalRedirectUserNo = userNo;
						if (finalRedirectEnrNo == null) finalRedirectEnrNo = currentEnrNo;
					}


					professorGradeService.updateAndCalculateFinalGrade(
							lecNo, userNo, midScr, finScr, assScr, attScr
					);
				} catch (Exception e) {
					log.error("업데이트 오류 발생 - enrollNo: {}, lecNo: {}, error: {}", currentEnrNo, lecNo, e.getMessage(), e);
					ra.addFlashAttribute("message", "일부 학생의 성적 업데이트에 실패했습니다. 오류: " + e.getMessage());
					String redirectUrl = "/professor/grade/gradeStdList.do?lecNo=" + lecNo;
					if (finalRedirectUserNo != null) redirectUrl += "&userNo=" + finalRedirectUserNo;
					if (finalRedirectEnrNo != null) redirectUrl += "&enrNo=" + finalRedirectEnrNo;
					return "redirect:" + redirectUrl;
				}
			}
			ra.addFlashAttribute("message", "완료.");
		} else {
			ra.addFlashAttribute("message", "성적 없음..");
		}
		if (finalRedirectUserNo != null && finalRedirectEnrNo != null) {
			return "redirect:/professor/grade/gradeStdList.do?lecNo=" + lecNo + "&enrNo=" + finalRedirectEnrNo + "&userNo=" + finalRedirectUserNo;
		} else {
			return "redirect:/professor/grade/attendclassList.do";
		}
	}
	
}
