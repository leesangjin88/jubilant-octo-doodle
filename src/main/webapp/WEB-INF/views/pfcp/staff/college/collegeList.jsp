<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> 
<%--  
	파일명 : collegeList.jsp  
	프로그램명 : 단과 대학 목록 조회
	설명 : 
	작성자 : 김규민
	작성일 : 2025. 07. 02
--%>
<title>단과 대학 목록</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 

<body>
	<a href="/staff/college/collegeInsert.do" class="submitButton" style="float:right">+ 등록</a>
	<h1>단과 대학</h1>
	
    <!-- 테이블 -->
    <div class="section">
        <div class="tableContainer">
            <table class="defaultTable">
                <thead class="tableHead">
                    <tr>
                        <th class="tableTh">단과 대학 코드</th>              
                        <th class="tableTh">단과 대학명</th>
                        <th class="tableTh">학과 수</th>
                        <th class="tableTh">관리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty college}">
                            <c:forEach var="college" items="${college}">
                                <tr class="tableRowHover tableRow" data-collegeno="${college.collegeNo}">
                                    <td class="tableTd">
                                    	${college.collegeNo}
                                    </td>
                                    
                                    <td class="tableTd">
                                        <a href="/staff/college/departmentList.do?collegeNo=${college.collegeNo}">
                                            ${college.collegeName}
                                        </a>
                                    </td>
                                    
                                    <td class="tableTd">
                                        ${college.department.dcount}
                                    </td>
                                    
                                    <td class="tableTd">
                                    	<div class="button-group">
                                            <button onclick="" class="editButton">수정</button>
                                            <button onclick="confirmDeleteNotice(this)" class="deleteButton">삭제</button>
                                        </div>
                                    </td>
                                    
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="3" style="text-align: center; padding: 15px;">
                                    등록된 단과 대학이 없습니다.
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>
    
    <script>
    function confirmDeleteNotice(button) { 
        // 클릭한 버튼에서 data-collegeno 추출
        const collegeNo = button.closest('tr').getAttribute('data-collegeno');
        
        // 확인창 띄우기
        const isConfirmed = confirm("정말로 이 단과대학을 삭제하시겠습니까?");
        if (isConfirmed) {
            // 삭제 처리
            window.location.href = "/staff/college/collegeDelete.do?collegeNo=" + collegeNo;
        }
    }
    
	</script>
</body>



