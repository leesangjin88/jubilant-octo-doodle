package kr.or.ddit.pfcp.common.service;

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
public interface AtchFileService {
	public void createAtchFile(AtchFileVO atchFile);
	
	public AtchFileVO readAtchFile(String atchId);
}
