package kr.or.ddit.pfcp.common.service;

import kr.or.ddit.pfcp.common.vo.UserDataVO;
import kr.or.ddit.pfcp.common.vo.UserVO;
import kr.or.ddit.vo.MemberVO;

public interface UserService {
//  public void createMember(MemberVO member);
//  public readMemberList()
  public UserVO readMember(String username);
  
  public String findUserName(UserDataVO userdata);
  public boolean modifyUserPassword(String email);
  public boolean existsByEmail(String userEmail);
//  public void removeMember(String username, String password);
  
}
