package kr.or.ddit.pfcp.common.vo;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "programNo")
public class ProgramVO {
	private String userNo;
	private String programNo;
	private String typeCode;
	@NotBlank(message = "제목은 필수입니다.")
	private String programTitle;
	@NotBlank(message = "설명은 필수입니다.")
	private String programDesp;
	@NotNull(message = "정원은 필수입니다.")
    @Min(value = 1, message = "정원은 최소 1명 이상이어야 합니다.")
	private Integer programCapacity;
	private String startDate;
	private String endDate;
	@NotBlank(message = "장소는 필수입니다.")
	private String place;
	private String programActive;
	
	private TypeVO type;
	
	private List<ProgramEnrollVO> enrollList;
}
