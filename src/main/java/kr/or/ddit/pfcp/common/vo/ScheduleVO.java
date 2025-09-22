package kr.or.ddit.pfcp.common.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LSJ
 * @since 250701
 * 
 *        << 개정이력(Modification Information) >> 수정일 | 수정자 | 수정 내용
 *        --------------------------------    250707 | 이상진 | 최초생성
 *        
 */
@Data
@EqualsAndHashCode(of="scheduleNo")
public class ScheduleVO {
	private String scheduleNo;
	@NotBlank(message = "일정 제목은 필수입니다.")
	private String scheduleTitle;
	@NotNull(message = "시작일을 선택해주세요.")
	private String startDate;
	@NotNull(message = "종료일을 선택해주세요.")
	private String endDate;
	private String startTime;
	private String endTime; 
	private String scheduleDesp;
	private String lecNo;
	private String scheduleTargetId;
	private String scheduleTypeNo;
	private String isNotice;
	private String isActive;
	
	private ScheduleTypeVO type;
	
	public String getColorByGroup() {
	    if (type == null || type.getScheduleGroup() == null) {
	        return "#cccccc"; // 기본 색
	    }

	    switch (type.getScheduleGroup()) {
	        case "행정": return "#5bc0de";
	        case "비교과": return "#f0ad4e";
	        case "취업지원": return "#0275d8";
	        case "업무": return "#6f42c1";
	        case "공통": return "#999999";
	        default: return "#cccccc";
	    }
	}

	
    
    
	
	
}
