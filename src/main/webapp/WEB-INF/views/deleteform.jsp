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
					  url:"/free/" + $('#id').val(),
					  type:"DELETE",
					  success: function(data){
					    console.log("전송성공");
					  }
					});
		    });
		});
	</script>
</head>
<body>
		id : <input type="text" id= 'id' name=id value="1" /><br>
		<button id="btn">게시물 삭제</button>
</body>
</html>