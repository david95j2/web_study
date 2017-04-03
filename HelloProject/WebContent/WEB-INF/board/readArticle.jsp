<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>

<head>
   <meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- include libraries(jQuery, bootstrap) --> 
   <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
   <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
   <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  	
    <title></title>
    
	<style type="text/css">
		.summary {
			padding: 20px;
		}
		.summary > i {
			float: right;
			padding-right : 20px;
		}
		
		.writebutton {
			margin-bottom: 10px;
			text-align: right;
		}
		
		.title {
			padding-bottom: 0px;
		}
		
		.bsummary {
			height: 35px;
		}
		
		.bsummary ul {
    		padding-left: 0;
		}
		
		.bsummary ul li {
			font-size: 12px;
			display: inline;
		}
		
		.bsummary a {
			color:black;
			float: right;
			margin-right: 15px;
		}
		
		.likezone ul {
			padding-left: 0;
		}
		
		.likezone ul li {
			font-size: 12px;
			display: inline;
		}
		
		.row ul {
			padding-left: 0;
		}
		
		.row ul li {
			font-size: 12px;
			display: inline;
		}
		
	</style>
</head>

<body>

    	<%@ include file="/WEB-INF/view/header.jsp" %>
    	
    	<section>
		<h3>게시글 읽기</h3>
		
		<div class="writebutton">
			<a href="/board/write.do" class="btn btn-primary" role="button" id="writebutton"><i class="fa fa-pencil"></i> 새 글쓰기</a>
		</div>
		
		<!-- head summary -->		
		<div class="panel panel-default">
			<div class="panel-heading summary">
				<a href="/user.do?user=${articleData.article.writer.id}"><img src="${articleData.article.writer.profileImage}" class="img-circle" id="profile" style="width: 70px; height: 70px;"></a>
				<a href="/user.do?user=${articleData.article.writer.id}" style="color: black;">${articleData.article.writer.nickname}</a> <span id="article_userId" style="display: none;">${articleData.article.writer.id}</span>
				
				<i class="fa fa-eye" style="font-size:15px">  ${articleData.article.readCnt }</i>
				<i class="fa fa-clock-o" style="font-size:15px"> ${articleData.article.transferRegDate }전</i>
			</div>
			
		<!-- title -->	
			<div class="panel-body title">
				<p><strong id="article_no">${articleData.article.number }</strong>번째 글 </p>
				<h3><strong>${articleData.article.title }</strong></h3>
			</div>
			<hr>
		<!-- content -->			
			<div class="panel-body content">${articleData.articleContent.content }
			
			<c:if test="${articleData.article.modDate != null }">
				<p id="moddate"><i class="fa fa-history" style="font-size: 11px;padding-bottom:0px; margin-top: 50px;"> ${articleData.article.transferModDate } 에 마지막 수정됨</i></p>
			</c:if>
			</div>
			
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
							<p>정말 삭제 하시겠습니까?</p>
						</div>
						<div class="modal-footer">
							<a href="/board/delete.do?no=${articleData.article.number }" class="btn btn-default" role="button" 
								>확인</a>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>
						</div>
					</div>
				</div>
			</div>
			
			<!-- bottom summary -->
			<div class="panel-heading bsummary">
			<ul>
				<li><c:set var="page"
					value="${empty param.page ? '1' : param.page}" />
					<c:set var="sort"
					value="${empty param.sort ? 'article_no' : param.sort}" />
				<a href="/board/list.do?page=${page }&sort=${sort }">목록보기</a> </li>
				<c:if test="${authUser.id == articleData.article.writer.id}">
					<li><a href="#" data-toggle="modal" data-target="#myModal" ><i class="fa fa-trash-o"> 삭제</i></a></li>
					<li><a href="/board/modify.do?no=${articleData.article.number }"><i class="fa fa-edit"> 수정</i></a></li>
				</c:if>
			</ul>
			</div>
		</div>

		<div class="likezone">
			<!-- like zone -->
			<c:if test="${articleData.article.getArticleLikeSize() == 0}">
			<ul id="likelist">
				<li id='countzero' class="likelist"><p>가장 먼저 좋아요를 눌러주세요.</p></li>
			</ul>
			</c:if>
			
			<c:if test="${articleData.article.getArticleLikeSize() >= 10}">
			<ul id="likelist">
				<li class="likelist"><i class="fa fa-heart" style="font-size:12px;">
					<b> 좋아요 <strong id="likeupdown">${articleData.article.getArticleLikeSize() }</strong>개</b></i>
				</li>
				
				<c:forEach var="like" items="${articleData.article.articleLikeList }">
					<li id="${like.number}" class="likelist" style="display: none;"><b>
					<span id="like_nickname">${like.nickname}</span>
					<span id="${like.memberId}" style="display: none;">${like.number}</span>
					</b></li>
				</c:forEach>
				
			</ul>
			</c:if>
			
			<c:if test="${articleData.article.getArticleLikeSize() < 10 && articleData.article.getArticleLikeSize() > 0}">
			<!-- <i class="fa fa-heart" style="font-size:12px;"></i> -->
			<ul id="likelist">
				<c:forEach var="like" items="${articleData.article.articleLikeList }">
					<li id="${like.number}" class="likelist"><b>
					<span id="like_nickname"><a href="/user.do?user=${like.memberId }" style="color: black;">${like.nickname}</a></span>
					<span id="${like.memberId}" style="display: none;">${like.number}</span>
					</b></li>
				</c:forEach>
				<li id="like_end" class="likelist">님이 좋아합니다.</li>
			</ul>
			</c:if>
			
		</div>
			
		<!-- reply zone -->	
		<p>			
		<i class="fa fa-comment-o" style="font-size:12px;"> 댓글<span id="replyupdown"> ${articleData.article.getArticleReplySize() }</span>개</i>	
		</p>
		
		
		<div class="row">
			<div class="col-sm-12">
						<!-- 	
				<ul id="replylist">
				<c:forEach var="reply" items="${articleData.article.articleReplyList }">
				<li id="${reply.replyNo }">
					<p>
						<b><span id="reply_nickname"><a href="/user.do?user=${reply.memberId }" style="color: black;">${reply.nickname }</a></span></b>
						<span id="reply_content">${reply.content }</span>
						<small style="color:#9C9C9C;"><i><span id="reply_regdate">${reply.transferRegDate }</span>전</i></small>
						
						<c:if test="${authUser.id == reply.memberId }">
						<button style="background-color: white; border: none; width: 14px; height: 14px"><i id="${reply.replyNo}" class="material-icons" style="font-size:14px">clear</i></button>
						</c:if>
					</p>
				</li>
				</c:forEach>
				</ul>
				 -->
				 
				<ul id="replylist">
				<c:forEach var="reply" items="${articleData.article.articleReplyList }">
				<li id="${reply.replyNo }">
					<p>
						<b><span id="reply_nickname"><a href="/user.do?user=${reply.memberId }" style="color: black;">${reply.nickname }</a></span></b>
						<span id="reply_content">${reply.content }</span>
						<small style="color:#9C9C9C;"><i><span id="reply_regdate">${reply.transferRegDate }</span>전</i></small>
						
						<c:if test="${authUser.id == reply.memberId }">
						<button style="background-color: white; border: none; width: 14px; height: 14px" onclick="deletereply(${reply.replyNo}, ${reply.articleNo });">
						<i class="material-icons" style="font-size:14px">clear</i>
						</button>
						</c:if>
					</p>
				</li>
				</c:forEach>
				</ul>
				 
			</div>
		</div>
		<hr>
		
		<c:if test="${!empty authUser && authUser.nickname != null && authUser.register_check }">
		<form class="form-horizontal" id="replyForm">
			<div class="form-group">
				
				<c:if test="${isLikeIt }">
					<label class="control-label col-sm-1" for="comment"><i id="heart" class="fa fa-heart" style="color:red;"> 좋아요</i></label>
				</c:if>
				
				<c:if test="${!isLikeIt }">
					<label class="control-label col-sm-1" for="comment"><i id="heart" class="fa fa-heart-o" style="color:#9C9C9C;"> 좋아요</i></label>
				</c:if>

				
				<div class="col-sm-11">
					<input type="text" class="form-control" id="comment" name="comment" placeholder="댓글 달기.." style="border: none;">
					<input type="hidden" id="reply_userId" value="${authUser.id }">
					<input type="hidden" id="reply_nickname" value="${authUser.nickname }">
					<input type="hidden" id="article_no" value="${articleData.article.number }">
				</div>
				
			</div>
			
			<input type="submit" id="reply-submitButton"class="btn btn-success" style="display:none;">
		</form>
		</c:if>
		<br> <br>
		<br> <br>
		<br> <br>

	</section>
	</div>
	<script src="/js/readArticle.js"></script>
</body>

</html>