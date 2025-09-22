package kr.or.ddit.pfcp.staff.professormanage.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.ProfessorVO;
import kr.or.ddit.pfcp.common.vo.TypeVO;

/**
 * 교수 관리(CRUD) business logic layer
 * 
 * @author seokyungdeok
 * @since 250701
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250701	|	서경덕	|	최초 생성
 */
public interface StaffProfessorManageService {
	public void createProfessor(ProfessorVO professor);
	public List<ProfessorVO> readProfessorList();
	public ProfessorVO readProfessor(String userNo);
	public void modifyProfessor(ProfessorVO professor);
	public void removeProfessor(String userNo);
	
	public List<DepartmentVO> readDepartmentList();
	public List<TypeVO> readBankCodeList();
	
	public String readGenerateNewUserNo();
}
