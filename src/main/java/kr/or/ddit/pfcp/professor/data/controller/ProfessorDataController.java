package kr.or.ddit.pfcp.professor.data.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.pfcp.common.vo.LectureEvalVO;
import kr.or.ddit.pfcp.professor.data.service.ProfessorLectureEvalDataService;

/**
*
* @author 김태수
* @since 2025.07.01
* @see
* 수정일		 |	수정자	|	수정 내용
* -----------|----------|--------------------------
* 2025.07.01 | 	김태수   	|   최초 작성
* 
*/
@Controller
@RequestMapping("/professor/dataManagement")
public class ProfessorDataController {
	
	private final ProfessorLectureEvalDataService professorLectureEvalDataService;
	
	@Autowired
	public ProfessorDataController(ProfessorLectureEvalDataService service) {
		this.professorLectureEvalDataService = service;
	}
	
	 @GetMapping("data.do")
	    public String dataMain(
	    		Model model
	    		,Principal principal
	    	) {
	        
	        if (principal != null) {
	            String userNo = principal.getName();
	            model.addAttribute("userNo", userNo);
	        }
	        
	        return "pfcp/professor/data/dataMain";
	    }
	
	/**
	 * 강의 및 학생 데이터 조회
	 * @return
	 */
	@GetMapping("lectureStudentData.do")
	public String lectureStudentData() {
		return "pfcp/professor/data/lectureStudentData";
	}
	
	/**
	 * 강의평가 - 강의 평가 점수 분포
	 * @return
	 */
	@GetMapping("lectureEvaluation.do")
	public String lectureEvaluation(
			 @RequestParam(value = "no", required = false) String userNo
			 ,RedirectAttributes ra
			,Model model) {
		List<LectureEvalVO> evalData;
		if(userNo == null || userNo.isEmpty()) {
			ra.addFlashAttribute("message", "교수 번호가 정상적으로 확인되지 않습니다.");
			return  "redirect:/professor/dataManagement/data.do";
		}
		
		evalData = professorLectureEvalDataService.readLectureEvalData(userNo);
		
		if (evalData == null || evalData.isEmpty()) {
            ra.addFlashAttribute("message", "해당 교수의 강의 평가 데이터가 없습니다."); 
            return "redirect:/professor/dataManagement/data.do"; 
        }
		
		model.addAttribute("evalData",evalData);
		return "pfcp/professor/data/lectureEvaluation";
	}
	
	
	/**
	 * 학생 출석
	 * @return
	 */
	@GetMapping("attendanceRateChart.do")
	public String attendanceRateChart() {
		return "pfcp/professor/data/attendanceRateChart";
	}
	
	
	/**
	 * 비교과 데이터 - 역량기반 이수 현황 
	 * @return
	 */
	@GetMapping("extracurricular.do")
	public String extracurricular() {
		return "pfcp/professor/data/extracurricular";
	}
	
	/**
	 * 과목 정보 데이터 / 필수 과목 데이터 / 선택 과목 데이터 / 과목별 차트
	 * @return
	 */
	@GetMapping("requiredSubjectData.do")
	public String requiredSubjectData() {
		return "pfcp/professor/data/requiredSubjectData";
	}
	
	/**
	 * 학생별 역량 발전 추이
	 * @return
	 */
	@GetMapping("evaluationAnalysis.do")
	public String evaluationAnalysis() {
		return "pfcp/professor/data/evaluationAnalysis";
	}
	
	
}
