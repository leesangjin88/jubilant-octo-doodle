package kr.or.ddit.pfcp.student.eclass.exam.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.pfcp.common.vo.ExamVO;
import kr.or.ddit.pfcp.common.vo.StudentExamSubmitRequest;
import kr.or.ddit.pfcp.student.eclass.exam.service.ExamService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest/exam")
@Slf4j
public class ExamRestController {
	@Autowired
	private ExamService examService;

	@GetMapping("{userNo}/{lecNo}")
	public ResponseEntity<List<ExamVO>> readExamList(@PathVariable String userNo, @PathVariable String lecNo) {
		List<ExamVO> examList = examService.readExamList(userNo, lecNo);
		
		return ResponseEntity.ok(examList);
	}
	
	@GetMapping("exam/{examNo}")
	public ResponseEntity<ExamVO> readExamDetail(
		@PathVariable String examNo
	) {
		ExamVO exam = examService.readExamDetail(examNo);
		
		return ResponseEntity.ok(exam);
	}
	
	@GetMapping("{examNo}/pdf")
	public ResponseEntity<byte[]> getExamPdf(@PathVariable String examNo) {
		ExamVO rawContent = examService.readPdfContentByExamNo(examNo);
		byte[] content = rawContent.getAtchContent();
	    
	    if (content == null) {
	        throw new RuntimeException("PDF content is null!");
	    }
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_PDF);
	    headers.setContentDispositionFormData("inline", "exam.pdf");
	    return ResponseEntity.ok()
	        .headers(headers)
	        .body(content);
	}


	@GetMapping("/no")
	public ResponseEntity<?> getExamNoByExamName(@RequestParam String examName) {
	    String examNo = examService.readExamNoByExamName(examName);
	    
	    if (examNo == null) {
	        return ResponseEntity.notFound().build();
	    }

	    HttpHeaders headers = new HttpHeaders();
	    headers.setCacheControl("no-cache, no-store, must-revalidate");
	    headers.add("Pragma", "no-cache");
	    headers.add("Expires", "0");
	    headers.add("ETag", "");

	    return ResponseEntity.ok()
	        .headers(headers)
	        .body(Map.of("examNo", examNo));
	}
	
	@GetMapping("/question-count")
	public ResponseEntity<?> getQuestionCountByExamNo(@RequestParam String examNo) {
	    int count = examService.readQuestionCountByExamNo(examNo);
	    return ResponseEntity.ok(Map.of("questionCount", count));
	}
	
	@PostMapping("/submit")
    public ResponseEntity<?> submitExam(@RequestBody StudentExamSubmitRequest request) {
		log.info("request: {}", request);
		
        int score = examService.calculateScore(request.getExamNo(), request.getAnswers());

        String examType = examService.getExamType(request.getExamNo());
        examService.saveGrade(request.getExamNo(), request.getUserNo(), score, examType);

        return ResponseEntity.ok(Map.of("message", "시험 제출 완료", "score", score));
    }
}