package kr.or.ddit.pfcp.common.vo;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="submitNo")
public class AssignmentSubmissionVO {
  private String submitNo;
  private String submitDate;
  private Integer submitScore;
  private String assignNo;
  private String studentNo;
  private String fileRefNo;
  
  private MultipartFile uploadFile;
  
  private AtchFileVO atchFile;
  
  private String fileName;
  
  private UserVO user;
  
  private FileRefVO fileRef;
}
