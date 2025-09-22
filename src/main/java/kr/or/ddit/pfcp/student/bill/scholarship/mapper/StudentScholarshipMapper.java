package kr.or.ddit.pfcp.student.bill.scholarship.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface StudentScholarshipMapper {
	public List<ScholarshipVO> selectScholarshipBenefitList(String studentNo);
	
	public List<ScholarshipTypeVO> selectScholarshipTypeList();
	
	public List<ScholarshipApplyVO> selectScholarshipApplyList(String studentNo);
	
	public ScholarshipApplyVO selectScholarshipApply(String no);
	
	public int deleteScholarshipApply(String no);
	
	public int insertScholarshipApply(ScholarshipApplyVO scholarshipApply);
	
	public int insertFileRef(FileRefVO fileRef);
	
	public int updateScholarshipApply(ScholarshipApplyVO scholarshipApply);
}
