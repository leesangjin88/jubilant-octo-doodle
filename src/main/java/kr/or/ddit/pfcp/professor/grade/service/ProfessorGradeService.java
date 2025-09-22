package kr.or.ddit.pfcp.professor.grade.service;

import java.util.List;

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
public interface ProfessorGradeService {

	public List<GradeVO> getStuGrd(String lecNo);
    public GradeVO selStuGrade(String lecNo, String userNo);
    public GradeVO selStuScr(String enrNo);
    public Integer getAttScr(String lecNo, String userNo);
    public Integer getAssSubScr(String assignNo, String userNo);

    public void updMidScr(String lecNo, String userNo, Integer midScr);
    public void updFinScr(String lecNo, String userNo, Integer finScr);
    public void updAssScr(String lecNo, String userNo, Integer assScr);
    public void updAttScr(String lecNo, String userNo, Integer attScr);

    public void calcAndSetFinGr(String lecNo, String userNo);
    
    public void updateAndCalculateFinalGrade(
        String lecNo,
        String userNo,
        Integer midtermScore,
        Integer finalScore,
        Integer assignmentScore,
        Integer attendanceScore
    );
    
    public void updStuScrs(
    		String lecNo, 
    		Integer midScr, 
    		Integer finScr, 
    		Integer assScr, 
    		Integer attScr
    );
    
    
	public void updStuScrs(
			String lecNo, 
			String userNo, 
			Integer midScr, 
			Integer finScr, 
			Integer assScr, 
			Integer attScr
	);
	
}
