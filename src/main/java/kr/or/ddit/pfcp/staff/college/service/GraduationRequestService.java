package kr.or.ddit.pfcp.staff.college.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.GraduationRequestVO;

public interface GraduationRequestService {
	public List<GraduationRequestVO> readGraduationRequestList();
	public GraduationRequestVO readGraduationRequest(String gradReqNo);
	
//	public void createGraduationRequest(GraduationRequestVO gradReq);
	public void modifyGraduationRequest(GraduationRequestVO gradReq);
	public void removeGraduationRequest(String gradReqNo);
}
