<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<title>수업 수강 학생 조회</title>


<!-- ======================================== -->
<div class="sectionHeaderLine">
  <div>
    <div class="sectionHeaderTitle">[ ${lectureStudent[0].lectureReq.lecName} ] 수강 학생 목록</div>
    <div class="sectionHeaderDescription">전체 ${totalCount}명의 학생</div>
  </div>
</div>

<div class="tableContainer">
  <table class="defaultTable">
    <thead class="tableHead">
      <tr>
        <th class="tableTh">번호</th>
        <th class="tableTh">학과</th>
        <th class="tableTh">학번</th>
        <th class="tableTh">학생명</th>
      </tr>
    </thead>
    <tbody>
    
      <c:choose>
        <c:when test="${not empty lectureStudent }">
          <c:forEach items="${lectureStudent }" var="lectureStudent" varStatus="status">
					<tr class="tableRowHover" onclick="moveToAttendance(this)" data-user-no="${lectureStudent.user.userNo}" data-enr-no="${lectureStudent.enrollNo}">
						<td class="tableTd">${status.index+1}</td>
						<td class="tableTd">${lectureStudent.department.departmentName }</td>
						<td class="tableTd">${lectureStudent.user.userNo}</td>
						<td class="tableTd">${lectureStudent.user.userName}</td>
					 </tr>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <tr>
            <td colspan="7" class="tableTd noticeInfo">해당 강의를 수강하는 학생이 없음.</td>
          </tr>
        </c:otherwise>
      </c:choose>
      
    </tbody>
  </table>
</div>


<div class="pagination">
	<c:if test="${pageNo > 1}">
		<a href="?pageNo=${pageNo - 1}" class="pageButton">이전</a>
	</c:if>

	<c:forEach var="i" begin="1" end="${totalPage}">
		<c:choose>
			<c:when test="${i == pageNo}">
				<strong class="pageButton active">${i}</strong>
			</c:when>
			<c:otherwise>
				<a href="?pageNo=${i}" class="pageButton">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${pageNo < totalPage}">
		<a href="?pageNo=${pageNo + 1}" class="pageButton">다음</a>
	</c:if>
</div>

<script type="text/javascript">
	function moveToAttendance(row) {
		const userNo = row.getAttribute("data-user-no");
		const enrNo = row.getAttribute("data-enr-no");
		window.location.href = "/professor/attendance/attendanceStdList.do?enrNo=" + enrNo +"&userNo=" + userNo;
	}
</script>


