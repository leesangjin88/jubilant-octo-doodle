package kr.or.ddit.pfcp.student.bill.scholarship.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipTypeVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipVO;
import kr.or.ddit.pfcp.student.bill.scholarship.mapper.StudentScholarshipMapper;

/**
 * @author seokyungdeok
 * @since 2025. 7. 8.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 8.	|	서경덕	|	최초 생성
 */
@Service(value = "studentScholarshipService")
public class StudentScholarshipServiceImpl implements StudentScholarshipService {
	@Autowired
	private StudentScholarshipMapper studentScholarshipMapper;
	
	@Override
	public List<ScholarshipVO> readScholarshipBenefitList(String studentNo) {
		
		return studentScholarshipMapper.selectScholarshipBenefitList(studentNo);
	}

	@Override
	public void createScholarshipApply(ScholarshipApplyVO scholarshipApply) {
		
		studentScholarshipMapper.insertScholarshipApply(scholarshipApply);
	}

	@Override
	public List<ScholarshipTypeVO> readSholarshipTypeList() {
		
		return studentScholarshipMapper.selectScholarshipTypeList();
	}

	@Override
	public void createFileRef(FileRefVO fileRef) {
		// TODO Auto-generated method stub
		studentScholarshipMapper.insertFileRef(fileRef);
	}

	@Override
	public List<ScholarshipApplyVO> readScholarshipApplyList(String studentNo) {
		// TODO Auto-generated method stub
		return studentScholarshipMapper.selectScholarshipApplyList(studentNo);
	}

	@Override
	public void modifyScholarshipApply(ScholarshipApplyVO scholarshipApply) {
		// TODO Auto-generated method stub
		studentScholarshipMapper.updateScholarshipApply(scholarshipApply);
	}

	@Override
	public ScholarshipApplyVO readScholarshipApply(String no) {
		// TODO Auto-generated method stub
		return studentScholarshipMapper.selectScholarshipApply(no);
	}

	@Override
	public void removeScholarshipApply(String no) {
		// TODO Auto-generated method stub
		studentScholarshipMapper.deleteScholarshipApply(no);
	}

}
