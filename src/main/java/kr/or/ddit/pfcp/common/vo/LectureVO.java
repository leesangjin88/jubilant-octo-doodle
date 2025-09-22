package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "lecNo")
public class LectureVO {
	
	private int rnum;
	private String lecNo;
	private String reqNo;
	private Integer currentEnrollment;
	private String lecStatus;
	private String lrNo;
	private String userNo;
	private String lecName;
	
	private transient LectureEnrVO lectureEnr;
	private LectureReqVO lectureReq;
	private transient UserVO user;
	private transient SubjectVO subject;
	private transient DepartmentVO department;
	
}
