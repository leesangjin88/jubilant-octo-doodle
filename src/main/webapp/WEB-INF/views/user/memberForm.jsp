<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<body>
	<div class="card">
		<div class="card-header">
			<h5>회원가입</h5>
		</div>
		<div class="card-body">
			<div class="row">
				<form:form modelAttribute="member" method="post">

					<div class="form-group mb-3">
						<form:label path="memId" cssClass="form-label">회원번호</form:label>
						<form:input path="memId" cssClass="form-control"
							placeholder="회원번호" />
						<form:errors path="memId" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memPassword" cssClass="form-label">비밀번호</form:label>
						<form:password path="memPassword" cssClass="form-control"
							placeholder="비밀번호" />
						<form:errors path="memPassword" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memName" cssClass="form-label">회원이름</form:label>
						<form:input path="memName" cssClass="form-control"
							placeholder="회원이름" />
						<form:errors path="memName" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memRegno1" cssClass="form-label">주민등록번호 앞 6자리</form:label>
						<form:input path="memRegno1" cssClass="form-control"
							placeholder="주민등록번호 앞 6자리" maxlength="6" />
						<form:errors path="memRegno1" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memRegno2" cssClass="form-label">주민등록번호 뒤 7자리</form:label>
						<form:input path="memRegno2" cssClass="form-control"
							placeholder="주민등록번호 뒤 7자리" maxlength="7" />
						<form:errors path="memRegno2" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memBir" cssClass="form-label">생년월일</form:label>
						<form:input path="memBir" cssClass="form-control"
							placeholder="생년월일" type="datetime-local" />
						<form:errors path="memBir" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memZip" cssClass="form-label">우편번호</form:label>
						<form:input path="memZip" cssClass="form-control"
							placeholder="우편번호" />
						<form:errors path="memZip" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memAdd1" cssClass="form-label">기본주소</form:label>
						<form:input path="memAdd1" cssClass="form-control"
							placeholder="기본주소" />
						<form:errors path="memAdd1" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memAdd2" cssClass="form-label">상세주소</form:label>
						<form:input path="memAdd2" cssClass="form-control"
							placeholder="상세주소" />
						<form:errors path="memAdd2" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memHometel" cssClass="form-label">집전화번호</form:label>
						<form:input path="memHometel" cssClass="form-control"
							placeholder="집전화번호" type="tel" />
						<form:errors path="memHometel" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memComtel" cssClass="form-label">회사전화번호</form:label>
						<form:input path="memComtel" cssClass="form-control"
							placeholder="회사전화번호" type="tel" />
						<form:errors path="memComtel" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memHp" cssClass="form-label">핸드폰번호</form:label>
						<form:input path="memHp" cssClass="form-control"
							placeholder="핸드폰번호" type="tel" />
						<form:errors path="memHp" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memMail" cssClass="form-label">메일주소</form:label>
						<form:input path="memMail" cssClass="form-control"
							placeholder="메일주소" type="email" />
						<form:errors path="memMail" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memJob" cssClass="form-label">직업</form:label>
						<form:input path="memJob" cssClass="form-control" placeholder="직업" />
						<form:errors path="memJob" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memHobby" cssClass="form-label">취미</form:label>
						<form:input path="memHobby" cssClass="form-control"
							placeholder="취미" />
						<form:errors path="memHobby" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memMemorial" cssClass="form-label">기념일종류</form:label>
						<form:input path="memMemorial" cssClass="form-control"
							placeholder="기념일종류" />
						<form:errors path="memMemorial" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3">
						<form:label path="memMemorialday" cssClass="form-label">기념일</form:label>
						<form:input path="memMemorialday" cssClass="form-control"
							placeholder="기념일" type="date" />
						<form:errors path="memMemorialday" cssClass="text-danger" />
					</div>

					<div class="form-group mb-3 text-center">
						<button type="submit" class="btn btn-primary">저장</button>
						<button type="button" class="btn btn-secondary"
							onclick="history.back()">취소</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>

</html>