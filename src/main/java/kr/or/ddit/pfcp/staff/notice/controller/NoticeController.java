package kr.or.ddit.pfcp.staff.notice.controller;

import java.io.IOException;
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

import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.service.UserService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.BoardVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.staff.notice.service.NoticeService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250628
 * 
 * 
 * * 수정일		수정자		수정 내용
 * ---------------------------------------
 * 20250706     이상진       일정 부분 수정
 */

@Slf4j
@Controller
@RequestMapping("/staff/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;
	
	static final String MODELNAME = "board";
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	@ModelAttribute(MODELNAME)
	public BoardVO board() {
		return new BoardVO();
	}
	
	/**
	 * 공지 사항 전체 조회
	 * @return
	 */
	@GetMapping("noticeList.do")
	public String noticeListUI(
			
		Model model,
		@RequestParam(defaultValue = "1") int pageNo
	) {
		int totalCnt = service.readTotalCount();
		int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;
	    int totalPage = (int) Math.ceil((double) totalCnt / pageSize);
	    
	    log.info("📌 totalCnt: {}", totalCnt);
	    log.info("📌 pageSize: {}", pageSize);
	    log.info("📌 totalPage: {}", totalPage);
		
		List<BoardVO> board = service.readBoardList(offset,pageSize);
		model.addAttribute("board", board);
		model.addAttribute("totalCount", totalCnt);
		model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);
		
		return "pfcp/staff/notice/noticeList";
	}
	
	/**
	 * 공지 사항 상세 조회
	 * @return
	 */
	@GetMapping("noticeDetail.do")
	public String noticeDetailUI(
		@RequestParam String what,
		Model model,
		Principal principal,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if(principal != null){
			
			BoardVO board = service.readBoard(what);
			model.addAttribute("board", board);
			
			String userNo = principal.getName();
			model.addAttribute("userNo", userNo);
			
			lvn = "pfcp/staff/notice/noticeDetail";
		}else {
			redirectAttributes.addFlashAttribute("success", "로그인 후 이용 가능합니다");
	        lvn = "redirect:/staff/notice/noticeList.do";
		}
	    
		return lvn;
	}
	
	/**
	 * 공지 사항 등록
	 * @return
	 */
	@GetMapping("noticeInsert.do")
	public String noticeInsertUI(
		Principal principal,
		Model model
	
	) {
		
		String userNo = principal.getName();
		model.addAttribute("userNo", userNo);
		
		return "pfcp/staff/notice/noticeInsert";
	}
	
	/**
	 * 공지 사항 등록 fromProcess
	 * @return
	 */
	@PostMapping("noticeInsertProcess.do")
	public String noticeInsertProcessUI(
		@ModelAttribute(MODELNAME) BoardVO board,
		Principal principal,
		BindingResult errors,
		RedirectAttributes redirectAttributes		
	) throws IOException {
		String lvn;
		
		String userNo = principal.getName();
		
		board.setWriterNo(userNo);
		
		if(!errors.hasErrors()) {
			// -------------------------------
	        // 파일 처리 시작!
	        // -------------------------------
			MultipartFile file = board.getUploadFile();
			
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
	            fileRef.setFileRefType("BOARD_APPLY");
	            fileRef.setFileRefTargetId(board.getBoardNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            board.setFileRefNo(fileRefNo);
			}
			// -------------------------------
	        // 파일 처리 끝!
	        // -------------------------------
			service.createBoard(board);
			redirectAttributes.addFlashAttribute("success", "등록 완료!");
			lvn = "redirect:/staff/notice/noticeList.do";
		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, board);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			lvn = "redirect:/staff/notice/noticeInsert.do";
		}
		
		return lvn;
	}
	
	
	
	
	
	/**
	 * 공지 사항 수정
	 * @return
	 */
	@GetMapping("noticeUpdate.do")
	public String noticeUpdateUI(
		String what,
		Model model
	) {
		BoardVO board = service.readBoard(what);
		model.addAttribute("board", board);
		model.addAttribute("mode", "edit");
		
		return "pfcp/staff/notice/noticeDetail";
	}
	
	/**
	 * 공지 사항 수정 fromProcess
	 * @return
	 */
	@PostMapping("noticeUpdateProcess.do")
	public String noticeUpdateProcessUI(
		String what,
		@ModelAttribute(MODELNAME) BoardVO board,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) {
		String lvn;
		
		if(!errors.hasErrors()) {
			service.modifyBoard(board);
			redirectAttributes.addFlashAttribute("success", "수정 완료!");
			lvn = "redirect:/staff/notice/noticeDetail.do?what=" + board.getBoardNo();
		}else {
			redirectAttributes.addFlashAttribute(MODELNAME, board);
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			lvn= "redirect:/staff/notice/noticeUpdate.do?what="+what;
		}
		
		return lvn;
	}
	
	/**
	 * 공지 사항 삭제
	 * @return
	 */
	@GetMapping("noticeDelete.do")
	public String noticeDeleteUI(
		String what
	) {
		service.modifyBoardDeleted(what);
		
		return "redirect:/staff/notice/noticeList.do";
	}
}




















