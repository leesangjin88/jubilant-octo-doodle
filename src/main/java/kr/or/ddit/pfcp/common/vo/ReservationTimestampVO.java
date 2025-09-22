package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "reservationId")
public class ReservationTimestampVO {
	private String reservationId;
	private String reservationDay;
	private int startHour;
	private String reservationType;
	private String userNo;
	private String facilityNo;
}
