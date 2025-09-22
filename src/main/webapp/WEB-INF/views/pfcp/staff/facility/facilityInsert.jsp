<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%--
    파일명 : facilityInsert.jsp
    프로그렴명 : 시설 등록 화면
    설 명 : 새로운 시설 정보를 등록할 수 있음.
    작성자 : 양 수 민 (기존 페이지 기반)
    작성일 : 2025. 07. 02
--%>	


  <title>시설 등록 - LCMS 관리자</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

  <div class="max-w-4xl mx-auto bg-white rounded-xl shadow p-6">
    <div class="flex justify-between items-center mb-6">
      <a href="/staff/facility/facilityList.do" class="text-blue-600 hover:underline text-sm">← 목록으로 돌아가기</a>
		<div>
			<form method="post" action="/staff/facility/facilityInsertProcess.do" class="inline-block">

                
                <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">등록</button>
                <a href="/staff/facility/facilityList.do" class="ml-2 text-gray-600 hover:text-red-500">취소</a>
		</div>
	</div>

    <div class="grfacilityNo grfacilityNo-cols-1 md:grfacilityNo-cols-2 gap-6">
      <div>
        <h3 class="text-lg font-semibold mb-2">🏢 기본 정보</h3>
        
        <%-- 등록 시에는 facilityNo를 입력받지 않습니다. (자동 생성) --%>

        <label class="block mt-4 mb-2 text-sm" for="facilityName">시설명</label>
        <input id="facilityName" name="facilityName" value="" required class="w-full border px-3 py-2 rounded" placeholder="시설명을 입력하세요." />

        <label class="block mt-4 mb-2 text-sm" for="facilityType">시설 유형</label>
        <input id="facilityType" name="facilityType" value="" required class="w-full border px-3 py-2 rounded" placeholder="예: 강의실, 실습실, 체육관" />

        <label class="block mt-4 mb-2 text-sm" for="location">위치</label>
        <input id="location" name="location" value="" required class="w-full border px-3 py-2 rounded" placeholder="예: 본관 301호" />

        <label class="block mt-4 mb-2 text-sm" for="facilityMp">수용 인원</label>
        <input id="facilityMp" name="facilityMp" type="number" value="0" min="0" required class="w-full border px-3 py-2 rounded" placeholder="숫자만 입력하세요." />
        
        <label class="block mt-4 mb-2 text-sm" for="facilityStatus">시설물 상태</label>
        <%-- 시설물 상태는 기본값을 '사용가능' 등으로 설정하거나, 드롭다운으로 선택하게 할 수 있습니다. --%>
        <input id="facilityStatus" name="facilityStatus" value="사용가능" required class="w-full border px-3 py-2 rounded" placeholder="예: 사용가능, 점검중, 폐쇄" />
        
      </div>
      
    </div>
   </div>
  </form>