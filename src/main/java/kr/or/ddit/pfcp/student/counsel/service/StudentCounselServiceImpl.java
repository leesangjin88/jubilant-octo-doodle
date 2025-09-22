package kr.or.ddit.pfcp.student.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.CounselReqVO;
import kr.or.ddit.pfcp.student.counsel.mapper.StudentCounselMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentCounselServiceImpl implements StudentCounselService {
	private final StudentCounselMapper mapper;
	
	@Override
	public void createStudentCounsel(CounselReqVO counselReq) {
		
		mapper.insertStudentCounsel(counselReq);
	}
	
	@Override
	public List<CounselReqVO> readStudentDepartmentCounselList(String studentNo) {
		
		return mapper.selectStudentDepartmentCounselList(studentNo);
	}
	
	@Override
	public List<CounselReqVO> readStudentEmploymentCounselList(String studentNo) {
		
		return mapper.selectStudentEmploymentCounselList(studentNo);
	}

	@Override
	public CounselReqVO readStudent(String counselReqno) {
		
		return mapper.selectStudentCounsel(counselReqno);
	}

	@Override
	public void removeStudentDepartmentCounsel(String counselReqno) {
		
		mapper.deleteStudentDepartmentCounselList(counselReqno);
	}
}
