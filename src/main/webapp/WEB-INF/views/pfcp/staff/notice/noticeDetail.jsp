<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:set var="editable" value="${mode eq 'edit'}"/>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<%--
   파일명 : noticeDetail.jsp
   프로그렴명 : 공지사항 상세 조회 / 수정
   설 명 : 공지사항 상세 조회 및 수정
   작성자 : 양 수 민
   작성일 : 2025. 07. 01
--%>

<c:if test="${not empty success}">
	<script>
    alert("${success}");
  </script>
</c:if>

<h4 class="pageTitle">공지 상세 정보</h4>
	
	
	<div id="noticeDetailSection" class="detail-section">
		<form id="noticeDetailForm" action="/staff/notice/noticeUpdateProcess.do" method="post">
		
<%-- 		<c:if test="${editable}"> --%>
<!-- 			<input type="hidden" id="noticeId" name="noticeId"	> -->
<!-- 			<label for="category">유형:</label> -->
<!-- 			<select id="category" name="category" required> -->
<!-- 				<option value="일반">일반</option> -->
<!-- 				<option value="학사">학사</option> -->
<!-- 				<option value="비교과">비교과</option> -->
<!-- 				<option value="건의사항">건의사항</option> -->
<!-- 				<option value="기타">기타</option> -->
<!-- 			</select> -->
<%-- 		</c:if> --%>
			
			<input name="boardNo" value="${board.boardNo }" hidden>
			
			<h4 class="pageTitle" style="align:center" <c:if test="${editable}">hidden</c:if>>${board.boardTitle }</h4>
			<input class="inputField" type="text" id="boardTitle" name="boardTitle" value="${board.boardTitle }" placeholder="공지 제목" required <c:if test="${!editable}">hidden</c:if>>

			<label for="noticeContent"></label>
			<textarea class="textareaField"  id="boardContent" name="boardContent" placeholder="공지 내용" required>${board.boardContent }</textarea>


			<c:if test="${editable}">
				<label for="noticeAttachments">첨부파일:</label>
				<input type="file" id="noticeAttachments" name="attachments" multiple>
			</c:if>
			
			<!--                 썸네일 미리보기 -->
			<div id="previewAttachments" class="attachment-list">
<%-- 				<c:if test="${not empty scholarship.fileRefNo}"> --%>
<%-- 			        <a href="/student/scholarship/fileDownload.do?fileRefNo=${scholarship.fileRefNo}">다운로드</a> --%>
<%-- 			    </c:if> --%>
			</div>
			<div id="currentAttachments" class="attachment-list">
			
			</div>

			<div class="button-group">
				<a class="cancelButton" href="/staff/notice/noticeList.do" <c:if test="${editable}">hidden</c:if>>목록으로</a>
				<button type="button" class="editButton" onclick="checKWriter()" <c:if test="${editable}">hidden</c:if>>수정</button>
				<button type="button" onclick="confirmDeleteNotice()" class="deleteButton" style="float:right" <c:if test="${editable}">hidden</c:if>>삭제</button>
				
				<button type="submit" class="submitButton" <c:if test="${!editable}">hidden</c:if>>저장</button>
				<button type="button" class="cancelButton" <c:if test="${!editable}">hidden</c:if> onclick="backToDetail()">취소</button>
			</div>
		</form>
	</div>

<!-- Custom Modal Structure -->
<div id="customModal" class="modal">
	<div class="modal-content">
		<p class="modal-message" id="modalMessage"></p>
		<div class="modal-buttons" id="modalButtons">
			<!-- Buttons will be injected here -->
		</div>
	</div>
</div>



<script>
		const currentUser = '${userNo}';
		const boardWriter = '${board.writerNo}';
		const boardNo = '${board.boardNo }';

		function confirmDeleteNotice() {
			
			if(currentUser == boardWriter){ 
				showCustomModal("정말로 이 공지를 삭제하시겠습니까?", 'confirm', () => {
			    	window.location.href="/staff/notice/noticeDelete.do?what=" + boardNo;
			    });
		    } else {
		    	alert("작성자만 삭제 가능합니다");

		        return false;
		    }
		    
		}


		
		function checKWriter(){
		  if(currentUser == boardWriter){ 
		        window.location.href="/staff/notice/noticeUpdate.do?what=" + boardNo;
		    } else {
		    	alert("작성자만 수정 가능합니다");
		        return false;
		    }
		}
		
		function backToDetail(){
			window.location.href="/staff/notice/noticeDetail.do?what=" + boardNo;
		}
		
		

        // Function to show custom alert/confirm modal
        function showCustomModal(message, type, onConfirm = null) {
            const modal = document.getElementById('customModal');
            const modalMessage = document.getElementById('modalMessage');
            const modalButtons = document.getElementById('modalButtons');

            modalMessage.innerText = message;
            modalButtons.innerHTML = ''; // Clear previous buttons

            if (type === 'alert') {
                const okButton = document.createElement('button');
                okButton.className = 'btn-primary';
                okButton.innerText = '확인';
                okButton.onclick = () => modal.style.display = 'none';
                modalButtons.appendChild(okButton);
            } else if (type === 'confirm') {
                const confirmButton = document.createElement('button');
                confirmButton.className = 'btn-danger';
                confirmButton.innerText = '삭제';
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

            modal.style.display = 'flex'; // Use flex to center the modal
        }

        // Load notices from localStorage
        function loadNotices() {
            const storedNotices = localStorage.getItem('notices');
            return storedNotices ? JSON.parse(storedNotices) : [];
        }

        // Save notices to localStorage
        function saveNotices(notices) {
            localStorage.setItem('notices', JSON.stringify(notices));
        }

        // Function to get URL parameter by name
        function getUrlParameter(name) {
            name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
            const regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
            const results = regex.exec(location.search);
            return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
        }


        function saveNoticeDetails() {
            const id = document.getElementById('noticeId').value;
            const type = document.getElementById('noticeType').value;
            const title = document.getElementById('noticeTitle').value;
            const content = document.getElementById('noticeContent').value;
            const attachmentsInput = document.getElementById('noticeAttachments');

            if (!type || !title || !content) {
                showCustomModal("필수 정보를 모두 입력해주세요 (유형, 제목, 내용).", 'alert');
                return;
            }

            let notices = loadNotices();
            const existingNoticeIndex = notices.findIndex(n => n.id === id);

            if (existingNoticeIndex > -1) {
                // Update existing notice
                const existingNotice = notices[existingNoticeIndex];
                existingNotice.type = type;
                existingNotice.title = title;
                existingNotice.content = content;

                // Add new attachments
                const newAttachments = [];
                for (const file of attachmentsInput.files) {
                    newAttachments.push({ name: file.name, url: URL.createObjectURL(file) }); // Create temp URL for display
                }
                existingNotice.attachments = existingNotice.attachments.concat(newAttachments);

                saveNotices(notices); // Save updated notices to localStorage
                showCustomModal(`"${title}" 공지 정보가 성공적으로 수정되었습니다.`, 'alert', () => {
                    window.location.href = 'index.html'; // Go back to list after saving
                });
            } else {
                showCustomModal("수정할 공지를 찾을 수 없습니다.", 'alert');
            }
        }

        

        function removeAttachment(noticeId, attachmentIndex) {
            let notices = loadNotices();
            const notice = notices.find(n => n.id === noticeId);
            if (notice && notice.attachments) {
                showCustomModal(`"${notice.attachments[attachmentIndex].name}" 파일을 삭제하시겠습니까?`, 'confirm', () => {
                    notice.attachments.splice(attachmentIndex, 1);
                    saveNotices(notices); // Save updated notices to localStorage
                    // Re-render attachments on the detail form after removal
                    const attachmentsDiv = document.getElementById('currentAttachments');
                    attachmentsDiv.innerHTML = '';
                    if (notice.attachments && notice.attachments.length > 0) {
                        notice.attachments.forEach((att, index) => {
                            const attDiv = document.createElement('div');
                            attDiv.innerHTML = `
                                <span>${att.name}</span> 
                                <button type="button" class="btn-danger" style="padding: 5px 8px; margin-left: 10px;" onclick="removeAttachment('${notice.id}', ${index})">X</button>
                            `;
                            attachmentsDiv.appendChild(attDiv);
                        });
                    } else {
                        attachmentsDiv.innerHTML = '첨부파일 없음';
                    }
                    showCustomModal("첨부파일이 삭제되었습니다.", 'alert');
                });
            }
        }
        
        document.getElementById('noticeAttachments').addEventListener('change', function () {
            const previewDiv = document.getElementById('previewAttachments');
            previewDiv.innerHTML = ''; // 초기화

            const files = Array.from(this.files);
            if (files.length === 0) {
                previewDiv.innerText = '첨부파일 없음';
                return;
            }

            files.forEach(file => {
                const fileDiv = document.createElement('div');

                // 이미지 파일 미리보기
                if (file.type.startsWith('image/')) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        const img = document.createElement('img');
                        img.src = e.target.result;
                        img.style.maxWidth = '100px';
                        img.style.maxHeight = '100px';
                        img.style.marginRight = '10px';
                        img.style.border = '1px solid #ccc';
                        img.style.borderRadius = '4px';
                        img.style.objectFit = 'cover';

                        fileDiv.appendChild(img);
                        fileDiv.appendChild(document.createTextNode(file.name));
                    };
                    reader.readAsDataURL(file);
                } else {
                    fileDiv.innerText = file.name;
                }

                previewDiv.appendChild(fileDiv);
            });
        });

    </script>
    
    
<style>
.container {
	max-width: 800px;
	margin: auto;
	background-color: #fff;
	padding: 30px;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1, h2 {
	color: #2c3e50;
	border-bottom: 2px solid #e0e0e0;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

/* Detail Page / Form Styling */
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

.attachment-list {
	margin-top: 10px;
	font-size: 0.9em;
	color: #666;
}

.attachment-list div {
	margin-bottom: 5px;
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

.btn-danger {
	padding: 10px 20px;
	background-color: #e74c3c;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.2s;
}

.btn-danger:hover {
	background-color: #c0392b;
}

/* Custom Modal for alerts/confirms */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	justify-content: center;
	align-items: center;
}

.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	border-radius: 8px;
	width: 80%; /* Could be more specific */
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
