package kr.or.ddit.pfcp.staff.scholarshipApply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;
import kr.or.ddit.pfcp.staff.scholarshipApply.mapper.StaffScholarshipApplyMapper;
import lombok.RequiredArgsConstructor;


/**
 * @author YSM
 * @since 250716
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250716	|	양수민	|	최초 생성
 */
@Service
@RequiredArgsConstructor
public class StaffScholarshipApplyServiceImpl implements StaffScholarshipApplyService {
	
	@Autowired
	private final StaffScholarshipApplyMapper mapper;
	
	@Override
	public List<ScholarshipApplyVO> readScholarshipApplyList(String schTypeNo, int offset, int pageSize) {
		return mapper.selectScholarshipApplyList(schTypeNo, offset, pageSize);
	}

	@Override
	public int readApplyTotalCount(String schTypeNo) {
		return mapper.selectApplyTotalCount(schTypeNo);
	}

	@Override
	public void modifyScholarshipApply(ScholarshipApplyVO scholarshipApply) {
		mapper.updateScholarshipApply(scholarshipApply);
		
	}

	@Override
	public String readScholarshipTypeName(String schTypeNo) {
		return mapper.selectScholarshipTypeName(schTypeNo);
	}

	@Override
	public ScholarshipApplyVO readScholarshipApply(String no) {
		return mapper.selectScholarshipApply(no);
	}

	
}
