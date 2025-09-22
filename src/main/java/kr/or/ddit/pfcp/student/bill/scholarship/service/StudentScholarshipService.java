package kr.or.ddit.pfcp.student.bill.scholarship.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipTypeVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 8.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 8.	|	서경덕	|	최초 생성
 */
public interface StudentScholarshipService {
	public List<ScholarshipVO> readScholarshipBenefitList(String studentNo);
	
	public List<ScholarshipTypeVO> readSholarshipTypeList();
	
	public List<ScholarshipApplyVO> readScholarshipApplyList(String studentNo);
	
	public ScholarshipApplyVO readScholarshipApply(String no);
	
	public void removeScholarshipApply(String no);
	
	public void createScholarshipApply(ScholarshipApplyVO scholarshipApply);
	
	public void createFileRef(FileRefVO fileRef);
	
	public void modifyScholarshipApply(ScholarshipApplyVO scholarshipApply);
}
