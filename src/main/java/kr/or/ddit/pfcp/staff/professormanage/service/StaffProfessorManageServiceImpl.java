package kr.or.ddit.pfcp.staff.professormanage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.ProfessorVO;
import kr.or.ddit.pfcp.common.vo.TypeVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.pfcp.staff.professormanage.mapper.StaffProfessorManageMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author seokyungdeok
 * @since 250701
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250630	|	서경덕	|	최초 생성
 */
@Service
@RequiredArgsConstructor
public class StaffProfessorManageServiceImpl implements StaffProfessorManageService {
	private final StaffProfessorManageMapper mapper;
	
	@Override
	public void createProfessor(ProfessorVO professor) {
		
		mapper.insertProfessor(professor);
	}

	@Override
	public List<ProfessorVO> readProfessorList() {
		
		return mapper.selectProfessorList();
	}

	@Override
	public ProfessorVO readProfessor(String userNo) {
		
		return mapper.selectProfessor(userNo);
	}

	@Override
	@Transactional
	public void modifyProfessor(ProfessorVO professor) {
		mapper.updateUser(professor.getUser());
		
		mapper.updateProfessor(professor);
	}

	@Override
	public void removeProfessor(String userNo) {
		
		mapper.deleteProfessor(userNo);
	}

	@Override
	public List<DepartmentVO> readDepartmentList() {
		// TODO Auto-generated method stub
		return mapper.selectDepartmentList();
	}

	@Override
	public List<TypeVO> readBankCodeList() {
		// TODO Auto-generated method stub
		return mapper.selectBankCodeList();
	}

	@Override
	public String readGenerateNewUserNo() {
		// TODO Auto-generated method stub
		return mapper.selectGenerateNewUserNo();
	}

}
