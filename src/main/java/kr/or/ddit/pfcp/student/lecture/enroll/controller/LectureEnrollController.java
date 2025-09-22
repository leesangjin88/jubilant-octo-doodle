package kr.or.ddit.pfcp.student.lecture.enroll.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;
import kr.or.ddit.pfcp.student.lecture.enroll.mapper.LectureEnrMapper;
import kr.or.ddit.pfcp.student.lecture.enroll.service.LectureEnrService;
import kr.or.ddit.security.auth.UserVOWrapper;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/student/lecture/enroll")
public class LectureEnrollController {
	
	
	
	private List<Integer> parsePeriods(String preTime) {
	    List<Integer> result = new ArrayList<>();

	    if (preTime == null || preTime.isBlank()) return result; // 빈 값이면 리턴

	    //  "교시"라는 글자가 포함되어 있다면 (즉, "1,2,3교시")
	    if (preTime.contains("교시")) {
	        // "1,2,3"만 남도록 "교시" 제거
	        String nums = preTime.replace("교시", ""); // "1,2,3"

	        //  쉼표 기준으로 잘라서 배열로
	        String[] tokens = nums.split(","); // ["1", "2", "3"]

	        //  각 문자열을 숫자로 변환해서 리스트에 담음
	        for (String token : tokens) {
	            try {
	                result.add(Integer.parseInt(token.trim())); // "1" → 1
	            } catch (NumberFormatException e) {
	                // 숫자 아닌 값 있으면 무시
	            }
	        }
	    }

	    // 시간 형식 처리 예시 (추가로 해도 됨)
	    else if (preTime.equals("09:00")) result.add(1);
	    else if (preTime.equals("10:00")) result.add(2);
	    else if (preTime.equals("11:00")) result.add(3);
	    else if (preTime.equals("12:00")) result.add(4);
	    else if (preTime.equals("13:00")) result.add(5);
	    else if (preTime.equals("14:00")) result.add(6);
	    else if (preTime.equals("15:00")) result.add(7);
	    else if (preTime.equals("16:00")) result.add(8);
	    else if (preTime.equals("17:00")) result.add(9);
	    else if (preTime.equals("18:00")) result.add(10);

	    return result;
	}
	
	public List<String> parseDays(String preDay) {
	    List<String> result = new ArrayList<>();
	    if (preDay == null || preDay.isBlank()) return result;

	    // "수요일" → "수"
	    preDay = preDay.replace("요일", "");

	    // "월,화,수" 또는 "월 화 수"
	    String[] tokens = preDay.split("[,\\s]+"); // 쉼표나 공백 기준 분할

	    for (String token : tokens) {
	        token = token.trim();
	        if (!token.isEmpty()) {
	            result.add(token); // "월", "화" 등 저장
	        }
	    }

	    return result;
	}

	

	
	@Autowired
	private LectureEnrService enrService;
	
	@Autowired
	private LectureEnrMapper mapper;
	
	
	// 1. 수강신청 메인 페이지 
	
    @GetMapping
    public String enrollMain(
    		@RequestParam(required = false) String departmentNo,
            @RequestParam(required = false) String day,
            @RequestParam(required = false) String period,
            @RequestParam(required = false) Integer credit,
    		Model model,
    		@AuthenticationPrincipal UserVOWrapper user
    	) {
    	//log.info("{}, {}, {}, {} >>>>", departmentNo, day, period, credit);
    	// student
		 String userNo = user.getUsername();
		 // log.info("{}", userNo);
    	 String nowSemester = enrService.readNowSemester();
    	 List<LectureVO> lecList = enrService.readLectureList(userNo, departmentNo, day, period, credit);
    	 List<LectureEnrVO> myEnrollList = enrService.getMyEnrollList(userNo);
    	 List<LectureVO> myLectureEnrollments = enrService.retrieveLectureVOEnrollmentsByUserNo(userNo);
    	 
    	 boolean isEnrollPeriod = enrService.isEnrollPeriod();
    	 
    	 for (LectureVO lec : myLectureEnrollments) {
    	        if (lec.getLectureReq() != null) {
    	            lec.getLectureReq().setPeriodList(parsePeriods(lec.getLectureReq().getPreTime()));
    	            lec.getLectureReq().setDayList(parseDays(lec.getLectureReq().getPreDay()));
    	        }
    	    }
    	 // 수강신청 일정 조회
    	 ScheduleVO schedule = enrService.readScheduleCurrent();
    	 String notice = "";
    	 if(schedule!=null) {
    		 notice = String.format("2024학년도 수강신청 기간은 %s ~ %s입니다.",
    	                schedule.getStartDate(),
    	                schedule.getEndDate()
    	      );
    	 }
    	 
    	  List<LectureEnrVO> preEnrollList = enrService.getPreEnrollList(userNo);

    	  model.addAttribute("scheduleNotice", notice);
    	  model.addAttribute("cartList",preEnrollList);
    	  // 필터링된 리스트로 수정
    	  model.addAttribute("lectureList", lecList);
    	  model.addAttribute("semesterNo", nowSemester);
    	  model.addAttribute("myEnrollList", myEnrollList);
    	  //수강 여부 판단하는 모델(수강신청 버튼 처리)
    	  model.addAttribute("myEnrollments",enrService.enrollEqlectureStatus(userNo));
    	  // 수강신청 시간표 중복 및 시간표 에브리타임 느낌
    	  model.addAttribute("myLectureEnrollments", myLectureEnrollments);
    	  model.addAttribute("isEnrollPeriod", isEnrollPeriod);
    	  
    	  // 필터링 목록
    	  model.addAttribute("departmentList", mapper.selectDepartmentList());
    	  model.addAttribute("dayList", List.of("월", "화", "수", "목", "금"));
    	  model.addAttribute("periodList", IntStream.rangeClosed(1, 9).boxed().collect(Collectors.toList()));
    	  model.addAttribute("creditList", List.of(1, 2, 3, 4, 5));
    	
        return "pfcp/student/lecture/enrollMain";
    }
    
    // 수강신청
    @PostMapping("/{lecNo}")
    @ResponseBody
    public ResponseEntity<?> enrollLecture(@PathVariable String lecNo,
                                           @AuthenticationPrincipal UserVOWrapper user) {
        try {
            enrService.enrollLecture(lecNo, user.getUsername());
            return ResponseEntity.ok("신청 완료");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // 2. 수강 정정 화면
    @GetMapping("/edit")
    public String editPage() {
        return "pfcp/student/lecture/editPage";
    }
    // 3. 수강 정정 처리 
    @PutMapping("/edit")
    public String processEdit(@RequestParam("lectureId") Long lectureId) {
       
    	
        return "redirect:/student/lecture/enroll";
    }

	// 3. 수강 신청 취소 처리
	@DeleteMapping("/{lecNo}")
	@ResponseBody
	public ResponseEntity<?> cancelLecture(
			@PathVariable String lecNo
			, @AuthenticationPrincipal UserVOWrapper user
			) {
		enrService.cancelLecture(lecNo, user.getUsername()); 
		return ResponseEntity.ok("취소 완료");
	}
	
	// 5. 예비 신청 취소 처리
		@DeleteMapping("/cart/{lecNo}")
		@ResponseBody
		public ResponseEntity<?> cancelPreLecture(
				@PathVariable String lecNo
				, @AuthenticationPrincipal UserVOWrapper user
				) {
			 enrService.cancelPreEnroll(lecNo, user.getUsername());
			 return ResponseEntity.ok("예비 수강신청이 취소되었습니다.");
		}
		
	
	
	// 4. 예비 수강 신청
	@PostMapping("/cart/{lecNo}")
	@ResponseBody
	public ResponseEntity<?> addLectureToCart(
			@PathVariable String lecNo,
			@AuthenticationPrincipal UserVOWrapper user
		){
		try {
	        enrService.addLectureToCart(lecNo, user.getUsername());
	        return ResponseEntity.ok("장바구니에 추가되었습니다.");
	    } catch (IllegalStateException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	    }
		
	}
	
  
	
	
}
