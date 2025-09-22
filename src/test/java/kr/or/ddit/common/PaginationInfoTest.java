package kr.or.ddit.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaginationInfoTest {
  
  @Test
  void test() {
    PaginationInfo paging = new PaginationInfo();
    paging.setTotalRecordCount(102);
    assertEquals(11, paging.getTotalPageCount());
    
    paging.setCurrentPageNo(3);
    assertEquals(1, paging.getFirstPageNoOnPageList());
    assertEquals(5, paging.getLastPageNoOnPageList());
    
    paging.setCurrentPageNo(11);
    assertEquals(11, paging.getFirstPageNoOnPageList());
    assertEquals(11, paging.getLastPageNoOnPageList());
  }
  
}
