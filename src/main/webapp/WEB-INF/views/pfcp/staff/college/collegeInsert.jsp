<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %> 
<%--  
	파일명 : collegeInsert.jsp  
	프로그램명 : 단과 대학 목록 등록
	설명 : 
	작성자 : 김규민
	작성일 : 2025. 07. 02
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>단과 대학 등록</title>
<link rel="stylesheet" href="/dist/assets/css/bodyFormat.css">
</head>
<body>
	<h1>단과 대학 등록</h1>
    <p>새로운 단과 대학 정보를 입력하세요.</p>
    
    <form action="/staff/college/insertCollege.do" method="post">
        
        <div>
            <label for="collegeName">단과 대학 이름 :</label>
            <input type="text" id="collegeName" name="collegeName" required>
        </div>
        
        <div>
            <button type="submit">등록</button>
            <button type="button" onclick="location.href='/staff/college/collegeList.do'">목록으로</button>
        </div>
    </form>
    
</body>
</html>