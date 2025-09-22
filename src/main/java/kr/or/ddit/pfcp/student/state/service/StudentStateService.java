package kr.or.ddit.pfcp.student.state.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.AcademicChangeRequestVO;
import kr.or.ddit.pfcp.common.vo.StudentVO;

/**
 * 학적 변경 -> 전공변경, 복수전공, 휴학, 복학을 한번에 처리 하였음.
 * 
 * @author seokyungdeok
 * @since 250704
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250704	|	서경덕	|	최초 생성
 *
 */
public interface StudentStateService {
	public StudentVO readStudentInfo(String userNo);
	
	public List<AcademicChangeRequestVO> readAcademicChangeRequestMajorChange(String studentNo);
	public List<AcademicChangeRequestVO> readAcademicChangeRequestLeaveApply(String studentNo);
	public List<AcademicChangeRequestVO> readAcademicChangeRequestReturn(String studentNo);
	public List<AcademicChangeRequestVO> readAcademicChangeRequestDouble(String studentNo);
	public List<AcademicChangeRequestVO> readAcademicChangeRequestGraduationSuspension(String studentNo);
	
	public AcademicChangeRequestVO readAcademicChangeRequestMajorChangeDetail(String requestNo);
	public AcademicChangeRequestVO readAcademicChangeRequestLeaveApplyDetail(String requestNo);
	public AcademicChangeRequestVO readAcademicChangeRequestReturnDetail(String requestNo);
	public AcademicChangeRequestVO readAcademicChangeRequestDoubleDetail(String requestNo);
	public AcademicChangeRequestVO readAcademicChangeRequestGraduationSuspensionDetail(String requestNo);
	
	public void createAcademicChangeRequestMajorChange(AcademicChangeRequestVO academicChangeRequest);
	public void createAcademicChangeRequestLeaveApply(AcademicChangeRequestVO academicChangeRequest);
	public void createAcademicChangeRequestReturn(AcademicChangeRequestVO academicChangeRequest);
	public void createAcademicChangeRequestDouble(AcademicChangeRequestVO academicChangeRequest);
	public void createAcademicChangeRequestGraduationSuspension(AcademicChangeRequestVO academicChangeRequest);
	
	public void modifyAcademicChangeRequestMajorChange(AcademicChangeRequestVO academicChangeRequest);
	public void modifyAcademicChangeRequestLeaveApply(AcademicChangeRequestVO academicChangeRequest);
	public void modifyAcademicChangeRequestReturn(AcademicChangeRequestVO academicChangeRequest);
	public void modifyAcademicChangeRequestDouble(AcademicChangeRequestVO academicChangeRequest);
	public void modifyAcademicChangeRequestGraduationSuspension(AcademicChangeRequestVO academicChangeRequest);
	
	public void removeAcademicChangeRequestMajorChange(String requestNo);
	public void removeAcademicChangeRequestLeaveApply(String requestNo);
	public void removeAcademicChangeRequestReturn(String requestNo);
	public void removeAcademicChangeRequestDouble(String requestNo);
	public void removeAcademicChangeRequestGraduationSuspension(String requestNo);
}
