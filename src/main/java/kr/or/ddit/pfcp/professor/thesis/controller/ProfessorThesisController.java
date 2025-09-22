package kr.or.ddit.pfcp.professor.thesis.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.ThesisVO;
import kr.or.ddit.pfcp.professor.thesis.service.ProfessorThesisService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * @author 김태수
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 9.	|	김태수	|	최초 생성
 */
@Controller
@RequestMapping("/professor/thesis")
@Slf4j
public class ProfessorThesisController {
	
	@Autowired
	private ProfessorThesisService professorThesisService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	private static final String MODELNAME = "thesis";
	
	@ModelAttribute(MODELNAME)
	public ThesisVO thesis() {
		return new ThesisVO();
	}
	
	/**
	 * 논문 조회
	 * @return
	 */
	@GetMapping("thesisList.do")
	public String thesisList(
			Principal principal,
			Model model,
			@RequestParam(defaultValue = "1") int pageNo
	) {
		String userNo = null; 
		if (principal != null) {
			userNo = principal.getName();
			model.addAttribute("userNo", userNo);
		}


		int pageSize = 10; 
		int offset = (pageNo - 1) * pageSize; 

		List<ThesisVO> thesisList = null;
		int totalCount = 0;

		if (userNo != null) {
			totalCount = professorThesisService.readTotalThesisCntByPaging(userNo);
			thesisList = professorThesisService.readThesisListByPaging(userNo, offset, pageSize);
		} else {
			totalCount = professorThesisService.readTotalThesisCnt(); 
			thesisList = professorThesisService.readThesisList(offset, pageSize);
		}

		int totalPage = (int) Math.ceil((double) totalCount / pageSize);

		model.addAttribute("count", totalCount); 
		model.addAttribute("thesisList", thesisList); 
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize); 
		model.addAttribute("totalPage", totalPage); 

		return "pfcp/professor/thesis/thesisList";
	}
	
	/**
	 * 논문 상세 조회
	 * @return
	 */
	@GetMapping("thesisDetail.do")
	public String thesisDetail(
			@RequestParam(value="no", required = false) String no
			,Model model
	) {
		ThesisVO thesis = professorThesisService.readThesisDetail(no);
		if (thesis == null) {
	        throw new IllegalArgumentException("해당 논문 정보를 찾을 수 없습니다: " + no);
	    }
		
		if (thesis.getFileRefNo() != null) {
	        FileRefVO fileRef = fileRefService.readFileRef(thesis.getFileRefNo());
	        
	        if (fileRef != null) {
	            AtchFileVO atchFile = atchFileService.readAtchFile(fileRef.getAtchId());
	            thesis.setAtchFile(atchFile);
	        }
	    }
		
		model.addAttribute("thesis",thesis);
		return "pfcp/professor/thesis/thesisDetail";
	}
	
	/**
	 * 논문 등록
	 * @return
	 */
	@GetMapping("thesisInsert.do")
	public String thesisInsert(
			Principal principal
			, Model model
	) {
		
		if (principal != null) {
	        String userNo = principal.getName(); 
	        model.addAttribute("userNo", userNo);
	    }
		return "pfcp/professor/thesis/thesisForm";
	}
	
	
	@PostMapping("thesisInsertProcess.do")
	public String thesisInsertProcess(
			@ModelAttribute(MODELNAME) ThesisVO thesis
			,Principal principal
			,BindingResult errors
			,RedirectAttributes redirectAttributes
	) throws IOException {
		
		String lvn;
		if(!errors.hasErrors()) {
			String prNo = principal.getName();
			thesis.setUserNo(prNo);
			
			
			MultipartFile file = thesis.getUploadFile();
			
			if (file != null && !file.isEmpty()) {
	            String atchId = "ATCH" + System.currentTimeMillis();
	            String fileRefNo = "FR" + System.currentTimeMillis();
	            
	            byte[] fileBytes = file.getBytes();

	            AtchFileVO atchFile = new AtchFileVO();
	            atchFile.setAtchId(atchId);
	            atchFile.setAtchMime(file.getContentType());
	            atchFile.setAtchOriginName(file.getOriginalFilename());
	            atchFile.setAtchSaveName(atchId + "_" + file.getOriginalFilename());
	            atchFile.setAtchSize(file.getSize());
	            atchFile.setAtchDate(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
	            atchFile.setAtchContent(fileBytes);
	            
	            atchFileService.createAtchFile(atchFile);
	            
	            FileRefVO fileRef = new FileRefVO();
	            fileRef.setFileRefNo(fileRefNo);
	            fileRef.setFileRefType("THESIS");
	            fileRef.setFileRefTargetId(thesis.getThesisNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            thesis.setFileRefNo(fileRefNo);
			}
			
			professorThesisService.createThesis(thesis);
			
			lvn = "redirect:/professor/thesis/thesisList.do";
			
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME,thesis);
			MultiValueMap<String, String> customError = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customError);
			lvn = "redirect:/professor/thesis/thesisInsert.do";
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
	 * 논문 수정
	 * @return
	 */
	@GetMapping("thesisUpdate.do")
	public String thesisUpdate(
			@RequestParam("no") String thesisNo
			,Model model
	) {
		
		ThesisVO thesis = professorThesisService.readThesisDetail(thesisNo);
		
		 if (thesis == null) {
		        return "redirect:/errorPage"; 
		    }

		    model.addAttribute(MODELNAME, thesis);
		
		return "pfcp/professor/thesis/thesisUpdateForm";
	}
	
	
	@PostMapping("thesisUpdateProcess.do")
	public String professorUpdateProcess(
		@ModelAttribute(MODELNAME) ThesisVO thesis, 
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) throws IOException{
		String lvn;
		if (!errors.hasErrors()) {
			
			MultipartFile file = thesis.getUploadFile();
			
			if (file != null && !file.isEmpty()) {
	            String atchId = "ATCH" + System.currentTimeMillis();
	            String fileRefNo = "FR" + System.currentTimeMillis();
	            
	            byte[] fileBytes = file.getBytes();

	            AtchFileVO atchFile = new AtchFileVO();
	            atchFile.setAtchId(atchId);
	            atchFile.setAtchMime(file.getContentType());
	            atchFile.setAtchOriginName(file.getOriginalFilename());
	            atchFile.setAtchSaveName(atchId + "_" + file.getOriginalFilename());
	            atchFile.setAtchSize(file.getSize());
	            atchFile.setAtchDate(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
	            atchFile.setAtchContent(fileBytes);
	            
	            atchFileService.createAtchFile(atchFile);
	            
	            FileRefVO fileRef = new FileRefVO();
	            fileRef.setFileRefNo(fileRefNo);
	            fileRef.setFileRefType("THESIS");
	            fileRef.setFileRefTargetId(thesis.getThesisNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            thesis.setFileRefNo(fileRefNo);
			}
			
			professorThesisService.modifyThesis(thesis);
			lvn = "redirect:/professor/thesis/thesisDetail.do?no=" + thesis.getThesisNo();
		} else {
			redirectAttributes.addFlashAttribute("thesis", thesis); 
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			lvn = "redirect:/professor/thesis/thesisUpdate.do?no=" +  thesis.getThesisNo();
		}
		return lvn;
	}
	
	/**
	 * 논문 삭제
	 * @return
	 */
	@GetMapping("thesisDelete.do")
	public String thesisDelete(
			@RequestParam("no") String thesisNo
	) {
		
		professorThesisService.removeThesis(thesisNo);
		return "redirect:/professor/thesis/thesisList.do";
	}
	
	
	/**
	 * 학생 논문 조회
	 * @return
	 */
	@GetMapping("studentThesisList.do")
	public String studentThesisList() {
		return "pfcp/professor/thesis/studentThesisList";
	}
	
	/**
	 * 학생 논문 상세 조회
	 * @return
	 */
	@GetMapping("studentThesisDetail.do")
	public String studentThesisDetail() {
		return "pfcp/professor/thesis/studentThesisDetail";
	}
	
}
