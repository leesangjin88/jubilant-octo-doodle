package kr.or.ddit.pfcp.student.facility.service;

import java.util.List;
import kr.or.ddit.pfcp.common.vo.FacilityVO;

/**
 * @author LSH
 * @since 2025-07-16
 */
public interface StudentFacilityService {
  /**
   * 시설 리스트 조회 서비스
   * @return
   */
  public List<FacilityVO> readFacilityList();
  /**
   * 시설 단일 조회 서비스
   * @param facilityNo
   * @return
   */
  public FacilityVO readFacility(String facilityNo);
}
