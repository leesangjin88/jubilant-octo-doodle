package kr.or.ddit.pfcp.staff.tuition.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.pfcp.common.vo.CollegeVO;
import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.staff.tuition.service.StaffTuitionService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author YSM
 * @since 250717
 * 
 * << 개정이력(Modification Information) >>
 * 수정일	|	수정자	|	수정 내용
 * -----------------------------------------------
 * 250717	|	양수민	|	최초 생성
 *
 */
@Slf4j
@Controller
@RequestMapping("/staff/tuition")
public class StaffTuitionController {
	
	
	@Autowired
	private StaffTuitionService staffTuitionService;
	
	/**
	 * 단과대학 목록 조회
	 * @return
	 */
	@GetMapping("tuitionList.do")
	public String tuitionListUI(
		Model model
	) {
		
		List<CollegeVO> collegeList = staffTuitionService.readCollegeList();
		model.addAttribute("collegeList", collegeList);
		return "pfcp/staff/tuition/tuitionList";
	}
	
	
	@GetMapping("departmentList.do")
	@ResponseBody
	public List<Map<String, String>> getCollegeDetail(@RequestParam("no") String collegeNo) {
	    List<DepartmentVO> deptList = staffTuitionService.readDepartmentsByCollegeNo(collegeNo);

	    List<Map<String, String>> result = new ArrayList<>();
	    for (DepartmentVO dept : deptList) {
	        Map<String, String> map = new HashMap<>();
	        map.put("departmentName", dept.getDepartmentName());
	        Integer tuition = dept.getDepartmentTuition();
	        map.put("tuition", tuition != null ? tuition.toString() : "정보 없음");
	        result.add(map);
	    }

	    return result;
	}


	
	/**
	 * 등록금 폼 등록
	 * @return
	 */
	@GetMapping("tuitionInsert.do")
	public String tuitionInsertUI() {
		return "pfcp/staff/tuition/tuitionInsert";
	}
	
	/**
	 * 등록금 폼 수정
	 * @return
	 */
	@GetMapping("tuitionUpdate.do")
	public String tuitionUpdateUI() {
		return "pfcp/staff/tuition/tuitionDetail";
	}
}
