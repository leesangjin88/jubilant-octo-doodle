<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<%--
파일명 : facilityDetail.jsp
프로그렴명 : 시설 상세 조회 화면 (학생용)
설 명 : 등록된 시설물의 상세 정보와 이용가능 시간을 조회할 수 있음.
작성자 : 이 성 화
작성일 : 2025. 07. 16 
--%>

<title>시설 상세 정보</title>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

<div class="max-w-4xl mx-auto bg-white rounded-xl shadow p-6">
    <div class="flex justify-between items-center mb-6">
    </div>

    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <!-- 기본 정보 -->
        <div>
            <h3 class="text-lg font-semibold mb-4">🏢 기본 정보</h3>
            
            <div class="space-y-4">
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-1">시설명</label>
                    <div class="w-full border border-gray-200 px-3 py-2 rounded bg-gray-50 text-gray-800">
                        ${facility.facilityName}
                    </div>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-1">시설 유형</label>
                    <div class="w-full border border-gray-200 px-3 py-2 rounded bg-gray-50 text-gray-800">
                        ${facility.facilityType}
                    </div>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-1">위치</label>
                    <div class="w-full border border-gray-200 px-3 py-2 rounded bg-gray-50 text-gray-800">
                        ${facility.location}
                    </div>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-1">수용 인원</label>
                    <div class="w-full border border-gray-200 px-3 py-2 rounded bg-gray-50 text-gray-800">
                        ${facility.facilityMp}명
                    </div>
                </div>

                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-1">시설물 상태</label>
                    <div class="w-full border border-gray-200 px-3 py-2 rounded bg-gray-50">
                        <c:choose>
                            <c:when test="${facility.facilityStatus == 'AVAILABLE'}">
                                <span class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-green-100 text-green-800">
                                    ✅ 이용 가능
                                </span>
                            </c:when>
                            <c:when test="${facility.facilityStatus == 'MAINTENANCE'}">
                                <span class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-yellow-100 text-yellow-800">
                                    🔧 유지보수중 (이용 불가능)
                                </span>
                            </c:when>
                            <c:when test="${facility.facilityStatus == 'UNAVAILABLE'}">
                                <span class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-red-100 text-red-800">
                                    ❌ 이용 불가능
                                </span>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <!-- 이용 안내 -->
        <div>
            <h3 class="text-lg font-semibold mb-4">📋 이용 안내</h3>
            
            <div class="bg-blue-50 border border-blue-200 rounded-lg p-4 mb-4">
                <h4 class="font-medium text-blue-900 mb-2">💡 이용 시 참고사항</h4>
                <ul class="text-sm text-blue-800 space-y-1">
                    <li>• 예약은 이용일 7일 전부터 가능합니다</li>
                    <li>• 예약 취소는 이용일 1일 전까지 가능합니다</li>
                    <li>• 시설 이용 시간은 09:00 ~ 22:00 입니다</li>
                    <li>• 시설 이용 후 정리정돈을 부탁드립니다</li>
                </ul>
            </div>

            <c:if test="${facility.facilityStatus != 'AVAILABLE'}">
                <div class="bg-red-50 border border-red-200 rounded-lg p-4">
                    <h4 class="font-medium text-red-900 mb-2">⚠️ 이용 불가 안내</h4>
                    <p class="text-sm text-red-800">
                        현재 이 시설은 
                        <c:choose>
                            <c:when test="${facility.facilityStatus == 'MAINTENANCE'}">
                                유지보수 중이므로 예약이 불가능합니다.
                            </c:when>
                            <c:otherwise>
                                이용이 불가능한 상태입니다.
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </c:if>
        </div>
    </div>

    <!-- 하단 버튼 영역 -->
    <div class="mt-8 flex justify-center space-x-4">
        <a href="/student/facility/facilityList.do" 
           class="bg-gray-500 hover:bg-gray-600 text-white px-6 py-2 rounded transition-colors">
            목록으로
        </a>
        <c:if test="${facility.facilityStatus == 'AVAILABLE'}">
            <a href="/student/reservation/reservationForm.do?facilityNo=${facility.facilityNo}" 
               class="bg-blue-500 hover:bg-blue-600 text-white px-6 py-2 rounded transition-colors">
                예약하기
            </a>
        </c:if>
    </div>
</div>