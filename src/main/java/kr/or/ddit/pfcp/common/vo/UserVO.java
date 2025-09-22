package kr.or.ddit.pfcp.common.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ???
 * @since 2506??
 * 
 *        << 개정이력(Modification Information) >> 수정일 | 수정자 | 수정 내용
 *        ----------------------------------------------- 250717 | 양수민 | 파일첨부용VO추가
 *        
 */


@Data
@NoArgsConstructor
public class UserVO {
    private String userNo;
    private String userPass;
    private String userName;
    private String gender;
    private String userRegno1;
    private String userRegno2;
    private String userTel;
    private String userZip;
    private String userAdd1;
    private String userAdd2;
    private String userEmail;
    private String bankCd;
    private String userRole;
    private String userBankno;

    private String fileRefNo;

    private MultipartFile uploadFile;
    private AtchFileVO atchFile;

    private DepartmentVO department;

    private ProfessorVO professor;
    private StaffVO staff;
    private StudentVO student;

    public UserVO(String userNo, String userPass, String userName, String gender,
                  String userRegno1, String userRegno2, String userTel, String userZip,
                  String userAdd1, String userAdd2, String userEmail, String bankCd,
                  String userRole, String userBankno, String fileRefNo,
                  MultipartFile uploadFile, AtchFileVO atchFile,
                  DepartmentVO department, ProfessorVO professor,
                  StaffVO staff, StudentVO student) {

        this.userNo = userNo;
        this.userPass = userPass;
        this.userName = userName;
        this.gender = gender;
        this.userRegno1 = userRegno1;
        this.userRegno2 = userRegno2;
        this.userTel = userTel;
        this.userZip = userZip;
        this.userAdd1 = userAdd1;
        this.userAdd2 = userAdd2;
        this.userEmail = userEmail;
        this.bankCd = bankCd;
        this.userRole = userRole;
        this.userBankno = userBankno;
        this.fileRefNo = fileRefNo;
        this.uploadFile = uploadFile;
        this.atchFile = atchFile;
        this.department = department;
        this.professor = professor;
        this.staff = staff;
        this.student = student;
    }
}
