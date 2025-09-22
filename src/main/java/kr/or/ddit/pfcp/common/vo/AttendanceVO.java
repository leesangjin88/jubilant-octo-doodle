package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "attendId")
public class AttendanceVO {
	private String attendId;
	private String enrollNo;
	private String attendDate;
	private String attendStatus;
	private String memo;
	private int week;
	
	private int rnum;
	
	private transient LectureReqVO lectureReq;
	private transient LectureEnrVO lectureEnr;
	private transient UserVO user;
	private transient StudentVO student;
	private transient LectureVO lecture;
}
