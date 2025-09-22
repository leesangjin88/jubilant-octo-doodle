package kr.or.ddit.pfcp.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YSM
 * @since 2025. 7. 14.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 14.	|	양수민	|	최초 생성
 * 2025. 7. 17.	|	김태수	|	성적 관련 사용할 것 추가
 * 
 */
@Data
@EqualsAndHashCode(of = "gradeNo")
public class GradeVO {
	private String gradeNo;
	private String lecNo;
	private String userNo;
	private String gradeDate;
	private String finalGradeAlpha;
	
	private Integer finalGrade;
	private Integer midtermScore;
	private Integer finalScore;
	private Integer assignmentScore;
	private Integer attendanceScore;
	private Integer submitScore;
	 
	private transient UserVO user;
	private transient DepartmentVO department;
	private transient LectureVO lecture;
	private transient LectureReqVO lectureReq;
	private transient StudentVO student;
	private transient AttendanceVO attendanceVO;
	
	@JsonProperty("enrNo")
	private String enrollNo;    
	private String userName;    
	private String departmentName; 
	private String subjectName; 
	
}
