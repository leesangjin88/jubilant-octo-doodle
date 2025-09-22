<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250710	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>성적 이의 신청</title>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<script src="/js/app/pfcp/student/studentGradeAppeal.js"></script>

<form method="post" action="/student/grade/appealInsertFormProcess.do">
	<div class="form-row" style="display: flex; gap: 20px;">
		<div class="form-group" style="flex: 1;">
			<label class="form-label" for="subjectCode">강의</label>
			<select name="subjectCode" id="subjectCode" class="selectBox" data-init-val="${lectureEnr.subject.subjectCode }">
				<option value="">강의 선택</option>
			</select>
		</div>
	
		<div class="form-group" style="flex: 1;">
			<label class="form-label" for="studentNo">학번</label>
			<input type="text" id="studentNo" name="studentNo" class="form-control" value="${studentNo}" readonly>
		</div>
	</div>
	<div class="form-group">
		<label class="inputLabel" for="counselComment">이의 신청 내용</label>
		<textarea class="textareaField" name="counselComment" rows="10" cols="" placeholder="이의 신청 내용"></textarea>
	</div>
	
	<button class="submitButton" type="submit">저장</button>
	<button class="deleteButton" type="button" onclick="history.back()">취소</button>
</form>