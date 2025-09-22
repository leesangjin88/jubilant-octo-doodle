<%--
 * 개정이력
 * 수정일		수정자	수정내용
 * ========================================
 * 250701	김태수	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>강의 신청 등록</title>
<script src="<c:url value='/js/app/pfcp/professor/lectureForm.js'/>"></script>
<script src="${pageContext.request.contextPath}/js/axios.min.js"></script>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">  
<h2 class="sectionTitle">강의 신청서</h2>
<div class="section">
    <div class="card">
        <form method="post" action="/professor/lecture/lecturetInsertProcess.do" enctype="multipart/form-data">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="lecName" class="inputLabel">강의명</label> 
					<input type="text" id="lecName" name="lecName" placeholder="강의명을 입력하세요." class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="lecCategory" class="inputLabel">강의 분류</label>
					<select id="lecCategory" name="lecCategory" class="selectBox">
						<option value="" class="selectOption">선택하세요</option> 
					</select>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="subjectCode" class="inputLabel">강의 과목</label>
					<select id="subjectCode" name="subjectCode" class="selectBox">
                		<option value="" class="selectOption">선택하세요</option> 
					</select>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="userNo" class="inputLabel">교수번호</label>
                	<input type="text" id="userNo" name="userNo" placeholder="교수번호" class="inputField" readonly value="${userNo}">
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="preDay" class="inputLabel">희망 요일</label>
					<input type="text" id="preDay" name="preDay" placeholder="예: 월, 화, 수" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="preTime" class="inputLabel">희망 교시</label>
					<input type="text" id="preTime" name="preTime" placeholder="예: 1, 2, 3교시" class="inputField">
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="preClassrm" class="inputLabel">희망 강의실</label>
                	<select id="preClassrm" name="preClassrm" class="selectBox">
                		<option value="" class="selectOption">선택하세요</option> 
					</select>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="maxCapacity" class="inputLabel">수강정원</label>
                	<input type="text" id="maxCapacity" name="maxCapacity" placeholder="수강 정원 입력" class="inputField">
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					 <label for="fileRefNo" class="inputLabel">강의계획서 파일첨부</label>
                	<input type="file" id="fileRefNo" name="uploadFile" class="inputField">
				</div>
			</div>
            <button type="reset" class="deleteButton" style="float:right;">취소</button>
            <button type="submit" class="submitButton" style="float:right; margin-right:20px;">저장</button>
        </form>
    </div>
</div>


