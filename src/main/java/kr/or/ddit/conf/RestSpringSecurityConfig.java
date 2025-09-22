package kr.or.ddit.conf;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import com.nimbusds.jose.JWSAlgorithm;
import kr.or.ddit.security.jwt.CookieBearerTokenResolver;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "cors")
@Configuration
public class RestSpringSecurityConfig {
  
  @Autowired
  private CorsConfigurationSource restCorsConfigurationSource;

   @Value("${jwt.secret-key}")
   private byte[] secretKey;
   
   @Bean
   public JwtDecoder jwtDecoder() {
     SecretKey key = new SecretKeySpec(secretKey, JWSAlgorithm.HS256.getName());
     NimbusJwtDecoder decoder =  NimbusJwtDecoder.withSecretKey(key)
                                                 .macAlgorithm(MacAlgorithm.HS256)
                                                 .build();
     return decoder;
   }
   
   @Autowired
   private CookieBearerTokenResolver cookieBearerTokenResolver;
   
   @Bean
   @Order(1)
   public SecurityFilterChain restSecurityFilterChain(HttpSecurity http) throws Exception {
      
      http.securityMatcher("/rest/**")
         .csrf(csrf->csrf.disable())
         .cors(cors->cors.configurationSource(restCorsConfigurationSource))
         .authorizeHttpRequests(authorize->
            authorize
              .requestMatchers("/rest/auth/**").permitAll()
//              .requestMatchers("/rest/buyer/**").hasAuthority("SCOPE_BUYER")
//              .requestMatchers(HttpMethod.GET, "/rest/lprod").hasAuthority("SCOPE_LPROD/READ")
//              .requestMatchers(HttpMethod.DELETE, "/rest/lprod/**").hasAuthority("SCOPE_LPROD/DELETE")
//              .requestMatchers("/rest/lprod/**").hasAuthority("SCOPE_LPROD/WRITE")
//              .requestMatchers("/rest/lprod/**").hasRole("ADMIN")
              .anyRequest().permitAll()
         )
         .sessionManagement(session->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         )
         .oauth2ResourceServer(oauth2resourceserver->
            oauth2resourceserver.jwt(jwt->jwt.decoder(jwtDecoder()))    // jwt.decoder(jwtDecoder()) 부분은 생략해도 Bean으로 등록해놓으면 알아서 등록됨
         );
      return http.build();
   }
   
}
