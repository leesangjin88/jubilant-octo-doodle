package kr.or.ddit.pfcp.staff.schedule.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.ScheduleTypeVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;
import kr.or.ddit.pfcp.staff.schedule.mapper.ScheduleMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author leesangjin
 * @since 250701
 * 
 *        << 개정이력(Modification Information) >> 수정일 | 수정자 | 수정 내용
 *        ----------------------------------------------- 250630 | 이상진 | 최초 생성
 */
@Service
@RequiredArgsConstructor
public class StaffScheduleServiceImpl implements StaffScheduleService {

	private final ScheduleMapper scheduleMapper;
	
	
	 @Override
	    public List<ScheduleVO> getScheduleList(Map<String, Object> param) {
	        return scheduleMapper.selectScheduleListWithType(param);
	    }

	    @Override
	    public ScheduleVO getSchedule(String scheduleNo) {
	        return scheduleMapper.selectSchedule(scheduleNo);
	    }

	    @Override
	    public int insertSchedule(ScheduleVO scheduleVO) {
	        return scheduleMapper.insertSchedule(scheduleVO);
	    }

	    @Override
	    public int updateSchedule(ScheduleVO scheduleVO) {
	        return scheduleMapper.updateSchedule(scheduleVO);
	    }

	    @Override
	    public int deleteSchedule(String scheduleNo) {
	        return scheduleMapper.deleteSchedule(scheduleNo);
	    }

	    @Override
	    public List<ScheduleTypeVO> getScheduleTypeList() {
	        return scheduleMapper.selectScheduleTypeList();
	    }

		@Override
		public List<ScheduleTypeVO> getScheduleTypes() {
			return scheduleMapper.selectScheduleTypes();
		}

	

}
