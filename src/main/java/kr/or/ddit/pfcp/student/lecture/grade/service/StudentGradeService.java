package kr.or.ddit.pfcp.student.lecture.grade.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.pfcp.common.vo.CounselReqVO;
import kr.or.ddit.pfcp.common.vo.GradeVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.SemesterVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 9.	|	서경덕	|	최초 생성
 */
public interface StudentGradeService {
	public List<LectureEnrVO> readStudentGradeList(String userNo, String semesterNo);
	
	public List<LectureEnrVO> readStudentGradeListSearch(String studentNo, String semesterNo);
	
	public List<CounselReqVO> readStudentGradeAppealList(String userNo);
	
	public List<SemesterVO> readSemesterList();
	
	public List<LectureEnrVO> readSubjectList(String userNo);
	
	public CounselReqVO readStudentGradeAppeal(String counselReqno);
	
	public void createStudentGradeAppeal(CounselReqVO counselReq);
	
	public GradeVO readGradeByUserAndLecture(String userNo, String lecNo);
	
	public String readLecNoByExamNo(String examNo);
	
	public void upsertGrade(GradeVO grade);
}
