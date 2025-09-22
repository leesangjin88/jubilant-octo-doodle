<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
    파일명 : studentInsert.jsp
    프로그렴명 : 학생 등록 화면
    설 명 : 학생 등록[이름/학번/학과/[학년/학기]/계좌/이메일/전화번호/주소/생년월일/상태]
    작성자 : 양 수 민
    작성일 : 2025. 07. 01
--%>
    
<title>학생 등록</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">


  <div class="max-w-4xl mx-auto bg-white rounded-xl shadow p-6">

    <form method="post" action="/staff/studentmanage/studentInsertProcess.do">
      <a href="studentList.jsp" class="text-blue-600 hover:underline text-sm">← 목록으로 돌아가기</a>
      <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">저장</button>
    
    	<table class="defaultTable">
    		<tr>
				<th class="tableTh">학번</th>
				<td class="tableTd">
					<input class="inputField" type="text" id="userNo" name="user.userNo" value="자동으로 부여됩니다" readonly />
				</td>
				<th class="tableTh">이름</th>
				<td class="tableTd">
					<input class="inputField" type="text" id="userName" name="user.userName" required />
				</td>
			</tr>
    	</table>
        <!-- 기본 정보 -->
        <div>
          <label class="block mb-2 text-sm">이름</label>
          <input name="user.userName" required class="w-full border px-3 py-2 rounded" value="이학범"/>

<!--           <label class="block mt-4 mb-2 text-sm">학번</label> -->
<!--           <input name="userNo" required class="w-full border px-3 py-2 rounded" value="ST03030303"/> -->

          <label class="block mt-4 mb-2 text-sm">학과</label>
          <select name="departmentNo" required class="w-half border px-3 py-2 rounded">
            <option value="DP001" selected>컴퓨터공학과</option>
            <option value="DP002">전자공학과</option>
            <option value="DP003">시각디자인학과</option>
            <option value="DP004">소프트웨어학과</option>
            <option value="DP005">국어국문학과</option>
          </select>

          <label class="block mt-4 mb-2 text-sm">학년</label>
          <select name="studentGrade" required class="w-half border px-3 py-2 rounded">
            <option value="1">1학년</option>
            <option value="2" selected>2학년</option>
            <option value="3">3학년</option>
            <option value="4">4학년</option>
          </select>

          <label class="block mt-4 mb-2 text-sm">계좌</label>
		<div class="flex gap-2">
		  <select name="user.bankCd" required class="border px-1 py-2 rounded ">
		    <option value="KB">국민</option>
		    <option value="NH">농협</option>
		    <option value="WR" selected>우리</option>
		    <option value="SH">신한</option>
		  </select>
		  <input name="user.bankNo" step="0.01" type="number" class="flex-1 border px-3 py-2 rounded" placeholder="'-'없이 숫자만 입력해주세요" value="23456778912364"/>
		</div>

        </div>

        <div>
          <h3 class="text-lg font-semibold mb-2">📧 연락처 정보</h3>
          <label class="block mb-2 text-sm">이메일</label>
          <input name="user.userEmail" type="email" class="w-full border px-3 py-2 rounded" value="hak@gmail.com"/>

          <label class="block mt-4 mb-2 text-sm">전화번호</label>
          <input name="user.userTel" class="w-full border px-3 py-2 rounded" value="010-3333-3333" />

          <label class="block mt-4 mb-2 text-sm">주소</label>
          <input name="user.userAdd1" class="w-full border px-3 py-2 rounded" value="대전광역시 중구 선화동"/>
          
          <label class="block mt-4 mb-2 text-sm">상세 주소</label>
          <input name="user.userAdd2" class="w-full border px-3 py-2 rounded" value="개나리아파트 304동 601호"/>

          <label class="block mt-4 mb-2 text-sm">생년월일</label>
          <input name="user.userRegno1" type="number" class="w-full border px-3 py-2 rounded" placeholder="ex) 070412" value="070412"/>


<!--           <label class="block mt-4 mb-2 text-sm">상태</label> -->
<!--           <select name="status" required class="w-full border px-3 py-2 rounded"> -->
<!--             <option value="재학">재학</option> -->
<!--             <option value="휴학">휴학</option> -->
<!--             <option value="졸업">졸업</option> -->
<!--           </select> -->
        </div>
    </form>
