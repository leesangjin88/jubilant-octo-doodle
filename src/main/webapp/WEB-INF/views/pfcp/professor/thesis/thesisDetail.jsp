<%--
 * 개정이력
 * 수정일		수정자	수정내용
 * ========================================
 * 250709	김태수	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<title>논문 신청 상세 조회</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">  
<h2 class="sectionTitle">논문 신청 상세 조회</h2>
<div class="section">
    <div class="card">
        <form method="post" action="/professor/thesis/thesisInsertProcess.do" enctype="multipart/form-data">
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="thesisTitle" class="inputLabel">제목</label> 
					<input type="text" id="thesisTitle" name="thesisTitle" class="inputField" value="${thesis.thesisTitle }" readonly>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="thesisNo" class="inputLabel">논문 번호</label>
					<input type="text" id="thesisNo" name="thesisNo" class="inputField" value="${thesis.thesisNo}" readonly>
				</div>
				<div class="form-group" style="flex: 1;">
					<label for="userNo" class="inputLabel">신청자(교수 이름)</label>
                	<input type="text" id="userNo" name="userNo"  class="inputField" readonly value="${thesis.userName}">
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
					<label for="submitDate" class="inputLabel">신청일</label> 
					<input type="text" id="submitDate" name="thesisTitle" class="inputField" value="${thesis.submitDate }" readonly>
				</div>
			</div>
			<div class="form-row" style="display: flex; gap: 20px;">
				<div class="form-group" style="flex: 1;">
				    <label for="status" class="inputLabel">처리 현황</label>
					<c:choose>
						<c:when test="${thesis.status eq '대기'}">
							<span class="badge badgeGreen inputField-like-badge">대기</span>
						</c:when>
						<c:when test="${thesis.status eq '반려'}">
							<span class="badge badgeYellow inputField-like-badge">반려</span>
						</c:when>
						<c:when test="${thesis.status eq '취소'}">
							<span class="badge badgeRed inputField-like-badge">취소</span>
						</c:when>
						<c:when test="${thesis.status eq '승인'}">
							<span class="badge badgeBlue inputField-like-badge">승인</span>
						</c:when>
						<c:otherwise>
							<span class="badge inputField-like-badge">${thesis.status}</span>
						</c:otherwise>
					</c:choose>
				</div>
			    <c:if test="${thesis.status eq '반려'}">
			        <div class="form-group" style="flex: 1;">
			            <label for="rejReason" class="inputLabel">반려 사유</label>
			            <c:choose>
			                <c:when test="${not empty thesis.rejReason}">
			                    <input type="text" id="rejReason" name="rejReason" class="inputField" value="${thesis.rejReason}" readonly>
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
						<c:when test="${not empty thesis.fileRefNo}">
							<a href="/professor/lecture/fileDownload.do?fileRefNo=${thesis.fileRefNo}" download class="download-button"> <i class="fas fa-download"></i> ${thesis.atchFile.atchOriginName}</a>
						</c:when>
						<c:otherwise>
							<a download class="download-button"> No File </a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
            <button type="button" class="editButton" onclick="location.href='/professor/thesis/thesisUpdate.do?no=${thesis.thesisNo}'" style="float:right;">수정</button>
            <c:if test="${thesis.status eq '대기'}">
            	<button type="button" onclick="event.stopPropagation(); location.href='/professor/thesis/thesisDelete.do?no=${thesis.thesisNo}'" class="deleteButton"  style="float:right; margin-right:20px;">신청취소</button>
            </c:if>
            <button type="button" class="cancelButton" onclick="location.href='/professor/thesis/thesisList.do'" style="float:right; margin-right:20px;">목록</button>
        </form>
    </div>
</div>