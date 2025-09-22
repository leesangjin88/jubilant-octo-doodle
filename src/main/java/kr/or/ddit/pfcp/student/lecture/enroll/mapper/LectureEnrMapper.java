package kr.or.ddit.pfcp.student.lecture.enroll.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.pfcp.common.vo.DepartmentVO;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.common.vo.ScheduleVO;

@Mapper
public interface LectureEnrMapper {
  public List<LectureEnrVO> selectLectureEnrollmentsByUserNo(String studentNo);
  
  public List<LectureVO> selectLectureListBySemester(
		  @Param("userNo") String userNo,
          @Param("departmentNo") String departmentNo,
          @Param("day") String day,
          @Param("period") String period,
          @Param("credit") Integer credit
          );
  
  public String getCurrentSemester();
  
  public int selectEnrollmentCountByLectureNO(String lecNo);
  
  // public int insertLectureEnrollmentCount(LectureEnrVO lecEnrVO);
  
  public int incrementCurrentEnrollment(String lecNo);
  
  public boolean existsEnrollment(@Param("userNo") String userNo, @Param("lecNo")String lecNo);
  
  public int selectCurrentEnrollment(String lecNo);
  
  public int selectMaxCapacity(String lecNo);
  
  public List<LectureEnrVO> selectMyLectureEnrollList(String userNo);
  
  public int deleteEnrollment(@Param("userNo") String userNo, @Param("lecNo") String lecNo);
  
  public int decrementCurrentEnrollment(String lecNo);
  
  public int selectCurrentEnrollmentForUpdate(String lecNo);
  
  public List<LectureEnrVO> selectMappingEnrollStatus(String userNo);
  
  public List<LectureVO> selectLectureVOEnrollmentsByUserNo(String userNo);
  
  public LectureVO selectLectureById(String lecNo);
  
  // public int insertLecturePreEnroll(LectureEnrVO lecEnrVO);
  
  public List<LectureEnrVO> selectPreEnrollListByUserNo(String userNo);
  
  public int cancelPreEnroll(@Param("userNo") String userNo, @Param("lecNo") String lecNo);
  
  public List<LectureEnrVO> selectPreEnrollListByUserNoOrderByPriority(String userNo);
  
  public boolean isNowRegularEnrollPeriod();
  
  public ScheduleVO selectCurrentScheduleByType();
  
  
  //
  // 수강신청(업데이트 연동 사용함)
  public int upsertLectureEnrollment(LectureEnrVO lecEnrVO);
  // exist로 enroll확인후 없으면 시쿼스 받아서 생성
  public String getNextEnrollNo();
  
  // 성적 존재확인
  public boolean existsGradeByEnrollNo(@Param("userNo") String userNo, @Param("lecNo") String lecNo);
  
  public int insertGradeForEnroll(@Param("userNo") String userNo, @Param("lecNo") String lecNo);
  
  public int reviveGradeByEnrollNo(@Param("userNo") String userNo, @Param("lecNo") String lecNo);
  
  public int deactivateGradeByEnrollNo(@Param("userNo") String userNo, @Param("lecNo") String lecNo);
  
  public String selectEnrollNoByUserAndLecture(@Param("userNo") String userNo, @Param("lecNo") String lecNo);

  public boolean existsEnrollmentByGrade(@Param("userNo") String userNo, @Param("lecNo") String lecNo);

  // 예비수강신청 중복방지 exist
  public boolean existsPreEnrollment(@Param("userNo") String userNo, @Param("lecNo") String lecNo);
  
  public int upsertPreLectureEnrollment(LectureEnrVO lecEnrVO);
  
  public List<DepartmentVO> selectDepartmentList();
  
}
