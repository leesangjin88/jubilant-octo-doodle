package kr.or.ddit.pfcp.staff.consult.mapper; // 실제 패키지 구조에 맞게 변경

import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO; // ReservationTimestampVO 임포트
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; // 파라미터가 하나여도 명시하는 습관이 좋습니다.

import java.util.List;


/**
 * @author YSM
 * @since 2025. 7. 7.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 7.	|	양수민	|	최초 생성
 * 2025. 7. 9.  |   양수민    |   수정
 */
@Mapper // 스프링이 매퍼 스캔 시 인식하도록 어노테이션 추가
public interface ConsultMapper { // ConsultMapper로 통일

    // 특정 교수의 상담 시간표 조회
    List<ReservationTimestampVO> selectConsultScheduleByUserNo(@Param("userNo") String userNo);

    // 특정 교수의 모든 상담 시간표 삭제
    void deleteConsultScheduleByUserNo(@Param("userNo") String userNo);

    // 단일 상담 시간표 추가
    // 여러 건을 한 번에 삽입하는 경우 List<ReservationTimestampVO>를 받는 메서드를 추가할 수 있습니다.
    int insertConsultSchedule(ReservationTimestampVO consultTime);
}