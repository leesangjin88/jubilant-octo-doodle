package kr.or.ddit.pfcp.professor.data.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.LectureEvalVO;

public interface ProfessorLectureEvalDataService {
	public List<LectureEvalVO> readLectureEvalDataList();
	public List<LectureEvalVO> readLectureEvalData(String userNo);
	
}
