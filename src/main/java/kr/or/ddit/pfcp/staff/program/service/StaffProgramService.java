package kr.or.ddit.pfcp.staff.program.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.ProgramEnrollVO;
import kr.or.ddit.pfcp.common.vo.ProgramVO;
import kr.or.ddit.pfcp.common.vo.TypeVO;

public interface StaffProgramService {

	public List<ProgramVO> readProgramList();
	
	public List<ProgramVO> readOpenProgramList();
	
	public ProgramVO readProgram(String programNo);
	
	public List<TypeVO> readProgramType();
	
	public int saveProgram(ProgramVO program);
	
	public int deleteProgram(String programNo);
	
	public ProgramVO readProgramWithEnroll(String programNo);
	
	public int updateEnrollAttended(ProgramEnrollVO enrollVO);
	
	public int updateEnrollCompletion(ProgramEnrollVO enrollVO);
	
	public int updateEnrollCertIssued(String enrollNo);
}
