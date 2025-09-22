package kr.or.ddit.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import kr.or.ddit.pfcp.common.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTProvider {
   
   @Value("${jwt.secret-key}")
   private byte[] secreteKey;
   public static final long VALID_TERM = 1000 * 60 * 30 * 5;
   
   
  /**
   * RestLoginController에서 토큰을 생성할 때 사용
   *  
   * @param authentication
   * @return
   */
  public String authenticationToToken(Authentication authentication) {

      try {
         // 서명객체
         JWSSigner signer = new MACSigner(secreteKey);
         
//         Map<String, List<String>> permissionTable = new HashMap<String, List<String>>();
//         
//         permissionTable.put("c001", List.of("LPROD/READ", "LPROD/WRITE", "LPROD/DELETE", "BUYER"));
//         permissionTable.put("b001", List.of("LPROD/READ"));

         // 클레임 집합으로 페이로드 생성
         // 표준 클레임(sub, iat,exp,iss) + 비표준 클레임(email, role, scope...)
         JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject(authentication.getName()).issuer("http:/www.part8.com")
               .issueTime(new Date()).expirationTime(new Date(System.currentTimeMillis() + VALID_TERM))
//               .claim("role", "ROLE_ADMIN")
               .claim("scope", authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
//               .claim("scope", permissionTable.get(authentication.getName()))
               .build();

         // 헤더 + 페이로드
         SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
         // 서명 객체로 토큰의 대상을 설명
         signedJWT.sign(signer);

         String token = signedJWT.serialize();
         return token;
      } catch (JOSEException e) {
         // TODO Auto-generated catch block
         throw new RuntimeException(e);
      }
   }
  /**
   * BearerDummyFilter에서 토큰을 파싱할 때 사용됨 
   * @param token
   * @return
   */
  public Authentication tokenToAuthentication(String token) {
    try {
        SecretKey key = new SecretKeySpec(secreteKey, JWSAlgorithm.HS256.getName());

        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(key)
                                                    .macAlgorithm(MacAlgorithm.HS256)
                                                    .build();

        Jwt jwt = decoder.decode(token);
        log.info("디코딩 성공: subject={}, claims={}", jwt.getSubject(), jwt.getClaims());

        String username = jwt.getSubject();
        List<String> scope = jwt.getClaimAsStringList("scope");
        if (scope == null) scope = List.of(); // 보호 처리

        return UsernamePasswordAuthenticationToken.authenticated(
            username,
            scope,
            scope.stream().map(SimpleGrantedAuthority::new).toList()
        );

    } catch (JwtException e) {
        log.error("JWT 디코딩 오류: {}", e.getMessage(), e);
        throw e; // 그대로 전달해도 Controller에서 401 처리됨
    }
}
}
