package kr.or.ddit.pfcp.student.bill.scholarship.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author seokyungdeok
 * @since 2025. 7. 8.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 7. 8.	|	서경덕	|	최초 생성
 */
@SpringBootTest
@Slf4j
class StudentScholarshipMapperTest {
	@Autowired
	private StudentScholarshipMapper studentScholarshipMapper;
	
	@Test
	void selectScholarshipBenefitListTest() {
		String studentNo = "ST20220810";
		
		assertNotNull(studentScholarshipMapper.selectScholarshipBenefitList(studentNo));
	}

}
