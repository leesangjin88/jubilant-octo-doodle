package kr.or.ddit.pfcp.common.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.or.ddit.pfcp.common.service.UserService;
import kr.or.ddit.pfcp.common.vo.UserDataVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ajax/user")
public class UserController {
  
  private final UserService service;
  
  @PostMapping("/findUserNo")
  public ResponseEntity<?> findUserNo(
        @RequestBody UserDataVO userdata
      ){
    
      Map<String, Object> response = new HashMap<String, Object>();
     try {
      if(userdata.getUsername() == null || userdata.getUsername().trim().isEmpty()) {
         response.put("success", false);
         response.put("message", "이름을 입력해주세요.");
         return ResponseEntity.badRequest().body(response);
       }
       
       if(userdata.getBirthDate() == null) {
         response.put("success", false);
         response.put("message", "생년월일을 입력해주세요.");
         return ResponseEntity.badRequest().body(response);
       }
       
       String userNo = service.findUserName(userdata);
       
       if(userNo != null) {
         response.put("success", true);
         response.put("userNo", userNo);
         response.put("message", "사용자를 찾았습니다.");
         return ResponseEntity.ok(response);
       } else {
         response.put("success", false);
         response.put("message", "일치하는 사용자를 찾을 수 없습니다.");
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
       }
    } catch (Exception e) {
      response.put("success", false);
      response.put("message", "조회 중 오류가 발생하였습니다 : " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
     
  }
  
  
  @PostMapping("/replaceUserPassword")
  public ResponseEntity<?> findUserPassword(
        @RequestParam String userNo,
        @RequestParam String userEmail
      ){
    log.info("userNo {}", userNo);
    log.info("userEmail {}", userEmail);
    Map<String, Object> response = new HashMap<String, Object>();
    try {
     if(userEmail == null || userEmail.trim().isEmpty()) {
        response.put("success", false);
        response.put("message", "이메일을 입력해주세요.");
        return ResponseEntity.badRequest().body(response);
      }
     
     if(userNo == null || userNo.trim().isEmpty()) {
       response.put("success", false);
       response.put("message", "학번(교번)을 입력해주세요.");
       return ResponseEntity.badRequest().body(response);
     }
      
      boolean isValid = service.modifyUserPassword(userEmail);
      
      if(isValid) {
        response.put("success", true);
        response.put("message", "이메일 전송이 완료되었습니다.");
        return ResponseEntity.ok(response);
      } else {
        response.put("success", false);
        response.put("message", "일치하는 사용자를 찾을 수 없습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      }
   } catch (Exception e) {
     response.put("success", false);
     response.put("message", "조회 중 오류가 발생하였습니다 : " + e.getMessage());
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
   }
  }
  
  
}
