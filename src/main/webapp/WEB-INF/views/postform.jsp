<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>jQuery Event</title>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
		$(function() {
			$("#btn").on("click", function() {
				
				$.ajax({
					  url:"/free",
					  type:"POST",
					  data:JSON.stringify({
						  title: $('#title').val(),
						 contents: $('#contents').val(),
						 publisher: $('#publisher').val()
					  }),
					  contentType:"application/json; charset=utf-8",
					  dataType:"json",
					  success: function(data){
					    console.log("전송성공");
					  }
					});
		    });
		});
	</script>
</head>

<body>
		title : <input type="text" id="title" name="title" value="title1" /><br>
	    contents : <input type="text" id="contents" name="contents" value="contents1" /><br>
	    publisher : <input type="text" id="publisher" name="publisher" value="cnl1MQ==" /><br>
		<button id="btn">게시물 등록</button>
	
</body>


</html>