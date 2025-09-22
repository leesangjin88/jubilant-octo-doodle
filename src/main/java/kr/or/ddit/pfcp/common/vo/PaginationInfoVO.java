package kr.or.ddit.pfcp.common.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaginationInfoVO<T> {

	// ✅ 페이징 정보
	private int totalRecord; // 전체 레코드 수
	private int currentPage; // 현재 페이지 번호
	private int screenSize = 10; // 한 페이지당 출력 수
	private int blockSize = 5; // 페이지 네비 블록 수

	private int startRow; // 쿼리 시작 행 번호
	private int endRow; // 쿼리 끝 행 번호

	private int startPage; // 페이징 네비 시작 번호
	private int endPage; // 페이징 네비 끝 번호
	private int totalPage; // 전체 페이지 수

	// ✅ 실제 결과 리스트
	private List<T> dataList;

	// ✅ 검색 필터 (비교과 프로그램 신청자 기준)
	private String programTitle; // 프로그램명 검색
	private String userName; // 신청자 이름 검색
	private String status; // 신청 상태 (e.g. 대기, 승인 등)
	private String startDate; // 신청일 시작
	private String endDate; // 신청일 종료

	// ✅ 페이징 계산 메서드
	public void calculatePageInfo() {
		totalPage = (int) Math.ceil((double) totalRecord / screenSize);
		startRow = (currentPage - 1) * screenSize + 1;
		endRow = currentPage * screenSize;

		startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
		endPage = startPage + blockSize - 1;
		if (endPage > totalPage)
			endPage = totalPage;
	}

}
