package kr.or.ddit.pfcp.common.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "examNo")
public class ExamVO {
	private String examNo;
	private String lecNo;
	private String examName;
	private String examDate;
	private String examType;
	private String examLimit;
	private String facilityNo;
	private String status;
	
	// ADDED
	private String fileRefNo;
	private AtchFileVO atchFile;
	private MultipartFile uploadFile;
	private byte[] atchContent;
	
	private UserVO user;
	private FacilityVO facility;
	private QuestionAnswerVO questionAnswer;
	private LectureReqVO lectureReq;
	
	private List<QuestionAnswerVO> questionAnswerList;
}
