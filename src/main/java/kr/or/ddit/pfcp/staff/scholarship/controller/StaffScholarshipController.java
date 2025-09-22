package kr.or.ddit.pfcp.staff.scholarship.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipApplyVO;
import kr.or.ddit.pfcp.common.vo.ScholarshipTypeVO;
import kr.or.ddit.pfcp.staff.scholarship.service.StaffScholarshipService;
import kr.or.ddit.pfcp.student.bill.scholarship.service.StudentScholarshipService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250628
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250628	|	양수민	|	최초 생성
 * 250711	|	양수민	|	  수정
 */
@Slf4j
@Controller
@RequestMapping("/staff/scholarship")
public class StaffScholarshipController {
	
	@Autowired
	private StudentScholarshipService studentScholarshipService;
	
	@Autowired
	private StaffScholarshipService staffScholarshipService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;
	
	private static final String MODEL_NAME = "scholarshipType";
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	@ModelAttribute(MODEL_NAME)
	public ScholarshipTypeVO scholarshipType() {
		return new ScholarshipTypeVO();
	}
	
	/**
	 * 장학금 목록 전체 조회
	 * @return
	 */
	@GetMapping("staffScholarshipList.do")
	public String scholarshipListUI(
		Model model
		, @RequestParam(defaultValue = "1") int pageNo
	) {
		int totalCnt = staffScholarshipService.readSchTypeTotalCount();
		int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;
	    int totalPage = (int) Math.ceil((double) totalCnt / pageSize);
		
	    model.addAttribute("totalCount", totalCnt);
		model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);
	    
		List<ScholarshipTypeVO> scholarshipTypeList = staffScholarshipService.readSholarshipTypeList(offset, pageSize);
		model.addAttribute("scholarshipTypeList", scholarshipTypeList);
		
		
//		System.out.println("들어옴1!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "pfcp/staff/scholarship/staffScholarshipList";
	}
	
	
	
	
	
	
	
	/**
	 * 장학금 목록 등록(formProcess)
	 * 
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("staffScholarshipInsertFormProcess.do")
	public String staffScholarshipInsertFormProcess(
		@ModelAttribute(MODEL_NAME) ScholarshipTypeVO scholarshipType,
		Principal principal, 
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) throws IOException {
		String lvn;
		
		if (!errors.hasErrors()) {
			// -------------------------------
	        // 파일 처리 시작!
	        // -------------------------------
			MultipartFile file = scholarshipType.getUploadFile();
			
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
	            fileRef.setFileRefType("SCHOLARSHIP_TYPE");
	            fileRef.setFileRefTargetId(scholarshipType.getSchTypeNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            scholarshipType.setSchFileRefNo(fileRefNo);
			}
			// -------------------------------
	        // 파일 처리 끝!
	        // -------------------------------
			
			staffScholarshipService.createScholarshipType(scholarshipType);
			
			lvn = "redirect:/staff/scholarship/staffScholarshipList.do";
			
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, scholarshipType);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/staff/scholarship/staffScholarshipList.do";
		}
		
		return lvn;
	}
	
	
	//  staff/scholarship/staffScholarshipUpdateProcess.do
	@PostMapping("staffScholarshipUpdateProcess.do")
	public String staffScholarshipUpdateProcess(
		String no,
		@ModelAttribute(MODEL_NAME) ScholarshipTypeVO scholarshipType,
		BindingResult errors, 
		@RequestParam String schTypeNo,
		@RequestParam String schName,
		@RequestParam String schDesc,
		@RequestPart(required = false) MultipartFile uploadFile,
		RedirectAttributes redirectAttributes
	) throws IOException {
		System.out.println("들어옴2!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
//		log.info(">>> schName: " + scholarshipType.getSchName());
//		log.info(">>> schDesc: " + scholarshipType.getSchDesc());
//		log.info(">>> schTypeNo: " + scholarshipType.getSchTypeNo());
//		log.info(">>> uploadFile: " + scholarshipType.getUploadFile());
		
		String lvn;
		
		if (!errors.hasErrors()) {
			// -------------------------------
	        // 파일 처리 시작!
	        // -------------------------------
			MultipartFile file = scholarshipType.getUploadFile();
			
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
	            fileRef.setFileRefType("SCHOLARSHIP_TYPE");
	            fileRef.setFileRefTargetId(scholarshipType.getSchTypeNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            scholarshipType.setSchFileRefNo(fileRefNo);
			}
			// -------------------------------
	        // 파일 처리 끝!
	        // -------------------------------
			
			staffScholarshipService.modifyScholarshipType(scholarshipType);
			
			redirectAttributes.addFlashAttribute("success", "수정 완료!");
			
			lvn = "redirect:/staff/scholarship/staffScholarshipList.do";
			
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, scholarshipType);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			redirectAttributes.addFlashAttribute("success", "수정에 실패하였습니다.");
			
			lvn = "redirect:/staff/scholarship/staffScholarshipList.do";
		}
		
		return lvn;
	}
	
	/**
	 * 장학금 목록 삭제
	 * 
	 * @return
	 */
	@GetMapping("staffScholarshipDelete.do")
	public String staffScholarshipDelete(
		@RequestParam String no
	) {
		staffScholarshipService.removeScholarshipType(no);
		
		return "redirect:/staff/scholarship/staffScholarshipList.do";
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
		
		System.out.println("들어옴3!!!!!!!!!!!!!!!!!!!!!!!!!!");
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
}
