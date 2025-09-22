package kr.or.ddit.pfcp.staff.professormanage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.ProfessorVO;
import kr.or.ddit.pfcp.common.vo.TypeVO;
import kr.or.ddit.pfcp.common.vo.UserVO;

/**
 * 교수 관리(CRUD)를 위한 Persistence Layer
 * 
 * @author seokyungdeok
 * @since 250701
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250701	|	서경덕	|	최초 생성
 */
@Mapper
public interface StaffProfessorManageMapper {
	/**
	 * 신규 교수 등록
	 * 
	 * @param professor
	 * @return
	 */
	public int insertProfessor(ProfessorVO professor);
	
	/**
	 * 교수 목록 조회
	 * 
	 * @return
	 */
	public List<ProfessorVO> selectProfessorList();
	
	/**
	 * 교수 상세 조회
	 * 
	 * @param userNo
	 * @return
	 */
	public ProfessorVO selectProfessor(String userNo);
	
	/**
	 * 교수 수정
	 * 
	 * @param professor
	 * @return
	 */
	public int updateProfessor(ProfessorVO professor);
	
	/**
	 * 사용자(User 테이블) 정보 수정
	 */
	public int updateUser(UserVO user);
	
	/**
	 * 교수 퇴직 처리
	 * 
	 * @param userNo
	 * @return
	 */
	public int deleteProfessor(String userNo);
	
	public List<DepartmentVO> selectDepartmentList();
	
	public List<TypeVO> selectBankCodeList();
	
	/**
	 * 시퀀스로 생성될 값 가져오기
	 * 
	 * @return
	 */
	public String selectGenerateNewUserNo();
}
