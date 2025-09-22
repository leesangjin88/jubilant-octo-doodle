document.addEventListener("DOMContentLoaded", () => {
	const lprodGuSel = document.getElementById("lprodGu");
	const buyerIdSel = document.getElementById("buyerId");

	const guInitVal = lprodGuSel.dataset.initVal;
	const idInitVal = buyerIdSel.dataset.initVal;
	const CPATH = document.body.dataset.contextPath;

	axios.get(`${CPATH}/ajax/lprod`)
		.then(resp => {
			const { lprodList } = resp.data;
			if (lprodList) {
				// map()을 통해 옵션을 생성하고 join()을 map() 이후에 실행
				const options = lprodList.map(({ lprodGu, lprodName }) =>
					`<option value="${lprodGu}">${lprodName}</option>`
				).join("\n");

				lprodGuSel.innerHTML += options;

				//            if(initVal)
				lprodGuSel.value = guInitVal ?? "";

			}
		})

	axios.get(`${CPATH}/ajax/buyer`)
		.then(resp => {
			const { buyerList } = resp.data;
			// console.log(buyerList);

			if (buyerList) {
				const options = buyerList.map(({ buyerId, buyerName, lprodGu }) => 
					`<option value="${buyerId}" class="${lprodGu}">${buyerName}</option>`
				).join("\n")

				buyerIdSel.innerHTML += options;

				buyerIdSel.value = idInitVal ?? "";
			}
		})
	/*lprodGuSel.addEventListener("change", (e) => {
		const selGu = e.target.value;   // P101

		buyerIdSel.querySelectorAll("option[class]")
			.forEach(op => {
				if (op.classList.contains(selGu)) {
					op.style.display = "block";
				} else {
					op.style.display = "none";
				}
			})
	})*/
});








