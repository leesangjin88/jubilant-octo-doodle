<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-04 	김태수	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>강의 신청 목록 조회</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<div class="section">
	<div class="sectionHeaderLine">
		<div>
			<div class="sectionHeaderTitle">강의 신청 목록</div>
			<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
		</div>
		<button type="button" class="submitButton" 
		onclick="location.href='/professor/lecture/lectureInsert.do'">+ 등록</button>
	</div>
	<div class="tableContainer">
		<table class="defaultTable">
			<thead class="tableHead">
				<tr>
					<th class="tableTh">번호</th>
					<th class="tableTh">과목코드</th>
					<th class="tableTh">과목이름</th>
					<th class="tableTh">교수이름</th>
					<th class="tableTh">강의명</th>
					<th class="tableTh">신청일자</th>
					<th class="tableTh">처리현황</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty lecture }">
						<c:forEach items="${lecture }" var="lecture"  varStatus="status">
							<c:url value="/professor/lecture/lectureDetail.do" var="detailURL">
								<c:param name="no" value="${lecture.reqNo}" />
							</c:url>
							<tr onclick="location.href='${detailURL}'" class="tableRowHover tableRowStripe">
								<td class="tableTd">${status.index+1}</td>
								<td class="tableTd">${lecture.subjectCode}</td>
								<td class="tableTd">${lecture.subjectName}</td>
								<td class="tableTd">${lecture.userName}</td>
								<td class="tableTd">${lecture.lecName}</td>
								<td class="tableTd">${lecture.reqDate}</td>
								<td class="tableTd">
									<c:choose>
											<c:when test="${lecture.status eq '대기'}">
												<span class="badge badgeGreen">대기</span>
											</c:when>
											<c:when test="${lecture.status eq '반려'}">
												<span class="badge badgeYellow">반려</span>
											</c:when>
											<c:when test="${lecture.status eq '승인'}">
												<span class="badge badgeBlue">승인</span>
											</c:when>
											<c:when test="${lecture.status eq '취소'}">
												<span class="badge badgeRed">취소</span>
											</c:when>
											<c:otherwise>
												<span class="badge">${lecture.status}</span>
											</c:otherwise>
										</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="tableRowHover tableRowStripe">
							<td class="tableTd" colspan="8">신청한 강의 목록이 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>

<div class="pagination">
	<c:if test="${pageNo > 1}">
		<a href="?pageNo=${pageNo - 1}" class="pageButton">이전</a>
	</c:if>

	<c:forEach var="i" begin="1" end="${totalPage}">
		<c:choose>
			<c:when test="${i == pageNo}">
				<strong class="pageButton active">${i}</strong>
			</c:when>
			<c:otherwise>
				<a href="?pageNo=${i}" class="pageButton">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${pageNo < totalPage}">
		<a href="?pageNo=${pageNo + 1}" class="pageButton">다음</a>
	</c:if>
</div>