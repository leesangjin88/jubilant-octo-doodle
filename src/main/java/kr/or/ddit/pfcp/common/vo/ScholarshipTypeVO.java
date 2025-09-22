package kr.or.ddit.pfcp.common.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "schTypeNo")
public class ScholarshipTypeVO {
	private String schTypeNo;
	private String schName;
	private Integer rate;
	private String schDesc;
	private String schActive;
	private String schFileRefNo;
	
	private MultipartFile uploadFile;
	
	private AtchFileVO atchFile;
}
