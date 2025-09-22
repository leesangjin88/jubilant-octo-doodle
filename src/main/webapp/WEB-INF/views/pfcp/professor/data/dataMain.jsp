<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<title>Insert title here</title>
<style>
.main-grid-container {
   display: grid;
   grid-template-columns: repeat(5, 1fr);
   gap: 10px; 
   flex-grow: 1; 
   width: 100%;
   height: 650px; 
}

.column {
   /* border: 2px solid blue;  */
   box-sizing: border-box; 
   display: flex;
   justify-content: center; 
   align-items: center; 
   text-align: center; 
}
.dataimg{
	width: 20%;
	
}
</style>
<h1>데이터 조회</h1><br>
<div class="main-grid-container">
   <div class="column">
   <a href="/professor/dataManagement/lectureEvaluation.do?no=${userNo}">
      <img src="/dist/assets/img/professor/강의평가.png" class="dataimg" />
      <h1>강의 평가</h1>
   </a>
   </div>
   <div class="column">
   <a href="/professor/dataManagement/attendanceRateChart.do">
      <img src="/dist/assets/img/professor/출석.png" class="dataimg" />
      <h1>학생 출석</h1>
   </a>
   </div>
   <div class="column">
   <a href="/professor/dataManagement/extracurricular.do">
      <img src="/dist/assets/img/professor/비교과.png" class="dataimg"  />
      <h1>비교과</h1>
   </a>
   </div>
   <div class="column">
   <a href="/professor/dataManagement/requiredSubjectData.do">
      <img src="/dist/assets/img/professor/과목정보.png" class="dataimg" />
      <h1>과목 정보</h1>
   </a>
   </div>
   <div class="column">
   <a href="/professor/dataManagement/evaluationAnalysis.do">
      <img src="/dist/assets/img/professor/역량발전.png" class="dataimg" />
      <h1>학생별 역량 발전</h1>
   </a>
   </div>
</div>

<script>
    <c:if test="${not empty message}">
        alert('${message}');
    </c:if>
</script>