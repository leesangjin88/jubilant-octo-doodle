package kr.or.ddit.pfcp.staff.college.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.CollegeVO;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Mapper
public interface CollegeMapper {

	public List<CollegeVO> selectAllCollegeList();
	
	public List<CollegeVO> selectCollege(String collegeNo);

	public int insertCollege(CollegeVO college);

	public int updateCollege(CollegeVO college);

	// 삭제 상태로 변경
	public int updateCollegeDelYN(String collegeNo);
}
	

