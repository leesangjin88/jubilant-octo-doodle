package kr.or.ddit.pfcp.student.state.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.AcademicChangeRequestVO;
import kr.or.ddit.pfcp.common.vo.ProfessorVO;
import kr.or.ddit.pfcp.common.vo.StudentVO;
import kr.or.ddit.pfcp.student.state.service.StudentStateService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 액터 본인 학적 조회
 * 
 * @author seokyungdeok
 * @since 250630
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250630	|	서경덕	|	최초 생성
 */
@Controller(value = "studentState")
@RequestMapping("/student")
@RequiredArgsConstructor
@Slf4j
public class StudentStateController {
	private final StudentStateService studentStateService;
	
	static final String MODELNAME = "academicChangeRequest";
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	@ModelAttribute(MODELNAME)
	public AcademicChangeRequestVO professor() {
		return new AcademicChangeRequestVO();
	}
	
	/**
	 * 학적 조회
	 * 학적에서 전공 변경, 복수 전공, 휴학, 복학 관리 가능
	 * 
	 * @return
	 */
	@GetMapping("studentState.do")
	public String studentState(
		Principal principal,
		Model model
	) {
		String studentNo = principal.getName();
		
		StudentVO student = studentStateService.readStudentInfo(studentNo);
		
		List<AcademicChangeRequestVO> majorChangeList = studentStateService.readAcademicChangeRequestMajorChange(studentNo);
		List<AcademicChangeRequestVO> doubleList = studentStateService.readAcademicChangeRequestDouble(studentNo);
		List<AcademicChangeRequestVO> leaveApplyList = studentStateService.readAcademicChangeRequestLeaveApply(studentNo);
		List<AcademicChangeRequestVO> returnList = studentStateService.readAcademicChangeRequestReturn(studentNo);
		List<AcademicChangeRequestVO> graduationSuspensionList = studentStateService.readAcademicChangeRequestGraduationSuspension(studentNo);
		
		model.addAttribute("majorChangeList", majorChangeList);
		model.addAttribute("doubleList", doubleList);
		model.addAttribute("leaveApplyList", leaveApplyList);
		model.addAttribute("returnList", returnList);
		model.addAttribute("graduationSuspensionList", graduationSuspensionList);
		model.addAttribute("student", student);
		
		return "pfcp/student/state/studentState";
	}
	
	// 전공변경 CRUD start
	/**
	 * 전공 변경 신청 조회(List)
	 * 
	 * @return
	 */
	@GetMapping("counsel/majorChangeList.do")
	public String majorChangeList(
		Principal principal,
		Model model
	) {
		String studentNo = principal.getName();
		
		StudentVO student = studentStateService.readStudentInfo(studentNo);
		
		List<AcademicChangeRequestVO> majorChangeList = studentStateService.readAcademicChangeRequestMajorChange(studentNo);
		
		int count = majorChangeList.size();
		
		model.addAttribute("majorChangeList", majorChangeList);
		model.addAttribute("student", student);
		model.addAttribute("count", count);
		
		return "pfcp/student/state/majorChange/majorChange";
	}
	
	@GetMapping("counsel/majorChangeDetail.do")
	public String majorChangeDetail(
		@RequestParam String no,
		Model model
	) {
		AcademicChangeRequestVO majorChange = studentStateService.readAcademicChangeRequestMajorChangeDetail(no);
		
		model.addAttribute("majorChange", majorChange);
		
		return "pfcp/student/state/majorChange/majorChangeDetail";
	}
	
	/**
	 * 전공 변경 신청 등록 (FormUI)
	 * 
	 * @return
	 */
	@GetMapping("counsel/majorChangeInsert.do")
	public String majorChangeInsertFormUI(
		Principal principal,
		Model model
	) {
		StudentVO student = studentStateService.readStudentInfo(principal.getName());
		
		model.addAttribute("student", student);
		
		return "pfcp/student/state/majorChange/majorChangeForm";
	}
	
	/**
	 * 전공 변경 신청 등록 (FormProcess)
	 * 
	 * @return
	 */
	@PostMapping("counsel/majorChangeInsertFormProcess.do")
	public String majorChangeInsertFormProcess(
		Principal principal,
		@ModelAttribute AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		String userNo = principal.getName();
		
		if(!errors.hasErrors()) {
			academicChangeRequest.setUserNo(userNo);
			
			studentStateService.createAcademicChangeRequestMajorChange(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/majorChangeList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/majorChangeInsert.do";
		}
		
		return lvn;
	}
	
	/**
	 * 전공 변경 신청 삭제
	 * 
	 * @return
	 */
	@GetMapping("counsel/majorChangeDelete.do")
	public String majorChangeDelete(
		@RequestParam(value = "no") String no,
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest
	) {
		studentStateService.removeAcademicChangeRequestMajorChange(no);
		
		return "redirect:/student/counsel/majorChangeList.do";
	}

	/**
	 * 전공 변경 신청 수정
	 * 
	 * @return
	 */
	@PostMapping("counsel/majorChangeUpdate.do")
	public String majorChangeUpdate(
		String no, 
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if (!errors.hasErrors()) {
			studentStateService.modifyAcademicChangeRequestMajorChange(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/majorChangeList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/majorChangeDetail.do";
		}
		
		return lvn;
	}
	
	// 복수 전공 CRUD start
	/**
	 * 복수 전공 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("counsel/doubleMajorList.do")
	public String doubleMajor(
		Principal principal,
		Model model
	) {
		String studentNo = principal.getName();
		
		StudentVO student = studentStateService.readStudentInfo(studentNo);
		
		List<AcademicChangeRequestVO> doubleList = studentStateService.readAcademicChangeRequestDouble(studentNo);
		
		int count = doubleList.size();
		
		model.addAttribute("doubleList", doubleList);
		model.addAttribute("student", student);
		model.addAttribute("count", count);
		
		return "pfcp/student/state/doubleMajor/doubleMajor";
	}
	
	/**
	 * 복수 전공 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("counsel/doubleMajorDetail.do")
	public String doubleMajorDetail(
		@RequestParam String no,
		Model model
	) {
		AcademicChangeRequestVO doubleMajor = studentStateService.readAcademicChangeRequestDoubleDetail(no);
		
		model.addAttribute("doubleMajor", doubleMajor);
		
		return "pfcp/student/state/doubleMajor/doubleMajorDetail";
	}
	
	/**
	 * 복수 전공 신청 등록 (FormUI)
	 * 
	 * @return
	 */
	@GetMapping("counsel/doubleMajorInsert.do")
	public String doubleMajorInsertFormUI(
		Principal principal,
		Model model
	) {
		StudentVO student = studentStateService.readStudentInfo(principal.getName());
		
		model.addAttribute("student", student);
		
		return "pfcp/student/state/doubleMajor/doubleMajorForm";
	}
	
	/**
	 * 복수 전공 신청 등록 (FormProcess)
	 * 
	 * @return
	 */
	@PostMapping("counsel/doubleMajorInsertFormProcess.do")
	public String doubleMajorInsertFormProcess(
		Principal principal,
		@ModelAttribute AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		String userNo = principal.getName();
		
		if(!errors.hasErrors()) {
			academicChangeRequest.setUserNo(userNo);
			
			studentStateService.createAcademicChangeRequestDouble(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/doubleMajorList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/doubleMajorInsert.do";
		}
		
		return lvn;
	}
	
	/**
	 * 복수 전공 신청 수정
	 * 
	 * @return
	 */
	@PostMapping("counsel/doubleMajorUpdate.do")
	public String doubleMajorUpdate(
		String no, 
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if (!errors.hasErrors()) {
			studentStateService.modifyAcademicChangeRequestMajorChange(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/doubleMajorList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/doubleMajorDetail.do";
		}
		
		return lvn;
	}
	
	/**
	 * 복수 전공 신청 삭제
	 * 
	 * @return
	 */
	@GetMapping("counsel/doubleMajorDelete.do")
	public String doubleMajorDelete(
		@RequestParam(value = "no") String no,
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest
	) {
		studentStateService.removeAcademicChangeRequestMajorChange(no);
		
		return "redirect:/student/counsel/doubleMajorList.do";
	}
	
	// 휴학 CRUD start
	/**
	 * 휴학 신청 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("counsel/leaveApplyList.do")
	public String leaveApply(
		Principal principal,
		Model model
	) {
		String studentNo = principal.getName();
		
		StudentVO student = studentStateService.readStudentInfo(studentNo);
		
		List<AcademicChangeRequestVO> leaveApplyList = studentStateService.readAcademicChangeRequestLeaveApply(studentNo);
		
		int count = leaveApplyList.size();
		
		model.addAttribute("leaveApplyList", leaveApplyList);
		model.addAttribute("student", student);
		model.addAttribute("count", count);
		
		return "pfcp/student/state/leaveApply/leaveApply";
	}
	
	/**
	 * 휴학 신청 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("counsel/leaveApplyDetail.do")
	public String leaveApplyDetail(
		@RequestParam String no,
		Model model
	) {
		AcademicChangeRequestVO leaveApply = studentStateService.readAcademicChangeRequestLeaveApplyDetail(no);
		
		model.addAttribute("leaveApply", leaveApply);
		
		return "pfcp/student/state/leaveApply/leaveApplyDetail";
	}
	
	@GetMapping("counsel/leaveApplyInsert.do")
	public String leaveApplyInsert(
		Principal principal,
		Model model
	) {
		StudentVO student = studentStateService.readStudentInfo(principal.getName());
		
		model.addAttribute("student", student);
		
		return "pfcp/student/state/leaveApply/leaveApplyForm";
	}
	
	
	/**
	 * 휴학 신청 등록
	 * 
	 * @return
	 */
	@PostMapping("counsel/leaveApplyInsertFormProcess.do")
	public String leaveApplyInsertFormProcess(
		Principal principal,
		@ModelAttribute AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		String userNo = principal.getName();
		
		if(!errors.hasErrors()) {
			academicChangeRequest.setUserNo(userNo);
			
			studentStateService.createAcademicChangeRequestLeaveApply(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/leaveApplyList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/leaveApplyInsert.do";
		}
		
		return lvn;
	}
	
	/**
	 * 휴학 신청 삭제
	 * 
	 * @return
	 */
	@GetMapping("counsel/leaveApplyDelete.do")
	public String leaveApplyDelete(
		@RequestParam(value = "no") String no,
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest
	) {
		studentStateService.removeAcademicChangeRequestMajorChange(no);
		
		return "redirect:/student/counsel/leaveApplyList.do";
	}
	
	/**
	 * 휴학 신청 수정
	 * 
	 * @return
	 */
	@PostMapping("counsel/leaveApplyUpdate.do")
	public String leaveApplyUpdate(
		String no, 
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if (!errors.hasErrors()) {
			studentStateService.modifyAcademicChangeRequestMajorChange(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/leaveApplyList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/leaveApplyDetail.do";
		}
		
		return lvn;
	}
	
	// 복학 신청 CRUD start
	/**
	 * 복학 신청 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("counsel/returnApplyList.do")
	public String returnApply(
		Principal principal,
		Model model
	) {
		String studentNo = principal.getName();
		
		StudentVO student = studentStateService.readStudentInfo(studentNo);
		
		List<AcademicChangeRequestVO> returnApplyList = studentStateService.readAcademicChangeRequestReturn(studentNo);
		
		int count = returnApplyList.size();
		
		model.addAttribute("returnApplyList", returnApplyList);
		model.addAttribute("student", student);
		model.addAttribute("count", count);
		
		return "pfcp/student/state/returnApply/returnApply";
	}
	
	/**
	 * 복학 신청 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("counsel/returnApplyDetail.do")
	public String returnApplyDetail(
		@RequestParam String no,
		Model model
	) {
		AcademicChangeRequestVO returnApply = studentStateService.readAcademicChangeRequestReturnDetail(no);
		
		model.addAttribute("returnApply", returnApply);
		
		return "pfcp/student/state/returnApply/returnApplyDetail";
	}
	
	/**
	 * 복학 신청 등록
	 * 
	 * @return
	 */
	@GetMapping("counsel/returnApplyInsert.do")
	public String returnApplyInsert(
		Principal principal,
		Model model
	) {
		StudentVO student = studentStateService.readStudentInfo(principal.getName());
		
		model.addAttribute("student", student);
		
		return "pfcp/student/state/returnApply/returnApplyForm";
	}
	
	@PostMapping("counsel/returnApplyInsertFormProcess.do")
	public String returnApplyInsertFormProcess(
		Principal principal,
		@ModelAttribute AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		String userNo = principal.getName();
		
		if(!errors.hasErrors()) {
			academicChangeRequest.setUserNo(userNo);
			
			studentStateService.createAcademicChangeRequestReturn(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/returnApplyList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/returnApplyInsert.do";
		}
		
		return lvn;
	}
	
	/**
	 * 복학 신청 수정
	 * 
	 * @return
	 */
	@PostMapping("counsel/returnApplyUpdate.do")
	public String returnApplyUpdateProcess(
		String no, 
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if (!errors.hasErrors()) {
			studentStateService.modifyAcademicChangeRequestMajorChange(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/returnApplyList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/returnApplyDetail.do";
		}
		
		return lvn;
	}
	
	/**
	 * 복학 신청 삭제
	 * 
	 * @return
	 */
	@GetMapping("counsel/returnApplyDelete.do")
	public String returnApplyDelete(
		@RequestParam(value = "no") String no,
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest
	) {
		studentStateService.removeAcademicChangeRequestMajorChange(no);
		
		return "redirect:/student/counsel/returnApplyList.do";
	}
	
	/**
	 * 졸업 유예 목록
	 * 
	 * @return
	 */
	@GetMapping("counsel/graduationSuspensionList.do")
	public String graduationSuspensionList(
		Principal principal,
		Model model
	) {
		String studentNo = principal.getName();
		
		StudentVO student = studentStateService.readStudentInfo(studentNo);
		
		List<AcademicChangeRequestVO> graduationSuspensionApplyList = studentStateService.readAcademicChangeRequestGraduationSuspension(studentNo);
		
		int count = graduationSuspensionApplyList.size();
		
		model.addAttribute("graduationSuspensionApplyList", graduationSuspensionApplyList);
		model.addAttribute("student", student);
		model.addAttribute("count", count);
		
		return "pfcp/student/state/graduationSuspension/graduationSuspension";
	}
	
	@GetMapping("counsel/graduationSuspensionApplyDetail.do")
	public String graduationSuspensionApplyDetail(
		@RequestParam String no,
		Model model
	) {
		AcademicChangeRequestVO graduationSuspensionApply = studentStateService.readAcademicChangeRequestGraduationSuspensionDetail(no);
		
		model.addAttribute("graduationSuspensionApply", graduationSuspensionApply);
		
		return "pfcp/student/state/graduationSuspension/graduationSuspensionDetail";
	}
	
	/**
	 * 졸업 유예 신청 등록
	 * 
	 * @return
	 */
	@GetMapping("counsel/graduationSuspensionApplyInsert.do")
	public String graduationSuspensionInsert(
		Principal principal,
		Model model
	) {
		StudentVO student = studentStateService.readStudentInfo(principal.getName());
		
		model.addAttribute("student", student);
		
		return "pfcp/student/state/graduationSuspension/graduationSuspensionForm";
	}
	
	@PostMapping("counsel/graduationSuspensionApplyInsertFormProcess.do")
	public String graduationSuspensionInsertFormProcess(
		Principal principal,
		@ModelAttribute AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		String userNo = principal.getName();
		
		if(!errors.hasErrors()) {
			academicChangeRequest.setUserNo(userNo);
			
			studentStateService.createAcademicChangeRequestGraduationSuspension(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/graduationSuspensionList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/graduationSuspensionApplyInsert.do";
		}
		
		return lvn;
	}
	
	/**
	 * 졸업 유예 신청 수정
	 * 
	 * @return
	 */
	@PostMapping("counsel/graduationSuspensionApplyUpdate.do")
	public String graduationSuspensionApplyUpdate(
		String no, 
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if (!errors.hasErrors()) {
			studentStateService.modifyAcademicChangeRequestMajorChange(academicChangeRequest);
			
			lvn = "redirect:/student/counsel/graduationSuspensionList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, academicChangeRequest);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/counsel/graduationSuspensionApplyDetail.do";
		}
		
		return lvn;
	}
	
	@GetMapping("counsel/graduationSuspensionApplyDelete.do")
	public String graduationSuspensionApplyDelete(
		@RequestParam(value = "no") String no,
		@ModelAttribute(MODELNAME) AcademicChangeRequestVO academicChangeRequest
	) {
		studentStateService.removeAcademicChangeRequestMajorChange(no);
		
		return "redirect:/student/counsel/graduationSuspensionList.do";
	}
}
