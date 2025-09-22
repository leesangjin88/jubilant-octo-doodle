package kr.or.ddit.pfcp.staff.lectureEvaluation.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.EvalCriteriaVO;

public interface StaffLectureEvaluationService {

	public List<EvalCriteriaVO> readAttendanceList();

	public void modifyCriteriaDescIfChanged(String criteriaNo, String criteriaDesc);

}
