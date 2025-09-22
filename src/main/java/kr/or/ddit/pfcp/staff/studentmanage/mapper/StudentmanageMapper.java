package kr.or.ddit.pfcp.staff.studentmanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.StudentVO;
import kr.or.ddit.pfcp.common.vo.UserVO;

@Mapper
public interface StudentmanageMapper {
	
	/**
	 * 학생 목록 조회
	 * @param pageSize 
	 * @param offset 
	 * 
	 * @return
	 */
	public List<StudentVO> selectStudentList(int offset, int pageSize);
	
	/**
	 * 전체 학생 수 조회
	 * 
	 * @return
	 */
	public int selectTotalCount();
	
	
	/**
	 * 학생 상세 조회
	 * 
	 * @param userNo
	 * @return
	 */
	public StudentVO selectStudent(String userNo);
	
	/**
	 * 학생 등록
	 * 
	 * @param student
	 */
	public void insertStudent(StudentVO student);
//	public void modifyStudent();

	/**
	 * 사용자(User 테이블) 정보 수정
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(UserVO user);

	
	/**
	 * 학생 수정
	 * 
	 * @param student
	 * @return
	 */
	public int updateStudent(StudentVO student);
	
}
