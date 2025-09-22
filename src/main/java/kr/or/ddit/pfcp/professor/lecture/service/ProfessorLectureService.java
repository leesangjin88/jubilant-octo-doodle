package kr.or.ddit.pfcp.professor.lecture.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;


/**
 * @author 김태수
 * @since 2025. 7. 9.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 9.	|	김태수	|	최초 생성
* 2025.07.09  |     이성화   |   retrieveApprovedLectureListByProfNo 메소드 추가
 */
public interface ProfessorLectureService {
	public void createLecture(LectureReqVO lectureReqVO);
	public List<LectureReqVO> readLectureList(int offset, int pageSize);
	public List<LectureReqVO> readLecture(String userNo);
	public int modifyLecture(LectureReqVO lectureReqVO);
	public void removeLecture(String reqNo);
	public LectureReqVO readLectureDetail(String reqNo);
	public List<LectureVO> getApprovedLectures();
	
	
	
	public List<String> retrieveLectureCategories();
    public List<Map<String, Object>> retrieveSubjects(); 
    public List<String> retrieveClassroomNames();
    public List<LectureVO> retrieveApprovedLectureListByProfNo(String professorNo);
    
    public void createFileRef(FileRefVO fileRef);
    public int readTotalLectureCnt();
    
    
	List<LectureReqVO> readLectureListByPaging(String userNo, int offset, int pageSize);
	public int readTotalLectureCntByPaging(String currentUserNo);
    
}
