package kr.or.ddit.pfcp.staff.college.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CollegeControllerTest {
  
  @Autowired
  MockMvc mockMvc;

  @Test
  void testCollegeList() throws Exception {
    
    mockMvc.perform(get("/staff/college/collegeList.do"))
           .andExpect(model().attributeExists("college"))
           .andExpect(view().name("pfcp/staff/college/collegeList"))
           .andDo(log());
  }

}
