package kr.or.ddit.pfcp.common.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YSM
 * @since 250704
 * 
 *        << 개정이력(Modification Information) >> 수정일 | 수정자 | 수정 내용
 *        ----------------------------------------------- 250704 | 양수민 | 최초 생성
 *        ----------------------------------------------- 250707 | 이상진 | 수정
 *        ----------------------------------------------- 250710 | 양수민 | 수정 | UserVO 추가
 *        
 */

@Data
@EqualsAndHashCode(of="boardNo")
public class BoardVO {
	private String boardNo;
	private String boardTitle;
	private String boardContent;
	private String writerNo;
	private String postDate;
	private String updateDate;
	private String category;
	private String scheduleNo;
	
	private String typeCode;
	
	private ScheduleVO schedule;
	
	private transient UserVO user;
	
	private String fileRefNo;
	private MultipartFile uploadFile;
	private AtchFileVO atchFile;
}
