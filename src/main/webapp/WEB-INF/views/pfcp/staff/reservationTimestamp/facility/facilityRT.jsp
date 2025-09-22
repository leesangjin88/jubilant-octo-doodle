<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>시설 예약 가능 시간 설정</title>
<!-- ✅ Bootstrap CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- ✅ 사용자 정의 CSS -->
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
</head>

<c:set var="days" value="${fn:split('MON,TUE,WED,THU,FRI', ',')}" />
<c:set var="dayNames" value="${fn:split('월요일,화요일,수요일,목요일,금요일', ',')}" />






<body class="bg-light text-dark font-sans">

<div class="container my-5 p-4 bg-white rounded shadow">
  <h2 class="text-center fw-bold mb-4 pageTitle">시설 예약 가능 시간 설정</h2>

  <div class="text-center mb-4 p-3 card">
    <h3 id="facilityName" class="fw-bold text-primary mb-1">
      ${facility.facilityName } <span class="fw-normal text-muted"> - ${facility.location }</span>
    </h3>
    <p id="facilityInfo" class="text-secondary">
      ${facility.facilityType } / ${facility.facilityMp }인 사용 가능
    </p>
  </div>

  <!-- 수정 모드 안내 -->
  <c:if test="${mode == 'edit'}">
    <div class="noticeBox noticeWarning text-center">
      📝 수정 모드: 시간표의 셀을 클릭하여 예약 가능 시간을 설정하세요.
    </div>
  </c:if>

  <div class="border-top pt-4 mt-4">
    <h3 class="sectionTitle">예약 가능 시간표</h3>

    <div class="tableContainer">
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
              <td class="tableTd bg-light fw-bold">${hour}:00 - ${hour+1}:00</td>
              <c:forEach var="day" items="${days}">
                <c:set var="found" value="false" />
                <c:forEach var="item" items="${facilityRT}">
                  <c:if test="${item.reservationDay eq day && item.startHour eq hour}">
                    <c:set var="found" value="true" />
                  </c:if>
                </c:forEach>
                <c:choose>
                  <c:when test="${found}">
                    <td class="tableTd text-primary fw-medium"
					    style="background-color: #bee3f8; cursor: pointer;"
					    onclick="toggleTimeSlot(this, '${day}', ${hour})"
					    data-day="${day}" data-hour="${hour}">
					  <span>예약가능</span>
					</td>

                  </c:when>
                  <c:otherwise>
                    <td class="tableTd bg-white text-muted <c:if test='${mode == "edit"}'> cursor-pointer </c:if>"
                        <c:if test="${mode == 'edit'}"> onclick="toggleTimeSlot(this, '${day}', ${hour})" </c:if>
                        data-day="${day}" data-hour="${hour}">
                      <span>-</span>
                    </td>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

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

  <!-- 버튼 영역 -->
  <div class="text-center mt-5">
    <c:choose>
      <c:when test="${mode == 'edit'}">
        <button onclick="saveSchedule()" class="submitButton">저장</button>
        <button onclick="resetSchedule()" class="editButton">초기화</button>
        <button onclick="cancelSchedule()" class="cancelButton">취소</button>
      </c:when>
      <c:otherwise>
	    <button onclick="goBack()" class="cancelButton me-2">목록으로</button>
        <button onclick="insertSchedule()" class="editButton">수정</button>
      </c:otherwise>
    </c:choose>
  </div>
</div>

<!-- ✅ 기존 JavaScript는 그대로 유지 -->
<script>
  let changedSlots = {};
  
  function resetSchedule() {
	  // changedSlots 초기화
	  changedSlots = {};

	  // 예약 시간표 td 중 data-day, data-hour 속성 있는 셀 모두 찾기
	  document.querySelectorAll('td[data-day][data-hour]').forEach(cell => {
	    const span = cell.querySelector('span');
	    // 텍스트를 '-'로 변경
	    span.textContent = '-';
	    // 텍스트 색상 변경
	    span.className = 'text-muted';

	    // 배경색 변경 (예약 불가능 색상)
	    cell.style.backgroundColor = '#ffffff'; // 흰색

	    // 예약 가능에서 붙어있던 클래스들 제거 (bg-info, bg-success 등)
	    cell.classList.remove('bg-info', 'bg-success');
	    // 예약 불가능에 붙어있을 수 있는 클래스 추가
	    cell.classList.add('bg-white');

	    // 클릭 가능 상태 유지 (필요하면)
	    cell.style.cursor = 'pointer';
	  });
	}


  function cancelSchedule() {
	  history.back();
	}
  
  function insertSchedule() {
    const urlParams = new URLSearchParams(window.location.search);
    const what = urlParams.get('what');
    window.location.href = "/staff/reservationTimestamp/facility/facilityInsert.do?what=" + what;
  }

  function goBack() {
    window.location.href = "/staff/facility/facilityList.do";
  }

  function toggleTimeSlot(cell, day, hour) {
    const key = day + '_' + hour;
    const span = cell.querySelector('span');

    if (span.textContent === '예약가능') {
      span.textContent = '-';
      span.className = 'text-muted';
      cell.classList.remove('bg-info', 'bg-success');
      cell.classList.add('bg-white');
      changedSlots[key] = 'delete';
    } else {
      span.textContent = '예약가능';
      span.className = 'text-primary fw-medium';
      cell.classList.remove('bg-white');
      cell.style.backgroundColor = '#bbf7d0';  // 파스텔 그린 색상 직접 지정
      changedSlots[key] = 'add';
    }
  }

  function saveSchedule() {
    const activeSlots = [];

    document.querySelectorAll("td[data-day][data-hour]").forEach(cell => {
      const day = cell.dataset.day;
      const hour = parseInt(cell.dataset.hour);
      const classList = cell.className;

      if (!classList.includes('bg-white')) {
        activeSlots.push({ day, hour });
      }
    });

    const urlParams = new URLSearchParams(window.location.search);
    const what = urlParams.get('what');

    const formData = new FormData();
    formData.append('what', what);
    formData.append('timeSlots', JSON.stringify(activeSlots));

    fetch('/staff/reservationTimestamp/facility/facilityUpdateProcess.do', {
      method: 'POST',
      body: formData
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          alert('예약 시간 설정이 저장되었습니다!');
          window.location.href = "/staff/reservationTimestamp/facility/facility.do?what=" + what;
        } else {
          alert('저장 중 오류가 발생했습니다.');
        }
      })
      .catch(error => {
        console.error('Error:', error);
        alert('저장 중 오류가 발생했습니다.');
      });
  }
</script>
</body>
</html>
