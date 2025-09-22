package kr.or.ddit.pfcp.common.vo;

import lombok.Data;

@Data
public class ChatMessageVO {
  private Integer messageNo;
  private Integer vcNo;
  private String senderNo;
  private String targetUserNo;
  private String messageType;
  private String messageContent;
  private String fileUrl;
  private String fileName;
  private String isPrivate;
  private String isDelete;
  private String messageCreatedAt;
}
