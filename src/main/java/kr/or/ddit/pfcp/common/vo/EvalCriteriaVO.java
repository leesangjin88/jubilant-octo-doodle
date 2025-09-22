package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "criteriaNo")
public class EvalCriteriaVO {
	private String criteriaNo;
	private String criteriaName;
	private Integer columnAlias;
	private String criteriaDesc;
	private String criteriaUse;
	private String useFor;
	private int rnum;
}
