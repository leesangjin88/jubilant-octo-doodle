package kr.or.ddit.pfcp.common.mapper;

import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface AtchFileMapper {
	public void insertAtchFile(AtchFileVO atchFile);
	
	public AtchFileVO selectAtchFile(String atchId);
}
