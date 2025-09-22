package kr.or.ddit.pfcp.student.lecture.material.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AssignmentSubmissionVO;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureMaterialVO;
import kr.or.ddit.pfcp.student.lecture.material.service.LectureMaterialService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/lecture/material")
public class LectureMaterialRestController {
	
	@Autowired
	LectureMaterialService lectureMaterialService;
	
	@Autowired
	FileRefService fileRefService;
	
	@Autowired
	AtchFileService atchFileService;
	
	@PostMapping
	public ResponseEntity<?> insertLectureMaterial(
	          @ModelAttribute LectureMaterialVO lectureMaterial
			) throws IOException{
		
		MultipartFile file = lectureMaterial.getUploadFile();
	      
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
	          fileRef.setFileRefType("LECTURE_MATERIAL");
	          fileRef.setAtchId(atchId);
	          
	          fileRefService.createFileRef(fileRef);
	          
	          lectureMaterial.setFileRefNo(fileRefNo);
	          
	          lectureMaterialService.createLectureMaterial(lectureMaterial);
	          
	      } else {
	        lectureMaterialService.createLectureMaterial(lectureMaterial);
	      }
	      return ResponseEntity.ok(Map.of("success", true));
	}
	
	@GetMapping("{lecNo}")
	public ResponseEntity<List<LectureMaterialVO>> getMaterialList(
				@PathVariable String lecNo
			){
		List<LectureMaterialVO> lectureMaterial = lectureMaterialService.retrieveLectureMaterial(lecNo);
		return ResponseEntity.ok(lectureMaterial);
	}
	
	@PutMapping
    public ResponseEntity<?> updateLectureMaterial(
              @ModelAttribute LectureMaterialVO lectureMaterial
            ) throws IOException{
        
        log.info("수정 요청 - materialNo: {}", lectureMaterial.getMaterialNo());
        
        MultipartFile file = lectureMaterial.getUploadFile();
        
        LectureMaterialVO existingMaterial = lectureMaterialService.retrieveLectureMaterialByMaterialNo(lectureMaterial.getMaterialNo());
        
        // 새 파일이 업로드된 경우
        if (file != null && !file.isEmpty()) {
            // 기존 파일 정보 조회
            // 기존 파일이 있다면 삭제 처리
            if (existingMaterial != null && existingMaterial.getFileRefNo() != null) {
              String existingFileRefNo = existingMaterial.getFileRefNo();

              // 기존 FILE_REF 정보 조회
              FileRefVO existingFileRef = fileRefService.readFileRef(existingFileRefNo);
              if (existingFileRef != null) {
                // 기존 ATCH_FILE 삭제 (선택사항 - 이력 관리를 위해 삭제하지 않을 수도 있음)
                // atchFileService.deleteAtchFile(existingFileRef.getAtchId());

                // 기존 FILE_REF 삭제
                fileRefService.removeFileRef(existingFileRefNo);
              }
            }
            
            // 새 파일 처리
            String newAtchId = "ATCH" + System.currentTimeMillis();
            String newFileRefNo = "FR" + System.currentTimeMillis();
            
            byte[] fileBytes = file.getBytes();
            
            // 새 ATCH_FILE insert
            AtchFileVO newAtchFile = new AtchFileVO();
            newAtchFile.setAtchId(newAtchId);
            newAtchFile.setAtchMime(file.getContentType());
            newAtchFile.setAtchOriginName(file.getOriginalFilename());
            newAtchFile.setAtchSaveName(newAtchId + "_" + file.getOriginalFilename());
            newAtchFile.setAtchSize(file.getSize());
            newAtchFile.setAtchDate(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
            newAtchFile.setAtchContent(fileBytes);
            
            atchFileService.createAtchFile(newAtchFile);
            
            // 새 FILE_REF insert
            FileRefVO newFileRef = new FileRefVO();
            newFileRef.setFileRefNo(newFileRefNo);
            newFileRef.setFileRefType("LECTURE_MATERIAL");
            newFileRef.setAtchId(newAtchId);
            
            fileRefService.createFileRef(newFileRef);
            
            lectureMaterial.setFileRefNo(newFileRefNo);
        }else {
          // 파일을 첨부하지 않은 경우 - 기존 파일 정보 유지
          log.info("파일 첨부 없음 - 기존 파일 유지");
          if (existingMaterial != null) {
              lectureMaterial.setFileRefNo(existingMaterial.getFileRefNo());
          }
      }
        // 파일이 없는 경우 기존 fileRefNo 유지
        
        // 강의 자료 정보 업데이트
        int result = lectureMaterialService.modifyLectureMaterial(lectureMaterial);
        
        if (result > 0) {
            return ResponseEntity.ok(Map.of("success", true, "message", "자료가 성공적으로 수정되었습니다."));
        } else {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "자료 수정에 실패했습니다."));
        }
    }
	
	@DeleteMapping("{materialNo}")
	public ResponseEntity<?> deleteMaterial(
	      @PathVariable Integer materialNo
	    ){
	  int result = lectureMaterialService.removeLectureMaterial(materialNo);
	  if(result > 0) {
	    return ResponseEntity.ok(Map.of("success", true));
	  } else {
	    return ResponseEntity.badRequest().body(Map.of("success", false, "message", "자료 삭제에 실패했습니다."));
	  }
	}

	
}
