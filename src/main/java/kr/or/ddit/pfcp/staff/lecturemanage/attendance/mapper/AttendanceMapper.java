package kr.or.ddit.pfcp.staff.lecturemanage.attendance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.AttendanceVO;
/**
 * @author YSM
 * @since 2025. 6. 28.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 6. 28.	 |	양수민     |	 최초 생성
 * 2025. 6. 28.  |   양수민    |   수정
 */
@Mapper
public interface AttendanceMapper {

	public List<AttendanceVO> selectAttendanceList(@Param("enrNo") String enrNo, @Param("userNo") String userNo);

	public void updateAttendance(AttendanceVO attendance);

	public AttendanceVO selectAttendanceById(String attendId);

}
