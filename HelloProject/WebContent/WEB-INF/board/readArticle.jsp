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
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
    <title>게시글 읽기</title>
<style type="text/css">
	.summery {
		padding: 20px;
	}
	.summery > i {
		float: right;
		padding-right : 20px;
	}
	
	.button {
		margin-bottom: 10px;
	}
	
	.button > #writebutton {
		float: right;
	}
	
	.title {
		padding-bottom: 0px;
	}
	
	.bsummery {
		padding-bottom: 20px;
	}
	
	.bsummery > #moddate {
		float: right;
		font-size: 11px;
		
	}
</style>
</head>

<body>

    <div class="container">
        <h1>게시글 읽기</h1>
        <p>게시글 페이지</p>
        
        <div class="button">
			<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}"/>
			<a href="/board/list.do?pageNo=${pageNo }"  class="btn btn-primary" role="button">목록보기</a>
			<c:if test="${authUser.id == articleData.article.writer.id}">
			<a href="/board/modify.do?no=${articleData.article.number }" class="btn btn-primary" role="button">글 수정</a>
			<a href="/board/delete.do?no=${articleData.article.number }" class="btn btn-primary" role="button">글 삭제</a>
			</c:if>
			<a href="/board/write.do" class="btn btn-primary" id="writebutton" role="button">새 글쓰기</a>
		</div>
		

		<div class="panel panel-default">
			<div class="panel-heading summery">
				<img src="${articleData.article.writer.profileImage}" class="img-circle" id="profile" style="width: 70px; height: 70px;">
				${articleData.article.writer.nickname}
			
				<i class="fa fa-heart" style="font-size:15px"> ${articleData.article.likeCnt }</i>
				<i class="fa fa-eye" style="font-size:15px">  ${articleData.article.readCnt }</i>
				<i class="fa fa-clock-o" style="font-size:15px"> ${articleData.article.transferRegDate }전</i>
			</div>
			
			<div class="panel-body title">
				<p>${articleData.article.number }번째 글 
				<h3><strong>${articleData.article.title }</strong></h3>
			</div>
			<hr>
			<div class="panel-body">${articleData.articleContent.content }</div>
			<div class="panel-heading bsummery">
				<c:if test="${articleData.article.modDate != null }">
					<p id="moddate">${articleData.article.transferModDate } 에 마지막 수정됨</p>
				</c:if>
			</div>
		</div>

	</div>

</body>

</html>