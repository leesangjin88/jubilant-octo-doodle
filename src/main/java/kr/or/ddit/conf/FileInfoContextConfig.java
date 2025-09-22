package kr.or.ddit.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource("classpath:kr/or/ddit/FileInfo.properties")
public class FileInfoContextConfig implements WebMvcConfigurer{
  
  @Value("${imagesFolder}") // 실제 파일 경로
  private Resource imagesLocation;
  
  @Value("${imagesUrl}")    // 웹 접근 URL http://localhost/images/cca73d6a-5583-4a02-b00b-1349514822e0 << 이런식으로 저장
  private String imagesUrl;
  
  @Value("${user.dir}")
  private String userDir;
  
  /**
   * 파일 시스템 자원을 웹자원으로 매핑할 때 --> prodDetail.jsp에서 img 태그 사용에 필요한 url 구조
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler(imagesUrl+"/**")
            .addResourceLocations(imagesLocation);
  }
  
  @PostConstruct
  public void init() {
    log.info("userDir 주입 완료 {}", userDir);
    log.info("imagesLocation 주입 완료 {}", imagesLocation);
  }
}
