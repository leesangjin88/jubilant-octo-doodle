package kr.or.ddit.pfcp.student.lecture.grade.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.CounselReqVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;
import kr.or.ddit.pfcp.common.vo.SemesterVO;
import kr.or.ddit.pfcp.common.vo.SubjectVO;
import kr.or.ddit.pfcp.student.lecture.grade.service.StudentGradeService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 액터의 성적 관리
 * 
 * @author seokyungdeok
 * @since 250630
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250630	|	서경덕	|	최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/student/grade")
public class StudentGradeController {
	@Autowired
	private StudentGradeService studentGradeService;
	
	static final String MODEL_NAME = "counselReq";
	
	static final String LECENR_MODEL_NAME = "lectureEnr";

	@Autowired
	private ErrorsUtils errorsUtils;
	
	@ModelAttribute(MODEL_NAME)
	public CounselReqVO counselReq() {
		return new CounselReqVO();
	}
	
	@ModelAttribute(LECENR_MODEL_NAME)
	public LectureEnrVO lectureEnr() {
		return new LectureEnrVO();
	}
	
	@GetMapping("semesterList.do")
	@ResponseBody
	public List<SemesterVO> semesterList() {
		List<SemesterVO> semesterList = studentGradeService.readSemesterList();
		
		return semesterList;
	}
	
	@GetMapping("subjectList.do")
	@ResponseBody
	public List<LectureEnrVO> subjectList(Principal principal) {
		List<LectureEnrVO> subjectList = studentGradeService.readSubjectList(principal.getName());
		
		return subjectList;
	}
	
	@GetMapping("historyList.do")
	@ResponseBody
	public List<LectureEnrVO> gradeListAjax(
	    @RequestParam(required = false) String year,
	    @RequestParam(required = false) String semester,
	    @RequestParam(required = false) String allTimeYn,
	    Principal principal
	) {
	    String userNo = principal.getName();
	    String semesterNo = null;
	    
	    if (!"Y".equals(allTimeYn) && year != null && !year.isEmpty() && semester != null && !semester.isEmpty()) {
	        semesterNo = year + "_" + semester.substring(0, 1);
	    }

	    List<LectureEnrVO> lectureEnrList = studentGradeService.readStudentGradeList(userNo, semesterNo);

	    for (LectureEnrVO lectureEnr : lectureEnrList) {
	        String grade = lectureEnr.getGrade();
	        
	        switch (grade) {
	            case "A+":
	                lectureEnr.setGradePoint(4.5); 
	                
	                break;
	            case "A0":
	                lectureEnr.setGradePoint(4.0); 
	                
	                break;
	            case "B+":
	                lectureEnr.setGradePoint(3.5); 
	                
	                break;
	            case "B0":
	                lectureEnr.setGradePoint(3.0); 
	                
	                break;
	            case "C+":
	                lectureEnr.setGradePoint(2.5); 
	                
	                break;
	            case "C0":
	                lectureEnr.setGradePoint(2.0); 
	                
	                break;
	            case "D+":
	                lectureEnr.setGradePoint(1.5); 
	                
	                break;
	            case "D0":
	                lectureEnr.setGradePoint(1.0);
	                
	                break;
	            case "F":
	                lectureEnr.setGradePoint(0); 
	                
	                break;
	        }
	    }
	    
	    return lectureEnrList;
	}

	
	/**
	 * 전체 학기 성적 조회
	 * 
	 * @return
	 */
	@GetMapping("history.do")
	public String gradeList(
		@RequestParam(required = false) String year,
	    @RequestParam(required = false) String semester,
	    @RequestParam(required = false) String allTimeYn,
		Principal principal,
		Model model
	) {
		String userNo = principal.getName();
		String semesterNo = null;
		
		if (!"Y".equals(allTimeYn) && year != null && semester != null) {
	        semesterNo = year + "_" + semester.substring(0, 1);
	    }

	    List<LectureEnrVO> lectureEnrList = studentGradeService.readStudentGradeList(userNo, semesterNo);

		
		for (LectureEnrVO lectureEnr : lectureEnrList) {
			String grade = lectureEnr.getGrade();
			
			switch (grade) {
				case "A+":
					lectureEnr.setGradePoint(4.5);
					
					break;
				case "A0":
					lectureEnr.setGradePoint(4.0);
					
					break;
				case "B+":
					lectureEnr.setGradePoint(3.5);
					
					break;
				case "B0":
					lectureEnr.setGradePoint(3.0);
					
					break;
				case "C+":
					lectureEnr.setGradePoint(2.5);
					
					break;
				case "C0":
					lectureEnr.setGradePoint(2.0);
					
					break;
				case "D+":
					lectureEnr.setGradePoint(1.5);
					
					break;
				case "D0":
					lectureEnr.setGradePoint(1.0);
					
					break;
				
				case "F":
					lectureEnr.setGradePoint(0);
					
					break;
			}
		}
		
		model.addAttribute("semesterNo", lectureEnrList.get(0).getSemester().getSemesterNo());
		model.addAttribute("lectureEnrList", lectureEnrList);
		
		return "pfcp/student/grade/gradeHistory";
	}
	
	/**
	 * 성적 이의 신청 목록 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("appeal.do")
	public String gradeAppealList(
		Principal principal,
		Model model
	) {
		String studentNo = principal.getName();
		
		List<CounselReqVO> gradeAppealList = studentGradeService.readStudentGradeAppealList(studentNo);
		
		log.info("체킁 : {}", gradeAppealList);
		
		int count = gradeAppealList.size();
		
		model.addAttribute("gradeAppealList", gradeAppealList);
		model.addAttribute("count", count);
		
		return "pfcp/student/grade/gradeAppeal";
	}
	
	/**
	 * 성적 이의 신청 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("appealDetail.do")
	public String gradeAppealDetail() {
		return "pfcp/student/grade/gradeAppealDetail";
	}
	
	/**
	 * 성적 이의 신청 등록(formUI)
	 * 
	 * @return
	 */
	@GetMapping("appealInsert.do")
	public String gradeAppealInsert(
		Principal principal, 
		Model model
	) {
		String studentNo = principal.getName();  // 로그인된 사용자 ID (userNo)
	    model.addAttribute("studentNo", studentNo);
		
		return "pfcp/student/grade/gradeAppealInsert";
	}
	
	/**
	 * 성적 이의 신청 등록(formProcess)
	 * 
	 * @return
	 */
	@PostMapping("appealInsertFormProcess.do")
	public String gradeAppealInsertFormProcess(
		@ModelAttribute(MODEL_NAME) CounselReqVO counselReq,
		Principal principal,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if (!errors.hasErrors()) {
			counselReq.setUserNo(principal.getName());
			
			studentGradeService.createStudentGradeAppeal(counselReq);
			
			lvn = "redirect:/student/grade/appeal.do";
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, counselReq);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/grade/appealInsertFormProcess.do";
		}
		
		return lvn;
	}
	
	/**
	 * 성적 이의 신청 삭제
	 * 
	 * @return
	 */
	@GetMapping("appealDelete.do")
	public String gradeAppealDelete() {
		return "redirect:/student/grade/appeal.do";
	}
	
	/**
	 * 성적 이의 신청 수정
	 * 
	 * @return
	 */
	@GetMapping("appealUpdate.do")
	public String gradeAppleaUpdate() {
		return "pfcp/student/grade/gradeAppealUpdate";
	}
}
