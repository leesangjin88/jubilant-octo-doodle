package kr.or.ddit.pfcp.student.facility.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import kr.or.ddit.pfcp.common.vo.FacilityVO;
import kr.or.ddit.pfcp.student.facility.service.StudentFacilityService;

@Controller
@RequestMapping("/student/facility")
public class StudentFacilityController {
  
  @Autowired
  StudentFacilityService studentFacilityService;
  
  static final String MODELNAME = "facility";
  
  @ModelAttribute(MODELNAME)
  public FacilityVO facility() {
    return new FacilityVO();
  }
  
  /**
   * 시설물 목록 전체 조회
   * @return
   */
  @GetMapping("studentFacilityList.do")
  public String facilityListUI(Model model) {
      List<FacilityVO> facility = studentFacilityService.readFacilityList();
      model.addAttribute("facility", facility);
      return "pfcp/student/facility/studentFacilityList";
  }

  /**
   * 시설물 상세 조회
   * @return
   */
  @GetMapping("studentFacilityDetail.do")
  public String facilityDetailUI(
      @RequestParam String what,
      Model model
  ) {
      FacilityVO facility = studentFacilityService.readFacility(what);
      model.addAttribute("facility", facility);
      return "pfcp/student/facility/studentFacilityDetail";
  }
  
}
