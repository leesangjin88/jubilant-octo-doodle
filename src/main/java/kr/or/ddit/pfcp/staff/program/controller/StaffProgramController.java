package kr.or.ddit.pfcp.staff.program.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import kr.or.ddit.pfcp.common.service.UserService;
import kr.or.ddit.pfcp.common.vo.ProgramEnrollVO;
import kr.or.ddit.pfcp.common.vo.ProgramStatisticsVO;
import kr.or.ddit.pfcp.common.vo.ProgramVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.pfcp.staff.program.service.StaffProgramService;
import kr.or.ddit.pfcp.staff.programEnroll.service.StaffProgramEnrollService;
import kr.or.ddit.pfcp.staff.programStatistics.service.ProgramStatisticsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ${user}
 * @since ${date}
 *
 *        << 개정이력(Modification Information) >> 수정일 | 수정자 | 수정 내용
 *        20250707 | 이상진 | 최초 생성
 */
@Controller
@Slf4j
@RequestMapping("/staff/program")
public class StaffProgramController {

	@Autowired
	private StaffProgramService programService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private StaffProgramEnrollService programEnrollService;

	@Autowired
	private ProgramStatisticsService statisticsService;

	private UserVO sessionUser;

	@ModelAttribute
	public void setSessionUser(Principal principal) {
		if (principal != null) {
			this.sessionUser = userService.readMember(principal.getName());
		}
	}

	/**
	 * 프로그램 목록 조회
	 * 
	 * @return
	 */
	@GetMapping("programList.do")
	public String listPrograms(
			Model model
			,@RequestParam(required = false) String programActive
		) {
		model.addAttribute("sessionUser", sessionUser);

		// 프로그램 통계 부분
		ProgramStatisticsVO stat = statisticsService.readStatistics();
		model.addAttribute("stat", stat);

		// 프로그램 관리 부분
		
		// 전체 프로그램(상태 상관 x)
		List<ProgramVO> programList = programService.readProgramList();
		model.addAttribute("programList", programList);
		ProgramVO program = new ProgramVO();
		if (sessionUser != null) {
			program.setUserNo(sessionUser.getUserNo());
		}
		// model.addAttribute("program", program); 
		
		List<ProgramVO> openProgramList = programService.readOpenProgramList();
		model.addAttribute("openProgramList", openProgramList);
		model.addAttribute("typeList", programService.readProgramType()); 
		
		return "pfcp/staff/program/programList";
	}

	
	@GetMapping("/form")
	public String createProgramForm(Model model) {
	    ProgramVO program = new ProgramVO();

	    // 로그인 사용자 정보 설정
	    if (sessionUser != null) {
	        program.setUserNo(sessionUser.getUserNo());
	    }

	    model.addAttribute("program", program); 
	    model.addAttribute("typeList", programService.readProgramType());
	    model.addAttribute("formMode", "insert");  // ✅ 등록 모드 설정
	    model.addAttribute("sessionUser", sessionUser);

	    return "pfcp/staff/program/programForm";
	}
	/**
	 * 프로그램 수정 폼 진입(상세 조회 포함)
	 * 
	 * @return
	 */
	@GetMapping("/edit/{programNo}")
	public String editProgramForm(@PathVariable String programNo, Model model) {
		ProgramVO program = programService.readProgram(programNo);
		model.addAttribute("program", program);
		model.addAttribute("typeList", programService.readProgramType());
		return "pfcp/staff/program/programForm";
	}

	/**
	 * 프로그램 저장
	 * 
	 * @param program
	 * @param model
	 * @return
	 */

	@PostMapping("/save")
	public String saveProgram(
	    @Valid @ModelAttribute("program") ProgramVO program,
	    BindingResult bindingResult,
	    RedirectAttributes redirectAttributes,
	    Model model
	) {
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("typeList", programService.readProgramType());
	        model.addAttribute("sessionUser", sessionUser);
	        return "pfcp/staff/program/programForm";
	    }
	    
	    String start = program.getStartDate();
		String end = program.getEndDate();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate startDate = LocalDate.parse(start, formatter);
		LocalDate endDate = LocalDate.parse(end, formatter);
		log.info("start : {}, end: {}", startDate,endDate);
		if (startDate.isAfter(endDate)) {
		    redirectAttributes.addFlashAttribute("errorMessage", "종료일은 시작일보다 같거나 늦어야 합니다.");
		    return "redirect:/staff/program/form";
		}

	    boolean isNew = (program.getProgramNo() == null || program.getProgramNo().isBlank());

	    if (sessionUser != null) {
	        program.setUserNo(sessionUser.getUserNo());
	    }

	    int result = programService.saveProgram(program);

	    if (result > 0) {
	        redirectAttributes.addFlashAttribute("message", isNew ? "등록 성공" : "수정 성공");
	        return "redirect:/staff/program/programList.do";
	    } else {
	        model.addAttribute("typeList", programService.readProgramType());
	        model.addAttribute("sessionUser", sessionUser);
	        model.addAttribute("program", program);
	        return "pfcp/staff/program/programForm";
	    }
	}

	
	@DeleteMapping("/delete/{programNo}")
	@ResponseBody
	public ResponseEntity<?> deleteProgram(@PathVariable String programNo) {
	    int result = programService.deleteProgram(programNo);
	    return result > 0 ? ResponseEntity.ok().build()
	                      : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@GetMapping("/apply/{programNo}")
	public String applyManagePage(
		Model model
		, @PathVariable String programNo
		) {
		ProgramVO program = programService.readProgramWithEnroll(programNo);
		model.addAttribute("program", program);
		return "pfcp/staff/program/applyManage";
	}
	
	@PostMapping("/apply/attended")
	@ResponseBody
	public ResponseEntity<?> updateAttended(
			@RequestBody ProgramEnrollVO enrollVO
			) {
	    int result = programService.updateEnrollAttended(enrollVO);
	    return result > 0 ? ResponseEntity.ok().build() :
	                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping("/apply/complete")
	@ResponseBody
	public ResponseEntity<?> updateCompletion(
			@RequestBody ProgramEnrollVO enrollVO
			) {
	    int result = programService.updateEnrollCompletion(enrollVO);
	    return result > 0 ? ResponseEntity.ok().build()
	                      : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@PostMapping("/apply/issue")
	@ResponseBody
	public ResponseEntity<?> issueSimpleCertificate(
			@RequestParam("enrollNo") String enrollNo
			) {
	    int result = programService.updateEnrollCertIssued(enrollNo);
	    return result > 0 ? ResponseEntity.ok().build()
	                      : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	

}
