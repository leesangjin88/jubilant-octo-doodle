package kr.or.ddit.common.zoomapi.service;

import java.util.List;
import kr.or.ddit.common.zoomapi.vo.SessionsVO;

public interface SessionsService {
  public int createSession(SessionsVO session);
  public int changeStatusSession(SessionsVO session);
  public List<SessionsVO> readSessions(String studentNo);
  public List<SessionsVO> readSessionsByProfNo();
  public void readSession(Integer sessionNo);
}
