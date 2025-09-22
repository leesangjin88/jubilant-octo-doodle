package kr.or.ddit.pfcp.student.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KGM
 * @since 250630
 */
@Controller
@RequestMapping("/student/mypage")
public class MypageController {
	
	/**
	 * 마이페이지 조회
	 * @return
	 */
	@GetMapping("mypageList.do")
	public String mypage() {
		return "pfcp/student/mypage/mypageList";
	}
	
	/**
	 * 마이페이지 수정
	 * @return
	 */
	@GetMapping("UpdateMypage.do")
	public String mypageUpdate() {
		return "pfcp/student/mypage/mypageUpdate";
	}
}
