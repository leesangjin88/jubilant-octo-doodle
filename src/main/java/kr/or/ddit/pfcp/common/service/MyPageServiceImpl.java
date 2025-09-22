package kr.or.ddit.pfcp.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.mapper.FileRefMapper;
import kr.or.ddit.pfcp.common.mapper.MyPageMapper;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
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
@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageMapper myPageMapper;
	
	@Autowired
	private FileRefMapper fileRefMapper;

	@Override
	public UserVO readMyPagePr(String userId) {
	    UserVO user = myPageMapper.selectMyPagePr(userId);

	    FileRefVO fileRefNo = fileRefMapper.selectProfileImgByUserNo(user.getUserNo());
	    if(fileRefNo != null && fileRefNo.getFileRefNo() != null) {
	        user.setFileRefNo(fileRefNo.getFileRefNo());
	    } else {
	        user.setFileRefNo(null);  // 혹은 기본값 처리
	    }

	    return user;
	}

	@Override
	public UserVO readMyPageAD(String userId) {
	    UserVO user = myPageMapper.selectMyPageAD(userId);

	    FileRefVO fileRefNo = fileRefMapper.selectProfileImgByUserNo(user.getUserNo());
	    if(fileRefNo != null && fileRefNo.getFileRefNo() != null) {
	        user.setFileRefNo(fileRefNo.getFileRefNo());
	    } else {
	        user.setFileRefNo(null);
	    }

	    return user;
	}

	@Override
	public UserVO readMyPageST(String userId) {
	    UserVO user = myPageMapper.selectMyPageST(userId);

	    FileRefVO fileRefNo = fileRefMapper.selectProfileImgByUserNo(user.getUserNo());
	    if(fileRefNo != null && fileRefNo.getFileRefNo() != null) {
	        user.setFileRefNo(fileRefNo.getFileRefNo());
	    } else {
	        user.setFileRefNo(null);
	    }

	    return user;
	}

	
}
