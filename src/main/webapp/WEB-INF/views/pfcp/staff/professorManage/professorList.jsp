<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250701	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<title>교수 목록 조회</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 
<div>
	<!-- ======================================== -->
	<div class="sectionHeaderLine">
	  	<!-- 왼쪽 제목 및 설명 -->
		<div>
			<div class="sectionHeaderTitle">교수 목록 조회</div>
			<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
		</div>
	
		<!-- 오른쪽 등록 버튼 -->
		<button type="button" class="submitButton" onclick="location.href='/staff/professorManagement/professorInsert.do'">+ 등록</button>
	</div>
	<!-- ======================================== -->
	<div class="tableContainer">
	
	<table class="defaultTable">
		<thead class="tableHead">
			<tr>
				<th class="tableTh">번호</th>
				<th class="tableTh">교번</th>
				<th class="tableTh">교수명</th>
				<th class="tableTh">교수 직책</th>
				<th class="tableTh">학과</th>
				<th class="tableTh">입사 일자</th>
				<th class="tableTh">퇴사 일자</th>
				<th class="tableTh">교수 상태</th>
			</tr>
		</thead>
		<tbody>
			<%--
			아래는 예시 코드입니다.
			<c:url value="/staff/professorManagement/professorDetail.do" var="detailURL">
				<c:param name="no" value="PR20250701"/>
			</c:url>
			<tr>
				<td>1</td>
				<td><a href="${detailURL }">PR20250701</a></td>
				<td>김태수</td>
				<td>시간 강사</td>
				<td>공과 대학</td>
				<td>2025년 7월 1일</td>
				<td>-</td>
				<td>재직중</td>
			</tr>
			--%>
			<%-- 예시 코드 끝 --%>
			<%-- 아래는 DB를 이용한 동적 코드입니다. --%>
			<c:choose>
				<c:when test="${not empty professor }">
					<c:forEach items="${professor }" var="professor" varStatus="status">
						<c:url value="/staff/professorManagement/professorDetail.do" var="detailURL">
							<c:param name="no" value="${professor.userNo }"/>
						</c:url>
						<tr onclick="location.href='${detailURL }'" class="tableRowHover">
							<td class="tableTd">${status.index+1}</td>
							<td class="tableTd">${professor.user.userNo }</td>
							<td class="tableTd">${professor.user.userName }</td>
							<td class="tableTd">${professor.proPosition }</td>
							<td class="tableTd">${professor.department.departmentName }</td>
							<td class="tableTd">${professor.proHiredate }</td>
							<td class="tableTd">${professor.proRdate }</td>
							<td class="tableTd">${professor.proStatus }</td>
						</tr>
					</c:forEach>
				</c:when> 
				
				<c:otherwise>
					<tr>
						<td class="tableTd" colspan="8">
							아직 교수 없음.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
</div>
