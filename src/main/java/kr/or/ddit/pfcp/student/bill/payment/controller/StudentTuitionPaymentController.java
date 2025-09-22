package kr.or.ddit.pfcp.student.bill.payment.controller;

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
public class StudentTuitionPaymentController {
	/**
	 * 학생 액터 본인 등록금 납부 내역 조회
	 * 
	 * @return
	 */
	@GetMapping("tuitionPayment.do")
	public String tuitionPayment() {
		return "pfcp/student/tuition/tuitionPayment";
	}
	
	/**
	 * 학생 액터 본인 등록금 납부 영수증 조회
	 * 
	 * @return
	 */
	@GetMapping("tuitionReceipt.do")
	public String tuitionReceipt() {
		return "pfcp/student/tuition/tuitionReceipt";
	}
}
