package kr.or.ddit.pfcp.student.eclass.assignement.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.pfcp.common.vo.AssignmentSubmissionVO;

@Mapper
public interface AssignSubmissionMapper {
  public int insertAssignSubmission(AssignmentSubmissionVO assignmentSubmission);
  public List<AssignmentSubmissionVO> selectAssignmentListByAssignNo(String assignNo);
  public int updateAssignmentSubmission(AssignmentSubmissionVO assignmentSubmission);
  public AssignmentSubmissionVO selectAssignmentSubmission(String assignNo, String studentNo);
  public int existsSubmission(AssignmentSubmissionVO assignmentSubmission);
  public int updateAssignSubmissionFile(AssignmentSubmissionVO assignmentSubmission);
  public int updateAssignSubmissionScore(AssignmentSubmissionVO assignmentSubmission);
}
