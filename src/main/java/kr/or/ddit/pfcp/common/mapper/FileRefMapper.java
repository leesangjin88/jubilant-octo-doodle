package kr.or.ddit.pfcp.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.FileRefVO;

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
public interface FileRefMapper {
	public void insertFileRef(FileRefVO fileRef);
	
	public FileRefVO selectFileRef(String fileRefNo);
	
	public int deleteFileRef(String fileRefNo);
	
	public FileRefVO selectProfileImgByUserNo(@Param("userNo") String userNo);

}
