package kr.or.ddit.pfcp.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LSH
 * 시설예약 VO
 */
@Data
@EqualsAndHashCode(of="reservationNo")
public class FacilityReservationVO {
	private String reservationNo;
	private String facilityNo;
	private String userNo;
	private String preferredDate;
}

	