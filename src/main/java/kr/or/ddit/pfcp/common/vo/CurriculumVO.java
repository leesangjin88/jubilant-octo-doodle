package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Data
@EqualsAndHashCode(of="curNo")
public class CurriculumVO {
	private String curNo;
	private String departmentNo;
	private String subjectCode;
	private String reqCode;
	
	private String subjectName; // 교과목 이름을 담을 필드
	
	private String subjectNo;
	
	
//	private transient SubjectVO subjectVO;
}
