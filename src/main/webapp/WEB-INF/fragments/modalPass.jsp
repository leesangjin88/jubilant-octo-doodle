<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal fade" id="findPasswordModal" tabindex="-1"
	aria-labelledby="findPasswordModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="findPasswordModalLabel">비밀번호 찾기</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form id="findPasswordForm">
					<div class="mb-3">
						<label for="findPasswordId" class="form-label">아이디</label> <input
							type="text" class="form-control" id="findUserNo" name="userNo"
							required>
					</div>
					<div class="mb-3">
						<label for="findPasswordEmail" class="form-label">이메일</label> <input
							type="email" class="form-control" id="findUserEmail" name="userEmail"
							required>
					</div>
					<div id="findPasswordResult" class="alert alert-info"
						style="display: none;">
						<!-- 비밀번호 재설정 결과가 여기에 표시됩니다 -->
					</div>
					<div class="modal-footer d-flex justify-content-end">
						<button type="button" class="btn btn-primary"
							onclick="findUserPassword()" style="width: auto;">비밀번호
							찾기</button>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal" style="width: auto;">닫기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>