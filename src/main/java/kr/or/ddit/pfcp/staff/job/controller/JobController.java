package kr.or.ddit.pfcp.staff.job.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author YSM
 * @since 250628
 */
@Controller
@RequestMapping("/staff/job")
public class JobController {
	
	/**
	 * 취업게시글 목록 전체 조회
	 * @return
	 */
	@GetMapping("jobNoticeList.do")
	public String jobNoticeListUI() {
		return "pfcp/staff/job/jobNoticeList";
	}
	
	/**
	 * 취업게시글 상세 조회
	 * @return
	 */
	@GetMapping("jobNoticeDetail.do")
	public String jobNoticeDetailUI() {
		return "pfcp/staff/job/jobNoticeDetail";
	}
	
	/**
	 * 취업게시글 등록
	 * @return
	 */
	@GetMapping("jobNoticeInsert.do")
	public String jobNoticeInsertUI() {
		return "pfcp/staff/job/jobNoticeInsert";
	}
	
	/**
	 * 취업게시글 수정
	 * @return
	 */
	@GetMapping("jobNoticeUpdate.do")
	public String jobNoticeUpdateUI() {
		return "pfcp/staff/job/jobNoticeDetail";
	}
	
	/**
	 * 취업게시글 삭제
	 * @return
	 */
	@GetMapping("jobNoticeDelete.do")
	public String jobNoticeDeleteUI() {
		return "redirect:/staff/job/jobNoticeList.do";
	}
	
	
}
