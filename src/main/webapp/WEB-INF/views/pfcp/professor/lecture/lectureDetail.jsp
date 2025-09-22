<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-10 	김태수	최초 생성 
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>강의 신청 상세 조회</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />
<h2 class="sectionTitle">강의 신청 상세 조회</h2>
<div class="section">
	<div class="card">
		<form> 
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="reqNo" class="inputLabel">강의 신청 번호</label>
					<input type="text" id="reqNo" name="reqNo" class="inputField" value="${lectureReq.reqNo}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="lecName" class="inputLabel">강의명</label>
					<input type="text" id="lecName" name="lecName" class="inputField" value="${lectureReq.lecName}" readonly>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="subjectName" class="inputLabel">강의 과목</label>
					<input type="text" id="subjectName" name="subjectName" class="inputField" value="${lectureReq.subjectName}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="userName" class="inputLabel">신청자(교수 이름)</label>
					<input type="text" id="userName" name="userName" class="inputField" value="${lectureReq.userName}" readonly>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="lecCategory" class="inputLabel">강의 분류</label>
					<input type="text" id="lecCategory" name="lecCategory" class="inputField" value="${lectureReq.lecCategory}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="reqDate" class="inputLabel">신청일자</label>
					<input type="text" id="reqDate" name="reqDate" class="inputField" value="${lectureReq.reqDate}" readonly>
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
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
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
				    <label for="status" class="inputLabel">처리 현황</label>
					<c:choose>
						<c:when test="${lectureReq.status eq '대기'}">
							<span class="badge badgeGreen inputField-like-badge">대기</span>
						</c:when>
						<c:when test="${lectureReq.status eq '반려'}">
							<span class="badge badgeYellow inputField-like-badge">반려</span>
						</c:when>
						<c:when test="${lectureReq.status eq '취소'}">
							<span class="badge badgeRed inputField-like-badge">취소</span>
						</c:when>
						<c:when test="${lectureReq.status eq '승인'}">
							<span class="badge badgeBlue inputField-like-badge">승인</span>
						</c:when>
						<c:otherwise>
							<span class="badge inputField-like-badge">${lectureReq.status}</span>
						</c:otherwise>
					</c:choose>
				</div>
			    <c:if test="${lectureReq.status eq '반려'}">
			        <div class="form-group" style="flex: 1;">
			            <label for="rejReason" class="inputLabel">반려 사유</label>
			            <c:choose>
			                <c:when test="${not empty lectureReq.rejReason}">
			                    <input type="text" id="rejReason" name="rejReason" class="inputField" value="${lectureReq.rejReason}" readonly>
			                </c:when>
			                <c:otherwise>
			                    <input type="text" class="inputField" value="반려 사유 없음" readonly>
			                </c:otherwise>
			            </c:choose>
			        </div>
			    </c:if>
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
            <button type="button" class="editButton" onclick="location.href='/professor/lecture/lectureUpdate.do?no=${lectureReq.reqNo}'" style="float:right;">수정</button>
            <c:if test="${lectureReq.status eq '대기'}">
            	<button type="button" onclick="event.stopPropagation(); location.href='/professor/lecture/lectureDelete.do?no=${lectureReq.reqNo}'" class="deleteButton"  style="float:right; margin-right:20px;">신청취소</button>
            </c:if>
            <button type="button" class="cancelButton" onclick="location.href='/professor/lecture/lectureList.do'" style="float:right; margin-right:20px;">목록</button>

		</form>
	</div>
</div>