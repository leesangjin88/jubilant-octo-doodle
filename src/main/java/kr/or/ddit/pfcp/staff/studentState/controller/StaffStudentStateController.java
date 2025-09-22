package kr.or.ddit.pfcp.staff.studentState.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YSM
 * @since 250628
 */
@Controller
@RequestMapping("/staff/studentState")
public class StaffStudentStateController {
	
	/**
	 * 학생 학적 변경 신청 전체 조회
	 * @return
	 */
	@GetMapping("studentStateList.do")
	public String studentStateListUI() {
		return "pfcp/staff/studentState/studentStateList";
	}
	
	/**
	 * 학생 학적 변경 신청 상세 조회
	 * @return
	 */
	@GetMapping("studentStateDetail.do")
	public String studentStateDetailUI() {
		return "pfcp/staff/studentState/studentStateDetail";
	}
	
	/**
	 * 학생 학적 변경 신청 적용(학적 정보 수정)
	 * @return
	 */
	@GetMapping("studentStateUpdate.do")
	public String studentStateUpdateUI() {
		return "pfcp/staff/studentmanage/studentDetail";
	}
}
