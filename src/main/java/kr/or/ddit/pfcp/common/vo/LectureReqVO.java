package kr.or.ddit.pfcp.common.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 김태수
 * @since 2025. 7. 7.
 *
 * << 개정이력(Modification Information) >>
 * 	수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 7.	|	김태수	|	최초 생성
 */
@Data
@EqualsAndHashCode(of = "reqNo")
public class LectureReqVO {
	
	private int rnum;
	
	private String reqNo;
	private String subjectCode;
	private String userNo;
	private String preDay;
	private String preTime;
	private String preClassrm;
	private String status;
	private String rejReason;
	private String reqDate;
	private Integer maxCapacity;
	private String fileRefNo;
	private String lecName;
	private String lecCategory;
	private String lecNo;
	
	private List<Integer> periodList;
	private List<String> dayList;
	
	
	
	private String subjectName; 
	private String userName;    
	
	private String lrNo;
	
	private MultipartFile uploadFile;
	
	private AtchFileVO atchFile;
	
	
}
