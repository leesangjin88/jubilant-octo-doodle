package kr.or.ddit.pfcp.student.bill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author seokyungdeok
 * @since 250630
 */
@Controller
@RequestMapping("/student")
public class StudentTuitionController {
	
	/**
	 * 학생 액터 본인 등록금 고지서 조회
	 * 
	 * @return
	 */
	@GetMapping("tuition.do")
	public String tuitionBill() {
		return "pfcp/student/tuition/tuition";
	}
}
