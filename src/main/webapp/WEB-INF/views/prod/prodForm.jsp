<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="<c:url value='/js/app/prod/prodForm.js'/>">
	// 스크립트 코드
</script>
<script src="${pageContext.request.contextPath}/js/axios.min.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
	<div class="card">
		<div class="card-header">
			<h5>제품 설명 입력</h5>
		</div>
		<div class="card-body">
			<div class="row">
<%-- 			<form:form modelAttribute="prod" method="post">
				<form:input path="prodName" cssClass="form-control"/>
				<form:errors path="prodName" />
			</form:form> --%>

				<form method="post" enctype="multipart/form-data">
					<c:if test="${action eq 'update' }">
						<div class="form-group">
							<label class="form-label" for="prodId">상품코드</label><input
								type="text" id="prodId" name="prodId" class="form-control"
								placeholder="상품코드" value="${prod.prodId}" readonly> <span
								class="text-danger">${errors.prodId }</span>
						</div>
					</c:if>
					<div class="form-group">
						<label class="form-label" for="prodName">상품명</label><input
							type="text" id="prodName" name="prodName" class="form-control"
							placeholder="상품명" value="${prod.prodName}"> <span
							class="text-danger">${errors.prodName }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="lprodGu">분류코드</label>
						<%-- input
							type="text" id="lprodGu" name="lprodGu" class="form-control"
							placeholder="분류코드" value="${prod.lprodGu }"> --%>
						<select name="lprodGu" id="lprodGu" class="form-select"
							data-init-val="${prod.lprodGu }">

							<option value="">분류선택</option>

						</select> <span class="text-danger">${errors.lprodGu }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="buyerId">거래처코드</label>
						<%-- <input
							type="text" id="buyerId" name="buyerId" class="form-control"
							placeholder="거래처코드" value="${prod.buyerId }"> --%>
						<select name="buyerId" id="buyerId" class="form-select"
							data-init-val="${prod.buyerId }">

							<option value="">분류선택</option>

						</select> <span class="text-danger">${errors.buyerId }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodCost">매입단가</label><input
							type="text" id="prodCost" name="prodCost" class="form-control"
							placeholder="매입단가" value="${prod.prodCost }"> <span
							class="text-danger">${errors.prodCost }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodPrice">매출단가</label><input
							type="text" id="prodPrice" name="prodPrice" class="form-control"
							placeholder="매출단가" value="${prod.prodPrice }"> <span
							class="text-danger">${errors.prodPrice }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodSale">할인판매단가</label><input
							type="text" id="prodSale" name="prodSale" class="form-control"
							placeholder="할인판매단가" value="${prod.prodSale }"> <span
							class="text-danger">${errors.prodSale }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodOutline">대충설명</label><input
							type="text" id="prodOutline" name="prodOutline"
							class="form-control" placeholder="대충설명"
							value="${prod.prodOutline }"> <span class="text-danger">${errors.prodOutline }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodDetail">자세한설명</label><input
							type="text" id="prodDetail" name="prodDetail"
							class="form-control" placeholder="자세한설명"
							value="${prod.prodDetail }"> <span class="text-danger">${errors.prodDetail }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodImage">상품이미지</label><input
							type="text" id="prodImg" name="prodImg" class="form-control"
							placeholder="상품이미지" value="${prod.prodImg }" readonly>
							<input
							type="file" id="prodImage" name="prodImage" class="form-control"
						 	value="${prod.prodImage }">
							 <span class="text-danger">${errors.prodImage }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodTotalstock">전재고량</label><input
							type="text" id="prodTotalstock" name="prodTotalstock"
							class="form-control" placeholder="전재고량"
							value="${prod.prodTotalstock }"> <span
							class="text-danger">${errors.prodTotalstock }</span>
					</div>
					<%-- <div class="form-group">
						<label class="form-label" for="prodInsdate">전재고량</label><input
							type="text" id="prodInsdate" name="prodInsdate"
							class="form-control" placeholder="전재고량"
							value="${prod.prodInsdate }"> <span
							class="text-danger">${errors.prodInsdate }</span>
					</div> --%>
					<div class="form-group">
						<label class="form-label" for="prodProperstock">적정재고</label><input
							type="text" id="prodProperstock" name="prodProperstock"
							class="form-control" placeholder="적정재고"
							value="${prod.prodProperstock }"> <span
							class="text-danger">${errors.prodProperstock }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodSize">크기</label><input
							type="text" id="prodSize" name="prodSize" class="form-control"
							placeholder="크기" value="${prod.prodSize }"> <span
							class="text-danger">${errors.prodSize }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodColor">색상</label><input
							type="text" id="prodColor" name="prodColor" class="form-control"
							placeholder="색상" value="${prod.prodColor }"> <span
							class="text-danger">${errors.prodColor }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodDelivery">배달주의사항</label><input
							type="text" id="prodDelivery" name="prodDelivery"
							class="form-control" placeholder="배달주의사항"
							value="${prod.prodDelivery }"> <span class="text-danger">${errors.prodDelivery }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodUnit">판매단위</label><input
							type="text" id="prodUnit" name="prodUnit" class="form-control"
							placeholder="판매단위" value="${prod.prodUnit }"> <span
							class="text-danger">${errors.prodUnit }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodQtyin">포장수량</label><input
							type="text" id="prodQtyin" name="prodQtyin" class="form-control"
							placeholder="포장수량" value="${prod.prodQtyin }"> <span
							class="text-danger">${errors.prodQtyin }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodQtysale">판매단위(수량)</label><input
							type="text" id="prodQtysale" name="prodQtysale"
							class="form-control" placeholder="판매단위(수량)"
							value="${prod.prodQtysale }"> <span class="text-danger">${errors.prodQtysale }</span>
					</div>
					<div class="form-group">
						<label class="form-label" for="prodMileage">마일리지</label><input
							type="text" id="prodMileage" name="prodMileage"
							class="form-control" placeholder="마일리지"
							value="${prod.prodMileage }"> <span class="text-danger">${errors.prodMileage }</span>
					</div>
					<div>
						<button type="submit" class="btn btn-primary mb-4">제출</button>
						<button type="reset" class="btn btn-secondary mb-4">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>