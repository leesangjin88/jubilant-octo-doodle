<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link rel="stylesheet" href="/dist/assets/css/mypage.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script>
	document.addEventListener('DOMContentLoaded', function() {
		let userNo = "${user.userNo}";

		const userNoTh = document.querySelector('table tr:nth-child(1) th');

		if (userNoTh) {
			if (userNo.startsWith("PR")) {
				userNoTh.textContent = "교번";
			} else if (userNo.startsWith("AD")) {
				userNoTh.textContent = "교번";
			} else if (userNo.startsWith("ST")) {
				userNoTh.textContent = "학번";
			} else {
				userNoTh.textContent = "번호";
			}
		}
	});
</script>
</head>
<body>
	<!-- Modal -->
	<%-- <div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">회원 탈퇴</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="/user/userDelete.do" method="post">
						<security:csrfInput />
						<input type="password" name="password" placeholder="비밀번호 입력" />
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary">탈퇴</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div> --%>
	
	
	<main class="container">
		<table class="table">
		
			<tr>
				<th>USER_NO</th>
				<td>${user.userNo}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${user.userName}</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>${user.gender}</td>
			</tr>
			<tr>
				<th>핸드폰</th>
				<td>${user.userTel}</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>${user.userZip}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${user.userAdd1}</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>${user.userAdd2}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${user.userEmail}</td>
			</tr>
			<tr>
				<th>은행명</th>
				<td>${user.bankCd}</td>
			</tr>
			<tr>
				<th>계좌번호</th>
				<td>${user.userBankno}</td>
			</tr>
			<tr>
				<th>test image</th>
				<td><img srt="${user.fileRefNo}" alt="프로필 사진이 존재하지 않습니다."></td>
			</tr>
		</table>
		<!-- Button trigger modal -->
		<!-- <button type="button" class="btn btn-danger" data-bs-toggle="modal"
			data-bs-target="#exampleModal">회원 탈퇴</button> -->
		<button type="button" class="deleteButton" onclick="history.back()" style="float : right">뒤로가기</button>
		<button type="button" class="submitButton" onclick="history.back()" style="float : right; margin-right: 20px;" >수정</button>
	</main>
</body>
</html>