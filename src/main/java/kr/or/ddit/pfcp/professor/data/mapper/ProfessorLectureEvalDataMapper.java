package kr.or.ddit.pfcp.professor.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.LectureEvalVO;

@Mapper
public interface ProfessorLectureEvalDataMapper {
	
	
	/**
	 * 강의 평가 데이터 조회 리스트
	 * @return
	 */
	public List<LectureEvalVO> selectLectureEvalDataList();
	public List<LectureEvalVO> selectLectureEvalData(String userNo);
}
