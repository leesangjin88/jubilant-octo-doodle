package kr.or.ddit.pfcp.common.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author seokyungdeok
 * @since 250701
 * 
 * << 개정이력(Modification Information) >> 
 * 수정일 | 수정자 | 수정 내용
 * ----------------------------------------------- 
 * 250701 | 서경덕 | 최초 생성
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProfessorVO extends UserVO implements Serializable {
  private Integer rnum;
  @NotBlank
  private String proName;
  private String proPosition;
  private String departmentNo;
  private String proHiredate;
  private String proRdate;
  private String proStatus;

  public ProfessorVO(String userNo, String userPass, String userName, String gender,
      String userRegno1, String userRegno2, String userTel, String userZip, String userAdd1,
      String userAdd2, String userEmail, String bankCd, String userRole, String userBankno,
      String proName, String proPosition, String departmentNo, String proRdate, String proHiredate,
      String proStatus, String fileRefNo) {
    super(userNo, userPass, userName, gender, userRegno1, userRegno2, userTel, userZip, userAdd1,
        userAdd2, userEmail, bankCd, userRole, userBankno , fileRefNo);
    this.proName = proName;
    this.proPosition = proPosition;
    this.departmentNo = departmentNo;
    this.proRdate = proRdate;
    this.proHiredate = proHiredate;
    this.proStatus = proStatus;
  }
  
  @JsonIgnore
  public UserVO getUser() {
    return this;
  }

  private transient DepartmentVO department;
  private transient TypeVO type;
}
