package kr.or.ddit.pfcp.professor.attendance.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.AttendanceVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.pfcp.professor.attendance.service.ProfessorAttendanceService;
import kr.or.ddit.pfcp.professor.attendclass.service.ProfessorAttendClassService;
import kr.or.ddit.pfcp.professor.lecture.service.ProfessorLectureService;
import kr.or.ddit.pfcp.staff.lecturemanage.attendance.service.AttendanceService;
import kr.or.ddit.pfcp.staff.lecturemanage.attendance.wrapper.AttendanceListWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author 김태수
 * @since 2025.07.01
 * @see
﻿ * << 개정이력(Modification Information) >>
 * 수정일		  |		수정자	|	수정 내용
 * -----------|-------------|--------------------------
 * 2025.07.01 | 	김태수   	|   최초 작성
 * 2025.07.14 | 	김태수   	|   출성 정보 등록 / 수정 추가
 * 2025.07.16 | 	김태수   	|   생성 버튼 제거, 수정 버튼으로 삽입/업데이트 동시 처리
 * 
 */
@Controller
@RequestMapping("/professor/attendance")
@RequiredArgsConstructor
@Slf4j
public class ProfessorAttendanceController {
	
	private final ProfessorLectureService professorLectureService;
	
	@Autowired
	private ProfessorAttendanceService professorAttendanceService;
	
	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private ProfessorAttendClassService attendClassService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;
	
	static final String MN = "attendance";
	
	
	@ModelAttribute(MN)
	public AttendanceVO attendance() {
		return new AttendanceVO();
	}
	
	
	 
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

		return "pfcp/professor/attendance/attendclassList";
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
	            return "pfcp/professor/attendance/attendclassDetail";
	        	
	           
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

	        return "pfcp/professor/attendance/attendclassDetail";
	    }
	
	/**
	 * 학생 출석 정보 등록
	 * @return
	 */
	 @GetMapping("attendanceStdList.do")
		public String attendanceListUI(
				@RequestParam(value = "no", required = false) String reqNo, 
				@RequestParam String enrNo,
				@RequestParam String userNo,
				Model model
		) {
			List<AttendanceVO> attendance = attendanceService.readAttendanceList(enrNo, userNo);
			LectureReqVO lectureReq = professorLectureService.readLectureDetail(reqNo); 
			
			log.info("체크크으킁: {}",lectureReq);
			
			model.addAttribute("lectureReq", lectureReq);
			model.addAttribute("attendance", attendance);

			log.info("attendance : " + attendance);
			log.info("enrNo: " + enrNo);
			log.info("userNo: " + userNo);

			String lectureName = "강의명 정보 없음";
			String userName = "학생명 정보 없음";

			if (attendance != null && !attendance.isEmpty()) {
				AttendanceVO firstAttendance = attendance.get(0);
				if (firstAttendance.getLectureReq() != null) {
					lectureName = firstAttendance.getLectureReq().getLecName();
				} else {
					log.warn("첫 번째 출석 데이터에 강의 요청 정보가 없습니다. 수강번호={}, 사용자번호={}", enrNo, userNo);
				}
				if (firstAttendance.getUser() != null) {
					userName = firstAttendance.getUser().getUserName();
				} else {
					log.warn("첫 번째 출석 데이터에 사용자 정보가 없습니다. 수강번호={}, 사용자번호={}", enrNo, userNo);
				}
			} else {
				log.info("기본 값 설정 : {}", enrNo);

				try {
					UserVO studentInfo = professorAttendanceService.selectUser(userNo);
					if (studentInfo != null) {
						userName = studentInfo.getUserName();
					} else {
						log.warn("사용자 정보를 찾을 수 없습니다. 사용자번호: {}", userNo);
					}

					LectureEnrVO enrollmentInfo = professorAttendanceService.selectEnrNo(enrNo);
					if (enrollmentInfo != null && enrollmentInfo.getLecNo() != null) {
						LectureReqVO lectureInfo = professorAttendanceService.selectLecNo(enrollmentInfo.getLecNo());
						if (lectureInfo != null) {
							lectureName = lectureInfo.getLecName();
						} else {
							log.warn("강의 정보를 찾을 수 없습니다. 강의번호: {}", enrollmentInfo.getLecNo());
						}
					} else {
						log.warn("수강 정보를 찾을 수 없습니다. 수강번호: {}", enrNo);
					}

				} catch (Exception e) {
					log.error("출석 정보가 없을 때 학생 또는 강의 정보를 가져오는 중 오류 발생: {}", e.getMessage(), e);
				}
			}

			model.addAttribute("lectureName", lectureName);
			model.addAttribute("userName", userName);

			return "pfcp/professor/attendance/attendanceStdList";
		}
	 
	/**
	 * 학생 출석 정보 수정
	 * @return
	 */
	@PostMapping("attendanceUpdateProcess.do")
	public String studentAttendanceModify(
			@ModelAttribute("attendanceWrapper") AttendanceListWrapper wrapper,
			BindingResult bindingResult,
			@RequestParam String enrNo, 
			@RequestParam String userNo,
			@RequestHeader(value = "Referer", required = false) String referer
	) {
		if (bindingResult.hasErrors()) {
		    return "redirect:" + (referer != null ? referer : "/professor/attendance/attendclassList.do");
		}

		if (wrapper != null && wrapper.getAttendanceList() != null) {
			for(AttendanceVO att : wrapper.getAttendanceList()) {
				if (att.getAttendId() == null || att.getAttendId().isEmpty()) {
				    att.setEnrollNo(enrNo);
					professorAttendanceService.insertAttendance(List.of(att));
				} else {
					attendanceService.updateAttendanceIfChanged(att);
				}
			}
		}
		String redirectUrl = "/professor/attendance/attendanceStdList.do?enrNo=" + enrNo + "&userNo=" + userNo;
		return "redirect:" + redirectUrl;
	}


	/**
	 * 출석 데이터 - 통계 조회
	 * @return
	 */
	@GetMapping("attendanceData.do")
	public String attendanceData() {
		return "pfcp/professor/attendance/attendanceData";
	}
	
	/**
	 * 출석 데이터 - 출석 현황 보고서
	 * @return
	 */
	@GetMapping("attendanceReport.do")
	public String attendanceReport() {
		return "pfcp/professor/theies/attendanceReport";
	}
	
	/**
	 * 출석 데이터 - 출석 현황 보고서 등록
	 * @return
	 */
	@GetMapping("attendanceInsertReport.do")
	public String attendanceInsertReport() {
		return "pfcp/professor/theies/attendanceInsertReport";
	}
	
	
	
}