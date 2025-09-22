<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%--
    파일명 : facilityList.jsp
    프로그렴명 : 시설 전체 조회 화면
    설 명 : 등록된 시설물 전체를 조회할 수 있음.
    작성자 : 이 성 화
    작성일 : 2025. 07. 16
--%>


<title>시설 목록</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<div class="card max-w-6xl mx-auto p-6">

	<!-- ======================================== -->
	<div class="sectionHeaderLine">
		<!-- 왼쪽 제목 및 설명 -->
		<div>
			<div class="sectionHeaderTitle">시설 목록</div>
			<div class="sectionHeaderDescription">전체 ${count}개의 게시글</div>
		</div>
	</div>
	<div class="mb-6">
		<div class="flex space-x-2">
			<input type="text" id="facilitySearchInput"
				placeholder="시설명 또는 시설 유형으로 검색" class="inputField"
				style='width: 95%' />
			<button onclick="searchFacilities()" class="searchButton">검색</button>
		</div>
	</div>
	<!-- ======================================== -->

	<!-- 시설 검색 -->

	<!-- 시설 목록 -->
	<div class="tableContainer overflow-x-auto">
		<table class="defaultTable">
			<thead class="tableHead">
				<tr>
					<!-- <th class="tableTh">시설 코드</th> -->
					<th class="tableTh text-left">번호</th>
					<th class="tableTh text-left">시설명</th>
					<th class="tableTh text-left">시설 유형</th>
					<th class="tableTh text-left">위치</th>
					<th class="tableTh text-left">수용 인원</th>
					<th class="tableTh text-left">시설 상태</th>
				</tr>
			</thead>
			<tbody id="facilityTableBody"
				class="bg-white divide-y divide-gray-100">
				<c:choose>
					<c:when test="${not empty facility }">
						<c:forEach items="${facility }" var="facility" varStatus="status">
							<c:url value="/student/facility/studentFacilityDetail.do"
								var="detailURL">
								<c:param name="what" value="${facility.facilityNo }" />
							</c:url>
							<tr class="tableRowHover tableRowStripe" style="cursor: pointer;"
								onclick="location.href='${detailURL}'">
								<td class="tableTd">${status.index+1 }</a></td>
								<td class="tableTd">${facility.facilityName}</td>
								<td class="tableTd">${facility.facilityType}</td>
								<td class="tableTd">${facility.location}</td>
								<td class="tableTd">${facility.facilityMp}</td>
								<td class="tableTd"><c:choose>
										<c:when test="${facility.facilityStatus == 'AVAILABLE'}">
											<span
												class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-green-100 text-green-800">
												✅ 이용 가능 </span>
										</c:when>
										<c:when test="${facility.facilityStatus == 'MAINTENANCE'}">
											<span
												class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-yellow-100 text-yellow-800">
												🔧 유지보수중 (이용 불가능) </span>
										</c:when>
										<c:when test="${facility.facilityStatus == 'UNAVAILABLE'}">
											<span
												class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-red-100 text-red-800">
												❌ 이용 불가능 </span>
										</c:when>
										<c:otherwise>
											<span
												class="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-gray-100 text-gray-800">
												상태 미정 </span>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</c:when>

					<c:otherwise>
						<tr>
							<td colspan="5" class="tableTd noticeInfo text-center">아직 시설
								없음.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>


<script>
	function searchFacilities() {
		const searchInput = document.getElementById('facilitySearchInput').value
				.toLowerCase();
		const tableRows = document.getElementById('facilityTableBody')
				.getElementsByTagName('tr');

		for (let i = 0; i < tableRows.length; i++) {
			const row = tableRows[i];
			const facilityName = row.cells[0] ? row.cells[0].textContent
					.toLowerCase() : '';
			const facilityType = row.cells[1] ? row.cells[1].textContent
					.toLowerCase() : '';

			if (facilityName.includes(searchInput)
					|| facilityType.includes(searchInput)) {
				row.style.display = '';
			} else {
				row.style.display = 'none';
			}
		}
	}

	document.getElementById('facilitySearchInput').addEventListener('keyup',
			function(event) {
				if (event.key === 'Enter') {
					searchFacilities();
				}
			});

	function showAddFacilityPage() {
		alert('새 시설 등록 페이지로 이동합니다.');
		window.location.href = '/staff/facility/facilityInsert.do';
	}
</script>



