package kr.or.ddit.pfcp.common.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="departmentNo")
public class DepartmentVO implements Serializable {
	private String departmentNo;
	private String departmentName;
	private Integer departmentTuition;
	private String dgrNo;
	private String collegeNo;
	private String departmentDesc;
	
	private String delYN;
	private String dcount;
	
	// 커리큘럼 vo만들어서 join해서 가져와
	private transient CurriculumVO curriculumVO;
	
	private transient DGRRequestVO dgrReqVO;
	
	
}
