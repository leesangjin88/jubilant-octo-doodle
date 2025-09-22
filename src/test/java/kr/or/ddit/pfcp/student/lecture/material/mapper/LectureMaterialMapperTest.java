package kr.or.ddit.pfcp.student.lecture.material.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import kr.or.ddit.pfcp.common.vo.LectureMaterialVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class LectureMaterialMapperTest {
  
  @Autowired
  LectureMaterialMapper lectureMaterialMapper;

  @Test
  void testInsertLectureMaterial() {
    LectureMaterialVO lectureMaterial = new LectureMaterialVO();
    
    lectureMaterial.setMaterialTitle("test자료");
    lectureMaterial.setMaterialDesp("test자료 내용");
    lectureMaterial.setLecNo("LEC001");
    lectureMaterial.setProfessorNo("PR20250701");
    
    
    lectureMaterialMapper.insertLectureMaterial(lectureMaterial);
  }

}
