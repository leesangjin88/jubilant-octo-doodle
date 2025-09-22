package kr.or.ddit.common.zoomapi.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SessionsMapperTest {
  
  @Autowired
  private SessionsMapper sessionsMapper;

  @Test
  void testSelectSessions() {
//    log.info("{}", sessionsMapper.selectSessions());
  }

}
