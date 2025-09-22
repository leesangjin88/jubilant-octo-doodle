package kr.or.ddit.common.zoomapi.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ZoomMeetingCreateResponse implements Serializable{
  
  private String host_email;
  
  private String id;
  
  private String created_at;
  
  private String join_url;
  
  private String start_time;
  
  private String password;
  
  private String agenda;
  
  private Integer duration;
  
  private String timezone;
  
  private String topic;
  
  private Integer type;
}
