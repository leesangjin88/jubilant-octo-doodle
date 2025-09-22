package kr.or.ddit.pfcp.staff.schedule.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kr.or.ddit.pfcp.common.vo.BoardVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;
import kr.or.ddit.pfcp.staff.notice.service.NoticeService;
import kr.or.ddit.pfcp.staff.schedule.service.StaffScheduleService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author 이상진
 * @since 20250701
 * < 개정이력(Modification Information) >>
 * 수정일		수정자		수정 내용
 * ---------------------------------------
 * 20250701 이상진       최초 생성

 */



@Slf4j
@Controller
@RequestMapping("/staff/schedule")
public class StaffScheduleController {

	@ModelAttribute("scheduleVO")
	public ScheduleVO setupScheduleVO() {
		return new ScheduleVO();
	}

	@Autowired
	private StaffScheduleService scheduleService;
	
	@Autowired
	private NoticeService noticeService;
	
	// 스케쥴과 게시판 타입 매핑 메소드
	private String mapScheduleTypeToBoardType(String scheduleTypeNo) {
	    switch(scheduleTypeNo) {
	        case "1":  // 수강신청 기간
	        case "2":  // 수강 정정 기간
	        case "14":
	            return "ACA_SCH";

	        case "3":  // 학적변경 기간
	            return "ACA_STA";

	        case "4":  // 장학금 신청
	            return "ACA_TUI";

	        case "5":  // 비교과 신청
	        case "6":  // 비교과 인증
	            return "EXT";

	        case "7":  // 취업 프로그램
	        case "8":  // 자격증 과정
	            return "GEN";  // 따로 코드 없으니 일반공지 처리

	        case "9":  // 회의
	        case "10": // 휴가
	        case "11": // 마감
	        case "12": // 기타
	            return "ETC";

	        case "13": // 시험일정
	            return "ACA_EXM";

	        default:
	            return "GEN"; // 기본값은 일반공지
	    }
	}



	/** 1. 일정 메인 화면 */
	

	@GetMapping
	public String scheduleMain(Model model) {
		// 스케쥴 그룹 타입
		model.addAttribute("scheduleTypes", scheduleService.getScheduleTypeList());
		return "pfcp/staff/schedule/scheduleMain";
	}

	@GetMapping("/form")
	public String scheduleForm(Model model) {
	    if (!model.containsAttribute("scheduleVO")) {
	        model.addAttribute("scheduleVO", new ScheduleVO());
	    }
	    model.addAttribute("scheduleTypes", scheduleService.getScheduleTypeList());
	    return "pfcp/staff/schedule/scheduleMain";
	}
	
	/** 2. 일정 목록 조회 */
	@GetMapping("/list")
	@ResponseBody
	public List<Map<String, Object>> getScheduleList(
			@RequestParam(required = false) String scheduleTypeNo
			, @RequestParam(required = false) String startDate
			, @RequestParam(required = false) String endDate
			) {
		Map<String, Object> param = new HashMap<>();
		param.put("scheduleTypeNo", scheduleTypeNo);
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		List<ScheduleVO> schedules = scheduleService.getScheduleList(param);

		
		List<Map<String, Object>> result = schedules.stream().map(schedule -> {
			Map<String, Object> event = new HashMap<>();
			event.put("id", schedule.getScheduleNo()); 
			event.put("title", schedule.getScheduleTitle()); 
			event.put("start", schedule.getStartDate()); 
			event.put("end", schedule.getEndDate()); 
			event.put("backgroundColor", schedule.getColorByGroup());
		    event.put("borderColor", schedule.getColorByGroup());
			
			// 추가 정보
			Map<String, Object> extended = new HashMap<>();
			extended.put("scheduleDesp", schedule.getScheduleDesp());
			extended.put("startTime", schedule.getStartTime());
			extended.put("endTime", schedule.getEndTime());
			extended.put("type", schedule.getType()); 

			event.put("extendedProps", extended);
			
		    

			return event;
		}).collect(Collectors.toList());

		return result;
	}

	/**
	 * 단일 일정 상세 조회 (수정용)
	 */
	@GetMapping("/{scheduleNo}")
	@ResponseBody
	public ScheduleVO getScheduleDetail(@PathVariable("scheduleNo") String scheduleNo) {
		
		return scheduleService.getSchedule(scheduleNo);
	}

	// 등록 및 수정 처리
	@PostMapping("/save")
	public String saveSchedule(
			@Valid @ModelAttribute("scheduleVO") ScheduleVO scheduleVO
			, @RequestParam("formMode") String formMode
			,@RequestParam(name="isNotice", required=false) boolean isNotice
			, RedirectAttributes redirectAttributes
			, BindingResult bindingResult
			, Model model

	) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("scheduleTypes", scheduleService.getScheduleTypeList());
			return "pfcp/staff/schedule/scheduleMain";
		}
		
		String start = scheduleVO.getStartDate();
		String end = scheduleVO.getEndDate();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate startDate = LocalDate.parse(start, formatter);
		LocalDate endDate = LocalDate.parse(end, formatter);
		log.info("start : {}, end: {}", startDate,endDate);
		if (startDate.isAfter(endDate)) {
		    redirectAttributes.addFlashAttribute("errorMessage", "종료일은 시작일보다 같거나 늦어야 합니다.");
		    return "redirect:/staff/schedule/form";
		}
		
		
		int result = 0;
		if ("insert".equals(formMode)) {
			log.info("scheduleVO ?>>> {}", scheduleVO);
			result = scheduleService.insertSchedule(scheduleVO);
			redirectAttributes.addFlashAttribute("message", result > 0 ? "일정이 등록되었습니다." : "등록 실패");
			
			//공지사항 동기화
			if(result>0 && isNotice) {
				BoardVO board = new BoardVO();
				board.setBoardTitle(scheduleVO.getScheduleTitle());
				board.setBoardContent(scheduleVO.getScheduleDesp());
			    board.setWriterNo("AD00000002"); 
			    board.setCategory("학사");
			    board.setScheduleNo(scheduleVO.getScheduleNo()); 
			    
			    String scheduleTypeNo = scheduleVO.getType().getScheduleTypeNo();
			    board.setTypeCode(mapScheduleTypeToBoardType(scheduleTypeNo));
			    
			    noticeService.createBoard(board);
			    //공지사항으로 이동할지
			}
			
		} else if ("update".equals(formMode)) {
			log.info("scheduleVO ?>>> {}", scheduleVO);
			result = scheduleService.updateSchedule(scheduleVO);
			redirectAttributes.addFlashAttribute("message", result > 0 ? "일정이 수정되었습니다." : "수정 실패");
			
			if(result>0 && isNotice) {
				BoardVO updateBoard = noticeService.readBoardByScheduleNo(scheduleVO.getScheduleNo());
				String typecode = mapScheduleTypeToBoardType(scheduleVO.getType().getScheduleTypeNo());
				
				if(updateBoard != null) {
					updateBoard.setBoardTitle(scheduleVO.getScheduleTitle());
					updateBoard.setBoardContent(scheduleVO.getScheduleDesp());
					updateBoard.setBoardNo(updateBoard.getBoardNo());
					updateBoard.setTypeCode(typecode);
				    noticeService.modifyBoard(updateBoard);
				} else {
					BoardVO newBoard  = new BoardVO();
			        newBoard.setBoardTitle(scheduleVO.getScheduleTitle());
			        newBoard.setBoardContent(scheduleVO.getScheduleDesp());
			        newBoard.setWriterNo("AD00000002"); // 임시 (로그인 적용 전)
			        newBoard.setCategory("학사");
			        newBoard.setScheduleNo(scheduleVO.getScheduleNo());
			        newBoard.setTypeCode(typecode);
			        noticeService.createBoard(newBoard);
				}
			}
		}

		return "redirect:/staff/schedule";
	}

    /** 5. 일정 삭제 */
    @DeleteMapping("/{scheduleNo}")
    @ResponseBody
    public ResponseEntity<?> deleteSchedule(@PathVariable("scheduleNo") String scheduleNo) {
        int result = scheduleService.deleteSchedule(scheduleNo);
        if(result>0) {
        	return ResponseEntity.ok(Map.of("result", "success"));
        }
        
        return ResponseEntity.ok(Map.of("result", "fail"));
    }
   
}
