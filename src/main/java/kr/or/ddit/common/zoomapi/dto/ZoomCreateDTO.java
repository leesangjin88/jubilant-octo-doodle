package kr.or.ddit.common.zoomapi.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ZoomCreateDTO {
  private ProgramType type;
  
  private Integer th;
  
  private String title;
  
  private LocalDateTime startDate;
}
