package kr.or.ddit.pfcp.student.schedule.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.ScheduleTypeVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;
import kr.or.ddit.pfcp.student.schedule.mapper.StudentScheduleMapper;

@Service(value = "studentScheduleService")
public class StudentScheduleServiceImpl implements StudentScheduleService {
	@Autowired
	private StudentScheduleMapper studentScheduleMapper;

	@Override
	public List<ScheduleVO> readScheduleListWithType(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return studentScheduleMapper.selectScheduleListWithType(param);
	}

	@Override
	public ScheduleVO readSchedule(String scheduleNo) {
		// TODO Auto-generated method stub
		return studentScheduleMapper.selectSchedule(scheduleNo);
	}

	@Override
	public int createSchedule(ScheduleVO scheduleVO) {
		// TODO Auto-generated method stub
		return studentScheduleMapper.insertSchedule(scheduleVO);
	}

	@Override
	public int modifySchedule(ScheduleVO scheduleVO) {
		// TODO Auto-generated method stub
		return studentScheduleMapper.updateSchedule(scheduleVO);
	}

	@Override
	public int removeSchedule(String scheduleNo) {
		// TODO Auto-generated method stub
		return studentScheduleMapper.deleteSchedule(scheduleNo);
	}

	@Override
	public int readScheduleCount(String scheduleNo) {
		// TODO Auto-generated method stub
		return studentScheduleMapper.selectScheduleCount(scheduleNo);
	}

	@Override
	public List<ScheduleTypeVO> readScheduleTypeList() {
		// TODO Auto-generated method stub
		return studentScheduleMapper.selectScheduleTypeList();
	}

	@Override
	public List<ScheduleTypeVO> readScheduleTypes() {
		// TODO Auto-generated method stub
		return studentScheduleMapper.selectScheduleTypes();
	}

}
