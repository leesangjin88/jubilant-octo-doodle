package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.DGRRequestVO;

/**
 * @author KGM
 * @since 250702
 * @see
 */
public interface DGRRequestService {
	public List<DGRRequestVO> readDGRRequestList();
	public DGRRequestVO readDGRRequest(String departmentNo);
	
	public void createDGRRequest(DGRRequestVO dgr);
	public void modifyDGRRequest(DGRRequestVO dgr);
	public void removeDGRRequest(String dgrNo);
}
