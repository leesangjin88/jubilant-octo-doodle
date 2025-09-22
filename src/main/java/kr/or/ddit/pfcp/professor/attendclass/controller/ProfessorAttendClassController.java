package kr.or.ddit.pfcp.professor.attendclass.controller;

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
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.StudentVO;
import kr.or.ddit.pfcp.professor.attendclass.service.ProfessorAttendClassService;
import kr.or.ddit.pfcp.professor.lecture.service.ProfessorLectureService;
import kr.or.ddit.pfcp.staff.studentmanage.service.StudentmanageService;
import kr.or.ddit.validate.utils.ErrorsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 김태수
 * @since 2025. 7. 14.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 14.	|	김태수	|	최초 생성
 */
@Controller
@RequestMapping("/professor/attendclass")
@RequiredArgsConstructor
@Slf4j
public class ProfessorAttendClassController {
	
	
	private final ProfessorLectureService professorLectureService;
	
	@Autowired
	private ProfessorAttendClassService attendClassService;
	
	@Autowired
	private StudentmanageService studentService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;

	static final String MODELNAME = "lecture";  
	static final String LECTURE_MODEL_NAME = "lectureReq";

	@Autowired
	private ErrorsUtils errorsUtils;
	

	
	/**
	 * 허가된 강의
	 * 
	 * @param principal
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@GetMapping("attendclassList.do")
	public String attendclassList(
			Principal principal,
			Model model, 
			@RequestParam(defaultValue = "1") int pageNo
	) {
		String currentUserNo = null;
		if (principal != null) {
			currentUserNo = principal.getName();
			model.addAttribute("userNo", currentUserNo);
		}

		int pageSize = 100;
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

		return "pfcp/professor/attendclass/attendclassList";
	}

	 @GetMapping("attendclassDetail.do")
	    public String attendclassDetail(
	        @RequestParam(value = "no", required = false) String reqNo, 
	        Model model,
	        @RequestParam(defaultValue = "1") int studentPageNo 
	    ) {
		 
		 log.info("요청된 REQ_NO : {}", reqNo);
		 
		 LectureReqVO lectureReq = professorLectureService.readLectureDetail(reqNo); 
	        if (lectureReq == null) {
	        	log.warn("REQ_NO {}에 해당하는 LectureReqVO를 찾을 수 없습니다.", reqNo);
	            model.addAttribute("studentList", null);
	            model.addAttribute("studentCount", 0);
	            return "pfcp/professor/attendclass/attendclassDetail";
	        	
	           
	        }
	        FileRefVO fileRef = fileRefService.readFileRef(lectureReq.getFileRefNo());
	        if (fileRef != null) {
                AtchFileVO atchFile = atchFileService.readAtchFile(fileRef.getAtchId());
                lectureReq.setAtchFile(atchFile);
            }
	        model.addAttribute("lectureReq", lectureReq);

	        String lecNo = null;
	        if (lectureReq != null) {
	            lecNo = lectureReq.getLecNo();
	        }

	        if (lecNo != null && !lecNo.isEmpty()) {
	            int studentPageSize = 10;
	            int studentOffset = (studentPageNo - 1) * studentPageSize;

	            int totalStudentCount = attendClassService.countEnrStd(lecNo); 
	            List<LectureEnrVO> studentList = attendClassService.retrieveEnrStdPaging(lecNo, studentOffset, studentPageSize); 
	            int totalStudentPages = (int) Math.ceil((double) totalStudentCount / studentPageSize);

	            model.addAttribute("studentList", studentList);
	            model.addAttribute("studentCount", totalStudentCount);
	            model.addAttribute("studentPageNo", studentPageNo);
	            model.addAttribute("studentPageSize", studentPageSize);
	            model.addAttribute("totalStudentPages", totalStudentPages);
	        } else {
	            model.addAttribute("studentList", null);
	            model.addAttribute("studentCount", 0);
	            model.addAttribute("studentPageNo", 1);
	            model.addAttribute("studentPageSize", 10);
	            model.addAttribute("totalStudentPages", 1);
	        }

	        return "pfcp/professor/attendclass/attendclassDetail";
	    }
	 
	 
	 
		@GetMapping("attendStdDetail.do")
		public String attendStdDetail(
				@RequestParam("userNo") String userNo,
				Model model
		) {
			StudentVO stdDetail = studentService.readStudent(userNo);
			if (stdDetail != null) {
				model.addAttribute("stdDetail",stdDetail);
			}else {
				model.addAttribute("errorMessage", "정보를 가져오는 과정에서 오류가 발생했습니다.");
			}
			return "pfcp/professor/attendclass/attendStdDetail";
		}
	 
	 
	
	/**
	 * 강의 수정 폼 제공
	 * @param no 수정할 강의 신청 번호 (reqNo)
	 * @param model 뷰로 데이터를 전달할 모델 객체
	 * @return 강의 수정 폼 페이지 경로
	 */
	@GetMapping("attendclassUpdate.do")
	public String attendclassUpdate(
	        @RequestParam("no") String reqNo,
	        Model model
	) {
	    LectureReqVO lectureReq = professorLectureService.readLectureDetail(reqNo);


	    if (lectureReq == null) {
	        return "redirect:/errorPage"; 
	    }

	    model.addAttribute(LECTURE_MODEL_NAME, lectureReq);
	    return "pfcp/professor/attendclass/attendclassUpdateForm";
	}


	@PostMapping("attendclassUpdateProcess.do")
	public String attendclassUpdateProcess(
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
			lvn = "redirect:/professor/attendclass/attendclassDetail.do?no=" + lectureReq.getReqNo();
		} else {
			redirectAttributes.addFlashAttribute(LECTURE_MODEL_NAME, lectureReq); 
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			lvn = "redirect:/professor/attendclass/attendclassUpdate.do?no=" +  lectureReq.getReqNo();
		}
		return lvn;
	}
	
}
