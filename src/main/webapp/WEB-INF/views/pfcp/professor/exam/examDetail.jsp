<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>시험 상세</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<div class="sectionTitle">
    <h5>${exam.examName } 시험 상세</h5>
</div>
<div class="section">
	<div class="card">
		<form method="post" enctype="multipart/form-data" action="/professor/exam/examUpdateProcess.do">
			<input type="hidden" name="examNo" value="${exam.examNo}">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="lecNo" class="inputLabel">시험 과목</label>
					<select name="lecNo" id="lecNo" class="selectBox" data-init-val="${exam.lecNo }">
						<option value="${exam.lecNo }">${exam.lectureReq.lecName }</option>
					</select>
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="examName" class="inputLabel">시험 이름</label>
				    <input type="text" id="examName" name="examName" value="${exam.examName }" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="examType" class="inputLabel">시험 유형</label>
				    <select id="examType" name="examType" class="selectBox">
				    	<option value="중간고사">중간고사</option>
				    	<option value="기말고사">기말고사</option>
				    	<option value="퀴즈">퀴즈</option>
				    	<option value="발표">발표</option>
				    </select>
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="facilityNo" class="inputLabel">시험 장소</label>
				    <select id="facilityNo" name="facilityNo" class="selectBox" data-init-facility="${exam.facilityNo }">
				    	<option value="${exam.facilityNo }">${exam.facility.facilityName }</option>
				    </select>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="userNo" class="inputLabel">시험 일자</label>
				    <input type="date" id="examDate" name="examDate" value="${exam.examDate }" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="examLimit" class="inputLabel">시험 제한시간</label>
				    <select id="examLimit" name="examLimit" class="selectBox">
				    	<option value="30분">30분</option>
				    	<option value="60분">60분</option>
				    	<option value="90분">90분</option>
				    	<option value="120분">120분</option>
				    </select>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">문제지</label>
					<p>현재 업로드된 파일: ${exam.atchFile.atchOriginName}</p>
					<input type="file" id="uploadFile" name="uploadFile">
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">문제수</label>
					<input type="number" class="questionCount" name="questionCount">
					<button class="submitButton questionCount">추가</button>
				</div>
			</div>
			
			<div class="questionField">
				<c:forEach var="question" items="${exam.questionAnswerList}" varStatus="status">
					<div class="question-form" style="margin-bottom: 20px;">
						<p>문제 ${status.index + 1} 정답 선택:</p>
						<c:forEach begin="1" end="4" var="num">
				            <label>
				                <input type="radio" name="answerList[${status.index}]" 
				                	value="${num}" <c:if test="${question.answer eq num}">checked</c:if>>
				                ${num}번
				            </label>&nbsp;&nbsp;
				        </c:forEach>
				        <p>배점 : <input type="text" name="scoreList[${status.index}]" value="${question.questionScore }"></p>
					</div>
				</c:forEach>
			</div>
			
			<div class="form-group" style="flex: 1;">
					<button class="cancelButton" type="button" onclick="history.back()">목록으로</button>
					<button class="submitButton" type="submit">저장</button>
					<button class="deleteButton" type="button" onclick="location.href='/professor/exam/examDelete.do?examNo=${exam.examNo}'">삭제</button>
				</div>
        </form>
	</div>
</div>