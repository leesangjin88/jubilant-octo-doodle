<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250705	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>성적 이의 신청</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<div>
	<!-- ======================================== -->
	<div class="sectionHeaderLine">
	  	<!-- 왼쪽 제목 및 설명 -->
		<div>
			<div class="sectionHeaderTitle">성적 이의 신청 목록</div>
			<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
		</div>
	
		<!-- 오른쪽 등록 버튼 -->
		<button type="button" class="submitButton" onclick="location.href='/student/grade/appealInsert.do'">+ 등록</button>
	</div>
	<!-- ======================================== -->
	<label class="inputLabel">* 한번 등록하신 이의 신청은 수정 및 삭제가 불가능하니 신중히 결정 부탁드립니다.</label>
	<div class="tableContainer">
		<table class="defaultTable">
			<thead class="tableHead">
				<tr>
					<th class="tableTh">신청 일자</th>
					<th class="tableTh">과목명</th>					
					<th class="tableTh">상담 내용</th>
					<th class="tableTh">상담 상태</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty gradeAppealList }">
						<c:forEach items="${gradeAppealList }" var="gradeAppeal">
							<tr onclick="" class="tableRowHover">
								<td class="tableTd">${gradeAppeal.counselReqdate }</td>
								<td class="tableTd">${gradeAppeal.lectureReq.lecName }</td>
								<td class="tableTd">${gradeAppeal.counselComment }</td>
								<td class="tableTd">${gradeAppeal.status }</td>
								<td class="tableTd">
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6" class="tableTd">아직 상담 없음.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>
