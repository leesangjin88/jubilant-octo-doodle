package kr.or.ddit.pfcp.student.eclass.assignement.service;

import java.util.List;
import org.springframework.stereotype.Service;
import kr.or.ddit.pfcp.common.vo.AssignmentVO;
import kr.or.ddit.pfcp.student.eclass.assignement.mapper.AssignmentMapper;
import lombok.RequiredArgsConstructor;


/**
 * @author LSH
 * @since 20250710
 * 
 */
@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
  
  private final AssignmentMapper assignmentMapper;
  /**
   * 과제 목록 조회 구현
   */
  @Override
  public List<AssignmentVO> retrieveAssignments(String lecNo) {
    return assignmentMapper.selectAssignmentList(lecNo);
  }
  @Override
  public int createAssignment(AssignmentVO assignment) {
    return assignmentMapper.insertAssignment(assignment);
  }

}
