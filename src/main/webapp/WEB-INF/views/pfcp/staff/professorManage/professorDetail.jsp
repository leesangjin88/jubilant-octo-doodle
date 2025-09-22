<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250701	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<title>교수 상세 조회</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 
<script src="/js/app/pfcp/staff/professorDetail.js"></script>

<h4>교수 상세 조회</h4>
<div class="section">
	<div class="card">
		<form method="post" action="/staff/professorManagement/professorUpdateProcess.do">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userNo">교번</label>
					<input class="inputField" type="text" id="userNo" name="user.userNo" value="${professor.user.userNo }" readonly>
				</div>
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userName">교수명</label>
					<input class="inputField" type="text" id="userName" name="user.userName"
						 value="${professor.user.userName }" ${professor.proStatus eq '퇴직' ? 'readonly' : ''}>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="proPosition">교수 직책</label>
					<input class="inputField" type="text" id="proPosition" name="proPosition" 
						value="${professor.proPosition }" ${professor.proStatus eq '퇴직' ? 'readonly' : ''}>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="departmentNo">학과 코드</label>
					<select id="departmentNo" name="departmentNo" class="selectBox" 
						data-init-val="${professor.department.departmentNo }" ${professor.proStatus eq '퇴직' ? 'disabled' : ''}>
						<option value="${professor.department.departmentNo }">${professor.department.departmentName }</option>
					</select>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="proHiredate">입사 일자</label>
					<input class="inputField" type="text" id="proHiredate" name="proHiredate" value="${professor.proHiredate }" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="proRdate">퇴직 일자</label>
					<input class="inputField" type="text" id="proRdate" name="proRdate" value="${professor.proRdate }" readonly>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="proStatus">교수 상태</label>
					<select id="proStatus" name="proStatus" class="selectBox" ${professor.proStatus eq '퇴직' ? 'disabled' : ''}>
						<option value="재직" ${professor.proStatus eq '재직' ? 'selected' : ''}>재직</option>
						<option value="휴직" ${professor.proStatus eq '휴직' ? 'selected' : ''}>휴직</option>
						<option value="퇴직" ${professor.proStatus eq '퇴직' ? 'selected' : ''}>퇴직</option>
					</select>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="gender">성별</label>
					<input class="inputField" type="text" id="gender" name="user.gender" value="${professor.user.gender }" readonly>
				</div>
			</div>
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userRegno1">주민등록번호 앞자리</label>
					<input class="inputField" type="text" id="userRegno1" name="user.userRegno1" value="${professor.user.userRegno1 }" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userRegno2">주민등록번호 뒷자리</label>
					<input class="inputField" type="text" id="userRegno2" name="user.userRegno2" value="${professor.user.userRegno2 }" readonly>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userTel">전화번호</label>
					<input class="inputField" type="text" id="userTel" name="user.userTel" 
						value="${professor.user.userTel }" ${professor.proStatus eq '퇴직' ? 'readonly' : ''}>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userZip">우편번호</label>
					<input class="inputField" type="text" id="userZip" name="user.userZip" 
						value="${professor.user.userZip }" ${professor.proStatus eq '퇴직' ? 'readonly' : ''}>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userAdd1">기본 주소</label>
					<input class="inputField" type="text" id="userAdd1" name="user.userAdd1" 
						value="${professor.user.userAdd1 }" ${professor.proStatus eq '퇴직' ? 'readonly' : ''}>
				</div>
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userAdd2">상세 주소</label>
					<input class="inputField" type="text" id="userAdd2" name="user.userAdd2" 
						value="${professor.user.userAdd2 }" ${professor.proStatus eq '퇴직' ? 'readonly' : ''}>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userEmail">이메일</label>
					<input class="inputField" type="text" id="userEmail" name="user.userEmail" 
						value="${professor.user.userEmail }" ${professor.proStatus eq '퇴직' ? 'readonly' : ''}>
				</div>
				
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="bankCd">은행코드</label>
					<select id="bankCd" name="user.bankCd" class="selectBox" data-init-bank="${professor.type.typeCode }" class="selectBox" ${professor.proStatus eq '퇴직' ? 'disabled' : ''}>
	                	<option value="${professor.type.typeCode }">${professor.type.typeName }</option>
	                </select>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel" for="userBankno">계좌번호</label>
					<input class="inputField" type="text" id="userBankno" name="user.userBankno" 
						value="${professor.user.userBankno }" ${professor.proStatus eq '퇴직' ? 'readonly' : ''}>
				</div>
				<div class="form-group" style="flex: 1;">
					<button class="submitButton" type="submit">저장</button>
					<button class="deleteButton" type="button" onclick="history.back()">취소</button>
				</div>
			</div>
		</form>
	</div>
</div>
