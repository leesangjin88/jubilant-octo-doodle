package kr.or.ddit.common.zoomapi.dto;

public enum ProgramType {
  LECTURE("강의"),
  WEBINAR("웨비나"),
  MEETING("회의"),
  DISCUSSION("토론"),
  PRESENTATION("발표");
  
  private final String value;
  
  ProgramType(String value) {
      this.value = value;
  }
  
  public String getValue() {
      return value;
  }
}
