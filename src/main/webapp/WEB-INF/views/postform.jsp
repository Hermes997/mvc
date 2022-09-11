<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ryu.assign.mvc.model.UploadList" %>
<%@ page import="ryu.assign.mvc.model.FreePost" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form action="/free" method="post">
		title : <input type="text" name="title" value="title1" /><br>
	    contents : <input type="text" name="contents" value="contents1" /><br>
	    publisher : <input type="text" name="publisher" value="ryu1" /><br>
	    <input type= "submit" value="게시물 작성" />
	</form>
</body>
</html>