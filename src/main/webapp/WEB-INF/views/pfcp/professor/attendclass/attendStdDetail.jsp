<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-15 	김태수	최초 생성 
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>    
<title>학생 상세 정보</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<h2 class="sectionTitle">학생 상세 정보</h2>
<div class="section">
	<div class="card">
		    <c:choose>
		        <c:when test="${not empty stdDetail}">
		        <div  class="form-row" style="display: flex; gap: 20px;">
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">학번</label>
		        		<input type="text" id="userNo" name="userNo" class="inputField" value="${stdDetail.userNo}" readonly>
		        	</div>
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">이름</label>
		        		<input type="text" id="userName" name="userName" class="inputField" value="${stdDetail.userName}" readonly>
		        	</div>
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">성별</label>
		        		<input type="text" id="gender" name="gender" class="inputField" value="${stdDetail.gender}" readonly>
		        	</div>
		        </div>
		        <br>
		        <div  class="form-row" style="display: flex; gap: 20px;">
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">전화번호</label>
		        		<input type="text" id="userTel" name="userTel" class="inputField" value="${stdDetail.userTel}" readonly>
		        	</div>
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">이메일</label>
		        		<input type="text" id="userEmail" name="userEmail" class="inputField" value="${stdDetail.userEmail}" readonly>
		        	</div>
		        </div>
		        <br>
		        <div  class="form-row" style="display: flex; gap: 20px;">
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">생년월일</label>
		        		<input type="text" id="userRegno1" name="userRegno1" class="inputField" value="${stdDetail.userRegno1}" readonly>
		        	</div>
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">주소</label>
		        		<input type="text" id="userAdd1" name="userAdd1" class="inputField" value="${stdDetail.userAdd1}" readonly>
		        	</div>
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">상세주소</label>
		        		<input type="text" id="userAdd2" name="userAdd2" class="inputField" value="${stdDetail.userAdd2}" readonly>
		        	</div>
		        </div>
		        </c:when>
		    </c:choose>
	</div>	
<br>
<h2 class="sectionTitle">소속</h2>
<div class="section">
	<div class="card">
		    <c:choose>
		        <c:when test="${not empty stdDetail}">
		        <div  class="form-row" style="display: flex; gap: 20px;">
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">소속 단과대학</label>
		        		<input type="text" id="collegeName" name="collegeName" class="inputField" value="${stdDetail.college.collegeName}" readonly>
		        	</div>
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">소속 학과</label>
		        		<input type="text" id="departmentName" name="departmentName" class="inputField" value="${stdDetail.department.departmentName}" readonly>
		        	</div>
		        	<div class="from-group" style="flex: 1;">
		        		<label class="inputLabel">학년</label>
		        		<input type="text" id="studentGrade" name="studentGrade" class="inputField" value="${stdDetail.studentGrade}" readonly>
		        	</div>
		        </div>
		        </c:when>
		    </c:choose>
	</div>
</div>
<button type="button" class="cancelButton" onclick="history.back()"  style="float:right; margin-right:20px;">목록</button>