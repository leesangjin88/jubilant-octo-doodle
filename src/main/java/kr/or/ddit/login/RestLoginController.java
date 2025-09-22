package kr.or.ddit.login;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.security.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rest/auth")
@RequiredArgsConstructor
public class RestLoginController {

  private final AuthenticationManager authenticationManager;

  private final JWTProvider jwtProvider;

  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody RestAuthVO auth) {
    UsernamePasswordAuthenticationToken inputData =
        UsernamePasswordAuthenticationToken.unauthenticated(auth.getUsername(), auth.getPassword());

    try {
      Authentication authentication = authenticationManager.authenticate(inputData);

      String encodedToken = jwtProvider.authenticationToToken(authentication);

      return ResponseEntity.ok().body(Map.of("token", encodedToken));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
          .body(Map.of("error", 401, "message", e.getMessage()));
    }

  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletResponse response) {
    Cookie authCookie = new Cookie("auth_token", "");
    authCookie.setMaxAge(0); // 즉시 만료
    authCookie.setPath("/");
    authCookie.setHttpOnly(true);

    response.addCookie(authCookie);

    return ResponseEntity.ok().build();
  }

  @GetMapping("/verify")
  public ResponseEntity<?> verifyToken(HttpServletRequest request) {
    log.info("토큰 검증 요청");

    Cookie[] cookies = request.getCookies();
    String token = null;

    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("auth_token".equals(cookie.getName())) {
          token = cookie.getValue();
          break;
        }
      }
    }

    if (token != null) {
      try {
        // JWT 토큰을 다시 Authentication 객체로 변환
        Authentication authentication = jwtProvider.tokenToAuthentication(token);

        return ResponseEntity.ok(Map.of("authenticated", true, "username", authentication.getName(),
            "authorities", authentication.getAuthorities()));
      } catch (Exception e) {
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
            .body(Map.of("authenticated", false, "message", "토큰이 유효하지 않습니다."));
      }
    }

    return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
        .body(Map.of("authenticated", false, "message", "토큰을 찾을 수 없습니다."));
  }
}
