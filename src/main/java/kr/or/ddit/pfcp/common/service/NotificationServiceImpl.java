package kr.or.ddit.pfcp.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.mapper.NotificationMapper;
import kr.or.ddit.pfcp.common.vo.NotificationVO;
import lombok.RequiredArgsConstructor;

@Service(value = "notificationService")
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private NotificationMapper notificationMapper;
	
	private final SimpMessagingTemplate messagingTemplate;
	
	public void notifyUser(String userNo, NotificationVO notification) {
		messagingTemplate.convertAndSend("/meeting/notify/" + userNo, notification);
	}

	@Override
	public List<NotificationVO> readUnreadByUser(String userNo) {
		
		return notificationMapper.selectUnreadByUser(userNo);
	}

	@Override
	public void createNotification(NotificationVO notification) {
		// TODO Auto-generated method stub
		notificationMapper.insertNotification(notification);
	}

	@Override
	public List<NotificationVO> readUnreadNotifications(String userNo) {
		// TODO Auto-generated method stub
		return notificationMapper.selectUnreadByUser(userNo);
	}

	@Override
	public void markAsRead(String notiNo) {
		// TODO Auto-generated method stub
		notificationMapper.markAsRead(notiNo);
	}
}
