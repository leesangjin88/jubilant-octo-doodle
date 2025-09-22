package kr.or.ddit.pfcp.professor.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.ExamVO;
import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.common.vo.QuestionAnswerVO;
import kr.or.ddit.pfcp.professor.exam.mapper.ProfessorExamMapper;

/**
 * @author seokyungdeok
 * @since 2025. 7. 15.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 15.	|	서경덕	|	최초 생성
 */
@Service(value = "professorExamService")
public class ProfessorExamServiceImpl implements ProfessorExamService {
	@Autowired
	private ProfessorExamMapper professorExamMapper;
	
	@Override
	public List<ExamVO> readExamList(String userNo) {
		// TODO Auto-generated method stub
		return professorExamMapper.selectExamList(userNo);
	}

	@Override
	public void createExam(ExamVO exam) {
		// TODO Auto-generated method stub
		professorExamMapper.insertExam(exam);
	}

	@Override
	public void createQuestionAnswer(List<QuestionAnswerVO> questionAnswerList) {
		
		for (QuestionAnswerVO questionAnswer : questionAnswerList) {
		    professorExamMapper.insertQuestionAnswer(questionAnswer);
		}
		
	}

	@Override
	public List<LectureVO> readLecNameList(String userNo) {
		// TODO Auto-generated method stub
		return professorExamMapper.selectLecNameList(userNo);
	}

	@Override
	public List<FacilityVO> readFacilityList() {
		// TODO Auto-generated method stub
		return professorExamMapper.selectFacilityList();
	}

	@Override
	public ExamVO readExamDetail(String examNo) {
		// TODO Auto-generated method stub
		return professorExamMapper.selectExamDetail(examNo);
	}

	@Override
	public List<QuestionAnswerVO> readQuestionList(String examNo) {
		// TODO Auto-generated method stub
		return professorExamMapper.selectQuestionList(examNo);
	}

	@Override
	public void modifyExam(ExamVO exam) {
		// TODO Auto-generated method stub
		professorExamMapper.updateExam(exam);
	}

	@Override
	public void modifyQuestionAnswer(List<QuestionAnswerVO> questionAnswerList) {
		// TODO Auto-generated method stub
		for (QuestionAnswerVO questionAnswer : questionAnswerList) {
			professorExamMapper.updateQuestionAnswer(questionAnswer);
		}
	}

	@Override
	public void removeExam(String examNo) {
		// TODO Auto-generated method stub
		professorExamMapper.deleteExam(examNo);
	}
}
