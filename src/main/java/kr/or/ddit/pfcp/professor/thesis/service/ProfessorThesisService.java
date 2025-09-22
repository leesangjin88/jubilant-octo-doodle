package kr.or.ddit.pfcp.professor.thesis.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.ThesisVO;


/**
 * @author 김태수
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 9.	|	김태수	|	최초 생성
 */
public interface ProfessorThesisService {
	
	public void createThesis(ThesisVO theiseVO);
	public List<ThesisVO> readThesisList();
	public List<ThesisVO> readThesis(String userNo);
	public void modifyThesis(ThesisVO thesisVO);
	public void removeThesis(String thesisNo);
	public ThesisVO readThesisDetail(String thesisNo);
	
	public void createFileRef(FileRefVO fileRef);
	public int readTotalThesisCnt();
	
	
	
	public List<ThesisVO> readThesisListByPaging(String userNo, int offset, int pageSize);
	public int readTotalThesisCntByPaging(String userNo);
	public List<ThesisVO> readThesisList(int offset, int pageSize);
	public void modifyThesisFileRefNo(String thesisNo, String fileRefNo);

}
