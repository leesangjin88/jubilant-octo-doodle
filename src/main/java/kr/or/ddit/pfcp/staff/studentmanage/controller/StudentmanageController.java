package kr.or.ddit.pfcp.staff.studentmanage.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.StudentVO;
import kr.or.ddit.pfcp.staff.studentmanage.service.StudentmanageService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250628
 */
@Slf4j
@Controller
@RequestMapping("/staff/studentmanage")
public class StudentmanageController {
	
	static final String MODELNAME = "student";
	
	@ModelAttribute(MODELNAME)
	public StudentVO student() {
		return new StudentVO();
	}
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	@Autowired
	private StudentmanageService service;
	
	
	
	/**
	 * 학생 목록 전체 조회
	 * @return
	 */
	@GetMapping("studentList.do")
	public String studentListUI(
			Model model,
			@RequestParam(required = false) String searchType,
            @RequestParam(required = false) String searchKeyword,
			@RequestParam(defaultValue = "1") int pageNo
			
	) {
		
		
//		검색 시작 !!!! ====================================
		Map<String, Object> searchParams = new HashMap<>();
        searchParams.put("searchType", searchType);
        searchParams.put("searchKeyword", searchKeyword);
        
//        List<StudentVO> studentList = studentService.getStudents(searchParams);
        
        model.addAttribute("searchType", searchType); // Pass back to JSP to retain selection
        model.addAttribute("searchKeyword", searchKeyword);
//		검색 끝  !!!! ====================================	
        
        
        
		int totalCnt = service.readTotalCount();
		int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;
	    int totalPage = (int) Math.ceil((double) totalCnt / pageSize);
	    
		List<StudentVO> student = service.readStudentList(offset, pageSize);
		model.addAttribute("student", student);
		
		model.addAttribute("totalCount", totalCnt);
		model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);
		
		return "pfcp/staff/studentmanage/studentList";
	}
	
	/**
	 * 학생 상세 조회
	 * @return
	 */
	@GetMapping("studentDetail.do")
	public String studentDetailUI(
			@RequestParam String what,
			Model model
	) {
		StudentVO student = service.readStudent(what);
		model.addAttribute("student", student);
		
//		log.info("student"+ student);
		
		return "pfcp/staff/studentmanage/studentDetail";
	}
	
	/** 
	 * 학생 등록
	 * @return
	 */
	@GetMapping("studentInsert.do")
	public String studentInsertFormUI(
			Principal principal
	) {
		return "pfcp/staff/studentmanage/studentInsert";
	}
	
	/** 
	 * 학생 등록 fromProcess
	 * @return
	 */
	@PostMapping("studentInsertProcess.do")
	public String studentInsertFormProcess(
		@ModelAttribute(MODELNAME) StudentVO student,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if(!errors.hasErrors()) {
			service.createStudent(student);
			redirectAttributes.addFlashAttribute("success", "등록 완료!");
			lvn = "redirect:/staff/studentmanage/studentList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, student);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/staff/studentmanagement/studentInsert.do";
		}
		return lvn;
	}
	
	/**
	 * 학생 상세 정보 수정
	 * @return
	 */
	@GetMapping("studentUpdate.do")
	public String studentUpdateUI(
		String what,
		Model model
	) {
		StudentVO student = service.readStudent(what);
		model.addAttribute("student", student);
		model.addAttribute("mode", "edit");
		return "pfcp/staff/studentmanage/studentDetail";
	}
	
	/**
	 * 학생 상세 정보 수정 fromProcess
	 * @return
	 */
	@PostMapping("studentUpdateProcess.do")
	public String studentUpdateProcessUI(
		String what,
		@ModelAttribute(MODELNAME) StudentVO student,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		student.setUserNo(student.getUser().getUserNo());
		
		if(!errors.hasErrors()) {
			service.modifyStudent(student);
			redirectAttributes.addFlashAttribute("success", "수정 완료!");
			lvn = "redirect:/staff/studentmanage/studentDetail.do?what=" + student.getUserNo();
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, student);
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			lvn= "redirect:/staff/studentmanage/studentUpdate.do?what="+what;
		}
		
		
		return lvn;
	}
}



























