package kr.or.ddit.pfcp.staff.schedule.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.or.ddit.pfcp.common.vo.ScheduleTypeVO;
import kr.or.ddit.pfcp.staff.schedule.service.StaffScheduleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class StaffScheduleControllerTest {


    @Autowired
    private StaffScheduleService scheduleService;

	@Test
	void testGetScheduleList() {
		 Map<String, Object> param = new HashMap<>();
		 ScheduleTypeVO type = new ScheduleTypeVO();
		 type.setScheduleTypeNo("13");
	        param.put("scheduleType", type);
	        param.put("startDate", "2025-12-02");
	        param.put("endDate", "2025-12-08");
	        log.info("{}",scheduleService.getScheduleList(param));
	}

}
