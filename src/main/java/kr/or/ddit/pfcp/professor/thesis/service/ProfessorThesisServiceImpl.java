package kr.or.ddit.pfcp.professor.thesis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.ThesisVO;
import kr.or.ddit.pfcp.professor.thesis.mapper.ProfessorThesisMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author 김태수
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 9.	|	김태수	|	최초 생성
 */
@Service(value = "professorThesisService")
@RequiredArgsConstructor
public class ProfessorThesisServiceImpl implements ProfessorThesisService {
	
	private final ProfessorThesisMapper professorThesisMapper;
	
	@Override
	public void createThesis(ThesisVO theiseVO) {
		professorThesisMapper.insertThesis(theiseVO);
	}

	@Override
	public List<ThesisVO> readThesisList() {
		return professorThesisMapper.selectThesisList();
	}

	@Override
	public List<ThesisVO> readThesis(String userNo) {
		return professorThesisMapper.selectThesis(userNo);
	}
	
	@Override
	public ThesisVO readThesisDetail(String thesisNo) {
		return professorThesisMapper.selectThesisDetail(thesisNo);
	}

	@Override
	public void modifyThesis(ThesisVO thesisVO) {
		professorThesisMapper.updateThesis(thesisVO);
	}

	@Override
	public void removeThesis(String thesisNo) {
		professorThesisMapper.deleteThesis(thesisNo);
	}

	@Override
	public void createFileRef(FileRefVO fileRef) {
		professorThesisMapper.insertFileRef(fileRef);
	}

	@Override
	public int readTotalThesisCnt() {
		return professorThesisMapper.selectTotalThesisCnt();
	}

	@Override
    public List<ThesisVO> readThesisListByPaging(String userNo, int offset, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("userNo", userNo);
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return professorThesisMapper.selectThesisListByPaging(params);
    }

    @Override
    public int readTotalThesisCntByPaging(String userNo) {
        return professorThesisMapper.selectTotalThesisCntByPaging(userNo);
    }

    @Override
    public List<ThesisVO> readThesisList(int offset, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return professorThesisMapper.selectThesisListWithPaging(params);
    }

    @Transactional
    @Override
    public void modifyThesisFileRefNo(String thesisNo, String fileRefNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("thesisNo", thesisNo);
        params.put("fileRefNo", fileRefNo);
        professorThesisMapper.updateThesisFileRefNo(params);
    }

	

}
