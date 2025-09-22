<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250708	양수민	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> --%>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<title>강의평가 문항 등록</title>

<c:if test="${not empty msg}">
<script>
    alert('${msg}');
</script>
</c:if>
<form method="post" action="/staff/lectureEvaluation/lectureEvalutaionFormUpdateProcess.do">
<div class="form-section">
	<!-- ======================================== -->
	<div class="sectionHeaderLine">
	  <div>
	    <div class="sectionHeaderTitle">강의평가 질문 목록</div>
	  </div>
	  
	  <button type="submit" class="submitButton">저장</button>
	</div>
	<!-- ======================================== -->

	<c:choose>
		<c:when test="${not empty lectureEvaluation}">
			<div class="card" style="padding: 1.25rem; margin-top: 1rem;">
				<c:forEach items="${lectureEvaluation}" var="lectureEvaluation" varStatus="status">
					<div class="question-item" data-criteria-no="${lectureEvaluation.criteriaNo }" data-criteria-desc="${lectureEvaluation.criteriaDesc }" style="display: flex; align-items: center; gap: 1rem; margin-bottom: 1rem;">
						<label class="inputLabel" style="width:50px; text-align:center;">${status.index+1 }</label>
						<label class="inputLabel" style="width: 200px; margin-left: 60px;">${lectureEvaluation.criteriaName}</label>
						<input type="hidden" name="criteriaNoList[${vs.index}]" value="${lectureEvaluation.criteriaNo}" />
						<input type="text" name="criteriaDescList[${vs.index}]" value="${lectureEvaluation.criteriaDesc}" class="inputField" style="flex: 1;">
						
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<div class="noticeBox noticeInfo" style="text-align: center;">
				아직 강의 평가 목록 없음.</div>
		</c:otherwise>
	</c:choose>
</form>


