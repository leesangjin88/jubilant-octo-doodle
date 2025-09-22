package kr.or.ddit.pfcp.staff.programStatistics.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.ProgramStatisticsVO;

@Mapper
public interface ProgramStatisticsMapper {
	
	public int countAllPrograms();
	
    public int countAllApplicants();

    public int countThisMonthApplicants();

    public int calculateCompleteRate();

    public void insertStatistics(ProgramStatisticsVO stat);
    
    public ProgramStatisticsVO selectTodayStatistics();
	
}
