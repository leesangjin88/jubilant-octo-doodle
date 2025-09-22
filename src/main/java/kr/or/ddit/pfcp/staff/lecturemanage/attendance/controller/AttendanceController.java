package kr.or.ddit.pfcp.staff.lecturemanage.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.AttendanceVO;
import kr.or.ddit.pfcp.staff.lecturemanage.attendance.service.AttendanceService;
import kr.or.ddit.pfcp.staff.lecturemanage.attendance.wrapper.AttendanceListWrapper;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 2025. 6. 28.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 6. 28.	 |	양수민     |	 최초 생성
 * 2025. 6. 28.  |   양수민    |   수정
 */
@Slf4j
@Controller
@RequestMapping("/staff/lecturemanage/attendance")
public class AttendanceController {
	
	@Autowired
	private AttendanceService service;
	
	static final String MODELNAME = "attendance";
	
	@ModelAttribute(MODELNAME)
	public AttendanceVO attendance() {
		return new AttendanceVO();
	}
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	//=========================================================================================
	
	
	/**
	 * 출석 목록 전체 조회 (수업 별 학생 별 조회)
	 * @return
	 */
	@GetMapping("attendanceList.do")
	public String attendanceListUI(
			@RequestParam String enrNo,
			@RequestParam String userNo,
			Model model
	) {
		
		List<AttendanceVO> attendance = service.readAttendanceList(enrNo, userNo);
		model.addAttribute("attendance", attendance);
		
		
		log.info("attendance : " + attendance);
		log.info("enrNo" + enrNo);
		log.info("userNo" + userNo);
		
		
		if (!attendance.isEmpty()) {
		    model.addAttribute("lectureName", attendance.get(0).getLectureReq().getLecName());
		    model.addAttribute("userName", attendance.get(0).getUser().getUserName());
		}
		
		return "pfcp/staff/lecturemanage/attendance/attendanceList";
	}
	
	/**
	 * 출석 목록 수정 사항 저장
	 * @return
	 */
	@PostMapping("attendanceUpdateProcess.do")
	public String attendanceUpdateUI(
		String what,
		@ModelAttribute(MODELNAME) List<AttendanceVO> attendanceList,
		BindingResult errors,
		@ModelAttribute("attendanceList") AttendanceListWrapper wrapper,
		@RequestHeader(value = "Referer", required = false) String referer,
		RedirectAttributes redirectAttributes
	) {
		
		for (AttendanceVO att : wrapper.getAttendanceList()) {
	        service.updateAttendanceIfChanged(att);
	    }

	    redirectAttributes.addFlashAttribute("msg", "출석 정보가 저장되었습니다.");
		
		return "redirect:" + (referer != null ? referer : "/staff/lecturemanage/attendance/attendanceList.do");
	}
}


























