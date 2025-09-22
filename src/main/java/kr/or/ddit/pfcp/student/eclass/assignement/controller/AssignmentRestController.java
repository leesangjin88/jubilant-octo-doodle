package kr.or.ddit.pfcp.student.eclass.assignement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AssignmentSubmissionVO;
import kr.or.ddit.pfcp.common.vo.AssignmentVO;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.student.eclass.assignement.service.AssignSubmissionService;
import kr.or.ddit.pfcp.student.eclass.assignement.service.AssignmentService;

/**
 * @author LSH
 * @since 20250710
 */
@RestController
@RequestMapping("/rest/assignment")
public class AssignmentRestController {
  
  @Autowired
  AssignmentService assignmentService;
  
  @Autowired
  FileRefService fileRefService;
  
  @Autowired
  AtchFileService atchFileService;
  
  @Autowired
  AssignSubmissionService assignSubmissionService;
   
  @GetMapping("{lecNo}")
  public ResponseEntity<List<AssignmentVO>> getAssignmentList(@PathVariable String lecNo){
    List<AssignmentVO> assignmentList = assignmentService.retrieveAssignments(lecNo);
    return ResponseEntity.ok(assignmentList);
  }
  
  @PostMapping
  public ResponseEntity<?> createAssignment(@RequestBody AssignmentVO assignment){
    assignmentService.createAssignment(assignment);
    return ResponseEntity.ok(Map.of("success", true));
  }
  
  @PostMapping("assign-submission")
  public ResponseEntity<?> uploadAssignment(
        @ModelAttribute AssignmentSubmissionVO assignmentSubmission
      ) throws IOException{
    MultipartFile file = assignmentSubmission.getUploadFile();
      
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
          fileRef.setFileRefType("ASSIGN_SUBMISSION");
          fileRef.setFileRefTargetId(assignmentSubmission.getSubmitNo());
          fileRef.setAtchId(atchId);
          
          fileRefService.createFileRef(fileRef);
          
          assignmentSubmission.setFileRefNo(fileRefNo);
          
          assignSubmissionService.createAssginSubmission(assignmentSubmission);
          
      }
      return ResponseEntity.ok(Map.of("success", true));
  }
  
  @GetMapping("assign-submission/{assignNo}/{studentNo}")
  public ResponseEntity<?> getAssignmentFileName(
        @PathVariable String assignNo,
        @PathVariable String studentNo
      ){
    AssignmentSubmissionVO submission = assignSubmissionService.readAssignmentSubmission(assignNo, studentNo);
    
    if(submission == null) {
      return ResponseEntity.ok(null);
    }
    
    return ResponseEntity.ok(Map.of(
          "fileName", submission.getFileName(),
          "fileRefNo", submission.getFileRefNo(),
          "submitScore", submission.getSubmitScore()
        ));
  }
  
  @GetMapping("assign-submission/list/{assignNo}")
  public ResponseEntity<?> getAssignSubmissionList(
        @PathVariable String assignNo
      ){
    
    List<AssignmentSubmissionVO> submissions = assignSubmissionService.retrieveAssignmentSubmissionByAssignNo(assignNo);
    
    if(submissions == null) {
      return ResponseEntity.ok(null);
    }
    
    return ResponseEntity.ok(submissions);
  }
  
  @PutMapping("assign-submission/score")
  public ResponseEntity<?> changeAssignSubmissionScore(
        @RequestBody AssignmentSubmissionVO assignmentSubmission
      ){
    assignSubmissionService.modifyAssignmentSubmissionScore(assignmentSubmission);
    return ResponseEntity.ok(Map.of("success", true));
  }
 
}
