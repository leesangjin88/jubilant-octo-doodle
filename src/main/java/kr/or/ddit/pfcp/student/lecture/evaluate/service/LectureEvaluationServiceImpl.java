package kr.or.ddit.pfcp.student.lecture.evaluate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.EvalCriteriaVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureEvalScoreVO;
import kr.or.ddit.pfcp.common.vo.LectureEvalVO;
import kr.or.ddit.pfcp.student.lecture.evaluate.mapper.LectureEvaluationMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author seokyungdeok
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 5.	|	서경덕	|	최초 생성
 */
@Service(value = "lectureEvaluationService")

public class LectureEvaluationServiceImpl implements LectureEvaluationService {
	@Autowired
	private LectureEvaluationMapper lectureEvaluationMapper;
	
	@Override
	public List<EvalCriteriaVO> readLectureEvaluationList() {
		
		return lectureEvaluationMapper.selectLectureEvaluationList();
	}

	@Override
	public List<LectureEnrVO> readLectureEnr(String studentNo) {
		
		return lectureEvaluationMapper.selectLectureEnr(studentNo);
	}

	@Override
	public void createLectureEvalScore(LectureEvalScoreVO lectureEvalScore) {

		lectureEvaluationMapper.insertLectureEvalScore(lectureEvalScore);
	}

	@Override
	public void createLectureEval(LectureEvalVO lectureEval) {

		lectureEvaluationMapper.insertLectureEval(lectureEval);
	}

	@Override
	public boolean existsEvalByEnrollNo(String enrollNo) {
		
		return lectureEvaluationMapper.existsEvalByEnrollNo(enrollNo);
	}

	@Override
	public void modifyLectureEnrEvalYN(String studentNo) {
		
		lectureEvaluationMapper.updateLectureEnrEvalYN(studentNo);
	}
}
