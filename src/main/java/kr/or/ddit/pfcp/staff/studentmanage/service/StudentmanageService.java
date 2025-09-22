package kr.or.ddit.pfcp.staff.studentmanage.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.StudentVO;

public interface StudentmanageService {
	public List<StudentVO> readStudentList(int offset, int pageSize);
	public int readTotalCount();
	public StudentVO readStudent(String userNo);
	public void createStudent(StudentVO student);
	public void modifyStudent(StudentVO student);
}
