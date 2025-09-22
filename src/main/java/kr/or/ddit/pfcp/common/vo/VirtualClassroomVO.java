package kr.or.ddit.pfcp.common.vo;

import lombok.Data;

@Data
public class VirtualClassroomVO {
  private Integer vcNo;
  private Integer sessionNo;
  private String olNo;
  private String vcTitle;
  private String accessCode;
  private String chatEnabled;
  private String screenShareEnabled;
  private String studMicEnabled;
  private String studCamEnabled;
  private String isActive;
  private String vcCreatedAt;
}
