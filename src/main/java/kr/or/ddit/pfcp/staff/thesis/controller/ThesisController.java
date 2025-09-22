package kr.or.ddit.pfcp.staff.thesis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KGM
 * @since 250630
 */
@Controller
@RequestMapping("/staff/thesis")
public class ThesisController {

	/**
	 * 논문 목록 조회
	 * @return
	 */
	@GetMapping("thesisList.do")
	public String thesis() {
		return "pfcp/staff/thesis/thesisList";
	}
	
	/**
	 * 논문 등록
	 * @return
	 */
	@GetMapping("thesisInsert.do")
	public String thesisInsert() {
		return "pfcp/staff/thesis/thesisInsert";
	}
	
	/**
	 * 논문 상세 조회
	 * @return
	 */
	@GetMapping("thesisDetail.do")
	public String thesisDetail() {
		return "pfcp/staff/thesis/thesisDetail";
	}
}
