package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Data
@EqualsAndHashCode(of="dgrNo")
public class DGRRequestVO {
	private String dgrNo;
	private Integer dgrGrade;
	private Integer dgrMc;
	private Integer dgrLac;
	private Integer dgrFcc;
	private String dgrDate;
	private Integer dgrVolunteerHour;
}
