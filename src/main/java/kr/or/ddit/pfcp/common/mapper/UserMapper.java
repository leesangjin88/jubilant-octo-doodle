package kr.or.ddit.pfcp.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.pfcp.common.vo.UserDataVO;
import kr.or.ddit.pfcp.common.vo.UserVO;

@Mapper
public interface UserMapper {
  public UserVO selectUser(String userNo);
 
  public String findUserId(UserDataVO userdata);
  
  public int countByEmail(String email);
  
  public int updateUserPassword(UserVO user); 
  
  public UserVO findUserEmail(String email);
}
