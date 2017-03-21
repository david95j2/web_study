<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.dasol.util.CookieBox"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
.jb-center {
	text-align: center;
}

.table > tbody > tr > td > p {
	padding-top: 10px;
}

.btn {
	float: right;
	margin-bottom: 20px;
}

.menu {
	float: right;
}

</style>
<body>
	<div class="container">
		<h2>게시판 프로젝트</h2>
		<c:if test="${authUser.email == null}">
			<a class="menu" href="/join.do">[회원가입]</a>
			<a class="menu" href="/login.do">[로그인]</a>
			
		</c:if>
		<c:if test="${authUser.email != null}">
			<a class="menu" href="/noti.do">[알림]<span class="badge" style="background-color: red;">1</span></a>
			<a class="menu" href="/myinfo.do">[내정보]</a>
			<a class="menu" href="/logout.do">[로그아웃]</a>
			<p><strong>${authUser.nickname}</strong>님,반가워요.</p>
			<c:if test="${authUser.register_check}">
				<div class="alert alert-success alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
					<strong>성공!</strong> 이메일 인증이 완료 되었습니다.
				</div>
			</c:if>
			<c:if test="${!authUser.register_check}">
				<div class="alert alert-warning alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
					<strong>주의!</strong> 이메일 인증을 하지 않으면 서비스를 이용하실 수 없습니다. <a href="#" class="alert-link"><i class="fa fa-retweet" style="font-size:18px"></i>이메일 인증 재전송</a>
				</div>
			</c:if>
		</c:if>
		<hr>
		
		<a href="/board/list.do?sort=article_no">[최신순]</a>
		<a href="/board/list.do?sort=like_cnt">[추천순]</a>
		<a href="/board/list.do?sort=reply_cnt">[댓글순]</a>
		<a href="/board/list.do?sort=read_cnt">[조회순]</a>
		<a href="/board/write.do" class="btn btn-primary" role="button">새 글쓰기</a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="10%" nowrap>글 번호</th>
					<th width="40%" nowrap>제목</th>
					<th width="20%" nowrap>작성자</th>
					<th width="10%" nowrap><i class="fa fa-clock-o" style="font-size:15px"></i></th>
					<th width="10%" nowrap><i class="fa fa-eye" style="font-size:18px"></i></th>
					<th width="10%" nowrap><i class="fa fa-heart" style="font-size:18px;color:red"></i></th>
				</tr>
			</thead>
			<c:if test="${articlePage.hasNoArticles() }">
				<tr>
					<td colspan="6">게시글이 없습니다.</td>
				</tr>
			</c:if>

			<c:forEach var="article" items="${articlePage.articleList }">
				<tbody>
					<tr>
						<td width="10%" nowrap><p>${article.number}</p></td>
						<td width="40%" nowrap>
						<p><a href="/board/read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
						<c:out value="${article.title }" />
						<span class="badge">${article.replyCnt }</span>
						</a></p>
						</td>
						<td width="20%" nowrap>
						<a href="#"><img src="${article.writer.profileImage}" class="img-circle" id="profile" style="width: 40px; height: 40px;"></a>
						<a href="#">${article.writer.nickname }</a>
						</td>
						<td width="10%" nowrap><p>${article.transferRegDate }전</p></td>
						<td width="10%" nowrap><p>${article.readCnt }</p></td>
						<td width="10%" nowrap><p>${article.likeCnt }</p></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		
		
		<c:if test="${articlePage.hasArticles() }">
			<div class="jb-center">
				<ul class="pagination">
					<c:if test="${1 >= articlePage.currentPage }">
						<li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
					</c:if>
					<c:if test="${1 < articlePage.currentPage }">
						<li><a href="/board/list.do?page=${articlePage.currentPage - 1 }&sort=${articlePage.sort }"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
					</c:if>
					
					<c:forEach var="page" begin="${articlePage.startPage }"
						end="${articlePage.endPage }">
						<c:if test="${articlePage.currentPage == page }">
							<li class="active"><a href="/board/list.do?page=${page }&sort=${articlePage.sort }">${page }</a></li>
						</c:if>
						<c:if test="${articlePage.currentPage != page }">
							<li><a href="/board/list.do?page=${page }&sort=${articlePage.sort }">${page }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${articlePage.totalPages > articlePage.currentPage }">
						<li><a href="/board/list.do?page=${articlePage.currentPage + 1 }&sort=${articlePage.sort }"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
					</c:if>
					<c:if test="${articlePage.totalPages <= articlePage.currentPage }">
						<li class="disabled"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
					</c:if>
				</ul>
			</div>
		</c:if>
		
	</div>
</body>
</html>