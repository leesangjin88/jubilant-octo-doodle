package kr.or.ddit.pfcp.common.vo;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="materialNo")
public class LectureMaterialVO {
  private Integer materialNo;
  private String materialTitle;
  private String materialDesp;
  private String uploadDate;
  private String professorNo;
  private Integer downloadCount;
  private String lecNo;
  private String fileRefNo;
  
  private FileRefVO fileRef;
  private UserVO user;
  private MultipartFile uploadFile;
  private AtchFileVO atchFile;
}
