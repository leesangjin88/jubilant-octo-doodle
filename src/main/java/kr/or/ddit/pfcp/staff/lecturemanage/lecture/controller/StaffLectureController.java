package kr.or.ddit.pfcp.staff.lecturemanage.lecture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.staff.lecturemanage.lecture.service.StaffLectureService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250628
 */
@Slf4j
@Controller
@RequestMapping("/staff/lecturemanage/lecture")

public class StaffLectureController {
	
	@Autowired
	private StaffLectureService service;
	
	static final String MODELNAME = "lecture";
	
	@ModelAttribute(MODELNAME)
	public LectureVO lecture() {
		return new LectureVO();
	}
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	//=========================================================================================
	
	/**
	 * 강의 목록 전체 조회
	 * @return
	 */
	@GetMapping("lectureList.do")
	public String lectureListUI(
		Model model,
		@RequestParam(defaultValue = "1") int pageNo
	) {
		int totalCnt = service.readTotalCount();
		int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;
	    int totalPage = (int) Math.ceil((double) totalCnt / pageSize);
	    
	    log.info("totalCnt" + totalCnt);
	    log.info("offset" + offset);
	    log.info("totalPage" + totalPage);
		
		
		List<LectureVO> lecture = service.readLectureList(offset, pageSize);
		model.addAttribute("lecture", lecture);
		log.info("lecture" + lecture);
		
		model.addAttribute("totalCount", totalCnt);
		model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);
		
		
		return "pfcp/staff/lecturemanage/lecture/lectureList";
	}
	
	/**
	 * 강의 상세 조회
	 * @return
	 */
	@GetMapping("lectureDetail.do")
	public String lectureDetailUI(
		@RequestParam String what,
		Model model,
		@RequestParam(defaultValue = "1") int pageNo
	) {
		int totalCnt = service.readLectureStudentTotalCount(what);
		int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;
	    int totalPage = (int) Math.ceil((double) totalCnt / pageSize);
		
	    model.addAttribute("totalCount", totalCnt);
		model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);
		
		List<LectureEnrVO> lectureStudent = service.readLectureStudent(what);
		model.addAttribute("lectureStudent", lectureStudent);
		log.info("lectureStudent" + lectureStudent);
		
		
		
//		if (!lectureStudent.isEmpty()) {
//		    model.addAttribute("enrNo", lectureStudent.get(0).getEnrollNo());
//		    model.addAttribute("userName", lectureStudent.get(0).getUser().getUserName());
//		}
		
		return "pfcp/staff/lecturemanage/lecture/lectureDetail";
	}
	
}
