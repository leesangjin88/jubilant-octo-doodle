package kr.or.ddit.pfcp.staff.grademanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;

/**
 * 
 * @author YSM
 * @since 2025. 7. 15.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 15.	|	양수민	|	최초 생성
 */

@Mapper
public interface GrademanageMapper {

	public List<LectureEnrVO> selectLectureStudent(String what, int offset, int pageSize);

	public void updateGradeIfChanged(String lecNo, String userNo, Integer midtermScore, Integer finalScore,
			Integer assignmentScore);

}
