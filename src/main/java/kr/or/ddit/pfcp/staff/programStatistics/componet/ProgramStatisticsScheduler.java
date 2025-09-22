package kr.or.ddit.pfcp.staff.programStatistics.componet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.ddit.pfcp.staff.programStatistics.service.ProgramStatisticsService;

@Component
public class ProgramStatisticsScheduler {

	@Autowired
	private ProgramStatisticsService statisticsService;

	 @Scheduled(cron = "0 50 10 * * *")
	    public void collectMorningStats() {
		 statisticsService.collectAndStatistics();
	    }

}
