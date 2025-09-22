package kr.or.ddit.pfcp.staff.consult.service; // 실제 패키지 구조에 맞게 변경

import kr.or.ddit.pfcp.common.vo.ReservationTimestampVO;
import kr.or.ddit.pfcp.staff.consult.mapper.ConsultMapper; // ConsultMapper 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 트랜잭션 처리를 위해 추가

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
@Service("consultService") // Spring Bean으로 등록
public class ConsultServiceImpl implements ConsultService { // ConsultService 인터페이스 구현

    @Autowired
    private ConsultMapper consultMapper; // ConsultMapper 주입

    @Override
    public List<ReservationTimestampVO> readConsultSchedule(String userNo) {
        return consultMapper.selectConsultScheduleByUserNo(userNo);
    }

    @Override
    @Transactional
    public void updateConsultSchedule(String userNo, List<ReservationTimestampVO> consultTimeList) {
        // 1. 기존 해당 교수의 모든 'CONSULT' 타입 시간표 삭제
        consultMapper.deleteConsultScheduleByUserNo(userNo);

        // 2. 새로운 시간표 삽입
        if (consultTimeList != null && !consultTimeList.isEmpty()) {
            for (ReservationTimestampVO time : consultTimeList) {
                // 이 부분이 중요합니다! userNo가 제대로 설정되는지 확인하세요.
                // 만약 time 객체에 userNo가 이미 담겨있다면 다시 설정할 필요는 없지만,
                // 안전을 위해 명시적으로 설정하는 것이 좋습니다.
                time.setUserNo(userNo); // <--- 여기가 제대로 동작하는지 확인
                time.setReservationType("CONSULT");
                consultMapper.insertConsultSchedule(time);
            }
        }
    }
}