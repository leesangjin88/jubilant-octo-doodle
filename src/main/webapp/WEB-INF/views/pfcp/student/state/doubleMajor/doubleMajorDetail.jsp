<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<title>복수 전공</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 

<div class="section">
	<div>
		<h5>복수 전공</h5>
	</div>
	<div class="card">
		<form method="post" action="/student/counsel/doubleMajorUpdate.do">
			<input type="hidden" id="requestNo" name="requestNo" value="${doubleMajor.requestNo }">
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userNo">신청 일자</label>
					<input class="inputField" type="text" id="userNo" name="user.userNo" value="${doubleMajor.requestDate }" readonly>
				</div>
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userName">학번</label>
					<input class="inputField" type="text" id="userName" name="user.userName" value="${doubleMajor.userNo }" readonly>
				</div>
				<!-- <div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="requestThing">신청 내역</label>
					<input class="inputField" type="text" id="requestThing" name="requestThing" value="전공 변경" readonly>
				</div> -->
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
			<div class="form-group" style="flex: 1;">
				<label class="inputLabel" for="userName">내용</label>
				<textarea class="textareaField" name="reason" rows="" cols="">${doubleMajor.reason }</textarea>
			</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<button class="cancelButton" type="button" onclick="history.back()">목록으로</button>
					<button class="submitButton" type="submit">저장</button>
					<button class="deleteButton" type="button" onclick="location.href='/student/counsel/doubleMajorDelete.do?no=${doubleMajor.requestNo}'">삭제</button>
				</div>
				<div class="form-group" style="flex: 1;">
				</div>
			</div>
		</form>
	</div>
</div>