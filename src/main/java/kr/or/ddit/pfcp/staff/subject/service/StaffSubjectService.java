package kr.or.ddit.pfcp.staff.subject.service;

import java.util.List;

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
public interface StaffSubjectService {
	public List<LectureReqVO> readLectureReqList();
	
	public LectureReqVO readLectureReqDetail(String reqNo);
	
	public void modifySubjectAccept(String reqNo);
	
	public void createAcceptLecture(LectureReqVO lectureReq);
	
	public void returnLectureReq(LectureReqVO lectureReq);
}
