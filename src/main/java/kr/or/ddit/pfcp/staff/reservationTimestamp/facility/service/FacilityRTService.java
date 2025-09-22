package kr.or.ddit.pfcp.staff.reservationTimestamp.facility.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO;

public interface FacilityRTService {

	public List<ReservationTimestampVO>  readFacilityRT(String facilityNo);

	public void insertFacilityRT(ReservationTimestampVO reservationTimestamp);

	public void deleteFacilityRT(String facilityNo);


}
