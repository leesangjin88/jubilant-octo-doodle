<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%--
    파일명 : studentInsert.jsp
    프로그렴명 : 학생 등록 화면
    설 명 : 학생 등록[이름/학번/학과/[학년/학기]/계좌/이메일/전화번호/주소/생년월일/상태]
    작성자 : 양 수 민
    작성일 : 2025. 07. 01
--%>

<title>학생 등록</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<c:if test="${not empty success}">
  <script>
    alert("${success}");
  </script>
</c:if>

<form method="post" action="/staff/studentmanage/studentInsertProcess.do">
<h4 class="pageTitle">학생 상세 정보</h4>

	<div class="tableContainer">	

		<table class="defaultTable">
			<tbody>
				<tr class="tableRowHover">
					<th class="tableTh">이름</th>
					<td class="tableTd">
						<input class="inputField" type="text" id="userName" name="user.userName" required />
					</td>
					<th class="tableTh">성별</th>
					<td class="tableTd">
						<input class="inputField" type="text" id="gender" name="user.gender" placeholder="W or M"/>
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">학과</th>
					<td class="tableTd">
						<select id="departmentNo" name="departmentNo" class="selectBox">
							<option value="DP001">컴퓨터공학과</option>
							<option value="DP002">전자공학과</option>
							<option value="DP003">시각디자인학과</option>
							<option value="DP004">소프트웨어학과</option>
							<option value="DP005">국어국문학과</option>
					</select></td>
					<th class="tableTh">학년</th>
					<td class="tableTd"><select id="studentGrade"
						name="studentGrade" class="selectBox">
							<option value="1">1학년</option>
							<option value="2">2학년</option>
							<option value="3">3학년</option>
							<option value="4">4학년</option>
					</select></td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">이메일</th>
					<td class="tableTd">
						<input type="text" id="userEmail" name="user.userEmail" class="inputField" placeholder="ex) aaa@gmail.com" />
					</td>
					<th class="tableTh">전화번호</th>
					<td class="tableTd">
						<input type="text" id="userTel" name="user.userTel" class="inputField" placeholder="ex) 000-0000-0000"/>
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">생일</th>
					<td class="tableTd">
						<input type="text" id="userRegno1" name="user.userRegno1" class="inputField" placeholder="ex) 070412"/>
					</td>
					<th class="tableTh">학적 상태</th>
					<td class="tableTd">
						<select id="stuStatus" name="stuStatus" class="selectBox">
							<option value="재학">재학</option>
							<option value="군휴학">군휴학</option>
							<option value="일반휴학">일반휴학</option>
							<option value="자퇴">자퇴</option>
							<option value="졸업">졸업</option>
							<option value="퇴학">퇴학</option>
						</select>
					</td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">은행</th>
					<td class="tableTd">
						<select id="bankCd" name="user.bankCd" class="selectBox">
							<option value="KB">국민</option>
							<option value="NH">농협</option>
							<option value="WR">우리</option>
							<option value="SH">신한</option>
					</select></td>
					<th class="tableTh">계좌번호</th>
					<td class="tableTd">
						<input type="text" id="userBankno" name="user.userBankno" class="inputField" placeholder="ex) 000-00-000-00000"/>
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
					<td></td>
				</tr>
				<tr class="tableRowHover">
					<th class="tableTh">기본주소</th>
					<td class="tableTd">
						<input type="text" id="sample4_roadAddress" name="user.userAdd1" class="inputField" placeholder="도로명주소" />
					</td>
					<th class="tableTh">상세주소</th>
					<td class="tableTd">
						<input type="text" id="sample4_detailAddress" name="user.userAdd2"  class="inputField" placeholder="상세주소" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div style="margin-top:20px;">
		<button type="submit" class="submitButton" style="float: right;">저장</button>
		<button type="button" class="cancelButton" onclick="history.back()"
			style="float: right; margin-right: 20px;">취소</button>
	</div>
	
<!-- 	<button type="submit" class="submitButton" style="float:right;">저장</button> -->
<!-- 	<button onclick="history.back()" class="cancelButton" style="float:right; ">취소</button> -->
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
