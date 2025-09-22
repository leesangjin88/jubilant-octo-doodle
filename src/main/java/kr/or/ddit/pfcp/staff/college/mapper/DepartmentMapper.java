package kr.or.ddit.pfcp.staff.college.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.SubjectVO;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Mapper
public interface DepartmentMapper {
	
	public List<DepartmentVO> selectAllDepartmentList(String departmentNo);

	public DepartmentVO selectDepartment(String departmentNo);

	public List<DepartmentVO> selectAllDepartmentList();
	
	
	
	public int insertDepartment(DepartmentVO department);

	public int updateDepartment(DepartmentVO department);
	
	
	public int insertSubject(SubjectVO subject);
	
	
	// 삭제 상태로 변경
	public int updateDepartmentDelYN(String departmentNo);
}
