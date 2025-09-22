<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-15 	김태수	최초 생성 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>강의 상세 조회</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<h2 class="sectionTitle">강의 상세 조회</h2>
<div class="section">
	<div class="card">
		<form> 
		
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="lecNo" class="inputLabel">강의 번호</label>
					<input type="text" id="lecNo" name="lecNo" class="inputField" value="${lectureReq.lecNo}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="subjectName" class="inputLabel">강의 과목</label>
					<input type="text" id="subjectName" name="subjectName" class="inputField" value="${lectureReq.subjectName}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="lecCategory" class="inputLabel">강의 분류</label>
					<input type="text" id="lecCategory" name="lecCategory" class="inputField" value="${lectureReq.lecCategory}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="lecName" class="inputLabel">강의명</label>
					<input type="text" id="lecName" name="lecName" class="inputField" value="${lectureReq.lecName}" readonly>
				</div>
			</div>
			
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="preDay" class="inputLabel">희망 요일</label>
					<input type="text" id="preDay" name="preDay" class="inputField" value="${lectureReq.preDay}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="preTime" class="inputLabel">희망 교시</label>
					<input type="text" id="preTime" name="preTime" class="inputField" value="${lectureReq.preTime}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="preClassrm" class="inputLabel">희망 강의실</label>
					<input type="text" id="preClassrm" name="preClassrm" class="inputField" value="${lectureReq.preClassrm}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="maxCapacity" class="inputLabel">수강정원</label>
					<input type="text" id="maxCapacity" name="maxCapacity" class="inputField" value="${lectureReq.maxCapacity}" readonly>
				</div>
			</div>
			
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="userName" class="inputLabel">신청자(교수 이름)</label>
					<input type="text" id="userName" name="userName" class="inputField" value="${lectureReq.userName}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="reqDate" class="inputLabel">신청일자</label>
					<input type="text" id="reqDate" name="reqDate" class="inputField" value="${lectureReq.reqDate}" readonly>
				</div>
			</div>
			
			
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="fileRefNo" class="inputLabel">첨부파일</label>
					<c:choose>
						<c:when test="${not empty lectureReq.fileRefNo}">
							<a href="/professor/lecture/fileDownload.do?fileRefNo=${lectureReq.fileRefNo}" download class="download-button"> <i class="fas fa-download"></i> ${lectureReq.atchFile.atchOriginName}</a>
						</c:when>
						<c:otherwise>
							<a download class="download-button"> No File </a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
            <button type="button" class="cancelButton" onclick="location.href='/professor/grade/attendclassList.do'" style="float:right; margin-right:20px;">목록</button>
		</form>
	</div>
	
	
	<!-- 아래서부터는 학생 조회 -->
	
	<h2 class="sectionTitle">수강 신청 학생 조회</h2>
	<div class="section">
		<div class="card">
            <div class="sectionHeaderLine">
				<div>
					<div class="sectionHeaderDescription">전체 수강생 : ${studentCount}명</div>
				</div>
			</div>
			<div class="tableContainer">
				<table class="defaultTable">
					<thead class="tableHead">
						<tr>
							<th class="tableTh">번호</th>
							<th class="tableTh">학번</th>
							<th class="tableTh">이름</th>
							<th class="tableTh">단과대학</th>
							<th class="tableTh">학과</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty studentList }">
								<c:forEach items="${studentList }" var="student" varStatus="status">
								<c:url value="/professor/grade/gradeStdList.do" var="detailURL">
									<c:param name="userNo" value="${student.userNo}" />
								</c:url>
									<tr class="tableRowHover" onclick="moveToAttendance(this)" data-user-no="${student.userNo}" data-enr-no="${student.enrollNo}"  data-lec-no="${lectureReq.lecNo}">
										<td class="tableTd">${(studentPageNo - 1) * studentPageSize + status.index + 1}</td>
										<td class="tableTd">${student.userNo}</td>
										<td class="tableTd">${student.userName}</td>
										<td class="tableTd">${student.collegeName}</td>
										<td class="tableTd">${student.departmentName}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="tableRowHover tableRowStripe">
									<td class="tableTd" colspan="5">해당 강의를 수강 신청한 학생이 없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>

			<div class="pagination">
				<c:if test="${studentPageNo > 1}">
					<a href="?no=${lectureReq.reqNo}&studentPageNo=${studentPageNo - 1}" class="pageButton">이전</a>
				</c:if>

				<c:forEach var="i" begin="1" end="${totalStudentPages}">
					<c:choose>
						<c:when test="${i == studentPageNo}">
							<strong class="pageButton active">${i}</strong>
						</c:when>
						<c:otherwise>
							<a href="?no=${lectureReq.reqNo}&studentPageNo=${i}" class="pageButton">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${studentPageNo < totalStudentPages}">
					<a href="?no=${lectureReq.reqNo}&studentPageNo=${studentPageNo + 1}" class="pageButton">다음</a>
				</c:if>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function moveToAttendance(row) {
		const userNo = row.getAttribute("data-user-no");
		const enrNo = row.getAttribute("data-enr-no");
		const lecNo = row.getAttribute("data-lec-no"); 
		 window.location.href = "/professor/grade/gradeStdList.do?lecNo=" + lecNo + "&enrNo=" + enrNo +"&userNo=" + userNo;
	}
</script>