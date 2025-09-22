package kr.or.ddit.pfcp.staff.consult.service; // 실제 패키지 구조에 맞게 변경

import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO; // ReservationTimestampVO 임포트
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
public interface ConsultService { // ConsultService로 통일

    // 특정 교수의 상담 시간표를 읽어오는 메서드
    List<ReservationTimestampVO> readConsultSchedule(String userNo);

    // 상담 시간표를 업데이트하는 메서드 (기존 삭제 후 새로 삽입)
    void updateConsultSchedule(String userNo, List<ReservationTimestampVO> consultTimeList);
}