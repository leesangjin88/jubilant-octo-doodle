package kr.or.ddit.pfcp.staff.program.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.vo.ProgramEnrollVO;
import kr.or.ddit.pfcp.common.vo.ProgramVO;
import kr.or.ddit.pfcp.common.vo.TypeVO;
import kr.or.ddit.pfcp.staff.program.mapper.StaffProgramMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffProgramServiceImpl implements StaffProgramService {
	
	private final StaffProgramMapper mapper;
	
	
	@Override
	public List<ProgramVO> readProgramList() {
		return mapper.selectProgramList();
	}


	@Override
	public ProgramVO readProgram(String programNo) {
		return mapper.selectProgramById(programNo);
	}


	@Override
	public List<TypeVO> readProgramType() {
		return mapper.selectProgramType();
	}


	@Override
	public int saveProgram(ProgramVO program) {
		if(program.getProgramNo()==null || program.getProgramNo().isBlank()) {
			return mapper.insertProgram(program);
		}else {
			return mapper.updateProgram(program);
		}
	}


	@Override
	public int deleteProgram(String programNo) {
		return mapper.deleteProgram(programNo);
	}


	@Override
	public List<ProgramVO> readOpenProgramList() {
		return mapper.selectOpenProgramList();
	}


	@Override
	public ProgramVO readProgramWithEnroll(String programNo) {
		return mapper.selectProgramWithEnroll(programNo);
	}


	@Override
	public int updateEnrollAttended(ProgramEnrollVO enrollVO) {
		return mapper.updateAttended(enrollVO);
	}


	@Override
	public int updateEnrollCompletion(ProgramEnrollVO enrollVO) {
		return mapper.updateCompletion(enrollVO);
	}


	@Override
	public int updateEnrollCertIssued(String enrollNo) {
		return mapper.updateIsCertIssued(enrollNo);
	}

}
