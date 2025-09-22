<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<title>캘린더</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/main.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.5/locales-all.min.js"></script>

<style>
#calendar {
	max-width: 900px;
	margin: 50px auto;
}

#error {
	color: red;
	font-size: 0.9em;
}

#schedulePopup {
	display: none;
	position: fixed;
	top: 20%;
	left: 50%;
	transform: translateX(-50%);
	background: #fff;
	padding: 20px;
	border: 2px solid #333;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
	z-index: 9999;
	min-width: 300px;
	border-radius: 8px;
}
</style>

<h2 style="text-align: center;">캘린더</h2>
<div class="card" style="margin-bottom: 1rem;">
	<button id="toggleFilter" class="filterButton active">필터 열기</button>
	<div id="filterBox" style="display: none; overflow: hidden; transition: all 0.3s ease;">
		<div class="form-group">
			<label class="inputLabel" for="filterType">일정 유형</label> 
			<select id="filterType" class="selectBox">
				<option value="">전체</option>
				<c:forEach var="type" items="${scheduleTypes}">
					<option value="${type.scheduleTypeNo}">${type.scheduleTypeName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label class="inputLabel" for="filterStart">시작일</label> 
			<input type="date" id="filterStart" class="inputField" />
		</div>
		<div class="form-group">
			<label class="inputLabel" for="filterEnd">종료일</label> 
			<input type="date" id="filterEnd" class="inputField" />
		</div>
		<div class="button-group">
			<button type="button" id="filterBtn" class="searchButton">검색</button>
			<button type="button" id="resetFilterBtn" class="cancelButton">초기화</button>
		</div>
	</div>
</div>

<div id="calendar"></div>

<script>
	let calendar;
	let currentScheduleId = null;
	
	const toggleBtn = document.getElementById("toggleFilter");
	const filterBox = document.getElementById("filterBox");
	
	toggleBtn.addEventListener("click", () => {
		if (filterBox.style.display === "none") {
			filterBox.style.display = "block";
			filterBox.style.maxHeight = filterBox.scrollHeight + "px";
			toggleBtn.textContent = "필터 닫기";
		} else {
			filterBox.style.display = "none";
			toggleBtn.textContent = "필터 열기";
		}
	});
	
	document.addEventListener('DOMContentLoaded', function () {
		calendarEl = document.getElementById('calendar');
	
	  	calendar = new FullCalendar.Calendar(calendarEl, {
	    initialView: 'dayGridMonth',
	    locale: 'ko',
	    headerToolbar: {
	      left: 'prev,next today',
	      center: 'title',
	      right: 'dayGridMonth'
	    },
	    events: {
	      url: '/staff/schedule/list',
	      method: 'GET',
	      eventDataTransform: function (event) {
	    	  console.log("ecoloro", event.backgroundColor);
	        return {
	          id: event.id,
	          title: event.title,
	          start: event.start,
	          end: event.end,
	          backgroundColor: event.backgroundColor,
	          borderColor: event.borderColor,
	          extendedProps: {
	            startTime: event.startTime,
	            endTime: event.endTime,
	            scheduleDesp: event.scheduleDesp,
	            type: event.type
	          }
	        };
	      }
	    },
	    eventClick: function (info) {
	      currentScheduleId = info.event.id;
	
	      fetch(`/staff/schedule/\${currentScheduleId}`)
	        .then(res => res.json())
	        .then(dt => {
	          console.log("dt>>>>", dt);
	
	          const content = `
	            <strong>📌 제목: \${dt.scheduleTitle}</strong><br/>
	            <strong>📅 시작일: \${dt.startDate}</strong><br/>
	            <strong>📅 종료일: \${dt.endDate}</strong><br/>
	            <strong>🕑 시간: \${dt.startTime} ~ \${dt.endTime}</strong><br/>
	            <strong>📂 유형: \${dt.type?.scheduleTypeName || '없음'}</strong><br/>
	            <strong>📝 설명: \${dt.scheduleDesp || '없음'}</strong>
	          `;
	
	          document.getElementById("popupContent").innerHTML = content;
	          document.getElementById("schedulePopup").style.display = "block";
	        });
	    }
	  });
	
	  calendar.render();
	
	  document.querySelector("#resetBtn").addEventListener("click", function () {
		document.querySelector("#scheduleNo").value = "";
		document.querySelector("#scheduleTitle").value = "";
		document.querySelector("#startDate").value = "";
		document.querySelector("#endDate").value = "";
		document.querySelector("#scheduleType").value = "";
		document.querySelector("#scheduleDesp").value = "";
		document.querySelector("#formMode").value = "insert";
		document.querySelector("#submitBtn").textContent = "저장";
		document.querySelector("#regititle").textContent = "일정 등록";
		document.querySelector("#resetBtn").style.display = "none";
	  });
	
	  document.getElementById("editBtn").addEventListener("click", function () {
	    fetch(`/staff/schedule/\${currentScheduleId}`)
	    	.then(res => res.json())
	        .then(data => {
	        	document.querySelector("#scheduleNo").value = data.scheduleNo;
	        	document.querySelector("#scheduleTitle").value = data.scheduleTitle;
	        	document.querySelector("#startDate").value = data.startDate;
	        	document.querySelector("#endDate").value = data.endDate;
	        	document.querySelector("#scheduleType").value = data.type?.scheduleTypeNo || '';
	        	document.querySelector("#scheduleDesp").value = data.scheduleDesp;
	        	document.querySelector("#formMode").value = "update";
	        	document.querySelector("#submitBtn").textContent = "수정";
	        	document.querySelector("#regititle").textContent = "일정 수정";
	       	 	document.querySelector("#resetBtn").style.display = "inline-block";
	        	closePopup();
	      	});
	  });
	
	  document.getElementById("deleteBtn").addEventListener("click", function () {
	    if (confirm("정말 삭제하시겠습니까?")) {
	      fetch(`/staff/schedule/\${currentScheduleId}`, {
	        method: 'DELETE'
	      })
	        .then(res => res.json())
	        .then(result => {
	          if (result.result === 'success') {
	            alert("삭제되었습니다.");
	            calendar.refetchEvents(); // 또는 location.reload()
	          } else {
	            alert("삭제에 실패했습니다.");
	          }
	        });
	      closePopup();
	    }
	  });
	});
	
	document.getElementById("filterBtn").addEventListener("click", function () {
	    const scheduleTypeNo = document.getElementById("filterType").value;
	    const startDate = document.getElementById("filterStart").value;
	    const endDate = document.getElementById("filterEnd").value;
	
	    // ✅ 기존 모든 소스 제거
	    calendar.getEventSources().forEach(source => source.remove());
	
	    // ✅ 필터링된 소스 추가
	    calendar.addEventSource({
	        url: "/staff/schedule/list",
	        method: "GET",
	        extraParams: {
	            scheduleTypeNo: scheduleTypeNo,
	            startDate: startDate,
	            endDate: endDate
	        },
	        failure: function () {
	            alert("일정 데이터를 불러오는 데 실패했습니다.");
	        }
	    });
	});
	
	document.getElementById("resetFilterBtn").addEventListener("click", function () {
	    // 1. 필터 입력 값 초기화
	    document.getElementById("filterType").value = "";
	    document.getElementById("filterStart").value = "";
	    document.getElementById("filterEnd").value = "";
	
	    // 2. 기존 소스 제거
	    calendar.getEventSources().forEach(source => source.remove());
	
	    // 3. 전체 일정 다시 로드
	    calendar.addEventSource({
	        url: "/staff/schedule/list",
	        method: "GET",
	        failure: function () {
	            alert("일정 데이터를 불러오는 데 실패했습니다.");
	        }
	    });
	});
	
	function closePopup() {
	  document.getElementById("schedulePopup").style.display = "none";
	}
</script>

<h3 style="text-align: center;" id="regititle">일정 등록</h3>

<div class="section">
	<div class="card">
		<form:form id="scheduleForm" method="post"
			action="/staff/schedule/save" modelAttribute="scheduleVO">
			<input type="hidden" id="scheduleNo" name="scheduleNo" />
			<input type="hidden" id="formMode" name="formMode" value="insert" />
			<label> 
				<input type="checkbox" name="isNotice" value="true" />
				공지사항으로 등록
			</label>

			<div class="form-row">
				<div class="form-group">
					<label for="scheduleTitle" class="inputLabel">일정 제목 *</label>
					<form:input path="scheduleTitle" id="scheduleTitle" cssClass="inputField" required="required" />
					<form:errors path="scheduleTitle" cssClass="error" />
				</div>

				<div class="form-group">
					<label for="scheduleType" class="inputLabel">일정 유형 *</label>
					<form:select path="type.scheduleTypeNo" id="scheduleType" cssClass="selectBox" required="required">
						<form:option value="" label="-- 선택 --" />
						<form:options items="${scheduleTypes}" itemValue="scheduleTypeNo" itemLabel="scheduleTypeName" />
					</form:select>
					<form:errors path="type.scheduleTypeNo" cssClass="error" />
				</div>
			</div>

			<div class="form-row">
				<div class="form-group">
					<label for="startDate" class="inputLabel">시작일</label>
					<form:input path="startDate" id="startDate" type="date" cssClass="inputField" />
					<form:errors path="startDate" cssClass="error" />
				</div>

				<div class="form-group">
					<label for="endDate" class="inputLabel">종료일</label>
					<form:input path="endDate" id="endDate" type="date" cssClass="inputField" />
					<form:errors path="endDate" cssClass="error" />
				</div>
			</div>
			<div class="form-row">
				<div class="form-group">
					<label for="startTime" class="inputLabel">시작 시간</label>
					<form:input path="startTime" id="startTime" type="time" cssClass="inputField" />
					<form:errors path="startTime" cssClass="error" />
				</div>

				<div class="form-group">
					<label for="endTime" class="inputLabel">종료 시간</label>
					<form:input path="endTime" id="endTime" type="time" cssClass="inputField" />
					<form:errors path="endTime" cssClass="error" />
				</div>
			</div>

			<div class="form-group">
				<label for="scheduleDesp" class="inputLabel">설명</label>
				<form:textarea path="scheduleDesp" id="scheduleDesp" cssClass="textareaField" placeholder="상세 설명을 입력하세요" />
				<form:errors path="scheduleDesp" cssClass="error" />
			</div>
			<div class="button-group">
				<button class="editButton" type="button" id="resetBtn" style="display: none;">돌아가기</button>
				<button type="submit" id="submitBtn" class="submitButton">등록</button>
				<button type="button" id="resetBtn" class="cancelButton" style="display: none;">초기화</button>
			</div>
		</form:form>
	</div>
</div>

<!-- 📋 일정 상세 팝업 모달 -->
<div id="schedulePopup">
	<div id="popupContent" style="line-height: 1.6;"></div>
	<div style="text-align: right; margin-top: 12px;">
		<button id="editBtn" style="margin-right: 6px;" class="editButton">수정</button>
		<button id="deleteBtn" style="margin-right: 6px;"
			class="deleteButton">삭제</button>
		<button onclick="closePopup()" class="cancelButton">닫기</button>
	</div>
</div>

<c:if test="${not empty message}">
	<script>
        alert("${message}");
    </script>
</c:if>

<c:if test="${not empty errorMessage}">
	<script>
       alert("${errorMessage}");
   </script>
</c:if>
