package kr.or.ddit.conf;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class SiteMeshFilterConfig {
  
  @Bean
  FilterRegistrationBean<ConfigurableSiteMeshFilter> siteMeshFilter(){
    FilterRegistrationBean<ConfigurableSiteMeshFilter> filter = 
        new FilterRegistrationBean<ConfigurableSiteMeshFilter>();
    // 필터 생성
    filter.setFilter(
          ConfigurableSiteMeshFilter.create((builder)->
            builder.setDecoratorPrefix("/WEB-INF/decorators/")
                   .addExcludedPath("/ajax/**")
                   .addExcludedPath("/rest/**")
                   .addExcludedPath("/swagger**/*")
                   .addDecoratorPath("/member/*Insert*", "simpleDecorator.jsp")
                   .addDecoratorPath("/login", "simpleDecorator.jsp")
                   .addDecoratorPath("/logout", "simpleDecorator.jsp")
                   .addDecoratorPath("/**", "mantisDecorator.jsp")
            )
        );
    
    // 필터 우선순위 설정
    filter.setOrder(Ordered.LOWEST_PRECEDENCE - 100);   // 우선 순위가 낮아짐
    
    // 필터 매핑 설정
    filter.addUrlPatterns("/*");
    
    return filter;
  }
  
}
