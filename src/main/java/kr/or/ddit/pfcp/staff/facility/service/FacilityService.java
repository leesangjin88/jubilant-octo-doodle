package kr.or.ddit.pfcp.staff.facility.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.common.vo.ProfessorVO;
import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO;

/**
 * 시설 관리(CRUD) business logic layer
 * 
 * @author YSM
 * @since 250630
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * 250702	|	양수민	|	메소드 생성
 * -----------------------------------------------
 * 250630	|	양수민	|	최초 생성
 */
public interface FacilityService {
	public List<FacilityVO> readFacilityList();
	public FacilityVO readFacility(String facilityNo);
	public void createFacility(FacilityVO facility);
	public void modifyFacility(FacilityVO facility);
	
	
}
