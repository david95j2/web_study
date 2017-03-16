<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.dasol.util.CookieBox" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>게시판 프로젝트</h2>
		<c:if test="${authUser.email == null}">
			<a href="login.do">[로그인]</a>
			<a href="join.do">[회원가입]</a>
		</c:if>
		<c:if test="${authUser.email != null}">
			<a href="logout.do">[로그아웃]</a>
			<a href="myinfo.do">[내정보]</a>
			<a href="noti.do">[알림]</a>
			<p>${authUser.email}님, 반가워요.</p> <br>
			<p>이메일 인증 = ${authUser.register_check}</p>
		</c:if>
		<hr>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Email</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>John</td>
					<td>Doe</td>
					<td>john@example.com</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>