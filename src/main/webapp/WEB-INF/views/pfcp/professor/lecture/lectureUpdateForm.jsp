<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-10 	김태수	최초 생성 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>강의 신청 수정</title>
<script src="<c:url value='/js/app/pfcp/professor/lectureForm.js'/>"></script>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<h2 class="sectionTitle">강의 신청 수정</h2>
<div class="section">
	<div class="card">
		<form method="post" action="/professor/lecture/lectureUpdateProcess.do" enctype="multipart/form-data">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="reqNo" class="inputLabel">신청 번호</label>
					<input type="text" id="reqNo" name="reqNo" class="inputField" value="${lectureReq.reqNo}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="lecName" class="inputLabel">강의명</label>
					<input type="text" id="lecName" name="lecName" class="inputField" value="${lectureReq.lecName}" >
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="subjectCode" class="inputLabel">강의 과목</label>
				    <select id="subjectCode" name="subjectCode" class="selectBox" data-init-val="${lectureReq.subjectCode}">
				        <option value="${lectureReq.subjectCode}" class="selectOption">${lectureReq.subjectName}</option>
				        <c:forEach var="subject" items="${subjectList}">
				            <c:if test="${subject.subjectCode ne lectureReq.subjectCode}">
				                <option value="${subject.subjectCode}">${subject.subjectName}</option>
				            </c:if>
				        </c:forEach>
				    </select>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="userName" class="inputLabel">신청자(교수 이름)</label>
					<input type="text" id="userName" name="userName" class="inputField" value="${lectureReq.userName}" readonly>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="lecCategory" class="inputLabel">강의 분류</label>
					<select id="lecCategory" name="lecCategory" class="selectBox" data-init-val="${lectureReq.lecCategory}">
						<option value="${lectureReq.lecCategory}" class="selectOption">${lectureReq.lecCategory}</option> 
					</select>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="reqDate" class="inputLabel">신청일자</label>
					<input type="text" id="reqDate" name="reqDate" class="inputField" value="${lectureReq.reqDate}" readonly>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="preDay" class="inputLabel">희망 요일</label>
					<input type="text" id="preDay" name="preDay" class="inputField" value="${lectureReq.preDay}" >
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="preTime" class="inputLabel">희망 교시</label>
					<input type="text" id="preTime" name="preTime" class="inputField" value="${lectureReq.preTime}" >
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="preClassrm" class="inputLabel">희망 강의실</label>
					<select id="preClassrm" name="preClassrm" class="selectBox" data-init-val="${lectureReq.preClassrm}">
                		<option value="${lectureReq.preClassrm}" class="selectOption">${lectureReq.preClassrm}</option> 
					</select>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="maxCapacity" class="inputLabel">수강정원</label>
					<input type="text" id="maxCapacity" name="maxCapacity" class="inputField" value="${lectureReq.maxCapacity}" >
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="fileRefNo" class="inputLabel">첨부파일</label>
					<input type="file" id="fileRefNo" name="uploadFile" class="inputField">
				</div>
			</div>

            <button type="submit" class="editButton"  style="float:right;">저장</button>
            <button type="button" class="cancelButton" onclick="location.href='/professor/lecture/lectureList.do'" style="float:right; margin-right:20px;">목록</button>
		</form>
	</div>
</div>