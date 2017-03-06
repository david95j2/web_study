<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<style>
    .help-block {
        color: red;
        font-size: 12px;
    }    
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>회원 가입</h1>
		<p>회원 가입 페이지</p>
		<form action="join.do" method="post">
			<div class="form-group">
				<label for="email">이메일 주소</label> 
                <input type="text"
						class="form-control" id="email" name="email" placeholder="이메일 주소">
				<c:if test="${!errors.duplicatedId }"> <p class="help-block" id="email-error"></p> </c:if>
				<c:if test="${errors.duplicatedId }"> <p class="help-block" id="email-error">*중복된 이메일 입니다.</p> </c:if>
			</div>
            
			<div class="form-group">
				<label for="pwd">비밀번호</label> 
                <input type="password"
						class="form-control" id="password" name="password" placeholder="비밀번호 (영문+숫자혼합 8자리 이상)">
						<p class="help-block" id="password-error"></p>
			</div>
            
			<div class="form-group">
				<label for="Confirm">비밀번호 확인</label> 
                <input type="password"
						class="form-control" id="confirmPassword" name="confirmPassword" placeholder="비밀번호 확인">
						<p class="help-block" id="passwordConfirm-error"></p>
			</div>
            <button type="submit" id="btn_submit" class="btn btn-primary btn-block">회원 가입</button>
		</form>
	</div>
	
	<script src="js/joinForm.js"></script>
	</body>
</html>