/**
 * 
 */
document.addEventListener("DOMContentLoaded", function() {
	const CPATH = document.body.dataset.contextPath;
	const lecNo = document.querySelector("#lecNo");
	const initVal = lecNo.dataset.initVal;
	
	const facilityNo = document.querySelector("#facilityNo");
	const initFacility = facilityNo.dataset.initFacility;
	
	axios.get(`${CPATH}/professor/exam/lecNameList.do`).then(resp => {
		const lecList = resp.data;
		
		if (lecList) {
			const options =  lecList.map(item => 
				`<option value="${item.lecNo}">${item.lectureReq.lecName}</option>`
			).join("\n");
			
			lecNo.innerHTML += options;
			
			lecNo.value = initVal ?? "";
		}
	})
	
	axios.get(`${CPATH}/professor/exam/facilityList.do`).then(resp => {
		const facilityList = resp.data;
		
		if (facilityList) {
			const options = facilityList.map(item => 
				`<option value="${item.facilityNo}">${item.facility.facilityName}</option>`
			).join("\n");
			
			facilityNo.innerHTML += options;
						
			facilityNo.value = initFacility ?? "";
		}
	})
	
	const addButton = document.querySelector("button.submitButton.questionCount");
	const questionCountInput = document.querySelector("input.questionCount");
    const questionField = document.querySelector(".questionField");
	
	addButton.addEventListener("click", function(e) {
		e.preventDefault();
		
		questionField.innerHTML = "";
		
		const count = parseInt(questionCountInput.value);
		
		if (isNaN(count) || count <= 0) {
            alert("문제 수를 올바르게 입력해주세요.");
            return;
        }
		
		for (let i = 0; i < count; i++) {
            const div = document.createElement("div");
            div.className = "question-form";
			
            div.style.marginBottom = "20px";
			
			let radioHtml = `<p>문제 ${i + 1} 정답 선택:</p>`;
			
            for (let j = 1; j <= 4; j++) {
                radioHtml += `
                    <label>
                        <input type="radio" class="radioButton" name="answerList[${i}]" value="${j}"> ${j}번
                    </label>&nbsp;&nbsp;
                `;
            }
			
			scoreHtml = `<p>배점 : <input type="text" name="scoreList[${i}]"></p>`;

            div.innerHTML = radioHtml + scoreHtml;
			
            questionField.appendChild(div);
        }
	});
	
	document.querySelector("form").addEventListener("submit", function(e) {
		const questionForms = document.querySelectorAll(".question-form");

		for (let i = 0; i < questionForms.length; i++) {
			const checked = questionForms[i].querySelector(`input[name="answerList[${i}]"]:checked`);
			if (!checked) {
				alert(`${i + 1}번 문제의 정답을 선택해주세요.`);
				e.preventDefault();
				return;
			}
		}
	});
});