<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-09 	김태수	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>논문 조회</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<div class="section">
	<div class="sectionHeaderLine">
		<div>
			<div class="sectionHeaderTitle">논문 목록</div>
			<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
		</div>
		<button type="button" class="submitButton" 
		onclick="location.href='/professor/thesis/thesisInsert.do'">+ 등록</button>
	</div>
	<div class="tableContainer">
		<table class="defaultTable">
			<thead class="tableHead">
				<tr>
					<th class="tableTh">번호</th>
					<th class="tableTh">제목</th>
					<th class="tableTh">작성 일자</th>
					<th class="tableTh">교수이름</th>
					<th class="tableTh">처리현황</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty thesisList }">
						<c:forEach items="${thesisList }" var="thesis" varStatus="status">
							<c:url value="/professor/thesis/thesisDetail.do" var="detailURL">
								<c:param name="no" value="${thesis.thesisNo }" />
							</c:url>
							<tr onclick="location.href='${detailURL}'" class="tableRowHover tableRowStripe">
								<td class="tableTd">${status.index+1}</td>
								<td class="tableTd">${thesis.thesisTitle}</td>
								<td class="tableTd">${thesis.submitDate}</td>
								<td class="tableTd">${thesis.userName}</td>
								<td class="tableTd">
									<c:choose>
											<c:when test="${thesis.status eq '대기'}">
												<span class="badge badgeGreen">대기</span>
											</c:when>
											<c:when test="${thesis.status eq '반려'}">
												<span class="badge badgeYellow">반려</span>
											</c:when>
											<c:when test="${thesis.status eq '등록'}">
												<span class="badge badgeBlue">승인</span>
											</c:when>
											<c:when test="${thesis.status eq '취소'}">
												<span class="badge badgeRed">취소</span>
											</c:when>
											<c:otherwise>
												<span class="badge">${thesis.status}</span>
											</c:otherwise>
										</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="tableRowHover tableRowStripe">
							<td class="tableTd" colspan="8">등록한 논문 목록이 존재하지 않습니다.</td>
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