package kr.or.ddit.pfcp.student.eclass.assignement.service;

import java.util.List;
import kr.or.ddit.pfcp.common.vo.AssignmentSubmissionVO;

public interface AssignSubmissionService {
  public int createAssginSubmission(AssignmentSubmissionVO assignmentSubmission);
  public AssignmentSubmissionVO readAssignmentSubmission(String assignNo, String studentNo);
  public List<AssignmentSubmissionVO> retrieveAssignmentSubmissionByAssignNo(String assignNo);
  public int modifyAssignmentSubmissionScore(AssignmentSubmissionVO assignmentSubmission);
}
