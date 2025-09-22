<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-14 	김태수	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>내 강의</title>
<%-- 기존 external CSS 링크 유지 --%>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link rel="stylesheet" href="/dist/assets/css/attendclass.css">

<style>

</style>

<div class="section">
	<div class="sectionHeaderLine">
		<div>
			<div class="sectionHeaderTitle">내 강의</div>
		</div>
	</div>

	<div class="lecture-list-container">
		<c:set var="displayedCount" value="0" />
		<c:choose>
			<c:when test="${not empty lecture }">
				<c:forEach items="${lecture }" var="lec" varStatus="status">
					<c:if test="${lec.status eq '승인'}">
						<c:url value="/professor/attendclass/attendclassDetail.do" var="detailURL">
							<c:param name="no" value="${lec.reqNo}" />
						</c:url>
						<a href="${detailURL}" class="lecture-item-link">
							<div class="lecture-item-content">
								<div class="lecture-info-group">
									<div class="lecture-icon-wrapper">
										<svg xmlns="http://www.w3.org/2000/svg" class="lecture-icon"
											fill="none" viewBox="0 0 24 24" stroke="currentColor">
											<path stroke-linecap="round" stroke-linejoin="round"
												stroke-width="2" d="M9 5l7 7-7 7" />
										</svg>
									</div>
									<div>
										<span class="lecture-name">${lec.lecName}</span>
										<p class="lecture-details">
											・ 강의 번호: ${lec.lecNo}<br>
											・ 교수: ${lec.userName}<br>
											・ 과목코드: ${lec.subjectCode} <br>
											・ 과목명: ${lec.subjectName}
											
										</p>
									</div>
								</div>
							</div>
						</a>
						<c:set var="displayedCount" value="${displayedCount + 1}" />
					</c:if>
				</c:forEach>
			</c:when>
		</c:choose>
		
		<c:if test="${displayedCount == 0}">
			<div class="no-lecture-message">
				승인된 강의 목록이 존재하지 않습니다.
			</div>
		</c:if>
	</div>
</div>