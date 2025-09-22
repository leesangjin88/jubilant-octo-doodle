package kr.or.ddit.pfcp.professor.subject.controller;

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
@RequestMapping("/professor/lecture")
public class ProfessorSubjectController {
	
	/**
	 * 교과목 목록 조회 (List)
	 * 
	 * @return
	 */
	@GetMapping("subjectList.do")
	public String subjectList() {
		return "pfcp/professor/subject/subjectList";
	}
	
	/**
	 * 교과목 상세 조회 (Detail)
	 * 
	 * @return
	 */
	@GetMapping("subjectDetail.do")
	public String subjectDetail() {
		return "pfcp/professor/subject/subjectDetail";
	}
	
	/**
	 * 교과목 등록
	 * 
	 * @return
	 */
	@GetMapping("subjectInsert.do")
	public String subjectFormUI() {
		return "pfcp/professor/subject/subjectForm";
	}
	
	/**
	 * 교과목 수정
	 * 
	 * @return
	 */
	@GetMapping("subjectUpdate.do")
	public String subjectUpdate() {
		return "pfcp/professor/subject/subjectUpdateForm";
	}
	
	
	/**
	 * 교과목 삭제
	 * 
	 * @return
	 */
	@GetMapping("subjectDelete.do")
	public String subjectDelete() {
		return "redirect:/professor/lecture/subjectList.do";
	}
}
