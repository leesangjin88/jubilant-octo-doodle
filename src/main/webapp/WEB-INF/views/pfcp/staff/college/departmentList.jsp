<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%--  
   파일명 : departmentList.jsp  
   프로그램명 : 단과 대학 상세 조회(학과)
   설명 : 
   작성자 : 김규민
   작성일 : 2025. 07. 03
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 
</head>
<body>
   <a href="/staff/college/departmentInsert.do" class="submitButton" style="float:right">+ 등록</a>
   <h1>학과</h1>
   
   <!-- 테이블 -->
        <div class="section">
            <!-- <h2 class="sectionTitle">테이블</h2> -->
            <div class="tableContainer">
                <table class="defaultTable">
                    <thead class="tableHead">
                        <tr>
                            <th class="tableTh">학과 번호</th>              
                            <th class="tableTh">학과 명</th>
                            <th class="tableTh">관리</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                    
					<c:choose>
                		<c:when test="${not empty departmentList}">
                    		<c:forEach var="dept" items="${departmentList}">


								<tr class="tableRowHover tableRow" data-deparmentno="${dept.departmentNo}">
								
									<td class="tableTd">${dept.departmentNo}</td>
									
									<td class="tableTd">
										<a href="/staff/college/departmentDetail.do?departmentNo=${dept.departmentNo}">
                                  			${dept.departmentName}
                               			</a>
									</td>
								
									<td class="tableTd">
										<div class="button-group">
											<button class="editButton">수정</button>
											<button onclick="confirmDeleteNotice(this)" class="deleteButton">삭제</button>
										</div>
									</td>
								</tr>
								
							</c:forEach>
						</c:when>
						
						<c:otherwise>
                    		<tr>
                        		<td colspan="3" style="text-align: center; padding: 15px;">등록된 학과가 없습니다.</td>
                    		</tr>
                		</c:otherwise>
                		
            		</c:choose>

				</tbody>
                </table>
            </div>
        </div>	
    
    <hr>
        
    <div>
        <button type="button" onclick="location.href='/staff/college/collegeList.do'">단과대학 목록</button>
    </div>
    
    <script>
    function confirmDeleteNotice(button) { 
        // 클릭한 버튼에서 data-collegeno 추출
        const departmentNo = button.closest('tr').getAttribute('data-deparmentno');
        
        // 확인창 띄우기
        const isConfirmed = confirm("정말로 이 학과를 삭제하시겠습니까?");
        if (isConfirmed) {
            // 삭제 처리
            window.location.href = "/staff/college/departmentDelete.do?collegeNo=" + collegeNo;
        }
    }
    
	</script>
	
</body>
</html>