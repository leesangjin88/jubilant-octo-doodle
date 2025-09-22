package kr.or.ddit.pfcp.staff.schedule.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.ScheduleTypeVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;

/**
 * author : 이상진
 * created on : 2025-07-01
 */
@Mapper
public interface ScheduleMapper {

	
	  /** 일정 목록 조회 (유형 포함) */
    public List<ScheduleVO> selectScheduleListWithType(Map<String, Object> param);

    /** 일정 상세 조회 (수정용 단건) */
    public ScheduleVO selectSchedule(String scheduleNo);

    /** 일정 등록 */
    public int insertSchedule(ScheduleVO scheduleVO);

    /** 일정 수정 */
    public int updateSchedule(ScheduleVO scheduleVO);

    /** 일정 삭제 */
    public int deleteSchedule(String scheduleNo);

    /** 일정 존재 여부 확인 */
    public int selectScheduleCount(String scheduleNo);

    /** 일정 유형 목록 조회 (select box용) */
    public List<ScheduleTypeVO> selectScheduleTypeList();
    
    public List<ScheduleTypeVO> selectScheduleTypes();
    
    
}
