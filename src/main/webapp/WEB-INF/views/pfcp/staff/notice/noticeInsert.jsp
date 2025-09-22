<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%--
   파일명 : noticeInsert.jsp
   프로그렴명 : 공지사항 등록
   설 명 : 카테고리[전체/일반/학사/비교과/건의사항/기타] 선택 후 공지사항 등록
   작성자 : 양 수 민
   작성일 : 2025. 07. 01
--%>

<style>

.container {
	max-width: 800px;
	margin: auto;
	background-color: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h2 {
	color: #2c3e50;
	border-bottom: 2px solid #e0e0e0;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

.detail-section {
	margin-top: 30px;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #f9f9f9;
}

.detail-section label {
	font-weight: bold;
	display: block;
	margin-bottom: 5px;
	color: #555;
}

.detail-section input[type="text"], .detail-section input[type="file"],
	.detail-section textarea, .detail-section select {
	width: calc(100% - 22px);
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.detail-section textarea {
	resize: vertical;
	min-height: 150px;
}

.detail-section .button-group {
	text-align: right;
	margin-top: 20px;
}

.detail-section .button-group button {
	margin-left: 10px;
}
/* General Buttons */
.btn-primary {
	padding: 10px 20px;
	background-color: #2ecc71;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.2s;
}

.btn-primary:hover {
	background-color: #27ae60;
}

.btn-secondary {
	padding: 10px 20px;
	background-color: #95a5a6;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.2s;
}

.btn-secondary:hover {
	background-color: #7f8c8d;
}
/* Modal styling 그대로 유지 (생략 가능) */
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
	justify-content: center;
	align-items: center;
}

.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	border-radius: 8px;
	width: 80%;
	max-width: 400px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	text-align: center;
}

.modal-buttons button {
	margin: 10px 5px;
	padding: 8px 15px;
	border-radius: 4px;
	cursor: pointer;
}

.modal-message {
	margin-bottom: 20px;
	font-size: 1.1em;
}
</style>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<div class="container">
	<h2>공지 등록</h2>
	<div class="detail-section">
		<form id="noticeRegisterForm" enctype="multipart/form-data" action="/staff/notice/noticeInsertProcess.do" method="post">
			<label for="typeCode">유형:</label>
			<select id="typeCode" name="typeCode" required>
				<option value="" disabled selected>선택하세요</option>
				<option value="GEN">일반</option>
				<option value="EXT">비교과</option>
				<option value="ACA_SCH">[학사]수강신청</option>
				<option value="ACA_EXM">[학사]시험 및 평가 일정</option>
				<option value="ACA_STA">[학사]학적 변경</option>
				<option value="ACA_GRD">[학사]졸업</option>
				<option value="ACA_TUI">[학사]등록금 및 장학금</option>
				<option value="ACA_HOL">[학사]방학 및 휴일</option>
				<option value="SUG">건의사항</option>
				<option value="ETC">기타</option>
			</select>
			
			<label for="boardTitle">제목:</label>
			<input type="text" id="boardTitle" name="boardTitle" placeholder="공지 제목" required>

			<label for="boardContent">내용:</label>
			<textarea id="boardContent" name="boardContent" placeholder="공지 내용" required></textarea>

<!-- 			<label for="noticeAttachments">첨부파일:</label> -->
<!-- 			<input type="file" id="noticeAttachments" name="attachments" multiple> -->

			<div class="form-group">
				<label class="form-label" for="fileRefNo">첨부파일</label>
				<input type="file" id="uploadFile" name="uploadFile" class="form-control" multiple>
			</div>


			<div class="button-group">
				<button type="submit" class="submitButton">저장</button>
				<button type="button" class="cancelButton" onclick="history.back()">취소</button>
			</div>
			
			<input name="writerNo" value="${userNo }" style="display:none">
		</form>
	</div>
</div>

<!-- Custom Modal (동일) -->
<div id="customModal" class="modal">
	<div class="modal-content">
		<p class="modal-message" id="modalMessage"></p>
		<div class="modal-buttons" id="modalButtons"></div>
	</div>
</div>

<script>

        function showCustomModal(message, type, onConfirm = null) {
            const modal = document.getElementById('customModal');
            const modalMessage = document.getElementById('modalMessage');
            const modalButtons = document.getElementById('modalButtons');

            modalMessage.innerText = message;
            modalButtons.innerHTML = '';

            if (type === 'alert') {
                const okButton = document.createElement('button');
                okButton.className = 'btn-primary';
                okButton.innerText = '확인';
                okButton.onclick = () => modal.style.display = 'none';
                modalButtons.appendChild(okButton);
            } else if (type === 'confirm') {
                const confirmButton = document.createElement('button');
                confirmButton.className = 'btn-primary';
                confirmButton.innerText = '확인';
                confirmButton.onclick = () => {
                    modal.style.display = 'none';
                    if (onConfirm) onConfirm();
                };
                modalButtons.appendChild(confirmButton);

                const cancelButton = document.createElement('button');
                cancelButton.className = 'btn-secondary';
                cancelButton.innerText = '취소';
                cancelButton.onclick = () => modal.style.display = 'none';
                modalButtons.appendChild(cancelButton);
            }

            modal.style.display = 'flex';
        }

        
    </script>