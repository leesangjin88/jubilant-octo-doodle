package kr.or.ddit.pfcp.student.lecture.enroll.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;
import kr.or.ddit.pfcp.student.lecture.enroll.mapper.LectureEnrMapper;
import lombok.RequiredArgsConstructor;

//이상진
@Service
@RequiredArgsConstructor
public class LectureEnrServiceImpl implements LectureEnrService {
  private final LectureEnrMapper lectureEnrMapper;
  
  
  
  @Override
  public List<LectureEnrVO> retrieveLectureEnrByUserNo(String studentNo) {
    return lectureEnrMapper.selectLectureEnrollmentsByUserNo(studentNo);
  }

	@Override
	public List<LectureVO> readLectureList(String userNo, String departmentNo, String day, String period, Integer credit) {
		return lectureEnrMapper.selectLectureListBySemester(userNo, departmentNo, day, period, credit);
	}

	@Override
	public String readNowSemester() {
		return lectureEnrMapper.getCurrentSemester();
	}

	@Override
	public int countEnrollLectureByStudent(String lecNo) {
		return lectureEnrMapper.selectEnrollmentCountByLectureNO(lecNo);
	}
	
	//수강신청
	@Transactional
	@Override
	public void enrollLecture(String lecNo, String userNo) {
		// 중복시간검증
		LectureVO newLecture = lectureEnrMapper.selectLectureById(lecNo);
		LectureReqVO newReq = newLecture.getLectureReq();
		List<LectureVO> enrolledLectures = lectureEnrMapper.selectLectureVOEnrollmentsByUserNo(userNo);
			List<LectureReqVO> enrolledReqs = enrolledLectures.stream()
		       .map(LectureVO::getLectureReq)
		       .collect(Collectors.toList());

		 // 3.  시간 중복 체크
		    if (hasTimeConflict(newReq, enrolledReqs)) {
		        throw new IllegalStateException("신청한 강의와 시간이 중복됩니다.");
		    }

		// 중복수강검증
		if(lectureEnrMapper.existsEnrollment(userNo, lecNo)) {
			throw new IllegalStateException("이미 신청한 강의입니다.");
		}	
		
		// 정원 초과 검사
		int current = lectureEnrMapper.selectCurrentEnrollmentForUpdate(lecNo);
		int max = lectureEnrMapper.selectMaxCapacity(lecNo);
		
		if(current>= max) {
			throw new IllegalStateException("정원이 초과되어 신청할 수 없습니다.");
		}
		
		
		// 수강신청 테이블 등록
		LectureEnrVO enrVo = new LectureEnrVO();

		// ENROLL_NO 존재 여부 확인
		boolean existEn = lectureEnrMapper.existsEnrollmentByGrade(userNo, lecNo);
		if(!existEn) {
			enrVo.setEnrollNo(lectureEnrMapper.getNextEnrollNo());
		}
		
		enrVo.setLecNo(lecNo);
		enrVo.setUserNo(userNo);
		
		// 수강신청등록
		lectureEnrMapper.upsertLectureEnrollment(enrVo);
		// 현재강의 수강신청 인원
		lectureEnrMapper.incrementCurrentEnrollment(lecNo);
		
		boolean hasGrade = lectureEnrMapper.existsGradeByEnrollNo(userNo, lecNo);
		if(!hasGrade) {
			lectureEnrMapper.insertGradeForEnroll(userNo, lecNo);
		}else {
			lectureEnrMapper.reviveGradeByEnrollNo(userNo, lecNo);
		}
		
	}

	// 수강신청 목록
	@Override
	public List<LectureEnrVO> getMyEnrollList(String userNo) {
		return lectureEnrMapper.selectMyLectureEnrollList(userNo);
	}

	// 수강취소
	@Transactional
	@Override
	public void cancelLecture(String lecNo, String userNo) {
		lectureEnrMapper.deleteEnrollment(userNo, lecNo);
		lectureEnrMapper.decrementCurrentEnrollment(lecNo);
		
		boolean hasGd = lectureEnrMapper.existsGradeByEnrollNo(userNo, lecNo);
		if(hasGd) {
			lectureEnrMapper.deactivateGradeByEnrollNo(userNo, lecNo);
		}
	}

	@Override
	public List<LectureEnrVO> enrollEqlectureStatus(String userNo) {
		return lectureEnrMapper.selectMappingEnrollStatus(userNo);
	}

	@Override
	public List<LectureVO> retrieveLectureVOEnrollmentsByUserNo(String userNo) {
		return lectureEnrMapper.selectLectureVOEnrollmentsByUserNo(userNo);
	}
	
	
	// 시간표 중복 체크 메서드
	private boolean hasTimeConflict(LectureReqVO newReq, List<LectureReqVO> enrolledList) {
	    Set<String> newTimeBlocks = toTimeBlocks(newReq);

	    for (LectureReqVO req : enrolledList) {
	        Set<String> existingTimeBlocks = toTimeBlocks(req);
	        for (String block : newTimeBlocks) {
	            if (existingTimeBlocks.contains(block)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}

	private Set<String> toTimeBlocks(LectureReqVO req) {
	    Set<String> blocks = new HashSet<>();
	    String[] days = req.getPreDay().split(",");
	    String[] times = req.getPreTime().split(",");

	    for (String day : days) {
	        for (String time : times) {
	            blocks.add(day.trim() + time.trim()); // 예: "화3", "수2"
	        }
	    }

	    return blocks;
	}

	 // 예비 수강신청 로직
	@Override
	public void addLectureToCart(String lecNo, String userNo) {
		// 1. 중복 여부 확인
	    boolean exists = lectureEnrMapper.existsEnrollmentByGrade(lecNo, userNo);
	   
	    LectureEnrVO enrVO = new LectureEnrVO();
	    enrVO.setLecNo(lecNo);;
	    enrVO.setUserNo(userNo);
	    enrVO.setEnrollStatus("예비");
	    enrVO.setEnrollType("예비");
	    enrVO.setPriority(1);
	    if (!exists) {
	        String newEnrollNo = lectureEnrMapper.getNextEnrollNo();
	        enrVO.setEnrollNo(newEnrollNo);
	    }
	    lectureEnrMapper.upsertPreLectureEnrollment(enrVO);
	    //lectureEnrMapper.insertLecturePreEnroll(enrVO);

	}

	@Override
	public List<LectureEnrVO> getPreEnrollList(String userNo) {
		return lectureEnrMapper.selectPreEnrollListByUserNo(userNo);
	}
	
	@Override
	public void cancelPreEnroll(String lecNo, String userNo) {
		lectureEnrMapper.cancelPreEnroll(userNo, lecNo);
		
	}
	
	@Override
	public List<LectureEnrVO> readPreEnrollListByPriority(String userNo) {
		return lectureEnrMapper.selectPreEnrollListByUserNoOrderByPriority(userNo);
	}

	@Override
	public boolean isEnrollPeriod() {
		return lectureEnrMapper.isNowRegularEnrollPeriod();
	}

	@Override
	public ScheduleVO readScheduleCurrent() {
		return lectureEnrMapper.selectCurrentScheduleByType();
	}
	
	
	

}
