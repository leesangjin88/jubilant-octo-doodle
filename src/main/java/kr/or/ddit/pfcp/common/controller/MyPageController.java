package kr.or.ddit.pfcp.common.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.service.MyPageService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.security.auth.RealUserWrapper;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김태수
 * @since 2025. 7. 12.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 12.	|	김태수	|	최초 생성
 */
@Slf4j
@Controller
public class MyPageController {

	@Autowired
	private MyPageService myPageSerivce;

	@Autowired
	private FileRefService fileRefService;

	@Autowired
	private AtchFileService atchFileService;
	
	static final String MODELNAME = "user";

	@Autowired
	private ErrorsUtils errorsUtils;
	
	@ModelAttribute(MODELNAME)
	public UserVO user() {
		return new UserVO();
	}
	
	
	@GetMapping("/mypage")
	public String mypage(Authentication auth, Model model) {
	    String userId = auth.getName();
	    UserVO user;

	    if (userId.startsWith("PR")) {
	        user = myPageSerivce.readMyPagePr(userId);
	    } else if (userId.startsWith("AD")) {
	        user = myPageSerivce.readMyPageAD(userId);
	    } else {
	        user = myPageSerivce.readMyPageST(userId);
	    }

	    model.addAttribute("user", user);

	    // fileRefVO 변수명 주의!
	    FileRefVO fileRefVO = fileRefService.readProfileImgByUserNo(user.getUserNo());
	    if (fileRefVO != null) {
	        // String 타입 fileRefNo라는 이름으로 넣어주기
	        model.addAttribute("fileRefNo", fileRefVO.getFileRefNo());
	    } else {
	        model.addAttribute("fileRefNo", null);
	    }

	    return "user/mypage";
	}



	
	/**
	 * 프로필 사진 등록 fromProcess
	 * @param user UserVO containing userNo and uploadFile
	 * @return ResponseEntity for AJAX response
	 */
	@PostMapping("ProfImgInsertProcess.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> profImgInsertProcessUI(
		@ModelAttribute(MODELNAME) UserVO user,
		BindingResult errors
	) throws IOException {
		Map<String, Object> response = new HashMap<>();
		
		log.info("Received user for profile image upload: {}", user.getUserNo());
		log.info("File received: {}", user.getUploadFile() != null ? user.getUploadFile().getOriginalFilename() : "No file");

		if(errors.hasErrors()) {
			response.put("success", false);
			response.put("message", "파일 업로드에 실패했습니다. 유효성 검사 오류.");
			response.put("errors", errorsUtils.errorsToMap(errors));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		MultipartFile file = user.getUploadFile();
		
		if (file == null || file.isEmpty()) {
			response.put("success", false);
			response.put("message", "업로드할 파일이 없습니다.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			// -------------------------------
			FileRefVO existingProfileFileRef = fileRefService.readProfileImgByUserNo(user.getUserNo());
			if (existingProfileFileRef != null) {
			    // 기존 프로필 이미지가 존재하므로 삭제
			    fileRefService.removeFileRef(existingProfileFileRef.getFileRefNo());
			}
			// -------------------------------
			if (existingProfileFileRef != null) {
			    log.info("Existing profile image found for user {}: {}", user.getUserNo(), existingProfileFileRef.getFileRefNo());
			    // 1. ATCH_FILE 테이블에서 실제 파일 데이터 삭제 (선택 사항이지만 권장)
			    //    (atchFileService에 deleteAtchFile(String atchId) 메서드가 있다고 가정)
			    //    int atchDeleteCount = atchFileService.deleteAtchFile(existingProfileFileRef.getAtchId());
			    //    log.info("Deleted {} records from ATCH_FILE for atchId {}", atchDeleteCount, existingProfileFileRef.getAtchId());

			    // 2. FILE_REF 테이블에서 참조 정보 삭제
			    int fileRefDeleteCount = fileRefService.removeFileRef(existingProfileFileRef.getFileRefNo());
			    log.info("Deleted {} records from FILE_REF for fileRefNo {}", fileRefDeleteCount, existingProfileFileRef.getFileRefNo());
			}
			// -------------------------------
			// 파일 처리 시작!
			// -------------------------------
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
			fileRef.setFileRefType("PROF_IMG");
			fileRef.setFileRefTargetId(user.getUserNo());
			fileRef.setAtchId(atchId);
			
			fileRefService.createFileRef(fileRef);
			
			// 사용자 정보에 새로운 fileRefNo 반영 (DB 업데이트는 별도의 서비스 메서드 필요)
			// 예: myPageSerivce.updateUserFileRefNo(user.getUserNo(), fileRefNo);
			
			response.put("success", true);
			response.put("message", "프로필 사진이 성공적으로 등록되었습니다.");
			response.put("fileRefNo", fileRefNo);
			
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			log.error("Error uploading profile image for user {}: {}", user.getUserNo(), e.getMessage(), e);
			response.put("success", false);
			response.put("message", "프로필 사진 등록 중 서버 오류가 발생했습니다.");
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 프로필 이미지 조회 (<img> 태그 src에 사용)
	 * @param fileRefNo
	 * @param response
	 * @throws IOException 
	 */
	@GetMapping("profileImage.do")
	public void serveProfileImage(
	    @RequestParam(value = "fileRefNo", required = false, defaultValue = "") String fileRefNo,
	    HttpServletResponse response
	) throws IOException {
	    // fileRefNo가 null 또는 빈 문자열인지 먼저 체크
	    if (fileRefNo == null || fileRefNo.trim().isEmpty()) {
	        log.info("fileRefNo is null or empty, serving default profile image.");

	        serveDefaultProfileImage(response);
	        return;
	    }

	    FileRefVO fileRef = null;
	    try {
	        fileRef = fileRefService.readFileRef(fileRefNo);
	    } catch (Exception e) {
	        log.error("Exception occurred while reading FileRef for fileRefNo {}: {}", fileRefNo, e.getMessage(), e);
	        // 파일 참조 조회 실패 시에도 기본 이미지 제공
	        serveDefaultProfileImage(response);
	        return;
	    }

	    if (fileRef == null) {
	        log.info("No FileRef found for fileRefNo {}, serving default profile image.", fileRefNo);
	        serveDefaultProfileImage(response);
	        return;
	    }

	    String atchId = fileRef.getAtchId();
	    if (atchId == null || atchId.trim().isEmpty()) {
	        log.info("FileRef atchId is null or empty for fileRefNo {}, serving default profile image.", fileRefNo);
	        serveDefaultProfileImage(response);
	        return;
	    }

	    AtchFileVO atchFile = null;
	    try {
	        atchFile = atchFileService.readAtchFile(atchId);
	    } catch (Exception e) {
	        log.error("Exception occurred while reading AtchFile for atchId {}: {}", atchId, e.getMessage(), e);
	        serveDefaultProfileImage(response);
	        return;
	    }

	    if (atchFile == null || atchFile.getAtchContent() == null) {
	        log.info("No profile image found in AtchFile for atchId {}, serving default image.", atchId);
	        serveDefaultProfileImage(response);
	        return;
	    }

	    // 정상적으로 이미지 응답
	    response.setContentType(atchFile.getAtchMime());
	    response.setContentLength((int) atchFile.getAtchSize());

	    try (OutputStream out = response.getOutputStream()) {
	        out.write(atchFile.getAtchContent());
	        out.flush();
	    } catch (Exception e) {
	        log.error("Error serving profile image for fileRefNo {}: {}", fileRefNo, e.getMessage(), e);
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "이미지 서비스 중 오류가 발생했습니다.");
	    }
	}

	/**
	 * 기본 프로필 이미지를 응답하는 헬퍼 메서드
	 */
	private void serveDefaultProfileImage(HttpServletResponse response) throws IOException {
	    byte[] defaultImageBytes;
	    try {
	        defaultImageBytes = loadDefaultProfileImage();
	    } catch (FileNotFoundException fnfe) {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "기본 이미지가 없습니다.");
	        return;
	    } catch (IOException ioe) {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "기본 이미지 서비스 중 오류가 발생했습니다.");
	        return;
	    }

	    response.setContentType("image/png"); 
	    response.setContentLength(defaultImageBytes.length);

	    try (OutputStream out = response.getOutputStream()) {
	        out.write(defaultImageBytes);
	        out.flush();
	    } catch (Exception e) {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "기본 이미지 서비스 중 오류가 발생했습니다.");
	    }
	}



	// Helper method to load your default image
	private byte[] loadDefaultProfileImage() throws IOException {
	    try (InputStream is = getClass().getResourceAsStream("/static/images/default_profile.png")) {
	        if (is == null) {
	            throw new FileNotFoundException("Default profile image not found in resources.");
	        }
	        return is.readAllBytes();
	    }
	}
}