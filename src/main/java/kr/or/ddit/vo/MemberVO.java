package kr.or.ddit.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "memId")
public class MemberVO {
  @NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
  private String memId;
  @NotBlank
  private String memPassword;
  @NotBlank
  private String memName;
  @ToString.Exclude
  private String memRegno1;
  @ToString.Exclude
  private String memRegno2;
  private LocalDateTime memBir;
  private String memZip;
  private String memAdd1;
  private String memAdd2;
  private String memHometel;
  private String memComtel;
  private String memHp;
  private String memMail;
  private String memJob;
  private String memHobby;
  private String memMemorial;
  private LocalDate memMemorialday;
  private Integer memMileage;
  private boolean memDelete;
  private String memRole;
}
