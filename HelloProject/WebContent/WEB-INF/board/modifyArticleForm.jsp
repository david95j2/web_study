<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
   <title></title>
</head>

<body>
    	<%@ include file="/WEB-INF/view/header.jsp" %>
		<section>    	
        <h3>게시글 수정</h3>
		
		<!-- modify article form -->
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
			
			<!-- summernote -->			           
            <textarea id="summernote" name="content" >${modReq.content }</textarea>
            
			<script>
				<!-- summernote jquery ajax-->
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
			
			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"><i class="fa fa-exclamation-circle" style="font-size:20px;color:red"></i> 알림</h4>
						</div>
						<div class="modal-body">
							<p>정말 취소 하시겠습니까?</p>
						</div>
						<div class="modal-footer">
							<a href="read.do?no=${modReq.articleNumber }" class="btn btn-default" role="button" 
								>확인</a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>

			<button type="submit" id="btn_submit" class="btn btn-primary btn-block">등록</button>
            <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#myModal">취소</button>
        </form>
        </section>
        <br>
    </div>

</body>

</html>