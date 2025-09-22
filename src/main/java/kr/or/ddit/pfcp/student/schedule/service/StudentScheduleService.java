package kr.or.ddit.pfcp.student.schedule.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.pfcp.common.vo.ScheduleTypeVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 15.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 15.	|	서경덕	|	최초 생성
 */
public interface StudentScheduleService {
	/**
     * 일정 목록 조회 (유형 포함)
     * 
     * @param param
     * @return
     */
    public List<ScheduleVO> readScheduleListWithType(Map<String, Object> param);

    /**
     * 일정 상세 조회 (수정용 단건)
     * 
     * @param scheduleNo
     * @return
     */
    public ScheduleVO readSchedule(String scheduleNo);

    /**
     * 일정 등록
     * 
     * @param scheduleVO
     * @return
     */
    public int createSchedule(ScheduleVO scheduleVO);

    /**
     * 일정 수정
     * 
     * @param scheduleVO
     * @return
     */
    public int modifySchedule(ScheduleVO scheduleVO);

    /**
     * 일정 삭제
     * 
     * @param scheduleNo
     * @return
     */
    public int removeSchedule(String scheduleNo);

    /**
     * 일정 존재 여부 확인
     * 
     * @param scheduleNo
     * @return
     */
    public int readScheduleCount(String scheduleNo);

    /**
     * 일정 유형 목록 조회 (select box용)
     * 
     * @return
     */
    public List<ScheduleTypeVO> readScheduleTypeList();
    
    /**
     * @return
     */
    public List<ScheduleTypeVO> readScheduleTypes();
}
