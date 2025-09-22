package kr.or.ddit.pfcp.common.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserMapperTest {
  
  @Autowired
  UserMapper mapper;

  @Test
  void testCountByEmail() {
    log.info("select user {}", mapper.selectUser("ST20220810"));
  }

}
