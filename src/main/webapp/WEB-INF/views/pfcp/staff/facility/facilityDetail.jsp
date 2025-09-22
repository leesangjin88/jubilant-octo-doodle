<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<%--
    파일명 : facilityDetail.jsp
    프로그렴명 : 시설 상세 조회 화면
    설 명 : 등록된 시설물의 상세 정보와 이용가능 시간을 조회할 수 있음.
    작성자 : 양 수 민
    작성일 : 2025. 07. 01
--%>	



  <title>시설 상세 정보 - LCMS 관리자</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

  <div class="max-w-4xl mx-auto bg-white rounded-xl shadow p-6">
    <div class="flex justify-between items-center mb-6">
      <a href="/staff/facility/facilityList.do" class="cancelButton">← 목록으로 돌아가기</a>
		<div>
			<form method="post" action="/staff/facility/facilityUpdateProcess.do" class="inline-block">

					<button type="submit" class="submitButton">저장</button>
<!-- 					<a href="/staff/facility/facilityList.do" class="cancelButton">취소</a> -->


					<c:url value="/staff/facility/facilityUpdate.do" var="updateURL">
						<c:param name="what" value="${facility.facilityNo }" />
					</c:url>
<%-- 					<a href="${updateURL }" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">상세 정보 수정</a> --%>
<%-- 					<c:url value="/staff/reservationTimestamp/facility/facilityInsert.do" var="updateURL2"> --%>
<%-- 						<c:param name="what" value="${facility.facilityNo }" /> --%>
<%-- 					</c:url> --%>
<%-- 					<a href="${updateURL2 }" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">시간표 수정</a> --%>
		</div>
	</div>

    <div class="grfacilityNo grfacilityNo-cols-1 md:grfacilityNo-cols-2 gap-6">
      <!-- 기본 정보 -->
      <div>
        <h3 class="text-lg font-semibold mb-2">🏢 기본 정보</h3>
        
        <input type="hidden" name="facilityNo" value="${facility.facilityNo}" /> 

        <label class="block mt-4 mb-2 text-sm" for="facilityName">시설명</label>
        <input id="facilityName" name="facilityName" value="${facility.facilityName}" class="w-full border px-3 py-2 rounded" />

        <label class="block mt-4 mb-2 text-sm" for="facilityType">시설 유형</label>
        <input id="facilityType" name="facilityType" value="${facility.facilityType}" class="w-full border px-3 py-2 rounded" />

        <label class="block mt-4 mb-2 text-sm" for="location">위치</label>
        <input id="location" name="location" value="${facility.location}" class="w-full border px-3 py-2 rounded" />

        <label class="block mt-4 mb-2 text-sm" for="facilityMp">수용 인원</label>
        <input id="facilityMp" name="facilityMp" type="number" value="${facility.facilityMp}" class="w-full border px-3 py-2 rounded" />
        
        <label class="block mt-4 mb-2 text-sm" for="facilityStatus">시설물 상태</label>
<%--         <input id="facilityStatus" name="facilityStatus" value="${facility.facilityStatus}" class="w-full border px-3 py-2 rounded" /> --%>
        <select id="facilityStatus" name="facilityStatus" class="selectBox">
			<option value="AVAILABLE" <c:if test="${facility.facilityStatus == 'AVAILABLE'}">selected</c:if>>이용 가능</option>
			<option value="MAINTENANCE" <c:if test="${facility.facilityStatus == 'MAINTENANCE'}">selected</c:if>>유지보수중(이용 불가능)</option>
			<option value="UNAVAILABLE" <c:if test="${facility.facilityStatus == 'UNAVAILABLE'}">selected</c:if>>이용 불가능</option>
		</select>
      </div>
      
    </div>
   </div>
  </form>
  	


