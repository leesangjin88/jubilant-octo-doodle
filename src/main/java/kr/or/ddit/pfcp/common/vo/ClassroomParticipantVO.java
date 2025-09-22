package kr.or.ddit.pfcp.common.vo;

import lombok.Data;

@Data
public class ClassroomParticipantVO {
  private Integer participantNo;
  private Integer vcNo;
  private String userNo;
  private String joinTime;
  private String leaveTime;
  private String attendenceStatus;
  private String participantRole;
  private String isCameraOn;
  private String isCamOn;
  private String isMicOn;
  private String isScreenShareOn;
  private String field;
}
