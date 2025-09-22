package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author seokyungdeok
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 9.	|	서경덕	|	최초 생성
 */
@Data
@EqualsAndHashCode(of = "fileRefNo")
public class FileRefVO {
	private String fileRefNo;
	private String fileRefType;
	private String fileRefTargetId;
	private String atchId;
}
