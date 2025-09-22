package kr.or.ddit.pfcp.common.vo;

import lombok.Data;

/**
 * 알림 DTO
 * 
 * @author seokyungdeok
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	서경덕	|	최초 생성
 */
@Data
public class NotificationVO {
    private String notiNo;
    private String userNo;
    private String message;
    private String linkUrl;
    private String iconClass;
    private String faIcon;
    private String timeAgo;
    private String isRead;
    private String createdAt;
}
