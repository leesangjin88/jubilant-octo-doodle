package kr.or.ddit.pfcp.professor.attendance.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.AttendanceVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.UserVO;

/**
 * @author 김태수
 * @since 2025. 7. 16.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 16.	|	김태수	|	최초 생성
 */
public interface ProfessorAttendanceService {
	

    public void insertAttendance(List<AttendanceVO> attendanceList);

	public UserVO selectUser(String userNo);

	public LectureEnrVO selectEnrNo(String enrNo);

	public LectureReqVO selectLecNo(String lecNo);

	
}
