/**
 * 
 */

function moveToReactWithAuth() {
	fetch('/api/set-auth-cookie', {
		method: 'POST',
		credentials: 'include',
		headers: {
			'Content-Type': 'application/json',
			// CSRF 토큰이 필요한 경우
			'X-Requested-With': 'XMLHttpRequest'
		}
	})
		.then(response => response.json())
		.then(data => {
			if (data.success) {
				window.location.href = 'http://localhost:6060/';
			} else {
				alert('인증 처리 중 오류가 발생했습니다: ' + data.message);
			}
		})
		.catch(error => {
			console.error('Error:', error);
			alert('서버 오류가 발생했습니다.');
		});
}