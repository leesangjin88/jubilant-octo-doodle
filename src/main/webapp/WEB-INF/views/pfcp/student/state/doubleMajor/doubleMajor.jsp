<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>복수 전공</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 

<!-- ======================================== -->
<div class="sectionHeaderLine">
  	<!-- 왼쪽 제목 및 설명 -->
	<div>
		<div class="sectionHeaderTitle">복수 전공 신청 목록</div>
		<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
	</div>

	<!-- 오른쪽 등록 버튼 -->
	<button type="button" class="submitButton" onclick="location.href='/student/counsel/doubleMajorInsert.do'">+ 등록</button>
</div>
<!-- ======================================== -->
<div class="tableContainer">
	<table class="defaultTable">
		<thead class="tableHead">
			<tr>				
				<th class="tableTh">번호</th>
				<th class="tableTh">신청일자</th>
				<th class="tableTh">내용</th>
				<th class="tableTh">처리 현황</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty doubleList }">
					<c:forEach items="${doubleList }" var="doubleList" varStatus="status">
						<c:url value="/student/counsel/doubleMajorDetail.do" var="detailURL">
							<c:param name="no" value="${doubleList.requestNo }"/>
						</c:url>
						<tr onclick="location.href='${detailURL}'" class="tableRowHover">						
							<td class="tableTd">${status.index+1 }</td>
							<td class="tableTd">${doubleList.requestDate }</td>
							<td class="tableTd">${doubleList.reason }</td>
							<td class="tableTd">${doubleList.status }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">아직 신청 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>