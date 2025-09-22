package kr.or.ddit.pfcp.staff.college.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.GraduationRequestVO;
import kr.or.ddit.pfcp.common.vo.GraduationResultVO;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Mapper
public interface GraduationRequestMapper {
	
	public List<GraduationRequestVO> selectAllGraduationRequestList();

	public GraduationRequestVO selectGraduationRequest(String gradReqNo);
	
	public int updateGraduationRequest(GraduationRequestVO gradReqNo);
	
	public int deleteGraduationRequest(String gradReqNo);
	
	
	
	public int insertGraduationResult(GraduationResultVO gradResultNo);
	
	public int updateGraduationResult(GraduationResultVO gradResultNo);
	
	public int deleteGraduationResult(String gradResultNo);
}
