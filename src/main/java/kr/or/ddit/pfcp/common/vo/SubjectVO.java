package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "subjectCode")
public class SubjectVO {
	private String subjectCode;
	private String departmentNo;
	private Integer gradeLevel;
	private String subjectName;
	private Integer credit;
	private String subjectDesp;
	private String semesterNo;
}
