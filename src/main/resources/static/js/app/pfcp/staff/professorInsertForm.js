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
            
            
            if (departmentList) {
                const options = departmentList.map(item =>
                    `<option value="${item.department.departmentNo}">${item.department.departmentName}</option>`
                ).join("\n");
                
                departmentNoSel.innerHTML += options;

                departmentNoSel.value = initVal ?? "";
            }
	})
	
	axios.get(`${CPATH}/staff/professorManagement/bankCodeList.do`)
		 .then(resp => {
			const bankCodeList = resp.data;
			
			console.log("체킁 :", bankCodeList);
			
			if (bankCodeList) {
				const cOptions = bankCodeList.map(item => 
					`<option value="${item.typeCode}">${item.typeName}</option>`
				).join("\n");
				
				bankCd.innerHTML += cOptions;
				
				bankCd.value = initBank ?? "";
			}
	})
});
