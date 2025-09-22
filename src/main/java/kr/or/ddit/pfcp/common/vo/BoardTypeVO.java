package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YSM
 * @since 250704
 * 
 *        << 개정이력(Modification Information) >> 수정일 | 수정자 | 수정 내용
 *        ----------------------------------------------- 250704 | 양수민 | 최초 생성
 */
@Data
@EqualsAndHashCode(of = "typeCode")
public class BoardTypeVO {
	private String typeCode;
	private String name;
	private String replyAllow;
}
