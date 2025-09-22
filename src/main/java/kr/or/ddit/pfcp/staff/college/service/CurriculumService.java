package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.CurriculumVO;
import kr.or.ddit.pfcp.common.vo.SubjectVO;

/**
 * @author KGM
 * @since 250702
 * @see
 */
public interface CurriculumService {
	
	public List<SubjectVO> readSubjectList();
	
	public List<CurriculumVO> readCurriculumList();
	public List<CurriculumVO> readCurriculum(String departmentNo);
	
	
	public int createCurriculum(CurriculumVO curriculum);
	
	public void modifyCurriculum(CurriculumVO curriculum);
	
}
