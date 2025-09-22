package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.CollegeVO;
import kr.or.ddit.pfcp.staff.college.mapper.CollegeMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService {
	private final CollegeMapper mapper;	
	
	@Override
	public List<CollegeVO> readCollegeList() {
		return mapper.selectAllCollegeList();
	}

	@Override
	public List<CollegeVO> readCollege(String collegeNo) {
		return mapper.selectCollege(collegeNo);
	}
	
	@Override
	public int createCollege(CollegeVO college) {
		return mapper.insertCollege(college);
	}

	@Override
	public void modifyCollege(CollegeVO college) {
		mapper.updateCollege(college);
	}

	
	@Override
	public void disableCollege(String collegeNo) {
		mapper.updateCollegeDelYN(collegeNo);
	}
	
	
}
