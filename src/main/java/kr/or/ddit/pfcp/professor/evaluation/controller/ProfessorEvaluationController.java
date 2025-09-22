package kr.or.ddit.pfcp.professor.evaluation.controller;

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
@RequestMapping("/professor/evaluation")
public class ProfessorEvaluationController {

	/**
	 * 강의 평가 조회
	 * @return
	 */
	@GetMapping("evaluationtList.do")
	public String evaluationtList() {
		return "pfcp/professor/evaluation/evaluationtList";
	}
	
	/**
	 * 강의 평가 등록
	 * @return
	 */
	@GetMapping("evaluationtInsert.do")
	public String evaluationtInsert() {
		return "pfcp/professor/evaluation/evaluationtForm";
	}
	
	/**
	 * 강의 평가 수정
	 * @return
	 */
	@GetMapping("evaluationtUpdate.do")
	public String evaluationtUpdate() {
		return "pfcp/professor/evaluation/evaluationtUpdateForm";
	}
	
	/**
	 * 강의 평가 삭제
	 * @return
	 */
	@GetMapping("evaluationtDelete.do")
	public String evaluationtDelete() {
		return "redirect:/professor/evaluation/evaluationtDelete.do";
	}
	
}
