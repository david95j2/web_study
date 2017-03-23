<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.dasol.util.CookieBox" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<% 
		Map<String, String> cookiesMap = null;
		cookiesMap = CookieBox.getCookiesValueMap(request);
		System.out.println(cookiesMap);
		if(cookiesMap.get("aT") != null) { // 쿠키 존재 한다면 자동 로그인
			System.out.println("aT="+cookiesMap.get("aT"));
			response.sendRedirect("/alogin.do");
		} else
			response.sendRedirect("/board/list.do");
	%>
</body>
</html>