<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<title>데이터 관리 - 강의 평가</title>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>
	.btn {
        padding: 10px 15px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 14px;
       
    }
    
    .button-group {
        display: flex;
        justify-content: flex-end; 
    }

    .button-group .btn:not(:last-child) {
        margin-right: 5px; 
    }
</style>
 <div class="container container-lg">
        <h2 class="text-center mb-20">강의 평가 점수 현황</h2>

      

        <div class="button-group center mt-20">
            <button class="btn btn-secondary" onclick="window.location.reload();">새로고침</button>
            <button class="btn btn-success"  onclick="location.href='/professor/dataManagement/data.do';">목록으로</button>
        </div>
        <br>
        <div class="button-group center mt-20">
            
        </div>
          <div class="p-20">
            <canvas id="lectureEvalChart"></canvas>
        </div>
    </div>

<script>
        const evalData = [];
        <c:forEach var="eval" items="${evalData}">
            evalData.push({
                eval_no: '${eval.evalNo}',
                overall_score: ${eval.overallScore},
                subject_name: '${eval.subjectName}'
            });
        </c:forEach>

       
        const labels = evalData.map(item => item.subject_name); 
        const scores = evalData.map(item => item.overall_score);

  
        const ctx = document.getElementById('lectureEvalChart').getContext('2d');
        const lectureEvalChart = new Chart(ctx, {
            type: 'bar', 
            data: {
                labels: labels, 
                datasets: [{
                    label: '총점',
                    data: scores,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(201, 203, 207, 0.6)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)',
                        'rgba(201, 203, 207, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        max: 100, 
                        title: {
                            display: true,
                        }
                    },
                    x: {
                        title: {
                            display: true,
                        }
                    }
                },
                plugins: {
                    title: {
                        display: true,
                        text: '강의 평가별 총점 분포 (과목명 기준)' 
                    },
                    legend: {
                        display: false 
                    }
                }
            }
        });
    </script>