package kr.or.ddit.pfcp.professor.attendclass.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;

/**
 * @author 김태수
 * @since 2025. 7. 14.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 14.	|	김태수	|	최초 생성
 */
@Mapper
public interface ProfessorAttendClassMapper {


	/**
     * 특정 강의의 수강생 목록 총원 수를 조회합니다.
     * @param lecNo 강의 번호
     * @return 수강 학생 총원 수
     */
    public int cntEnrStd(@Param("lecNo") String lecNo); // 명칭 변경: cntEnrollStd -> cntEnrStd

    /**
     * 특정 강의를 수강하는 학생 목록을 페이징하여 조회합니다.
     * @param lecNo 강의 번호
     * @param offset 오프셋 (조회를 시작할 위치)
     * @param size 페이지당 항목 수
     * @return 수강 학생 목록 (LectureEnrVO 리스트)
     */
    public List<LectureEnrVO> selEnrStdPaging( // 명칭 변경: selEnrollStdPaging -> selEnrStdPaging
        @Param("lecNo") String lecNo,
        @Param("offset") int offset,
        @Param("size") int size
    );
    
    
    
}