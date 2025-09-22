package kr.or.ddit.pfcp.staff.employment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff/employment")
public class EmploymentController {

	/**
	 *  1.취업지원 프로그램 관리
	 * @return
	 */
	@GetMapping("/program")
	public String listPrograms() {
		return "pfcp/staff/employment/programList";
	}
	
	@GetMapping("/program/{id}")
	public String programDetail(@PathVariable String id ) {
		return "pfcp/staff/employment/programDetail";
	}
	
	@PostMapping
	public String registerProgram() {
		return "redirect:/staff/employment/program";
	}
	
	/**
	 * 취업지원 프로그램 신청자 관리
	 * @return
	 */
	@GetMapping("applicants")
	public String applicantList() {
		return "pfcp/staff/employment/applicantList";
	}
	
	@PutMapping("/applicants/{id}/approve")
    public String approveApplicant(@PathVariable String id) {
        return "redirect:/staff/employment/applicants";
    }

    @PutMapping("/applicants/{id}/reject")
    public String rejectApplicant(@PathVariable String id) {
        return "redirect:/staff/employment/applicants";
    }

    /**
     * 3. 이력서/면접 워크숍
     * @param id
     * @return
     */
    @GetMapping("/workshops/resume/{id}/guide")
    public String resumeGuide(@PathVariable String id) {
        return "pfcp/staff/employment/resumeGuide";
    }

    @GetMapping("/workshops/interview/{id}/mock")
    public String mockInterview(@PathVariable String id) {
        return "pfcp/staff/employment/mockInterview";
    }

    /**
     *  4. 채용/실습/기업
     * @return
     */
    @GetMapping("/recruitments")
    public String recruitments() {
        return "pfcp/staff/employment/recruitmentList";
    }

    @GetMapping("/internships")
    public String internships() {
        return "pfcp/staff/employment/internshipList";
    }

    @GetMapping("/companies")
    public String companies() {
        return "pfcp/staff/employment/companyList";
    }

    /**
     *  5. 취업 통계
     * @return
     */
    @GetMapping("/statistics")
    public String employmentStatistics() {
        return "pfcp/staff/employment/statistics";
    }
    
    // 피드백 관리
    @GetMapping("/feedback")
    public String feedbackList() { 
    	return "program/feedbackList"; 
    }

    @PutMapping("/feedback/apply")
    public String applyFeedbackToNext() { 
    	return "feedback applied"; 
    }
	
}
