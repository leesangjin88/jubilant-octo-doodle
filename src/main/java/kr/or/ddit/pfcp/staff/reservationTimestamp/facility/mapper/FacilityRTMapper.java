package kr.or.ddit.pfcp.staff.reservationTimestamp.facility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO;

@Mapper
public interface FacilityRTMapper {

	public List<ReservationTimestampVO> selectFacilityRT(String facilityNo);

	public void insertFacilityRT(ReservationTimestampVO reservationTimestamp);

	public void deleteFacilityRT(String facilityNo);

}
