package kr.or.ddit.pfcp.staff.facility.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO;

import java.util.List;

@Mapper
public interface FacilityMapper {
	
	/**
	 * 시설 전체 조회
	 * @return
	 */
	public List<FacilityVO> selectFacilityList();
	/**
	 * 시설 상세 조회
	 * @param facilityNo
	 * @return
	 */
	public FacilityVO selectFacility(String facilityNo);
	/**
	 * 시설 등록
	 * @param facility
	 * @return
	 */
	public int insertFacility(FacilityVO facility);
	/** 
	 * 시설 수정
	 * @param facility
	 * @return
	 */
	public int updateFacility(FacilityVO facility);
	
	// 시설 시간표 수정 ============================================================
	
}
