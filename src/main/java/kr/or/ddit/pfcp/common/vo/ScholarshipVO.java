package kr.or.ddit.pfcp.common.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "scholarshipNo")
public class ScholarshipVO {
	private Integer rnum;
	private String scholarshipNo;
	private String userNo;
	private String schTypeNo;
	private Integer tuitionAmount;
	private Integer disAmount;
	private String applyDate;
	private Integer total;
	
	private ScholarshipTypeVO scholarshipType;
	private MultipartFile uploadFile;
}
