<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250702	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Insert title here</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<form method="post" action="/student/counsel/departmentCounsel/departmentCounselInsertProcess.do">
	<div class="form-row" style="display: flex; gap: 20px;">
		<div class="form-group" style="flex: 1;">
			<label class="form-label" for="studentNo">학번</label>
			<input type="text" id="studentNo" name="studentNo" class="form-control" value="${studentNo}" readonly>
		</div>
		<div class="form-group" style="flex: 1;">
			<label class="form-label" for="preferredDate">희망 상담 일자</label>
			<input type="datetime-local" id="preferredDate" name="preferredDate" class="form-control" placeholder="희망 상담 일자">
		</div>
	</div>
	
	<label hidden class="form-label" for="typeCode">유형 코드</label>
	<input type="hidden" id="typeCode" name="typeCode" class="form-control" value="CN_DEPT" readonly>
		
	
	<div class="form-group">
		<label class="form-label" for="reqType">상담 유형</label>
		<select name="reqType" id="reqType" class="selectBox">
			<option value="">선택하시오</option>
			<option value="대면">대면상담</option>
			<option value="온라인">온라인상담</option>
		</select>
	</div>
	
	<div class="form-group">
		<label class="form-label" for="counselComment">상담 내용</label>
		<textarea class="textareaField" name="counselComment" rows="" cols=""></textarea>
	</div>
	
	<button class="submitButton" type="submit">저장</button>
	<button class="deleteButton" type="button" onclick="history.back()">취소</button>
</form>