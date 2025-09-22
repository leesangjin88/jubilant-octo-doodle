package kr.or.ddit.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.or.ddit.common.component.NotificationInterceptor;
import lombok.RequiredArgsConstructor;

/**
 * @author seokyungdeok
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	서경덕	|	최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
	private final NotificationInterceptor notificationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(notificationInterceptor)
		         .addPathPatterns("/**")
		         .excludePathPatterns("/css/**", "/js/**", "/images/**", "/favicon.ico");
	}
}
