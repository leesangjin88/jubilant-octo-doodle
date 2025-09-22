package kr.or.ddit.pfcp.staff.grademanage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.staff.grademanage.mapper.GrademanageMapper;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author YSM
 * @since 2025. 7. 15.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 15.	|	양수민	|	최초 생성
 */

@Service
@RequiredArgsConstructor
public class GrademanageServiceImpl implements GrademanageService{
	
	private final GrademanageMapper mapper;

	@Override
	public List<LectureEnrVO> readLectureStudent(String what, int offset, int pageSize) {
		return mapper.selectLectureStudent(what, offset, pageSize);
	}

	@Override
	public void modifyGradeIfChanged(String lecNo, String userNo, Integer midtermScore, Integer finalScore,
			Integer assignmentScore) {
		mapper.updateGradeIfChanged(lecNo, userNo, midtermScore, finalScore, assignmentScore);
		
	}


}
