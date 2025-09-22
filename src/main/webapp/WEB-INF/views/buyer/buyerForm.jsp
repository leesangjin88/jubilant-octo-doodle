<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="/js/app/buyer/buyerForm.js"></script>
<script src="${pageContext.request.contextPath}/js/axios.min.js"></script>
</head>
<body data-context-path="${pageContext.request.contextPath}">
   <div class="card">
      <div class="card-header">
         <h5>Form controls</h5>
      </div>
      <div class="card-body">
         <div class="row">
            <form:form modelAttribute="buyer" method="post" enctype="multipart/form-data">
            	<div class="form-group">
            		<label class="form-label" for="buyerImage">제조사 전경</label>
            		<input type="file" name="buyerImage" id="buyerImage" accept="image/*" />
            		<form:errors path="buyerImage" cssClass="text-danger"/>
            	</div>
               <div class="form-group">
                    <form:label path="buyerName" cssClass="form-label">거래처이름(*)</form:label>
                  <form:input path="buyerName" value="${buyerName}" type="text" cssClass="form-control" placeholder="거래처이름(*)"/>
                  <form:errors path="buyerName" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                    <form:label path="lprodGu" cssClass="form-label">분류코드(*)</form:label>                  
                  <select name="lprodGu" id="lprodGu" class="form-select" data-init-val="${buyer.lprodGu }">
                     <option value="">분류 선택</option>
                  </select>
                  <form:errors path="lprodGu" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                    <form:label path="buyerBank" cssClass="form-label">주거래은행</form:label>
                  <form:input path="buyerBank" value="${buyerBank}" type="text" cssClass="form-control" placeholder="주거래은행"/>
                  <form:errors path="buyerBank" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                    <form:label path="buyerBankno" cssClass="form-label">계좌번호</form:label>
                  <form:input path="buyerBankno" value="${buyerBankno}" type="text" cssClass="form-control" placeholder="계좌번호"/>
                  <form:errors path="buyerBankno" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                  <form:label path="buyerBankname" cssClass="form-label">계좌주</form:label>
                  <form:input path="buyerBankname" value="${buyerBankname}" type="text" cssClass="form-control" placeholder="계좌주"/>
                  <form:errors path="buyerBankname" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                  <form:label path="buyerZip" cssClass="form-label">우편번호</form:label>
                  <form:input path="buyerZip" value="${buyerZip}" type="text" cssClass="form-control" placeholder="우편번호"/>
                  <form:errors path="buyerZip" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                    <form:label path="buyerAdd1" cssClass="form-label">기본주소</form:label>
                  <form:input path="buyerAdd1" value="${buyerAdd1}" type="text" cssClass="form-control" placeholder="기본주소"/>
                  <form:errors path="buyerAdd1" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                  <form:label path="buyerAdd2" cssClass="form-label">상세주소</form:label>
                  <form:input path="buyerAdd2" value="${buyerAdd2}" type="text" cssClass="form-control" placeholder="상세주소"/>
                  <form:errors path="buyerAdd2" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                  <form:label path="buyerComtel" cssClass="form-label">회사전화번호</form:label>
                  <form:input path="buyerComtel" value="${buyerComtel}" type="text" cssClass="form-control" placeholder="회사전화번호"/>
                  <form:errors path="buyerComtel" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                  <form:label path="buyerFax" cssClass="form-label">팩스번호</form:label>
                  <form:input path="buyerFax" value="${buyerFax}" type="text" cssClass="form-control" placeholder="팩스번호"/>
                  <form:errors path="buyerFax" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                  <form:label path="buyerMail" cssClass="form-label">메일주소</form:label>
                  <form:input path="buyerMail" value="${buyerMail}" type="text" cssClass="form-control" placeholder="메일주소"/>
                  <form:errors path="buyerMail" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                  <form:label path="buyerCharger" cssClass="form-label">담당자</form:label>
                  <form:input path="buyerCharger" value="${buyerCharger}" type="text" cssClass="form-control" placeholder="담당자"/>
                  <form:errors path="buyerCharger" cssClass="text-danger"/>
               </div>
               <div class="form-group">
                  <form:label path="buyerTelext" cssClass="form-label">내선번호</form:label>
                  <form:input path="buyerTelext" value="${buyerTelext}" type="text" cssClass="form-control" placeholder="내선번호"/>
                  <form:errors path="buyerTelext" cssClass="text-danger"/>
               </div>
               
               <div>
                  <button type="submit" class="btn btn-primary mb-4">Submit</button>
                  <button type="reset" class="btn btn-danger mb-4">Reset</button>
               </div>

               <div class="form-group">
                  <label class="form-label" for="exampleFormControlSelect1">Example
                     select</label> <select class="form-select" id="exampleFormControlSelect1">
                     <option>1</option>
                     <option>2</option>
                     <option>3</option>
                     <option>4</option>
                     <option>5</option>
                  </select>
               </div>
               <div class="form-group">
                  <label class="form-label" for="exampleFormControlTextarea1">Example
                     textarea</label>
                  <textarea class="form-control" id="exampleFormControlTextarea1"
                     rows="3"></textarea>
               </div>
            </form:form>
         </div>
      </div>
   </div>
</body>
</html>