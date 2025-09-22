<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%--  
	파일명 : curriculumInsert.jsp  
	프로그램명 : 커리큘럼 등록
	설명 : 
	작성자 : 김규민
	작성일 : 2025. 07. 11
--%>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- <script>
	document.addEventListener("DOMContentLoaded", () => {
		const CPATH = document.body.dataset.contextPath || '';
		const subjectCode = document.querySelector("#subjectCode");
		const initVal = subjectCode.dataset.initVal;
	
		axios.get(`${CPATH}/staff/college/selectSubjectList`).then(resp => {
			const subjectList = resp.data;
			
			console.log(subjectList);
	
			if (subjectList) {
				
				console.log("체킁 :", subjectList);
				
				const options = subjectList.map(({subjectCode, subjectName})=>`<option value="\${subjectCode}">\${subjectName}</option>`
				).join("\n");
				
				console.log("체크킁 :", options);
	
				subjectCode.innerHTML += options;
				
				subjectCode.value = initVal ?? "";
			}
		}).catch(err => {
			console.error("과목명 로드 실패:", err);
		});
	});
</script> -->
	
	<h1>커리큘럼 등록</h1>
	<form action="/staff/college/insertCurriculum.do" method="post">
		<input type="hidden" name="departmentNo" value="${departmentNo}">
		<!-- 시퀀스 -->
		<!-- <div>
            <label for="subjectName">커리큘럼 코드 :</label>
            <select id="subjectName" name="subjectName" required></select>
        </div> -->
        
		<!-- select ajax -->
        <!-- <div>
            <label for="collegeSelect">단과 대학 :</label>
            <select id="collegeSelect" name="collegeNo" required></select>
        </div> -->

        <div>
            <label for="subjectCode">과목명 :</label>
            <select id="subjectCode" name="subjectName" required>
            	<option value="">선택하세요</option>
            </select>
        </div>
        
        <!-- <div>
            <label for="subjectName">커리큘럼명 :</label>
            <input type="text" id="subjectName" name="subjectName" required>
        </div> -->
        
        <div>
            <label for="reqCode">필수여부 :</label>
            <select id="reqCode" name="reqCode" required>
            	<option value="REQ_MANDATORY">필수</option>
            	<option value="REQ_SELECTIVE">선택</option>
            </select>
        </div>
        
        
		<div>
			
            <button type="submit">등록</button>
            <button type="button" onclick="history.back()">돌아가기</button>
        			
		</div>
	
	</form>
	
<script type="text/javascript">
$(function(){
	let subjectCode = $("#subjectCode");
	
	$.ajax({
		url : "/staff/college/selectSubject",
		type : "get",
		success : function(res){
			let html = ``;
			if(res != null){
				res.map(function(v,i){
					html += `
						<option value="\${v.subjectCode}">\${v.subjectName}</option>
					`; 
				});
				subjectCode.html(html);
			}
		},
		error : function(){
			
		}
	});
});
</script>