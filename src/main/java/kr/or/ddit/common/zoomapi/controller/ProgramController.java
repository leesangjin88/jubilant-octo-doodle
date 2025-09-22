package kr.or.ddit.common.zoomapi.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.ddit.common.zoomapi.component.ZoomAuthenticationHelper;
import kr.or.ddit.common.zoomapi.component.ZoomMeetingApiHelper;
import kr.or.ddit.common.zoomapi.dto.ZoomCreateDTO;
import kr.or.ddit.common.zoomapi.dto.ZoomMeetingCreateResponse;
import kr.or.ddit.common.zoomapi.service.SessionsService;
import kr.or.ddit.common.zoomapi.vo.SessionsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/program")
@Tag(name = "Program")
public class ProgramController {
  
  private final SessionsService service;

  private final ZoomAuthenticationHelper zoomAuthenticationHelper;
  
  private final ZoomMeetingApiHelper zoomMeetingApiHelper;

  @GetMapping("/zoom")
  public ResponseEntity<String> zoom() throws Exception {
    return ResponseEntity.ok(zoomAuthenticationHelper.getAccessToken());
  }

  @PostMapping("/zoom")
  public ResponseEntity<ZoomMeetingCreateResponse> zoom(@RequestBody ZoomCreateDTO requestDTO) throws Exception {
    
    return ResponseEntity.ok(zoomMeetingApiHelper.createMeeting(requestDTO.getType(),
        requestDTO.getTitle(), requestDTO.getTh(), requestDTO.getStartDate()));
  }
  
  @PostMapping("/zoom/insertData")
  public int insertSession(@RequestBody SessionsVO session) {
    
    log.info("session : {}" + session);
    return service.createSession(session);
  }
  
  @GetMapping("/zoom/sessions/{userNo}")
  public ResponseEntity<List<SessionsVO>> getSessionList(
        @PathVariable String userNo
      ){
    try {
      List<SessionsVO> sessions = service.readSessions(userNo);
      return ResponseEntity.ok(sessions);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @GetMapping("/zoom/sessions/prof")
  public ResponseEntity<List<SessionsVO>> getSessionListByProfNo(
      ){
    try {
      List<SessionsVO> sessions = service.readSessionsByProfNo();
      return ResponseEntity.ok(sessions);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @PutMapping("/zoom/updateData")
  public ResponseEntity<List<SessionsVO>> updateSession(@RequestBody SessionsVO session){
    try {
      service.changeStatusSession(session);
      log.info("session {}", session);
      List<SessionsVO> sessions = null;
      if(session.getUser().getUserRole().equals("ROLE_PROF")) {
        sessions = service.readSessionsByProfNo();
      } else {
        sessions = service.readSessions(session.getUserNo());
      }
      return ResponseEntity.ok(sessions);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
