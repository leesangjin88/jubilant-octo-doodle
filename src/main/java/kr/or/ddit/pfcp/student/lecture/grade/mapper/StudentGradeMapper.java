package kr.or.ddit.pfcp.student.lecture.grade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.CounselReqVO;
import kr.or.ddit.pfcp.common.vo.GradeVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.SemesterVO;

/**
 * 학생 액터 성적 관리(CRUD)를 위한 Persistence Layer
 * 
 * @author seokyungdeok
 * @since 250705
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250705	|	서경덕	|	최초 생성
 */
@Mapper
public interface StudentGradeMapper {
	public List<LectureEnrVO> selectStudentGradeList(@Param("userNo") String userNo, @Param("semesterNo") String semesterNo);
	
	public List<LectureEnrVO> selectStudentGradeListSearch(String studentNo, String semesterNo);
	
	public List<CounselReqVO> selectStudentGradeAppealList(String userNo);
	
	public List<SemesterVO> selectSemesterList();
	
	public List<LectureEnrVO> selectSubjectList(String userNo);
	
	public CounselReqVO selectStudentGradeAppeal(String counselReqno);
	
	public int insertStudentGradeAppeal(CounselReqVO counselReq);
	
	public GradeVO selectGradeByUserAndLecture(String userNo, String lecNo);
	
	public String selectLecNoByExamNo(String examNo);
	
	public int upsertGrade(GradeVO grade);
}
