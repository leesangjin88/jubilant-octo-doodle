package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "evalNo")
public class LectureEvalVO {
	
	private String evalNo;
	private String enrollNo;
	private String evalDate;
	private Integer overallScore;
	private String comment;
	private String subjectName;
	
	private String userNo;
	
}
