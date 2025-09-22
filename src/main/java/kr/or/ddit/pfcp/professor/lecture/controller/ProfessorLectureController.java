package kr.or.ddit.pfcp.professor.lecture.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.professor.lecture.service.ProfessorLectureService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/professor/lecture")
@RequiredArgsConstructor
@Slf4j
public class ProfessorLectureController {

	private final ProfessorLectureService professorLectureService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;

	static final String MODELNAME = "lecture";  
	static final String LECTURE_MODEL_NAME = "lectureReq";

	@Autowired
	private ErrorsUtils errorsUtils;

	@ModelAttribute(MODELNAME)
	public LectureReqVO lecture() {
		return new LectureReqVO();
	}

	/**
	 * 강의 목록 조회
	 * @param principal 현재 로그인한 사용자 정보 (교수 번호 획득용)
	 * @param model 뷰로 데이터를 전달할 모델 객체
	 * @param userNo (선택 사항) 특정 교수 번호로 필터링할 경우 사용. 이전에 principalUserNo로 통합될 가능성 있음.
	 * @return 강의 목록 페이지 경로
	 */
	@GetMapping("lectureList.do")
	public String lecturetList(
	    Principal principal,
	    Model model,
	    @RequestParam(defaultValue = "1") int pageNo
	) {
	    String currentUserNo = null;
	    if (principal != null) {
	        currentUserNo = principal.getName();
	        model.addAttribute("userNo", currentUserNo);
	    }

	    int pageSize = 10;
	    int offset = (pageNo - 1) * pageSize;

	    List<LectureReqVO> lectureList = null;
	    int totalCount = 0;

	    if (currentUserNo != null) {
	        totalCount = professorLectureService.readTotalLectureCntByPaging(currentUserNo);
	        lectureList = professorLectureService.readLectureListByPaging(currentUserNo, offset, pageSize);
	    } else {
	        totalCount = professorLectureService.readTotalLectureCnt();
	        lectureList = professorLectureService.readLectureList(offset, pageSize);
	    }

	    int totalPage = (int) Math.ceil((double) totalCount / pageSize);

	    model.addAttribute("count", totalCount);
	    model.addAttribute("lecture", lectureList);
	    model.addAttribute("pageNo", pageNo);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);

		return "pfcp/professor/lecture/lectureList";
	}
	
	
	@GetMapping("lectureDetail.do")
	public String lectureDetail(
			@RequestParam(value = "no", required = false) String no
			,Model model
	) {
		
		LectureReqVO lectureReq = professorLectureService.readLectureDetail(no);

		if (lectureReq.getFileRefNo() != null) {
	        FileRefVO fileRef = fileRefService.readFileRef(lectureReq.getFileRefNo());
	        
	        if (fileRef != null) {
	            AtchFileVO atchFile = atchFileService.readAtchFile(fileRef.getAtchId());
	            lectureReq.setAtchFile(atchFile);
	        }
	    }
		
		model.addAttribute("lectureReq",lectureReq);
		return "pfcp/professor/lecture/lectureDetail";
	}
	

	/**
	 * 강의 신청 등록 폼 제공
	 * @param principal 현재 로그인한 사용자 정보
	 * @param model JSP로 데이터를 전달할 모델
	 * @return 강의 신청 폼 페이지 경로
	 */
	@GetMapping("lectureInsert.do")
	public String lectureInsert(
		Principal principal,
		Model model
	) {
		if (principal != null) {
			model.addAttribute("userNo", principal.getName());
		}
		return "pfcp/professor/lecture/lectureForm"; 
	}
	
	
    @GetMapping("/categories.do")
    @ResponseBody
    public List<String> getLectureCategories() {
        return professorLectureService.retrieveLectureCategories();
    }

    @GetMapping("/subjectNames.do") 
    @ResponseBody
    public List<Map<String, Object>> getSubjectNames() { 
        return professorLectureService.retrieveSubjects(); 
    }

    @GetMapping("/classroomNames.do")
    @ResponseBody
    public List<String> getClassroomNames() { 
        return professorLectureService.retrieveClassroomNames(); 
    }
	

	/**
	 * 강의 등록 formProcess
	 * @param lectureReq 폼에서 바인딩된 강의 신청 정보 (LectureReqVO)
	 * @param errors 데이터 바인딩/검증 오류 결과
	 * @param redirectAttributes 리다이렉트 시 데이터 전달용
	 * @param uploadFile 강의 계획서 파일
	 * @return 리다이렉트 경로
	 */
	@PostMapping("lecturetInsertProcess.do")
	public String lecturetInsertProcess(
		@ModelAttribute(MODELNAME) LectureReqVO lectureReq, 
		BindingResult errors,
		RedirectAttributes redirectAttributes,
		@RequestParam("uploadFile") MultipartFile uploadFile 
	) throws IOException{
		String lvn;
		if (!errors.hasErrors()) {

			MultipartFile file = lectureReq.getUploadFile();

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
				fileRef.setFileRefType("LECTURE_REQ_REPORT");
				fileRef.setFileRefTargetId(lectureReq.getReqNo());
				fileRef.setAtchId(atchId);

				fileRefService.createFileRef(fileRef);

				lectureReq.setFileRefNo(fileRefNo);
			}

			professorLectureService.createLecture(lectureReq);
			lvn = "redirect:/professor/lecture/lectureList.do";

		} else {
			redirectAttributes.addFlashAttribute(MODELNAME, lectureReq); // 모델명 통일
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);

			lvn = "redirect:/professor/lecture/lectureInsert.do";
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
	 * 강의 수정 폼 제공
	 * @param no 수정할 강의 신청 번호 (reqNo)
	 * @param model 뷰로 데이터를 전달할 모델 객체
	 * @return 강의 수정 폼 페이지 경로
	 */
	@GetMapping("lectureUpdate.do")
	public String lecturetUpdate(
	        @RequestParam("no") String reqNo,
	        Model model
	) {
	    LectureReqVO lectureReq = professorLectureService.readLectureDetail(reqNo);


	    if (lectureReq == null) {
	        return "redirect:/errorPage"; 
	    }

	    model.addAttribute(LECTURE_MODEL_NAME, lectureReq);
	    return "pfcp/professor/lecture/lectureUpdateForm";
	}

	/**
	 * 강의 수정 formProcess
	 *
	 * @param reqNo 수정할 강의 신청 번호
	 * @param lectureReq 폼에서 바인딩된 강의 신청 정보 (LectureReqVO)
	 * @param errors 데이터 바인딩/검증 오류 결과
	 * @param redirectAttributes 리다이렉트 시 데이터 전달용
	 * @return 리다이렉트 경로
	 */
	@PostMapping("lectureUpdateProcess.do")
	public String professorUpdateProcess(
		@ModelAttribute(LECTURE_MODEL_NAME) LectureReqVO lectureReq, 
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) throws IOException{
		String lvn;
		if (!errors.hasErrors()) {
			
			MultipartFile file = lectureReq.getUploadFile();
			
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
	            fileRef.setFileRefType("LECTURE_REQ_REPORT");
	            fileRef.setFileRefTargetId(lectureReq.getReqNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            lectureReq.setFileRefNo(fileRefNo);
			}
			
			professorLectureService.modifyLecture(lectureReq);
			lvn = "redirect:/professor/lecture/lectureDetail.do?no=" + lectureReq.getReqNo();
		} else {
			redirectAttributes.addFlashAttribute(LECTURE_MODEL_NAME, lectureReq); 
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			lvn = "redirect:/professor/lecture/lectureUpdate.do?no=" +  lectureReq.getReqNo();
		}
		return lvn;
	}


	/**
	 * 강의 삭제
	 * @param no 삭제할 강의 신청 번호 (reqNo)
	 * @return 리다이렉트 경로
	 */
	@GetMapping("lectureDelete.do")
	public String lecturetDelete(
			@RequestParam("no") String reqNo 
	) {
		professorLectureService.removeLecture(reqNo);
		return "redirect:/professor/lecture/lectureList.do";
	}


	//////////////////////////////////////////////////////////////////////////////

	/**
	 * 과제 목록 조회
	 * @return
	 */
	@GetMapping("assignmentList.do")
	public String assignmentList() {
		return "pfcp/professor/lecture/assignmentList";
	}

	/**
	 * 과제 목록 상세조회
	 * @return
	 */
	@GetMapping("assignmentDetail.do")
	public String assignmentDetail() {
		return "pfcp/professor/lecture/assignmentDetail";
	}

	/**
	 * 과제 등록
	 * @return
	 */
	@GetMapping("assignmentInsert.do")
	public String assignmentInsert() {
		return "pfcp/professor/lecture/assignmentForm";
	}

	/**
	 * 과제 수정
	 * @return
	 */
	@GetMapping("assignmentUpdate.do")
	public String assignmentUpdate() {
		return "pfcp/professor/lecture/assignmentUpdateForm";
	}


	/**
	 * 과제 삭제
	 * @return
	 */
	@GetMapping("assignmentDelete.do")
	public String assignmentDelete() {
		return "redirect:/professor/lecture/assignmentList.do";
	}

	/**
	 * 강의 평가 조회
	 * @return
	 */
	@GetMapping("assignmentEvaluationList.do")
	public String assignmentEvaluationList() {
		return "pfcp/professor/lecture/assignmentEvaluationList";
	}

	/**
	 * 강의 평가 등록
	 * @return
	 */
	@GetMapping("assignmentEvaluationInsert.do")
	public String assignmentEvaluationInsert() {
		return "pfcp/professor/lecture/assignmentEvaluationInsert";
	}

	/**
	 * 강의 평가 수정
	 * @return
	 */
	@GetMapping("assignmentEvaluationUpdate.do")
	public String assignmentEvaluationUpdate() {
		return "pfcp/professor/lecture/assignmentEvaluationUpdate";
	}

	/**
	 * 강의 평가 삭제 ..?
	 * @return
	 */
	@GetMapping("assignmentEvaluationDelete.do")
	public String assignmentEvaluationDelete() {
		return "redirect:/professor/lecture/assignmentEvaluationList.do";
	}

	


}