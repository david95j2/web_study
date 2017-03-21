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
			font-size: 11px;
			display: inline;
		}
		
		.bsummary a {
			color:black;
			float: right;
			margin-right: 15px;
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
				<img src="${articleData.article.writer.profileImage}" class="img-circle" id="profile" style="width: 70px; height: 70px;">
				${articleData.article.writer.nickname}
			
				<i class="fa fa-heart" style="font-size:15px"> ${articleData.article.likeCnt }</i>
				<i class="fa fa-eye" style="font-size:15px">  ${articleData.article.readCnt }</i>
				<i class="fa fa-clock-o" style="font-size:15px"> ${articleData.article.transferRegDate }전</i>
			</div>
			
		<!-- title -->	
			<div class="panel-body title">
				<p>${articleData.article.number }번째 글 
				<h3><strong>${articleData.article.title }</strong></h3>
			</div>
			<hr>
		<!-- content -->			
			<div class="panel-body">${articleData.articleContent.content }</div>
			
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
				<li><c:set var="pageNo"
					value="${empty param.pageNo ? '1' : param.pageNo}" />
				<a href="/board/list.do?pageNo=${pageNo }">목록보기</a> </li>
				<c:if test="${authUser.id == articleData.article.writer.id}">
					<li><a href="#" data-toggle="modal" data-target="#myModal" ><i class="fa fa-trash-o"> 삭제</i></a></li>
					<li><a href="/board/modify.do?no=${articleData.article.number }"><i class="fa fa-edit"> 수정</i></a></li>
				</c:if>
			
				<c:if test="${articleData.article.modDate != null }">
					<li><p id="moddate"><i class="fa fa-history"> ${articleData.article.transferModDate } 에 마지막 수정됨</i></p></li>
				</c:if>
			</ul>
			</div>
		</div>
		</section>
	</div>

</body>

</html>