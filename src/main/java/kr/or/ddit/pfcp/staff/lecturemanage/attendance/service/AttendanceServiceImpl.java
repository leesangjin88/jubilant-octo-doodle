package kr.or.ddit.pfcp.staff.lecturemanage.attendance.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.AttendanceVO;
import kr.or.ddit.pfcp.staff.lecturemanage.attendance.mapper.AttendanceMapper;
import lombok.RequiredArgsConstructor;
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
@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

	private final AttendanceMapper mapper;
	
	@Override
	public List<AttendanceVO> readAttendanceList(String enrNo, String userNo) {
		return mapper.selectAttendanceList(enrNo, userNo);
	}

	@Override
	public void updateAttendanceIfChanged(AttendanceVO input) {
		AttendanceVO origin = mapper.selectAttendanceById(input.getAttendId());

	    if (!Objects.equals(input.getAttendStatus(), origin.getAttendStatus()) ||
	        !Objects.equals(input.getMemo(), origin.getMemo())) {
	        mapper.updateAttendance(input);
	    }
	}

//	@Override
//	public int modifyAttendance(List<AttendanceVO> attendanceList) {
//		return mapper.updateAttendanceList(attendanceList);
//	}

}
