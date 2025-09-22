<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250708	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>장학 신청 내역</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<div class="sectionHeaderLine">
	<!-- 왼쪽 제목 및 설명 -->
	<div>
		<div class="sectionHeaderTitle">장학금 신청 내역 목록</div>
		<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
	</div>
</div>
<div class="tableContainer">
	<table class="defaultTable">
		<thead class="tableHead">
			<tr>
				<th class="tableTh">번호</th>
				<th class="tableTh">장학 유형</th>
				<th class="tableTh">신청 일자</th>
				<th class="tableTh">내용</th>
				<th class="tableTh">신청 상태</th>
				<th class="tableTh">승인 일자</th>
				<th class="tableTh">첨부파일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${scholarshipApplyList }" var="scholarship" varStatus="status">
				<c:url value="/student/scholarship/scholarshipUpdate.do" var="detailURL">
					<c:param name="no" value="${scholarship.applyNo }"/>
				</c:url>
				<tr onclick="location.href='${detailURL }'" class="tableRowHover">
					<td class="tableTd">${status.index+1 }</td>
					<td class="tableTd">${scholarship.scholarshipType.schName }</td>
					<td class="tableTd">${scholarship.requestDate }</td>
					<td class="tableTd">${scholarship.requestComment }</td>
					<td class="tableTd">${scholarship.applyStatus }</td>
					<td class="tableTd">${scholarship.approveDate }</td>
					<td class="tableTd">
						<c:if test="${not empty scholarship.fileRefNo}">
					        <a download href="/student/scholarship/fileDownload.do?fileRefNo=${scholarship.fileRefNo}"><i class="fas fa-download"></i> 다운로드</a>
					    </c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>