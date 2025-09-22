package kr.or.ddit.pfcp.student.bill.tuitionpolicy.controller;

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
@RequestMapping("/student/tuition")
public class StudentTuitionPolicyController {
	/**
	 * 학생 액터 본인 등록금 기준 조회
	 * ex) 공과대학 : OOO 만원,
	 *     사범대학 : OOO 만원 ...
	 * 
	 * @return
	 */
	@GetMapping("tuitionPolicy.do")
	public String tuitionPolicy() {
		return "pfcp/student/tuition/tuitionPolicy";
	}
}
