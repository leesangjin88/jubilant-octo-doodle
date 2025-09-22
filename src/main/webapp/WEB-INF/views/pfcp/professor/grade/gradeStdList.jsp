<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%--
 * == 개정이력(Modification Information) ==
 * 수정일			수정자	수정내용
 * ========================================
 * 2025-07-17 	김태수	최초 생성
--%>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<title>수업 수강 학생 조회</title>
    <div class="container">
        <h1>강의 성적 관리</h1>

        <p><strong>강의 번호:</strong> ${lecNo}</p>
        <p><strong>강의명:</strong> <c:out value="${studentGrades[0].subjectName}"/></p>

        <c:if test="${not empty message}">
            <div style="color: green; font-weight: bold; margin-bottom: 10px;">${message}</div>
        </c:if>

        <form id="gradeForm" method="post" action="/professor/grade/updateGrades.do">
            <input type="hidden" name="lecNo" value="${lecNo}" />

            <div class="table-responsive">
                <table class="grade-table">
                    <thead>
                        <tr>
                            <th>학번</th>
                            <th>이름</th>
                            <th>학과</th>
                            <th>중간고사</th>
                            <th>기말고사</th>
                            <th>과제 제출 점수</th>
                            <th>출석점수</th>
                            <th>총 점</th>
                            <th>등급</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty studentGrades}">
                                <c:forEach var="student" items="${studentGrades}" varStatus="status">
                                    <tr>
                                        <td>${student.userNo}</td>
                                        <td>${student.userName}</td>
                                        <td>${student.departmentName}</td>
                                        <input type="hidden" name="enrollNoList" value="${student.enrollNo}" />
                                        <td>
                                            <input type="number" class="input-score" name="midtermScoreList"
                                                   value="${student.midtermScore != null ? student.midtermScore : 0}"
                                                   min="0" max="100">
                                        </td>
                                        <td>
                                            <input type="number" class="input-score" name="finalScoreList"
                                                   value="${student.finalScore != null ? student.finalScore : 0}"
                                                   min="0" max="100">
                                        </td>
                                        <td>
                                            <input type="number" class="input-score readonly-input"
                                                   value="${student.assignmentScore  != null ? student.assignmentScore  : 0}"
                                                   readonly>
                                        </td>
                                        <td>
                                            <input type="number" class="input-score" name="attendanceScoreList"
                                                   value="${student.attendanceScore != null ? student.attendanceScore : 0}"
                                                   min="0" max="100">
                                        </td>
                                        <td>
                                            <span class="final-grade">${student.finalGrade != null ? student.finalGrade : 0}</span>
                                        </td>
                                        <td>${student.finalGradeAlpha}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="11">해당 강의를 수강하는 학생이 없습니다.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

            <div class="button-container">
                <button type="button" onclick="history.back()">뒤로 가기</button>
                <button type="submit">성적 수정</button> </div>
        </form>
    </div>

<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function() {
        const form = document.getElementById('attendanceForm');
        const tbody = document.querySelector('#attendanceTable tbody');
        const currentEnrNo = form.querySelector('input[name="enrNo"]').value;

        let initialRowCount = tbody.rows.length;
        console.log('Initial row count:', initialRowCount);

        if (initialRowCount < 10) {
            for (let i = initialRowCount; i < 10; i++) {
                const week = i + 1; 
                const newRow = tbody.insertRow();
                newRow.className = 'tableRowHover';
                
                newRow.innerHTML = 
                    '<td class="tableTd">' + week + '주차' + '</td>' + 
                    '<td class="tableTd" style="text-align:center;">' +
                        '<input type="hidden" name="attendanceList[' + i + '].attendId" value="" />' +
                        '<input type="hidden" name="attendanceList[' + i + '].enrollNo" value="' + currentEnrNo + '" />' +
                        '<input type="hidden" name="attendanceList[' + i + '].week" value="' + week + '" />' +
                        '<select name="attendanceList[' + i + '].attendStatus" class="form-control selectBox" style="width:30%; display:inline-block; text-align:center">' +
                            '<option value="출석">출석</option>' +
                            '<option value="지각">지각</option>' +
                            '<option value="결석">결석</option>' +
                            '<option value="인정결석">인정결석</option>' +
                            '<option value="미정" selected>미정</option>' + 
                        '</select>' +
                    '</td>' +
                    '<td class="tableTd">' +
                        '<input class="inputField" name="attendanceList[' + i + '].memo" value="" style="width:80%" />' +
                    '</td>';
            }
        }
    });

    function submitForm(actionType) {
        const form = document.getElementById('attendanceForm');
         if (actionType === 'update') {
            form.action = '/professor/attendance/attendanceUpdateProcess.do';
            form.submit();
        }
    }
</script>