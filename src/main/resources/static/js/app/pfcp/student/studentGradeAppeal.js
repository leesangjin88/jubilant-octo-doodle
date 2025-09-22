/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
	const CPATH = document.body.dataset.contextPath;
	const subjectCode = document.querySelector("#subjectCode");
	const initVal = subjectCode.dataset.initVal;
	
	axios.get(`${CPATH}/student/grade/subjectList.do`)
		 .then(resp => {
			const subjectList = resp.data;
			
			console.log(subjectList);
			
			if (subjectList) {
				const options = subjectList.map(({subject}) => 
					`<option value="${subject.subjectCode}">${subject.subjectName}</option>`
				).join("\n");
				
				subjectCode.innerHTML += options;
				
				subjectCode.value = initVal ?? "";
			}
		 })
})