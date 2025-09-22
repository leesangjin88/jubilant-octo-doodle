package kr.or.ddit.pfcp.common.vo;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudentVO extends UserVO {
  private int rnum;
  private Integer studentGrade;

  @NotBlank(groups = UpdateGroup.class)
  private String studentNo;

  private transient String studentEdate;
  private transient String studentGdate;
  private transient String studentEmp;
  private transient String departmentNo;
  private transient String remainLeave;
  private transient String stuStatus;
  private transient String subDepartmentNo;

  public StudentVO(String userNo, String userPass, String userName, String gender,
      String userRegno1, String userRegno2, String userTel, String userZip, String userAdd1,
      String userAdd2, String userEmail, String bankCd, String userRole, String userBankno,
      String studentNo, String studentEdate, String studentEmp, String departmentNo, String remainLeave , String fileRefNo) {
    super(userNo, userPass, userName, gender, userRegno1, userRegno2, userTel, userZip, userAdd1,
        userAdd2, userEmail, bankCd, userRole, userBankno, fileRefNo, uploadFile, atchFile, department, professor, staff, student);
    this.studentNo = studentNo;
    this.studentEdate = studentEdate;
    this.studentEmp = studentEmp;
    this.departmentNo = departmentNo;
    this.remainLeave = remainLeave;
  }
 
  public UserVO getUser() {
    return this;
  }

  private transient DepartmentVO department;
  private transient CollegeVO college;
  private transient AcademicChangeRequestVO academicChangeRequest;

}
