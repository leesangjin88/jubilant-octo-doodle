package kr.or.ddit.pfcp.student.lecture.evaluate.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.EvalCriteriaVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureEvalScoreVO;
import kr.or.ddit.pfcp.common.vo.LectureEvalVO;
import kr.or.ddit.pfcp.student.lecture.evaluate.service.LectureEvaluationService;
import lombok.extern.slf4j.Slf4j;

/**
 * 학생 액터가 강의 평가의 기준을 조회하고 등록.
 * 
 * @author seokyungdeok
 * @since 2025. 7. 5.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 5.	|	서경덕	|	최초 생성
 */
@Controller
@RequestMapping("/student")
@Slf4j
public class LectureEvaluationController {
	
	@Autowired
	private LectureEvaluationService lectureEvaluationService;
	
	/**
	 * 강의 선택 화면
	 * 
	 * @return
	 */
	@GetMapping("/selectLecture.do")
	public String lectureSelect(
		Principal principal,
		Model model
	) {
		List<LectureEnrVO> lectureList = lectureEvaluationService.readLectureEnr(principal.getName());
		
		model.addAttribute("lectureList", lectureList);
		
		return "pfcp/student/lecture/selectLecture";
	}
	
	/**
	 * 강의 평가 등록 폼 (+ 기준 조회)
	 * 
	 * @return
	 */
	@GetMapping("/lectureEvaluation.do")
	public String lectureEvaluationForm(
		Principal principal,
		@RequestParam("enrollNo") String enrollNo,
		Model model,
		RedirectAttributes redirectAttributes
	) {
		List<LectureEnrVO> lectureList = lectureEvaluationService.readLectureEnr(principal.getName());
		List<EvalCriteriaVO> evalList = lectureEvaluationService.readLectureEvaluationList();
		
		boolean alreadyExists = lectureEvaluationService.existsEvalByEnrollNo(enrollNo);
	    
	    if (alreadyExists) {
	    	redirectAttributes.addFlashAttribute("message", "이미 평가를 등록한 강의입니다.");
            return "redirect:/student/selectLecture.do";
	    }
		
		LectureEnrVO selectedLecture = lectureList.stream()
											      .filter(l -> l.getEnrollNo().equals(enrollNo))
											      .findFirst()
											      .orElse(null);
		
		model.addAttribute("evalList", evalList);
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("selectedLecture", selectedLecture);
	    model.addAttribute("enrollNo", enrollNo);
		
		return "pfcp/student/lecture/lectureEvaluationForm";
	}
	
	@PostMapping("/completeEvaluation.do")
	public String insertEvaluation(
        Principal principal,
        @RequestParam Map<String, String> paramMap
    ) {
		String enrollNo = paramMap.get("enrollNo");
		
		int sum = 0;
		
		String evalNo = "EV" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String comment = paramMap.get("comment");

		LectureEvalScoreVO evalScore = new LectureEvalScoreVO();
		LectureEvalVO eval = new LectureEvalVO();
		
		evalScore.setEvalNo(evalNo);
		
		for (String key : paramMap.keySet()) {
	        if (key.startsWith("score_")) {
	            String score = paramMap.get(key);
	            String criteriaNoKey = "criteriaNo_" + key.split("_")[1];
	            String criteriaNo = paramMap.get(criteriaNoKey);

	            log.info("criteriaNo : {}, score : {}", criteriaNo, score);
	            
	            sum += Integer.parseInt(score);
	            
	            evalScore.setEvalScore(Integer.parseInt(score));
	            evalScore.setCriteriaNo(criteriaNo);
	            
	            lectureEvaluationService.createLectureEvalScore(evalScore);
	        }
	    }
				
		eval.setEvalNo(evalNo);
		eval.setEnrollNo(enrollNo);
		eval.setOverallScore(sum);
		eval.setComment(comment);
		
		lectureEvaluationService.createLectureEval(eval);
		lectureEvaluationService.modifyLectureEnrEvalYN(principal.getName());
		
		return "redirect:/student/selectLecture.do";
	}
}
