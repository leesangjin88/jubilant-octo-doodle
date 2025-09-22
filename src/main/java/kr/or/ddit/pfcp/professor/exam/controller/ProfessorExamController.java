package kr.or.ddit.pfcp.professor.exam.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.ExamVO;
import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.common.vo.QuestionAnswerVO;
import kr.or.ddit.pfcp.professor.exam.service.ProfessorExamService;
import kr.or.ddit.validate.utils.ErrorsUtils;

/**
*
* @author 김태수
* @since 2025.07.01
* @see
﻿ * << 개정이력(Modification Information) >>
* 수정일	 |		수정자		|	수정 내용
* -----------|------------------|--------------------------
* 2025.07.01 | 		김태수   	|   최초 작성
* 2025.07.15 |		서경덕		|	내용 추가
* 
*/
@Controller
@RequestMapping("/professor/exam")
public class ProfessorExamController {
	@Autowired
	private ProfessorExamService professorExamService;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService;
	
	@Autowired
	private ErrorsUtils errorsUtils;
	
	static final String MODEL_NAME = "exam";
	
	@ModelAttribute(MODEL_NAME)
	public ExamVO exam() {
		return new ExamVO();
	}
	
	@GetMapping("facilityList.do")
	@ResponseBody
	public List<FacilityVO> facilityList() {
		int i = 0;
		
		List<FacilityVO> facilityList = professorExamService.readFacilityList();
		
		return facilityList;
	}
	
	@GetMapping("lecNameList.do")
	@ResponseBody
	public List<LectureVO> lecNameList(Principal principal) {
		String professorNo = principal.getName();
		
		List<LectureVO> lecNameList = professorExamService.readLecNameList(professorNo);
		
		return lecNameList;
	}
	
	/**
	 * 시험 정보 조회 - 시험 유형 , 시험 제목 , 교과목
	 * @return
	 */
	@GetMapping("examList.do")
	public String examList(
		Principal principal,
		Model model
	) {
		String professorNo = principal.getName();
		
		List<ExamVO> examList = professorExamService.readExamList(professorNo);
		
		int count = examList.size();
		
		model.addAttribute("examList", examList);
		model.addAttribute("count", count);
		
		return "pfcp/professor/exam/examList";
	}
	
	
	/**
	 * 시험 등록
	 * @return
	 */
	@GetMapping("examInsert.do")
	public String examInsert() {
		return "pfcp/professor/exam/examInsert";
	}
	
	@PostMapping("examInsertProcess.do")
	public String examInsertFormProcess(
		@RequestParam MultiValueMap<String, String> paramMap,
		@ModelAttribute(MODEL_NAME) ExamVO exam,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) throws IOException {
		String lvn;
		
		if(!errors.hasErrors()) {
			MultipartFile file = exam.getUploadFile();
			
			String examNo = "EX" + System.currentTimeMillis();
	        exam.setExamNo(examNo);
			
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
	            fileRef.setFileRefType("EXAM_FILE");
	            fileRef.setFileRefTargetId(examNo);
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            exam.setFileRefNo(fileRefNo);
			}
			
			professorExamService.createExam(exam);
			
			List<String> answers = new ArrayList<>();
			List<String> scores = new ArrayList<>();
			
			int i = 0;
			
			while (true) {
		        String key = "answerList[" + i + "]";
		        String scoreKey = "scoreList[" + i + "]";
		        
		        if (!paramMap.containsKey(key) || !paramMap.containsKey(scoreKey)) {
		        	break;
		        }
		        
		        answers.add(paramMap.getFirst(key));
		        scores.add(paramMap.getFirst(scoreKey));
		        
		        i++;
		    }
			
			if (!answers.isEmpty()) {
		        List<QuestionAnswerVO> questionAnswerList = new ArrayList<>();
		        
		        for (int idx = 0; idx < answers.size(); idx++) {
		            QuestionAnswerVO questionAnswer = new QuestionAnswerVO();
		            
		            questionAnswer.setExamNo(exam.getExamNo());
		            questionAnswer.setAnswer(Integer.parseInt(answers.get(idx)));
		            questionAnswer.setQuestionScore(Integer.parseInt(scores.get(idx)));
		            questionAnswer.setQuestionSeq(idx + 1);
		            
		            questionAnswerList.add(questionAnswer);
		        }
		        
		        professorExamService.createQuestionAnswer(questionAnswerList);
		    }
			
			lvn = "redirect:/professor/exam/examList.do";
			
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, exam);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/professor/exam/examInsert.do";
		}
		
		return lvn;
	}
	
	/**
	 * 시험 수정
	 * @return
	 * @throws IOException 
	 */
	@PostMapping("examUpdateProcess.do")
	public String examUpdate(
		String examNo,
		@RequestParam MultiValueMap<String, String> paramMap,
		@ModelAttribute(MODEL_NAME) ExamVO exam,
		BindingResult errors,
		RedirectAttributes redirectAttributes
	) throws IOException {
		String lvn;
		
		if (exam.getExamNo() == null || exam.getExamNo().isEmpty()) {
	        exam.setExamNo(examNo);
	    }
		
		if (!errors.hasErrors()) {
			// -------------------------------
	        // 파일 처리 시작!
	        // -------------------------------
			MultipartFile file = exam.getUploadFile();
			
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
	            fileRef.setFileRefTargetId(exam.getExamNo());
	            fileRef.setAtchId(atchId);
	            
	            fileRefService.createFileRef(fileRef);
	            
	            exam.setFileRefNo(fileRefNo);
			}
			// -------------------------------
	        // 파일 처리 끝!
	        // -------------------------------
			
			professorExamService.modifyExam(exam);
			
			List<String> answers = new ArrayList<>();
			List<String> scores = new ArrayList<>();
			
			int i = 0;
			
			while (true) {
		        String key = "answerList[" + i + "]";
		        String scoreKey = "scoreList[" + i + "]";
		        
		        if (!paramMap.containsKey(key) || !paramMap.containsKey(scoreKey)) {
		        	break;
		        }
		        
		        answers.add(paramMap.getFirst(key));
		        scores.add(paramMap.getFirst(scoreKey));
		        
		        i++;
		    }
			
			if (!answers.isEmpty()) {
		        List<QuestionAnswerVO> questionAnswerList = new ArrayList<>();
		        
		        for (int idx = 0; idx < answers.size(); idx++) {
		            QuestionAnswerVO questionAnswer = new QuestionAnswerVO();
		            
		            questionAnswer.setExamNo(exam.getExamNo());
		            questionAnswer.setAnswer(Integer.parseInt(answers.get(idx)));
		            questionAnswer.setQuestionScore(Integer.parseInt(scores.get(idx)));
		            questionAnswer.setQuestionSeq(idx + 1);
		            
		            questionAnswerList.add(questionAnswer);
		        }
		        
		        professorExamService.modifyQuestionAnswer(questionAnswerList);
		    }
			
			lvn = "redirect:/professor/exam/examList.do";
			
		} else {
			redirectAttributes.addFlashAttribute(MODEL_NAME, exam);
			
			MultiValueMap<String, String> customErrors = errorsUtils.errorsToMap(errors);
			
			redirectAttributes.addFlashAttribute("errors", customErrors);
			
			lvn = "redirect:/professor/exam/examDetail.do?examNo=" + examNo;
		}
		
		return lvn;
	}
	
	/**
	 * 시험 상세 조회
	 * @return
	 */
	@GetMapping("examDetail.do")
	public String examDetail(
		@RequestParam String examNo,
		Model model
	) {
		ExamVO exam = professorExamService.readExamDetail(examNo);
		
		List<QuestionAnswerVO> questionList = professorExamService.readQuestionList(examNo);
		System.out.println("체크: questionList => " + questionList);
		
	    exam.setQuestionAnswerList(questionList);
		
		model.addAttribute("exam", exam);
		
		return "pfcp/professor/exam/examDetail";
	}
	
	/**
	 * 시험 삭제
	 * @return
	 */
	@GetMapping("examDelete.do")
	public String examDelete(
		@RequestParam String examNo
	) {
		professorExamService.removeExam(examNo);
		
		return "redirect:/professor/exam/examList.do";
	}
	
	
	
	///////////////////////
	
	/**
	 * 문제 관리 - 문제 조회
	 * @return
	 */
	@GetMapping("questionList.do")
	public String questionList() {
		return "pfcp/professor/exam/questionList";
	}
	
	/**
	 * 문제 등록
	 * @return
	 */
	@GetMapping("questionInsert.do")
	public String questionInsert() {
		return "pfcp/professor/exam/questionInsert";
	}
	
	/**
	 * 문제 수정
	 * @return
	 */
	@GetMapping("questionUpdate.do")
	public String questionUpdate() {
		return "pfcp/professor/exam/questionUpdate";
	}
	
	/**
	 * 문제 상세 조회
	 * @return
	 */
	@GetMapping("questionDetail.do")
	public String questionDetail() {
		return "pfcp/professor/exam/questionDetail";
	}
	
	/**
	 * 문제 삭제
	 * @return
	 */
	@GetMapping("questionDelete.do")
	public String questionDelete() {
		return "redirect:/professor/exam/questionList.do";
	}
	
	
}
