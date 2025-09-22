/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	const client = new StompJs.Client({
		brokerURL: 'ws://localhost/ws/stomp',
		debug: function(str) {
			console.log(str);
		},
		reconnectDelay: 5000,
		heartbeatIncoming: 4000,
		heartbeatOutgoing: 4000,
	});

	//   STOMP는 메시지를 frame의 형태로 표현함.
	//   frame : destination(서비스 식별자) + header(message metadata) + body(message)
	//   destination convention : /topic (다수를 대상으로 한 broadcasting용)
	//                      /queue (하나의 구독자를 대상으로 한 메시지 발행용)
	client.onConnect = function(frame) {
		client.subscribe("/topic/status", function({ body }) {
			console.log("구독한 메시지 : ", body);
			const vo = JSON.parse(body);
			contentarea.innerHTML = vo.message;
		});
	};

	client.activate();

	$(".edit").prop("contenteditable", true);

	$("#send-btn").on("click", () => {
		const senderTxt = sender.value;
		const message = contentarea.innerHTML;
		const body = {
			sender: senderTxt,
			message: message
		}

		client.publish({
			destination: "/app/status",
			header: {},
			body: JSON.stringify(body)
		});
	})

	$("#resetbtn").on("click", () => {
		contentarea.innerHTML = cnttmpl.innerHTML;
	});
});