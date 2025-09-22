<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>교수 상담 시간 설정</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>

body{
	background-color:#F5F7FD;
}

.main-container {
    margin-left: auto;
    margin-right: auto;
    margin-top: 3rem; /* my-12 */
    margin-bottom: 3rem; /* my-12 */
    padding: 2rem; /* p-8 */
    background-color: #ffffff; /* bg-white */
    border-radius: 0.75rem; /* rounded-xl */
    box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05); /* shadow-lg */
}



/* Main layout wrapper */
.layout-wrapper {
    display: flex;
    gap: 2rem;
    align-items: flex-start;
}

/* Department dropdown */
.department-dropdown {
    margin-bottom: 1.5rem; /* margin-bottom: 1.5rem; */
}

.selectBox {
    width: 100%;
    padding: 0.5rem 1rem; /* py-2 px-4 */
    border: 1px solid #d1d5db; /* border border-gray-300 */
    border-radius: 0.375rem; /* rounded-md */
    background-color: #ffffff; /* bg-white */
    appearance: none; /* remove default arrow */
    -webkit-appearance: none;
    -moz-appearance: none;
    background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%2032%2032%22%3E%3Cpath%20d%3D%22M16%2C20.7L6.6%2C11.3c-0.4-0.4-1-0.4-1.4%2C0s-0.4%2C1%2C0%2C1.4l10.7%2C10.7c0.4%2C0.4%2C1%2C0.4%2C1.4%2C0l10.7-10.7c0.4-0.4%2C0.4-1%2C0-1.4s-1-0.4-1.4%2C0L16%2C20.7z%22%2F%3E%3C%2Fsvg%3E'); /* custom arrow */
    background-repeat: no-repeat;
    background-position: right 0.75rem center;
    background-size: 0.75rem;
    font-size: 1rem;
    line-height: 1.5rem;
}

/* Professor card wrapper */
.professor-card-wrapper {
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
    text-align: center;
}

/* Professor card */
.professor-card {
    flex: 1 1 calc(50% - 0.5rem); /* flex: 1 1 calc(50% - 0.5rem) */
    cursor: pointer;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 1.5rem; /* p-6 */
    background-color: #ffffff; /* bg-white */
    border-radius: 0.5rem; /* rounded-lg */
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06); /* shadow-sm */
    transition: transform 0.2s ease, border 0.2s ease, box-shadow 0.2s ease;
}

.professor-card:hover {
    transform: translateY(-3px);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

/* Selected card style */
.professor-card.selected {
    border: 2px solid #3b82f6; /* Tailwind blue-500 */
    transform: translateY(-5px);
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.professor-card-icon {
    font-size: 2rem; /* text-3xl */
    background-color: #f1f5f9; /* bg-slate-100 */
    border-radius: 9999px; /* rounded-full */
    padding: 0.75rem; /* p-3 */
    color: #3b82f6; /* text-blue-500 */
}

.professor-info h4 {
    margin: 0;
    font-size: 1.125rem; /* text-lg */
    font-weight: 600; /* font-semibold */
    color: #1e293b; /* text-slate-900 */
}

.professor-info p {
    margin: 0.25rem 0 0; /* mt-1 */
    font-size: 0.875rem; /* text-sm */
    color: #64748b; /* text-slate-500 */
}

/* Notice box for empty professor list */
.noticeBox {
    padding: 1rem;
    border-radius: 0.375rem;
    font-size: 0.875rem;
    text-align: center;
    width: 100%;
}

.noticeWarning {
    background-color: #fef3c7; /* bg-amber-100 */
    color: #b45309; /* text-amber-700 */
    border: 1px solid #fbbf24; /* border-amber-400 */
}

/* Professor detail area */
#professorDetailArea {
    flex: 1;
    border-left: 1px solid #e2e8f0; /* border-l border-gray-200 */
    padding-left: 2rem; /* pl-8 */
}

#professorDetailArea .text-center {
    text-align: center;
    margin-bottom: 2rem; /* mb-8 */
    padding: 1rem; /* p-4 */
    background-color: #eff6ff; /* bg-blue-50 */
    border-radius: 0.5rem; /* rounded-lg */
}

#professorDetailArea h3 {
    font-size: 1.25rem; /* text-xl */
    font-weight: 700; /* font-bold */
    color: #1d4ed8; /* text-blue-700 */
    margin-bottom: 0.25rem; /* mb-1 */
}

#professorDetailArea h3 .font-normal {
    font-weight: 400; /* font-normal */
    color: #4b5563; /* text-gray-600 */
}

/* Schedule table container */
#scheduleTableContainer {
    border-top: 1px solid #e2e8f0; /* border-t border-gray-200 */
    padding-top: 2rem; /* pt-8 */
    margin-top: 2rem; /* mt-8 */
}

#scheduleTableContainer p {
    color: #4b5563; /* text-gray-600 */
    text-align: center;
}

/* Button area */
#buttonArea {
    display: flex;
    justify-content: center;
    margin-top: 2.5rem; /* mt-10 */
    gap: 1rem; /* space-x-4 */
}

/* Button base styles */
.cancelButton, .saveButton, .editButton {
    padding: 0.75rem 1.5rem; /* py-3 px-6 */
    border-radius: 0.5rem; /* rounded-lg */
    font-weight: 600; /* font-semibold */
    cursor: pointer;
    transition: background-color 0.2s ease, box-shadow 0.2s ease;
}

.cancelButton {
    background-color: #e5e7eb; /* bg-gray-200 */
    color: #4b5563; /* text-gray-700 */
    border: none;
}

.cancelButton:hover {
    background-color: #d1d5db; /* hover:bg-gray-300 */
}

.saveButton {
    background-color: #22c55e; /* bg-green-500 */
    color: #ffffff; /* text-white */
    border: none;
}

.saveButton:hover {
    background-color: #16a34a; /* hover:bg-green-600 */
}

.editButton {
    background-color: #3b82f6; /* bg-blue-500 */
    color: #ffffff; /* text-white */
    border: none;
}

.editButton:hover {
    background-color: #2563eb; /* hover:bg-blue-600 */
}

/* Styles for schedule table cells (from getConsultScheduleFragment.do) */
/* These styles should ideally be applied to the dynamically loaded content as well */
.schedule-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 1rem;
}

.schedule-table th, .schedule-table td {
    border: 1px solid #e5e7eb; /* border border-gray-300 */
    padding: 0.75rem; /* p-3 */
    text-align: center;
}

.schedule-table th {
    background-color: #f9fafb; /* bg-gray-50 */
    font-weight: 600;
    color: #4b5563;
    white-space: nowrap; /* prevent wrapping for time headers */
}

.schedule-table td {
    background-color: #ffffff; /* bg-white */
    transition: background-color 0.2s ease;
}

.schedule-table td.bg-blue-200 { /* This class is used for existing reservations */
    background-color: #bfdbfe; /* bg-blue-200 */
}

.schedule-table td.bg-green-200 { /* This class is used for newly added slots in edit mode */
    background-color: #d1fae5; /* bg-green-200 */
}

.schedule-table td.hover\:bg-blue-300:hover { /* This class is for hover effect on existing reservations */
    background-color: #93c5fd; /* hover:bg-blue-300 */
}

.schedule-table td.hover\:bg-green-300:hover { /* This class is for hover effect on newly added slots */
    background-color: #a7f3d0; /* hover:bg-green-300 */
}

.schedule-table td.hover\:bg-gray-100:hover { /* This class is for hover effect on empty slots */
    background-color: #f3f4f6; /* hover:bg-gray-100 */
}


.schedule-table td span {
    display: block;
    font-size: 0.875rem; /* text-sm */
    line-height: 1.25rem;
}

.schedule-table td span.text-gray-400 {
    color: #9ca3af; /* text-gray-400 */
}

.schedule-table td span.text-blue-700 {
    color: #1d4ed8; /* text-blue-700 */
    font-weight: 500;
}

.schedule-table td span.text-green-700 {
    color: #047857; /* text-green-700 */
    font-weight: 500;
}


</style>
</head>

	<div class="main-container">

		<div class="layout-wrapper" style="display: flex; gap: 2rem; align-items: flex-start;">

			<div style="flex: 0 0 35%;">
				<div class="department-dropdown" style="margin-bottom: 1.5rem;">
					<select id="departmentFilter" class="selectBox" onchange="filterProfessors(this.value)">
						<option value="">학과를 선택하세요</option>
						<option value="DP001">컴퓨터공학과</option>
						<option value="DP002">전자공학과</option>
						<option value="DP003">시각디자인학과</option>
						<option value="DP004">소프트웨어학과</option>
						<option value="DP005">국어국문학과</option>
		                <option value="1">정보통신공학과</option> </select>
				</div>
				
				<div class="professor-card-wrapper" style="display: flex; flex-wrap: wrap; gap: 1rem; text-align:center;">
					<c:choose>
						<c:when test="${not empty professorList }">
							<c:forEach items="${professorList }" var="professor">
								<div class="card professor-card"
						             data-department="${professor.department.departmentNo}"
						             data-prof-id="${professor.userNo}"
						             onclick="selectProfessor('${professor.userNo}', '${professor.user.userName}', '${professor.department.departmentName}')"
						             style="
						                flex: 1 1 calc(50% - 0.5rem);
						                cursor: pointer;
						                display: flex;
						                flex-direction: column;
						                align-items: center;
						                justify-content: center;
						                padding: 1.5rem;
						                transition: transform 0.2s ease, border 0.2s ease, box-shadow 0.2s ease;
						             ">
									<div class="professor-card-icon"
										 style="font-size: 2rem; background-color: #f1f5f9; border-radius: 9999px; padding: 0.75rem; color: #3b82f6;">
										👨‍🏫
									</div>
									<div class="professor-info">
										<h4 style="margin: 0; font-size: 1rem; font-weight: 600; color: #1e293b;">${professor.user.userName}</h4>
										<p style="margin: 0.25rem 0 0; font-size: 0.875rem; color: #64748b;">${professor.department.departmentName}</p>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="noticeBox noticeWarning" style="width: 100%;">
								아직 교수 없음.
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div id="professorDetailArea" style="flex: 1; border-left: 1px solid #e2e8f0; padding-left: 2rem;">
				<div class="text-center mb-8 p-4 bg-blue-50 rounded-lg">
					<h3 id="consultName" class="text-xl font-bold text-blue-700 mb-1">
						교수를 선택하여 상담 시간표를 확인하세요.
					</h3>
				</div>

				<div id="scheduleTableContainer" class="border-t border-gray-200 pt-8 mt-8">
					<%-- 여기에 AJAX로 불러온 시간표 HTML이 삽입됩니다. --%>
					<p class="text-gray-600 text-center">좌측 교수 목록에서 교수를 선택해 주세요.</p>
				</div>

				<div id="buttonArea" style= "margin-left:45%;" class="hidden">
					<div style="display:flex; gap:1rem;">
						<button id="saveScheduleButton" onclick="saveSchedule()" class="submitButton" style="display:none;">저장</button>
						<button id="cancelEditButton" onclick="cancelEdit()" class="cancelButton" style="display:none;" >취소</button>
					</div>
					<button id="editScheduleButton" onclick="toggleEditMode()" class="editButton" >수정</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		// 전역 변수
		let currentSelectedUserNo = null;
		let currentMode = 'view'; // 'view' 또는 'edit'
		
		// DOM 로드 후 초기화
		document.addEventListener('DOMContentLoaded', () => {
			filterProfessors(''); // 페이지 로드 시 전체 교수 목록 표시
			
			let buttonArea = document.getElementById('buttonArea');
			buttonArea.style.display = 'none'; 

			// URL에서 'what' (userNo) 파라미터가 있다면 해당 교수 정보 로드
			const urlParams = new URLSearchParams(window.location.search);
			const initialUserNo = urlParams.get('what');
			const initialMode = urlParams.get('mode');

			if (initialUserNo) {
				// 초기 로딩 시 특정 교수를 선택한 것처럼 처리
				selectProfessor(initialUserNo, '', ''); // 이름/학과 정보는 AJAX 응답에서 가져올 것
				if (initialMode === 'edit') {
					toggleEditMode(); // 수정 모드로 시작
				}
			}
		});

		// 학과 필터링 함수
		function filterProfessors(selectedDepartmentNo) {
			const professorCards = document.querySelectorAll('.professor-card');
			professorCards.forEach(card => {
				const departmentNo = card.dataset.department;
				if (selectedDepartmentNo === '' || departmentNo === selectedDepartmentNo) {
					card.style.display = 'flex';
				} else {
					card.style.display = 'none';
				}
			});
		}

		// 교수 카드 클릭 시 상세 정보 및 시간표 로드
		function selectProfessor(userNo, userName, departmentName) {
			console.log('selectProfessor userNo:', userNo);
			
			let buttonArea = document.getElementById('buttonArea');
			buttonArea.style.display = 'block'; 
			

			
			// 이전에 선택된 카드에서 'selected' 클래스 제거
			document.querySelectorAll('.professor-card').forEach(card => {
				card.classList.remove('selected');
			});

			// 현재 클릭된 카드에 'selected' 클래스 추가
			const selectedCard = document.querySelector(`.professor-card[data-prof-id="${userNo}"]`);
			if (selectedCard) {
				selectedCard.classList.add('selected');
			}

			// 전역 변수 업데이트
			currentSelectedUserNo = userNo;
			currentSelectedUserName = userName;
			currentSElectedDepartmentName = departmentName

			// 상단 교수 이름/학과 업데이트 (클릭 시 받은 정보로 먼저 업데이트)
			const consultNameEl = document.getElementById('consultName');
			if (consultNameEl) {
				consultNameEl.innerHTML = "<span class='font-normal text-gray-600'>상담 시간표</span>";
			}
			
			// 버튼 영역 보이기
			document.getElementById('buttonArea').classList.remove('hidden');

			// AJAX 요청을 통해 해당 교수의 상담 시간표 HTML 조각을 가져옴
			// URL: /staff/consult/getConsultScheduleFragment.do
			
			
  
			fetch("/staff/consult/getConsultScheduleFragment.do?userNo=" + userNo + "&mode=" + currentMode)
				.then(response => {
					console.log('fetch URL:', `/staff/consult/getConsultScheduleFragment.do?userNo=${userNo}&mode=${currentMode}`);
					if (!response.ok) {
						throw new Error('Network response was not ok');
					}
					return response.text(); // HTML 조각을 텍스트로 받음
				})
				.then(html => {
					document.getElementById('scheduleTableContainer').innerHTML = html;
					// 초기 로딩 시 이름/학과 정보가 비어있다면, 서버에서 받은 값으로 업데이트하는 로직은 필요시 추가
				})
				.catch(error => {
					console.error('교수 시간표 로드 오류:', error);
					document.getElementById('scheduleTableContainer').innerHTML = '<p class="text-red-500 text-center">상담 시간표를 불러오는 데 실패했습니다.</p>';
				});
			

		}

		// 시간 슬롯 토글 함수 (AJAX로 로드된 HTML 내부에서도 동작해야 함)
		function toggleTimeSlot(cell, day, hour) {
			if (currentMode !== 'edit') return;

			const span = cell.querySelector('span');
			
			if (span.textContent === '예약가능') {
				span.textContent = '-';
				span.className = 'text-gray-400';
				cell.classList.remove('bg-blue-200', 'bg-green-200');
				cell.classList.add('bg-white');
				cell.classList.remove('hover:bg-blue-300', 'hover:bg-green-300');
				cell.classList.add('hover:bg-gray-100');
			} else {
				span.textContent = '예약가능';
				span.className = 'text-blue-700 font-medium';
				cell.classList.remove('bg-white');
				cell.style.backgroundColor = '#bbf7d0';
				cell.classList.remove('hover:bg-gray-100');
				cell.classList.add('hover:bg-green-300');
			}
		}

		// '수정' 모드 토글 함수
		function toggleEditMode() {
			if (!currentSelectedUserNo) {
				alert('먼저 교수를 선택해주세요.');
				return;
			}
			currentMode = 'edit';
			let cancelEditButton = document.getElementById('cancelEditButton');
			let saveScheduleButton = document.getElementById('saveScheduleButton');
			let editScheduleButton = document.getElementById('editScheduleButton');
			
			cancelEditButton.style.display = 'block'; 
			saveScheduleButton.style.display = 'block'; 
			editScheduleButton.style.display = 'none'; 

			// 시간표를 다시 로드하여 모든 셀에 클릭 이벤트 핸들러가 활성화되도록 함
			// 현재 consultName에 표시된 이름과 학과를 다시 전달합니다.
			selectProfessor(currentSelectedUserNo, 
			document.querySelector('#consultName').firstChild.textContent.trim(),
			document.querySelector('#consultName span').textContent.replace('-', '').trim()
			);
			
			console.log('toggleEditMode currentSelectedUserNo:', currentSelectedUserNo);
		}
		
		// '취소' 버튼 클릭 시 (수정 모드 취소)
		function cancelEdit() {
			currentMode = 'view';
			
			let cancelEditButton = document.getElementById('cancelEditButton');
			let saveScheduleButton = document.getElementById('saveScheduleButton');
			let editScheduleButton = document.getElementById('editScheduleButton');
			
			cancelEditButton.style.display = 'none'; 
			saveScheduleButton.style.display = 'none';
			editScheduleButton.style.display = 'block';

			// 시간표를 다시 로드하여 클릭 이벤트 핸들러가 비활성화되도록 함
			// 현재 consultName에 표시된 이름과 학과를 다시 전달합니다.
			selectProfessor(currentSelectedUserNo, 
			document.querySelector('#consultName').firstChild.textContent.trim(), 
			document.querySelector('#consultName span').textContent.replace('-', '').trim()
			);
		}

		// '목록으로' 버튼 클릭 시
		function goToList() {
			alert('이미 교수 목록 페이지입니다. 선택을 해제하고 초기화합니다.');
			currentSelectedUserNo = null;
			currentMode = 'view';
			document.querySelectorAll('.professor-card').forEach(card => {
				card.classList.remove('selected');
			});
			document.getElementById('consultName').innerHTML = '교수를 선택하여 상담 시간표를 확인하세요.';
			document.getElementById('scheduleTableContainer').innerHTML = '<p class="text-gray-600 text-center">좌측 교수 목록에서 교수를 선택해 주세요.</p>';
			document.getElementById('buttonArea').classList.add('hidden');
			document.getElementById('cancelEditButton').classList.add('hidden');
			document.getElementById('saveScheduleButton').classList.add('hidden');
			document.getElementById('editScheduleButton').classList.remove('hidden');
		}
		
		// '저장' 버튼 클릭 시
		function saveSchedule() {
			  const activeSlots = [];

			  // 모든 시간 슬롯 셀을 순회하며 '예약가능' 상태인 셀의 정보를 수집
			  document.querySelectorAll("#scheduleTableContainer td[data-day][data-hour]").forEach(cell => {
			    const span = cell.querySelector('span');
			    if (span && span.textContent === '예약가능') {
			      const day = cell.dataset.day;
			      const hour = parseInt(cell.dataset.hour);
			      activeSlots.push({ reservationDay: day, startHour: hour }); // ReservationTimestampVO 필드명과 일치
			    }
			  });

			  if (!currentSelectedUserNo) {
			      alert('저장할 교수가 선택되지 않았습니다.');
			      return;
			  }

			  const formData = new FormData();
			  formData.append('userNo', currentSelectedUserNo); 
			  formData.append('consultList', JSON.stringify(activeSlots)); 

			  fetch('/staff/consult/consultUpdateProcess.do', { // Controller의 매핑 경로 확인
			    method: 'POST',
			    body: formData
			  })
			    .then(response => response.json())
			    .then(data => {
			      if (data.success) {
			        alert('예약 시간 설정이 저장되었습니다!');
			        // 저장 후 보기 모드로 전환하고 시간표 리로드
			        cancelEdit(); // 수정 모드 취소 (보기 모드로 돌아감)
			      } else {
			        alert('저장 중 오류가 발생했습니다: ' + (data.message || '알 수 없는 오류'));
			      }
			    })
			    .catch(error => {
			      console.error('Error:', error);
			      alert('저장 중 통신 오류가 발생했습니다.');
			    });
		}

	</script>

