package kr.or.ddit.pfcp.professor.lecture.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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
 */
@Mapper
public interface ProfessorLectureMapper {
	
	/**
	 * 신규 개설 강의 신청(등록)
	 * 
	 * @param lecture
	 * @return
	 */
	public int insertLecture(LectureReqVO lectureReqVO);
	
	/**
	 * 강의 신청 리스트 조회
	 * @return
	 */
	public List<LectureReqVO> selectLectureList(int offset, int pageSize);
	
	
	/**
	 * 강의 신청 리스트 카운트
	 * @return
	 */
	public int selectLectureTotalCnt();
	
	/**
	 * 강의 신청 상세 조회
	 * @param userNo
	 * @return
	 */
	public List<LectureReqVO> selectLecture(String reqNo);
	
	/**
	 * 강의 신청 수정
	 * @param lecture
	 * @return
	 */
	public int updateLecutre(LectureReqVO lectureReqVO);
	
	/**
	 * 강의 신청 취소(삭제)
	 * @param userNo
	 * @return
	 */
	public int deleteLecutre(String reqNo);
	
	
	/**
     * 강의 분류 목록 조회 (LECTURE_REQ.LEC_CATEGORY)
     * @return LECTURE_REQ 테이블의 모든 중복 없는 LEC_CATEGORY 값 목록
     */
    public List<String> selectLectureCategories();

    /**
     * 강의 과목 목록 조회 
     * @return
     */
    List<Map<String, Object>> selectSubjects();

    /**
     * 희망 강의실 목록 조회 (LECTURE_ROOM.LR_NAME)
     * @return LECTURE_ROOM 테이블의 모든 중복 없는 LR_NAME 값 목록
     */
    public List<String> selectClassroomNames(); 
	
    public List<LectureVO> selectSubjectListByProfNo(String professorNo);
    
    /**
     * 상세조회
     * @param reqNo
     * @return
     */
    public LectureReqVO selectLectureDetail(String reqNo);
    public int insertFileRef(FileRefVO fileRef);

	public List<LectureReqVO> selectLectureListByPaging(Map<String, Object> params);

	public int selectTotalLectureCntByPaging(String userNo);
	public List<LectureVO> selectApprovedLectures();
}
