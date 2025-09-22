package kr.or.ddit.pfcp.student.state.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.AcademicChangeRequestVO;
import kr.or.ddit.pfcp.common.vo.StudentVO;
import kr.or.ddit.pfcp.student.state.mapper.StudentStateMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author seokyungdeok
 * @since 250704
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250704	|	서경덕	|	최초 생성
 *
 */
@Service(value = "studentStateService")
@RequiredArgsConstructor
public class StudentStateServiceImpl implements StudentStateService {
	private final StudentStateMapper studentStateMapper;
	
	@Override
	public StudentVO readStudentInfo(String userNo) {
	    return studentStateMapper.selectStudentInfo(userNo);
	}

	@Override
	public List<AcademicChangeRequestVO> readAcademicChangeRequestMajorChange(String studentNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestMajorChange(studentNo);
	}

	@Override
	public List<AcademicChangeRequestVO> readAcademicChangeRequestLeaveApply(String studentNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestLeaveApply(studentNo);
	}

	@Override
	public List<AcademicChangeRequestVO> readAcademicChangeRequestReturn(String studentNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestReturn(studentNo);
	}

	@Override
	public List<AcademicChangeRequestVO> readAcademicChangeRequestDouble(String studentNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestDouble(studentNo);
	}

	@Override
	public void createAcademicChangeRequestMajorChange(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.insertAcademicChangeRequestMajorChange(academicChangeRequest);
	}

	@Override
	public void createAcademicChangeRequestLeaveApply(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.insertAcademicChangeRequestLeaveApply(academicChangeRequest);
	}

	@Override
	public void createAcademicChangeRequestReturn(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.insertAcademicChangeRequestReturn(academicChangeRequest);
	}

	@Override
	public void createAcademicChangeRequestDouble(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.insertAcademicChangeRequestDouble(academicChangeRequest);
	}

	@Override
	public List<AcademicChangeRequestVO> readAcademicChangeRequestGraduationSuspension(String studentNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestGraduationSuspension(studentNo);
	}

	@Override
	public void createAcademicChangeRequestGraduationSuspension(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.insertAcademicChangeRequestGraduationSuspension(academicChangeRequest);
	}

	@Override
	public AcademicChangeRequestVO readAcademicChangeRequestMajorChangeDetail(String requestNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestMajorChangeDetail(requestNo);
	}

	@Override
	public AcademicChangeRequestVO readAcademicChangeRequestLeaveApplyDetail(String requestNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestLeaveApplyDetail(requestNo);
	}

	@Override
	public AcademicChangeRequestVO readAcademicChangeRequestReturnDetail(String requestNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestReturnDetail(requestNo);
	}

	@Override
	public AcademicChangeRequestVO readAcademicChangeRequestGraduationSuspensionDetail(String requestNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestGraduationSuspensionDetail(requestNo);
	}

	@Override
	public AcademicChangeRequestVO readAcademicChangeRequestDoubleDetail(String requestNo) {
		// TODO Auto-generated method stub
		return studentStateMapper.selectAcademicChangeRequestDoubleDetail(requestNo);
	}

	@Override
	public void modifyAcademicChangeRequestMajorChange(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.updateAcademicChangeRequestMajorChange(academicChangeRequest);
	}

	@Override
	public void modifyAcademicChangeRequestLeaveApply(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.updateAcademicChangeRequestLeaveApply(academicChangeRequest);
	}

	@Override
	public void modifyAcademicChangeRequestReturn(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.updateAcademicChangeRequestReturn(academicChangeRequest);
	}

	@Override
	public void modifyAcademicChangeRequestDouble(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.updateAcademicChangeRequestDouble(academicChangeRequest);
	}

	@Override
	public void modifyAcademicChangeRequestGraduationSuspension(AcademicChangeRequestVO academicChangeRequest) {
		// TODO Auto-generated method stub
		studentStateMapper.updateAcademicChangeRequestGraduationSuspension(academicChangeRequest);
	}

	@Override
	public void removeAcademicChangeRequestMajorChange(String requestNo) {
		// TODO Auto-generated method stub
		studentStateMapper.deleteAcademicChangeRequestMajorChange(requestNo);
	}

	@Override
	public void removeAcademicChangeRequestLeaveApply(String requestNo) {
		// TODO Auto-generated method stub
		studentStateMapper.deleteAcademicChangeRequestLeaveApply(requestNo);
	}

	@Override
	public void removeAcademicChangeRequestReturn(String requestNo) {
		// TODO Auto-generated method stub
		studentStateMapper.deleteAcademicChangeRequestReturn(requestNo);
	}

	@Override
	public void removeAcademicChangeRequestDouble(String requestNo) {
		// TODO Auto-generated method stub
		studentStateMapper.deleteAcademicChangeRequestDouble(requestNo);
	}

	@Override
	public void removeAcademicChangeRequestGraduationSuspension(String requestNo) {
		// TODO Auto-generated method stub
		studentStateMapper.deleteAcademicChangeRequestGraduationSuspension(requestNo);
	}
}
