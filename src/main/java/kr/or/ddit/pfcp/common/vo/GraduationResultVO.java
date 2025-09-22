package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Data
@EqualsAndHashCode(of="gradResultNo")
public class GraduationResultVO {
	private String gradResultNo;
	private String gradReqNo;
	private String dgrNo;
	private Integer totalCredit;
	private Integer majorCredits;
	private Integer liberalCredits;
	private String etcPass;
	private String isGraduated;
	private String resultDate;
}
