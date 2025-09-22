<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>전공 변경</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<script src="/js/app/pfcp/student/studentNewMajor.js"></script>

<div class="sectionTitle">
    <h5>전공 변경 신청</h5>
</div>
<div class="section">
	<div class="card">
		<form method="post" action="/student/counsel/majorChangeInsertFormProcess.do">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="newMajorApplyCurrent">현재 전공:</label> 
					<input type="text" id="newMajorApplyCurrent" class="inputField"
						value="${student.department.departmentName}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="requestThing">희망 전공:</label> 
					<select id="requestThing" name="requestThing" class="selectBox" data-init-val="${student.department.departmentNo }">
						
					</select>
				</div>
			</div>
			
			<div class="form-group" style="flex: 1;">
				<label for="newMajorApplyReason">신청 사유:</label>
				<textarea id="newMajorApplyReason" name="reason" class="textareaField"
					placeholder="신청 사유를 입력하세요."></textarea>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
			        <button type="submit" class="submitButton">저장</button>
			        <button type="reset" class="deleteButton" onclick="history.back()">취소</button>
				</div>
				<div class="form-group" style="flex: 1;">
		        </div>
	        </div>
        </form>
	</div>
</div>