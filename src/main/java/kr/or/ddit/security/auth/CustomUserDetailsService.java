package kr.or.ddit.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import kr.or.ddit.mapper.MemberMapper;
import kr.or.ddit.pfcp.common.mapper.UserMapper;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.vo.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
  
  private final UserMapper mapper;
  
  @Override
  public UserDetails loadUserByUsername(String username) 
      throws UsernameNotFoundException {
    UserVO realUser = mapper.selectUser(username);
    
    if(realUser==null) {
      throw new UsernameNotFoundException(String.format("%s 회원 없음", username));
    }
    
    return new UserVOWrapper(realUser);
  }
  
}
