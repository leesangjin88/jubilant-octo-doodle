package kr.or.ddit.pfcp.professor.grade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import kr.or.ddit.pfcp.common.vo.GradeVO;
import kr.or.ddit.pfcp.professor.grade.mapper.ProfessorGradeMapper;
import lombok.RequiredArgsConstructor;

/**
 * @author 김태수
 * @since 2025. 7. 17.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 17.	|	김태수	|	최초 생성
 */
@Service
@RequiredArgsConstructor
public class ProfessorGradeServiceImpl implements ProfessorGradeService {

    private final ProfessorGradeMapper professorGradeMapper;

    @Override
    public List<GradeVO> getStuGrd(String lecNo) {
        List<GradeVO> stuGrds = professorGradeMapper.selStuGrd(lecNo);

        List<String> assignNos = professorGradeMapper.getAssignNosByLecNo(lecNo);

        for (GradeVO stu : stuGrds) {
            if (stu.getUserNo() != null && stu.getLecNo() != null) {
                Integer attScr = professorGradeMapper.calAttScr(stu.getLecNo(), stu.getUserNo());
                stu.setAttendanceScore(attScr);

                int totalSubmitScore = 0;
                if (assignNos != null && !assignNos.isEmpty()) {
                    for (String assignNo : assignNos) {
                        Integer subScr = professorGradeMapper.getAssSubScr(assignNo, stu.getUserNo());
                        if (subScr != null) {
                            totalSubmitScore += subScr;
                        }
                    }
                }
                stu.setSubmitScore(totalSubmitScore);

                if (stu.getAssignmentScore() == null) {
                    stu.setAssignmentScore(totalSubmitScore);
                }
            }
            if (stu.getFinalGrade() == null) {
                stu.setFinalGrade(0);
            }
            if (stu.getGradeDate() == null) {
                stu.setGradeDate("");
            }
            if (stu.getFinalGradeAlpha() == null) {
                stu.setFinalGradeAlpha("N/A"); 
            }
        }
        return stuGrds;
    }

    @Override
    public Integer getAttScr(String lecNo, String userNo) {
        return professorGradeMapper.calAttScr(lecNo, userNo);
    }

    @Override
    public Integer getAssSubScr(String assignNo, String userNo) {
        return professorGradeMapper.getAssSubScr(assignNo, userNo);
    }

    @Override
    public void updStuScrs(String lecNo, String userNo, Integer midScr, Integer finScr, Integer assScr, Integer attScr) {
        GradeVO gradeVO = new GradeVO();
        gradeVO.setLecNo(lecNo);
        gradeVO.setUserNo(userNo);
        gradeVO.setMidtermScore(midScr);
        gradeVO.setFinalScore(finScr);
        gradeVO.setAssignmentScore(assScr);
        gradeVO.setAttendanceScore(attScr);

        professorGradeMapper.updAllScores(gradeVO);
        calcAndSetFinGr(lecNo, userNo);
    }

    @Override
    public void updMidScr(String lecNo, String userNo, Integer midtermScore) { 
        professorGradeMapper.updMidScr(lecNo, userNo, midtermScore);
        calcAndSetFinGr(lecNo, userNo);
    }

    @Override
    public void updFinScr(String lecNo, String userNo, Integer finalScore) { 
        professorGradeMapper.updFinScr(lecNo, userNo, finalScore);
        calcAndSetFinGr(lecNo, userNo);
    }

    @Override
    public void updAssScr(String lecNo, String userNo, Integer assignmentScore) { 
        professorGradeMapper.updAssScr(lecNo, userNo, assignmentScore);
        calcAndSetFinGr(lecNo, userNo);
    }

    @Override
    public void updAttScr(String lecNo, String userNo, Integer attendanceScore) {
        professorGradeMapper.updAttScr(lecNo, userNo, attendanceScore);
        calcAndSetFinGr(lecNo, userNo);
    }

    @Override
    @Transactional 
    public void calcAndSetFinGr(String lecNo, String userNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("lecNo", lecNo);
        params.put("userNo", userNo);

        GradeVO calculatedGrade = professorGradeMapper.selectStuGrade(params);

        if (calculatedGrade != null && calculatedGrade.getFinalGrade() != null && calculatedGrade.getFinalGradeAlpha() != null) {
            Map<String, Object> updateGradeFinalParams = new HashMap<>();
            updateGradeFinalParams.put("lecNo", lecNo);
            updateGradeFinalParams.put("userNo", userNo);
            updateGradeFinalParams.put("finalGrade", calculatedGrade.getFinalGrade());
            updateGradeFinalParams.put("finalGradeAlpha", calculatedGrade.getFinalGradeAlpha());
            professorGradeMapper.updFinGradeAndAlpha(updateGradeFinalParams); // GRADE 테이블 업데이트

            Map<String, Object> updateLecEnrParams = new HashMap<>();
            updateLecEnrParams.put("lecNo", lecNo);
            updateLecEnrParams.put("userNo", userNo);
            updateLecEnrParams.put("finalGradeAlpha", calculatedGrade.getFinalGradeAlpha());
            professorGradeMapper.updLecEnrGradeAlpha(updateLecEnrParams); // LECTURE_ENR 테이블 업데이트
        } else {

            System.err.println("경고: 학생 " + userNo + "의 강의 " + lecNo + "에 대한 성적 정보를 찾을 수 없거나 유효한 최종 성적을 계산할 수 없습니다. 초기 성적 입력이 필요할 수 있습니다.");
 
        }
    }

    @Override
    @Transactional 
    public void updateAndCalculateFinalGrade(
        String lecNo,
        String userNo,
        Integer midtermScore,
        Integer finalScore,
        Integer assignmentScore,
        Integer attendanceScore
    ) {
        
        Map<String, Object> selectParams = new HashMap<>();
        selectParams.put("lecNo", lecNo);
        selectParams.put("userNo", userNo);
        GradeVO existingGrade = professorGradeMapper.selectStuGrade(selectParams); 

        
        GradeVO gradeToProcess = new GradeVO();
        gradeToProcess.setLecNo(lecNo);
        gradeToProcess.setUserNo(userNo);
        gradeToProcess.setMidtermScore(midtermScore);
        gradeToProcess.setFinalScore(finalScore);
        gradeToProcess.setAssignmentScore(assignmentScore);
        gradeToProcess.setAttendanceScore(attendanceScore);

        if (existingGrade != null && existingGrade.getGradeNo() != null) {
            professorGradeMapper.updAllScores(gradeToProcess); 
        } else {
            if (gradeToProcess.getFinalGrade() == null) gradeToProcess.setFinalGrade(0);
            if (gradeToProcess.getFinalGradeAlpha() == null) gradeToProcess.setFinalGradeAlpha("F");
            professorGradeMapper.insGrd(gradeToProcess); 
        }

        calcAndSetFinGr(lecNo, userNo);
    }

    @Override
    public GradeVO selStuScr(String enrNo) {
        return professorGradeMapper.selStuScr(enrNo);
    }

    @Override
    public GradeVO selStuGrade(String lecNo, String userNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("lecNo", lecNo);
        params.put("userNo", userNo);
        return professorGradeMapper.selectStuGrade(params);
    }

	@Override
	public void updStuScrs(String lecNo, Integer midScr, Integer finScr, Integer assScr, Integer attScr) {
		// TODO Auto-generated method stub
		
	}
}