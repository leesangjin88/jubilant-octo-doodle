package kr.or.ddit.mapper;

import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.vo.MemberVO;

/**
 *  회원 관리를 위한 Persistence Layer 
 *  
 *  MyBatis의 Mapper : 데이터베이스를 사용하기 위해 필요한 객체
 *  
 *  1. Mapper interface 정의 ex) MemberMapper
 *  2. Mapper XML 정의 ex) MemberMapper.xml, 네임스페이스로 인터페이스와 연결됨
 *  3. 필요한 쿼리 등록, 인터페이스의 메소드와 한 세트로 동작해야 함
 *     쿼리 id를 메소드 이름으로 설정. 그 외에 parameterType, resultType은 메소드 선언부를 따라감
 *     --> mybatis는 등록한 모든 쿼리로 preparedStatement 객체를 생성하고,
 *         생성된 모든 쿼리 객체는 Map으로 관리함 --> SQL mapper
 *         key : xml의 네임스페이스 "." 쿼리 아이디
 *         ex) kr.or.ddit.mapper.MemberMapper.selectMember
 *  4. persistence layer 구현체에서 SqlSession open -> 실행
 */
@Mapper
public interface MemberMapper {
  public int insertMember(MemberVO member);
  
  /**
   * 한 사람의 회원 정보 조회
   * @param username 조회할 대상 pk
   * @return 조회한 사람의 정보를 가진 VO
   *         해당 회원이 존재하지 않는 경우
   */
  public MemberVO selectMember(String username);
  /**
   * 
   *  OAuth2 로그인 사용자의 정보 조회
   * @param mail
   * @return
   */
  public MemberVO selectMemberByMail(String mail);
  
  public int updateMemberDelete(String username);
  
  public int updateMember(MemberVO member);
  
}
