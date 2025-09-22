package kr.or.ddit.pfcp.staff.studentmanage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.ProfessorVO;
import kr.or.ddit.pfcp.common.vo.StudentVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.pfcp.staff.studentmanage.mapper.StudentmanageMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author YSM
 * @since 250630
 */
@Service
@RequiredArgsConstructor
public class StudentmanageServiceImpl implements StudentmanageService {
	
	private final StudentmanageMapper mapper;
	
	
	@Override
	public List<StudentVO> readStudentList(int offset, int pageSize) {
		return mapper.selectStudentList(offset, pageSize);
	}

	@Override
	public int readTotalCount() {
		return mapper.selectTotalCount();
	}
	
	@Override
	public StudentVO readStudent(String userNo) {
		return mapper.selectStudent(userNo);
	}

	@Override
	public void createStudent(StudentVO student) {
		mapper.insertStudent(student);
	}

	@Override
	public void modifyStudent(StudentVO student) {
		mapper.updateUser(student.getUser());
		
		mapper.updateStudent(student);
		
	}

	

	

}
