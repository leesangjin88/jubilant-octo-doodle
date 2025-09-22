package kr.or.ddit.pfcp.staff.lectureEvaluation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.EvalCriteriaVO;
import kr.or.ddit.pfcp.staff.lectureEvaluation.mapper.StaffLectureEvaluationMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffLectureEvaluationServiceImpl implements StaffLectureEvaluationService {
	
	private final StaffLectureEvaluationMapper mapper;
	
	@Override
	public List<EvalCriteriaVO> readAttendanceList() {
		return mapper.selectAttendanceList();
	}

	@Override
	public void modifyCriteriaDescIfChanged(String criteriaNo, String criteriaDesc) {
		mapper.updateCriteriaDescIfChanged(criteriaNo, criteriaDesc);
		
	}

}
