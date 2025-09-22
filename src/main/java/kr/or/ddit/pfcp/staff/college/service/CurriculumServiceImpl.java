package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.CurriculumVO;
import kr.or.ddit.pfcp.common.vo.SubjectVO;
import kr.or.ddit.pfcp.staff.college.mapper.CurriculumMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author KGM
 * @since 250702
 * @see
 */
@Service
@RequiredArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {
	private final CurriculumMapper mapper;
	
	@Override
	public int createCurriculum(CurriculumVO curriculum) {
		return mapper.insertCurriculum(curriculum);
	}

	@Override
	public List<CurriculumVO> readCurriculumList() {
		return mapper.selectAllCurriculumList();
	}

	/**
	 * 커리큘럼 학과 상세페이지에서 보이기
	 */
	@Override
	public List<CurriculumVO> readCurriculum(String departmentNo) {
		return mapper.selectCurriculum(departmentNo);
	}

	@Override
	public void modifyCurriculum(CurriculumVO curriculum) {
		mapper.updateCurriculum(curriculum);
	}

	@Override
	public List<SubjectVO> readSubjectList() {
		return mapper.selectAllSubjectList();
	}



}
