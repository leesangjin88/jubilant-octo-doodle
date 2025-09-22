package kr.or.ddit.pfcp.student.lecture.grade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.CounselReqVO;
import kr.or.ddit.pfcp.common.vo.GradeVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.SemesterVO;
import kr.or.ddit.pfcp.student.lecture.grade.mapper.StudentGradeMapper;

/**
 * @author seokyungdeok
 * @since 2025. 7. 10.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 10.	|	서경덕	|	최초 생성
 */
@Service(value = "studentGradeService")
public class StudentGradeServiceImpl implements StudentGradeService {
	@Autowired
	private StudentGradeMapper studentGradeMapper;
	
	@Override
	public List<LectureEnrVO> readStudentGradeList(String userNo, String semesterNo) {
		
		return studentGradeMapper.selectStudentGradeList(userNo, semesterNo);
	}

	@Override
	public List<SemesterVO> readSemesterList() {
		
		return studentGradeMapper.selectSemesterList();
	}

	@Override
	public List<LectureEnrVO> readStudentGradeListSearch(String studentNo, String semesterNo) {
		
		return studentGradeMapper.selectStudentGradeListSearch(studentNo, semesterNo);
	}

	@Override
	public List<CounselReqVO> readStudentGradeAppealList(String userNo) {
		
		return studentGradeMapper.selectStudentGradeAppealList(userNo);
	}

	@Override
	public void createStudentGradeAppeal(CounselReqVO counselReq) {
		// TODO Auto-generated method stub
		studentGradeMapper.insertStudentGradeAppeal(counselReq);
	}

	@Override
	public CounselReqVO readStudentGradeAppeal(String counselReqno) {
		// TODO Auto-generated method stub
		return studentGradeMapper.selectStudentGradeAppeal(counselReqno);
	}

	@Override
	public List<LectureEnrVO> readSubjectList(String userNo) {
		// TODO Auto-generated method stub
		return studentGradeMapper.selectSubjectList(userNo);
	}

	@Override
	public GradeVO readGradeByUserAndLecture(String userNo, String lecNo) {
		// TODO Auto-generated method stub
		return studentGradeMapper.selectGradeByUserAndLecture(userNo, lecNo);
	}

	@Override
	public void upsertGrade(GradeVO grade) {
		// TODO Auto-generated method stub
		studentGradeMapper.upsertGrade(grade);
	}

	@Override
	public String readLecNoByExamNo(String examNo) {
		// TODO Auto-generated method stub
		return studentGradeMapper.selectLecNoByExamNo(examNo);
	}
}
