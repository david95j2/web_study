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
	
	<% 
		Map<String, String> cookiesMap = null;
		cookiesMap = CookieBox.getCookiesValueMap(request);
		System.out.println(cookiesMap);
		if(cookiesMap.get("aT") != null)
			response.sendRedirect("/alogin.do");
		else
			response.sendRedirect("/board/list.do");
	%>
</body>
</html>