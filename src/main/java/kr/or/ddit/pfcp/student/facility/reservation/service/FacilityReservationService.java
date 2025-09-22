package kr.or.ddit.pfcp.student.facility.reservation.service;

import java.util.List;
import kr.or.ddit.pfcp.common.vo.FacilityVO;

public interface FacilityReservationService {
  public List<FacilityVO> getAvailableFacilities();
  public FacilityVO getFacilityByNo(String facilityNo);
  public boolean createReservation(String facilityNo, String userNo, String preferredDate);
}
