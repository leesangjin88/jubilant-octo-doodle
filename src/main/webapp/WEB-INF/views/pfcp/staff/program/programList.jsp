<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>비교과 프로그램 관리</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css" />
<link rel="stylesheet" href="/dist/assets/css/attendclass.css">
<style>
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

.stat-update-time {
	font-size: 0.9rem;
	color: #888;
	text-align: right;
	margin-bottom: 0.5rem;
}

.ahover:hover {
	background: #f1f3f5;
}
</style>
<script>
	
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

	      // ✅ 신청 관리 탭 클릭 시 
	      if (selected === 'apply') {
	        loadProgramEnrollList({ page: 1 });
	      }
	    });
	  });
	});
function goToApplyPage(programNo) {
	location.href = '/staff/program/apply/' + programNo;
}

function goToDetail(programNo) {
	  location.href = '/staff/program/edit/' + programNo;
}
function deleteProgram(programNo) {
	  if (!confirm("정말 이 프로그램을 삭제하시겠습니까?")) return;

	  fetch('/staff/program/delete/' + programNo, {
	    method: 'Delete'
	  })
	  .then(res => {
	    if (res.ok) {
	      alert("삭제되었습니다.");
	      location.reload();
	    } else {
	      alert("삭제 실패");
	    }
	  })
	  .catch(() => alert("서버 오류"));
}
</script>

</head>
<body>
	<div class="container">
		<h1>비교과 프로그램 관리 시스템</h1>
		<p class="stat-update-time">
			마지막 갱신 시각: <strong>${stat.regDate}</strong>
		</p>


		<div class="summary-grid">
			<div>
				전체 프로그램<span>${stat.totalPrograms }</span>
			</div>
			<div>
				총 참여자 수<span>${stat.totalApplicants }</span>
			</div>
			<div>
				이번 달 신청 수<span>${stat.monthlyApply }</span>
			</div>
			<div>
				완료율<span>${stat.completeRate }%</span>
			</div>
		</div>

		<div class="tab-menu">
			<button data-tab="program" class="tab active">프로그램 관리</button>
			<button data-tab="apply" class="tab">신청 관리</button>
			<button data-tab="followup" class="tab">사후 관리</button>
			<button data-tab="statistics" class="tab">통계 분석</button>
			<button data-tab="survey" class="tab">설문 관리</button>

		</div>


		<!--비교과 프로그램 관리 탭  -->
		<div class="tab-content" data-tab-content="program"
			style="display: block;">
			<h3>비교과 프로그램 관리</h3>
			<div class="add-button">

				<button type="button" class="btn-add"
					onclick="location.href='/staff/program/form'">새 프로그램 추가</button>
			</div>
			<table>
				<thead>
					<tr>
						<th>프로그램명</th>
						<th>기간</th>
						<th>상태</th>
						<th>유형</th>
						<th>정원</th>
						<th>장소</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${programList}">
						<tr class="ahover">
							<td onclick="goToDetail('${p.programNo}')">${p.programTitle}</td>
							<td onclick="goToDetail('${p.programNo}')">${p.startDate}~${p.endDate}</td>
							<td onclick="goToDetail('${p.programNo}')"><c:choose>
									<c:when test="${p.programActive eq 'N'}">
										모집중
									</c:when>
									<c:when test="${p.programActive eq 'Y'}">
										진행중
									</c:when>
									<c:when test="${p.programActive eq 'C'}">
										완료
									</c:when>
								</c:choose></td>
							<td onclick="goToDetail('${p.programNo}')">${p.type.typeName }</td>
							<td onclick="goToDetail('${p.programNo}')">${p.programCapacity }</td>
							<td onclick="goToDetail('${p.programNo}')">${p.place }</td>
							<td>
								<button onclick="deleteProgram('${p.programNo}')" title="삭제"
									style="background: none; border: none; cursor: pointer;">
									🗑</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<div class="tab-content" data-tab-content="apply"
			style="display: none;">
			<div class="section">
				<div class="sectionHeaderLine">
					<div>
						<div class="sectionHeaderTitle">비교과 프로그램 신청 관리</div>
					</div>
				</div>

				<div class="lecture-list-container">
					<c:set var="displayedCount" value="0" />
					<c:choose>
						<c:when test="${not empty openProgramList}">
							<c:forEach var="p" items="${openProgramList}">
								<c:url value="/staff/program/apply/${p.programNo}"
									var="detailURL" />
								<a href="${detailURL}" class="lecture-item-link">
									<div class="lecture-item-content">
										<div class="lecture-info-group">
											<div class="lecture-icon-wrapper">
												<svg xmlns="http://www.w3.org/2000/svg" class="lecture-icon"
													fill="none" viewBox="0 0 24 24" stroke="currentColor">
											<path stroke-linecap="round" stroke-linejoin="round"
														stroke-width="2" d="M9 5l7 7-7 7" />
										</svg>
											</div>
											<div>
												<span class="lecture-name">${p.programTitle}</span>
												<p class="lecture-details">
													・ 프로그램 번호: ${p.programNo}<br> ・ 유형: ${p.type.typeName}<br>
													・ 기간: ${p.startDate} ~ ${p.endDate}<br> ・ 정원:
													${p.programCapacity}명
												</p>
											</div>
										</div>
									</div>
								</a>
								<c:set var="displayedCount" value="${displayedCount + 1}" />
							</c:forEach>
						</c:when>
					</c:choose>

					<c:if test="${displayedCount == 0}">
						<div class="no-lecture-message">모집 중인 비교과 프로그램이 없습니다.</div>
					</c:if>
				</div>
			</div>
		</div>



		<div class="tab-content" data-tab-content="followup"
			style="display: none;">
			<table>
				<p>사후관리</p>
			</table>
		</div>
		<div class="tab-content" data-tab-content="statistics"
			style="display: none;">
			<table>
				<p>통계분석</p>
			</table>
		</div>
		<div class="tab-content" data-tab-content="survey"
			style="display: none;">
			<table>
				<p>설문관리</p>
			</table>
		</div>
	</div>
</body>
</html>



