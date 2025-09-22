package kr.or.ddit.pfcp.common.service;

public interface EmailService {
  
  public void sendNewPasswordEmail(String toEmail, String name, String newPassword);
  
}
