package kr.or.ddit.pfcp.staff.facility.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO;
import kr.or.ddit.pfcp.staff.facility.service.FacilityService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250628
 */
@Slf4j
@Controller
@RequestMapping("/staff/facility")
public class FacilityController {

	@Autowired
	private FacilityService service;
	
	static final String MODELNAME = "facility";

	@Autowired
	private ErrorsUtils errorsUtils;
	
	@ModelAttribute(MODELNAME)
	public FacilityVO facility() {
		return new FacilityVO();
	}
	
	/**
	 * 시설물 목록 전체 조회
	 * @return
	 */
	@GetMapping("facilityList.do")
	public String facilityListUI(Model model) {
		List<FacilityVO> facility = service.readFacilityList();
		model.addAttribute("facility", facility);
		return "pfcp/staff/facility/facilityList";
	}

	/**
	 * 시설물 상세 조회
	 * @return
	 */
	@GetMapping("facilityDetail.do")
	public String facilityDetailUI(
		@RequestParam String what,
		Model model
	) {
		FacilityVO facility = service.readFacility(what);
		model.addAttribute("facility", facility);
		
		return "pfcp/staff/facility/facilityDetail";
	}

	/**
	 * 시설물 등록
	 * 
	 * @return
	 */
	@GetMapping("facilityInsert.do")
	public String facilityInsertUI(
		Principal principal
	) {
		return "pfcp/staff/facility/facilityInsert";
	}
	
	/**
	 * 시설 등록 formProcess
	 * 
	 * @return
	 */
	@PostMapping("facilityInsertProcess.do")
	public String facilityInsertFormProcess(
		@ModelAttribute(MODELNAME) FacilityVO facility,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if(!errors.hasErrors()) {
			service.createFacility(facility);
			
			lvn = "redirect:/staff/facility/facilityList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, facility);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/staff/facility/facilityInsert.do";
		}
		return lvn;
	}

	/**
	 * 시설물 수정
	 * 
	 * @return
	 */
	@GetMapping("facilityUpdate.do")
	public String facilityUpdateUI(
		String what,
		Model model
	) {
		FacilityVO facility = service.readFacility(what);
		model.addAttribute("facility", facility);
		model.addAttribute("mode", "edit");
		return "pfcp/staff/facility/facilityDetail";
	}

	/**
	 * 시설 수정 formProcess
	 * 
	 * @return
	 */
	@PostMapping("facilityUpdateProcess.do")
	public String facilityUpdateProcess(
		String what, 
		@ModelAttribute(MODELNAME) FacilityVO facility,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		
		if (!errors.hasErrors()) {
			service.modifyFacility(facility);
			lvn = "redirect:/staff/facility/facilityList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, facility);
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			lvn = "redirect:/staff/facility/facilityUpdate.do?what=" + what;
		}
		
		return lvn;
	}
	
	//=================================================================================================
	
	

	
	
	/**
	 * 시설물 건의사항 목록 전체 조회
	 * 
	 * @return
	 */
	@GetMapping("facilityAppealList.do")
	public String facilityAppealListUI() {
		return "pfcp/staff/facility/facilityAppealList";
	}

	/**
	 * 시설물 건의사항 목록 상세 조회
	 * 
	 * @return
	 */
	@GetMapping("facilityAppealDetail.do")
	public String facilityAppealDetailUI() {
		return "pfcp/staff/facility/facilityAppealDetail";
	}

	/**
	 * 시설물 건의사항 목록 상태 변경(수정)
	 * 
	 * @return
	 */
	@GetMapping("facilityAppealUpdate.do")
	public String facilityAppealUpdateUI() {
		return "pfcp/staff/facility/facilityAppealDetail";
	}
}
