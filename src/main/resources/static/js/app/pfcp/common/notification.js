/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
	const userNo = `${userNo}`;
	const socket = new SockJS("/ws");
	const stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function(frame) {
		console.log("Connected: " + frame);
		
		stompClient.subscribe('/meeting/notify/' + userNo, function(message) {
			const noti = JSON.parse(message.body);
			
			/* <a href="#">
				<div class="notif-icon notif-primary">
					<i class="fa fa-user-plus"></i>
				</div>
				<div class="notif-content">
					<span class="block"> New user registered </span> 
					<span class="time">5 minutes ago</span>
				</div>
			</a>  */
			
			const container = document.querySelector(".notif-center");
			const html = `
				<a href="${noti.linkUrl}">
					<div class="notif-icon ${noti.iconClass}">
						<i class="fa \${noti.faIcon}"></i>
					</div>
					<div class="notif-content">
						<span class="block">${noti.message}</span>
						<span class="time">${noti.timeAgo}</span>
					</div>
				</a>
			  `;
			container.insertAdjacentHTML("afterbegin", html);
			
			  // 알림 카운트 증가
			const countElem = document.querySelector(".notification");
			const newCount = (parseInt(countElem.innerText || '0') + 1);
			countElem.innerText = newCount;
		});
	});
})