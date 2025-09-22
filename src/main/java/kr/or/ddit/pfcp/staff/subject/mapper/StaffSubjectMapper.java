package kr.or.ddit.pfcp.staff.subject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.LectureReqVO;

/**
 * @author seokyungdeok
 * @since 2025. 7. 12.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 12.	|	서경덕	|	최초 생성
 */
@Mapper
public interface StaffSubjectMapper {
	public List<LectureReqVO> selectLectureReqList();
	
	public LectureReqVO selectLectureReqDetail(String reqNo);
	
	public int updateSubjectAccept(String reqNo);
	
	public int insertAcceptLecture(LectureReqVO lectureReq);
	
	public int returnLectureReq(LectureReqVO lectureReq);
}
