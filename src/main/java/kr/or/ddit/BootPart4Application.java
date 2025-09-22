package kr.or.ddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 개발 환경에서 사용
 */
@SpringBootApplication
@EnableScheduling
public class BootPart4Application {

	public static void main(String[] args) {
		SpringApplication.run(BootPart4Application.class, args);
	}

}
