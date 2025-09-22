package kr.or.ddit.pfcp.common.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author seokyungdeok
 * @since 2025. 7. 8.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 8.	|	서경덕	|	최초 생성
 * 2025. 7. 16.	|	양수민	|	 수정
 */
@Data
@EqualsAndHashCode(of = "applyNo")
public class ScholarshipApplyVO {
	private String applyNo;
	private String userNo;
	private String schTypeNo;
	private String requestDate;
	private String applyDate;
	private String applyStatus;
	private String approveDate;
	private String requestComment;
	
	private String fileRefNo;
	private MultipartFile uploadFile;
	
	private String applyComment;
	
	private ScholarshipTypeVO scholarshipType;
	
	private AtchFileVO atchFile;
	
	private UserVO user;
}
