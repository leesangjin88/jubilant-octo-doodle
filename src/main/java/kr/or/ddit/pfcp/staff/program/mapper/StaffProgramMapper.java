package kr.or.ddit.pfcp.staff.program.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.ProgramEnrollVO;
import kr.or.ddit.pfcp.common.vo.ProgramVO;
import kr.or.ddit.pfcp.common.vo.TypeVO;

@Mapper
public interface StaffProgramMapper {
	
	public List<ProgramVO> selectProgramList();
	
	public List<ProgramVO> selectOpenProgramList();
	
	public ProgramVO selectProgramById(String programNo);
	
	public List<TypeVO> selectProgramType();
	
	public int insertProgram(ProgramVO program);
	
	public int updateProgram(ProgramVO program);
	
	public int deleteProgram(String programNo);
	
	public ProgramVO selectProgramWithEnroll(String programNo);

	public int updateAttended(ProgramEnrollVO enrollVo);
	
	public int updateCompletion(ProgramEnrollVO enrollVo);
	
	public int updateIsCertIssued(String enrollNo);
}
