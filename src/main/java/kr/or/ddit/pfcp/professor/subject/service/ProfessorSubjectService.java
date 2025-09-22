package kr.or.ddit.pfcp.professor.subject.service;

import java.util.List;
import java.util.Optional;

import kr.or.ddit.pfcp.common.vo.SubjectVO;

public interface ProfessorSubjectService {
	
	public Optional<SubjectVO> readSubject(String subjectCode);
	public List<SubjectVO> readSubjectList();
	public void createSubject(SubjectVO subject);
	public void modifySubject(SubjectVO subject);
	
	
}
