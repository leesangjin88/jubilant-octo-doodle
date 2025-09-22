package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper= true)
public class StaffVO extends UserVO{
	private String userNo;
	private String staffDepartment;
	private String staffPosition;
	private String staffHiredate;
	private String staffRdate;
}
