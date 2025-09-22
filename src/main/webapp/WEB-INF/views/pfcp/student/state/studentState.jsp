<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250704	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>Insert title here</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<script src="/js/app/pfcp/student/studentState.js"></script>

<style>
.container {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	max-width: 900px;
	margin: auto;
}

h1, h2, h3, h4 {
	color: #333;
}

.nav-menu {
	margin-bottom: 20px;
	display: flex;
	flex-wrap: wrap;
	border-bottom: 2px solid #ddd;
}

.nav-menu button {
	padding: 12px 15px; /* Adjust padding for more buttons */
	margin-right: 5px;
	margin-bottom: 5px; /* For wrapping */
	border: none;
	background-color: #e0e0e0;
	color: #555;
	border-top-left-radius: 8px;
	border-top-right-radius: 8px;
	cursor: pointer;
	font-size: 14px; /* Adjust font size for more buttons */
	transition: background-color 0.3s, color 0.3s;
	white-space: nowrap; /* Prevent text wrap inside button */
}

.nav-menu button.active {
	background-color: #007bff;
	color: white;
	border-bottom: 2px solid #007bff; /* Active tab underline */
}

.nav-menu button:hover:not(.active) {
	background-color: #d0d0d0;
}

.screen {
	display: none;
	margin-top: 20px;
	padding-top: 20px;
}

.screen.active {
	display: block;
}

.sub-screen {
	display: none;
	margin-top: 15px;
	padding: 15px;
	border: 1px solid #eee;
	border-radius: 5px;
	background-color: #f9f9f9;
}

.sub-screen.active {
	display: block;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 15px;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
	color: #333;
}

td {
	background-color: #fff;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
	color: #555;
}

.form-group input[type="text"], .form-group input[type="date"],
	.form-group select, .form-group textarea {
	width: calc(100% - 22px);
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 14px;
}

.form-group textarea {
	resize: vertical;
	min-height: 80px;
}

.button-group {
	margin-top: 20px;
	text-align: right;
}

.button-group button {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 15px;
	margin-left: 10px;
	transition: background-color 0.3s, opacity 0.3s;
}

.button-group .submit {
	background-color: #28a745;
	color: white;
}

.button-group .submit:hover {
	background-color: #218838;
}

.button-group .cancel {
	background-color: #6c757d;
	color: white;
}

.button-group .cancel:hover {
	background-color: #5a6268;
}

.action-buttons button {
	padding: 8px 12px;
	margin-right: 5px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s;
}

.action-buttons .apply-btn {
	background-color: #007bff;
	color: white;
}

.action-buttons .apply-btn:hover {
	background-color: #0056b3;
}

.action-buttons .edit-btn {
	background-color: #ffc107;
	color: #333;
}

.action-buttons .edit-btn:hover {
	background-color: #e0a800;
}

.action-buttons .delete-btn {
	background-color: #dc3545;
	color: white;
}

.action-buttons .delete-btn:hover {
	background-color: #c82333;
}
</style>

<h1>학적 변경 신청</h1>

<div class="nav-menu">
	<button id="majorChangeTab" class="active" onclick="showScreen('majorChange', this)">전공 변경</button>
	<button id="multipleMajorTab" onclick="showScreen('multipleMajor', this)">복수전공</button>
	<button id="leaveOfAbsenceTab" onclick="showScreen('leaveOfAbsence', this)">휴학</button>
	<button id="returnToSchoolTab" onclick="showScreen('returnToSchool', this)">복학</button>
	<button id="graduationSuspensionTab" onclick="showScreen('graduationSuspension', this)">졸업 유예</button>	
</div>

<div id="majorChange" class="screen active">
	<h2>전공 변경</h2>

	<h3>현재 전공 변경 신청 현황 조회</h3>
	<p>학적의 전공 변경 탭에서 신청된 전공 변경 신청을 조회할 수 있습니다.</p>
	<table class="defaultTable">
		<thead>
			<tr>
				<th class="tableHead">신청일자</th>
				<th class="tableHead">내용</th>
				<th class="tableHead">처리 현황</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty majorChangeList }">
					<c:forEach items="${majorChangeList }" var="majorChangeList">
						<tr>
							<td class="tableTd">${majorChangeList.requestDate }</td>
							<td class="tableTd">${majorChangeList.reason }</td>
							<td class="tableTd">${majorChangeList.status }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3" class="tableTd">아직 신청 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
	<br>
	<div class="action-buttons">
		<button class="apply-btn"
			onclick="showSubScreen('majorChangeApplyForm', this)">전공 변경
			신청하기</button>
	</div>

	<div id="majorChangeApplyForm" class="sub-screen">
		<h4>전공 변경 신청서 등록</h4>
		<p>전공 변경 신청 화면에서 전공 변경 신청을 등록할 수 있습니다.</p>
		<div class="form-group">
			<label for="newMajorApplyCurrent">현재 전공:</label> <input type="text"
				id="newMajorApplyCurrent" class="inputLabel"
				value="${majorChangeList[0].student.department.departmentName}"
				readonly>
		</div>
		<div class="form-group">
			<label for="newMajorApplyDesired">희망 전공:</label> 
			<select id="newMajorApplyDesired" name="newMajorApplyDesired" class="selectBox" data-init-nm="${majorChangeList[0].student.department.departmentNo }">
				<option value="">선택하세요</option>
			</select>
		</div>
		<div class="form-group">
			<label for="newMajorApplyReason">신청 사유:</label>
			<textarea id="newMajorApplyReason" class="textareaField"
				placeholder="신청 사유를 입력하세요."></textarea>
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitApplication('전공 변경')">신청</button>
			<button class="cancel"
				onclick="hideSubScreen('majorChangeApplyForm')">취소</button>
		</div>
	</div>

	<div id="majorChangeEditForm" class="sub-screen">
		<h4>전공 변경 신청 수정</h4>
		<p>수정 버튼 클릭 시 전공 변경 신청을 변경할 수 있습니다.</p>
		<div class="form-group">
			<label for="editMajorCurrent">현재 전공:</label> <input type="text"
				id="editMajorCurrent" value="컴퓨터공학과" class="inputField" readonly>
		</div>
		<div class="form-group">
			<label for="editMajorDesired">희망 전공:</label> <select
				id="editMajorDesired">
				<option value="software" selected>소프트웨어학과</option>
				<option value="design">디자인학과</option>
				<option value="business">경영학과</option>
			</select>
		</div>
		<div class="form-group">
			<label for="editMajorReason">신청 사유:</label>
			<textarea id="editMajorReason">학업 목표 변경에 따라 전공 변경을 희망합니다.</textarea>
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitModification('전공 변경')">수정
				완료</button>
			<button class="cancel" onclick="hideSubScreen('majorChangeEditForm')">취소</button>
		</div>
	</div>
</div>

<div id="multipleMajor" class="screen">
	<h2>복수전공</h2>

	<h3>현재 복수전공 신청 현황 조회</h3>
	<p>학적의 복수전공 탭에서 신청된 복수전공 신청을 조회할 수 있습니다.</p>
	<table class="defaultTable">
		<thead>
			<tr>
				<th class="tableHead">신청일자</th>
				<th class="tableHead">내용</th>
				<th class="tableHead">처리 현황</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty doubleList }">
					<c:forEach items="${doubleList }" var="doubleList">
						<tr>
							<td>${doubleList.requestDate }</td>
							<td>${doubleList.reason }</td>
							<td>${doubleList.status }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">아직 신청 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<h3>복수전공 신청</h3>
	<div class="action-buttons">
		<button class="apply-btn"
			onclick="showSubScreen('multipleMajorApplyForm', this)">복수전공
			신청하기</button>
	</div>

	<div id="multipleMajorApplyForm" class="sub-screen">
		<h4>복수전공 신청서 등록</h4>
		<p>복수전공 신청 화면에서 복수전공 신청을 등록할 수 있습니다.</p>
		<div class="form-group">
			<label for="newMultipleMajorDesired">희망 복수전공:</label> <select
				id="newMultipleMajorDesired">
				<option value="">선택하세요</option>
				<option value="software">소프트웨어학과</option>
				<option value="design">디자인학과</option>
				<option value="business">경영학과</option>
				<option value="english">영문학과</option>
			</select>
		</div>

		<div class="form-group">
			<label for="newMultipleMajorReason">신청 사유:</label>
			<textarea id="newMultipleMajorReason" class="textareaField"
				placeholder="신청 사유를 입력하세요."></textarea>
		</div>

		<div class="button-group">
			<button class="submit" onclick="submitApplication('복수전공')">신청</button>
			<button class="cancel"
				onclick="hideSubScreen('multipleMajorApplyForm')">취소</button>
		</div>
	</div>

	<div id="multipleMajorEditForm" class="sub-screen">
		<h4>복수전공 신청 수정</h4>
		<p>수정 버튼 클릭 시 복수전공 신청을 변경할 수 있습니다.</p>
		<div class="form-group">
			<label for="editMultipleMajorDesired">희망 복수전공:</label> <select
				id="editMultipleMajorDesired">
				<option value="business" selected>경영학과</option>
				<option value="software">소프트웨어학과</option>
				<option value="design">디자인학과</option>
				<option value="english">영문학과</option>
			</select>
		</div>
		<div class="form-group">
			<label for="editMultipleMajorReason">신청 사유:</label>
			<textarea id="editMultipleMajorReason" class="textareaField">학업 계획 변경으로 희망 복수전공을 수정합니다.</textarea>
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitModification('복수전공')">수정
				완료</button>
			<button class="cancel"
				onclick="hideSubScreen('multipleMajorEditForm')">취소</button>
		</div>
	</div>
</div>

<div id="leaveOfAbsence" class="screen">
	<h2>휴학</h2>

	<h3>현재 휴학 신청 현황 조회</h3>
	<p>학적의 휴학 탭에서 신청된 휴학 신청을 조회할 수 있습니다.</p>
	<p>잔여 휴학 학기 : ${student.remainLeave }</p>
	<table class="defaultTable">
		<thead>
			<tr>
				<th class="tableHead">신청일자</th>
				<th class="tableHead">내용</th>
				<th class="tableHead">처리 현황</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty leaveApplyList }">
					<c:forEach items="${leaveApplyList }" var="leaveApplyList">
						<tr>
							<td>${leaveApplyList.requestDate }</td>
							<td>${leaveApplyList.reason }</td>
							<td>${leaveApplyList.status }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">아직 신청 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<!-- <tr>
				<td>2024-09-01</td>
				<td>일반휴학</td>
				<td>1년</td>
				<td>승인완료</td>
				<td class="action-buttons">
					<button class="edit-btn" disabled title="승인 완료된 신청은 수정할 수 없습니다.">수정</button>
					<button class="delete-btn" disabled title="승인 완료된 신청은 삭제할 수 없습니다.">삭제</button>
				</td>
			</tr>
			<tr>
				<td>2025-06-15</td>
				<td>군휴학</td>
				<td>미정 (입대 대기)</td>
				<td>심사중</td>
				<td class="action-buttons">
					<button class="edit-btn"
						onclick="showSubScreen('leaveEditForm', this)">수정</button>
					<button class="delete-btn"
						onclick="confirmAndDelete('leaveOfAbsence', '2025-06-15')">삭제</button>
				</td>
			</tr> -->
		</tbody>
	</table>

	<h3>휴학 신청</h3>
	<div class="action-buttons">
		<button class="apply-btn"
			onclick="showSubScreen('leaveApplyForm', this)">휴학 신청하기</button>
	</div>

	<div id="leaveApplyForm" class="sub-screen">
		<h4>휴학 신청서 등록</h4>
		<p>휴학 화면에서 휴학 신청을 등록할 수 있습니다.</p>
		<div class="form-group">
			<label for="newLeaveType">휴학 종류:</label> <select id="newLeaveType">
				<option value="">선택하세요</option>
				<option value="general">일반휴학</option>
				<option value="military">군휴학</option>
				<option value="maternity">출산휴학</option>
			</select>
		</div>
		<div class="form-group">
			<label for="newLeavePeriod">휴학 기간:</label> 
			<select id="newLeavePeriod">
				<option value="">선택하세요</option>
				<option value="1">1학기</option>
				<option value="2">2학기</option>
				<option value="3">3학기</option>
				<option value="4">4학기</option>
			</select>
		</div>
		<div class="form-group">
			<label for="newLeaveReason">신청 사유:</label>
			<textarea id="newLeaveReason" placeholder="신청 사유를 입력하세요."></textarea>
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitApplication('휴학')">신청</button>
			<button class="cancel" onclick="hideSubScreen('leaveApplyForm')">취소</button>
		</div>
	</div>

	<div id="leaveEditForm" class="sub-screen">
		<h4>휴학 신청 수정</h4>
		<p>수정 버튼 클릭 시 휴학 신청을 변경할 수 있습니다.</p>
		<div class="form-group">
			<label for="editLeaveType">휴학 종류:</label> <select id="editLeaveType">
				<option value="military" selected>군휴학</option>
				<option value="general">일반휴학</option>
				<option value="maternity">출산휴학</option>
			</select>
		</div>
		<div class="form-group">
			<label for="editLeavePeriod">휴학 기간:</label> 
			<select id="editLeavePeriod">
				<option value="">선택하세요</option>
				<option value="1">1학기</option>
				<option value="2">2학기</option>
				<option value="3">3학기</option>
				<option value="4">4학기</option>
			</select>
		</div>
		<div class="form-group">
			<label for="editLeaveReason">신청 사유:</label>
			<textarea id="editLeaveReason">입대일이 확정되어 휴학 기간을 조정합니다.</textarea>
		</div>
		<div class="form-group">
			<label for="editLeaveAttach">첨부파일 (선택):</label> <input type="file"
				id="editLeaveAttach">
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitModification('휴학')">수정
				완료</button>
			<button class="cancel" onclick="hideSubScreen('leaveEditForm')">취소</button>
		</div>
	</div>
</div>

<div id="returnToSchool" class="screen">
	<h2>복학</h2>

	<h3>현재 복학 신청 현황 조회</h3>
	<p>학적의 복학 탭에서 신청된 복학 신청을 조회할 수 있습니다.</p>
	<table class="defaultTable">
		<thead>
			<tr>
				<th class="tableHead">신청일자</th>
				<th class="tableHead">내용</th>
				<th class="tableHead">처리 현황</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty returnList }">
					<c:forEach items="${returnList }" var="returnList">
						<tr>
							<td>${returnList.requestDate }</td>
							<td>${returnList.reason }</td>
							<td>${returnList.status }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">아직 신청 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<h3>복학 신청</h3>
	<div class="action-buttons">
		<button class="apply-btn"
			onclick="showSubScreen('returnApplyForm', this)">복학 신청하기</button>
	</div>

	<div id="returnApplyForm" class="sub-screen">
		<h4>복학 신청서 등록</h4>
		<p>복학 신청 화면에서 복학 신청을 등록할 수 있습니다.</p>
		<div class="form-group">
			<label for="newReturnSemester">복학 희망 학기:</label> <select
				id="newReturnSemester">
				<option value="">선택하세요</option>
				<option value="2025-2">2025학년도 2학기</option>
				<option value="2026-1">2026학년도 1학기</option>
			</select>
		</div>
		<div class="form-group">
			<label for="newReturnReason">신청 사유:</label>
			<textarea id="newReturnReason" placeholder="신청 사유를 입력하세요."></textarea>
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitApplication('복학')">신청</button>
			<button class="cancel" onclick="hideSubScreen('returnApplyForm')">취소</button>
		</div>
	</div>

	<div id="returnEditForm" class="sub-screen">
		<h4>복학 신청 수정</h4>
		<p>수정 버튼 클릭 시 복학 신청을 변경할 수 있습니다.</p>
		<div class="form-group">
			<label for="editReturnSemester">복학 희망 학기:</label> <select
				id="editReturnSemester">
				<option value="2025-2" selected>2025학년도 2학기</option>
				<option value="2026-1">2026학년도 1학기</option>
			</select>
		</div>
		<div class="form-group">
			<label for="editReturnReason">신청 사유:</label>
			<textarea id="editReturnReason">개인 사정 변경으로 복학 희망 학기를 수정합니다.</textarea>
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitModification('복학')">수정
				완료</button>
			<button class="cancel" onclick="hideSubScreen('returnEditForm')">취소</button>
		</div>
	</div>
</div>

<div id="graduationSuspension" class="screen">
	<h2>졸업 유예</h2>

	<h3>현재 졸업 유예 신청 현황 조회</h3>
	<p>학적의 졸업 유예 탭에서 신청된 졸업 유예 신청을 조회할 수 있습니다.</p>
	<table class="defaultTable">
		<thead>
			<tr>
				<th class="tableHead">신청일자</th>
				<th class="tableHead">내용</th>
				<th class="tableHead">처리 현황</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty graduationSuspensionList }">
					<c:forEach items="${graduationSuspensionList }" var="graduationSuspensionList">
						<tr>
							<td>${graduationSuspensionList.requestDate }</td>
							<td>${graduationSuspensionList.reason }</td>
							<td>${graduationSuspensionList.status }</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">아직 신청 없음</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<h3>졸업 유예 신청</h3>
	<div class="action-buttons">
		<button class="apply-btn"
			onclick="showSubScreen('graduationSuspensionForm', this)">졸업 유예 신청하기</button>
	</div>

	<div id="graduationSuspensionForm" class="sub-screen">
		<h4>졸업 유예 신청서 등록</h4>
		<p>졸업 유예 신청 화면에서 졸업 유예 신청을 등록할 수 있습니다.</p>
		
		<div class="form-group">
			<label for="newSuspensionReason">신청 사유:</label>
			<textarea id="newSuspensionReason" placeholder="신청 사유를 입력하세요."></textarea>
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitApplication('졸업 유예')">신청</button>
			<button class="cancel" onclick="hideSubScreen('graduationSuspensionForm')">취소</button>
		</div>
	</div>

	<div id="suspensionEditForm" class="sub-screen">
		<h4>졸업 유예 신청 수정</h4>
		<p>수정 버튼 클릭 시 졸업 유예 신청을 변경할 수 있습니다.</p>
		
		<div class="form-group">
			<label for="editSuspesionReason">신청 사유:</label>
			<textarea id="editSuspesionReason"></textarea>
		</div>
		<div class="button-group">
			<button class="submit" onclick="submitModification('졸업 유예')">수정
				완료</button>
			<button class="cancel" onclick="hideSubScreen('suspensionEditForm')">취소</button>
		</div>
	</div>
</div>

<script>
	function showScreen(screenId, clickedButton) {
	    // Hide all main screens
	    const screens = document.querySelectorAll('.screen');
	    screens.forEach(screen => {
	        screen.classList.remove('active');
	    });
	    // Show the selected main screen
	    document.getElementById(screenId).classList.add('active');
	
	    // Deactivate all nav buttons
	    const navButtons = document.querySelectorAll('.nav-menu button');
	    navButtons.forEach(button => {
	        button.classList.remove('active');
	    });
	    // Activate the clicked button
	    if (clickedButton) { // Ensure clickedButton is passed
	        clickedButton.classList.add('active');
	    } else { // Fallback for initial load if not using button
	         document.getElementById(screenId + 'Tab').classList.add('active');
	    }
	
	
	    // Hide all sub-screens (forms) when switching main screens
	    const subScreens = document.querySelectorAll('.sub-screen');
	    subScreens.forEach(subScreen => {
	        subScreen.classList.remove('active');
	    });
	}
	
	function showSubScreen(subScreenId) {
	    // Hide all sub-screens within the current main screen
	    const currentMainScreen = document.querySelector('.screen.active');
	    if (currentMainScreen) {
	        const subScreensInCurrent = currentMainScreen.querySelectorAll('.sub-screen');
	        subScreensInCurrent.forEach(subScreen => {
	            subScreen.classList.remove('active');
	        });
	    }
	    // Show the selected sub-screen
	    document.getElementById(subScreenId).classList.add('active');
	}
	
	function hideSubScreen(subScreenId) {
	    document.getElementById(subScreenId).classList.remove('active');
	}
	
	function confirmAndDelete(type, id) {
	    const typeNameMap = {
	        'majorChange': '전공 변경 신청',
	        'multipleMajor': '복수전공 신청',
	        'cancelMultipleMajor': '복수전공 취소 신청',
	        'leaveOfAbsence': '휴학 신청',
	        'returnToSchool': '복학 신청',
	        'graduationSuspension' : '졸업 유예 신청',
	        'reAdmission': '재입학 신청',
	        'withdrawal': '자퇴 신청'
	    };
	    const displayTypeName = typeNameMap[type] || '신청'; // Fallback
	
	    if (confirm(`정말 ${displayTypeName}(${id})을(를) 삭제하시겠습니까?`)) {
	        alert(`${displayTypeName}이(가) 삭제되었습니다. (ID: ${id})`);
	        // TODO: 여기에 실제 삭제 로직 (예: API 호출, UI에서 해당 행 제거) 추가
	    }
	}
	
	function submitApplication(type) {
	    const applyDesired = document.getElementById('newMajorApplyDesired').value;
	    const applyReason = document.getElementById('newMajorApplyReason').value;
	    
	    const multipleMajorDesired = document.getElementById('newMultipleMajorDesired').value;
		const multipleMajorReason = document.getElementById('newMultipleMajorReason').value;
		
		const leaveType = document.getElementById('newLeaveType').value; 
		const leavePeriod = document.getElementById('newLeavePeriod').value;
		const leaveReason = document.getElementById('newLeaveReason').value;
		
		const returnSemester = document.getElementById('newReturnSemester').value;
		const returnReason = document.getElementById('newReturnReason').value;
		
		const suspensionReason = document.getElementById('newSuspensionReason').value;

	    /* const payload = {
	        semesterNo: applyDesired, // 여기서는 예제상 semesterNo로 전달
	        reason: applyReason
	    }; */
	    
	    const applyData = {
		    desiredMajor: applyDesired,
		    reason: applyReason
		};
		
		const majorChangeData = {
			desiredMajor: multipleMajorDesired,
		    reason: multipleMajorReason
		};
		
		const leaveApplyData = {
			desiredMajor: leaveType,
			period: leavePeriod,
		    reason: leaveReason
		}
		
		const returnData = {
			desiredMajor: returnSemester,
			reason: returnReason
		}
		
		const suspensionData = {
		    reason: suspensionReason
		};

	    if (type === '전공 변경') {
	        fetch('/student/counsel/majorChangeInsert.do', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify(applyData)
	        }).then(res => res.text())
	          .then(res => {
	              alert('전공 변경 신청이 완료되었습니다.');
	              location.reload();
	              hideSubScreen('majorChangeApplyForm');
	          }).catch(err => {
	              alert('신청 중 오류가 발생했습니다.');
	          })
	        
	    } else if (type === '복수전공') {
			fetch('/student/counsel/doubleMajorInsert.do', {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json'
				},
				body : JSON.stringify(majorChangeData)
			}).then(res => res.text())
	      	  .then(res => {
				alert('복수 전공 신청이 완료되었습니다.');
				location.reload();
				hideSubScreen('multipleMajorApplyForm');
		    }).catch(err => {
				alert('신청 중 오류가 발생했습니다.');
		    })
			
	    } else if (type === '휴학') {
	    	fetch('/student/counsel/leaveApplyInsert.do', {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json'
				},
				body : JSON.stringify(leaveApplyData)
			}).then(res => res.text())
	      	  .then(res => {
				alert('휴학 신청이 완료되었습니다.');
				location.reload();
				hideSubScreen('leaveApplyForm');
		    }).catch(err => {
				alert('신청 중 오류가 발생했습니다.');
		    })
			
	    } else if (type === '복학') {
	    	fetch('/student/counsel/returnApplyInsert.do', {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json'
				},
				body : JSON.stringify(returnData)
			}).then(res => res.text())
	      	  .then(res => {
				alert('복학 신청이 완료되었습니다.');
				location.reload();
				hideSubScreen('returnApplyForm');
		    }).catch(err => {
				alert('신청 중 오류가 발생했습니다.');
		    })
	    	
	    } else if (type === '졸업 유예') {
	        fetch('/student/counsel/graduationSuspensionInsert.do', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify(suspensionData)
	        }).then(res => res.text())
	          .then(res => {
	            alert('졸업 유예 신청이 완료되었습니다.');
	            location.reload();
	            hideSubScreen('graduationSuspensionForm');
	        }).catch(err => {
	            alert('신청 중 오류가 발생했습니다.');
	        })
	    }
	}
	
	/* function submitApplication(type) {
		const applyDesired = document.getElementById('newMajorApplyDesired').value;
		const applyReason = document.getElementById('newMajorApplyReason').value;
		
		const multipleMajorDesired = document.getElementById('newMultipleMajorDesired').value;
		const multipleMajorReason = document.getElementById('newMultipleMajorReason').value;
		
		const leaveType = document.getElementById('newLeaveType').value; 
		const leavePeriod = document.getElementById('newLeavePeriod').value;
		const leaveReason = document.getElementById('newLeaveReason').value;
		
		const returnSemester = document.getElementById('newReturnSemester').value;
		const returnReason = document.getElementById('newReturnReason').value;

		const applyData = {
		    desiredMajor: applyDesired,
		    reason: applyReason
		};
		
		const majorChangeData = {
			desiredMajor: multipleMajorDesired,
		    reason: multipleMajorReason
		};
		
		const leaveApplyData = {
			desiredMajor: leaveType,
			period: leavePeriod,
		    reason: leaveReason
		}
		
		const returnData = {
			desiredMajor: returnSemester,
			reason: returnReason
		}
		
	    alert(`${type} 신청이 완료되었습니다.`);
	    
	    if (type === '전공 변경') {
		    fetch('/student/counsel/majorChangeInsert.do', {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json'
				},
				body : JSON.stringify(applyData)
		    }).then(res => res.text())
		      .then(res => {
				alert('전공 변경 신청이 완료되었습니다.');
		    	
		    	location.reload();

		    	hideSubScreen('majorChangeApplyForm');
		    }).catch(err => {
				alert('신청 중 오류가 발생했습니다.');
		    })
		}
	    
	    else if (type === '복수전공') {
			fetch('/student/counsel/doubleMajorInsert.do', {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json'
				},
				body : JSON.stringify(majorChangeData)
			}).then(res => res.text())
	      	  .then(res => {
				alert('전공 변경 신청이 완료되었습니다.');
		    	
				hideSubScreen('multipleMajorApplyForm');
		    }).catch(err => {
				alert('신청 중 오류가 발생했습니다.');
		    })
	    }
	    
	    else if (type === '휴학') {
	    	fetch('/student/counsel/leaveApplyInsert.do', {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json'
				},
				body : JSON.stringify(leaveApplyData)
			}).then(res => res.text())
	      	  .then(res => {
				alert('전공 변경 신청이 완료되었습니다.');
		    	
				hideSubScreen('leaveApplyForm');
		    }).catch(err => {
				alert('신청 중 오류가 발생했습니다.');
		    })
			
	    }
	    
	    else if (type === '복학') {
	    	fetch('/student/counsel/returnApplyInsert.do', {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json'
				},
				body : JSON.stringify(returnData)
			}).then(res => res.text())
	      	  .then(res => {
				alert('전공 변경 신청이 완료되었습니다.');
		    	
				hideSubScreen('returnApplyForm');
		    }).catch(err => {
				alert('신청 중 오류가 발생했습니다.');
		    })
			
	    }
	} */
	
	function submitModification(type) {
	    alert(`${type} 신청이 수정되었습니다.`);
	    // TODO: 여기에 실제 수정 데이터 전송 로직 (예: API 호출) 추가
	    // 수정 완료 후 해당 폼 숨기기
	    if (type === '전공 변경') hideSubScreen('majorChangeEditForm');
	    else if (type === '복수전공') hideSubScreen('multipleMajorEditForm');
	    else if (type === '휴학') hideSubScreen('leaveEditForm');
	    else if (type === '복학') hideSubScreen('returnEditForm');
	}
	
	// Initial screen load
	document.addEventListener('DOMContentLoaded', () => {
	    showScreen('majorChange', document.getElementById('majorChangeTab'));
	});
</script>