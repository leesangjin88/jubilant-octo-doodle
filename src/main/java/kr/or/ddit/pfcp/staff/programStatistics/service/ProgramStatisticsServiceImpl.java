package kr.or.ddit.pfcp.staff.programStatistics.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.ProgramStatisticsVO;
import kr.or.ddit.pfcp.staff.programStatistics.mapper.ProgramStatisticsMapper;

@Service
public class ProgramStatisticsServiceImpl implements ProgramStatisticsService {

	@Autowired
	private ProgramStatisticsMapper statisticsMapper;

	@Override
	public void collectAndStatistics() {
		int totalPrograms = statisticsMapper.countAllPrograms();
		int totalApplicants = statisticsMapper.countAllApplicants();
		int monthlyApply = statisticsMapper.countThisMonthApplicants();
		int completeRate = statisticsMapper.calculateCompleteRate();

		ProgramStatisticsVO stat = new ProgramStatisticsVO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = LocalDate.now().format(formatter);
		stat.setStatDate(today);
		stat.setTotalPrograms(totalPrograms);
		stat.setTotalApplicants(totalApplicants);
		stat.setMonthlyApply(monthlyApply);
		stat.setCompleteRate(completeRate);

		statisticsMapper.insertStatistics(stat);
	}

	@Override
	public ProgramStatisticsVO readStatistics() {
		return statisticsMapper.selectTodayStatistics();
	}

}
