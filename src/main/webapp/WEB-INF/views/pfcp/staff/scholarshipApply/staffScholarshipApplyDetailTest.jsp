<%--
 * == 개정이력(Modification Information) ==
 * 수정일	수정자	수정내용
 * ========================================
 * 250716	양수민	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>장학금 신청 정보 수정 (교직원)</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css"> 

<c:if test="${not empty success}">
	<script>
    alert("${success}");
  </script>
</c:if>


<div class="form-container">
    <div class="sectionHeaderLine">
        <div>
            <h2 class="sectionHeaderTitle">장학금 신청 정보</h2>
            <p class="sectionHeaderDescription">신청된 장학금의 상세 정보를 확인하고 승인 여부를 결정할 수 있습니다.</p>
        </div>
    </div>
    
    <form method="post" enctype="multipart/form-data" action="/staff/scholarshipApply/staffScholarshipUpdateProcess.do">
        <input type="hidden" name="no" value="${scholarship.applyNo}">
        <input type="hidden" name="applyNo" value="${scholarship.applyNo}">
        <input type="hidden" name="userNo" value="${scholarship.userNo}">
        <input type="hidden" name="fileRefNo" value="${scholarship.fileRefNo}">

		

        <div class="form-section">
	        <div class="form-group">
	            <label class="inputLabel">신청자 정보</label>
	            <input class="inputField" type="text" value="${scholarship.userNo}" readonly style="width:40%">
	            <input class="inputField" type="text" value="${scholarship.user.userName}" readonly style="width:40%">
	        </div>
        
            <div class="form-grid">
                <div class="form-group">
                    <label class="inputLabel" for="schName">장학금 유형</label>
                    <input style="display:none;" type="text" class="schTypeNo" id="schTypeNo" value="${scholarship.schTypeNo}" readonly>
                    <input type="text" class="inputField" id="schName" value="${scholarship.scholarshipType.schName}" readonly>
                </div>
                
                <div class="form-group">
                    <label class="inputLabel" for="requestDate">신청 일자</label>
                    <input type="text" class="inputField" id="requestDate" name="requestDate" value="${scholarship.requestDate}" readonly>
                </div>
                
                <div class="form-group">
                    <span class="attachment-label">첨부 파일 다운로드 </span>
                    <c:if test="${not empty scholarship.fileRefNo}">
                        <a href="/student/scholarship/fileDownload.do?fileRefNo=${scholarship.fileRefNo}" class="download-button">
                            <i class="fas fa-download"></i> ${scholarship.atchFile.atchOriginName}
                        </a>
                    </c:if>
                    <c:if test="${empty scholarship.fileRefNo}">
                        <span class="text-muted">첨부된 파일 없음</span>
                    </c:if>
                </div>

                
            </div>
            
            <div class="form-group full-width">
                <label class="inputLabel" for="requestComment">신청 내용</label>
                <textarea class="textareaField" id="requestComment" name="requestComment" rows="5" readonly>${scholarship.requestComment}</textarea>
            </div>

				<div class="form-grid">
				    <div class="form-group">
				        <label class="inputLabel" for="applyStatus">승인 여부</label>
				        <select class="selectBox" id="applyStatus" name="applyStatus">
				            <option value="신청대기" <c:if test="${scholarship.applyStatus eq '신청대기'}">selected</c:if>>신청대기</option>
				            <option value="승인" <c:if test="${scholarship.applyStatus eq '승인'}">selected</c:if>>승인</option>
				            <option value="반려" <c:if test="${scholarship.applyStatus eq '반려'}">selected</c:if>>반려</option>
				        </select>
				    </div>
				
				    <div class="form-group">
				        <label class="inputLabel" for="applyComment">승인 코멘트</label>
				        <input type="text" value="${scholarship.applyComment}" class="inputField" id="applyComment" name="applyComment" placeholder="전달 사항이 있다면 적어주세요">
				    </div>
				</div>

        <div class="button-group">
            <button type="button" onclick="backToList()" class="cancelButton">목록으로</button>
            <button type="submit" class="submitButton">저장</button>
            <button type="button" onclick="confirmDelete()" class="deleteButton">삭제</button>
        </div>
    </form>
</div>

<script>
	let schTypeNoElement = document.getElementById('schTypeNo');
	let schTypeNo = schTypeNoElement.value;
	
	function backToList(){
	    window.location.href="/staff/scholarshipApply/staffScholarshipDetail.do?schTypeNo=" + schTypeNo;
	}
</script>

<style>
    /* General Form Layout */
    .form-section {
        margin-bottom: 1.5rem;
        padding: 1.5rem;
        background-color: #ffffff;
        border-radius: 0.5rem;
        border: 1px solid #e2e8f0;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    }
    
    /* Grid layout for form groups for better space utilization */
    .form-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); /* Responsive columns */
        gap: 1rem 2rem; /* Row gap, Column gap */
        align-items: end; /* Align items to the bottom if content height varies */
    }

    /* Full-width groups for textarea or single large elements */
    .form-group.full-width {
        grid-column: 1 / -1; /* Spans all columns */
    }

    .form-group {
        display: flex;
        flex-direction: column; /* Stack label and input by default */
        margin-bottom: 0; /* Managed by grid gap */
    }

    /* Override for read-only input fields and textareas */
    .inputField[readonly],
    .textareaField[readonly] {
        background-color: #f8fafc;
        color: #64748b;
        cursor: not-allowed;
    }

    /* Button Group */
    .button-group {
        display: flex;
        justify-content: flex-end;
        gap: 0.75rem;
        margin-top: 2rem;
    }

    /* Specific adjustments for attachment section */
    .attachment-section {
        display: flex; /* Keep it flex for label and button */
        flex-wrap: wrap;
        align-items: center;
        gap: 10px; /* Space between label and download button */
        /* Removed top/bottom padding to let grid gap handle it */
    }
    .attachment-label {
        margin-bottom: 0; /* Remove default margin-bottom for labels in flex */
        white-space: nowrap; /* Prevent label from wrapping */
    }
    
    /* Separator Line */
    .staff-action-separator {
        margin: 2rem 0;
        border-top: 1px dashed #e2e8f0;
    }

    /* Staff approval info title */
    .sectionTitle {
        margin-top: 0; /* Reduce top margin if following a separator */
        margin-bottom: 1rem;
    }
    
    .form-grid {
    display: flex; /* Makes the form-grid a flex container */
    gap: 20px; /* Adds space between the form groups (adjust as needed) */
    align-items: flex-start; /* Aligns items to the start of the cross-axis */
}

.form-group:nth-child(1) { /* Targets the "승인 여부" form-group */
    flex: 1; /* Takes up 1 part of the available space */
    min-width: 150px; /* Optional: ensures a minimum width for the select box */
}

.form-group:nth-child(2) { /* Targets the "승인 코멘트" form-group */
    flex: 2; /* Takes up 2 parts of the available space (making it twice as wide as the first) */
}

/* Optional: Basic styling for labels and input fields for better presentation */
.inputLabel {
    display: block; /* Makes labels stack above their inputs */
    margin-bottom: 5px;
    font-weight: bold;
}

.selectBox,
.inputField {
    width: 100%; /* Makes the select and input fields fill their parent container */
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box; /* Includes padding and border in the element's total width */
}

.inputField {
    display: inline-block; /* Makes the input elements behave like text but allows width/height */
    width: 40%;
    margin-right: 10px; /* Adds some space to the right of the first input (optional) */
}

.inputField:last-child {
    margin-right: 0; /* Removes margin from the last input */
}
</style>