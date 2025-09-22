package kr.or.ddit.pfcp.staff.tuition.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.CollegeVO;
import kr.or.ddit.pfcp.common.vo.DepartmentVO;

/**
 * @author YSM
 * @since 250717
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250717	|	양수민	|	최초 생성
 *
 */
@Mapper
public interface StaffTuitionMapper {

	public List<CollegeVO> selectCollegeList();

	public List<DepartmentVO> selectDepartmentsByCollegeNo(String collegeNo);


}
