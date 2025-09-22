package kr.or.ddit.pfcp.student.bill.scholarship.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipTypeVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipVO;
import kr.or.ddit.pfcp.student.bill.scholarship.service.StudentScholarshipService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author seokyungdeok
 * @since 250630
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250630	|	서경덕	|	최초 생성
 */
@Controller
@RequestMapping("/student/scholarship")
@Slf4j
public class StudentScholarshipController {
	@Autowired
	private StudentScholarshipService studentScholarshipService;
	
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
	
	@GetMapping("scholarshipTypeList.do")
	@ResponseBody
	public List<ScholarshipTypeVO> scholarshipTypeList() {
		List<ScholarshipTypeVO> scholarshipTypeList = studentScholarshipService.readSholarshipTypeList();
		
		return scholarshipTypeList;
	}
	
	/**
	 * 장학금 신청 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("scholarshipList.do")
	public String studentScholarshipList(
		Principal principal,
		Model model
	) {
		List<ScholarshipApplyVO> scholarshipApplyList = studentScholarshipService.readScholarshipApplyList(principal.getName());
		
		int count = scholarshipApplyList.size();
		
		model.addAttribute("scholarshipApplyList", scholarshipApplyList);
		model.addAttribute("count", count);
		
		return "pfcp/student/scholarship/scholarship";
	}
	
	/**
	 * 장학금 신청 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("scholarshipDetail.do")
	public String studentScholarshipDetail() {
		return "pfcp/student/scholarship/scholarshipDetail";
	}
	
	/**
	 * 장학금 신청 등록(formUI)
	 * 
	 * @return
	 */
	@GetMapping("scholarshipInsertFormUI.do")
	public String studentScholarshipInsertFormUI() {
		return "pfcp/student/scholarship/scholarshipInsert";
	}
	
	/**
	 * 장학금 신청 등록(formProcess)
	 * 
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("scholarshipInsertFormProcess.do")
	public String studentScholarshipInsertFormProcess(
		@ModelAttribute(MODEL_NAME) ScholarshipApplyVO scholarshipApply,
		Principal principal, 
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) throws IOException {
		String lvn;
		
		if (!errors.hasErrors()) {
			String studentNo = principal.getName();
			
			scholarshipApply.setUserNo(studentNo);
			
			// -------------------------------
	        // 파일 처리 시작!
	        // -------------------------------
			MultipartFile file = scholarshipApply.getUploadFile();
			
			if (file != null && !file.isEmpty()) {
				// ID 생성
	            String atchId = "ATCH" + System.currentTimeMillis();
	            String fileRefNo = "FR" + System.currentTimeMillis();
	            
	            byte[] fileBytes = file.getBytes();

	            // ATCH_FILE insert
	            AtchFileVO atchFile = new AtchFileVO();
	            atchFile.setAtchId(atchId);
	            atchFile.setAtchMime(file.getContentType());
	            atchFile.setAtchOriginName(file.getOriginalFilename());
	            atchFile.setAtchSaveName(atchId + "_" + file.getOriginalFilename());
	            atchFile.setAtchSize(file.getSize());
	            atchFile.setAtchDate(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
	            atchFile.setAtchContent(fileBytes);
	            
	            atchFileService.createAtchFile(atchFile);
	            
	            // FILE_REF insert
	            FileRefVO fileRef = new FileRefVO();
	            fileRef.setFileRefNo(fileRefNo);
	            fileRef.setFileRefType("SCHOLARSHIP_APPLY");
	            fileRef.setFileRefTargetId(scholarshipApply.getApplyNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            scholarshipApply.setFileRefNo(fileRefNo);
			}
			// -------------------------------
	        // 파일 처리 끝!
	        // -------------------------------
			
			studentScholarshipService.createScholarshipApply(scholarshipApply);
			
			lvn = "redirect:/student/scholarship/scholarshipBenefit.do";
			
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, scholarshipApply);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/scholarship/scholarshipInsertFormUI.do";
		}
		
		return lvn;
	}
	
	/**
	 * 파일 다운로드
	 * 
	 * @param fileRefNo
	 * @throws IOException 
	 */
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
	 * 장학금 신청 수정
	 * 
	 * @return
	 */
	@GetMapping("scholarshipUpdate.do")
	public String studentScholarshipUpdate(
		String no,
		Model model
	) {
		ScholarshipApplyVO scholarshipApply = studentScholarshipService.readScholarshipApply(no);
		
		// fileRefNo가 있을 때만 파일 정보를 불러오기
		if (scholarshipApply.getFileRefNo() != null) {
	        FileRefVO fileRef = fileRefService.readFileRef(scholarshipApply.getFileRefNo());
	        
	        if (fileRef != null) {
	            AtchFileVO atchFile = atchFileService.readAtchFile(fileRef.getAtchId());
	            scholarshipApply.setAtchFile(atchFile);
	        }
	    }
		
		model.addAttribute("scholarship", scholarshipApply);
		
		return "pfcp/student/scholarship/scholarshipUpdate";
	}
	
	
	/**
	 * 장학금 신청 수정 process
	 * 
	 * @param no
	 * @param scholarshipApply
	 * @param errors
	 * @param redirectAttributes
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("scholarshipUpdateProcess.do")
	public String studentScholarshipUpdateProcess(
		String no,
		@ModelAttribute(MODEL_NAME) ScholarshipApplyVO scholarshipApply,
		BindingResult errors, 
		RedirectAttributes redirectAttributes
	) throws IOException {
		String lvn;
		
		if (!errors.hasErrors()) {
			// -------------------------------
	        // 파일 처리 시작!
	        // -------------------------------
			MultipartFile file = scholarshipApply.getUploadFile();
			
			if (file != null && !file.isEmpty()) {
				// ID 생성
	            String atchId = "ATCH" + System.currentTimeMillis();
	            String fileRefNo = "FR" + System.currentTimeMillis();
	            
	            byte[] fileBytes = file.getBytes();

	            // ATCH_FILE insert
	            AtchFileVO atchFile = new AtchFileVO();
	            atchFile.setAtchId(atchId);
	            atchFile.setAtchMime(file.getContentType());
	            atchFile.setAtchOriginName(file.getOriginalFilename());
	            atchFile.setAtchSaveName(atchId + "_" + file.getOriginalFilename());
	            atchFile.setAtchSize(file.getSize());
	            atchFile.setAtchDate(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
	            atchFile.setAtchContent(fileBytes);
	            
	            atchFileService.createAtchFile(atchFile);
	            
	            // FILE_REF insert
	            FileRefVO fileRef = new FileRefVO();
	            fileRef.setFileRefNo(fileRefNo);
	            fileRef.setFileRefType("SCHOLARSHIP_APPLY");
	            fileRef.setFileRefTargetId(scholarshipApply.getApplyNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            scholarshipApply.setFileRefNo(fileRefNo);
			}
			// -------------------------------
	        // 파일 처리 끝!
	        // -------------------------------
			
			studentScholarshipService.modifyScholarshipApply(scholarshipApply);
			
			lvn = "redirect:/student/scholarship/scholarshipList.do";
			
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, scholarshipApply);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/student/scholarship/scholarshipUpdateProcess.do?no=" + no;
		}
		
		return lvn;
	}
	
	/**
	 * 장학금 신청 삭제
	 * 
	 * @return
	 */
	@GetMapping("scholarshipDelete.do")
	public String studentScholarshipDelete(
		@RequestParam String no
	) {
		studentScholarshipService.removeScholarshipApply(no);
		
		return "redirect:/student/scholarship/scholarshipList.do";
	}
	
	/**
	 * 장학금 수혜 내역 조회
	 * 
	 * @return
	 */
	@GetMapping("scholarshipBenefit.do")
	public String studentScholarshipBenefit(
		Principal principal,
		Model model
	) {
		String studentNo = principal.getName();
		
		List<ScholarshipVO> scholarship = studentScholarshipService.readScholarshipBenefitList(studentNo);
		
		int count = scholarship.size();
		
		for (ScholarshipVO scholar : scholarship) {
			Integer tuitionAmount = scholar.getTuitionAmount();
			Integer disAmount = scholar.getDisAmount();
			
			scholar.setTotal(tuitionAmount - disAmount);
		}
		
		model.addAttribute("scholarship", scholarship);
		model.addAttribute("count", count);
		
		return "pfcp/student/scholarship/scholarshipBenefit";
	}
}
