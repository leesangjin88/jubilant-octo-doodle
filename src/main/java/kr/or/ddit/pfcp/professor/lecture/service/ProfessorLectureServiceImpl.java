package kr.or.ddit.pfcp.professor.lecture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.professor.lecture.mapper.ProfessorLectureMapper;
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
@Service
@RequiredArgsConstructor
public class ProfessorLectureServiceImpl implements ProfessorLectureService {

	private final ProfessorLectureMapper professorLectureMapper;
	
	@Override
	public void createLecture(LectureReqVO lectureReqVO) {
		professorLectureMapper.insertLecture(lectureReqVO);
	}

	@Override
	public List<LectureReqVO> readLectureList(int offset, int pageSize) {
		return professorLectureMapper.selectLectureList(offset,pageSize);
	}

	@Override
	public List<LectureReqVO> readLecture(String reqNo) {
		return professorLectureMapper.selectLecture(reqNo);
	}

	@Override
	public int modifyLecture(LectureReqVO lectureReqVO) {
		return professorLectureMapper.updateLecutre(lectureReqVO);
	}

	@Override
	public void removeLecture(String reqNo) {
		professorLectureMapper.deleteLecutre(reqNo);
	}
	
	@Override
	public LectureReqVO readLectureDetail(String reqNo) {
		return professorLectureMapper.selectLectureDetail(reqNo);
	}
	
	@Override
	public void createFileRef(FileRefVO fileRef) {
		professorLectureMapper.insertFileRef(fileRef);
	}
	
	
	
	  @Override
	    public List<LectureReqVO> readLectureListByPaging(String userNo, int offset, int pageSize) {
	        Map<String, Object> params = new HashMap<>();
	        params.put("userNo", userNo);
	        params.put("offset", offset);
	        params.put("pageSize", pageSize);
	        return professorLectureMapper.selectLectureListByPaging(params);
	    }

	    @Override
	    public int readTotalLectureCntByPaging(String userNo) {
	        return professorLectureMapper.selectTotalLectureCntByPaging(userNo);
	    }
	
	
	
	@Override
    public List<String> retrieveLectureCategories() {
        return professorLectureMapper.selectLectureCategories();
    }

    @Override
    public List<Map<String, Object>> retrieveSubjects() { 
        return professorLectureMapper.selectSubjects(); 
    }

    @Override
    public List<String> retrieveClassroomNames() { 
        return professorLectureMapper.selectClassroomNames(); 
    }

    @Override
    public List<LectureVO> retrieveApprovedLectureListByProfNo(String professorNo) {
      return professorLectureMapper.selectSubjectListByProfNo(professorNo);
    }

	@Override
	public int readTotalLectureCnt() {
		return professorLectureMapper.selectLectureTotalCnt();
	}

	@Override
	public List<LectureVO> getApprovedLectures() {
        return professorLectureMapper.selectApprovedLectures();
    }

	

}
