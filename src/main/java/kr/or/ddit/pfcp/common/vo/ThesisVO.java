package kr.or.ddit.pfcp.common.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 김태수
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 9.	|	김태수	|	최초 생성
 */
@Data
@EqualsAndHashCode(of = "thesisNo")
public class ThesisVO {
	
	private int rnum;
	
	private String thesisNo;
	private String thesisTitle;
	private String submitDate;
	private String userNo;
	private String fileRefNo;
	private String status;
	private String rejReason;
	
	private String userName;
	private MultipartFile uploadFile;
	
	private String fileStatus;
	
	private AtchFileVO atchFile;
	
}
