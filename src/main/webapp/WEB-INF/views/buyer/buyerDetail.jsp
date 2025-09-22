<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<td colspan="2">
			<c:url value="/buyer/buyerUpdate.do" var="updateURL">
				<c:param name="what" value="${buyer.buyerId }" />
			</c:url>
				<a class="btn btn-primary" href="${updateURL }">수정</a>
			</td>
		</tr>
		<tr>
			<th>전경 사진</th>
			<td>
				<img src="data:image/*;base64,${buyer.buyerImgBase64 }" />
			</td>
			
		</tr>
		<tr>
			<th>기본주소</th>
			<td>${buyer.buyerAdd1}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${buyer.buyerAdd2}</td>
		</tr>
		<tr>
			<th>회사전화번호</th>
			<td>${buyer.buyerComtel}</td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td>${buyer.buyerFax}</td>
		</tr>
		<tr>
			<th>메일주소</th>
			<td>${buyer.buyerMail}</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>${buyer.buyerCharger}</td>
		</tr>
		<tr>
			<th>BUYER_TELEXT</th>
			<td>${buyer.buyerTelext}</td>
		</tr>
		<tr>
			<th>거래처이름(*)</th>
			<td>${buyer.buyerName}</td>
		</tr>
		<tr>
			<th>분류코드</th>
			<td>${buyer.lprod.lprodName}</td>
		</tr>
		<tr>
			<th>주거래은행</th>
			<td>${buyer.buyerBank}</td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td>${buyer.buyerBankno}</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>${buyer.buyerBankname}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyerZip}</td>
		</tr>
		<tr>
			<th>거래품목</th>
			<td>
				<table>
					<thead>
						<tr>
							<th>품목명</th>
							<th>구매가</th>
							<th>판매가</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty buyer.prodList }">
								<c:forEach items="${buyer.prodList }" var="pd">
									<tr>
										<td>${pd.prodName}</td>
										<td>${pd.prodCost}</td>
										<td>${pd.prodPrice}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="3">거래품목 없음</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>