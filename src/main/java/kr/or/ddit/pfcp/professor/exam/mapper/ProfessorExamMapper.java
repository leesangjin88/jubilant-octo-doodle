package kr.or.ddit.pfcp.professor.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface ProfessorExamMapper {
	public List<ExamVO> selectExamList(String userNo);
	
	public List<LectureVO> selectLecNameList(String userNo);
	
	public List<FacilityVO> selectFacilityList();
	
	public ExamVO selectExamDetail(String examNo);
	
	public List<QuestionAnswerVO> selectQuestionList(String examNo);
	
	public int insertExam(ExamVO exam);
	public int insertQuestionAnswer(QuestionAnswerVO questionAnswer);
	
	public int updateExam(ExamVO exam);
	public int updateQuestionAnswer(QuestionAnswerVO questionAnswer);
	
	public int deleteExam(String examNo);
}
