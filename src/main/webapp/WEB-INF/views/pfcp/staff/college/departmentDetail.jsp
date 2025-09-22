<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%--  
	파일명 : departmentList.jsp  
	프로그램명 : 학과 상세 조회
	설명 : 
	작성자 : 김규민
	작성일 : 2025. 07. 04
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${department.departmentName}상세정보</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 

</head>
<body>
	<h1>${department.departmentName}</h1>

	<div class="item">
		<strong>학과 소개:</strong>
		<p>
			<c:choose>
				<c:when test="${department.departmentName eq '컴퓨터공학과'}">
                            ${department.departmentDesc}
                           
                </c:when>
				<c:when test="${department.departmentName eq '전자공학과'}">
                            ${department.departmentDesc}
                            
                </c:when>
				<c:when test="${department.departmentName eq '소프트웨어학과'}">
                            ${department.departmentDesc}
                            
                </c:when>

				<c:when test="${department.departmentName eq '시각디자인학과'}">
                            ${department.departmentDesc}
                            
                </c:when>

				<c:when test="${department.departmentName eq '국어국문학과'}">
                            ${department.departmentDesc}
                            
                </c:when>

				<c:when test="${department.departmentName eq '정보통신공학과'}">
                            ${department.departmentDesc}
                            
                </c:when>

			</c:choose>
		</p>
	</div>
	
	<hr>

	<div>
		<h2>${department.departmentName} 커리큘럼</h2>
		<c:choose>
			<c:when test="${not empty curriculumList}">
				<table class="curriculum table">
					<thead>
						<tr>
							<th>번호</th>
							<th>커리큘럼코드</th>
								
							<th>과목명</th>
							<th>필수여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="curriculum" items="${curriculumList}" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td>${curriculum.curNo}</td>
								<%-- <td>${curriculum.subjectCode}</td> --%>
								<td>${curriculum.subjectName}</td>
								<td>${curriculum.reqCode}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p>등록된 커리큘럼이 없습니다.</p>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div>
		<button type="button" onclick="location.href='/staff/college/curriculumInsert.do?departmentNo=${department.departmentNo}'">커리큘럼 등록</button>
	</div>
	
	<hr>

	<div>
		<h2>${department.departmentName} 졸업 요건</h2>
		<c:choose>
			<c:when test="${not empty dgrList}">
				<table class="curriculum table">
					<thead>
						<tr>
							<th>졸업요건번호</th>
							<th>졸업요구학점</th>
							<th>전공학점</th>
							<th>교양학점</th>
							<th>자유이수학점</th>
							<th>적용일자</th>
							<th>봉사시간</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${dgrList.dgrNo}</td>
							<td>${dgrList.dgrGrade}</td>
							<td>${dgrList.dgrMc}</td>
							<td>${dgrList.dgrLac}</td>
							<td>${dgrList.dgrFcc}</td>
							<td>${dgrList.dgrDate}</td>
							<td>${dgrList.dgrVolunteerHour}</td>
						</tr>	
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p>등록된 졸업 요건이 없습니다.</p>
			</c:otherwise>
		</c:choose>
	</div>
	
	<hr>

    	<%-- 기존 코드: onclick="location.href='/staff/college/updateDepartment.do?departmentNo=${department.departmentNo}'" --%>
    	<%-- 수정된 코드: updateDepartment 함수 호출 --%>
	<div>
    	<button type="button" onclick="updateDepartment('${department.departmentNo}')">수정</button>
	</div>
	
	<div>
    	<button type="button" onclick="removeDepartment('${department.departmentNo}', '${department.collegeNo}')">삭제</button>
	</div>
	
    <div>
    	<button type="button" onclick="location.href='/staff/college/collegeList.do'">단과대학 목록</button>
    </div>
        
<script>
    /**
     * 학과를 삭제(비활성화)하는 JavaScript 함수.
     * 삭제 전 확인 메시지를 띄우고, POST 요청으로 departmentNo와 collegeNo를 함께 전송합니다.
     * @param {string} departmentNo - 삭제할 학과의 번호
     * @param {string} collegeNo - 학과가 속한 단과대학의 번호
     */
    function removeDepartment(departmentNo, collegeNo) { // collegeNo 파라미터 추가
        // 사용자에게 삭제 여부 재확인
        if (!confirm("정말 삭제하시겠습니까?")) {
            return;
        }

        // 요청 본문에 departmentNo와 collegeNo를 포함
        const requestBody = new URLSearchParams();
        requestBody.append('departmentNo', departmentNo);
        requestBody.append('collegeNo', collegeNo); // collegeNo를 요청 본문에 추가

        // fetch API를 사용하여 서버에 POST 요청 전송
        fetch('/staff/college/removeDepartment.do', { // Controller의 @PostMapping 경로와 일치
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' // 폼 데이터 형식으로 전송
            },
            body: requestBody.toString() // URLSearchParams 객체를 문자열로 변환하여 본문으로 사용
        })
        .then(response => {
            // 서버에서 리다이렉트 응답을 보냈을 경우
            if (response.redirected) {
                // 리다이렉트된 URL (departmentList.do?collegeNo=...)로 이동
                window.location.href = response.url;
            } else if (response.ok) {
                // 리다이렉트가 아닌 경우 (예: 성공 메시지만 반환)
                alert("학과 삭제 성공!");
                // 필요시 현재 페이지 새로고침
                location.reload();
            } else {	
                // 서버 응답이 성공(OK)이 아닌 경우 (예: 4xx, 5xx 에러)
                // JSON 응답이 있을 경우 파싱하여 에러 메시지 확인
                return response.text().then(text => {
                    try {
                        const errorData = JSON.parse(text);
                        alert("학과 삭제 실패: " + (errorData.message || "알 수 없는 오류"));
                    } catch (e) {
                        alert("학과 삭제 실패: " + text); // JSON 파싱 실패 시 일반 텍스트로 알림
                    }
                });
            }
        })
        .catch(error => {
            // 네트워크 오류 등 fetch 요청 자체의 실패
            console.error('학과 삭제 중 에러 발생:', error);
            alert("서버와 통신 중 오류가 발생했습니다.");
        });
    }
	function updateDepartment(departmentNo) {
        
    	location.href = `/staff/college/departmentModify.do?departmentNo=${departmentNo}`;
    }
</script>

</body>

</html>

















