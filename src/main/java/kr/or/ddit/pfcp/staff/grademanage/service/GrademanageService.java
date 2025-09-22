package kr.or.ddit.pfcp.staff.grademanage.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;

public interface GrademanageService {

	List<LectureEnrVO> readLectureStudent(String what, int offset, int pageSize);

	void modifyGradeIfChanged(String lecNo, String userNo, Integer midtermScore, Integer finalScore,
			Integer assignmentScore);

}
