<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250716	양수민	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

<title>학생 장학 신청 목록</title>

<div class="sectionHeaderLine">
	<div>
		<div class="sectionHeaderTitle">[ ${schTypeName } ] 신청 목록</div>
		
		<span class="sectionHeaderDescription">전체 ${totalCount }개의 게시글</span>
	</div>
</div>

<div class="tableContainer">
	<table class="defaultTable">
		<thead class="tableHead">
			<tr>
				<th class="tableTh">번호</th>
				<th class="tableTh">학번</th>
				<th class="tableTh">신청자명</th>
				<th class="tableTh">신청 일자</th>
				<th class="tableTh">신청 상태</th>
				<th class="tableTh">처리 일자</th>
				<th class="tableTh">첨부파일</th>
				<th class="tableTh">처리현황</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
        <c:when test="${not empty scholarshipApplyList }">
			<c:forEach items="${scholarshipApplyList }" var="scholarshipApplyList" varStatus="status">
				<c:url value="/staff/scholarshipApply/staffScholarshipUpdate.do" var="detailURL">
					<c:param name="no" value="${scholarshipApplyList.applyNo }"/>
				</c:url>
				<tr onclick="location.href='${detailURL }'" class="tableRowHover">
					<td class="tableTd">${status.index+1 }</td>
					<td class="tableTd">${scholarshipApplyList.userNo }</td>
					<td class="tableTd">${scholarshipApplyList.user.userName }</td>
					<td class="tableTd">${scholarshipApplyList.requestDate }</td>
					<td class="tableTd">${scholarshipApplyList.applyStatus }</td>
					<td class="tableTd">${scholarshipApplyList.approveDate }</td>
					<td class="tableTd">
						<c:if test="${not empty scholarshipApplyList.fileRefNo}">
					        <a download href="/student/scholarship/fileDownload.do?fileRefNo=${scholarshipApplyList.fileRefNo}"><i class="fas fa-download"></i> 다운로드</a>
					    </c:if>
					</td>
					<td class="tableTd">
						<c:if test="${scholarshipApplyList.applyStatus eq '반려'}">
					        <span class="badge badgeRed">${scholarshipApplyList.applyStatus}</span>
					    </c:if>
						<c:if test="${scholarshipApplyList.applyStatus eq '승인'}">
					        <span class="badge badgeGreen">${scholarshipApplyList.applyStatus}</span>
					    </c:if>
						<c:if test="${scholarshipApplyList.applyStatus eq '대기중'}">
					        <span class="badge badgeGray">${scholarshipApplyList.applyStatus}</span>
					    </c:if>
					</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
          <tr>
            <td colspan="8" class="tableTd noticeInfo">아직 신청된 장학금 목록이 존재하지 않습니다.</td>
          </tr>
        </c:otherwise>
      </c:choose>
		</tbody>
	</table>
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