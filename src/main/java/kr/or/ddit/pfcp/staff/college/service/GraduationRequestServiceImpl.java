package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.GraduationRequestVO;
import kr.or.ddit.pfcp.staff.college.mapper.GraduationRequestMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraduationRequestServiceImpl implements GraduationRequestService {
	private final GraduationRequestMapper mapper;
	
	@Override
	public List<GraduationRequestVO> readGraduationRequestList() {
		return mapper.selectAllGraduationRequestList();
	}

	@Override
	public GraduationRequestVO readGraduationRequest(String gradReqNo) {
		return mapper.selectGraduationRequest(gradReqNo);
	}

//	@Override
//	public void createGraduationRequest(GraduationRequestVO gradReq) {
//		mapper.insertGraduation(gradReq);
//	}

	@Override
	public void modifyGraduationRequest(GraduationRequestVO gradReq) {
		mapper.updateGraduationRequest(gradReq);
	}

	@Override
	public void removeGraduationRequest(String gradReqNo) {
		mapper.deleteGraduationRequest(gradReqNo);
	}

}
