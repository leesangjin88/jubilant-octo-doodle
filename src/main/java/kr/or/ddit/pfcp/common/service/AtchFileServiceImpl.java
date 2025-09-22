package kr.or.ddit.pfcp.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.mapper.AtchFileMapper;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 8.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 8.	|	서경덕	|	최초 생성
 */
@Service(value = "atchFileService")
public class AtchFileServiceImpl implements AtchFileService {
	@Autowired
	private AtchFileMapper atchFileMapper;
	
	@Override
	public void createAtchFile(AtchFileVO atchFile) {
		// TODO Auto-generated method stub
		atchFileMapper.insertAtchFile(atchFile);
	}

	@Override
	public AtchFileVO readAtchFile(String atchId) {
		// TODO Auto-generated method stub
		return atchFileMapper.selectAtchFile(atchId);
	}

}
