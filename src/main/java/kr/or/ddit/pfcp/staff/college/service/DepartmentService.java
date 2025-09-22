package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.DepartmentVO;

/**
 * @author KGM
 * @since 250703
 * @see
 */
public interface DepartmentService {
	public List<DepartmentVO> readDepartmentByCollege(String departmentNo);
	public List<DepartmentVO> readDepartmentList();
	public DepartmentVO readDepartment(String departmentNo);
	
	
	public int createDepartment(DepartmentVO department);
	public void modifyDepartment(DepartmentVO department);
	
	
	public void disableDepartment(String departmentNo);
}
