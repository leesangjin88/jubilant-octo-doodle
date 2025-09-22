package kr.or.ddit.pfcp.staff.scholarship.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.ScholarshipTypeVO;

public interface StaffScholarshipService {

//	public void createFileRef(FileRefVO fileRef);

	public ScholarshipTypeVO readScholarship(String no);

	public void modifyScholarshipType(ScholarshipTypeVO scholarshipType);

	public void createScholarshipType(ScholarshipTypeVO scholarshipType);

	public void removeScholarshipType(String no);

	public int readSchTypeTotalCount();

	public List<ScholarshipTypeVO> readSholarshipTypeList(int offset, int pageSize);

}
