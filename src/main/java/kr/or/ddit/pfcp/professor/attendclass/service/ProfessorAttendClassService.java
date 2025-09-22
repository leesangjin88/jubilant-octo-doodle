package kr.or.ddit.pfcp.professor.attendclass.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;

/**
 * @author 김태수
 * @since 2025. 7. 14.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 14.	|	김태수	|	최초 생성
 */
public interface ProfessorAttendClassService {
	
	 /**
     * 특정 강의를 수강하는 학생들의 총원 수를 가져옵니다.
     * @param lecNo 강의 번호
     * @return 수강 학생 총원 수
     */
    public int countEnrStd(String lecNo); 

    /**
     * 특정 강의를 수강하는 학생 목록을 페이징하여 가져옵니다.
     * @param lecNo 강의 번호
     * @param offset 오프셋 (조회를 시작할 위치)
     * @param size 페이지당 항목 수
     * @return 수강 학생 목록 (LectureEnrVO 리스트)
     */
    public List<LectureEnrVO> retrieveEnrStdPaging(String lecNo, int offset, int size); 
	
}
