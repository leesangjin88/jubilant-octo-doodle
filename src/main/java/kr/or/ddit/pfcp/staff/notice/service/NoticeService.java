package kr.or.ddit.pfcp.staff.notice.service;

import java.util.List;

import kr.or.ddit.pfcp.common.vo.BoardVO;

/**
 * @author YSM
 * @since 2025. 6. 30.
 *
 * << 개정이력(Modification Information) >>
 * 수정일		|	수정자	|	수정 내용
 * -----------------------------------------------
 * 2025. 6. 30.	|	양수민	|	최초 생성
 * 2025. 7. 4.	|	양수민	|	 수정
 * 2025. 7. 5.	|	양수민	|	 수정
 * 2025. 7. 7.	|	이성화	|	 수정
 * 2025. 7. 8.	|	양수민	|	 수정
 * 2025. 7. 11.	|	양수민	|	 수정
 */
public interface NoticeService {

	public List<BoardVO> readBoardList(int offset, int pageSize);

	public BoardVO readBoard(String boardNo);

	public void modifyBoard(BoardVO board);

	public void createBoard(BoardVO board);
	
	public BoardVO readBoardByScheduleNo(String ScheduleNo);

	public void modifyBoardDeleted(String what);

	public int readTotalCount();

}
