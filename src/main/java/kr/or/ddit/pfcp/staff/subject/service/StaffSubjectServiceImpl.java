package kr.or.ddit.pfcp.staff.subject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.pfcp.common.service.AtchFileService;
import kr.or.ddit.pfcp.common.service.FileRefService;
import kr.or.ddit.pfcp.common.vo.AtchFileVO;
import kr.or.ddit.pfcp.common.vo.FileRefVO;
import kr.or.ddit.pfcp.common.vo.LectureReqVO;
import kr.or.ddit.pfcp.staff.subject.mapper.StaffSubjectMapper;

/**
 * @author seokyungdeok
 * @since 2025. 7. 12.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 12.	|	서경덕	|	최초 생성
 */
@Service(value = "staffSubejctService")
public class StaffSubjectServiceImpl implements StaffSubjectService {
	@Autowired
	private StaffSubjectMapper staffSubjectMapper;
	
	@Autowired
	private FileRefService fileRefService;
	
	@Autowired
	private AtchFileService atchFileService; 

	@Override
	public List<LectureReqVO> readLectureReqList() {
		List<LectureReqVO> lectureReqList = staffSubjectMapper.selectLectureReqList();

	    for (LectureReqVO lectureReq : lectureReqList) {
	    	
	        if (lectureReq.getFileRefNo() != null) {
	            FileRefVO fileRef = fileRefService.readFileRef(lectureReq.getFileRefNo());
	            
	            if (fileRef != null) {
	                AtchFileVO atchFile = atchFileService.readAtchFile(fileRef.getAtchId());
	                lectureReq.setAtchFile(atchFile);
	            }
	        }
	    }

	    return lectureReqList;
	}

	@Override
	public LectureReqVO readLectureReqDetail(String reqNo) {
		LectureReqVO lectureReq = staffSubjectMapper.selectLectureReqDetail(reqNo);
		
        if (lectureReq.getFileRefNo() != null) {
            FileRefVO fileRef = fileRefService.readFileRef(lectureReq.getFileRefNo());
            
            if (fileRef != null) {
                AtchFileVO atchFile = atchFileService.readAtchFile(fileRef.getAtchId());
                lectureReq.setAtchFile(atchFile);
            }
        }
	    
		return lectureReq;
	}

	@Override
	public void modifySubjectAccept(String reqNo) {
		
		staffSubjectMapper.updateSubjectAccept(reqNo);
	}

	@Override
	public void createAcceptLecture(LectureReqVO lectureReq) {
		
		staffSubjectMapper.insertAcceptLecture(lectureReq);
	}

	@Override
	public void returnLectureReq(LectureReqVO lectureReq) {
		// TODO Auto-generated method stub
		staffSubjectMapper.returnLectureReq(lectureReq);
	}

}
