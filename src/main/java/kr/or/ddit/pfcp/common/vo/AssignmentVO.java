package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="assignNo")
public class AssignmentVO {

  private String assignNo;
  private String lecNo;
  private String assignTitle;
  private String assignDeadline;
  private String assignDesp;
  private String fileRefNo;
	
}
