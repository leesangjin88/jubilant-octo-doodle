<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<title>교과목 관리</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 
<!-- ======================================== -->
<div class="sectionHeaderLine">
  	<!-- 왼쪽 제목 및 설명 -->
	<div>
		<div class="sectionHeaderTitle">강의 신청 조회</div>
		<div class="sectionHeaderDescription">전체 ${count}개의 게시글 (밑의 조회 결과 항목들은 더미데이터로, 다소 정확하지 않습니다.)</div>
	</div>
</div>
<!-- ======================================== -->

<div class="tableContainer">
	<table class="defaultTable">
		<thead class="tableHead">
			<tr>
				<th class="tableTh">번호</th>
				<th class="tableTh">교수명</th>
				<th class="tableTh">과목명</th>
				<th class="tableTh">강의명</th>
				<th class="tableTh">희망 요일</th>
				<th class="tableTh">희망 교시</th>
				<th class="tableTh">희망 강의실</th>
				<th class="tableTh">신청 일자</th>
				<th class="tableTh">강의 분류</th>
				<th class="tableTh">강의 계획서</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty lectureReqList }">
					<c:forEach items="${lectureReqList }" var="lectureReq" varStatus="status">
						<c:url value="/staff/subject/subjectDetail.do" var="detailURL">
							<c:param name="no" value="${lectureReq.reqNo }"/>
						</c:url>
						<tr class="tableRowHover" onclick="location.href='${detailURL }'">
							<td class="tableTd">${status.index+1}</td>
							<td class="tableTd">${lectureReq.userName }</td>
							<td class="tableTd">${lectureReq.subjectName }</td>
							<td class="tableTd">${lectureReq.lecName }</td>
							<td class="tableTd">${lectureReq.preDay }</td>
							<td class="tableTd">${lectureReq.preTime }</td>
							<td class="tableTd">${lectureReq.preClassrm }</td>
							<td class="tableTd">${lectureReq.reqDate }</td>
							<c:if test="${lectureReq.lecCategory eq 'MJ_REQ'}">
								<td class="tableTd">전공 필수</td>							
							</c:if>
							<c:if test="${lectureReq.lecCategory eq 'MJ_SEL'}">
								<td class="tableTd">전공 선택</td>							
							</c:if>
							<td class="tableTd">
								<a href="/staff/subject/fileDownload.do?fileRefNo=${lectureReq.fileRefNo}">
									${lectureReq.atchFile.atchOriginName}
								</a>
							</td>
						</tr>
					</c:forEach>
				</c:when> 
				
				<c:otherwise>
					<tr>
						<td class="tableTd" colspan="9">
							아직 강의 등록 신청 없음.
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>