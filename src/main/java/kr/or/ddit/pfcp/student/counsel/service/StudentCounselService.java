package kr.or.ddit.pfcp.student.counsel.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.CounselReqVO;

/**
 * 교수 관리(CRUD) business logic layer
 * 
 * @author seokyungdeok
 * @since 250702
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250702	|	서경덕	|	최초 생성
 */
public interface StudentCounselService {
	public void createStudentCounsel(CounselReqVO counselReq);
	
	public List<CounselReqVO> readStudentDepartmentCounselList(String studentNo);
	
	public List<CounselReqVO> readStudentEmploymentCounselList(String studentNo);
	
	public CounselReqVO readStudent(String counselReqno);
//	modify
	public void removeStudentDepartmentCounsel(String counselReqno);
}
