<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>회원정보</h1>
		<p>회원 정보 페이지</p>
        
        <form action="/myinfo.do" method="post" enctype="multipart/form-data">
        	<p>${myinfo.nickname} <br></p>
			<p>${authUser.email}</p>
            <div class="content">
                <img src="${myinfo.profileImage}" class="img-circle" id="profile" style="width: 100px; height: 100px;">
                <a id="profileChange" href="#">사진 변경하기</a>
                <input type="file" id="file" name="profile_image" style="display:none;">
            </div>
			<br>
            <div class="form-group">
                <input type="text"
                    class="form-control" id="nickname" name="nickname" value="${myinfo.nickname}">	
            </div>

            <button type="submit" id="btn_submit" class="btn btn-primary btn-block">회원정보 변경</button>
            <a href="/changePwd.do" class="btn btn-primary btn-block" role="button">비밀번호 변경</a>
            <a href="/deleteMyAccount.do" class="btn btn-primary btn-block" role="button">계정 삭제</a>
        </form>
    </div>
    
    <script type="text/javascript">
    	$('#profileChange').click(
    		function() {
				MyFunction();
				return false;
		});
    	
    	function MyFunction() {
			console.log("Hello jquery");
			$('#file').click();
		}
    	
    	$('#file').change(
    		function () {
				if(window.FileReader){ 
					//image 파일만 
					if (!$(this)[0].files[0].type.match(/image\//)) 
						return; 
					var reader = new FileReader(); 
					reader.onload = function(e){ 
						var src = e.target.result; 
						$('#profile').attr("src", src);
					} 
					reader.readAsDataURL($(this)[0].files[0]);
				}
				
			}		
    	);
    </script>
    
</body>
</html>