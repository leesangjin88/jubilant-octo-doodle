package kr.or.ddit.pfcp.staff.lectureEvaluation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.EvalCriteriaVO;
import kr.or.ddit.pfcp.staff.lectureEvaluation.service.StaffLectureEvaluationService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250628
 */

@Slf4j
@Controller
@RequestMapping("/staff/lectureEvaluation")
public class StaffLectureEvaluationController {
	@Autowired
	private StaffLectureEvaluationService service;
	
	static final String MODELNAME = "lectureEvaluation";
	
	@ModelAttribute(MODELNAME)
	public EvalCriteriaVO lectureEvaluation() {
		return new EvalCriteriaVO();
	}
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	//=========================================================================================
	
	
	/**
	 * 강의 평가 목록 전체 조회
	 * @return
	 */
	@GetMapping("lectureEvalutaionForm.do")
	public String lectureEvalutaionFromUI(Model model) {
		List<EvalCriteriaVO> lectureEvaluation = service.readAttendanceList();
		model.addAttribute("lectureEvaluation", lectureEvaluation);
		
		if(model.containsAttribute("msg")) {
	        System.out.println("메시지: " + model.getAttribute("msg"));
	    }
		
		return "pfcp/staff/lectureEvaluation/staffLectureEvaluationForm";
	}
	
	/**
	 * 강의 평가 목록 등록
	 * @return
	 */
	
	@PostMapping("lectureEvalutaionFormUpdateProcess.do")
	public String lectureEvalutaionFormUpdateProcessUI(
			@RequestParam("criteriaNoList") List<String> criteriaNoList,
		    @RequestParam("criteriaDescList") List<String> criteriaDescList,
		    RedirectAttributes redirectAttributes
	) {
		for (int i = 0; i < criteriaNoList.size(); i++) {
	        String criteriaNo = criteriaNoList.get(i);
	        String criteriaDesc = criteriaDescList.get(i);
	        
	        Map<String, Object> param = new HashMap<>();
	        param.put("criteriaNo", criteriaNo);
	        param.put("criteriaDesc", criteriaDesc);


	        // service에서 기존 DESC와 비교 후, 다를 경우만 업데이트
	        service.modifyCriteriaDescIfChanged(criteriaNo, criteriaDesc);
	    }
		redirectAttributes.addFlashAttribute("msg", "변경 완료!");
		
		return "redirect:/staff/lectureEvaluation/lectureEvalutaionForm.do";
	}
}

















