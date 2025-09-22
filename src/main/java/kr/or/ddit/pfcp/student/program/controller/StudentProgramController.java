package kr.or.ddit.pfcp.student.program.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author seokyungdeok
 * @since 250701
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250701	|	서경덕	|	최초 생성
 */
@Controller
@RequestMapping("/student/program")
public class StudentProgramController {
	/**
	 * 비교과 프로그램 목록 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("programApplList.do")
	public String programApplList() {
		return "pfcp/student/program/programAppl";
	}
	
	/**
	 * 비교과 프로그램 상세 조회 (Detail)
	 * -> 신청 현황, 신청 가능 여부, 취소/변경 기간 조회, 승인 여부, 
	 *    일정, 장소, 출석 기록 조회, 참여 현황, 활동 점수, 평가 기준 조회 가능
	 * 
	 * @return
	 */
	@GetMapping("programApplDetail.do")
	public String programApplDetail() {
		return "pfcp/student/program/programApplDetail";
	}
	
	/**
	 * 비교과 프로그램 신청 등록
	 * 
	 * @return
	 */
	@GetMapping("programApplInsert.do")
	public String programApplInsert() {
		return "pfcp/student/program/programApplInsert";
	}
	
	/**
	 * 비교과 프로그램 신청 삭제
	 * 
	 * @return
	 */
	@GetMapping("programApplDelete.do")
	public String programApplDelete() {
		return "redirect:/student/programApplList.do";
	}
	
	/**
	 * 참여 중인 비교과 프로그램 목록 조회 (List)
	 * -> 프로그램 별 참여 횟수, 성취도 조회 가능
	 * 
	 * @return
	 */
	@GetMapping("myParticipationList.do")
	public String participationProgramList() {
		return "pfcp/student/program/participationProgram/participationProgram";
	}
	
	/**
	 * 참여 중인 비교과 프로그램 상세 조회 (Detail)
	 * -> 프로그램 이수 여부, 이수 기준 조회, 이수 인증서 발급 요청 등록 가능 
	 * 
	 * @return
	 */
	@GetMapping("myParticipationDetail.do")
	public String participationProgramDetail() {
		return "pfcp/student/program/participationProgram/participationProgramDetail";
	}
	
	/**
	 * 프로그램 성과 분석 및 피드백 조회
	 * 
	 * @return
	 */
	@GetMapping("feedbackAnalysis.do")
	public String feedbackAnalysis() {
		return "pfcp/student/program/participationProgram/participationProgramFeedbackAnalysis";
	}
	
	/**
	 * 프로그램 만족도 조사 등록
	 * 
	 * @return
	 */
	@GetMapping("surveyInsert.do")
	public String satisfactionSurveyInsert() {
		return "pfcp/student/program/participationProgram/participationProgramServeyForm";
	}
	
	/**
	 * 역량 평가 결과 조회
	 * 
	 * @return
	 */
	@GetMapping("myEvaluationResult.do")
	public String evaluationResult() {
		return "pfcp/student/program/participationProgram/evaluationResult";
	}
}
