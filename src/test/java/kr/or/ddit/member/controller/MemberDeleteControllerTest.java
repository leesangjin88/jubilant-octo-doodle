package kr.or.ddit.member.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.or.ddit.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MemberDeleteControllerTest {
  
  @Autowired
  MemberService service;

  @Test
  void testDeleteMember() {
    service.removeMember("d001", "java");
  }

}
