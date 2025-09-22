<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<%--
   파일명 : noticeList.jsp
   프로그렴명 : 공지사항 전체 조회
   설 명 : 공지 사항을 카테고리별로 전체 조회[전체/일반/학사/비교과/건의사항/기타]
   작성자 : 양 수 민
   작성일 : 2025. 07. 01
--%>


<title>공지사항 목록</title>

<c:if test="${not empty success}">
  <script>
    alert("${success}");
  </script>
</c:if>


<!-- <div class="max-w-6xl mx-auto mt-12 p-8 bg-white rounded-xl shadow"> -->

  <a href="/staff/notice/noticeInsert.do" class="submitButton" style="float:right">+ 등록</a>
  <h4 class="pageTitle">공지사항</h4>
<!--   <h2 class="text-2xl font-bold text-gray-800 border-b pb-4 mb-6">📢 공지사항</h2> -->

  <!-- 상단 탭 -->
  <div class="flex flex-wrap gap-2 border-b mb-6 CategoryContainer">
    <button class="px-4 py-2 rounded-t-md border-b-2 font-medium text-gray-600 hover:text-blue-600 focus:outline-none active-tab"
            onclick="filterNotices('전체', this)">전체</button>
    <button class="px-4 py-2 rounded-t-md border-b-2 font-medium text-gray-600 hover:text-blue-600 focus:outline-none"
            onclick="filterNotices('일반', this)">일반</button>
    <button class="px-4 py-2 rounded-t-md border-b-2 font-medium text-gray-600 hover:text-blue-600 focus:outline-none"
            onclick="filterNotices('학사', this)">학사</button>
    <button class="px-4 py-2 rounded-t-md border-b-2 font-medium text-gray-600 hover:text-blue-600 focus:outline-none"
            onclick="filterNotices('비교과', this)">비교과</button>
    <button class="px-4 py-2 rounded-t-md border-b-2 font-medium text-gray-600 hover:text-blue-600 focus:outline-none"
            onclick="filterNotices('건의사항', this)">건의사항</button>
    <button class="px-4 py-2 rounded-t-md border-b-2 font-medium text-gray-600 hover:text-blue-600 focus:outline-none"
            onclick="filterNotices('기타', this)">기타</button>
  </div>

  <!-- 학사 소분류 -->
  <div id="subCategoryContainer" class="mb-6 hidden">
    <div class="flex flex-wrap gap-2">
      <button class="px-3 py-1 border rounded text-sm bg-blue-100 text-blue-700 active-sub"
              onclick="filterSubCategory('ALL', this)">학사전체</button>
      <button class="px-3 py-1 border rounded text-sm bg-gray-100 hover:bg-blue-100"
              onclick="filterSubCategory('ACA_SCH', this)">수강일정</button>
      <button class="px-3 py-1 border rounded text-sm bg-gray-100 hover:bg-blue-100"
              onclick="filterSubCategory('ACA_EXM', this)">시험 및 평가 일정</button>
      <button class="px-3 py-1 border rounded text-sm bg-gray-100 hover:bg-blue-100"
              onclick="filterSubCategory('ACA_STA', this)">학적 변경</button>
      <button class="px-3 py-1 border rounded text-sm bg-gray-100 hover:bg-blue-100"
              onclick="filterSubCategory('ACA_GRD', this)">졸업</button>
      <button class="px-3 py-1 border rounded text-sm bg-gray-100 hover:bg-blue-100"
              onclick="filterSubCategory('ACA_TUI', this)">등록금 및 장학금</button>
      <button class="px-3 py-1 border rounded text-sm bg-gray-100 hover:bg-blue-100"
              onclick="filterSubCategory('ACA_HOL', this)">방학 및 휴일</button>
    </div>
  </div>

  <!-- 공지 목록 테이블 -->
  <div class="overflow-x-auto">
  <div class="tableContainer">
    <table class="w-full defaultTable">
      <thead class="tableHead ">
        <tr>
          <th class="p-3 border tableTh">번호</th>
          <th class="p-3 border tableTh">유형</th>
          <th class="p-3 border tableTh">제목</th>
          <th class="p-3 border tableTh">작성자</th>
          <th class="p-3 border tableTh">작성일시</th>
        </tr>
      </thead>
      <tbody id="noticeTableBody" class="text-sm">
	     <c:choose>
				<c:when test="${not empty board }">
					<c:forEach items="${board }" var="board">
						<c:url value="/staff/studentmanage/studentDetail.do"
							var="detailURL">
							<c:param name="what" value="${student.userNo }" />
						</c:url>
				      	<tr class="tableRowHover" onclick="moveToDetail(this)" data-board-no="${board.boardNo }" data-category="${board.category}" data-typecode="${board.typeCode}" data-writer="${board.writerNo }">
					        <td class="p-3 border tableTd">${board.boardNo}</td>
					        <td class="p-3 border tableTd">${board.category}</td>
					        <td class="p-3 border tableTd" style="text-align:left">${board.boardTitle}</td>
					        <td class="p-3 border tableTd">${board.user.userName}</td>
					        <td class="p-3 border tableTd">${board.updateDate}</td>
				      	</tr>
				      </c:forEach>
				</c:when>
	
				<c:otherwise>
					<tr>
						<td class="p-3 border tableTd" colspan="5">아직 게시글 없음.</td>
					</tr>
				</c:otherwise>
			</c:choose>
      </tbody>
    </table>
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
  let currentType = '전체';
  let currentSubType = '';
  
  document.addEventListener('DOMContentLoaded', function() {
	  // "전체" 버튼을 초기 상태로 활성화
	  const defaultButton = document.querySelector('.CategoryContainer button');
	  filterNotices(defaultButton.innerText, defaultButton);
	});
  
  
  function moveToDetail(row) {
		const boardNo = row.getAttribute("data-board-no");
		window.location.href = "/staff/notice/noticeDetail.do?what=" + boardNo;
	}
  


  function renderNoticeList(list) {
    const tbody = document.getElementById('noticeTableBody');
    tbody.innerHTML = '';

    if (list.length === 0) {
      tbody.innerHTML = '<tr><td colspan="6" class="text-center py-6 text-gray-500">조회된 공지가 없습니다.</td></tr>';
      return;
    }

    list.forEach(notice => {
      const row = document.createElement('tr');
      row.className = "hover:bg-gray-50";
      row.innerHTML = `
    	  //----------------------
      `;
      tbody.appendChild(row);
    });
  }

  function filterNotices(category, btn) {
	    // 1. 버튼 상태 초기화
	    const buttons = document.querySelectorAll('.CategoryContainer button');
	    buttons.forEach(b => b.classList.remove('text-blue-600', 'border-blue-600'));
	    btn.classList.add('text-blue-600', 'border-blue-600');

	    // 2. 학사 소분류 보이기/숨기기
	    const subCategoryContainer = document.getElementById('subCategoryContainer');
	    if (category === '학사') {
	      subCategoryContainer.classList.remove('hidden');
	    } else {
	      subCategoryContainer.classList.add('hidden');
	    }

	 // 게시글 필터링
	    const rows = document.querySelectorAll('#noticeTableBody tr');
	    rows.forEach(row => {
	      const rowCategory = row.getAttribute('data-category');

	      
	      if (category === '전체' || rowCategory === category) {
	        row.style.display = '';
	      } else {
	        row.style.display = 'none';
	      }
	    });
	    
	    
	  }



  function filterSubCategory(subtype, btn) {
    currentSubType = subtype;

    // 모든 소분류 초기화
    document.querySelectorAll('#subCategoryContainer button').forEach(b => {
      b.classList.remove('bg-blue-100', 'text-blue-700');
      b.classList.add('bg-gray-100', 'text-gray-700');
    });

    // 현재 소분류 활성화
    btn.classList.remove('bg-gray-100', 'text-gray-700');
    btn.classList.add('bg-blue-100', 'text-blue-700');

  // 게시글 필터링
    const rows = document.querySelectorAll('#noticeTableBody tr');
    rows.forEach(row => {
      const typecode = row.getAttribute('data-typecode');

      if(subtype == "ALL"){
    	  row.style.display = '';
      } else if (subtype === typecode) {
        row.style.display = '';
      } else {
        row.style.display = 'none';
      }
    });
    
    applyFilters();
  }

  function applyFilters() {
    let result = [...notices];
    if (currentType !== '전체') result = result.filter(n => n.type === currentType);
    if (currentType === '학사' && currentSubType && currentSubType !== '학사전체')
      result = result.filter(n => n.subtype === currentSubType);
    renderNoticeList(result);
  }

  document.addEventListener('DOMContentLoaded', loadNotices);
</script>


