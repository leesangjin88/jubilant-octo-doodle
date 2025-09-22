package kr.or.ddit.pfcp.staff.scholarshipApply.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;
import kr.or.ddit.pfcp.staff.scholarshipApply.service.StaffScholarshipApplyService;
import kr.or.ddit.pfcp.student.bill.scholarship.service.StudentScholarshipService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * @author YSM
 * @since 250716
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250716	|	양수민	|	최초 생성
 */
@Slf4j
@Controller
@RequestMapping("/staff/scholarshipApply")
public class StaffScholarshipApplyController {
	
	@Autowired
	private StudentScholarshipService studentScholarshipService;

	
	@Autowired
	private StaffScholarshipApplyService staffScholarshipApplyService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;
	
	private static final String MODEL_NAME = "scholarshipApply";
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	@ModelAttribute(MODEL_NAME)
	public ScholarshipApplyVO scholarshipApply() {
		return new ScholarshipApplyVO();
	}
	
	
	/**
	 * 장학금 신청 목록 조회
	 * 
	 * @return
	 */
	@GetMapping("staffScholarshipDetail.do")
	public String staffScholarshipApply(
		@RequestParam String schTypeNo,
		Model model,
		@RequestParam(defaultValue = "1") int pageNo
	) {
		int totalCnt = staffScholarshipApplyService.readApplyTotalCount(schTypeNo);
		int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;
	    int totalPage = (int) Math.ceil((double) totalCnt / pageSize);
	    model.addAttribute("totalCount", totalCnt);
		model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);
		
	    String schTypeName = staffScholarshipApplyService.readScholarshipTypeName(schTypeNo);
	    model.addAttribute("schTypeName", schTypeName);

	    List<ScholarshipApplyVO> scholarshipApplyList = staffScholarshipApplyService.readScholarshipApplyList(schTypeNo, offset, pageSize);
	    model.addAttribute("scholarshipApplyList", scholarshipApplyList);
		
		return "pfcp/staff/scholarshipApply/staffScholarshipApplyList";
	}
	
	
	/**
	 * 장학금 신청 목록 조회
	 * 
	 * @return
	 */
	@GetMapping("staffScholarshipUpdate.do")
	public String studentScholarshipUpdate(
			String no,
			Model model
	) {
		ScholarshipApplyVO scholarshipApply = staffScholarshipApplyService.readScholarshipApply(no);
		
		// fileRefNo가 있을 때만 파일 정보를 불러오기
		if (scholarshipApply.getFileRefNo() != null) {
	        FileRefVO fileRef = fileRefService.readFileRef(scholarshipApply.getFileRefNo());
	        
	        if (fileRef != null) {
	            AtchFileVO atchFile = atchFileService.readAtchFile(fileRef.getAtchId());
	            scholarshipApply.setAtchFile(atchFile);
	        }
	    }
		
		model.addAttribute("scholarship", scholarshipApply);
		
		return "pfcp/staff/scholarshipApply/staffScholarshipApplyDetail";
	}
	
	
	/**
	 * 장학금 신청 상태 변경(승인/반려)
	 * 
	 * @param no
	 * @param scholarshipApply
	 * @param errors
	 * @param redirectAttributes
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("staffScholarshipUpdateProcess.do")
	public String studentScholarshipUpdateProcess(
		String no,
		@ModelAttribute(MODEL_NAME) ScholarshipApplyVO scholarshipApply,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) throws IOException {
		String lvn;
		
		if (!errors.hasErrors()) {
			
			staffScholarshipApplyService.modifyScholarshipApply(scholarshipApply);
			
			redirectAttributes.addFlashAttribute("success", "처리가 완료되었습니다");
			
			
			lvn = "redirect:/staff/scholarshipApply/staffScholarshipUpdate.do?no=" + no;
			
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, scholarshipApply);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			redirectAttributes.addFlashAttribute("success", "처리에 실패하였습니다");
			
			lvn = "redirect:/staff/scholarshipApply/staffScholarshipUpdateProcess.do?no=" + no;
		}
		
		return lvn;
	}
	
	
}
