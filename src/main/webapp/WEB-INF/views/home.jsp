<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ryu.assign.mvc.model.UploadList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<button id='post' type="button" onclick="location.href='/postform'"> 게시물 작성</button><br><br>
<button id='get' type="button" onclick="location.href='/free'"> 게시물 출력</button>
<button id='put' type="button" onclick="location.href='/changeform'"> 게시물 수정</button>
<button id='delete' type="button" onclick="location.href='/changeform'"> 게시물 삭제</button>
</body>
</html>