package kr.or.ddit.pfcp.student.schedule.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface StudentScheduleMapper {
    /**
     * 일정 목록 조회 (유형 포함)
     * 
     * @param param
     * @return
     */
    public List<ScheduleVO> selectScheduleListWithType(Map<String, Object> param);

    /**
     * 일정 상세 조회 (수정용 단건)
     * 
     * @param scheduleNo
     * @return
     */
    public ScheduleVO selectSchedule(String scheduleNo);

    /**
     * 일정 등록
     * 
     * @param scheduleVO
     * @return
     */
    public int insertSchedule(ScheduleVO scheduleVO);

    /**
     * 일정 수정
     * 
     * @param scheduleVO
     * @return
     */
    public int updateSchedule(ScheduleVO scheduleVO);

    /**
     * 일정 삭제
     * 
     * @param scheduleNo
     * @return
     */
    public int deleteSchedule(String scheduleNo);

    /**
     * 일정 존재 여부 확인
     * 
     * @param scheduleNo
     * @return
     */
    public int selectScheduleCount(String scheduleNo);

    /**
     * 일정 유형 목록 조회 (select box용)
     * 
     * @return
     */
    public List<ScheduleTypeVO> selectScheduleTypeList();
    
    /**
     * @return
     */
    public List<ScheduleTypeVO> selectScheduleTypes();
    
}
