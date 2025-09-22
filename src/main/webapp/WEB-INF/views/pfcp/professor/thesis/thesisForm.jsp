<%--
 * 개정이력
 * 수정일		수정자	수정내용
 * ========================================
 * 250709	김태수	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>논문 등록</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">  
<h2 class="sectionTitle">논문 등록</h2>
<div class="section">
    <div class="card">
        <form method="post" action="/professor/thesis/thesisInsertProcess.do" enctype="multipart/form-data">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="thesisTitle" class="inputLabel">제목</label> 
					<input type="text" id="thesisTitle" name="thesisTitle" placeholder="논문 제목을 입력하세요." class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="userNo" class="inputLabel">교수번호</label>
                	<input type="text" id="userNo" name="userNo" placeholder="교수번호" class="inputField" readonly value="${userNo}">
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					 <label for="fileRefNo" class="inputLabel">논문 첨부</label>
                	<input type="file" id="uploadFile" name="uploadFile" class="inputField">
				</div>
			</div>
            <button type="reset" class="deleteButton" style="float:right;">취소</button>
            <button type="submit" class="submitButton" style="float:right; margin-right:20px;">저장</button>
        </form>
    </div>
</div>