<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250708	서경덕	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>장학 신청</title>
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
				
				console.log("체킁 :", scholarshipTypeList);
				
				const options = scholarshipTypeList.map(({schTypeNo, schName})=>`<option value="\${schTypeNo}">\${schName}</option>`
				).join("\n");
				
				console.log("체크킁 :", options);
	
				schTypeNo.innerHTML += options;
				
				schTypeNo.value = initVal ?? "";
			}
		}).catch(err => {
			console.error("장학금 유형 목록 로드 실패:", err);
		});
	});
</script>

<form method="post" enctype="multipart/form-data" action="/student/scholarship/scholarshipInsertFormProcess.do">
	<div class="form-group">
		<label class="form-label" for="schTypeNo">장학금 유형</label>
		<select name="schTypeNo" id="schTypeNo" class="selectBox" data-init-val="${scholarshipApply.schTypeNo }">
			<option value="">분류 선택</option>
		</select>
	</div>
	
	<div class="form-group">
		<label class="form-label" for="requestComment">신청 사유</label>
		<textarea id="requestComment" name="requestComment" class="textareaField" rows="10" cols="" style="resize: none;"></textarea>
	</div>
	
	<div class="form-group">
		<label class="form-label" for="fileRefNo">첨부파일</label>
		<input type="file" id="uploadFile" name="uploadFile" class="form-control">
	</div>

	<button class="submitButton" type="submit">저장</button>
	<button class="deleteButton" type="reset">취소</button>
</form>