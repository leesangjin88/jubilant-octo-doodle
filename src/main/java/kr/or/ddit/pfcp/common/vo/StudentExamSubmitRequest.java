package kr.or.ddit.pfcp.common.vo;

import java.util.Map;

import lombok.Data;

/**
 * 학생 액터 시험 제출 DTO
 * 
 * @author seokyungdeok
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	서경덕	|	최초 생성
 */
@Data
public class StudentExamSubmitRequest {
	private String examNo;
    private String userNo;
    private Map<String, Integer> answers;
}
