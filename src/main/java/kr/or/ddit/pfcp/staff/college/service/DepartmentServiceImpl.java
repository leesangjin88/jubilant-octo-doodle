package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.pfcp.common.vo.DGRRequestVO;
import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.staff.college.mapper.DGRRequestMapper;
import kr.or.ddit.pfcp.staff.college.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	private final DepartmentMapper mapper;
	private final DGRRequestMapper dgrReqMapper;
	

	@Override
	public List<DepartmentVO> readDepartmentByCollege(String departmentNo) {
		return mapper.selectAllDepartmentList(departmentNo);
	}
	
	@Override
	public DepartmentVO readDepartment(String departmentNo) {
		return mapper.selectDepartment(departmentNo);
	}
	
	@Override
	public List<DepartmentVO> readDepartmentList() {
		// TODO Auto-generated method stub
		return mapper.selectAllDepartmentList();
	}
	
	@Override
	@Transactional
	public int createDepartment(DepartmentVO department) {
	    DGRRequestVO dgrReq = department.getDgrReqVO();

	    if (dgrReq == null) {
	        throw new IllegalArgumentException("졸업요건 정보(dgrReqVO)가 DepartmentVO 내에 존재하지 않습니다.");
	        
	    }
	    
	    int dgrReqResult = dgrReqMapper.insertDGRRequest(dgrReq);
	    
	    if (dgrReqResult == 0) {
	        throw new RuntimeException("졸업요건 데이터 삽입 실패");
	    }

	    department.setDgrNo(dgrReq.getDgrNo());
	    
	    return mapper.insertDepartment(department); // departmentMapper 사용
	}


	@Override
	public void modifyDepartment(DepartmentVO department) {
		mapper.updateDepartment(department);
	}

	@Override
	public void disableDepartment(String departmentNo) {
		mapper.updateDepartmentDelYN(departmentNo);
	}

}
