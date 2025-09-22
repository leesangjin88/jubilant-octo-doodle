package kr.or.ddit.pfcp.common.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/file")
public class FileDownloadRestController {
  @Autowired
  private FileRefService fileRefService;
  
  @Autowired
  private AtchFileService atchFileService;
  
  @GetMapping("/download/{fileRefNo}")
  public void downloadFile(
        @PathVariable("fileRefNo") String fileRefNo,
        HttpServletResponse response
      ) throws IOException {
    try {
      log.info("RESTful 파일 다운로드 요청 - fileRefNo: {}", fileRefNo);
      
      // FILE_REF에서 ATCH_ID 가져오기
      FileRefVO fileRef = fileRefService.readFileRef(fileRefNo);
      
      if (fileRef == null) {
          log.error("파일 참조 정보가 없습니다. fileRefNo: {}", fileRefNo);
          response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일 참조 정보가 없습니다.");
          return;
      }
      
      String atchId = fileRef.getAtchId();
      log.info("파일 ID 조회 완료 - atchId: {}", atchId);
      
      // ATCH_FILE에서 파일 정보 가져오기
      AtchFileVO atchFile = atchFileService.readAtchFile(atchId);
      
      if (atchFile == null || atchFile.getAtchContent() == null) {
          log.error("파일 정보가 없습니다. atchId: {}", atchId);
          response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일 정보가 없습니다.");
          return;
      }
      
      log.info("파일 다운로드 시작 - 파일명: {}, 크기: {}", 
              atchFile.getAtchOriginName(), atchFile.getAtchSize());
      
      // 파일명 인코딩 처리
      String encodedFileName = getEncodedFileName(atchFile.getAtchOriginName());
      
      // 응답 헤더 설정
      response.setContentType(atchFile.getAtchMime());
      response.setHeader("Content-Disposition", 
              "attachment; filename=\"" + encodedFileName + "\"");
      response.setContentLength((int) atchFile.getAtchSize());
      
      // CORS 및 캐시 방지 헤더 설정
      response.setHeader("Access-Control-Allow-Origin", "*");
      response.setHeader("Access-Control-Allow-Methods", "GET");
      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
      response.setHeader("Pragma", "no-cache");
      response.setHeader("Expires", "0");
      
      // 파일 데이터 쓰기
      try (OutputStream out = response.getOutputStream()) {
          out.write(atchFile.getAtchContent());
          out.flush();
      }
      
      log.info("파일 다운로드 완료 - fileRefNo: {}", fileRefNo);
      
  } catch (Exception e) {
      log.error("파일 다운로드 오류 - fileRefNo: {}", fileRefNo, e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
              "파일 다운로드 중 오류가 발생했습니다.");
    }
    
  }
  
  private String getEncodedFileName(String fileName) throws UnsupportedEncodingException {
    // 브라우저별 파일명 인코딩 처리
    return URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
}
  
}
