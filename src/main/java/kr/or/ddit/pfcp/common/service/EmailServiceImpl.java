package kr.or.ddit.pfcp.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
  
  @Autowired
  JavaMailSender mailSender;
  
  @Override
  public void sendNewPasswordEmail(String toEmail, String name, String newPassword) {
    SimpleMailMessage message;
    try {
      message = new SimpleMailMessage();
      message.setTo(toEmail);
      message.setSubject("새로운 비밀번호 안내");
      message.setText(
          "안녕하세요, " + name + "님!\n\n" +
          "요청하신 새로운 비밀번호를 발송해드립니다.\n\n" +
          "새로운 비밀번호: " + newPassword + "\n\n" +
          "로그인 후 반드시 비밀번호를 변경해주세요.\n\n" +
          "감사합니다."
      );
      mailSender.send(message);
      log.info("메일 전송 성공 {}", message);
    } catch (Exception e) {
      log.info("메일 전송 실패 {}", e.getMessage());
      e.printStackTrace();
    }
    
  }

}
