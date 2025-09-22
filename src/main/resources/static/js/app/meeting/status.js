document.addEventListener("DOMContentLoaded", () => {

	const ws = new WebSocket(`ws://${location.host}/ws/meeting/status`);

	ws.onopen = console.log;
	ws.onclose = console.log;
	ws.onerror = console.error;
	ws.onmessage = function({data}){
		alert(data);
		/*contentarea.innerHTML = data;*/
	}

	$(".edit").prop("contenteditable", true);
	$("#send-btn").on("click", (() => {
		const message = contentarea.innerHTML;
		ws.send(message);
	}))

});