package kr.or.ddit.pfcp.student.lecture.evaluate.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.EvalCriteriaVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureEvalScoreVO;
import kr.or.ddit.pfcp.common.vo.LectureEvalVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 5.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 5.	|	서경덕	|	최초 생성
 */
public interface LectureEvaluationService {
	public List<LectureEnrVO> readLectureEnr(String studentNo);
	
	public List<EvalCriteriaVO> readLectureEvaluationList();
	
	public void createLectureEvalScore(LectureEvalScoreVO lectureEvalScore);
	
	public void createLectureEval(LectureEvalVO lectureEval);
	
	public void modifyLectureEnrEvalYN(String studentNo);
	
	public boolean existsEvalByEnrollNo(@Param("enrollNo") String enrollNo);
}
