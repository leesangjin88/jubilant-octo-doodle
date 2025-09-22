package kr.or.ddit.pfcp.professor.thesis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface ProfessorThesisMapper {
	
	public void insertThesis(ThesisVO theiseVO);
	public List<ThesisVO> selectThesisList();
	public List<ThesisVO> selectThesis(String userNo);
	public int updateThesis(ThesisVO thesisVO);
	public int deleteThesis(String thesisNo);
	public int insertFileRef(FileRefVO fileRef);
	
	public int selectTotalThesisCnt();
	public ThesisVO selectThesisDetail(String thesisNo);
	

    public List<ThesisVO> selectThesisListByPaging(Map<String, Object> params);
    public int selectTotalThesisCntByPaging(@Param("userNo") String userNo);
    public List<ThesisVO> selectThesisListWithPaging(Map<String, Object> params);
    public int updateThesisFileRefNo(Map<String, Object> params);
}
