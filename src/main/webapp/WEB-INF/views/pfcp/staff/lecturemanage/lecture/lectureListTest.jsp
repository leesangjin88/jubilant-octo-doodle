<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<title>수업 목록 조회</title>


<!-- ======================================== -->
<div class="sectionHeaderLine">
  <div>
    <div class="sectionHeaderTitle">강의 목록</div>
    <div class="sectionHeaderDescription">전체 ${totalCount}개의 게시글</div>
  </div>
</div>


<%--       <c:choose> --%>
<%--         <c:when test="${not empty lecture }"> --%>
        
        
<%--           <c:forEach items="${lecture }" var="lecture" varStatus="status"> --%>
<%-- 					<tr class="tableRowHover" onclick="moveToDetail(this)" data-lecno="${lecture.lecNo}"> --%>
<%-- 						<td class="tableTd">${status.index+1}</td> --%>
<%-- 						<td class="tableTd" style="text-align: left;">${lecture.lectureReq.lecName}</td> --%>
<%-- 						<td class="tableTd">${lecture.lecNo}</td> --%>

<%-- 						<c:choose> --%>
<%-- 							<c:when test="${lecture.lectureReq.lecCategory eq 'MJ_REQ'}"> --%>
<!-- 								<td class="tableTd">전공필수</td> -->
<%-- 							</c:when> --%>
<%-- 							<c:when test="${lecture.lectureReq.lecCategory eq 'MJ_SEL'}"> --%>
<!-- 								<td class="tableTd">전공선택</td> -->
<%-- 							</c:when> --%>
<%-- 							<c:when test="${lecture.lectureReq.lecCategory eq 'GE_REQ'}"> --%>
<!-- 								<td class="tableTd">교양필수</td> -->
<%-- 							</c:when> --%>
<%-- 							<c:when test="${lecture.lectureReq.lecCategory eq 'GE_SEL'}"> --%>
<!-- 								<td class="tableTd">교양선택</td> -->
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								<td class="tableTd">기타</td> -->
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>

<%-- 						<td class="tableTd">${lecture.user.userName }</td> --%>
<%-- 						<td class="tableTd">${lecture.subject.gradeLevel }</td> --%>
<%-- 						<td class="tableTd">${lecture.lectureReq.preDay }</td> --%>
<!-- 					 </tr> -->
<%--           </c:forEach> --%>
<%--         </c:when> --%>

<%--         <c:otherwise> --%>
<!--           <tr> -->
<!--             <td colspan="7" class="tableTd noticeInfo">아직 개설된 강의 없음.</td> -->
<!--           </tr> -->
<%--         </c:otherwise> --%>
<%--       </c:choose> --%>

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

<script type="text/javascript">
	function moveToDetail(row) {
		const lecNo = row.getAttribute("data-lecno");
		window.location.href = "/staff/lecturemanage/lecture/lectureDetail.do?what=" + lecNo;
	}
</script>


