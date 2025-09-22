<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250701	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<title>교수 수정</title>

<div>
	<div>
		<h5>교수 수정</h5>
	</div>
	<div>
		<div>
			<form method="post" action="/staff/professorManagement/professorUpdateProcess.do">
				<div>
					<label for="userNo">교번</label>
					<input type="text" id="userNo" name="user.userNo" value="${professor.user.userNo }" readonly>
				</div>
				
				<div>
					<label for="userName">교수명</label>
					<input type="text" id="userName" name="user.userName" value="${professor.user.userName }">
				</div>
				
				<div>
					<label for="proPosition">교수 직책</label>
					<input type="text" id="proPosition" name="proPosition" value="${professor.proPosition }">
				</div>
				<div>
					<label for="departmentNo">학과 코드</label>
					<input type="text" id="departmentNo" name="departmentNo" value="${professor.departmentNo }">
				</div>
				<div>
					<label for="proHiredate">입사 일자</label>
					<input type="text" id="proHiredate" name="proHiredate" value="${professor.proHiredate }" readonly>
				</div>
				<div>
					<label for="proRdate">퇴직 일자</label>
					<input type="text" id="proRdate" name="proRdate" value="${professor.proRdate }" readonly>
				</div>
				<div>
					<label for="proStatus">교수 상태</label>
					<input type="text" id="proStatus" name="proStatus" value="${professor.proStatus }" readonly>
				</div>
				
				<div>
					<label for="gender">성별</label>
					<input type="text" id="gender" name="user.gender" value="${professor.user.gender }" readonly>
				</div>
				
				<div>
					<label for="userRegno1">주민등록번호 앞자리</label>
					<input type="text" id="userRegno1" name="user.userRegno1" value="${professor.user.userRegno1 }" readonly>
				</div>
				
				<div>
					<label for="userRegno2">주민등록번호 뒷자리</label>
					<input type="text" id="userRegno2" name="user.userRegno2" value="${professor.user.userRegno2 }" readonly>
				</div>
				
				<div>
					<label for="userTel">전화번호</label>
					<input type="text" id="userTel" name="user.userTel" value="${professor.user.userTel }">
				</div>
				
				<div>
					<label for="userZip">우편번호</label>
					<input type="text" id="userZip" name="user.userZip" value="${professor.user.userZip }">
				</div>
				
				<div>
					<label for="userAdd1">기본 주소</label>
					<input type="text" id="userAdd1" name="user.userAdd1" value="${professor.user.userAdd1 }">
				</div>
				
				<div>
					<label for="userAdd2">상세 주소</label>
					<input type="text" id="userAdd2" name="user.userAdd2" value="${professor.user.userAdd2 }">
				</div>
				
				<div>
					<label for="userEmail">이메일</label>
					<input type="text" id="userEmail" name="user.userEmail" value="${professor.user.userEmail }">
				</div>
				
				<div>
					<label for="bankCd">은행코드</label>
					<input type="text" id="bankCd" name="user.bankCd" value="${professor.user.bankCd }">
				</div>
				
				<div>
					<label for="userBankno">계좌번호</label>
					<input type="text" id="userBankno" name="user.userBankno" value="${professor.user.userBankno }">
				</div>
				
				<div>
					<button type="submit">Submit</button>
					<button type="reset">Reset</button>
				</div>
			</form>
		</div>
	</div>
</div>
