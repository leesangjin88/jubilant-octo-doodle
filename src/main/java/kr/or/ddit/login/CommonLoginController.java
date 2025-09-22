package kr.or.ddit.login;

import java.util.Map;
import org.springframework.boot.web.server.Cookie.SameSite;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.security.jwt.CookieBearerTokenResolver;
import kr.or.ddit.security.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommonLoginController {
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final SecurityContextRepository securityContextRepository;
    private final LogoutHandler logoutHandler;
    
    @PostMapping("/common/auth/revoke")
    public ResponseEntity<?> revoke(
        HttpServletRequest req
        , HttpServletResponse resp
        , Authentication authentication
    ) {
      
        // 세션 무효화
        HttpSession session = req.getSession(false);
        if (session != null) {
          session.invalidate();
        }
  
        // 세션 삭제
        SecurityContextHolder.clearContext();
  
        // 세션 기반 인증 상태를 로그아웃으로 처리
        logoutHandler.logout(req, resp, authentication);
        
        // 토큰 기반 인증 상태를 로그아웃으로 처리
        String tokenCookie = ResponseCookie.from(CookieBearerTokenResolver.ACCESSTOKENCOOKIE)
                .value("")
                .path("/")
//                .domain("https://f046be59057e.ngrok-free.app")
                .httpOnly(true)
//                .secure(true)
                .sameSite(SameSite.STRICT.attributeValue())
                .maxAge(0)
                .build().toString();
        
        String sessionCookie = ResponseCookie.from("JSESSIONID")
                .value("")
                .path("/")
                .httpOnly(true)
                .sameSite(SameSite.STRICT.attributeValue())
                .maxAge(0)
                .build().toString();
                                         
        
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, tokenCookie)
                .header(HttpHeaders.SET_COOKIE, sessionCookie)
                .build();
    }
    

    @PostMapping("/common/auth")
    public ResponseEntity<?> authenticate(
        @RequestBody RestAuthVO auth
        , HttpServletRequest req
        , HttpServletResponse resp
    ) {
        UsernamePasswordAuthenticationToken inputData =
                UsernamePasswordAuthenticationToken
                    .unauthenticated(auth.getUsername(), auth.getPassword());
        
        try {
            Authentication authentication = authenticationManager.authenticate(inputData);
            
            // 토큰 기반 인증 처리
            String encodedToken = jwtProvider.authenticationToToken(authentication);
            
            String tokenCookie = ResponseCookie.from(CookieBearerTokenResolver.ACCESSTOKENCOOKIE)
                        .value(encodedToken)
                        .path("/")
//                      .domain(".naver.com")
                        .httpOnly(true)
//                      .secure(true)
                        .sameSite(SameSite.STRICT.attributeValue())
                        .maxAge(JWTProvider.VALID_TERM / 1000)
                        .build().toString();
                        
            
            // 세션 기반 인증 처리
            SecurityContext newContext = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(newContext);
            securityContextRepository.saveContext(newContext, req, resp);
            
            return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, tokenCookie)
                            .build();
            
        } catch (AuthenticationException e) {
            
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                            .body(Map.of("error", 401, "message", e.getMessage()));
        }
        
    }
}
