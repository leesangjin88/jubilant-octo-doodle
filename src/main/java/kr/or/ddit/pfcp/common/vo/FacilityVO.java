package kr.or.ddit.pfcp.common.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YSM
 * @since 250702
 * 
 *        << 개정이력(Modification Information) >> 수정일 | 수정자 | 수정 내용
 *        ----------------------------------------------- 250701 | 양수민 | 최초 생성
 */
@Data
@EqualsAndHashCode(of="facilityNo")
public class FacilityVO {
	
	@NotBlank
	private String facilityNo;
	
	private String facilityName;
	private String facilityType;
	private String location;
	private Integer facilityMp;
	private String facilityStatus;
}
