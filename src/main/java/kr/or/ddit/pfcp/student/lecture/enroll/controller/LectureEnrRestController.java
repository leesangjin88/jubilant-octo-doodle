package kr.or.ddit.pfcp.student.lecture.enroll.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.or.ddit.pfcp.common.vo.LectureEnrVO;
import kr.or.ddit.pfcp.student.lecture.enroll.service.LectureEnrService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/lectureEnrollment")
@RequiredArgsConstructor
public class LectureEnrRestController {
  
  private final LectureEnrService lectureEnrService;
  
  @GetMapping("{studentNo}")
  public ResponseEntity<List<LectureEnrVO>> getLectureEnrList(@PathVariable String studentNo) {
    return ResponseEntity.ok(lectureEnrService.retrieveLectureEnrByUserNo(studentNo));
  }
  
}
