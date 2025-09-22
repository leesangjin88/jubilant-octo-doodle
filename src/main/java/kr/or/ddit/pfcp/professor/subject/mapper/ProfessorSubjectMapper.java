package kr.or.ddit.pfcp.professor.subject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.pfcp.common.vo.SubjectVO;

@Mapper
public interface ProfessorSubjectMapper {
	
	/**
	 * 교과목 신청 상세 조회
	 * @param SubjectVO
	 * @return
	 */
	public SubjectVO selectSubject(String SubjectVO);
	
	/**
	 * 교과목 신청 리스트 조회
	 * @return
	 */
	public List<SubjectVO> selectSubjectList();
	
	
	/**
	 * 교과목 신청 등록
	 * @param subject
	 * @return
	 */
	public int insertSubject(SubjectVO subject);
	
	
	/**
	 * 교과목 신청 수정
	 * @param subjcet
	 * @return
	 */
	public int updateSubject(SubjectVO subjcet);
	
}
