package kr.or.ddit.pfcp.student.eclass.assignement.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.or.ddit.pfcp.common.vo.AssignmentSubmissionVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class AssignSubmissionMapperTest {
  
  @Autowired
  AssignSubmissionMapper assignSubmissionMapper;

//  @Test
//  void testInsertAssignSubmission() {
//    AssignmentSubmissionVO assignmentSubmissionVO = new AssignmentSubmissionVO();
//    
//    assignmentSubmissionVO.setStudentNo("ST20220810");
//    assignmentSubmissionVO.setAssignNo("ASG001");
//    assignmentSubmissionVO.setFileRefNo("FR1752208606517");
//    
//    log.info("{}", assignSubmissionMapper.insertAssignSubmission(assignmentSubmissionVO));
//  }
  
  @Test
  void testAssignSubmission() {
    log.info("select {}", assignSubmissionMapper.selectAssignmentSubmission("ASG001", "ST20220810"));
  }

}
