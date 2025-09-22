<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<title>수업 수강 학생 조회</title>


<!-- ======================================== -->
<c:if test="${not empty msg}">
<script>
    alert("${msg}");
</script>
</c:if>

<form method="post" action="/staff/lecturemanage/attendance/attendanceUpdateProcess.do">
	<div class="sectionHeaderLine">
	  <div>
	    <div class="sectionHeaderTitle">${lectureName} [${userName }] 출석 정보 조회</div>
	    <div class="sectionHeaderDescription">전체 수업 일수 : ${totalCount}</div>
	  </div>
	<button type="submit" class="submitButton" style="float:right; margin-top:10px;">저장</button>
	</div>
	
<%-- 	<input type="hidden" name="enrNo" value="${enrNo}" /> --%>
<%--   	<input type="hidden" name="userNo" value="${userNo}" /> --%>
	
	<div class="tableContainer">
	  <table class="defaultTable">
	    <thead class="tableHead">
	      <tr>
	        <th class="tableTh">번호</th>
	        <th class="tableTh">날짜</th>
	        <th class="tableTh">출석상태</th>
	        <th class="tableTh">비고</th>
	      </tr>
	    </thead>
	    <tbody>
	    
	      <c:choose>
	        <c:when test="${not empty attendance }">
	          <c:forEach items="${attendance}" var="attendance" varStatus="status">
			  <tr class="tableRowHover">
			    <td class="tableTd">${attendance.rnum}</td>
			    <td class="tableTd">${attendance.attendDate}</td>
			    <td class="tableTd" style="text-align:center;">
			      <input type="hidden" name="attendanceList[${status.index}].attendId" value="${attendance.attendId}" />
			      <select name="attendanceList[${status.index}].attendStatus" class="form-control selectBox" style="width:30%; display:inline-block; text-align:center">
			          <option value="출석" ${attendance.attendStatus eq '출석' ? 'selected' : ''} >출석</option>
			          <option value="지각" ${attendance.attendStatus eq '지각' ? 'selected' : ''}>지각</option>
			          <option value="결석" ${attendance.attendStatus eq '결석' ? 'selected' : ''}>결석</option>
			          <option value="인정결석" ${attendance.attendStatus eq '인정결석' ? 'selected' : ''}>인정결석</option>
			          <option value="미정" ${attendance.attendStatus eq '미정' || attendance.attendStatus eq null || attendance.attendStatus eq '' ? 'selected' : ''}>미정</option>
			      </select>
			    </td>
			    <td class="tableTd">
			      <input class="inputField" name="attendanceList[${status.index}].memo" value="${attendance.memo}" style="width:80%" />
			    </td>
			  </tr>
			</c:forEach>

	        </c:when>
	        <c:otherwise>
	          <tr>
	            <td colspan="7" class="tableTd noticeInfo">출석 정보 입력 기간이 아닙니다.</td>
	          </tr>
	        </c:otherwise>
	      </c:choose>
	      
	    </tbody>
	  </table>
	</div>
</form>


<script type="text/javascript">
	function moveToAttendance(row) {
		const lecNo = row.getAttribute("data-lecno");
		window.location.href = "/staff/lecturemanage/attendance/attendanceList.do";
	}
	
	
</script>


