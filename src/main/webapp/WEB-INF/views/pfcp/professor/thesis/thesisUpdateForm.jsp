<%--
 * 개정이력
 * 수정일		수정자	수정내용
 * ========================================
 * 250711	김태수	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>논문 수정</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">  
<h2 class="sectionTitle">논문 수정</h2>
<div class="section">
    <div class="card">
        <form method="post" action="/professor/thesis/thesisUpdateProcess.do" enctype="multipart/form-data">
        <div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="thesisTitle" class="inputLabel">제목</label> 
					<input type="text" id="thesisTitle" name="thesisTitle" value="${thesis.thesisTitle}" class="inputField">
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="thesisNo" class="inputLabel">논문 번호</label> 
					<input type="text" id="thesisNo" name="thesisNo" value="${thesis.thesisNo}" class="inputField" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="userNo" class="inputLabel">교수번호</label>
                	<input type="text" id="userNo" name="userNo" placeholder="교수번호" class="inputField" readonly value="${thesis.userNo}">
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="submitDate" class="inputLabel">신청일자</label> 
					<input type="text" id="submitDate" name="submitDate" value="${thesis.submitDate}" class="inputField" readonly>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					 <label for="fileRefNo" class="inputLabel">논문 첨부</label>
                	<input type="file" id="uploadFile" name="uploadFile" class="inputField">
				</div>
			</div>
            <button type="submit" class="editButton"  style="float:right;">저장</button>
            <button type="button" class="cancelButton" onclick="location.href='/professor/thesis/thesisList.do'" style="float:right; margin-right:20px;">목록</button>
        </form>
    </div>
</div>