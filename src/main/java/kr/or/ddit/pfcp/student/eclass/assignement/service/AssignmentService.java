package kr.or.ddit.pfcp.student.eclass.assignement.service;

import java.util.List;
import kr.or.ddit.pfcp.common.vo.AssignmentVO;

/**
 * @author LSH
 * @since 20250710
 */
public interface AssignmentService {
  /**
   * 과제 목록 서비스
   * @param lecNo
   * @return
   */
  public List<AssignmentVO> retrieveAssignments(String lecNo);
  
  /**
   * 과제 생성 서비스
   * @param lecNo
   * @return
   */
  public int createAssignment(AssignmentVO assignment);
}
