<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">

<title>수업 수강 학생 조회</title>
<form id="attendanceForm" method="post" action="">
    <input type="hidden" name="enrNo" value="${param.enrNo}" />
    <input type="hidden" name="userNo" value="${param.userNo}" />
    <div class="sectionHeaderLine">
        <div>
            <div class="sectionHeaderTitle">${lectureName} [${userName}] 출석 정보 조회</div>
        </div>
    </div>

    <div style="margin-bottom: 60px;">
        <button type="button" class="editButton" style="float:right;" onclick="submitForm('update')">수정</button>
    </div>

    <div class="tableContainer">
        <table class="defaultTable" id="attendanceTable">
            <thead class="tableHead">
                <tr>
                    <th class="tableTh">주차</th>
                    <th class="tableTh">출석상태</th>
                    <th class="tableTh">비고</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${attendance}" var="item" varStatus="status">
                    <tr class="tableRowHover">
                        <td class="tableTd">${item.week}주차</td>
                        <td class="tableTd" style="text-align:center;">
                            <input type="hidden" name="attendanceList[${status.index}].attendId" value="${item.attendId}" />
                            <input type="hidden" name="attendanceList[${status.index}].enrollNo" value="${item.enrollNo}" />
                            <input type="hidden" name="attendanceList[${status.index}].week" value="${item.week}"/>
                            <select name="attendanceList[${status.index}].attendStatus" class="form-control selectBox" style="width:30%; display:inline-block; text-align:center">
                                <option value="출석" ${item.attendStatus eq '출석' ? 'selected' : ''}>출석</option>
                                <option value="지각" ${item.attendStatus eq '지각' ? 'selected' : ''}>지각</option>
                                <option value="결석" ${item.attendStatus eq '결석' ? 'selected' : ''}>결석</option>
                                <option value="인정결석" ${item.attendStatus eq '인정결석' ? 'selected' : ''}>인정결석</option>
                                <option value="미정" ${item.attendStatus eq '미정' || item.attendStatus eq null || item.attendStatus eq '' ? 'selected' : ''}>미정</option>
                            </select>
                        </td>
                        <td class="tableTd">
                            <input class="inputField" name="attendanceList[${status.index}].memo" value="${item.memo}" style="width:80%" />
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty attendance || attendance.size() lt 10}">
                </c:if>
            </tbody>
        </table>
    </div>
</form>

<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function() {
        const form = document.getElementById('attendanceForm');
        const tbody = document.querySelector('#attendanceTable tbody');
        
        const currentEnrNo = form.querySelector('input[name="enrNo"]').value;

        // 현재 tbody의 행 개수를 확인합니다.
        let initialRowCount = tbody.rows.length;
        console.log('Initial row count:', initialRowCount);

        // DB에서 가져온 데이터가 없거나 (initialRowCount가 0)
        // 10주차 미만의 데이터만 있을 경우, 나머지 주차를 채웁니다.
        if (initialRowCount < 10) {
            for (let i = initialRowCount; i < 10; i++) {
                const week = i + 1; // 1부터 10까지의 주차
                const newRow = tbody.insertRow();
                newRow.className = 'tableRowHover';
                
                newRow.innerHTML = 
                    '<td class="tableTd">' + week + '주차' + '</td>' + // 주차 (1부터 시작)
                    '<td class="tableTd" style="text-align:center;">' +
                        // attendId는 아직 없으므로 빈 값으로 둡니다.
                        '<input type="hidden" name="attendanceList[' + i + '].attendId" value="" />' +
                        '<input type="hidden" name="attendanceList[' + i + '].enrollNo" value="' + currentEnrNo + '" />' +
                        '<input type="hidden" name="attendanceList[' + i + '].week" value="' + week + '" />' +
                        '<select name="attendanceList[' + i + '].attendStatus" class="form-control selectBox" style="width:30%; display:inline-block; text-align:center">' +
                            '<option value="출석">출석</option>' +
                            '<option value="지각">지각</option>' +
                            '<option value="결석">결석</option>' +
                            '<option value="인정결석">인정결석</option>' +
                            '<option value="미정" selected>미정</option>' + // 기본값은 '미정'으로 선택
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
        // '생성' 버튼은 이제 단순히 화면에 데이터가 없어서 폼을 채우는 용도가 아니라,
        // 현재 화면에 있는 1~10주차 데이터를 처음 DB에 삽입할 때 사용하는 버튼입니다.
        // '수정' 버튼은 이미 DB에 있는 데이터를 업데이트할 때 사용합니다.
        
         if (actionType === 'update') {
            form.action = '/professor/attendance/attendanceUpdateProcess.do';
            form.submit();
        }
    }
</script>