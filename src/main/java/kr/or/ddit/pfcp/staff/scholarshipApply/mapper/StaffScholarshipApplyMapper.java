package kr.or.ddit.pfcp.staff.scholarshipApply.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;

@Mapper
public interface StaffScholarshipApplyMapper {

	public List<ScholarshipApplyVO> selectScholarshipApplyList(String schTypeNo, int offset, int pageSize);

	public int selectApplyTotalCount(String schTypeNo);

	public void updateScholarshipApply(ScholarshipApplyVO scholarshipApply);

	public String selectScholarshipTypeName(String schTypeNo);

	public ScholarshipApplyVO selectScholarshipApply(String no);

}
