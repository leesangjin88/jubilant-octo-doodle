<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>비교과 프로그램 신청자 관리</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css" />

<h2 class="sectionTitle">비교과 프로그램 상세 정보</h2>
<div class="section">
	<div class="card">
		<form>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">프로그램 번호</label> <input type="text"
						class="inputField" value="${program.programNo}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">프로그램명</label> <input type="text"
						class="inputField" value="${program.programTitle}" readonly>
				</div>
				<%-- <div class="form-group" style="flex: 1;">
					<label class="inputLabel">유형</label> <input type="text"
						class="inputField" value="${program.type.typeName}" readonly>
				</div> --%>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">정원</label> <input type="text"
						class="inputField" value="${program.programCapacity}" readonly>
				</div>
			</div>

			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">시작일</label> <input type="text"
						class="inputField" value="${program.startDate}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">종료일</label> <input type="text"
						class="inputField" value="${program.endDate}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label class="inputLabel">장소</label> <input type="text"
						class="inputField" value="${program.place}" readonly>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group" style="width: 100%;">
					<label class="inputLabel">설명</label>
					<textarea class="inputField" readonly style="height: 80px;">${program.programDesp}</textarea>
				</div>
			</div>

			<button type="button" class="cancelButton"
				onclick="location.href='/staff/program/programList.do'"
				style="float: right; margin-right: 20px;">목록</button>
		</form>
	</div>

	<h2 class="sectionTitle">신청자 목록</h2>
	<div class="section">
		<div class="card">
			<div class="sectionHeaderLine">
				<div class="sectionHeaderDescription">전체 신청자 수:
					${fn:length(program.enrollList)} / ${program.programCapacity } 명</div>
			</div>

			<div class="tableContainer">
				<table class="defaultTable">
					<thead class="tableHead">
						<tr>
							<th class="tableTh">번호</th>
							<th class="tableTh">신청자 이름</th>
							<th class="tableTh">신청일</th>
							<th class="tableTh">완료 여부</th>
							<th class="tableTh">인증서 발급</th>
							<th class="tableTh">관리</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty program.enrollList}">
								<c:forEach items="${program.enrollList}" var="e"
									varStatus="status">
									<tr class="tableRowHover">
										<td class="tableTd">${status.index + 1}</td>
										<td class="tableTd">${e.student.user.userName}</td>
										<td class="tableTd">${e.applyDate}</td>
										<td class="tableTd"><c:choose>
												<c:when test="${e.isAttended eq 'Y'}">
													<c:choose>
														<c:when test="${e.isCompleted eq 'Y'}">
															<span class="badge badgeBlue">이수됨</span>
														</c:when>
														<c:otherwise>
															<button class="submitButton"
																onclick="markCompleted('${e.enrollNo}')">이수 처리</button>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<span class="badge badgeGray">참여 대상 아님</span>
												</c:otherwise>
											</c:choose></td>

										<td class="tableTd"><c:choose>
												<c:when test="${e.isCompleted eq 'Y'}">
													<c:choose>
														<c:when test="${e.isCertIssued eq 'Y'}">
															<span class="badge badgeGreen">발급됨</span>
														</c:when>
														<c:otherwise>
															<button class="submitButton"
																onclick="issueCertificate('${e.enrollNo}')">발급하기</button>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<span class="badge badgeGray">이수자 아님</span>
												</c:otherwise>
											</c:choose></td>

										<td class="tableTd"><c:choose>
												<c:when test="${empty e.isAttended}">
													<button class="deleteButton"
														onclick="updateAttended('${e.enrollNo}', 'N')">거절</button>
													<button class="submitButton"
														onclick="updateAttended('${e.enrollNo}', 'Y')">승인</button>
												</c:when>
												<c:when test="${e.isAttended eq 'Y'}">
													<span class="badge badgeGreen">수락됨</span>
												</c:when>
												<c:when test="${e.isAttended eq 'N'}">
													<span class="badge badgeRed">거절됨</span>
												</c:when>
											</c:choose></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5" class="tableTd">신청자가 없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script>
function updateAttended(enrollNo, value) {
	  fetch('/staff/program/apply/attended', {
	    method: 'POST',
	    headers: {'Content-Type': 'application/json'},
	    body: JSON.stringify({
	      enrollNo: enrollNo,
	      isAttended: value
	    })
	  })
	  .then(res => {
	    if (res.ok) {
	      alert(value === 'Y' ? '수락 처리되었습니다.' : '거절 처리되었습니다.');
	      location.reload();
	    } else {
	      alert("처리 실패");
	    }
	  });
}

function markCompleted(enrollNo) {
	  fetch('/staff/program/apply/complete', {
	    method: 'POST',
	    headers: {'Content-Type': 'application/json'},
	    body: JSON.stringify({
	      enrollNo: enrollNo,
	      isCompleted: 'Y'
	    })
	  })
	  .then(res => {
	    if (res.ok) {
	      alert("이수 처리 완료");
	      location.reload();
	    } else {
	      alert("이수 처리 실패");
	    }
	  });
}

function issueCertificate(enrollNo) {
	fetch('/staff/program/apply/issue?enrollNo=' + enrollNo, {
	    method: 'POST'
	  })
	  .then(res => {
	    if (res.ok) {
	      alert("이수증 발급 처리 완료");
	      location.reload();
	    } else {
	      alert("발급 실패");
	    }
	  });
}

</script>
