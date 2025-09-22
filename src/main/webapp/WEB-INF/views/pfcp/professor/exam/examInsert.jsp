<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250701	김태수	최초 작성
 * 250715	서경덕	내용 추가
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
    
<title>시험 등록</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<script src="/js/app/pfcp/professor/examInsert.js"></script>

<div class="sectionTitle">
    <h5>시험 등록</h5>
</div>
<div class="section">
	<div class="card">
		<form method="post" enctype="multipart/form-data" action="/professor/exam/examInsertProcess.do">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="lecNo" class="inputLabel">시험 과목</label>
					<select required name="lecNo" id="lecNo" class="selectBox" data-init-val="${exam.lecNo }">
						<option value="">선택하세요</option>
					</select>
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="examName" class="inputLabel">시험 이름</label>
				    <input required type="text" id="examName" name="examName" placeholder="시험 이름" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="examType" class="inputLabel">시험 유형</label>
				    <select required id="examType" name="examType" class="selectBox">
				    	<option value="중간고사">중간고사</option>
				    	<option value="기말고사">기말고사</option>
				    	<option value="퀴즈">퀴즈</option>
				    	<option value="발표">발표</option>
				    </select>
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="facilityNo" class="inputLabel">시험 장소(필수 X)</label>
				    <select id="facilityNo" name="facilityNo" class="selectBox" data-init-facility="${exam.facilityNo }">
				    	<option value="">선택하세요</option>
				    </select>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="userNo" class="inputLabel">시험 일자</label>
				    <input required type="date" id="examDate" name="examDate" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="examLimit" class="inputLabel">시험 제한시간</label>
				    <select required id="examLimit" name="examLimit" class="selectBox">
				    	<option value="30분">30분</option>
				    	<option value="60분">60분</option>
				    	<option value="90분">90분</option>
				    	<option value="120분">120분</option>
				    </select>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">문제지 등록</label>
					<input type="file" id="uploadFile" name="uploadFile" required>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">문제수</label>
					<input required type="number" class="questionCount" name="questionCount">
					<button class="submitButton questionCount">추가</button>
				</div>
			</div>
			
			<div class="questionField">
				
			</div>
		
			<button class="deleteButton" type="reset" style="float:right;">취소</button>
			<button class="submitButton" type="submit" style="float:right; margin-right:20px;">저장</button>
			<button class="cancelButton" type="button" onclick="history.back()" style="float:right; margin-right:20px;">목록으로</button>
				
        </form>
	</div>
</div>


