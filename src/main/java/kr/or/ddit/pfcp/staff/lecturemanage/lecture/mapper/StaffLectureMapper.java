package kr.or.ddit.pfcp.staff.lecturemanage.lecture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;

@Mapper
public interface StaffLectureMapper {

	public int selectLectureStudentTotalCount(String what);

	public List<LectureVO> selectLectureList(@Param("offset") int offset, @Param("pageSize") int pageSize);

	public int selectTotalCount();

	public List<LectureEnrVO> selectLectureStudent(String what);

}
