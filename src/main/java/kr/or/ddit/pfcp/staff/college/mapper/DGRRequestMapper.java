package kr.or.ddit.pfcp.staff.college.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.DGRRequestVO;

/**
 * @author KGM
 * @since 250701
 * @see
 */
@Mapper
public interface DGRRequestMapper {
	
	public List<DGRRequestVO> selectAllDGRRequestList();

	public DGRRequestVO selectDGRRequest(String dgrNo);

	public int insertDGRRequest(DGRRequestVO dgr);

	public int updateDGRRequest(DGRRequestVO dgr);

	public int deleteDGRRequest(String dgrNo);
}
