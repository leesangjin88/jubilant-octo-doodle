/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {
	const CPATH = document.body.dataset.contextPath;
	
	const newMajorApplyDesired = document.querySelector("#requestThing");
	
	const initVal = newMajorApplyDesired.dataset.initVal;
	
	axios.get(`${CPATH}/staff/professorManagement/departmentList.do`).then(resp => {
		const departmentList = resp.data;
		
		console.log("체킁 :", departmentList);
		
		if (departmentList) {
           const options = departmentList.map(item =>
               `<option value="${item.department.departmentNo}">${item.department.departmentName}</option>`
           ).join("\n");
           
           newMajorApplyDesired.innerHTML += options;

           newMajorApplyDesired.value = initVal ?? "";
       }
	})
	
	
})