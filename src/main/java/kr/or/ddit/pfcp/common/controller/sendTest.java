package kr.or.ddit.pfcp.common.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.pfcp.common.service.NotificationServiceImpl;
import kr.or.ddit.pfcp.common.vo.NotificationVO;

/**
 * 테스트 컨트롤러
 * 
 * @author seokyungdeok
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	서경덕	|	최초 생성
 */
@Controller
@RequestMapping("/notif")
public class sendTest {
	@Autowired
	private NotificationServiceImpl notificationService;
	
	@GetMapping("send-test")
	@ResponseBody
	public String sendTest() {
	    NotificationVO noti = new NotificationVO();
	    
	    noti.setMessage("새 과제가 등록되었습니다!");
	    noti.setLinkUrl("/student/assignment/view/EX123456");
	    noti.setFaIcon("fa-file-alt");
	    noti.setIconClass("notif-info");
	    noti.setTimeAgo("방금 전");

	    notificationService.notifyUser("ST20220810", noti);
	    return "Sent!";
	}
	
	@GetMapping("test-alert")
	@ResponseBody
	public String testNotification() {
	    NotificationVO noti = new NotificationVO();
	    
	    noti.setNotiNo("NT" + UUID.randomUUID().toString().replace("-", "").substring(0, 12));
	    noti.setUserNo("ST20220810");
	    noti.setMessage("테스트 알림입니다!");
	    noti.setLinkUrl("/test/page");
	    noti.setFaIcon("fa-bell");
	    noti.setIconClass("notif-info");
	    noti.setTimeAgo("방금 전");
	    
	    notificationService.createNotification(noti);
	    
	    notificationService.notifyUser("ST20220810", noti);

	    return "알림 발송됨!";
	}
	
	@PostMapping("/notifications/read")
	@ResponseBody
	public ResponseEntity<Void> readNotification(@RequestBody Map<String, String> body) {
	    String notiNo = body.get("notiNo");
	    
	    notificationService.markAsRead(notiNo); // DB 업데이트
	    
	    return ResponseEntity.ok().build();
	}
}
