package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Data
@EqualsAndHashCode(of="collegeNo")
public class CollegeVO {
    private String collegeNo;
    private String collegeName;
    
    private String delYN;
    
    private DepartmentVO department;
}
