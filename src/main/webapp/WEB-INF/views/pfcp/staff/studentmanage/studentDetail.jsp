
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%-- 
    파일명 : studentDetail.jsp
    프로그렴명 : 학생 상세 조회 화면
    설 명 : 학생 상세 정보 조회 [이름/학번/학과/[학년/학기]/계좌/이메일/전화번호/주소/생년월일/상태]
    작성자 : 양 수 민
    작성일 : 2025. 07. 01 
--%>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<title>학생 상세 정보</title>

<c:if test="${not empty success}">
  <script>
    alert("${success}");
  </script>
</c:if>

<form action="/staff/studentmanage/studentUpdateProcess.do"
	method="post">
	<c:url value="/staff/studentmanage/studentUpdate.do" var="updateURL">
		<c:param name="what" value="${student.user.userNo }" />
	</c:url>


	<h4 class="pageTitle">학생 상세 정보</h4>

	<div class="tableContainer">
		<table class="defaultTable">
			<tbody>
			
				<tr class="tableRowHover">
					<th class="tableTh">학번</th>
					<td class="tableTd">
						<input class="inputField" type="text" id="userNo" name="user.userNo" value="${student.user.userNo}" readonly />
					</td>
					<th class="tableTh">이름</th>
					<td class="tableTd">
						<input class="inputField" type="text" id="userName" name="user.userName" value="${student.user.userName}" />
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">학과</th>
					<td class="tableTd">
						<select id="departmentNo" name="departmentNo" class="selectBox">
							<option value="DP001"
								<c:if test="${student.departmentNo == 'DP001'}">selected</c:if>>컴퓨터공학과</option>
							<option value="DP002"
								<c:if test="${student.departmentNo == 'DP002'}">selected</c:if>>전자공학과</option>
							<option value="DP003"
								<c:if test="${student.departmentNo == 'DP003'}">selected</c:if>>시각디자인학과</option>
							<option value="DP004"
								<c:if test="${student.departmentNo == 'DP004'}">selected</c:if>>소프트웨어학과</option>
							<option value="DP005"
								<c:if test="${student.departmentNo == 'DP005'}">selected</c:if>>국어국문학과</option>
						</select>
					</td>
					<th class="tableTh">학년</th>
					<td class="tableTd">
						<select id="studentGrade" name="studentGrade" class="selectBox">
							<option value="1"
								<c:if test="${student.studentGrade == '1'}">selected</c:if>>1학년</option>
							<option value="2"
								<c:if test="${student.studentGrade == '2'}">selected</c:if>>2학년</option>
							<option value="3"
								<c:if test="${student.studentGrade == '3'}">selected</c:if>>3학년</option>
							<option value="4"
								<c:if test="${student.studentGrade == '4'}">selected</c:if>>4학년</option>
						</select>
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">이메일</th>
					<td class="tableTd">
						<input type="text" id="userEmail" name="user.userEmail" value="${student.user.userEmail}" class="inputField" />
					</td>
					<th class="tableTh">전화번호</th>
					<td class="tableTd">
						<input type="text" id="userTel" name="user.userTel" value="${student.user.userTel}" class="inputField" />
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">생일</th>
					<td class="tableTd">
						<input type="text" id="userRegno1" name="user.userRegno1" value="${student.user.userRegno1}" class="inputField" />
					</td>
					<th class="tableTh">학적 상태</th>
					<td class="tableTd">
						<select id="stuStatus" name="stuStatus" class="selectBox">
							<option value="재학"
								<c:if test="${student.stuStatus == '재학'}">selected</c:if>>재학</option>
							<option value="군휴학"
								<c:if test="${student.stuStatus == '군휴학'}">selected</c:if>>군휴학</option>
							<option value="일반휴학"
								<c:if test="${student.stuStatus == '일반휴학'}">selected</c:if>>일반휴학</option>
							<option value="자퇴"
								<c:if test="${student.stuStatus == '자퇴'}">selected</c:if>>자퇴</option>
							<option value="졸업"
								<c:if test="${student.stuStatus == '졸업'}">selected</c:if>>졸업</option>
							<option value="퇴학"
								<c:if test="${student.stuStatus == '퇴학'}">selected</c:if>>퇴학</option>
						</select>
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">은행</th>
					<td class="tableTd">
						<select id="bankCd" name="user.bankCd" class="selectBox">
							<option value="KB"
								<c:if test="${student.user.bankCd == 'KB'}">selected</c:if>>국민</option>
							<option value="NH"
								<c:if test="${student.user.bankCd == 'NH'}">selected</c:if>>농협</option>
							<option value="WR"
								<c:if test="${student.user.bankCd == 'WR'}">selected</c:if>>우리</option>
							<option value="SH"
								<c:if test="${student.user.bankCd == 'SH'}">selected</c:if>>신한</option>
						</select>
					</td>
					<th class="tableTh">계좌번호</th>
					<td class="tableTd">
						<input type="text" id="userBankno" name="user.userBankno" value="${student.user.userBankno}" class="inputField" />
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">우편번호</th>
					<td class="tableTd">
						<input type="text" id="sample4_postcode" class="inputField" placeholder="우편번호" style="flex: 1;" />
					</td>
					<th class="tableTd">
						<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="searchButton" style="height: 2.5rem;" />
					</th>
					<td>
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">기본주소</th>
					<td class="tableTd">
						<input type="text" id="sample4_roadAddress" name="user.userAdd1" value="${student.user.userAdd1}" class="inputField" placeholder="도로명주소" />
					</td>
					<th class="tableTh">상세주소</th>
					<td class="tableTd">
						<input type="text" id="sample4_detailAddress" name="user.userAdd2" value="${student.user.userAdd2}" class="inputField" placeholder="상세주소" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>

		
	<div style="margin-top:20px;">
		<button type="submit" class="submitButton" style="float: right;">저장</button>
		<button type="button" class="cancelButton" onclick="history.back()"
			style="float: right; margin-right: 20px;">목록으로</button>
	</div>
</form>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 도로명 주소 변수
            var roadAddr = data.roadAddress;

            // 우편번호와 도로명 주소만 필드에 넣기
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById('sample4_roadAddress').value = roadAddr;

            // 상세주소는 사용자가 직접 입력하는 필드이므로 초기화하지 않음
        }
    }).open();
}
</script>