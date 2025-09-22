<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />

<%--  
	파일명 : scholarshipList.jsp  
	프로그램명 : 장학금 목록 전체 조회 화면  
	설명 : 전체 장학금 목록 조회 가능
	작성자 : 양 수 민  
	작성일 : 2025. 07. 11 
--%>


<%-- 수정 중인 행 ID 파라미터 받기 --%>
<c:set var="editRowId" value="${param.editRowId}" />

<style>
.modal {
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0,0,0,0.4);
}

.modal-content {
  background-color: #fefefe;
  margin: 10% auto; 
  padding: 20px;
  border: 1px solid #888;
  width: 40%;
  border-radius: 8px;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: black;
}
</style>


<title>장학금 목록</title>

<!-- ======================================== -->
<div class="sectionHeaderLine">
	<!-- 왼쪽 제목 및 설명 -->
	<div>
		<div class="sectionHeaderTitle">장학금 목록</div>
		<div class="sectionHeaderDescription">전체 ${totalCount}개의 장학금</div>
	</div>

	<!-- 오른쪽 등록 버튼 -->
	<button type="button" class="submitButton" onclick="openModal()">+ 등록</button>
</div>
<!-- ======================================== -->


<c:if test="${not empty success}">
	<script>
    	alert("${success}");
    </script>
</c:if>




<div class="tableContainer">
<form method="post" enctype="multipart/form-data" action="/staff/scholarship/staffScholarshipUpdateProcess.do">
	<table class="defaultTable">
		<thead class="tableHead">
			<tr>
				<th class="tableTh">번호</th>
				<th class="tableTh">장학금명</th>
				<th class="tableTh">설명</th>
				<th class="tableTh">파일</th>
				<th class="tableTh">관리</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty scholarshipTypeList}">
					<c:forEach items="${scholarshipTypeList}" var="item" varStatus="status">
						<%-- 현재 행이 수정 중인지 확인 --%>
						<c:set var="isEditing" value="${editRowId eq item.schTypeNo}" />

						<tr class="tableRowHover" data-type-no="${item.schTypeNo }" onclick="moveToApplyList(this)">
							
							<td class="tableTd">${status.index + 1}</td>
							
							
							
							<td class="tableTd">
								<c:choose>
									<c:when test="${isEditing}">
										<input class="inputField" value="${item.schName}" name="schName" id="schName" style="text-align:center">
									</c:when>
									<c:otherwise>
										${item.schName}
									</c:otherwise>
								</c:choose>
							</td>
							
							
							<td class="tableTd" style="text-align:left;">
								<c:choose>
									<c:when test="${isEditing}">
										<input class="inputField" value="${item.schDesc}" name="schDesc" id="schDesc">
									</c:when>
									<c:otherwise>
										${item.schDesc}
									</c:otherwise>
								</c:choose>
							</td>

							<td class="tableTd" style="width:20%">
								<c:choose>
									<c:when test="${not empty item.schFileRefNo}">
										<c:if test="${isEditing}">
											<input type="file" name="uploadFile" class="form-control" />
										</c:if>
										<c:if test="${not isEditing}">
											<a download href="/staff/scholarship/fileDownload.do?fileRefNo=${item.schFileRefNo}">
												<i class="fas fa-download"></i> 다운로드
											</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:if test="${isEditing}">
											<input type="file" name="uploadFile" class="form-control" />
										</c:if>
										<c:if test="${not isEditing}">
											등록된 파일이 없습니다.
										</c:if>
									</c:otherwise>
								</c:choose>
							</td>
						

							<td class="tableTd" style="text-align:center;">
								<c:choose>
									<c:when test="${isEditing}">
										<!-- 저장 버튼 -->
										<button type="submit" class="submitButton" >
											<i class="fa-solid fa-floppy-disk"></i>
										</button>
										<!-- 취소 버튼 -->
										<button type="button" class="cancelButton" onclick="cancelEdit()">
											<i class="fa-solid fa-xmark"></i>
										</button>
										<input type="hidden" name="schTypeNo" value="${item.schTypeNo}" />
									</c:when>
									<c:otherwise>
										<!-- 수정 버튼 -->
										<button type="button" class="editButton" onclick="startEdit('${item.schTypeNo}')">
											<i class="fa-solid fa-pen-to-square"></i>
										</button>
										<!-- 삭제 버튼 -->
										<button type="button" class="deleteButton" onclick="activeToN('${item.schTypeNo}')">
											<i class="fa-solid fa-trash"></i>
										</button>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						
					</c:forEach>
					
					
				</c:when>
					

				<c:otherwise>
					<tr>
						<td colspan="5" class="tableTd noticeInfo">아직 등록된 장학금 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</form>
</div>

<!-- 등록 모달창 -->
<div id="registerModal" class="modal" style="display:none;">
  <div class="modal-content">
    <span class="close" onclick="closeModal()" style="text-align:right">&times;</span>
    <h3 style="text-align:center">장학금 등록</h3>
    <form method="post" enctype="multipart/form-data" action="/staff/scholarship/staffScholarshipInsertFormProcess.do">
      <div class="form-group">
        <label class="inputLabel">장학금명</label>
        <input type="text" name="schName" class="inputField" required>
      </div>
      <div class="form-group">
        <label class="inputLabel">설명</label>
        <input type="text" name="schDesc" class="inputField" required>
      </div>
      <div class="form-group">
        <label class="inputLabel">파일</label>
        <input type="file" name="uploadFile" class="form-control">
      </div>
      <div style="text-align: right; margin-top: 1rem;">
        <button type="submit" class="submitButton">저장</button>
      </div>
    </form>
  </div>
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

<script>

function moveToApplyList(row){
	const schTypeNo = row.getAttribute("data-type-no");
	window.location.href="/staff/scholarshipApply/staffScholarshipDetail.do?schTypeNo=" + schTypeNo;
}

//==========================================================================
let isEditing = false;

function openModal() {
    document.getElementById('registerModal').style.display = 'block';
  }

  function closeModal() {
    document.getElementById('registerModal').style.display = 'none';
  }
  
  window.onclick = function(event) {
    const modal = document.getElementById('registerModal');
    if (event.target === modal) {
      closeModal();
    }
  }


function startEdit(rowId) {
	const url = new URL(window.location.href);
	url.searchParams.set('editRowId', rowId);
	window.location.href = url.toString();
}

function activeToN(rowId){
	if (confirm("정말로 삭제하시겠습니까?")) {
		window.location.href = "/staff/scholarship/staffScholarshipDelete.do?no=" + rowId;
	}
}

function cancelEdit() {
	const url = new URL(window.location.href);
	url.searchParams.delete('editRowId');
	window.location.href = url.toString();
}

window.addEventListener('DOMContentLoaded', function() {
	const url = new URL(window.location.href);

	// 새로고침인지 확인 (최신 브라우저 기준)
	const navEntries = performance.getEntriesByType("navigation");
	const isReload = navEntries.length > 0 && navEntries[0].type === "reload";

	if (isReload && url.searchParams.has('editRowId')) {
		url.searchParams.delete('editRowId');
		window.location.replace(url.toString());
	}
});


</script>



