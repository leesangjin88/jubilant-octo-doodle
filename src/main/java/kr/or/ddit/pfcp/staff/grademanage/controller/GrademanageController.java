package kr.or.ddit.pfcp.staff.grademanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.AttendanceVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.staff.grademanage.service.GrademanageService;
import kr.or.ddit.pfcp.staff.lecturemanage.lecture.service.StaffLectureService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250628
 */
@Slf4j
@Controller
@RequestMapping("/staff/grademanage")
public class GrademanageController {
	
	@Autowired
	private StaffLectureService staffLectureService;
	
	@Autowired
	private GrademanageService grademanageService;
	
	static final String MODELNAME = "lecture";
	
	@ModelAttribute(MODELNAME)
	public LectureVO lecture() {
		return new LectureVO();
	}
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	/**
	 * 교과목 전체 조회
	 * @return
	 */
	@GetMapping("gradeList.do")
	public String grademanageListUI(
			Model model,
			@RequestParam(defaultValue = "1") int pageNo
	) {
		int totalCnt = staffLectureService.readTotalCount();
		int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;
	    int totalPage = (int) Math.ceil((double) totalCnt / pageSize);
	    
		List<LectureVO> lecture = staffLectureService.readLectureList(offset, pageSize);
		model.addAttribute("lecture", lecture);
		log.info("lecture" + lecture);
		
		model.addAttribute("totalCount", totalCnt);
		model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);
		
		
		return "pfcp/staff/grademanage/gradeList";
	}
	
	/**
	 * 교과목 상세 조회 [학생 목록 조회]
	 * @return
	 */
	@GetMapping("gradeDetail.do")
	public String grademanageDetailUI(
		@RequestParam String what,
		Model model,
		@RequestParam(defaultValue = "1") int pageNo
	) {
		int totalCnt = staffLectureService.readLectureStudentTotalCount(what);
		int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;
	    int totalPage = (int) Math.ceil((double) totalCnt / pageSize);
	    model.addAttribute("totalCount", totalCnt);
		model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);
		//==============================================================================   페이징 처리
		List<LectureEnrVO> lectureStudent = grademanageService.readLectureStudent(what, offset, pageSize);
		model.addAttribute("lectureStudent", lectureStudent);
		log.info("lectureStudent!!!!!!!!!!!"+ lectureStudent);
		//==============================================================================   수강 학생 전체 조회
		//==============================================================================   출석 정보 조회
		
		//==============================================================================   중간/기말/과제/출석 점수 조회
		
		//==============================================================================   총점 조회
		return "pfcp/staff/grademanage/gradeDetail";
	}
	
	/**
	 * 성적 기입 [학생 성적 기입]
	 * @return
	 */
//	@GetMapping("gradeInsert.do")
//	public String grademanageInsertUI() {
//		return "pfcp/staff/grademanage/gradeInsert";
//	}
	
	/**
	 * 성적 수정 [학생 성적 수정]
	 * @return
	 */
	@PostMapping("gradeUpdateProcess.do")
	public String grademanageUpdateUI(
		@RequestParam("lecNo") List<String> lecNoList,
		@RequestParam("userNoList") List<String> userNoList,
		@RequestParam("midtermScoreList") List<Integer> midtermScoreList,
	    @RequestParam("finalScoreList") List<Integer> finalScoreList,
	    @RequestParam("assignmentScoreList") List<Integer> assignmentScoreList,
	    RedirectAttributes redirectAttributes
	) {
		
		log.info("lecNoList:" + lecNoList);
		log.info("userNoList:" + userNoList);
		
		String lecNo = lecNoList.get(0);
		for (int i = 0; i < userNoList.size(); i++) {
	        String userNo = userNoList.get(i);
	        Integer midtermScore = midtermScoreList.get(i);
	        Integer finalScore = finalScoreList.get(i);
	        Integer assignmentScore = assignmentScoreList.get(i);
	        
	        Map<String, Object> param = new HashMap<>();
	        param.put("midtermScore", midtermScore);
	        param.put("finalScore", finalScore);
	        param.put("assignmentScore", assignmentScore);


	        // service에서 기존 DESC와 비교 후, 다를 경우만 업데이트
	        grademanageService.modifyGradeIfChanged(lecNo, userNo, midtermScore, finalScore, assignmentScore);
	    }
		redirectAttributes.addFlashAttribute("msg", "변경 완료!");
		
		
		return "redirect:/staff/grademanage/gradeDetail.do?what=" + lecNo;
	}
	

}
