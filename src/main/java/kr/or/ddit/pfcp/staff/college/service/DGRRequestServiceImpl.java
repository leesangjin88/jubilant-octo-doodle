package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.DGRRequestVO;
import kr.or.ddit.pfcp.staff.college.mapper.DGRRequestMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DGRRequestServiceImpl implements DGRRequestService {
	private final DGRRequestMapper mapper;
	
	@Override
	public void createDGRRequest(DGRRequestVO dgr) {
		mapper.insertDGRRequest(dgr);
	}

	@Override
	public List<DGRRequestVO> readDGRRequestList() {
		return mapper.selectAllDGRRequestList();
	}

	@Override
	public DGRRequestVO readDGRRequest(String dgrNo) {
		return mapper.selectDGRRequest(dgrNo);
	}

	@Override
	public void modifyDGRRequest(DGRRequestVO dgr) {
		mapper.updateDGRRequest(dgr);
	}

	@Override
	public void removeDGRRequest(String dgrNo) {
		mapper.deleteDGRRequest(dgrNo);
	}

}
