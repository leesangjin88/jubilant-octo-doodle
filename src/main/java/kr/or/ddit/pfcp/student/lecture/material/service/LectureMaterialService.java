package kr.or.ddit.pfcp.student.lecture.material.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.LectureMaterialVO;

public interface LectureMaterialService {
  public int createLectureMaterial(LectureMaterialVO lectureMaterial);
  public List<LectureMaterialVO> retrieveLectureMaterial(String lecNo);
  public LectureMaterialVO retrieveLectureMaterialByMaterialNo(Integer materialNo);
  public int modifyLectureMaterial(LectureMaterialVO lectureMaterial);
  public int removeLectureMaterial(Integer materialNo);
}
