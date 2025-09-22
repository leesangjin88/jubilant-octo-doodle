<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250708	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>장학금 수혜 내역</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 

<!-- ======================================== -->
<div class="sectionHeaderLine">
	<!-- 왼쪽 제목 및 설명 -->
	<div>
		<div class="sectionHeaderTitle">장학금 수혜 내역 목록</div>
		<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
	</div>
	
	<!-- 오른쪽 등록 버튼 -->
	<button type="button" class="submitButton" onclick="location.href='/student/scholarship/scholarshipInsertFormUI.do'">+ 등록</button>
</div>
<!-- ======================================== -->
<!-- <button class="submitButton" onclick="location.href='/student/scholarship/scholarshipInsertFormUI.do'">장학 신청</button> -->
<div class="tableContainer">
	<table id="mainListLoading" class="defaultTable">
		<thead class="tableHead">
			<tr>
				<th class="tableTh">번호</th>
				<th class="tableTh">지급일시</th>
				<th class="tableTh">장학금명</th>
				<th class="tableTh">장학금액</th>
				<th class="tableTh">등록금 총액</th>
				<th class="tableTh">계</th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${scholarship }" var="scholarship" varStatus="status">
				<tr>
					<td class="tableTd">${status.index+1 }</td>
					<td class="tableTd">${scholarship.applyDate }</td>
					<td class="tableTd">${scholarship.scholarshipType.schName }</td>
					<td class="tableTd">${scholarship.disAmount }</td>
					<td class="tableTd">${scholarship.tuitionAmount }</td>
					<td class="tableTd">${scholarship.total }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<br><br>

<button onclick="location.href='/student/scholarship/scholarshipList.do'" class="btn btn-primary">장학금 신청 내역</button>


