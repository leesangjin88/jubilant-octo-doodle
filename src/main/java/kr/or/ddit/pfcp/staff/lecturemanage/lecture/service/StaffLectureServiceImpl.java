package kr.or.ddit.pfcp.staff.lecturemanage.lecture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.staff.lecturemanage.lecture.mapper.StaffLectureMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffLectureServiceImpl implements StaffLectureService {

	private final StaffLectureMapper mapper;
	
	@Override
	public List<LectureVO> readLectureList(int offset, int totalPage) {
		return mapper.selectLectureList(offset, totalPage);
	}

	@Override
	public int readTotalCount() {
		return mapper.selectTotalCount();
	}

	@Override
	public List<LectureEnrVO> readLectureStudent(String what) {
		return mapper.selectLectureStudent(what);
	}

	@Override
	public int readLectureStudentTotalCount(String what) {
		return mapper.selectLectureStudentTotalCount(what);
	}

}
