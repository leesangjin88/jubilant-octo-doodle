package kr.or.ddit.pfcp.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.mapper.FileRefMapper;
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
@Service(value = "fileRefService")
public class FileRefServiceImpl implements FileRefService {
	@Autowired
	private FileRefMapper fileRefMapper;

	@Override
	public void createFileRef(FileRefVO fileRef) {
		// TODO Auto-generated method stub
		fileRefMapper.insertFileRef(fileRef);
	}

	@Override
	public FileRefVO readFileRef(String fileRefNo) {
		// TODO Auto-generated method stub
		return fileRefMapper.selectFileRef(fileRefNo);
	}

	@Override
	public int removeFileRef(String fileRefNo) {
		return fileRefMapper.deleteFileRef(fileRefNo);
	}

	@Override
	public FileRefVO readProfileImgByUserNo(String userNo) {
		return fileRefMapper.selectProfileImgByUserNo(userNo);
	}
}
