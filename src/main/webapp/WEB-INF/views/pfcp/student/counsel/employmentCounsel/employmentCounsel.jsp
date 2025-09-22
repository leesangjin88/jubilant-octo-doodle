<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250702	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<title>취업 상담</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 

<!-- ======================================== -->
<div class="sectionHeaderLine">
 	<!-- 왼쪽 제목 및 설명 -->
	<div>
		<div class="sectionHeaderTitle">취업 상담 신청 목록</div>
		<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
	</div>

	<!-- 오른쪽 등록 버튼 -->
	<button type="button" class="submitButton" onclick="location.href='/student/counsel/employmentCounsel/employmentCounselInsert.do'">+ 등록</button>
</div>
<!-- ======================================== -->
<div class="tableContainer">
	<table class="defaultTable">
		<thead class="tableHead">
			<tr>
				<th class="tableTh">번호</th>
				<th class="tableTh">상담 신청 일자</th>
				<th class="tableTh">상담 상태</th>
				<th class="tableTh">희망 상담 일자</th>
				<th class="tableTh">상담 유형</th>
			</tr>
		</thead>
		<tbody>
			<%-- 아래는 예시 코드입니다. --%>
			<%--
			<c:url value="/staff/professorManagement/professorDetail.do" var="detailURL">
				<c:param name="no" value="PR20250701"/>
			</c:url>
			<tr>
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
				<c:when test="${not empty counselList }">
					<c:forEach items="${counselList }" var="counselList" varStatus="status">
						<c:url
							value="/student/counsel/employmentCounsel/employmentCounselDetail.do"
							var="detailURL">
							<c:param name="no" value="${counselList.counselReqno }" />
						</c:url>
						<tr class="tableRowHover" data-bs-toggle="modal" data-bs-target="#${counselList.counselReqno}">
							<td class="tableTd">${status.index+1 }</td>
							<td class="tableTd">${counselList.counselReqdate }</td>
							<td class="tableTd">${counselList.type.typeName}</td>
							<td class="tableTd">${counselList.preferredDate }</td>
							<td class="tableTd">${counselList.reqType }</td>
						</tr>
						<div class="modal fade" id="${counselList.counselReqno}" tabindex="-1" aria-hidden="true">
							<div class="modal-dialog modal-dialog-scrollable">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title">상담 상세</h5>
									</div>
									
									<div class="modal-body">
										<label>담당 교수</label>
										<input type="text" id="couselProf" name="couselProf" class="form-control" value="-" readonly>
										<label>상담 내용</label>
										<input type="text" id="counselCom" name="counselCom" class="form-control" value="${counselList.counselComment }" readonly>
									</div>
									
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
										<button type="button" class="btn btn-danger deleteCounselBtn" data-no="${counselList.counselReqno}">
					                        상담 삭제
					                    </button>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6">아직 상담 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<script>
	document.addEventListener("DOMContentLoaded", () => {
	    $(".deleteCounselBtn").on("click", function() {
	        const counselReqno = $(this).data("no");
	        
	        if (confirm("정말 삭제하시겠습니까?")) {
	            location.href = '/student/counsel/departmentCounsel/departmentCounselDelete.do?no=' + counselReqno;
	        }
	    });
	});
</script>