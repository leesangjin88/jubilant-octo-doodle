<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<security:authorize access="isAuthenticated()">
	<security:authentication property="principal.realUser.userNo" var="userNo" />
</security:authorize>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>

<script>
document.addEventListener("DOMContentLoaded", () => {
	const userNo = `${userNo}`;
	const socket = new SockJS("/ws");
	const stompClient = Stomp.over(socket);
	
	const CPATH = document.body.dataset.contextPath;
	
	console.log("userNo :", userNo);
	console.log("socket :", socket);
	console.log("stompClient :", stompClient);
	
	stompClient.connect({}, function(frame) {
		console.log("Connected: " + frame);
		
		stompClient.subscribe('/meeting/notify/' + userNo, function(message) {
			const noti = JSON.parse(message.body);
			
			console.log("noti :", noti);
			
			const container = document.querySelector("#alertNotiContainer");
			
			const html = `
				<a href="\${noti.linkUrl}">
					<div class="notif-icon \${noti.iconClass}">
						<i class="fa \${noti.faIcon}"></i>
					</div>
					<div class="notif-content">
						<span class="block">\${noti.message}</span>
						<span class="time">\${noti.timeAgo}</span>
					</div>
				</a>
			  `;
			container.insertAdjacentHTML("afterbegin", html);
			
			const countElem = document.querySelector(".notification");
			const newCount = (parseInt(countElem.innerText || '0') + 1);
			countElem.innerText = newCount;
		});
	});
	
	document.querySelector("#alertNotiContainer").addEventListener("click", async (e) => {
		const notiItem = e.target.closest(".noti-item");
		
		if (!notiItem) {
			return;
		}
		
		const notiNo = notiItem.dataset.notiNo;
		
		if (!notiNo) {
			return;
		}
		
		try {
			await axios.post(`\${CPATH}/notif/notifications/read`, { notiNo });
			
			// 알림 카운트 감소
			const countElem = document.querySelector(".notification");
			
			const currentCount = parseInt(countElem.innerText || '0');
			
			const newCount = currentCount > 0 ? currentCount - 1 : 0;
			
			countElem.innerText = newCount;
			
			notiItem.remove();
	    } catch (err) {
			console.error("읽음 처리 실패:", err);
	    }
	});
})
</script>

<header>
	
	<div class="main-header">
		<div class="main-header-logo">
			<!-- Logo Header -->
			<div class="logo-header" data-background-color="dark">
				<a href="/" class="logo"> <img
					src="/dist/assets/img/kaiadmin/logo_light.svn" alt="navbar brand"
					class="navbar-brand" height="20" />
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
			<!-- End Logo Header -->
		</div>
		<!-- Navbar Header -->
		<nav
			class="navbar navbar-header navbar-header-transparent navbar-expand-lg border-bottom">
			<div class="container-fluid">
				<nav
					class="navbar navbar-header-left navbar-expand-lg navbar-form nav-search p-0 d-none d-lg-flex">
					<div class="input-group">
						<div class="input-group-prepend">
							<button type="submit" class="btn btn-search pe-1">
								<i class="fa fa-search search-icon"></i>
							</button>
						</div>
						<input type="text" placeholder="Search ..." class="form-control" />
					</div>
				</nav>

				<ul class="navbar-nav topbar-nav ms-md-auto align-items-center">
					<li
						class="nav-item topbar-icon dropdown hidden-caret d-flex d-lg-none">
						<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-expanded="false" aria-haspopup="true">
							<i class="fa fa-search"></i>
					</a>
						<ul class="dropdown-menu dropdown-search animated fadeIn">
							<form class="navbar-left navbar-form nav-search">
								<div class="input-group">
									<input type="text" placeholder="Search ..."
										class="form-control" />
								</div>
							</form>
						</ul>
					</li>
					<li>
						<a href="/login">임시 로그인</a>
					</li>
					<li class="nav-item topbar-icon dropdown hidden-caret"><a
						class="nav-link dropdown-toggle" href="#" id="messageDropdown"
						role="button" data-bs-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fa fa-envelope"></i>
					</a>
						<ul class="dropdown-menu messages-notif-box animated fadeIn"
							aria-labelledby="messageDropdown">
							<li>
								<div
									class="dropdown-title d-flex justify-content-between align-items-center">
									Messages <a href="#" class="small">Mark all as read</a>
								</div>
							</li>
							<li>
								<div class="message-notif-scroll scrollbar-outer">
									<div class="notif-center">
										<a href="#">
											<div class="notif-img">
												<img
													src="${pageContext.request.contextPath}/dist/assets/img/jm_denis.jpg"
													alt="Img Profile" />
											</div>
											<div class="notif-content">
												<span class="subject">Jimmy Denis</span> <span class="block">
													How are you ? </span> <span class="time">5 minutes ago</span>
											</div>
										</a> <a href="#">
											<div class="notif-img">
												<img
													src="${pageContext.request.contextPath}/dist/assets/img/chadengle.jpg"
													alt="Img Profile" />
											</div>
											<div class="notif-content">
												<span class="subject">Chad</span> <span class="block">
													Ok, Thanks ! </span> <span class="time">12 minutes ago</span>
											</div>
										</a> <a href="#">
											<div class="notif-img">
												<img
													src="${pageContext.request.contextPath}/dist/assets/img/mlane.jpg"
													alt="Img Profile" />
											</div>
											<div class="notif-content">
												<span class="subject">Jhon Doe</span> <span class="block">
													Ready for the meeting today... </span> <span class="time">12
													minutes ago</span>
											</div>
										</a> <a href="#">
											<div class="notif-img">
												<img
													src="${pageContext.request.contextPath}/dist/assets/img/talha.jpg"
													alt="Img Profile" />
											</div>
											<div class="notif-content">
												<span class="subject">Talha</span> <span class="block">
													Hi, Apa Kabar ? </span> <span class="time">17 minutes ago</span>
											</div>
										</a>
									</div>
								</div>
							</li>
							<li><a class="see-all" href="javascript:void(0);">See
									all messages<i class="fa fa-angle-right"></i>
							</a></li>
						</ul></li>
					<li class="nav-item topbar-icon dropdown hidden-caret">
						<a class="nav-link dropdown-toggle" href="#" id="notifDropdown" 
							role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
						<i class="fa fa-bell"></i> 
						<span class="notification">
							<c:out value="${fn:length(unreadNotis)}" />
						</span>
						</a>
						<ul class="dropdown-menu notif-box animated fadeIn" aria-labelledby="notifDropdown">
							<li>
								<div class="dropdown-title">You have <c:out value="${fn:length(unreadNotis)}" /> new notification</div>
							</li>
							<li>
								<div class="notif-scroll scrollbar-outer">
									<div class="notif-center" id="alertNotiContainer">
										<c:forEach var="noti" items="${unreadNotis}">
										  <a class="noti-item" data-noti-no="${noti.notiNo}">
										    <div class="notif-icon ${noti.iconClass}">
										      <i class="fa ${noti.faIcon}"></i>
										    </div>
										    <div class="notif-content">
										      <span class="block">${noti.message}</span>
										      <span class="time">${noti.timeAgo}</span>
										    </div>
										  </a>
										</c:forEach>
									</div>
								</div>
							</li>
							<li>
								<a class="see-all" href="javascript:void(0);">
									See all notifications
									<i class="fa fa-angle-right"></i>
								</a>
							</li>
						</ul>
					</li>
					<li class="nav-item topbar-icon dropdown hidden-caret"><a
						class="nav-link" data-bs-toggle="dropdown" href="#"
						aria-expanded="false"> <i class="fas fa-layer-group"></i>
					</a>
						<div class="dropdown-menu quick-actions animated fadeIn">
							<div class="quick-actions-header">
								<span class="title mb-1">Quick Actions</span> <span
									class="subtitle op-7">Shortcuts</span>
							</div>
							<div class="quick-actions-scroll scrollbar-outer">
								<div class="quick-actions-items">
									<div class="row m-0">
										<a class="col-6 col-md-4 p-0" href="#">
											<div class="quick-actions-item">
												<div class="avatar-item bg-danger rounded-circle">
													<i class="far fa-calendar-alt"></i>
												</div>
												<span class="text">Calendar</span>
											</div>
										</a> <a class="col-6 col-md-4 p-0" href="#">
											<div class="quick-actions-item">
												<div class="avatar-item bg-warning rounded-circle">
													<i class="fas fa-map"></i>
												</div>
												<span class="text">Maps</span>
											</div>
										</a> <a class="col-6 col-md-4 p-0" href="#">
											<div class="quick-actions-item">
												<div class="avatar-item bg-info rounded-circle">
													<i class="fas fa-file-excel"></i>
												</div>
												<span class="text">Reports</span>
											</div>
										</a> <a class="col-6 col-md-4 p-0" href="#">
											<div class="quick-actions-item">
												<div class="avatar-item bg-success rounded-circle">
													<i class="fas fa-envelope"></i>
												</div>
												<span class="text">Emails</span>
											</div>
										</a> <a class="col-6 col-md-4 p-0" href="#">
											<div class="quick-actions-item">
												<div class="avatar-item bg-primary rounded-circle">
													<i class="fas fa-file-invoice-dollar"></i>
												</div>
												<span class="text">Invoice</span>
											</div>
										</a> <a class="col-6 col-md-4 p-0" href="#">
											<div class="quick-actions-item">
												<div class="avatar-item bg-secondary rounded-circle">
													<i class="fas fa-credit-card"></i>
												</div>
												<span class="text">Payments</span>
											</div>
										</a>
									</div>
								</div>
							</div>
						</div></li>
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal" var="principal" />
						<li class="nav-item topbar-user dropdown hidden-caret"><a
							class="dropdown-toggle profile-pic" data-bs-toggle="dropdown"
							href="#" aria-expanded="false"> <i
								class="fas ${principal.realUser.userRole eq 'ROLE_PROF' ? 'fa-user-tie' : 
                     principal.realUser.userRole eq 'ROLE_STD' ? 'fa-user-graduate' : 
                     principal.realUser.userRole eq 'ROLE_STAFF' ? 'fa-user-cog' : 'fa-user'}"></i>
								<span class="profile-username"> <span class="fw-bold">
										<c:choose>
											<c:when test="${principal.realUser.userRole eq 'ROLE_PROF'}">교수</c:when>
											<c:when test="${principal.realUser.userRole eq 'ROLE_STD'}">학생</c:when>
											<c:when test="${principal.realUser.userRole eq 'ROLE_STAFF'}">직원</c:when>
											<c:otherwise>사용자</c:otherwise>
										</c:choose> ${principal.realUser.userName}
								</span>
							</span>
						</a>
							<ul class="dropdown-menu dropdown-user animated fadeIn">
								<div class="dropdown-user-scroll scrollbar-outer">
									<li>
										<div class="user-box">
											<div class="avatar-lg">
												<img
													src="${pageContext.request.contextPath}/dist/assets/img/profile3.png"
													alt="image profile" class="avatar-img rounded" />
											</div>
											<div class="u-text">
												<h4>${principal.realUser.userName }</h4>
												<p class="text-muted">${principal.realUser.userNo }</p>
											</div>
										</div>
									</li>
									<li>
										<div class="dropdown-divider">
											
										</div> 
											<br>
											<a href="/mypage" class="submitButton" style="margin: 10px;">마이페이지</a> 
											<a href="javascript:void(0)" class="deleteButton" id="logoutBtn">로그아웃</a>
											<br><br>
										
										<!-- 
										 필요 없는 부분 주석 처리
										<a class="dropdown-item" href="#">My Profile</a> 
										<a class="dropdown-item" href="#">My Balance</a> <a class="dropdown-item" href="#">Inbox</a>
										<div class="dropdown-divider"></div>
										 <a class="dropdown-item" href="#">Account Setting</a>
										<div class="dropdown-divider"></div>
										 <a href="javascript:void(0)" class="cancelButton" id="logoutBtn">로그아웃</a>
										 -->
										<script>
            	logoutBtn.addEventListener("click", (e)=>{
            		e.preventDefault();
            		axios.post("/common/auth/revoke", {}, {
            			withCredentials:true
            		}).then(resp=>location.href="/");
            	});
            </script>
									</li>
								</div>
							</ul></li>
					</security:authorize>
				</ul>
			</div>
		</nav>
		<!-- End Navbar -->
	</div>
</header>
