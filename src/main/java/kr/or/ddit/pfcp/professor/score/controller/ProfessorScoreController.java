package kr.or.ddit.pfcp.professor.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author 김태수
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	김태수	|	최초 생성
 */
@Controller
@RequestMapping("/professor/score")
public class ProfessorScoreController {
	

	/**
	 * 성적 목록 조회
	 * @return
	 */
	@GetMapping("scoretList.do")
	public String scoretList() {
		return "pfcp/professor/score/scoretList";
	}
	
	/**
	 * 성적 상세 조회
	 * @return
	 */
	@GetMapping("scoretDetail.do")
	public String scoretDetail() {
		return "pfcp/professor/score/scoretDetail";
	}
	
	/**
	 * 성적 등록
	 * @return
	 */
	@GetMapping("scoretInsert.do")
	public String scoretInsert() {
		return "pfcp/professor/score/scoretForm";
	}
	
	/**
	 * 성적 수정
	 * @return
	 */
	@GetMapping("scoretUpdate.do")
	public String scoretUpdate() {
		return "pfcp/professor/score/scoretUpdateForm";
	}
	
	/**
	 * 성적 삭제
	 * @return
	 */
	@GetMapping("scoretDelete.do")
	public String scoretDelete() {
		return "redirect:/professor/score/scoretList.do";
	}
	
	
	/**
	 * 성적 피드백 상세 조회
	 * @return
	 */
	@GetMapping("scoretFeedbackDetail.do")
	public String scoretFeedbackDetail() {
		return "pfcp/professor/score/scoretFeedbackDetail";
	}
	
	/**
	 * 성적 피드백 등록
	 * @return
	 */
	@GetMapping("scoretFeedbackInsert.do")
	public String scoretFeedbackInsert() {
		return "pfcp/professor/score/scoretFeedbackForm";
	}
	
	/**
	 * 성적 피드백 수정
	 * @return
	 */
	@GetMapping("scoretFeedbackUpdate.do")
	public String scoretFeedbackUpdate() {
		return "pfcp/professor/score/scoretFeedbackUpdateForm";
	}
	
	/**
	 * 성적 데이터 분포 조회
	 * @return
	 */
	@GetMapping("scoreDistribution.do")
	public String scoreDistribution() {
		return "pfcp/professor/score/scoreDistribution";
	}
	
	/**
	 * 성적 분포 보고서
	 * @return
	 */
	@GetMapping("scoreDistributionReport.do")
	public String scoreDistributionReport() {
		return "pfcp/professor/score/scoreDistributionReport";
	}
	
	
}
