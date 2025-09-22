package kr.or.ddit.member.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import kr.or.ddit.common.exception.PKDuplicatedException;
import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  
  private final MemberMapper mapper;
  
  private final PasswordEncoder passwordEncoder;
  
  private final AuthenticationManager authenticationManager;

  @Override
  public MemberVO readMember(String username) {
    return mapper.selectMember(username);
  }

  @Override
  public void createMember(MemberVO member) {
    if(mapper.selectMember(member.getMemId()) == null) {
      String encodedPassword = passwordEncoder.encode(member.getMemPassword());
      member.setMemPassword(encodedPassword);
      mapper.insertMember(member);
    } else {
      throw new PKDuplicatedException(member.getMemId());
    }
  }

  @Override
  public void removeMember(String username, String password) {
    UsernamePasswordAuthenticationToken inputData =
        UsernamePasswordAuthenticationToken.unauthenticated(username, password);
    authenticationManager.authenticate(inputData);
    
    mapper.updateMemberDelete(username);
  }

  @Override
  public void modifyMember(MemberVO member) {
    UsernamePasswordAuthenticationToken inputData =
        UsernamePasswordAuthenticationToken.unauthenticated(member.getMemId(), member.getMemPassword());
    authenticationManager.authenticate(inputData);
    
    mapper.updateMember(member);
    // 기존 인증 객체 변경
    changeAuthentication(member);
    
  }

  private void changeAuthentication(MemberVO member) {
    UsernamePasswordAuthenticationToken inputData = UsernamePasswordAuthenticationToken
        .unauthenticated(member.getMemId(), member.getMemPassword());
    
    SecurityContext context = SecurityContextHolder.getContext();
    
    UsernamePasswordAuthenticationToken before =
        (UsernamePasswordAuthenticationToken) context.getAuthentication();
    
    UsernamePasswordAuthenticationToken newAuthentication = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(inputData);

    Object details = before.getDetails();
    
    newAuthentication.setDetails(details);

    context.setAuthentication(newAuthentication);
  }

}
