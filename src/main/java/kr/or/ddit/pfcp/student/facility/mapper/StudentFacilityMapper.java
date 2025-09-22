package kr.or.ddit.pfcp.student.facility.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.pfcp.common.vo.FacilityVO;

/**
 * 학생 시설 매퍼
 * @author LSH
 * @since 2025-07-16
 */
@Mapper
public interface StudentFacilityMapper {
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
}
