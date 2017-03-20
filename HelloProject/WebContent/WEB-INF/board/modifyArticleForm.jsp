<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <!-- include libraries(jQuery, bootstrap) --> 
   <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
   <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
   <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
   <!-- include summernote css/js--> 
   <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.1/summernote.css" rel="stylesheet"> 
   <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.1/summernote.js"></script>
   <script src="../js/summernote-ko-KR.js"></script>
    <title>게시글 쓰기</title>
</head>

<body>

    <div class="container">
        <h1>게시글 수정</h1>
        <p>게시글 수정 페이지</p>

        <form action="/board/modify.do" method="post">
        	<div class="form-group" style="margin-bottom: 0px;">
        	<img src="${authUser.profileImage}" class="img-circle" id="profile" style="width: 70px; height: 70px;">
        	${authUser.nickname }
        	</div>
        	
            <div class="form-group">
                <label for="title"></label>
                <input type="text" class="form-control" id="title" name="title" value="${modReq.title }" placeholder="제목을 입력하세요.">
                <p class="help-block" id="title-error"></p>
            </div>
           
            <textarea id="summernote" name="content" >${modReq.content }</textarea>
            
			<script>
				$(document).ready(function() {
					$('#summernote').summernote({
						height: 280,
						minHeight: 280,
						maxHeight: null,
						lang: 'ko-KR',
						placeholder: '내용을 입력하세요.',
						callbacks: {
					    	onImageUpload: function(files, editor, welEditable) {
					    		sendFile(files[0], this);
					        }
						}
					});
					
					function sendFile(file, el) {
					    var data = new FormData();
					    data.append("uploadFile", file);
					    $.ajax({
					        data : data,
					        type : "POST",
					        url : "/board/articleimage.do",
					        cache : false,
					        contentType : false,
					        enctype: 'multipart/form-data',
					        processData : false,
					        success : function(url) {
					            $(el).summernote('editor.insertImage', url);
					        }
					    });
					}					
				});
			</script>
			<input type="hidden" name="no" value="${modReq.articleNumber}">
			<button type="submit" id="btn_submit" class="btn btn-primary btn-block">등록</button>
            <button id="btn_submit" class="btn btn-primary btn-block">취소</button>
        </form>
    </div>

</body>

</html>