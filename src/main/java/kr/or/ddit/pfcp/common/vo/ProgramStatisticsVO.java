package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "statNo")
public class ProgramStatisticsVO {
	private String statNo;
	private String statDate;
	private Integer totalPrograms;
	private Integer totalApplicants;
	private Integer monthlyApply;
	private Integer completeRate;
	private String regDate;
}
