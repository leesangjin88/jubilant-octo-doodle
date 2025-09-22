<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/app/meeting/status-stomp.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@5.0.0/bundles/stomp.umd.min.js"></script>
</head>
<body>
	<button id="send-btn">전송</button>
	<button id="reset-btn">리셋</button>
	<input type="text" id="sender" placeholder="발행자" /> 
	<template id="cnttmpl">
		${tableContent}
	</template>
	<div id="contentarea">
		${tableContent}
	</div>
</body>
</html>