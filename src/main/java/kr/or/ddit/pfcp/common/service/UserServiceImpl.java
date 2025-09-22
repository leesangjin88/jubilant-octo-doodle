package kr.or.ddit.pfcp.common.service;

import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import kr.or.ddit.pfcp.common.mapper.UserMapper;
import kr.or.ddit.pfcp.common.vo.UserDataVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  
  private final UserMapper mapper;
  
  private final PasswordEncoder passwordEncoder;
  
  private final AuthenticationManager authenticationManager;
  
  @Autowired
  private EmailService emailService;

  @Override
  public UserVO readMember(String username) {
    return mapper.selectUser(username);
  }
  
  // 임시 비밀번호 생성
  private String generateRandomPassword() {
      String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
      SecureRandom random = new SecureRandom();
      StringBuilder password = new StringBuilder();
      
      for (int i = 0; i < 8; i++) {
          password.append(characters.charAt(random.nextInt(characters.length())));
      }
      
      return password.toString();
  }

  @Override
  public String findUserName(UserDataVO userdata) {
    return mapper.findUserId(userdata);
  }

  @Override
  public boolean modifyUserPassword(String email) {
    
    UserVO user = mapper.findUserEmail(email);
    
    if(user!=null) {
      String newPassword = generateRandomPassword();
      
      String encodedPassword = passwordEncoder.encode(newPassword);
      user.setUserPass(encodedPassword);
      int result = mapper.updateUserPassword(user);
      
      if(result > 0) {
        emailService.sendNewPasswordEmail(user.getUserEmail(), user.getUserName(), newPassword);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean existsByEmail(String userEmail) {
    return mapper.countByEmail(userEmail) > 0;
  }
  
//@Override
//public void removeMember(String username, String password) {
//  UsernamePasswordAuthenticationToken inputData =
//      UsernamePasswordAuthenticationToken.unauthenticated(username, password);
//  authenticationManager.authenticate(inputData);
//  
//  mapper.updateMemberDelete(username);
//}
//
//@Override
//public void modifyMember(MemberVO member) {
//  UsernamePasswordAuthenticationToken inputData =
//      UsernamePasswordAuthenticationToken.unauthenticated(member.getMemId(), member.getMemPassword());
//  authenticationManager.authenticate(inputData);
//  
//  mapper.updateMember(member);
//  // 기존 인증 객체 변경
//  changeAuthentication(member);
//  
//}

//private void changeAuthentication(MemberVO member) {
//  UsernamePasswordAuthenticationToken inputData = UsernamePasswordAuthenticationToken
//      .unauthenticated(member.getMemId(), member.getMemPassword());
//  
//  SecurityContext context = SecurityContextHolder.getContext();
//  
//  UsernamePasswordAuthenticationToken before =
//      (UsernamePasswordAuthenticationToken) context.getAuthentication();
//  
//  UsernamePasswordAuthenticationToken newAuthentication = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(inputData);
//
//  Object details = before.getDetails();
//  
//  newAuthentication.setDetails(details);
//
//  context.setAuthentication(newAuthentication);
//}

}
