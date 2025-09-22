package kr.or.ddit.pfcp.professor.exam.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.ExamVO;
import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.common.vo.QuestionAnswerVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 15.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 15.	|	서경덕	|	최초 생성
 */
public interface ProfessorExamService {
	public List<ExamVO> readExamList(String userNo);
	
	public List<LectureVO> readLecNameList(String userNo);
	
	public List<FacilityVO> readFacilityList();
	
	public ExamVO readExamDetail(String examNo);
	
	public List<QuestionAnswerVO> readQuestionList(String examNo);
	
	public void createExam(ExamVO exam);
	public void createQuestionAnswer(List<QuestionAnswerVO> questionAnswerList);
	
	public void modifyExam(ExamVO exam);
	public void modifyQuestionAnswer(List<QuestionAnswerVO> questionAnswerList);
	
	public void removeExam(String examNo);
}
