<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250705	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>Insert title here</title>
<div>
	<h4>강의 평가</h4>
	
	<c:forEach items="${lectureList }" var="lecture">
		<a href="/student/lectureEvaluation.do?enrollNo=${lecture.enrollNo}">${lecture.subject.subjectName }</a>
	</c:forEach>
</div>