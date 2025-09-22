package kr.or.ddit.pfcp.staff.scholarship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipTypeVO;
import kr.or.ddit.pfcp.staff.scholarship.mapper.StaffScholarshipMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author YSM
 * @since 2025. 7. 14.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 14.	|	양수민	|	최초 생성
 */
@Service(value = "staffScholarshipService")
@RequiredArgsConstructor
public class StaffScholarshipServiceImpl implements StaffScholarshipService {
	
	@Autowired
	private StaffScholarshipMapper staffScholarshipMapper;
	

//	@Override
//	public void createFileRef(FileRefVO fileRef) {
//		staffScholarshipMapper.insertFileRef(fileRef);
//		
//	}

	@Override
	public ScholarshipTypeVO readScholarship(String no) {
		return staffScholarshipMapper.selectScholarship(no);
	}

	@Override
	public void modifyScholarshipType(ScholarshipTypeVO scholarshipType) {
		staffScholarshipMapper.updateScholarshipType(scholarshipType);
	}

	@Override
	public void createScholarshipType(ScholarshipTypeVO scholarshipType) {
		staffScholarshipMapper.insertScholarshipType(scholarshipType);
		
	}

	@Override
	public void removeScholarshipType(String no) {
		staffScholarshipMapper.deleteScholarshipType(no);
	}

	@Override
	public int readSchTypeTotalCount() {
		return staffScholarshipMapper.selectSchTypeTotalCount();
	}

	@Override
	public List<ScholarshipTypeVO> readSholarshipTypeList(int offset, int pageSize) {
		return staffScholarshipMapper.selectSholarshipTypeList(offset, pageSize);
	}

}
