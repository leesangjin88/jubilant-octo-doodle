package kr.or.ddit.pfcp.professor.extracurricular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*
* @author 김태수
* @since 2025.07.01
* @see
﻿ * << 개정이력(Modification Information) >>
* 수정일		  |		수정자	|	수정 내용
* -----------|-------------|--------------------------
* 2025.07.01 | 	김태수   	|   최초 작성
* 
*/
@Controller
@RequestMapping("/professor/extracurricular")
public class ProfessorExtraCurricularController {
	
	
	/**
	 * 비교과 프로그램 조회 - 전체 비교과 프로그램 목록
	 * @return
	 */
	@GetMapping("extracurricularList.do")
	public String extracurricularList() {
		return "pfcp/professor/extracurricular/extracurricularList";
	}
	
	/**
	 * 프로그램 개설 신청 관리 - 개설 신청 조회
	 * @return
	 */
	@GetMapping("afoList.do")
	public String afoList() {
		return "pfcp/professor/extracurricular/afoList";
	}
	
	/**
	 * 개설 신정 프로그램 상세 조회
	 * @return
	 */
	@GetMapping("afoDetail.do")
	public String afoDetail() {
		return "pfcp/professor/extracurricular/afoDetail";
	}
	
	/**
	 * 개설 신청 프로그램 수정
	 * @return
	 */
	@GetMapping("afoUpdate.do")
	public String afoUpdate() {
		return "pfcp/professor/extracurricular/afoUpdate";
	}
	
	/**
	 * 개설 신청 프로그램 삭제
	 * @return
	 */
	@GetMapping("afoDelete.do")
	public String afoDelete() {
		return "redirect:/professor/extracurricular/afoList.do";
	}
	
	/**
	 * 비교과 프로그램 학생 조회
	 * @return
	 */
	@GetMapping("studentList.do")
	public String studentList() {
		return "pfcp/professor/extracurricular/studentList";
	}
	
	/**
	 * 비교과 프로그램 학생 상세 조회
	 * @return
	 */
	@GetMapping("studentDetail.do")
	public String studentDetail() {
		return "pfcp/professor/extracurricular/studentDetail";
	}
	
	/**
	 * 비교과 프로그램 학생 출석 조회
	 * @return
	 */
	@GetMapping("attendanceList.do")
	public String attendanceList() {
		return "pfcp/professor/extracurricular/attendanceList";
	}
	
	/**
	 * 비교과 프로그램 학생 출석 등록
	 * @return
	 */
	@GetMapping("attendanceInsert.do")
	public String attendanceInsert() {
		return "pfcp/professor/extracurricular/attendanceInsert";
	}
	
	/**
	 * 비교과 프로그램 학생 출석 수정
	 * @return
	 */
	@GetMapping("attendanceUpdate.do")
	public String attendanceUpdate() {
		return "pfcp/professor/extracurricular/attendanceUpdate";
	}
	
	/**
	 * 데이터 관리 - 비교과 프로그램 성과 차트
	 * @return
	 */
	@GetMapping("statisticsData.do")
	public String statisticsData() {
		return "redirect:/professor/extracurricular/statisticsData";
	}
	
	/**
	 * 보고서 관리 - 성과 보고서 조회
	 * @return
	 */
	@GetMapping("reportList.do")
	public String reportList() {
		return "redirect:/professor/extracurricular/reportList";
	}
	
	/**
	 * 보고서 등록
	 * @return
	 */
	@GetMapping("reportInsert.do")
	public String reportInsert() {
		return "redirect:/professor/extracurricular/reportInsert";
	}
	
	/**
	 * 보고서 상세 조회
	 * @return
	 */
	@GetMapping("reportDetail.do")
	public String reportDetail() {
		return "redirect:/professor/extracurricular/reportDetail";
	}
	
	/**
	 * 보고서 수정
	 * @return
	 */
	@GetMapping("reportUpdate.do")
	public String reportUpdate() {
		return "redirect:/professor/extracurricular/reportUpdate";
	}
	
	/**
	 * 이수 관리 - 이수 여부 조회
	 * @return
	 */
	@GetMapping("completionList.do")
	public String completionList() {
		return "redirect:/professor/extracurricular/completionList";
	}
	
	/**
	 * 이수 여부 상세조회
	 * @return
	 */
	@GetMapping("completionDetail.do")
	public String completionDetail() {
		return "redirect:/professor/extracurricular/completionDetail";
	}
	
	/**
	 * 이수 여부 등록
	 * @return
	 */
	@GetMapping("completionInsert.do")
	public String completionInsert() {
		return "redirect:/professor/extracurricular/completionInsert";
	}
	
	/**
	 * 이수 여부 수정
	 * @return
	 */
	@GetMapping("completionUpdate.do")
	public String completionUpdate() {
		return "redirect:/professor/extracurricular/completionUpdate";
	}
	
	/**
	 * 이수 여부 삭제
	 * @return
	 */
	@GetMapping("completionDelete.do")
	public String completionDelete() {
		return "redirect:/professor/extracurricular/completionDelete";
	}
	
	
}
