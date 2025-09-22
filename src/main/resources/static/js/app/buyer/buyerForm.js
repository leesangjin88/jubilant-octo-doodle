/**
 * 
 */

document.addEventListener("DOMContentLoaded", ()=>{
	// alert(axios);
	const CPATH = document.body.dataset.contextPath;
	const lprodGu = document.querySelector("#lprodGu");
	
	const initVal = lprodGu.dataset.initVal;
	
	axios.get(`${CPATH}/ajax/lprod`).then(resp=>{
		const {lprodList} = resp.data;
		console.log(lprodList);
		if(lprodList){
			const options = lprodList.map(({lprodGu, lprodName})=>`<option value="${lprodGu}">${lprodName}</option>`
			).join("\n");
			lprodGu.innerHTML += options;
			// if(initVal)
				
			lprodGu.value = initVal ?? "";
		}
		
	});
	
})