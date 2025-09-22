package kr.or.ddit.pfcp.student.counsel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.CounselReqVO;

/**
 * 상담관리(CRUD)를 위한 Persistence Layer
 * 
 * @author seokyungdeok
 * @since 250702
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250702	|	서경덕	|	최초 생성
 */
@Mapper
public interface StudentCounselMapper {
	public int insertStudentCounsel(CounselReqVO counselReq);
	
	public List<CounselReqVO> selectStudentDepartmentCounselList(String userNo);
	
	public List<CounselReqVO> selectStudentEmploymentCounselList(String userNo);
	
	public CounselReqVO selectStudentCounsel(String counselReqno);
//	update
	public int deleteStudentDepartmentCounselList(String counselReqno);
}
