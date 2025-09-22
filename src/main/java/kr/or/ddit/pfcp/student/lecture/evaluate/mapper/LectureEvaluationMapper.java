package kr.or.ddit.pfcp.student.lecture.evaluate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.EvalCriteriaVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureEvalScoreVO;
import kr.or.ddit.pfcp.common.vo.LectureEvalVO;

/**
 * 학생 액터 강의 평가(Create, Read)를 위한 Persistence Layer
 * 
 * @author seokyungdeok
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 5.	|	서경덕	|	최초 생성
 */
@Mapper
public interface LectureEvaluationMapper {
	public List<LectureEnrVO> selectLectureEnr(String studentNo);
	
	public List<EvalCriteriaVO> selectLectureEvaluationList();
	
	public int insertLectureEvalScore(LectureEvalScoreVO lectureEvalScore);
	
	public int insertLectureEval(LectureEvalVO lectureEval);
	
	public int updateLectureEnrEvalYN(String studentNo);
	
	public boolean existsEvalByEnrollNo(@Param("enrollNo") String enrollNo);
}
