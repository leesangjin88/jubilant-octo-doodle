package kr.or.ddit.pfcp.staff.lectureEvaluation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.EvalCriteriaVO;

@Mapper
public interface StaffLectureEvaluationMapper {

	public List<EvalCriteriaVO> selectAttendanceList();

	public void updateCriteriaDescIfChanged(@Param("criteriaNo") String criteriaNo, @Param("criteriaDesc") String criteriaDesc);

}
