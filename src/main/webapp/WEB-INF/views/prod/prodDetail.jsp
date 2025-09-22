<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>상품상세</title>
<script>
	// 더미 주석
</script>
</head>
<body>
	<h4>상품 상세 조회</h4>
	<table class="table table-bordered">
		<tr>
			<td colspan="2"><c:url value="/prod/prodUpdate.do"
					var="updateURL">
					<c:param name="what" value="${prodDetail.prodId }" />
				</c:url> <a class="btn btn-primary" href="${updateURL }">수정</a></td>
		</tr>
		<tr>
			<th>상품코드</th>
			<td>${prodDetail.prodId}</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prodDetail.prodName}</td>
		</tr>
		<tr>
			<th>상품분류</th>
			<td>${prodDetail.lprodGu}</td>
		</tr>
		<tr>
			<th>제조사</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>제조사명</th>
							<th>소재지</th>
							<th>전화번호</th>
							<th>담당자</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="buyer" value="${prodDetail.buyer }"></c:set>
						<c:url value="/buyer/buyerDetail.do" var="buyerRef">
							<c:param name="what" value="${buyer.buyerId }" />
						</c:url>
						<tr>
							<td><a href="${buyerRef }">${buyer.buyerName }</a></td>
							<td>${buyer.buyerAdd1 }</td>
							<td>${buyer.buyerComtel }</td>
							<td>${buyer.buyerCharger }</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>매입단가</th>
			<td>${prodDetail.prodCost}</td>
		</tr>
		<tr>
			<th>매출단가</th>
			<td>${prodDetail.prodPrice}</td>
		</tr>
		<tr>
			<th>할인판매단가</th>
			<td>${prodDetail.prodSale}</td>
		</tr>
		<tr>
			<th>대충설명</th>
			<td>${prodDetail.prodOutline}</td>
		</tr>
		<tr>
			<th>자세한설명</th>
			<td>${prodDetail.prodDetail}</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
			<c:if test="${not empty prodDetail.prodImg }">
				<img src="/images/${prodDetail.prodImg}">
			</c:if>
			</td>
		</tr>
		<tr>
			<th>전재고량</th>
			<td>${prodDetail.prodTotalstock}</td>
		</tr>
		<tr>
			<th>입고일자</th>
			<td>${prodDetail.prodInsdate}</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prodDetail.prodProperstock}</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prodDetail.prodSize}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prodDetail.prodColor}</td>
		</tr>
		<tr>
			<th>배달주의사항</th>
			<td>${prodDetail.prodDelivery}</td>
		</tr>
		<tr>
			<th>판매단위</th>
			<td>${prodDetail.prodUnit}</td>
		</tr>
		<tr>
			<th>포장수량</th>
			<td>${prodDetail.prodQtyin}</td>
		</tr>
		<tr>
			<th>판매단위(수량)</th>
			<td>${prodDetail.prodQtysale}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prodDetail.prodMileage}</td>
		</tr>
	</table>
</body>
</html>