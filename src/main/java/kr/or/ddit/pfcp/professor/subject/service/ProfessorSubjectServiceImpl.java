package kr.or.ddit.pfcp.professor.subject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.SubjectVO;
import kr.or.ddit.pfcp.professor.subject.mapper.ProfessorSubjectMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorSubjectServiceImpl implements ProfessorSubjectService {
	
	private final ProfessorSubjectMapper professorSubjectMapper;

	@Override
	public Optional<SubjectVO> readSubject(String subjectCode) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(professorSubjectMapper.selectSubject(subjectCode));
	}

	@Override
	public List<SubjectVO> readSubjectList() {
		return professorSubjectMapper.selectSubjectList();
	}

	@Override
	public void createSubject(SubjectVO subject) {
		professorSubjectMapper.insertSubject(subject);
	}

	@Override
	public void modifySubject(SubjectVO subject) {
		professorSubjectMapper.updateSubject(subject);
	}

	
}
