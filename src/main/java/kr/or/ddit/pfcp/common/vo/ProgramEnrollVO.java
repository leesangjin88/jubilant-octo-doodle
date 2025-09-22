package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "enrollNo")
public class ProgramEnrollVO {
	private String enrollNo;
	private String programNo;
	private String userNo;
	private String applyDate;
	private String isAttended;
	private String isCompleted;
	private Integer programScore;
	private Integer volunteerHour;
	private String isCertIssued;
	
	private StudentVO student;
	private ProgramVO program;
}
