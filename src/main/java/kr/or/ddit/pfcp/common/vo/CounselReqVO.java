package kr.or.ddit.pfcp.common.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author seokyungdeok
 * @since 250702
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250702	|	서경덕	|	최초 생성
 */
@Data
@EqualsAndHashCode(of = "counselReqno")
public class CounselReqVO {
	@NotBlank
	private String counselReqno;
	@NotBlank
	private String preferredDate;
	private String typeCode;
	private String userNo;
	private String status;
	private String counselReqdate;
	private String counselComment;
	private String reqType;
	private String subjectCode;
	
	private TypeVO type;
	private SubjectVO subject;
	private LectureReqVO lectureReq;
}
