package kr.or.ddit.common.zoomapi.service;

import java.util.List;
import org.springframework.stereotype.Service;
import kr.or.ddit.common.zoomapi.mapper.SessionsMapper;
import kr.or.ddit.common.zoomapi.vo.SessionsVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionsServiceImpl implements SessionsService {
  
  private final SessionsMapper sessionsMapper;

  @Override
  public int createSession(SessionsVO session) {
    return sessionsMapper.insertSession(session);
  }

  @Override
  public int changeStatusSession(SessionsVO session) {
    return sessionsMapper.updateSession(session);
  }

  @Override
  public List<SessionsVO> readSessions(String studentNo) {
    return sessionsMapper.selectSessions(studentNo);
  }

  @Override
  public void readSession(Integer sessionNo) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<SessionsVO> readSessionsByProfNo() {
    return sessionsMapper.selectSessionsByProfNo();
  }

}
