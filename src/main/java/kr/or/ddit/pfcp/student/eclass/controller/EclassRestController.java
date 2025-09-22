package kr.or.ddit.pfcp.student.eclass.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import kr.or.ddit.security.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;

/**
 * 수정일      |       수정자     |       수정 내용
 * ---------------------------------------------
 * 250630           이성화             최초 생성
 * 
 */
@RestController
@RequiredArgsConstructor
public class EclassRestController {
  private final JWTProvider jwtProvider;
  
  @GetMapping("/rest/auth")
  public ResponseEntity<?> checkAuthStatus(Authentication authentication) {
    if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
        return ResponseEntity.ok(Map.of(
            "authenticated", true,
            "username", authentication.getName(),
            "authorities", authentication.getAuthorities()
        ));
    } 
    
    return ResponseEntity.ok(Map.of("authenticated", false));
}
}
