package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Data
@EqualsAndHashCode(of="gradReqNo")
public class GraduationRequestVO {
	private String gradReqNo;
	private String userNo;
	private String semesterNo;
	private String reqDate;
	private String gradStatus;
	private String com;
}
