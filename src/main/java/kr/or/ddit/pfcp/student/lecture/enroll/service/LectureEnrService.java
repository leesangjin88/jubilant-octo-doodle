package kr.or.ddit.pfcp.student.lecture.enroll.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;

public interface LectureEnrService {
  public List<LectureEnrVO> retrieveLectureEnrByUserNo(String studentNo);

  public List<LectureVO> readLectureList(String userNo, String departmentNo, String day, String period, Integer credit);
  
  public String readNowSemester();
  
  public int countEnrollLectureByStudent(String lecNo);
  
  public void enrollLecture(String lecNo, String userNo);
  
  public List<LectureEnrVO> getMyEnrollList(String userNo);
  
  public void cancelLecture(String lecNo, String userNo); 
  
  public List<LectureEnrVO> enrollEqlectureStatus(String userNo);
  
  public List<LectureVO> retrieveLectureVOEnrollmentsByUserNo(String userNo);
  
  public void addLectureToCart(String lecNo, String userNo);
  
  public List<LectureEnrVO> getPreEnrollList(String userNo);
  
  public void cancelPreEnroll(String lecNo, String userNo);
  
  public List<LectureEnrVO> readPreEnrollListByPriority(String userNo);
  
  public boolean isEnrollPeriod();
  
  public ScheduleVO readScheduleCurrent();
  
}
