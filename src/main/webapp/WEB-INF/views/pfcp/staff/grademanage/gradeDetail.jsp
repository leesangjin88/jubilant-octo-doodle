<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<title>수업 수강 학생 조회</title>

<c:if test="${not empty msg}">
	<script>
    alert("${msg}");
  </script>
</c:if>


<!-- ======================================== -->
<form method="post" action="/staff/grademanage/gradeUpdateProcess.do">

	<div class="sectionHeaderLine">
	  <div>
	    <input value="${lectureStudent[0].lecture.lecNo}" name="lecNo" style="display:none;">
	    <div class="sectionHeaderTitle">[ ${lectureStudent[0].lectureReq.lecName} ] 수강 학생 목록</div>
	    <div class="sectionHeaderDescription">전체 ${totalCount}명의 학생</div>
	  </div>
	  
	  <button type="submit" class="submitButton">저장</button>
	</div>
	
	<div class="tableContainer">
	  <table class="defaultTable">
	    <thead class="tableHead">
	      <tr>
	        <th class="tableTh">번호</th>
	        <th class="tableTh">학과</th>
	        <th class="tableTh">학번</th>
	        <th class="tableTh">학생명</th>
	        <th class="tableTh">중간고사</th>
	        <th class="tableTh">기말고사</th>
	        <th class="tableTh">과제</th>
	        <th class="tableTh">출석</th>
	        <th class="tableTh">총점</th>
	        <th class="tableTh">등급</th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:choose>
	        <c:when test="${not empty lectureStudent }">
	          <c:forEach items="${lectureStudent }" var="lectureStudent" varStatus="status">
				<tr class="tableRowHover" data-user-no="${lectureStudent.user.userNo}" data-enr-no="${enrNo}" data-lec-no="${lectureStudent.lecNo}">
					<td class="tableTd">${status.index+1}<input style="display:none;" name="userNoList[${vs.index}]" value="${lectureStudent.user.userNo}"></td>
					<td class="tableTd">${lectureStudent.department.departmentName }</td>
					<td class="tableTd">${lectureStudent.user.userNo}</td>
					<td class="tableTd">${lectureStudent.user.userName}</td>
					
					<td class="tableTd" style="width:10%;">
						<input class="inputField" name="midtermScoreList[${vs.index}]" value="${lectureStudent.gradeVO.midtermScore}" placeholder="기입 전입니다" style="text-align:center;">
					</td>
					<td class="tableTd" style="width:10%;">
						<input class="inputField" name="finalScoreList[${vs.index}]" value="${lectureStudent.gradeVO.finalScore}" placeholder="기입 전입니다" style="text-align:center">
					</td>
					<td class="tableTd" style="width:10%;">
						<input class="inputField" name="assignmentScoreList[${vs.index}]" value="${lectureStudent.gradeVO.assignmentScore}" placeholder="기입 전입니다" style="text-align:center">
					</td>
					<td class="tableTd" style="width:13%;">
						<input class="inputField" name="" value="${lectureStudent.gradeVO.attendanceScore}" placeholder="자동으로 산출됩니다" style="text-align:center" readonly>
					</td>
					<td class="tableTd" style="width:13%;">
						<input class="inputField" name="" value="${lectureStudent.gradeVO.finalGrade}" placeholder="자동으로 산출됩니다" style="text-align:center" readonly>
					</td>
					<td class="tableTd" style="width:13%;">
						<input class="inputField" name="" value="${lectureStudent.grade}" placeholder="자동으로 산출됩니다" style="text-align:center" readonly>
					</td>
					
				 </tr>
	          </c:forEach>
	        </c:when>
	        <c:otherwise>
	          <tr>
	            <td colspan="10" class="tableTd noticeInfo">해당 강의를 수강하는 학생이 없음.</td>
	          </tr>
	        </c:otherwise>
	      </c:choose>
	      
	    </tbody>
	  </table>
	</div>

</form>



