<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <%-- 날짜 포맷팅을 위해 fmt 태그 라이브러리 추가 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>졸업 신청 목록</title>
<style>
    /* 기본적인 테이블 스타일 (필요에 따라 추가/수정) */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    .no-data-message {
        text-align: center;
        padding: 15px;
        font-style: italic;
        color: #666;
    }
</style>
</head>
<body>

    <h1>졸업 신청 현황</h1>

    <table>
        <thead>
            <tr>
                <th>신청 번호</th>
                <th>사용자 번호</th>
                <th>학기</th>
                <th>신청 일자</th>
                <th>졸업 상태</th>
                <th>비고</th> <%-- 'com' 필드에 대한 컬럼 헤더, 실제 의미에 맞게 변경 권장 --%>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <%-- gradReqList가 비어있지 않은 경우 --%>
                <c:when test="${not empty gradReqList}">
                    <%-- gradReqList의 각 요소를 gradReq 변수에 담아 반복 --%>
                    <c:forEach var="gradReq" items="${gradReqList}">
						
						<tr>
							
							<td>
								<%-- <a href="/staff/college/graduateApplyDetail.do?gradReqNo=${gradReq.gradReqNo}"> --%>
									${gradReq.gradReqNo}
								<!-- </a> -->
							</td>
							
							<td>${gradReq.userNo}</td>
							<td>${gradReq.semesterNo}</td>
							<%-- reqDate가 java.util.Date 또는 java.time.LocalDate 타입인 경우 포맷팅 --%>
							<td>${gradReq.reqDate}</td>
							<%-- reqDate가 String 타입인 경우: <td>${gradReq.reqDate}</td> --%>
							<td>${gradReq.gradStatus}</td>
							<td>${gradReq.com}</td>
							
						</tr>
						
					</c:forEach>
                </c:when>
                <%-- gradReqList가 비어있는 경우 --%>
                <c:otherwise>
                    <tr>
                        <td colspan="6" class="no-data-message">졸업 신청 목록이 없습니다.</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

</body>
</html>