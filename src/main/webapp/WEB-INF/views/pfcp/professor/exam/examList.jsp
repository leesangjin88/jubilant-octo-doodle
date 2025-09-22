<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250715	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<title>Insert title here</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<!-- ======================================== -->
<div class="sectionHeaderLine">
  	<!-- 왼쪽 제목 및 설명 -->
	<div>
		<div class="sectionHeaderTitle">시험 목록 조회</div>
		<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
	</div>

	<!-- 오른쪽 등록 버튼 -->
	<button type="button" class="submitButton" onclick="location.href='/professor/exam/examInsert.do'">+ 등록</button>
</div>
<!-- ======================================== -->
<div class="tableContainer">
	<table class="defaultTable">
		<thead class="tableHead">
			<tr>
				<th class="tableTh">번호</th>
				<th class="tableTh">시험명</th>
				<th class="tableTh">시험 유형</th>
				<th class="tableTh">시험 일자</th>
				<th class="tableTh">시험 제한 시간</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty examList }">
					<c:forEach items="${examList }" var="exam" varStatus="status">
						<c:url value="/professor/exam/examDetail.do" var="detailURL">
							<c:param name="examNo" value="${exam.examNo }"/>
						</c:url>
						<tr onclick="location.href='${detailURL }'" class="tableRowHover">
							<td class="tableTd">${status.index+1}</td>
							<td class="tableTd">${exam.examName }</td>
							<td class="tableTd">${exam.examType }</td>
							<td class="tableTd">${exam.examDate }</td>
							<td class="tableTd">${exam.examLimit }</td>
						</tr>
					</c:forEach>
				</c:when> 
				
				<c:otherwise>
					<tr>
						<td class="tableTd" colspan="6">
							아직 시험 없음.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>

