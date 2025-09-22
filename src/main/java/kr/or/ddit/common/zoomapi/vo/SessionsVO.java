package kr.or.ddit.common.zoomapi.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "sessionNo")
public class SessionsVO {
  @JsonProperty("session_no")
  private Integer sessionNo;
  @JsonProperty("title")
  private String sessionTitle;
  @JsonProperty("type")
  private String sessionType;
  @JsonProperty("start_time")
  private String startDate;
  @JsonProperty("th")
  private Integer sessionTh;
  private String attendanceRequired;
  @JsonProperty("zoomMeetingId")
  private String zoomMeetingId;
  @JsonProperty("joinUrl")
  private String joinUrl;
  @JsonProperty("password")
  private String joinPassword;
  @JsonProperty("hostEmail")
  private String hostEmail;
  @JsonProperty("timezone")
  private String timezone;
  @JsonProperty("agenda")
  private String agenda;
  
  private String lecNo;
  
  private String userNo;
  
  private transient UserVO user;
  private transient LectureVO lecture;
}
