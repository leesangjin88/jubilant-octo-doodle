package kr.or.ddit.pfcp.student.eclass.exam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface ExamMapper {
	/**
	 * userNo에 맞는 시험 목록 조회
	 * 
	 * @param userNo
	 * @return
	 */
	public List<ExamVO> selectExamList(Map<String, String> param);
	
	public ExamVO selectExamDetail(String examNo);
	
	public String selectExamNoByExamName(String examName);
	
	public ExamVO selectPdfContentByExamNo(String examNo);
	
	public int selectQuestionCountByExamNo(String examNo);
	
	public List<QuestionAnswerVO> selectQuestionByExamNo(String examNo);
	
	public String selectExamType(String examNo);
	
	public String selectLectureNoByExamNo(String examNo);
	
}
