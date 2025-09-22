package kr.or.ddit.common.zoomapi.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.common.zoomapi.vo.SessionsVO;

@Mapper
public interface SessionsMapper {
  public int insertSession(SessionsVO session);
  public List<SessionsVO> selectSessions(String studentNo);
  public SessionsVO selectSession(Integer sessionNo);
  public int updateSession(SessionsVO session);
  public List<SessionsVO> selectSessionsByProfNo();
}
