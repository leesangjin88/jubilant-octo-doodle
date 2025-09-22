package kr.or.ddit.pfcp.student.eclass.assignement.service;

import java.util.List;
import org.springframework.stereotype.Service;
import kr.or.ddit.pfcp.common.vo.AssignmentSubmissionVO;
import kr.or.ddit.pfcp.student.eclass.assignement.mapper.AssignSubmissionMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignSubmssionServiceImpl implements AssignSubmissionService {
  
  private final AssignSubmissionMapper assignSubmissionMapper;
  
  @Override
  public int createAssginSubmission(AssignmentSubmissionVO assignmentSubmission) {
    if(assignSubmissionMapper.existsSubmission(assignmentSubmission) > 0) {
      return assignSubmissionMapper.updateAssignSubmissionFile(assignmentSubmission);
    } else {
      return assignSubmissionMapper.insertAssignSubmission(assignmentSubmission);
    }
  }

  @Override
  public AssignmentSubmissionVO readAssignmentSubmission(String assignNo, String studentNo) {
    return assignSubmissionMapper.selectAssignmentSubmission(assignNo, studentNo);
  }

  @Override
  public List<AssignmentSubmissionVO> retrieveAssignmentSubmissionByAssignNo(String assignNo) {
    return assignSubmissionMapper.selectAssignmentListByAssignNo(assignNo);
  }

  @Override
  public int modifyAssignmentSubmissionScore(
      AssignmentSubmissionVO assignmentSubmission) {
    return assignSubmissionMapper.updateAssignSubmissionScore(assignmentSubmission);
  }

}
