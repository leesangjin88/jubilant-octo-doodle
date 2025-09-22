<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> 
<%--  
	파일명 : departmentInsert.jsp
	프로그램명 : 학과 등록
	설명 : 
	작성자 : 김규민
	작성일 : 2025. 07. 09
--%>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	
	<h1>학과 등록</h1>
    <form action="<c:url value="${empty department.departmentNo ? '/staff/college/insertDepartment.do' : '/staff/college/departmentModify.do'}" />" method="post">
        
        <div>
            <label for="departmentName">학과 :</label>
            <input type="text" id="departmentName" name="departmentName" required value="${department.departmentName }">
        </div>

        <div>
            <label for="departmentTuition">등록금 :</label>
            <input type="text" id="departmentTuition" name="departmentTuition" required value="${department.departmentTuition}">
        </div>

		<div>
            <label for="collegeSelect">단과 대학 :</label>
            <select id="collegeSelect" name="collegeNo" required></select>
        </div>

        <div>
            <label for="departmentDesc">학과 정보 :</label>
            <input type="text" id="departmentDesc" name="departmentDesc" required value="${department.departmentDesc}">
        </div>

        <hr> 
        
        <h1>졸업 요건 정보</h1>
        
        <div>
            <label for="dgrGrade">졸업 요구 학점 :</label>
            <input type="text" id="dgrGrade" name="dgrReqVO.dgrGrade" required value="${department.dgrGrade}"> 
        </div>

        <div>
            <label for="dgrMc">전공 학점 :</label>
            <input type="text" id="dgrMc" name="dgrReqVO.dgrMc" required value="${department.dgrMc}"> 
        </div>

        <div>
            <label for="dgrLac">교양 학점 :</label>
            <input type="text" id="dgrLac" name="dgrReqVO.dgrLac" required value="${department.dgrLac}"> 
        </div>

        <div>
            <label for="dgrFcc">자유이수 학점 :</label>
            <input type="text" id="dgrFcc" name="dgrReqVO.dgrFcc" required value="${department.dgrFcc}"> 
        </div>

        <div>
            <label for="dgrVolunteerHour">봉사 시간 :</label>
            <input type="text" id="dgrVolunteerHour" name="dgrReqVO.dgrVolunteerHour" required value="${department.dgrVolunteerHour	}"> 
        </div>

        <div>
            <button type="submit">등록</button>
            <button type="button" onclick="history.back()">돌아가기</button>
            <button type="button" onclick="location.href='/staff/college/collegeList.do'">단과 목록</button>
        </div>

    </form>
    
<script type="text/javascript">
$(function(){
	let collegeSelect = $("#collegeSelect");
	
	$.ajax({
		url : "/staff/college/selectCollege",
		type : "get",
		success : function(res){
			let html = ``;
			if(res != null){
				res.map(function(v,i){
					html += `
						<option value="\${v.collegeNo}">\${v.collegeName}</option>
					`; 
				});
				collegeSelect.html(html);
			}
		},
		error : function(){
			
		}
	});
});
</script>










