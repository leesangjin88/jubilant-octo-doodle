package kr.or.ddit.pfcp.student.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 이성화
 * @since ${date}
 * @see
 * 
 * <<개정이력(Modification Information)>>
 * 수정일      수정자     수정 내용
 * ------------------------------------------
 * ${date}    이성화     최초 생성
 */
@Controller
@RequestMapping("/community/board")
public class StudentCommunityController {
  
  @GetMapping
  public String getCommunityList() {
    return "pfcp/student/community/communityList";
  }
  
  @GetMapping("/boardInsert.do")
  public String communityForm() {
    return "pfcp/student/community/communityForm";
  }
  
  
}
