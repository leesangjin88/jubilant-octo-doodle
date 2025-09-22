<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 

<style>
	.sidebar-wrapper, .sidebar-content, .nav {
		transition: none !important;
	}
</style>

<div class="sidebar" data-background-color="dark"> 
	<div class="sidebar-logo">
		<!-- Logo Header -->
		<div class="logo-header" data-background-color="dark">
			<a href="/" class="logo"> 
				<img src="/dist/assets/img/kaiadmin/logo_pathFinder_white.png"
					 alt="navbar brand" class="navbar-brand" height="50" width="150" />
			</a>
			<div class="nav-toggle">
				<button class="btn btn-toggle toggle-sidebar">
					<i class="gg-menu-right"></i>
				</button>
				<button class="btn btn-toggle sidenav-toggler">
					<i class="gg-menu-left"></i>
				</button>
			</div>
			<button class="topbar-toggler more">
				<i class="gg-more-vertical-alt"></i>
			</button>
		</div>
	</div>
	
	<!-- Role Selector -->
	<div class="role-selector" style="padding: 15px;">
		<label for="roleSelector" class="visually-hidden">Select Role</label>
		<select id="roleSelector" class="form-select">
			<option value="staff">교직원</option>
			<option value="student">학생</option>
			<option value="professor">교수</option>
		</select>
	</div>
	
	<div class="sidebar-wrapper scrollbar scrollbar-inner">
		<div class="sidebar-content">
			
			<!-- Staff Menu -->
			<ul class="nav nav-secondary" id="staffMenu">
				<li class="nav-item">
					<a data-bs-toggle="collapse" href="#memberManagement" class="collapsed" aria-expanded="false">
						<i class="fas fa-users"></i>
						<p>구성원관리</p>
						<span class="caret"></span>
					</a>
					<div class="collapse" id="memberManagement">
						<ul class="nav nav-collapse">
							<li class="nav-item">
								<a href="/staff/studentmanage/studentList.do">
									<span class="sub-item">학생관리</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/professorManagement/professorList.do">
									<span class="sub-item">교수관리</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
				
				<li class="nav-item">
					<a data-bs-toggle="collapse" href="#classManagement" class="collapsed" aria-expanded="false">
						<i class="fas fa-chalkboard"></i>
						<p>수업관리</p>
						<span class="caret"></span>
					</a>
					<div class="collapse" id="classManagement">
						<ul class="nav nav-collapse">
							<li class="nav-item">
								<a href="/staff/subject/subjectList.do">
									<span class="sub-item">교과목관리</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/grademanage/gradeList.do">
									<span class="sub-item">성적관리</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/lectureEvaluation/lectureEvalutaionForm.do">
									<span class="sub-item">강의평가</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/lecturemanage/lecture/lectureList.do">
									<span class="sub-item">출석관리</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
				
				<li class="nav-item">
					<a data-bs-toggle="collapse" href="#studentSupport" class="collapsed" aria-expanded="false">
						<i class="fas fa-hands-helping"></i>
						<p>학생지원</p>
						<span class="caret"></span>
					</a>
					<div class="collapse" id="studentSupport">
						<ul class="nav nav-collapse">
							<li class="nav-item">
								<a href="/staff/consult/consultScheduleList.do">
									<span class="sub-item">상담</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/scholarship/staffScholarshipList.do">
									<span class="sub-item">장학</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/college/graduateApply.do">
									<span class="sub-item">졸업</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/job/jobNoticeList.do">
									<span class="sub-item">취업지원</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/program/programList.do">
									<span class="sub-item">비교과</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
				
				<li class="nav-item">
					<a data-bs-toggle="collapse" href="#academicAdmin" class="collapsed" aria-expanded="false">
						<i class="fas fa-file-signature"></i>
						<p>학사행정</p>
						<span class="caret"></span>
					</a>
					<div class="collapse" id="academicAdmin">
						<ul class="nav nav-collapse">
							<li class="nav-item">
								<a href="/staff/tuition/tuitionList.do">
									<span class="sub-item">등록금</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/certification/certificationFormList.do">
									<span class="sub-item">증명서</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
				
				<li class="nav-item">
					<a href="#">
						<i class="fas fa-file-alt"></i>
						<p>논문관리</p>
					</a>
				</li>
					
				<li class="nav-item">
					<a href="/staff/facility/facilityList.do">
						<i class="fas fa-building"></i>
						<span class="sub-item">시설</span>
					</a>
				</li>
				
				<li class="nav-item">
					<a data-bs-toggle="collapse" href="#infoManagement" class="collapsed" aria-expanded="false">
						<i class="fas fa-info-circle"></i>
						<p>정보관리</p>
						<span class="caret"></span>
					</a>
					<div class="collapse" id="infoManagement">
						<ul class="nav nav-collapse">
							<li class="nav-item">
								<a href="/staff/notice/noticeList.do">
									<span class="sub-item">공지사항</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/staff/college/collegeList.do">
									<span class="sub-item">단과대학</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="#">
									<span class="sub-item">통계</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
				
				<li class="nav-item">
					<a href="/staff/schedule">
						<i class="fas fa-calendar"></i>
						<p>캘린더</p>
					</a>
				</li>
			</ul>

			<!-- Student Menu -->
			<ul class="nav nav-secondary" id="studentMenu" style="display: none;">
				<li class="nav-item active">
					<a href="/student/lecture/enroll">
						<i class="fas fa-plus-circle"></i>
						<p>수강신청</p>
					</a>
				</li>
				<li class="nav-item">
					<a data-bs-toggle="collapse" href="#gradeManagement" class="collapsed" aria-expanded="false">
						<i class="fas fa-chart-line"></i>
						<p>성적관리</p>
						<span class="caret"></span>
					</a>
					<div class="collapse" id="gradeManagement">
						<ul class="nav nav-collapse">
							<li class="nav-item">
								<a href="/student/grade/history.do">
									<span class="sub-item">성적 조회</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/student/grade/appeal.do">
									<span class="sub-item">이의 신청</span>
								</a>
							</li>
						</ul>
						 
					</div>
				</li>
				<li class="nav-item">
					<a href="/student/selectLecture.do">
						<i class="fas fa-star"></i>
						<p>강의평가</p>
					</a>
				</li>
				<li class="nav-item">
					<a data-bs-toggle="collapse" href="#studentState" class="collapsed" aria-expanded="false">
						<i class="fas fa-id-card"></i>
						<p>학적</p>
						<span class="caret"></span>
					</a>
					<div class="collapse" id="studentState">
						<ul class="nav nav-collapse">
							<li class="nav-item">
								<a href="/student/counsel/majorChangeList.do">
									<span class="sub-item">전공 변경</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/student/counsel/doubleMajorList.do">
									<span class="sub-item">복수 전공</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/student/counsel/leaveApplyList.do">
									<span class="sub-item">휴학</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/student/counsel/returnApplyList.do">
									<span class="sub-item">복학</span>
								</a>
							</li>
							<li class="nav-item">
								<a href="/student/counsel/graduationSuspensionList.do">
									<span class="sub-item">졸업 유예</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
				<li class="nav-item">
					<a href="/student/tuition/tuitionPolicy">
						<i class="fas fa-user-plus"></i>
						<p>등록</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/student/scholarship/scholarshipBenefit.do">
						<i class="fas fa-graduation-cap"></i>
						<p>장학</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/student/certificate/issuedCertificateHistory.do">
						<i class="fas fa-certificate"></i>
						<p>증명서</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/student/counsel/counsel.do">
						<i class="fas fa-comments"></i>
						<p>상담</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="#">
						<i class="fas fa-handshake"></i>
						<p>취업·멘토링</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="#">
						<i class="fas fa-puzzle-piece"></i>
						<p>비교과</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/student/facility/studentFacilityList.do">
						<i class="fas fa-building"></i>
						<p>시설예약</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="#">
						<i class="fas fa-bullhorn"></i>
						<p>공지사항</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="#">
						<i class="fas fa-users"></i>
						<p>커뮤니티</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="http://localhost:6060"> 
						<i class="fas fa-laptop"></i>
						<p>사이버 강의실</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/student/schedule">
						<i class="fas fa-calendar"></i>
						<p>캘린더</p>
					</a>
				</li>
			</ul>

			<!-- Professor Menu -->
			<ul class="nav nav-secondary" id="professorMenu" style="display: none;">
				<li class="nav-item active">
					<a href="/professor/lecture/lectureList.do">
						<i class="fas fa-book-open"></i>
						<p>강의 개설신청</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/professor/attendclass/attendclassList.do">
						<i class="fas fa-chalkboard"></i>
						<p>강의관리</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/professor/exam/examList.do">
						<i class="fas fa-pencil-alt"></i>
						<p>시험관리</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/professor/attendclass/attendclassList.do">
						<i class="fas fa-folder-open"></i>
						<p>과제관리</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/professor/attendance/attendclassList.do">
						<i class="fas fa-clipboard-check"></i>
						<p>출석관리</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/professor/grade/attendclassList.do">
						<i class="fas fa-chart-line"></i>
						<p>성적관리</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="#">
						<i class="fas fa-star"></i>
						<p>강의평가</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/professor/thesis/thesisList.do">
						<i class="fas fa-file-alt"></i>
						<p>논문관리</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="#">
						<i class="fas fa-calendar"></i>
						<p>캘린더</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/professor/dataManagement/data.do">
						<i class="fas fa-database"></i>
						<p>데이터관리</p>
					</a>
				</li>
				<li class="nav-item">
					<a href="/staff/program/programList.do">
						<i class="fas fa-puzzle-piece"></i>
						<p>비교과관리</p>
					</a>
				</li>
				</li>
				<li class="nav-item">
					<a href="http://localhost:6060/"> 
						<i class="fas fa-laptop"></i>
						<p>사이버 강의실</p>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function () {
	const roleSelector = document.getElementById('roleSelector');
	const menus = {
		staff: document.getElementById('staffMenu'),
		student: document.getElementById('studentMenu'),
		professor: document.getElementById('professorMenu')
	};

	function closeAllCollapses() {
		document.querySelectorAll('.collapse.show').forEach(el => el.classList.remove('show'));
		document.querySelectorAll('[data-bs-toggle="collapse"]').forEach(el => {
			el.classList.add('collapsed');
			el.setAttribute('aria-expanded', 'false');
		});
	}

	function deactivateAllMenuItems() {
		document.querySelectorAll('.nav-item').forEach(item => item.classList.remove('active'));
	}

	function showOnlyMenu(role) {
		// hide all
		Object.values(menus).forEach(menu => {
			if (menu) menu.style.display = 'none';
		});

		// show selected
		const currentMenu = menus[role];
		if (currentMenu) {
			currentMenu.style.display = 'block';

			// optional: set first item active
			const firstItem = currentMenu.querySelector('.nav-item:not(.collapse):not(.nav-collapse)');
			if (firstItem) firstItem.classList.add('active');
		}
	}

	// 1. 초기 설정
	const savedRole = localStorage.getItem('selectedRole') || 'staff';
	roleSelector.value = savedRole;
	showOnlyMenu(savedRole);
	closeAllCollapses();
	deactivateAllMenuItems();

	// 2. 역할 변경 이벤트
	roleSelector.addEventListener('change', function () {
		const selectedRole = this.value;
		localStorage.setItem('selectedRole', selectedRole);
		showOnlyMenu(selectedRole);
		closeAllCollapses();
		deactivateAllMenuItems();
	});

	// 3. 메뉴 클릭 시 active 처리
	document.addEventListener('click', function (e) {
		const navLink = e.target.closest('.nav-item > a[href]:not([data-bs-toggle="collapse"])');
		if (navLink) {
			deactivateAllMenuItems();
			const navItem = navLink.closest('.nav-item');
			if (navItem) navItem.classList.add('active');
		}
	});
});
</script>
