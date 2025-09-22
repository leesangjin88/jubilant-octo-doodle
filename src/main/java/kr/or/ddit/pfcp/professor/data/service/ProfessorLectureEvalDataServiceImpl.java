package kr.or.ddit.pfcp.professor.data.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.LectureEvalVO;
import kr.or.ddit.pfcp.professor.data.mapper.ProfessorLectureEvalDataMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorLectureEvalDataServiceImpl implements ProfessorLectureEvalDataService {

	private final ProfessorLectureEvalDataMapper professorLectureEvalDataMapper;
	
	@Override
	public List<LectureEvalVO> readLectureEvalDataList() {
		return professorLectureEvalDataMapper.selectLectureEvalDataList();
	}

	@Override
	public List<LectureEvalVO> readLectureEvalData(String userNo) {
		return professorLectureEvalDataMapper.selectLectureEvalData(userNo);
	}


}
