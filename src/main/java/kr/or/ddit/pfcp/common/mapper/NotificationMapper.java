package kr.or.ddit.pfcp.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface NotificationMapper {
	public List<NotificationVO> selectUnreadByUser(String userNo);
	
	public int insertNotification(NotificationVO notification);
	
	public List<NotificationVO> selectUnreadNotifications(String userNo);
	
	public int markAsRead(String notiNo);
}
