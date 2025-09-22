package kr.or.ddit.pfcp.staff.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.CollegeVO;
import kr.or.ddit.pfcp.common.vo.CurriculumVO;
import kr.or.ddit.pfcp.common.vo.DGRRequestVO;
import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.GraduationRequestVO;
import kr.or.ddit.pfcp.common.vo.SubjectVO;
import kr.or.ddit.pfcp.professor.subject.service.ProfessorSubjectServiceImpl;
import kr.or.ddit.pfcp.staff.college.service.CollegeServiceImpl;
import kr.or.ddit.pfcp.staff.college.service.CurriculumServiceImpl;
import kr.or.ddit.pfcp.staff.college.service.DGRRequestServiceImpl;
import kr.or.ddit.pfcp.staff.college.service.DepartmentServiceImpl;
import kr.or.ddit.pfcp.staff.college.service.GraduationRequestServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author KGM
 * @since 250630
 * @see
 */
@Controller
@RequestMapping("/staff/college")
@Slf4j
public class CollegeController {
	
	@Autowired
	private CollegeServiceImpl service;
	
	@Autowired
	private DepartmentServiceImpl departmentService;
	
	@Autowired
	private CurriculumServiceImpl curriculumService;
	
	@Autowired
	private DGRRequestServiceImpl dgrRequestService;
	
	@Autowired
	private GraduationRequestServiceImpl graduationRequestService;
	
	@Autowired
	private ProfessorSubjectServiceImpl subjectService;
	
	
//	@GetMapping("subjectList.do")
//	@ResponseBody
//	public List<SubjectVO> subjectList(
//		@RequestParam(value = "departmentNo") String departmentNo
//	) {
//		List<SubjectVO> subjectList = curriculumService.readSubjectList(departmentNo);
//		
//		return subjectList;
//	}
	
	/**
	 * 단과대학 목록 조회	
	 * @return
	 */
	@GetMapping("collegeList.do")
	public String collegeList(
			Model model
	) {
        List<CollegeVO> collegeList = service.readCollegeList();
        log.info("collegeList", collegeList);
		model.addAttribute("college", collegeList);
		return "pfcp/staff/college/collegeList";
	}
	
	/**
	 * 단과대학 삭제	
	 * @return
	 */
	@GetMapping("collegeDelete.do")
	public String collegeDelete(
			Model model
			, @RequestParam("collegeNo") String collegeNo
	) {
        service.disableCollege(collegeNo);
		return "redirect:/staff/college/collegeList.do";
	}
	
	/**
	 * 학과 삭제
	 */
	@GetMapping("departmentDelete.do")
	public String removeDepartment(
			Model model
			,@RequestParam("departmentNo") String departmentNo
			,@RequestParam(value = "collegeNo", required = false) String collegeNo
	) {
		try {
			departmentService.disableDepartment(departmentNo);
		} catch (Exception e) {
			System.err.println("학과 삭제 오류 : " + e.getMessage());
		}
        
		return "redirect:/staff/college/departmentList.do";
    }
		
	/**
	 * 학과 목록 조회
	 * (단과 대학 목록에서 학과 목록으로 넘어감)
	 * @return
	 */
	@GetMapping("departmentList.do")
	public String collegeRead(
			@RequestParam(value = "collegeNo", required = false) String collegeNo
			, Model model
			, RedirectAttributes redirectAttributes
			) {	

		// 1. 단과 대학 상세 정보 조회
	    List<CollegeVO> college = service.readCollege(collegeNo);
	    
	    if (college != null) {
	        model.addAttribute("college", college); // 조회된 단과 대학 객체를 모델에 추가

	        // 2. 해당 단과 대학의 학과 목록 조회
	        List<DepartmentVO> departmentList = departmentService.readDepartmentByCollege(collegeNo);
	        model.addAttribute("departmentList", departmentList); // 학과 목록을 모델에 추가
	        
	        return "pfcp/staff/college/departmentList";
	    } else {
            redirectAttributes.addFlashAttribute("errorMessage", "해당 단과대학 정보를 찾을 수 없습니다."); // 오류 메시지 추가
			return "redirect:/staff/college/collegeList";
		}
	}
	
	/**
	 * 학과 상세 정보(학과 정보, 커리큘럼, 졸업 요건...)
	 * 
	 * @param departmentNo
	 * @param model
	 * @return
	 */
	@GetMapping("departmentDetail.do")
	public String departmentDetail(
		@RequestParam("departmentNo") String departmentNo
		, Model model
	) {
		// 학과 정보
	    DepartmentVO department = departmentService.readDepartment(departmentNo);
	    // 커리큘럼 조회 서비스 만들어서
	    List<CurriculumVO> curriculumList = curriculumService.readCurriculum(departmentNo);

	    DGRRequestVO dgrList = dgrRequestService.readDGRRequest(departmentNo);

	    model.addAttribute("department", department);
	    model.addAttribute("curriculumList", curriculumList);
	    model.addAttribute("dgrList", dgrList);
	    
	    return "pfcp/staff/college/departmentDetail"; 
	}
	
	/**
	 * 학과 등록(********************************************)
	 * @return
	 */
	@GetMapping("departmentInsert.do")
	public String departmentInsert(Model model) {
		return "pfcp/staff/college/departmentInsert";
	}
	
	@GetMapping("/selectCollege")
	public ResponseEntity<List<CollegeVO>> selectCollege() {
		List<CollegeVO> collegeList = service.readCollegeList();
		return ResponseEntity.ok(collegeList); 
	}
	
	@PostMapping("insertDepartment.do")
	public String insertDepartment(
			@ModelAttribute DepartmentVO department // 학과 폼 데이터 바인딩

	) {
		DGRRequestVO dgrRequest = department.getDgrReqVO();
		
		try {
            dgrRequestService.createDGRRequest(dgrRequest);
            department.setDgrNo(dgrRequest.getDgrNo()); // DGR_NO 설정

            int res = departmentService.createDepartment(department);

            if (res > 0) {
                return "redirect:/staff/college/departmentList.do";
            } else {
                return "redirect:/staff/college/departmentInsert.do";
            }
            
        } catch (Exception e) {
            return "pfcp/staff/college/departmentInsert";
        }
	}
  
	/**
	 * 학과 삭제 처리
     */
//	@PostMapping("removeDepartment.do")
//	public String removeDepartment(
//			@RequestParam("departmentNo") String departmentNo,
//			@RequestParam(value = "collegeNo", required = false) String collegeNo
//			
//	) {
//		log.info("{}, {}>>>>", departmentNo, collegeNo);
//		
//		try {
//			departmentService.disableDepartment(departmentNo);
//		} catch (Exception e) {
//			System.err.println("학과 삭제 오류 : " + e.getMessage());
//		}
//        
//		return "redirect:/staff/college/departmentList.do?collegeNo=" + collegeNo;
//    }
	
	/**
	 * 학과 수정 페이지 진입
	 */
	@GetMapping("departmentModify.do")
	public String departmentModifyForm(
			@RequestParam String departmentNo
			, Model model
	) {
		DepartmentVO department = departmentService.readDepartment(departmentNo);
		model.addAttribute("department", department);
		
		List<CollegeVO> collegeList = service.readCollegeList();
		model.addAttribute("collegeList", collegeList);
		
		return "pfcp/staff/college/departmentInsert";
		
	}
	
	/**
	 * 학과 실제 수정 처리
	 */
	@PostMapping("updateDepartment.do")
	public String departmentModify(
			@ModelAttribute DepartmentVO department	
	) {
		
		departmentService.modifyDepartment(department);
		
		return "redirect:/staff/college/departmentDetail.do?departmentNo=";
	}
	
	/**
	 * 커리큘럼 등록에 과목명 셀렉트 넣기
	 * @return
	 */
	@GetMapping("/selectSubject")
	public ResponseEntity<List<SubjectVO>> selectCurriculum() {
		List<SubjectVO> subjectList = curriculumService.readSubjectList();
		return ResponseEntity.ok(subjectList);
	}
	
	/**
	 * 커리큘럼 등록
	 * @return
	 */
	@GetMapping("curriculumInsert.do")
	public String curriculumInsert(
			Model model
			,@RequestParam String departmentNo
	) {
		model.addAttribute("departmentNo", departmentNo);
		model.addAttribute("curriculum", new CurriculumVO());
		return "pfcp/staff/college/curriculumInsert";
	}
	 
	
	/**
	 * ajax 과목명 가져오기
	 * @return
	 */
	@GetMapping("/selectSubjectList")
	public ResponseEntity<List<SubjectVO>> selectSubject() {
		List<SubjectVO> subjectList = subjectService.readSubjectList();
		return ResponseEntity.ok(subjectList); 
	}
	
	/**
	 * 커리큘럼 실제 등록
	 * @param curriculum
	 * @return
	 */
	@PostMapping("insertCurriculum.do")
	public String insertcurriculum(
			@ModelAttribute CurriculumVO curriculum,
			RedirectAttributes redirectAttributes
			
	) {
		curriculum.setSubjectCode(curriculum.getSubjectName());
		log.info("{} >> ", curriculum);
		curriculumService.createCurriculum(curriculum);
		redirectAttributes.addAttribute("departmentNo", curriculum.getDepartmentNo());
		return "redirect:/staff/college/departmentDetail.do";
	}
	
//		// 서비스 계층을 호출하여 DB에 데이터 저장
//		int res = curriculumService.createCurriculum(curriculum);
//		
//		if (res > 0) {
//			// 등록 성공 시, 단과대학 목록 페이지로 리다이렉트하여 새로 등록된 항목
//			return "redirect:/staff/college/departmentDetail.do";
//		} else {
//			// 등록 실패 시 (예: DB 오류 등), 다시 등록 폼으로 이동하거나 에러 메시지를 표시
//			// 여기서는 간단히 다시 등록 폼
//			log.warn("단과대학 등록 실패: {}", curriculum);
//			return "pfcp/staff/college/curriculumInsert";
//		}
	
	
	
//	/**
//     * 단과대학 수정 폼
//     */
//    @GetMapping("collegeUpdate.do")
//    public String collegeUpdateForm(
//    		@RequestParam("collegeNo") String collegeNo
//    		, Model model
//    		) {
//        log.info("단과대학 수정 폼 요청, collegeNo: {}", collegeNo);
//
//        CollegeVO college = service.readCollege(collegeNo); 
//        
//        if (college != null) {
//            model.addAttribute("college", college);
//            log.info("단과대학 정보 로드 성공: {}", college.getCollegeName());
//            
//            return "pfcp/staff/college/collegeUpdate"; // 수정 폼 JSP
//        } else {
//            log.warn("collegeNo {}에 해당하는 단과대학을 찾을 수 없습니다.", collegeNo);
//            
//            return "redirect:/staff/college/collegeList.do";
//        }
//    }

	/**
	 * 단과대학 등록
	 * @return
	 */
	@GetMapping("collegeInsert.do")
	public String collegeInsert() {
		return "pfcp/staff/college/collegeInsert";
	}
	
	// 새로운 단과대학 등록 처리 (POST 요청)
	@PostMapping("insertCollege.do") // 이 매핑이 'collegeInsert.jsp'의 폼 action과 일치해야 합니다.
	public String insertCollege(CollegeVO college) { // 폼에서 넘어온 'collegeName'이 CollegeVO에 자동으로 매핑됩니다.

		// 서비스 계층을 호출하여 DB에 데이터 저장
		int res = service.createCollege(college);

		if (res > 0) {
			return "redirect:/staff/college/collegeList.do";
		} else {
			
			return "pfcp/staff/college/collegeInsert";
		}
	}
	
	/**
	 * 졸업 요건 상세조회
	 * @return
	 */
	@GetMapping("graduateDetail.do")
	public String graduateDetail() {  // model
		return "pfcp/staff/college/graduateDetail";//(graduateInsert)  // frags 주기 // 등록, 수정 페이지와 같게
	}
	
	/**
	 * 졸업 신청 조회
	 * @return
	 */
	@GetMapping("graduateApply.do")
	public String graduateApply(
		Model model
	) {
		List<GraduationRequestVO> GraduationRequest = graduationRequestService.readGraduationRequestList();
		log.info("{} >>>>>" ,GraduationRequest);
		model.addAttribute("gradReqList", GraduationRequest);
		return "pfcp/staff/college/graduateApply";
	}
	
	/**
	 * 이수 학점 상세 조회
	 * @return
	 */
	@GetMapping("graduateCreditDetail.do")
	public String graduateCreditDetail() {
		return "pfcp/staff/college/graduateCreditDetail";
	}
	
}


