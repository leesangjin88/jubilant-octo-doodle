package kr.or.ddit.pfcp.staff.subject.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.staff.subject.service.StaffSubjectService;
import lombok.extern.slf4j.Slf4j;

/**
 * 교직원 액터 교과목 관리
 * 교수가 개설 신청한 교과목을 관리
 * 
 * @author seokyungdeok
 * @since 250628
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250628	|	서경덕	|	최초 생성
 */
@Controller
@RequestMapping("/staff/subject")
@Slf4j
public class StaffSubjectController {
	@Autowired
	private StaffSubjectService staffSubjectService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;
	
	/**
	 * 교과목 등록
	 * 
	 * @return
	 */
	@GetMapping("subjectInsert.do")
	public String subjectInsert() {
		return "pfcp/staff/subject/subjectInsertForm";
	}
	
	@GetMapping("fileDownload.do")
	public void fileDownload(
		@RequestParam("fileRefNo") String fileRefNo,
		HttpServletResponse response
	) throws IOException {
		// FILE_REF에서 ATCH_ID 가져오기
		FileRefVO fileRef = fileRefService.readFileRef(fileRefNo);
		
		if (fileRef == null) {
	        throw new RuntimeException("파일 참조 정보가 없습니다.");
	    }
		
		String atchId = fileRef.getAtchId();
		
		// ATCH_FILE에서 파일 정보 가져오기
	    AtchFileVO atchFile = atchFileService.readAtchFile(atchId);
	    
	    if (atchFile == null || atchFile.getAtchContent() == null) {
	        throw new RuntimeException("파일 정보가 없습니다.");
	    }
	    
	    // 응답 헤더 설정
	    response.setContentType(atchFile.getAtchMime());
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + 
	        new String(atchFile.getAtchOriginName().getBytes("UTF-8"), "ISO-8859-1") + "\"");
	    response.setContentLength((int) atchFile.getAtchSize());

	    // 파일 데이터 쓰기
	    try (OutputStream out = response.getOutputStream()) {
	        out.write(atchFile.getAtchContent());
	        out.flush();
	    }
	}
	
	/**
	 * 교과목 목록 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("subjectList.do")
	public String subjectList(Model model) {
		List<LectureReqVO> lectureReqList = staffSubjectService.readLectureReqList();
		
		int count = lectureReqList.size();
		
		model.addAttribute("lectureReqList", lectureReqList);
		model.addAttribute("count", count);
		
		return "pfcp/staff/subject/subjectList";
	}
	
	/**
	 * 교과목 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("subjectDetail.do")
	public String subjectDetail(
		@RequestParam String no,
		Model model
	) {
		LectureReqVO lectureReq = staffSubjectService.readLectureReqDetail(no);
		
		model.addAttribute("lectureReq", lectureReq);
		
		return "pfcp/staff/subject/subjectDetail";
	}
	
	@PostMapping("subjectAccept.do")
	public String subjectAccept(
		LectureReqVO vo,
		BindingResult errors,
	    RedirectAttributes redirectAttributes
	) {
		if (!errors.hasErrors()) {
	        staffSubjectService.modifySubjectAccept(vo.getReqNo());
	        staffSubjectService.createAcceptLecture(vo);
	        
	        return "redirect:/staff/subject/subjectList.do";
	    } else {
	    	
	        return "redirect:/staff/subject/subjectDetail.do?no=" + vo.getReqNo();
	    }
	}
	
	/**
	 * 교과목 반려
	 * 
	 * @return
	 */
	@PostMapping("subjectReturn.do")
	public String subjectReturn(LectureReqVO lectureReq) {
		
		staffSubjectService.returnLectureReq(lectureReq);
		
		return "redirect:/staff/subject/subjectList.do";
	}
}
