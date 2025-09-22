package kr.or.ddit.pfcp.professor.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.mapper.UserMapper;
import kr.or.ddit.pfcp.common.vo.AttendanceVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.pfcp.professor.attendance.mapper.ProfessorAttenanceMapper;
import kr.or.ddit.pfcp.student.lecture.enroll.mapper.LectureEnrMapper;

/**
 * @author 김태수
 * @since 2025. 7. 16.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 16.	|	김태수	|	최초 생성
 */
@Service
public class ProfessorAttendanceServiceImpl implements ProfessorAttendanceService {

	
	@Autowired
	ProfessorAttenanceMapper mapper;

	
	
	@Override
	public void insertAttendance(List<AttendanceVO> attendanceList) {
		mapper.insertAttendances(attendanceList);
		
	}

	@Override
	public UserVO selectUser(String userNo) {
		return mapper.selectUser(userNo);
	}

	@Override
	public LectureEnrVO selectEnrNo(String enrNo) {
		return mapper.selectEnrNo(enrNo);
	}

	@Override
	public LectureReqVO selectLecNo(String lecNo) {
		return mapper.selectLecNo(lecNo);
	}

}
