package kr.or.ddit.pfcp.student.facility.reservation.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.pfcp.common.vo.FacilityReservationVO;
import kr.or.ddit.pfcp.common.vo.FacilityVO;

/**
 * @author LSH
 * 시설 예약 매퍼
 */
@Mapper
public interface FacilityReservationMapper {
  /**
   * 예약 생성 메소드
   * @param facilityReservation
   * @return
   */
  public int insertFacilityReservation(FacilityReservationVO facilityReservation);
  /**
   * 예약 취소 메소드
   * @param facilityNo
   * @return
   */
  public int cancelFacilityReservation(String facilityNo);
  
  /**
   * 사용가능한 시설들 조회
   * @return
   */
  public List<FacilityVO> getAvailableFacilities();
  
  /**
   * 사용가능한 특정 시설 조회
   * @param facilityNo
   * @return
   */
  public FacilityVO getFacilityByNo(String facilityNo);
}
