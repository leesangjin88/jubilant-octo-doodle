package kr.or.ddit.pfcp.staff.college.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.CurriculumVO;
import kr.or.ddit.pfcp.common.vo.SubjectVO;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Mapper
public interface CurriculumMapper {
	
	/**
	 * 모든 커리큘럼 조회
	 * @param curriculum
	 * @return
	 */
	public List<CurriculumVO> selectAllCurriculumList();

	public List<CurriculumVO> selectCurriculum(String departmentNo);

	/**
	 * 새로운 커리큘럼 조회
	 * @param curriculum
	 * @return
	 */
	public int insertCurriculum(CurriculumVO curriculum);

	public int updateCurriculum(CurriculumVO curriculum);

	public int deleteCurriculum(String curNo);
	
	public List<SubjectVO> selectSubject(String departmentNo);
	
	
	public List<SubjectVO> selectAllSubjectList();
}
