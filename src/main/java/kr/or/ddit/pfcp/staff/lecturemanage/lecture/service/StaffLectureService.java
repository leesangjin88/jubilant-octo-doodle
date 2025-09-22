package kr.or.ddit.pfcp.staff.lecturemanage.lecture.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;

public interface StaffLectureService {

	public List<LectureVO> readLectureList(int offset, int totalPage);
	public int readTotalCount();
	public List<LectureEnrVO> readLectureStudent(String what);
	public int readLectureStudentTotalCount(String what);

}
