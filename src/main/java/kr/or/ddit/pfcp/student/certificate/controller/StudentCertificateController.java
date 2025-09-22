package kr.or.ddit.pfcp.student.certificate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 목록 조회 없음. 상세 조회만 있음.
 * 
 * @author seokyungdeok
 * @since 250630
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250630	|	서경덕	|	최초 생성
 */
@Controller
@RequestMapping("/student/certificate")
public class StudentCertificateController {
	/**
	 * 재학 증명서 조회
	 * 
	 * @return
	 */
	@GetMapping("enrollmentCertificate.do")
	public String enrollmentCertificate() {
		return "pfcp/student/certificate/enrollmentCertificate";
	}
	
	/**
	 * 졸업 증명서 조회
	 * 
	 * @return
	 */
	@GetMapping("graduationCertificate.do")
	public String graduationCertificate() {
		return "pfcp/student/certificate/graduationCertificate";
	}
	
	/**
	 * 성적 증명서 조회
	 * 
	 * @return
	 */
	@GetMapping("transcriptCertificate.do")
	public String transcriptCertificate() {
		return "pfcp/student/certificate/transcriptCertificate";
	}
	
	/**
	 * 증명서 발급 내역 조회
	 * 
	 * @return
	 */
	@GetMapping("issuedCertificateHistory.do")
	public String issuedCertificateHistory() {
		return "pfcp/student/certificate/issuedCertificateHistory";
	}
}
