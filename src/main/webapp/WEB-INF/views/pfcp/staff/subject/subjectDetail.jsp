<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<title>강의 신청 상세</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<h4>강의 신청 상세</h4>
<div class="section">
	<div class="card">
		<form method="post" action="/staff/subject/subjectAccept.do">
			<div class="form-row" style="display: flex; gap: 20px;">
				<input type="hidden" id="reqNo" name="reqNo" value="${lectureReq.reqNo }">
				<input type="hidden" id="userNo" name="userNo" value="${lectureReq.userNo }">
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userName">교수명</label>
					<input class="inputField" type="text" id="userName" name="userName" value="${lectureReq.userName }" readonly>
				</div>
				
				<div class="form-group" style="flex: 1;">
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="subjectName">과목명</label>
					<input class="inputField" type="text" id="subjectName" name="subjectName" value="${lectureReq.subjectName }" readonly>
				</div>
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="lecName">강의명</label>
					<input class="inputField" type="text" id="lecName" name="lecName" value="${lectureReq.lecName }" readonly>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="preDay">희망 요일</label>
					<input class="inputField" type="text" id="preDay" name="preDay" value="${lectureReq.preDay }" readonly>
				</div>
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="preTime">희망 교시</label>
					<input class="inputField" type="text" id="preTime" name="preTime" value="${lectureReq.preTime }" readonly>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="preDay">희망 강의실</label>
					<input type="hidden" id="lrNo" name="lrNo" value="${lectureReq.lrNo }">
					<input class="inputField" type="text" id="preClassrm" name="preClassrm" value="${lectureReq.preClassrm }" readonly>
				</div>
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="reqDate">신청 일자</label>
					<input class="inputField" type="text" id="reqDate" name="reqDate" value="${lectureReq.reqDate }" readonly>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="lecCategory">강의 분류</label>
					<c:if test="${lectureReq.lecCategory eq 'MJ_REQ' }">
						<input class="inputField" type="text" id="lecCategory" name="lecCategory" value="전공 필수" readonly>
					</c:if>
					<c:if test="${lectureReq.lecCategory eq 'MJ_SEL' }">
						<input class="inputField" type="text" id="lecCategory" name="lecCategory" value="전공 선택" readonly>
					</c:if>
					
				</div>
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="fileRefNo">강의 계획서</label>
					<c:if test="${not empty lectureReq.fileRefNo}">
						<a href="/staff/subject/fileDownload.do?fileRefNo=${lectureReq.fileRefNo}" download class="download-button"> <i class="fas fa-download"></i> ${lectureReq.atchFile.atchOriginName}</a>
						
					</c:if>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="rejReason">반려 사유</label>
					<textarea class="textareaField" name="rejReason" rows="" cols=""></textarea>
				</div>
				
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<button class="submitButton" type="submit">승인</button>
					<button class="cancelButton" type="submit" formaction="/staff/subject/subjectReturn.do">반려</button>
					<button class="deleteButton" type="button" onclick="history.back()">취소</button>
				</div>
			</div>
		</form>
	</div>
</div>