/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
	const CPATH = document.body.dataset.contextPath;
	const pHakgi = document.querySelector("#pHakgi");
	const initVal = pHakgi.dataset.initVal;

	console.log("체로롱 :", initVal);

	axios.get(`${CPATH}/student/grade/semesterList.do`).then(resp => {
		const semesterList = resp.data;

		console.log(semesterList);

		if (semesterList) {
			// term 값만 추출
			const termSet = new Set();

			// 중복 제거된 term 배열로 변환
			semesterList.forEach(item => {
				termSet.add(item.term);
			});

			// 중복 제거된 term 배열로 변환
			const uniqueTerms = Array.from(termSet);

			// option 태그 생성
			const options = uniqueTerms.map(term => `<option value="${term}">${term}</option>`)
				.join("\n");

			pHakgi.innerHTML += options;

			pHakgi.value = initVal ?? "";
		}
	}).catch(err => {
		console.error("학기 목록 로드 실패:", err);
	});

	document.querySelector("#submitButton").addEventListener("click", (e) => {
		e.preventDefault();

		const year = document.querySelector("#pHaknyeondo").value;
		const term = document.querySelector("#pHakgi").value;
		const allTimeYn = document.querySelector("#allTimeYn").checked ? "Y" : "N";

		console.log("year :", year);
		console.log("term :", term);

		axios.get(`${CPATH}/student/grade/historyList.do`, {
			params: {
				year: year,
				semester: term,
				allTimeYn: allTimeYn
			}
		}).then(resp => {
			const lectureList = resp.data;
			console.log(lectureList);

			const tbody = document.querySelector(".defaultTable tbody");
			tbody.innerHTML = ""; // 기존 테이블 초기화

			if (!lectureList || lectureList.length === 0) {
				tbody.innerHTML = `<tr><td class="tableTd" colspan='5'>조회된 데이터가 없습니다.</td></tr>`;

				return;
			}

			let html = "";
			lectureList.forEach(item => {
				html += `
		            	<tr>
			                <td class="tableTd">${item.semester?.semesterName ?? "-"}</td>
			                <td class="tableTd">${item.subject?.subjectName ?? "-"}</td>
			                <td class="tableTd">${item.subject?.credit ?? "-"}</td>
			                <td class="tableTd">${item.grade ?? "-"}</td>
			                <td class="tableTd">${item.gradePoint ?? "-"}</td>
			            </tr>
		            `;
			});
			tbody.innerHTML = html;
		}).catch(err => {
			console.error("성적 조회 실패:", err);
		});
	});

});