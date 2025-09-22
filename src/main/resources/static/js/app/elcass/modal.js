/**
 * 
 */

async function findUserId() {
	const username = document.querySelector("#findIdName").value;
	const birthDate = document.querySelector("#findBirth").value;
	
	console.log({
			username: username,
			birthDate: birthDate
		});
	
	if (!username || !birthDate) {
		alert('이름과 생년월일을 입력해주세요.');
		return;
	}

	try {
		const response = await axios.post("/ajax/user/findUserNo", {
			username: username,
			birthDate: birthDate
		});

		if (response.data.success) {
			console.log("사용자 번호 : ", response.data.userNo);
			document.getElementById('findIdResult').innerHTML =
				'찾은 아이디: <strong>' + response.data.userNo + '</strong>';
			document.getElementById('findIdResult').style.display = 'block';

		} else {
			console.error("조회 실패 : ", response.data.message);
			document.getElementById('findIdResult').innerHTML =
				'일치하는 정보를 찾을 수 없습니다.';
			document.getElementById('findIdResult').className = 'alert alert-warning';
			document.getElementById('findIdResult').style.display = 'block';
		}
	} catch (err) {
		console.error("요청 실패 : ", err);
	}

}

// 비밀번호 찾기 함수
async function findUserPassword() {
	// 폼 요소 가져오기
    const form = document.querySelector('#findPasswordForm');
    const resultDiv = document.querySelector('#findPasswordResult');
    
    const formData = new FormData(form);
    
    console.log("formData ", formData);
	
	const response = await axios.post("/ajax/user/replaceUserPassword", formData);
	
	if (response.data.success) {
		resultDiv.innerHTML =
			'비밀번호 재설정 링크가 이메일로 발송되었습니다.';
		resultDiv.style.display = 'block';
	} else {
		resultDiv.innerHTML =
			'일치하는 정보를 찾을 수 없습니다.';
		resultDiv.className = 'alert alert-warning';
		resultDiv.style.display = 'block';
	}
}

// 모달이 닫힐 때 폼 초기화
document.getElementById('findIdModal').addEventListener('hidden.bs.modal', function() {
	document.getElementById('findIdForm').reset();
	document.getElementById('findIdResult').style.display = 'none';
	document.getElementById('findIdResult').className = 'alert alert-info';
});

document.getElementById('findPasswordModal').addEventListener('hidden.bs.modal', function() {
	document.getElementById('findPasswordForm').reset();
	document.getElementById('findPasswordResult').style.display = 'none';
	document.getElementById('findPasswordResult').className = 'alert alert-info';
});