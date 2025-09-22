package kr.or.ddit.pfcp.student.eclass.exam.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.pfcp.common.vo.ExamVO;
import kr.or.ddit.pfcp.common.vo.GradeVO;
import kr.or.ddit.pfcp.common.vo.QuestionAnswerVO;
import kr.or.ddit.pfcp.student.eclass.exam.mapper.ExamMapper;
import kr.or.ddit.pfcp.student.lecture.grade.mapper.StudentGradeMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author seokyungdeok
 * @since 2025. 7. 16.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 16.	|	서경덕	|	최초 생성
 */
@Service(value = "examService")
@Slf4j
public class ExamServiceImpl implements ExamService {
	@Autowired
	private ExamMapper examMapper;
	
	@Autowired
    private StudentGradeMapper studentGradeMapper;

	@Override
	public List<ExamVO> readExamList(String userNo, String lecNo) {
		Map<String, String> param = new HashMap<>();
		
	    param.put("userNo", userNo);
	    param.put("lecNo", lecNo);
		
		return examMapper.selectExamList(param);
	}

	@Override
	public ExamVO readExamDetail(String examNo) {
		// TODO Auto-generated method stub
		return examMapper.selectExamDetail(examNo);
	}

	@Override
	public ExamVO readPdfContentByExamNo(String examNo) {
		// TODO Auto-generated method stub
		return examMapper.selectPdfContentByExamNo(examNo);
	}

	@Override
	public String readExamNoByExamName(String examName) {
		// TODO Auto-generated method stub
		return examMapper.selectExamNoByExamName(examName);
	}

	@Override
	public List<QuestionAnswerVO> readQuestionByExamNo(String examNo) {
		// TODO Auto-generated method stub
		return examMapper.selectQuestionByExamNo(examNo);
	}

	@Override
	public String readExamType(String examNo) {
		// TODO Auto-generated method stub
		return examMapper.selectExamType(examNo);
	}

	@Override
	public String readLectureNoByExamNo(String examNo) {
		// TODO Auto-generated method stub
		return examMapper.selectLectureNoByExamNo(examNo);
	}

	public int calculateScore(String examNo, Map<String, Integer> submittedAnswers) {
        int totalScore = 0;
        
        log.info("규민이 : {}", submittedAnswers);
        
        List<QuestionAnswerVO> questions = examMapper.selectQuestionByExamNo(examNo);
        
        log.info("questions : {}", questions);

        for (QuestionAnswerVO questionAnswer : questions) {
            Integer submitted = submittedAnswers.get(String.valueOf(questionAnswer.getQuestionSeq()));
            
            log.info("submitted : {}", submitted);
            
            if (submitted != null && submitted.equals(questionAnswer.getAnswer())) {
                totalScore += questionAnswer.getQuestionScore();
            }
        }
        
        log.info("totalScore : {}", totalScore);
        
        return totalScore;
    }
	
	public String getExamType(String examNo) {
        return examMapper.selectExamType(examNo);
    }
	
	@Transactional
	public void saveGrade(String examNo, String userNo, int score, String examType) {
        String lecNo = examMapper.selectLectureNoByExamNo(examNo);

        GradeVO grade = studentGradeMapper.selectGradeByUserAndLecture(userNo, lecNo);
        
        if (grade == null) {
            grade = new GradeVO();
            grade.setLecNo(lecNo);
            grade.setUserNo(userNo);
            grade.setGradeDate(LocalDate.now().toString());
        }

        if ("중간고사".equals(examType)) {
            grade.setMidtermScore(score);
        } else if ("기말고사".equals(examType)) {
            grade.setFinalScore(score);
        }

        studentGradeMapper.upsertGrade(grade);
    }

	@Override
	public int readQuestionCountByExamNo(String examNo) {
		// TODO Auto-generated method stub
		return examMapper.selectQuestionCountByExamNo(examNo);
	}
}
