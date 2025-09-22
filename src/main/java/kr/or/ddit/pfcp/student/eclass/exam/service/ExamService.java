package kr.or.ddit.pfcp.student.eclass.exam.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.pfcp.common.vo.ExamVO;
import kr.or.ddit.pfcp.common.vo.QuestionAnswerVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 16.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 16.	|	서경덕	|	최초 생성
 */
public interface ExamService {
	/**
	 * userNo에 맞는 시험 목록 조회
	 * 
	 * @param userNo
	 * @return
	 */
	public List<ExamVO> readExamList(String userNo, String lecNo);
	
	public ExamVO readExamDetail(String examNo);
	
	public String readExamNoByExamName(String examName);
	
	public ExamVO readPdfContentByExamNo(String examNo);
	
	public List<QuestionAnswerVO> readQuestionByExamNo(String examNo);
	
	public String readExamType(String examNo);
	
	public String readLectureNoByExamNo(String examNo);
	
	public String getExamType(String examNo);
	
	public int readQuestionCountByExamNo(String examNo);
	
	public int calculateScore(String examNo, Map<String, Integer> map);
	
	public void saveGrade(String examNo, String userNo, int score, String examType);
}
