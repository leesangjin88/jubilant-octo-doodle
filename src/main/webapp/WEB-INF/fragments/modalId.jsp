<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="modal fade" id="findIdModal" tabindex="-1"
	aria-labelledby="findIdModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="findIdModalLabel">
					<i class="fas fa-user-search me-2"></i>아이디 찾기
				</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close">
					<i class="fas fa-times"></i>
				</button>
			</div>
			<div class="modal-body">
				<div class="d-flex align-items-center">
					<i class="far fa-check-circle me-2"></i>
					<p class="text-muted mb-0">아이디 : 학번(교번) / 비밀번호 : 비밀번호 찾기</p>
				</div>
				<div class="d-flex align-items-center">
					<i class="far fa-check-circle me-2"></i>
					<p class="text-muted mb-0">비밀번호를 모르실 때는 비밀번호 찾기를 이용하시기 바랍니다.</p>
				</div>
				<br />
				<form id="findIdForm">
					<div class="mb-3">
						<label for="findIdName" class="form-label"> <i
							class="fas fa-user me-1"></i>이름
						</label> <input type="text" class="form-control" id="findIdName"
							name="name" placeholder="실명을 입력해주세요" required>
					</div>
					<div class="mb-3">
						<label for="findBirth" class="form-label"> <i
							class="fas fa-calendar-alt me-1"></i>생년월일
						</label> <input type="text" class="form-control" id="findBirth"
							name="birth" placeholder="YYMMDD" required>
					</div>
					<div id="findIdResult" class="alert alert-success"
						style="display: none;">
						<i class="fas fa-check-circle me-2"></i> <strong>찾은 아이디:</strong>
						<div id="foundIdText" class="mt-2"></div>
					</div>
			</div>
			<div class="modal-footer d-flex justify-content-end">
				<button type="button" class="btn btn-primary" onclick="findUserId()"
					style="width: auto;">아이디 찾기</button>
				<button type="button" class="btn btn-secondary me-2"
					data-bs-dismiss="modal" style="width: auto;">닫기</button>
			</div>
			</form>
		</div>
	</div>
</div>