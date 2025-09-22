package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "counselRecordno")
public class CounselRecordVO {
	private String counselRecordno;
	private String counselReqno;
	private String proNo;
	private String counselDate;
	private String counselDetail;
	private String followUp;
}
