<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- alert 창 띄우기. message 지우기 -->
	<c:if test="${not empty message }">
		<script>
			alert("${message}")
		</script>
		<c:remove var="message" scope="session" />
	</c:if>
	<form:form method="post" id="loginForm">
		<div class="card my-5">
			<div class="card-body">
				<div class="d-flex justify-content-between align-items-end mb-4">
					<h3 class="mb-0">
						<b>로그인</b>
					</h3>
				</div>
				<div class="form-group mb-3">
					<label class="form-label">아이디</label> <input type="text"
						class="form-control" placeholder="학번(교번)" name="username"
						>
				</div>
				<div class="form-group mb-3">
					<label class="form-label">비밀번호</label> <input type="password"
						class="form-control" placeholder="비밀번호" name="password"
						>
				</div>
				<div class="d-flex mt-1 justify-content-between">
					<div class="form-check">
						<input class="form-check-input input-primary" type="checkbox"
							id="customCheckc1" checked=""> <label
							class="form-check-label text-muted" for="customCheckc1">로그인
							저장</label>
					</div>
				</div>
				<div class="d-grid mt-4">
					<button type="submit" class="btn btn-primary">로그인</button>
				</div>
				
				<div class="d-flex justify-content-end mt-3">
					<p class="form-check-label text-muted mb-0 find-link me-3"
						id="findIdBtn"
						style="cursor: pointer; text-decoration: underline;"
						data-bs-toggle="modal" data-bs-target="#findIdModal">아이디 찾기</p>
					<p class="form-check-label text-muted mb-0"
						style="cursor: pointer; text-decoration: underline;"
						data-bs-toggle="modal" data-bs-target="#findPasswordModal">비밀번호 찾기</p>
				</div>
			</div>
		</div>
	</form:form>
	<p>임시 바로 로그인</p>
				<input type="radio" id="studentLogin"/><label for="studentLogin">학생</label>
				<input type="radio" id="professorLogin"/><label for="professorLogin">교수</label>
				<input type="radio" id="staffLogin"/><label for="staffLogin">교직원</label>
	<script>
	loginForm.addEventListener("submit", (e)=>{
		e.preventDefault();
		const json = axios.formToJSON(e.target);
		console.log(json);
		axios.post("/common/auth", json, {
			withCredentials:true
		}).then(resp=>location.href="/");
	});
	studentLogin.addEventListener("click", (e)=>{
		e.preventDefault();
		const json = {
				password: "java",
				username: "ST20220810"
		}
		console.log(json);
		axios.post("/common/auth", json, {
			withCredentials:true
		}).then(resp=>location.href="/"); 
	})
	professorLogin.addEventListener("click", (e)=>{
		e.preventDefault();
		const json = {
				password: "java",
				username: "PR20250701"
		}
		console.log(json);
		axios.post("/common/auth", json, {
			withCredentials:true
		}).then(resp=>location.href="/"); 
	})
	staffLogin.addEventListener("click", (e)=>{
		e.preventDefault();
		const json = {
				password: "java",
				username: "AD00000001"
		}
		console.log(json);
		axios.post("/common/auth", json, {
			withCredentials:true
		}).then(resp=>location.href="/"); 
	})
	</script>
</body>
</html>