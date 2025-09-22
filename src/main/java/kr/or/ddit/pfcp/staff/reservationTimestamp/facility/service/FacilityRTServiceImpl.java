package kr.or.ddit.pfcp.staff.reservationTimestamp.facility.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO;
import kr.or.ddit.pfcp.staff.reservationTimestamp.facility.mapper.FacilityRTMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityRTServiceImpl implements FacilityRTService {

	private final FacilityRTMapper mapper;
	
	@Override
	public List<ReservationTimestampVO> readFacilityRT(String facilityNo) {
		return mapper.selectFacilityRT(facilityNo);
	}

	@Override
	public void insertFacilityRT(ReservationTimestampVO reservationTimestamp) {
		mapper.insertFacilityRT(reservationTimestamp);
	}

	@Override
	public void deleteFacilityRT(String facilityNo) {
		mapper.deleteFacilityRT(facilityNo);
	}

}
