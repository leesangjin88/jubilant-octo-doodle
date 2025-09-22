package kr.or.ddit.pfcp.staff.programStatistics.service;

import kr.or.ddit.pfcp.common.vo.ProgramStatisticsVO;

public interface ProgramStatisticsService {
	public void collectAndStatistics();
	
	public ProgramStatisticsVO readStatistics();
}
