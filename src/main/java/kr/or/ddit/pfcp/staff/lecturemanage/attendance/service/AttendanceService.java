package kr.or.ddit.pfcp.staff.lecturemanage.attendance.service;

import java.util.List;

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
public interface AttendanceService {

	public List<AttendanceVO> readAttendanceList(String enrNo, String userNo);

	public void updateAttendanceIfChanged(AttendanceVO att);


}
