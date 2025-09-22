package kr.or.ddit.pfcp.student.facility.reservation.service;

import java.util.List;
import org.springframework.stereotype.Service;
import kr.or.ddit.pfcp.common.vo.FacilityVO;

@Service
public class FacilityReservationServiceImpl implements FacilityReservationService {

  @Override
  public List<FacilityVO> getAvailableFacilities() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public FacilityVO getFacilityByNo(String facilityNo) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean createReservation(String facilityNo, String userNo, String preferredDate) {
    // TODO Auto-generated method stub
    return false;
  }
  

}
