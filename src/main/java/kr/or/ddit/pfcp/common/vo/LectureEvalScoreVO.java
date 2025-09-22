package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "evalScore")
public class LectureEvalScoreVO {
	private Integer evalScore;
	private String evalNo;
	private String criteriaNo;
}
