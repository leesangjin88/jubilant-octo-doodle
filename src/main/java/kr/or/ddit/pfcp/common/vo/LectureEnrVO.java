package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "enrollNo")
public class LectureEnrVO {
	private String enrollNo;
	private String lecNo;
	private String userNo;
	private String enrollTime;
	private String enrollStatus;
	private String enrollType;
	private Integer priority;
	private Integer attendance;
	private String evalYn;
	private String grade;

	// -- added
	private double gradePoint;
	private String semesterName;
	// --

	private SemesterVO semester;
	private SubjectVO subject;

	private String subjectName;
	private String preDay;
	private String preTime;
	private String professorName;
	private String userName;
	private String departmentName;
	private String collegeName;
	
	private transient UserVO user;
	private transient DepartmentVO department;
	private transient LectureVO lecture;
	private transient LectureReqVO lectureReq;
	private transient StudentVO student;
	private transient GradeVO gradeVO;
	private transient AttendanceVO attendanceVO;
	
	
	private Integer credit;
	

}
