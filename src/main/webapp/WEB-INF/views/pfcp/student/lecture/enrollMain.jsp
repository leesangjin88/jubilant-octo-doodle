<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>수강신청</title>
<!-- LCMS 스타일 가이드 적용 -->
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css" />
<style type="text/css">
.container {
	max-width: 1000px;
	margin: 0 auto;
	padding: 2rem;
}

.summary-grid {
	display: flex;
	flex-wrap: wrap;
	gap: 1rem;
	margin-bottom: 2rem;
}

.summary-grid>div {
	flex: 1;
	background: #fff;
	padding: 1rem;
	border: 1px solid #ddd;
	border-radius: 0.5rem;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
	text-align: center;
}

.summary-grid span {
	display: block;
	margin-top: 0.5rem;
	font-size: 1.2rem;
	font-weight: bold;
	color: #2b77ef;
}

.tab-menu {
	display: flex;
	gap: 0.5rem;
	margin-bottom: 1.5rem;
}

.tab-menu button {
	padding: 0.5rem 1rem;
	background: #e9ecef;
	border: none;
	border-radius: 1.5rem;
	cursor: pointer;
	font-weight: 500;
}

.tab-menu button.active {
	background: #2b77ef;
	color: white;
}

.add-button {
	text-align: right;
	margin-bottom: 1rem;
}

.btn-add {
	background: #2b77ef;
	color: white;
	padding: 0.5rem 1rem;
	border: none;
	border-radius: 0.5rem;
	cursor: pointer;
	font-size: 1rem;
}

table {
	width: 100%;
	border-collapse: collapse;
	background-color: white;
	border: 1px solid #dee2e6;
}

table th, table td {
	padding: 0.75rem;
	text-align: center;
	border-bottom: 1px solid #dee2e6;
}

table th {
	background-color: #f1f3f5;
	font-weight: 600;
}

table td a {
	margin: 0 0.25rem;
	text-decoration: none;
	font-size: 1.1rem;
}

tr.canceled {
	background-color: #f1f3f5;
	color: #888;
}

.timetable-table {
	width: 100%;
	border-collapse: collapse;
	table-layout: fixed;
}

.timetable-table th, .timetable-table td {
	border: 1px solid #ccc;
	height: 80px;
	text-align: center;
	vertical-align: middle;
	padding: 5px;
	overflow-wrap: break-word;
}

.notice-banner {
	background-color: #e6f2ff;
	border-radius: 12px;
	padding: 1rem 1.5rem;
	margin: 1.5rem 0;
	border: 1px solid #b3d7ff;
	color: #003366;
	font-size: 1rem;
	line-height: 1.5;
	box-shadow: 0 2px 4px rgba(0, 102, 204, 0.1);
}

.notice-banner strong {
	font-size: 1.1rem;
	color: #2b77ef;
}

.form-row {
	display: flex;
	flex-wrap: wrap;
	gap: 1rem;
}

.form-col {
	flex: 1 1 calc(50% - 0.5rem); /* 두 개씩 나열되도록 설정 */
	margin-bottom: 1rem; /* 각 항목의 아래 여백 */
}

.form-group label {
	display: block;
	margin-bottom: 0.5rem; /* 레이블과 입력 필드 사이의 간격 */
}

.form-control {
	width: 100%;
	padding: 0.5rem;
	border: 1px solid #ddd;
	border-radius: 0.25rem;
}

.button-group {
	display: flex;
	gap: 0.5rem;
	justify-content: flex-start;
}

.cancelButton, .searchButton {
	padding: 0.75rem 1.5rem;
	border: 1px solid #007bff;
	background-color: #007bff;
	color: white;
	border-radius: 0.25rem;
	text-decoration: none;
	text-align: center;
}

.cancelButton:hover, .searchButton:hover {
	background-color: #0056b3; /* 버튼 hover 효과 */
}
</style>
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function () {
	document.querySelectorAll('[data-tab]').forEach(tab => {
	  tab.addEventListener('click', function () {
	    const selected = this.dataset.tab;
	
	    // 탭 활성화 처리
	    document.querySelectorAll('.tab').forEach(btn => btn.classList.remove('active'));
	    this.classList.add('active');
	
	    // 콘텐츠 보이기 처리
	    document.querySelectorAll('[data-tab-content]').forEach(div => {
	      div.style.display = div.dataset.tabContent === selected ? 'block' : 'none';
	    });
	    
	  });
	  
	});
});

	

</script>
</head>
<body>
	<c:if test="${not empty scheduleNotice}">
		<div class="notice-banner">
			<strong>📢 수강신청 안내:</strong> ${scheduleNotice}
		</div>
	</c:if>
	<div class="container">
		<h1>수강신청 페이지</h1>
		<div class="tab-menu">
			<button data-tab="enroll" class="tab active">수강신청</button>
			<!-- <button data-tab="enrollEdit" class="tab">수강신청 내역</button> -->
			<button data-tab="timetable" class="tab">시간표</button>
			<button data-tab="cart" class="tab">예비신청</button>
		</div>

		<div class="tab-content" data-tab-content="enroll">
			<%-- ${lectureList } --%>
			<table>
				<h3>신청 가능한 교과목</h3>
				<span>총 ${fn:length(lectureList)}개의 강의가 검색되었습니다.</span>


				<!-- 필터 토글 버튼 -->
				<div class="form-group" style="margin-bottom: 1rem;">
					<button type="button" class="searchButton" onclick="toggleFilter()"
						id="toggleFilter">필터 열기</button>
				</div>

				<!-- 필터 적용 부분 -->
				<div id="filterSection" style="display: none;" class="card">
					<form method="get" action="/student/lecture/enroll"
						class="form-group mb-2">
						<div class="form-row">
							<div class="form-group form-col">
								<label for="departmentNo">학과</label> <select name="departmentNo"
									id="departmentNo" class="form-control">
									<option value="">전체</option>
									<c:forEach var="dept" items="${departmentList}">
										<option value="${dept.departmentNo}"
											${param.departmentNo == dept.departmentNo ? 'selected' : ''}>${dept.departmentName}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group form-col">
								<label for="day">요일</label> <select name="day" id="day"
									class="form-control">
									<option value="">전체</option>
									<c:forEach var="day" items="${dayList}">
										<option value="${day}" ${param.day == day ? 'selected' : ''}>${day}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-row">
							<div class="form-group form-col">
								<label for="period">교시</label> <select name="period" id="period"
									class="form-control">
									<option value="">전체</option>
									<c:forEach var="period" items="${periodList}">
										<option value="${period}"
											${param.period == period ? 'selected' : ''}>${period}</option>
									</c:forEach>
								</select>
							</div>

							<div class="form-group form-col">
								<label for="credit">학점</label> <select name="credit" id="credit"
									class="form-control">
									<option value="">전체</option>
									<c:forEach var="cr" items="${creditList}">
										<option value="${cr}" ${param.credit == cr ? 'selected' : ''}>${cr}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<!-- 버튼 그룹 -->
						<div class="form-group form-col">
							<div class="button-group">
								<a href="/student/lecture/enroll" class="cancelButton">초기화</a>
								<button type="submit" class="searchButton">검색</button>
							</div>
						</div>

					</form>
				</div>



				<table>
					<thead>
						<tr>
							<th></th>
							<th>과목명</th>
							<th>강의명</th>
							<th>교수명</th>
							<th>학점</th>
							<th>시간</th>
							<th>정원</th>
							<th>예비신청</th>
							<th>신청</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="lecture" items="${lectureList}" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${lecture.subject.subjectName}</td>
								<td>${lecture.lectureReq.lecName }</td>
								<td>${lecture.user.userName}</td>
								<td>${lecture.subject.credit}</td>
								<td>${lecture.lectureReq.preDay}-${lecture.lectureReq.preTime}</td>
								<td>${lecture.currentEnrollment}/${lecture.lectureReq.maxCapacity}</td>
								<td>
									<button type="submit" class="saveButton"
										onclick="applyPreLecture('${lecture.lecNo}')">🛒 담기</button>
								</td>
								<td>
									<%-- 신청 여부 확인 --%> <c:set var="isEnrolled" value="false" /> <c:forEach
										var="enr" items="${myEnrollments}">
										<c:if
											test="${enr.lecNo eq lecture.lecNo && enr.enrollStatus eq '수강중'}">
											<c:set var="isEnrolled" value="true" />
										</c:if>
									</c:forEach> <%-- 버튼 분기 처리 --%> <c:choose>
										<c:when test="${isEnrolled}">
											<button class="btn-disabled saveButton" disabled>신청됨</button>
										</c:when>
										<c:when
											test="${lecture.currentEnrollment >= lecture.lectureReq.maxCapacity}">
											<button class="btn-disabled deleteButton" disabled>마감</button>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${not isEnrollPeriod}">
													<button class="btn-disabled" disabled>신청불가</button>
												</c:when>
												<c:otherwise>
													<button class="btn-add"
														onclick="applyLecture('${lecture.lecNo}')">신청</button>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<br>
				<hr />
				<!--=========================================================  -->
				<h3>내 수강신청 목록</h3>
				<table>
					<thead>
						<tr>
							<th>강의명</th>
							<th>교수명</th>
							<th>요일</th>
							<th>시간</th>
							<th>신청일시</th>
							<th>취소</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="enroll" items="${myEnrollList}">
							<tr class="${enroll.enrollStatus eq '취소' ? 'canceled' : ''}">
								<td>${enroll.subjectName}</td>
								<td>${enroll.professorName}</td>
								<td>${enroll.preDay}</td>
								<td>${enroll.preTime}</td>
								<td>${enroll.enrollTime}</td>
								<td><c:choose>
										<c:when test="${enroll.enrollStatus eq '수강중'}">
											<button onclick="cancelLecture('${enroll.lecNo}')"
												class="btn-add deleteButton">수강취소</button>
										</c:when>
										<c:otherwise>
											<span style="color: gray;" class="canceled">${enroll.enrollStatus}됨</span>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</table>
		</div>

		<%-- <div class="tab-content" data-tab-content="enrollEdit"
			style="display: none;">
			${myEnrollList}
			<table>
				<thead>
					<tr>
						<th>강의명</th>
						<th>교수명</th>
						<th>요일</th>
						<th>시간</th>
						<th>신청일시</th>
						<th>취소</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="enroll" items="${myEnrollList}">
						<tr class="${enroll.enrollStatus eq '취소' ? 'canceled' : ''}">
							<td>${enroll.subjectName}</td>
							<td>${enroll.professorName}</td>
							<td>${enroll.preDay}</td>
							<td>${enroll.preTime}</td>
							<td>${enroll.enrollTime}</td>
							<td><c:choose>
									<c:when test="${enroll.enrollStatus eq '수강중'}">
										<button onclick="cancelLecture('${enroll.lecNo}')"
											class="btn-add deleteButton">수강취소</button>
									</c:when>
									<c:otherwise>
										<span style="color: gray;" class="canceled">${enroll.enrollStatus}됨</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div> --%>


		<div class="tab-content" data-tab-content="timetable"
			style="display: none;">
			<%-- ${myLectureEnrollments } --%>

			<c:set var="totalCredit" value="0" />
			<c:forEach var="lecture" items="${myLectureEnrollments}">
				<c:set var="totalCredit" value="${totalCredit + lecture.subject.credit}" />
			</c:forEach>
			<h3>시간표</h3>
			<div style="margin-bottom: 1rem; font-weight: bold;">🧮 총 학점:
				${totalCredit}학점
			</div>
			<table border="1" style="width: 100%; text-align: center;"
				class="timetable-table">
				<thead>
					<tr>
						<th>시간</th>
						<th>월</th>
						<th>화</th>
						<th>수</th>
						<th>목</th>
						<th>금</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="period" begin="1" end="9">
						<tr>
							<td>${period}교시</td>
							<c:forEach var="day" items="${['월','화','수','목','금']}">
								<td><c:forEach var="lecture"
										items="${myLectureEnrollments}">
										<c:if test="${lecture.lectureReq != null}">
											<c:forEach var="d" items="${lecture.lectureReq.dayList}">
												<c:forEach var="p" items="${lecture.lectureReq.periodList}">
													<c:if test="${d eq day and p eq period}">
														<div style="font-weight: bold;">
															${lecture.subject.subjectName}<br /> <small>(${lecture.user.userName})</small>
														</div>
													</c:if>
												</c:forEach>
											</c:forEach>
										</c:if>
									</c:forEach></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>



		<div class="tab-content" data-tab-content="cart"
			style="display: none;">
			<table class="tableFormat">
				<thead>
					<tr>
						<th>과목명</th>
						<th>교수명</th>
						<th>요일</th>
						<th>시간</th>
						<th>학점</th>
						<th>우선순위</th>
						<th>신청일</th>
						<th>관리</th>

					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty cartList}">
							<c:forEach var="cart" items="${cartList}">
								<tr>
									<td>${cart.subjectName}</td>
									<td>${cart.professorName}</td>
									<td>${cart.preDay}</td>
									<td>${cart.preTime}</td>
									<td>${cart.credit}</td>
									<td>${cart.priority}</td>
									<td>${cart.enrollTime}</td>
									<td>
										<button type="submit" class="deleteButton"
											onclick="cancelPreLecture('${cart.lecNo}')">삭제</button> <c:if
											test="${isEnrollPeriod}">
											<button type="submit" class="submitButton"
												onclick="applyLecture('${cart.lecNo}')">신청</button>
										</c:if>

									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="8" class="centerText">예비 수강신청한 강의가 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	/*수강신청  */
	function applyLecture(lecNo) {
	    if (!confirm(lecNo + " 강의를 신청하시겠습니까?")) return;

	    fetch('/student/lecture/enroll/'+ encodeURIComponent(lecNo), {
	      method: 'POST',
	      credentials: 'include'
	    })
	    .then(res => {
	        if (res.status === 409) return res.text().then(msg => { throw new Error(msg); });
	        if (!res.ok) throw new Error('알 수 없는 오류');
	        return res.text();
	      })
	      .then(() => {
	        alert("신청 완료!");
	        location.reload();
	      })
	      .catch(err => {
	        alert(err.message); 
	      });
	    }
	
	/*수강취소 */
	function cancelLecture(lecNo) {
		  if (!confirm("이 강의를 수강취소 하시겠습니까?")) return;

		  fetch('/student/lecture/enroll/' + encodeURIComponent(lecNo), {
		    method: 'DELETE',
		    credentials: 'include'
		  })
		  .then(res => {
		    if (!res.ok) throw new Error("취소 실패");
		    return res.text();
		  })
		  .then(() => {
		    alert("수강취소 완료!");
		    location.reload();
		  })
		  .catch(err => {
		    alert("❗ " + err.message);
		  });
		}
	function applyPreLecture(lecNo) {
		  fetch(`/student/lecture/enroll/cart/\${lecNo}`, {
		    method: 'POST'
		  })
		  .then(res => {
		    if (res.ok) {
		      alert('장바구니에 추가되었습니다.');
		      location.reload();
		    } else if (res.status === 409) {
		      res.text().then(msg => alert('❗ ' + msg));
		    } else {
		      alert('알 수 없는 오류가 발생했습니다.');
		    }
		  })
		  .catch(error => {
		    console.error('Error:', error);
		    alert('서버 통신 오류 발생');
		  });
		}
	function cancelPreLecture(lecNo) {
		if (!confirm("이 강의를 수강취소 하시겠습니까?")) return;
		fetch("/student/lecture/enroll/cart/" + encodeURIComponent(lecNo), {
			  method: 'DELETE'
		})
		.then(res => {
		    if (!res.ok) throw new Error("취소 실패");
		    return res.text();
		 })
		 .then(() => {
			alert("수강취소 완료!");
		 	location.reload();
		 })
		 .catch(err => {
		    alert("❗ " + err.message);
		 });
	}
	function toggleFilter() {
	    const filter = document.getElementById('filterSection');
	    const toggleBtn = document.getElementById("toggleFilter");
	    if (filter.style.display === 'none' || filter.style.display === '') {
	      filter.style.display = 'block';
	      toggleBtn.textContent = "필터 닫기";
	    } else {
	      filter.style.display = 'none';
	      toggleBtn.textContent = "필터 열기";
	    }
	  }

</script>
</body>
</html>
