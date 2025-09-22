package kr.or.ddit.pfcp.staff.schedule.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.pfcp.common.vo.ScheduleTypeVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;


/**
 * @author leesangjin
 * @since 250701
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250630	|	이상진	|	최초 생성
 */
public interface StaffScheduleService {

	/** 일정 목록 조회 (유형 포함 + 필터) */
    public List<ScheduleVO> getScheduleList(Map<String, Object> param);

    /** 일정 단건 조회 */
    public ScheduleVO getSchedule(String scheduleNo);

    /** 일정 등록 */
    public int insertSchedule(ScheduleVO scheduleVO);

    /** 일정 수정 */
    public int updateSchedule(ScheduleVO scheduleVO);

    /** 일정 삭제 */
    public int deleteSchedule(String scheduleNo);

    /** 일정 유형 리스트 (select box용) */
    public List<ScheduleTypeVO> getScheduleTypeList();
    
    /** 일정 유형 이름가져오기*/
    public List<ScheduleTypeVO> getScheduleTypes();
  

}
