package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "questionNo")
public class QuestionAnswerVO {
	private String questionNo;
	private String examNo;
	private Integer questionSeq;
	private String questionText;
	private Integer answer;
	private Integer questionScore;
}
