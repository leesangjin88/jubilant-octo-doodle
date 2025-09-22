package kr.or.ddit.pfcp.staff.tuition.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.CollegeVO;
import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.staff.tuition.mapper.StaffTuitionMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author YSM
 * @since 250717
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250717	|	양수민	|	최초 생성
 *
 */
@Service
@RequiredArgsConstructor
public class StaffTuitionServiceImpl implements StaffTuitionService {

	private final StaffTuitionMapper mapper;
	
	@Override
	public List<CollegeVO> readCollegeList() {
		return mapper.selectCollegeList();
	}

	@Override
	public List<DepartmentVO> readDepartmentsByCollegeNo(String collegeNo) {
		return mapper.selectDepartmentsByCollegeNo(collegeNo);
	}



}
