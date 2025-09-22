package kr.or.ddit.pfcp.student.counsel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author seokyungdeok
 * @since 250630
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250630	|	서경덕	|	최초 생성
 */
@Controller
@RequestMapping("/student/counsel/graduationSuspension")
public class StudentGraduationSuspensionController {
	/**
	 * 졸업 유예 신청 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("graduationSuspension.do")
	public String graduationSuspensionList() {
		return "pfcp/student/counsel/graduationSuspension/graduationSuspensionList";
	}
	
	/**
	 * 졸업 유예 신청 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("graduationSuspensionDetail.do")
	public String graduationSuspensionDetail() {
		return "pfcp/student/counsel/graduationSuspension/graduationSuspensionDetail";
	}
	
	/**
	 * 졸업 유예 신청 등록
	 * 
	 * @return
	 */
	@GetMapping("graduationSuspensionInsert.do")
	public String graduationSuspensionInsert() {
		return "pfcp/student/counsel/graduationSuspension/graduationSuspensionInsert";
	}
	
	/**
	 * 졸업 유예 신청 수정
	 * 
	 * @return
	 */
	@GetMapping("graduationSuspensionUpdate.do")
	public String graduationSuspensionUpdate() {
		return "pfcp/student/counsel/graduationSuspension/graduationSuspensionUpdate";
	}
	
	/**
	 * 졸업 유예 신청 삭제
	 * 
	 * @return
	 */
	@GetMapping("graduationSuspensionDelete.do")
	public String graduationSuspensionDelete() {
		return "redirect:/student/counsel/graduationSuspension/graduationSuspension.do";
	}
}
