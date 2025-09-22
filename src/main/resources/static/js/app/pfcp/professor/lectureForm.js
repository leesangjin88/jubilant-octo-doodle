/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	const CPATH = document.body.dataset.contextPath;


	const lecCategorySelect = document.getElementById("lecCategory");
	if (lecCategorySelect) {
		axios.get(`${CPATH}/professor/lecture/categories.do`)
			.then(resp => {
				const categories = resp.data;
				if (categories) {
					const options = categories.map(cat => `<option value="${cat}">${cat}</option>`).join("\n");
					lecCategorySelect.innerHTML += options;
				}
			})
			.catch(err => {
				console.error("강의 분류 목록 로드 실패:", err);
			});
	}


	const subjectCodeSelect = document.getElementById("subjectCode");
	if (subjectCodeSelect) {
		axios.get(`${CPATH}/professor/lecture/subjectNames.do`)
			.then(resp => {
				const subjects = resp.data;
				if (subjects) {
					const options = subjects.map(subject =>
						`<option value="${subject.SUBJECT_CODE}">${subject.SUBJECT_NAME}</option>`
					).join("\n");
					subjectCodeSelect.innerHTML += options;
				}
			})
			.catch(err => {
				console.error("강의 과목 목록 로드 실패:", err);
			});
	}


	const preClassrmSelect = document.getElementById("preClassrm");
	if (preClassrmSelect) {
		axios.get(`${CPATH}/professor/lecture/classroomNames.do`)
			.then(resp => {
				const classroomNames = resp.data;
				if (classroomNames) {
					const options = classroomNames.map(name => `<option value="${name}">${name}</option>`).join("\n");
					preClassrmSelect.innerHTML += options;
				}
			})
			.catch(err => {
				console.error("희망 강의실 목록 로드 실패:", err);
			});
	}
});