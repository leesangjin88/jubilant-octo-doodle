package kr.or.ddit.pfcp.professor.lecture.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.ddit.pfcp.common.vo.LectureVO;
import kr.or.ddit.pfcp.professor.lecture.service.ProfessorLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
*
* @author 이성화
* @since 2025.07.09
* @see
*
* 수정일         |     수정자 |   수정 내용
* ------------|-------------|--------------------------
* 2025.07.09  |     이성화     |  최초 작성
*/
@RestController
@RequestMapping("/rest/lecture")
@RequiredArgsConstructor
@Slf4j
public class ProfessorLectureRestController {
  
  private final ProfessorLectureService professorLectureService;
  
  @GetMapping("/lectureList/{userNo}")
  public ResponseEntity<List<LectureVO>> getLectureList(@PathVariable String userNo){
    List<LectureVO> lectureList = professorLectureService.retrieveApprovedLectureListByProfNo(userNo);
    log.info("{}", lectureList);
    return ResponseEntity.ok(lectureList);
  }
}
