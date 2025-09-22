package kr.or.ddit.pfcp.common.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.NotificationVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	서경덕	|	최초 생성
 */
public interface NotificationService {
	public List<NotificationVO> readUnreadByUser(String userNo);
	
	public void createNotification(NotificationVO notification);
	
	public List<NotificationVO> readUnreadNotifications(String userNo);
	
	public void markAsRead(String notiNo);
}
