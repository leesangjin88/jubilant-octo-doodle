<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-17  	양수민	최초 생성
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>


<title>단과 대학 목록</title>
<%-- 기존 external CSS 링크 유지 --%>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
<link rel="stylesheet" href="/dist/assets/css/attendclass.css">

<style>
.lecture-list-container-flex {
  display: flex;
  gap: 30px;
  margin-top: 20px;
}

.lecture-list-left {
  width: 50%;
  
  /* Add Grid properties */
  display: grid;
  grid-template-columns: repeat(2, 1fr); /* Two columns, equal width */
  gap: 15px; /* Gap between grid items */
}

.lecture-details-right {
  width: 60%;
  min-height: 200px;
}

.college-item-card {
  background: #ffffff;
  border: 1px solid #e2e8f0;
  border-radius: 0.5rem;
  padding: 1rem;
  transition: all 0.2s;
  cursor: pointer;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.college-item-card:hover {
  border-color: #3b82f6;
  box-shadow: 0 4px 12px rgba(59, 130, 246, 0.15);
  transform: translateY(-2px);
}

.college-item-card.active {
  border-color: #3b82f6;
  background-color: #eff6ff;
  box-shadow: 0 0 0 2px rgba(59, 130, 246, 0.2);
}

.lecture-info-group {
  display: flex;
  align-items: flex-start;
  gap: 12px;
}

.lecture-icon-wrapper {
  flex-shrink: 0;
  width: 40px;
  height: 40px;
  background-color: #f8fafc;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s;
}

.college-item-card:hover .lecture-icon-wrapper {
  background-color: #3b82f6;
  color: #ffffff;
}

.lecture-icon {
  width: 20px;
  height: 20px;
  color: #64748b;
  transition: color 0.2s;
}

.college-item-card:hover .lecture-icon {
  color: #ffffff;
}

.lecture-name {
  font-size: 1rem;
  font-weight: 600;
  color: #1e293b;
  margin-bottom: 0.5rem;
  display: block;
}

.lecture-details {
  font-size: 0.875rem;
  color: #64748b;
  margin: 0.25rem 0;
}

.no-lecture-message {
  text-align: center;
  padding: 2rem;
  color: #64748b;
  background-color: #f8fafc;
  border-radius: 0.5rem;
  border: 1px solid #e2e8f0;
}

.detail-placeholder {
  color: #64748b;
  text-align: center;
  padding: 2rem;
  font-size: 0.875rem;
}

.college-detail-header {
  font-size: 1.125rem;
  font-weight: 600;
  color: #1e293b;
  margin-bottom: 1rem;
  padding-bottom: 0.5rem;
  border-bottom: 2px solid #e2e8f0;
}

.department-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 0.875rem;
  color: #475569;
}

.department-table th {
  background-color: #f8fafc;
  color: #334155;
  font-weight: 600;
  padding: 0.75rem;
  text-align: left;
  border-bottom: 2px solid #e2e8f0;
}

.department-table td {
  padding: 0.75rem;
  border-bottom: 1px solid #f1f5f9;
  color: #475569;
}

.department-table tr:hover {
  background-color: #f8fafc;
}

.department-table tr:nth-child(even) {
  background-color: #f9fafb;
}

.tuition-amount {
  font-weight: 600;
  color: #059669;
}

.error-message {
  color: #dc2626;
  text-align: center;
  padding: 1rem;
  background-color: #fee2e2;
  border-radius: 0.375rem;
  border: 1px solid #fecaca;
}


</style>

	<!-- ======================================== -->
	<div class="sectionHeaderLine">
		<!-- 왼쪽 제목 및 설명 -->
		<div>
			<div class="sectionHeaderTitle">단과대학 목록</div>
			<%-- <div class="sectionHeaderDescription">전체 ${count}개의 단과대학</div> --%>
		</div>

		<!-- 오른쪽 등록 버튼 -->
		<button type="button" class="submitButton">+ 등록</button>
	</div>
	<!-- ======================================== -->

	<div class="lecture-list-container-flex">
		<!-- 왼쪽: 카드 리스트 -->
		<div class="lecture-list-left">
			<c:set var="displayedCount" value="0" />
			<c:choose>
				<c:when test="${not empty collegeList}">
					<c:forEach items="${collegeList}" var="college" varStatus="status">
						<div class="college-item-card lecture-item-link" data-college-no="${college.collegeNo}">
							<div class="lecture-info-group">
								<div class="lecture-icon-wrapper">
									<svg xmlns="http://www.w3.org/2000/svg" class="lecture-icon"
										fill="none" viewBox="0 0 24 24" stroke="currentColor">
									<path stroke-linecap="round" stroke-linejoin="round"
											stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 8h1m-1-4h1m4 4h1m-1-4h1" />
								</svg>
								</div>
								<div>
									<span class="lecture-name">${college.collegeName}</span>
									<p class="lecture-details">・ 단과대학 번호: ${college.collegeNo}</p>
									<p class="lecture-details">・ 운영 학과 수: ${college.department.dcount}</p>
								</div>
							</div>
						</div>
						<c:set var="displayedCount" value="${displayedCount + 1}" />
					</c:forEach>
				</c:when>
			</c:choose>

			<c:if test="${displayedCount == 0}">
				<div class="no-lecture-message">단과대학 목록이 존재하지 않습니다.</div>
			</c:if>
		</div>

		<!-- 오른쪽: 세부 정보 출력 영역 -->
		<div class="lecture-details-right card" id="collegeDetailArea">
			<div class="detail-placeholder">단과대학을 선택하면 학과와 등록금 정보가 여기에 표시됩니다.</div>
		</div>
	</div>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
$(document).on("click", ".lecture-item-link", function() {
    // 이전 선택 제거
    $(".college-item-card").removeClass("active");
    // 현재 선택 추가
    $(this).addClass("active");
    
    const collegeNo = $(this).data("college-no");

    $.ajax({
        type: "GET",
        url: "/staff/tuition/departmentList.do",
        data: { no: collegeNo },
        success: function(data) {
            console.log("서버에서 받은 데이터:", data);
            
            var html = '<div class="college-detail-header">학과별 등록금</div>';
            html += '<table class="department-table">';
            html += '<thead><tr><th>학과명</th><th>등록금</th></tr></thead><tbody>';
            
            $.each(data, function(index, dept) {
                console.log(index + "번째 항목:", dept);
                
                var departmentName = dept.departmentName || '학과명 없음';
                var tuition = dept.tuition || '정보 없음';
                
                console.log("추출된 값:", departmentName, tuition);
                
                html += '<tr>';
                html += '<td>' + departmentName + '</td>';
                html += '<td><span class="tuition-amount">' + tuition + '</span></td>';
                html += '</tr>';
            });
            
            html += '</tbody></table>';
            console.log("생성된 HTML:", html);
            $("#collegeDetailArea").html(html);
        },
        error: function() {
            $("#collegeDetailArea").html('<div class="error-message">정보를 불러오는 데 실패했습니다.</div>');
        }
    });
});
</script>