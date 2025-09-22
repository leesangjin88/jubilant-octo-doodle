package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "requestNo")
public class AcademicChangeRequestVO {
	private String requestNo;
	private String userNo;
	private String typeCode;
	private String semesterNo;
	private String reason;
	private String requestDate;
	private String status;
	private String requestThing;
	
	private StudentVO student;
}
