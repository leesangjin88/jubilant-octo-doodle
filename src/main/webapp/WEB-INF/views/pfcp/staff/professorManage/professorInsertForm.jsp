<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250701	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>교수 등록</title>
<script src="<c:url value='/js/app/pfcp/staff/professorInsertForm.js'/>">
	// 스크립트 코드
</script>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 
<div class="sectionTitle">
    <h5>교수 등록</h5>
</div>
<div class="section">
	<div class="card">
		<form method="post" action="/staff/professorManagement/professorInsertProcess.do">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="userNo" class="inputLabel">교번</label>
				    <input type="text" id="userNo" name="user.userNo" value="${newUserNo }" class="inputField" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="userPass" class="inputLabel">비밀번호</label>
				    <input type="text" id="userPass" name="user.userPass" placeholder="비밀번호" class="inputField">
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="userName" class="inputLabel">이름</label>
				    <input type="text" id="userName" name="user.userName" placeholder="이름" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="proPosition" class="inputLabel">교수 직책</label>
				    <input type="text" id="proPosition" name="proPosition" placeholder="교수 직책" class="inputField">
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
			<div class="form-group" style="flex: 1;">
				<label for="departmentNo" class="inputLabel">학과</label>
				<select id="departmentNo" name="departmentNo" class="selectBox" data-init-val="${professor.department.departmentNo }">
					<option value="">학과 선택</option>
				</select>
				<span>${errors.lprodGu}</span>
			</div>
			<div class="form-group" style="flex: 1;">
			    <label for="gender" class="inputLabel">성별</label>
			    <input type="radio" class="radioButton" name="user.gender" value="M">남자	    
			    <input type="radio" class="radioButton" name="user.gender" value="W" style="margin-left:15px;">여자
			</div>
			
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="userRegno1" class="inputLabel">주민등록번호 앞자리</label>
				    <input type="text" id="userRegno1" name="user.userRegno1" placeholder="주민등록번호 앞자리" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="userRegno2" class="inputLabel">주민등록번호 뒷자리</label>
				    <input type="text" id="userRegno2" name="user.userRegno2" placeholder="주민등록번호 뒷자리" class="inputField">
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="userTel" class="inputLabel">전화번호</label>
				    <input type="text" id="userTel" name="user.userTel" placeholder="전화번호" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="userZip" class="inputLabel">우편번호</label>
				    <input type="text" id="userZip" name="user.userZip" placeholder="우편번호" class="inputField">
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="userAdd1" class="inputLabel">기본주소</label>
				    <input type="text" id="userAdd1" name="user.userAdd1" placeholder="기본주소" class="inputField">
				</div>
				<div class="form-group" style="flex: 1;">
				    <label for="userAdd2" class="inputLabel">상세주소</label>
				    <input type="text" id="userAdd2" name="user.userAdd2" placeholder="상세주소" class="inputField">
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="userEmail" class="inputLabel">이메일</label>
				    <input type="text" id="userEmail" name="user.userEmail" placeholder="이메일" class="inputField">
				</div>
	            <div class="form-group" style="flex: 1;">
	                <label for="bankCd" class="inputLabel">은행명</label>
	                <select id="bankCd" name="user.bankCd" class="selectBox" data-init-bank="${professor.type.typeCode }">
	                	<option value="">선택하세요</option>
	                </select>
	            </div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
	            <div class="form-group" style="flex: 1;">
	                <label for="userBankno" class="inputLabel">계좌번호</label>
	                <input type="text" id="userBankno" name="user.userBankno" placeholder="계좌번호" class="inputField">
	            </div>
	            <div class="form-group" style="flex: 1;">
	            </div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				</div>
				<div class="form-group" style="flex: 1;">
			        <button type="submit" class="submitButton">저장</button>
			        <button type="reset" class="deleteButton" onclick="history.back()">취소</button>
		        </div>
	        </div>
        </form>
	</div>
</div>