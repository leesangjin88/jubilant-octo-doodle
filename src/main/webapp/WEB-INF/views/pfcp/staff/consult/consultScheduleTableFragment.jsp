<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<c:set var="days" value="${fn:split('MON,TUE,WED,THU,FRI', ',')}" />
<c:set var="dayNames" value="${fn:split('월요일,화요일,수요일,목요일,금요일', ',')}" />

    <table class="defaultTable table table-bordered text-center">
       <thead class="tableHead">
            <tr>
            <th class="tableTh">시간</th>
                <c:forEach var="dayName" items="${dayNames}">
                    <th class="tableTh">${dayName}</th>
                </c:forEach>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="hour" begin="9" end="17">
                <tr>
                    <td class="tableTd bg-light fw-bold">${hour}:00-${hour + 1}:00</td>
                    <c:forEach var="day" items="${days}">
                        <td
                            class="border border-gray-300 p-3 text-center relative
                            <c:set var="found" value="false"/>
                            <c:forEach items="${consultRT}" var="item">
                                <c:if test="${item.reservationDay eq day && item.startHour eq hour}">
                                    <c:set var="found" value="true"/>
                                </c:if>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${found}">bg-blue-200 hover:bg-blue-300</c:when>
                                <c:otherwise>bg-white hover:bg-gray-100</c:otherwise>
                            </c:choose>
                            <c:if test="${mode == 'edit'}"> cursor-pointer transition-colors duration-200</c:if>
                            "
                            <c:if test="${mode == 'edit'}">
                                onclick="toggleTimeSlot(this, '${day}', ${hour})"
                            </c:if>
                            data-day="${day}" data-hour="${hour}">
                            <c:set var="found" value="false" />
                            <c:forEach items="${consultRT}" var="item">
                                <c:if test="${item.reservationDay eq day && item.startHour eq hour}">
                                    <span class="text-blue-700 font-medium">예약가능</span>
                                    <c:set var="found" value="true" />
                                </c:if>
                            </c:forEach>
                            <c:if test="${!found}">
                                <span class="text-gray-400">-</span>
                            </c:if>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>


<div class="d-flex justify-content-center gap-4 mt-4">
      <div class="d-flex align-items-center">
        <div class="me-2" style="width: 16px; height: 16px; background-color: #bee3f8; border: 1px solid #ccc;"></div>
        <span class="text-muted small">예약 가능 시간</span>
      </div>
      <div class="d-flex align-items-center">
        <div class="me-2" style="width: 16px; height: 16px; background-color: #ffffff; border: 1px solid #ccc;"></div>
        <span class="text-muted small">예약 불가능 시간</span>
      </div>
      <c:if test="${mode == 'edit'}">
        <div class="d-flex align-items-center">
          <div class="me-2" style="width: 16px; height: 16px; background-color: #bbf7d0; border: 1px solid #ccc;"></div>
          <span class="text-muted small">새로 추가된 시간</span>
        </div>
      </c:if>
    </div>
  </div>