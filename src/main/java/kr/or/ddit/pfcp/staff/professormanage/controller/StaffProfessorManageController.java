package kr.or.ddit.pfcp.staff.professormanage.controller;

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

import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.ProfessorVO;
import kr.or.ddit.pfcp.common.vo.TypeVO;
import kr.or.ddit.pfcp.staff.professormanage.service.StaffProfessorManageService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.RequiredArgsConstructor;

/**
 * @author seokyungdeok
 * @since 250630
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250630	|	서경덕	|	최초 생성
 *
 */
@Controller
@RequestMapping("/staff/professorManagement")
@RequiredArgsConstructor
public class StaffProfessorManageController {
	private final StaffProfessorManageService service;
	
	static final String MODELNAME = "professor";
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	@ModelAttribute(MODELNAME)
	public ProfessorVO professor() {
		return new ProfessorVO();
	}
	
	@GetMapping("bankCodeList.do")
	@ResponseBody
	public List<TypeVO> bankCodeList() {
		List<TypeVO> bankCodeList = service.readBankCodeList();
		
		return bankCodeList;
	}
	
	@GetMapping("departmentList.do")
	@ResponseBody
	public List<DepartmentVO> departmentList() {
		List<DepartmentVO> departmentList = service.readDepartmentList();
		
		return departmentList;
	}

	/**
	 * 교직원 액터 교수 등록 formUI
	 * 
	 * @return
	 */
	@GetMapping("professorInsert.do")
	public String professorInsertFormUI(
		Principal principal,
		Model model
	) {
		String newUserNo = service.readGenerateNewUserNo();
		
	    model.addAttribute("newUserNo", newUserNo);
	    
		return "pfcp/staff/professorManage/professorInsertForm";
	}
	
	/**
	 * 교수 등록 formProcess
	 * 
	 * @return
	 */
	@PostMapping("professorInsertProcess.do")
	public String professorInsertFormProcess(
		@ModelAttribute(MODELNAME) ProfessorVO professor,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if(!errors.hasErrors()) {
			service.createProfessor(professor);
			
			lvn = "redirect:/staff/professorManagement/professorList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, professor);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/staff/professorManagement/professorInsert.do";
		}
		return lvn;
	}
	
	/**
	 * 교수 목록 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("professorList.do")
	public String professorList(Model model) {
		List<ProfessorVO> professor = service.readProfessorList();
		
		int count = professor.size();
		
		model.addAttribute("professor", professor);
		model.addAttribute("count", count);
		
		return "pfcp/staff/professorManage/professorList";
	}
	
	/**
	 * 교수 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("professorDetail.do")
	public String professorDetail(
		@RequestParam String no,
		Model model
	) {
		ProfessorVO professor = service.readProfessor(no);
		
		model.addAttribute("professor", professor);
		
		return "pfcp/staff/professorManage/professorDetail";
	}
	
	/**
	 * 교수 퇴직
	 * 
	 * @return
	 */
	@GetMapping("professorDelete.do")
	public String professorDelete(
		@RequestParam String no
	) {
		service.removeProfessor(no);
		
		return "redirect:/staff/professorManagement/professorList.do";
	}
	
	/**
	 * 교수 수정 formUI
	 * 
	 * @return
	 */
	@GetMapping("professorUpdate.do")
	public String professorUpdate(
		String no,
		Model model
	) {
		ProfessorVO professor = service.readProfessor(no);
		
		model.addAttribute("professor", professor);
		
		return "pfcp/staff/professorManage/professorUpdateForm";
	}
	
	/**
	 * 교수 수정 formProcess
	 * 
	 * @return
	 */
	@PostMapping("professorUpdateProcess.do")
	public String professorUpdateProcess(
		String no, 
		@ModelAttribute(MODELNAME) ProfessorVO professor,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		professor.setUserNo(professor.getUser().getUserNo());
		
		if (!errors.hasErrors()) {
			if ("퇴직".equals(professor.getProStatus())) {
	            // 퇴직이면 deleteProfessor 수행

	            service.removeProfessor(professor.getUserNo());
	            service.modifyProfessor(professor);
	        } else {
	            // 그 외는 update 수행
	            service.modifyProfessor(professor);
	        }
			
			lvn = "redirect:/staff/professorManagement/professorList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, professor);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/staff/professorManagement/professorUpdate.do?no=" + no;
		}
		
		return lvn;
	}
}
