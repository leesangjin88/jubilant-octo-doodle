package kr.or.ddit.pfcp.staff.scholarshipApply.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;

/**
 * @author YSM
 * @since 250716
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250716	|	양수민	|	최초 생성
 */
public interface StaffScholarshipApplyService {

	public List<ScholarshipApplyVO> readScholarshipApplyList(String schTypeNo, int offset, int pageSize);

	public int readApplyTotalCount(String schTypeNo);

	public void modifyScholarshipApply(ScholarshipApplyVO scholarshipApply);

	public String readScholarshipTypeName(String schTypeNo);

	public ScholarshipApplyVO readScholarshipApply(String no);

}
