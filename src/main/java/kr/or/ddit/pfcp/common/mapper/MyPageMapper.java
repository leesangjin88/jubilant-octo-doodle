package kr.or.ddit.pfcp.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.UserVO;

/**
 * @author 김태수
 * @since 2025. 7. 12.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 12.	|	김태수	|	최초 생성
 * 2025. 7. 17.	|	양수민	|	수정
 */
@Mapper
public interface MyPageMapper {
	public UserVO selectMyPagePr(String userId);

	public UserVO selectMyPageAD(String userId);

	public UserVO selectMyPageST(String userId);
	
}
