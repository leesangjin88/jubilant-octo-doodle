package kr.or.ddit.pfcp.professor.grade.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.GradeVO;

/**
 * @author 김태수
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	김태수	|	최초 생성
 */
@Mapper
public interface ProfessorGradeMapper {

	public List<GradeVO> selStuGrd(@Param("lecNo") String lecNo);
    public Integer calAttScr(@Param("lecNo") String lecNo, @Param("userNo") String userNo);
    public Integer getAssignSubmitScore(@Param("assignNo") String assignNo, @Param("userNo") String userNo);
    public GradeVO selectStuGrade(Map<String, Object> params); 

    public List<String> getAssignNosByLecNo(@Param("lecNo") String lecNo);
    public GradeVO selStuScr(@Param("enrNo") String enrNo);

    public void updMidScr(@Param("lecNo") String lecNo, @Param("userNo") String userNo, @Param("midtermScore") Integer midtermScore);
    public void updFinScr(@Param("lecNo") String lecNo, @Param("userNo") String userNo, @Param("finalScore") Integer finalScore);
    public void updAssScr(@Param("lecNo") String lecNo, @Param("userNo") String userNo, @Param("assignmentScore") Integer assignmentScore);
    public void updAttScr(@Param("lecNo") String lecNo, @Param("userNo") String userNo, @Param("attendanceScore") Integer attendanceScore);
    public void updFinGrDt(@Param("lecNo") String lecNo, @Param("userNo") String userNo, @Param("finalGrade") Integer finalGrade);

    public void insGrd(GradeVO grdVO);
    public Integer getAssSubScr(@Param("assignNo") String assignNo, @Param("userNo") String userNo);


    public void updAllScores(GradeVO gradeVO);
    public void updFinGradeAndAlpha(Map<String, Object> params);
    public void updLecEnrGradeAlpha(Map<String, Object> params);
}
