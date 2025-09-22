package kr.or.ddit.pfcp.student.facility.service;

import java.util.List;
import org.springframework.stereotype.Service;
import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.student.facility.mapper.StudentFacilityMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author LSH
 * @since 2025-07-16
 */
@Service
@RequiredArgsConstructor
public class StudentFacilityServiceImpl implements StudentFacilityService {
  
  private final StudentFacilityMapper studentFacilityMapper;
  
  /**
   * 시설 리스트 조회 구현 메소드
   */
  @Override
  public List<FacilityVO> readFacilityList() {
    return studentFacilityMapper.selectFacilityList();
  }

  /**
   * 시설 단일 조회 구현 메소드
   */
  @Override
  public FacilityVO readFacility(String facilityNo) {
    return studentFacilityMapper.selectFacility(facilityNo);
  }

}
