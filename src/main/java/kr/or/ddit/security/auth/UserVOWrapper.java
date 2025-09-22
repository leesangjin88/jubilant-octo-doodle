package kr.or.ddit.security.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import kr.or.ddit.pfcp.common.vo.UserVO;

public class UserVOWrapper extends User implements RealUserWrapper<UserVO>{

  private final UserVO realUser;
  
  public UserVOWrapper(UserVO realUser) {
    super(
          realUser.getUserNo(),
          realUser.getUserPass(),
          // TODO: 탈퇴처리
          true,
          true,
          true,
          true,
          AuthorityUtils.createAuthorityList(realUser.getUserRole())
        );
    this.realUser = realUser;
  }

  @Override
  public UserVO getRealUser() {
    return realUser;
  }



  
}
