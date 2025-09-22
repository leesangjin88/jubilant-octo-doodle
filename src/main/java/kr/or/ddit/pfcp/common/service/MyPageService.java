package kr.or.ddit.pfcp.common.service;

import kr.or.ddit.pfcp.common.vo.UserVO;

/**
 * @author 김태수
 * @since 2025. 7. 12.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 12.	|	김태수	|	최초 생성
 */
public interface MyPageService {
	public UserVO readMyPagePr(String username);

	public UserVO readMyPageAD(String userId);

	public UserVO readMyPageST(String userId);
}
