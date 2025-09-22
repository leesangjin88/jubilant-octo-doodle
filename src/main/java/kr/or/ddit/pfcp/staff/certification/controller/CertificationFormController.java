package kr.or.ddit.pfcp.staff.certification.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YSM
 * @since 250630
 */
@Controller
@RequestMapping("/staff/certification")
public class CertificationFormController {
	
	/**
	 * 증명서 폼 목록 전체 조회
	 * @return
	 */
	@GetMapping("certificationFormList.do")
	public String certificationFormListUI() {
		return "pfcp/staff/certification/certificationFormList";
	}
	
	/**
	 * 증명서 폼 상세 조회
	 * @return
	 */
	@GetMapping("certificationFormDetail.do")
	public String certificationFormDetailUI() {
		return "pfcp/staff/certification/certificationFormDetail";
	}
	
	/**
	 * 증명서 폼 등록
	 * @return
	 */
	@GetMapping("certificationFormInsert.do")
	public String certificationFormInsertUI() {
		return "pfcp/staff/certification/certificationFormInsert";
	}
	
	/**
	 * 증명서 폼 수정
	 * @return
	 */
	@GetMapping("certificationFormUpdate.do")
	public String certificationFormUpdateUI() {
		return "pfcp/staff/certification/certificationFormDetail";
	}
}
