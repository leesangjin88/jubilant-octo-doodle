package kr.or.ddit.common.component;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.pfcp.common.service.NotificationService;
import kr.or.ddit.pfcp.common.vo.NotificationVO;
import kr.or.ddit.security.auth.UserVOWrapper;

/**
 * @author seokyungdeok
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	서경덕	|	최초 생성
 */ 
@Component
public class NotificationInterceptor implements HandlerInterceptor {
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Principal principal = request.getUserPrincipal();
		
		if (principal instanceof Authentication) {
			UserVOWrapper user = (UserVOWrapper) ((Authentication) principal).getPrincipal();
            String userNo = user.getRealUser().getUserNo();

            List<NotificationVO> unread = notificationService.readUnreadNotifications(userNo);
            request.setAttribute("unreadNotis", unread);
        }
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
