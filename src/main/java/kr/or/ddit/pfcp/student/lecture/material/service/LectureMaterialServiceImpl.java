package kr.or.ddit.pfcp.student.lecture.material.service;

import java.util.List;

import org.springframework.stereotype.Service;
import kr.or.ddit.pfcp.common.vo.LectureMaterialVO;
import kr.or.ddit.pfcp.student.lecture.material.mapper.LectureMaterialMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureMaterialServiceImpl implements LectureMaterialService {
   
  private final LectureMaterialMapper lectureMaterialMapper;
  
  @Override
  public int createLectureMaterial(LectureMaterialVO lectureMaterial) {
    return lectureMaterialMapper.insertLectureMaterial(lectureMaterial);
  }
  

  @Override
  public List<LectureMaterialVO> retrieveLectureMaterial(String lecNo) {
  	return lectureMaterialMapper.getLectureMaterialList(lecNo);
  }
  
  
  @Override
  public int modifyLectureMaterial(LectureMaterialVO lectureMaterial) {
    return lectureMaterialMapper.updateLectureMaterial(lectureMaterial);
  }


  @Override
  public LectureMaterialVO retrieveLectureMaterialByMaterialNo(Integer materialNo) {
    return lectureMaterialMapper.getLectureMaterial(materialNo);
  }


  @Override
  public int removeLectureMaterial(Integer materialNo) {
    return lectureMaterialMapper.deleteLecureMaterial(materialNo);
  }

}
