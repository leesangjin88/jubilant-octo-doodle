<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250709	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>Insert title here</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 

<script>
	document.addEventListener("DOMContentLoaded", () => {
		const CPATH = document.body.dataset.contextPath;
		const schTypeNo = document.querySelector("#schTypeNo");
		const initVal = schTypeNo.dataset.initVal;
	
		axios.get(`${CPATH}/student/scholarship/scholarshipTypeList.do`).then(resp => {
			const scholarshipTypeList = resp.data;
			
			console.log(scholarshipTypeList);
	
			if (scholarshipTypeList) {
				const options = scholarshipTypeList.filter(item => item.schTypeNo != initVal)
												   .map(({ schTypeNo, schName }) => `<option value="\${schTypeNo}">\${schName}</option>`)
												   .join("\n");
				
				schTypeNo.innerHTML += options;
				
				schTypeNo.value = initVal ?? "";
			}
		}).catch(err => {
			console.error("장학금 유형 목록 로드 실패:", err);
		});
	});
</script>

<div>
	<h5>장학 신청 수정</h5>
</div>
<div>
	<div>
		<form method="post" enctype="multipart/form-data" action="/student/scholarship/scholarshipUpdateProcess.do">
			<input type="hidden" name="applyNo" value="${scholarship.applyNo}">
			<input type="hidden" name="userNo" value="${scholarship.userNo}">
			<div>
				<label class="inputLabel" for="schName">장학 유형</label>
				<select name="schTypeNo" id="schTypeNo" class="selectBox" data-init-val="${scholarship.schTypeNo }">
					<option value="${scholarship.schTypeNo}">${scholarship.scholarshipType.schName }</option>
				</select>
			</div>
			
			<div>
				<label class="inputLabel" for="requestDate">신청 일자</label>
				<input type="text" class="inputField" id="requestDate" name="requestDate" value="${scholarship.requestDate }" readonly>
			</div>
			
			<div>
				<label class="inputLabel" for="requestComment">내용</label>
				<textarea class="textareaField" id="requestComment" name="requestComment" rows="" cols="">${scholarship.requestComment}</textarea>
			</div>
			<div>
				<label class="inputLabel" for="applyStatus">신청 상태</label>
				<input type="text" class="inputField" id="applyStatus" name="applyStatus" value="${scholarship.applyStatus }" readonly>
			</div>
			<div>
				<label class="inputLabel" for="approveDate">승인 일자</label>
				<input type="text" class="inputField" id="approveDate" name="approveDate" value="${scholarship.approveDate }" readonly>
			</div>
			<div>
				<c:if test="${not empty scholarship.fileRefNo}">
					<p>현재 파일 : 
						<a href="/student/scholarship/fileDownload.do?fileRefNo=${scholarship.fileRefNo}">
							${scholarship.atchFile.atchOriginName}
						</a>
					</p>
				</c:if>
				<p>새 파일 선택 : </p>
				<input type="file" id="uploadFile" name="uploadFile" class="form-control">
				<!-- 기존 파일RefNo 유지 -->
				<input type="hidden" name="fileRefNo" value="${scholarship.fileRefNo}">
			</div>
			
			<div>
				<button type="button" onclick="history.back()" class="cancelButton">목록으로</button>
				<button type="submit" class="submitButton">저장</button>
				<button type="button" onclick="location.href='/student/scholarship/scholarshipDelete.do?no=${scholarship.applyNo}'" class="deleteButton">삭제</button>
			</div>
		</form>
	</div>
</div>