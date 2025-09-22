package kr.or.ddit.pfcp.student.lecture.material.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.pfcp.common.vo.LectureMaterialVO;

@Mapper
public interface LectureMaterialMapper {
  public int insertLectureMaterial(LectureMaterialVO lectureMaterial);
  public int existsLectureMaterial(LectureMaterialVO lectureMaterial);
  public int updateLectureMaterial(LectureMaterialVO lectureMaterial);
  public List<LectureMaterialVO> getLectureMaterialList(String lecNo);
  public LectureMaterialVO getLectureMaterial(Integer materialNo);
  public int deleteLecureMaterial(Integer materialNo);
}
