/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
    const departmentNoSel = document.querySelector("#departmentNo");
    const initVal = departmentNoSel.dataset.initVal;
	
	const bankCd = document.querySelector("#bankCd");
	const initBank = bankCd.dataset.initBank;
    
    const CPATH = document.body.dataset.contextPath;
    
    axios.get(`${CPATH}/staff/professorManagement/departmentList.do`)
         .then(resp => {
            const departmentList = resp.data;
            console.log("체킁 :", departmentList);
            
            if (departmentList) {
				const options = departmentList.filter(item => item.departmentNo != initVal)
											  .map(({ department }) => `<option value="${department.departmentNo}">${department.departmentName}</option>`)
											  .join("\n");
                
                departmentNoSel.innerHTML += options;

                departmentNoSel.value = initVal ?? "";
            }
	})
	
	axios.get(`${CPATH}/staff/professorManagement/bankCodeList.do`)
		 .then(resp => {
			const bankCodeList = resp.data;
			
			console.log("체킁 :", bankCodeList);
			console.log(initBank);
			
			if (bankCodeList) {
				const cOptions = bankCodeList.filter(item => item.typeCode != initBank)
											 .map(item => `<option value="${item.typeCode}">${item.typeName}</option>`)
											 .join("\n");
				
				bankCd.innerHTML += cOptions;
				
				bankCd.value = initBank ?? "";
			}
	})
});