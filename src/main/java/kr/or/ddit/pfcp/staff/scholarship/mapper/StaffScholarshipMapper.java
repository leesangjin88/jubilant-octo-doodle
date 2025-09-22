package kr.or.ddit.pfcp.staff.scholarship.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.ScholarshipTypeVO;


/**
 * @author YSM
 * @since 2025. 7. 14.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 14.	|	양수민	|	최초 생성
 */

@Mapper
public interface StaffScholarshipMapper {


//	public void insertFileRef(FileRefVO fileRef);

	public List<ScholarshipTypeVO> selectSholarshipTypeList(int offset, int pageSize);

	public ScholarshipTypeVO selectScholarship(String no);

	public void updateScholarshipType(ScholarshipTypeVO scholarshipType);

	public void insertScholarshipType(ScholarshipTypeVO scholarshipType);

	public void deleteScholarshipType(String no);

	public int selectSchTypeTotalCount();

}
