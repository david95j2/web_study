<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
</head>
<body>
	
	<div class="container">
		<h1>비밀번호 변경</h1>
		<p>비밀번호 변경 페이지</p>
		
		<form action="changePwd.do" method="post">
		
		<c:if test="${authUser.hasPassword }">			
			<div class="form-group">
				<label for="curPwd">현재 비밀번호</label> 
                <input type="password"
						class="form-control" id="curPwd" name="curPwd" placeholder="현재 비밀번호">
				<c:if test="${!errors.badCurPwd }"> <p class="help-block" id="curPwd-error"></p> </c:if>
				<c:if test="${errors.badCurPwd }"> <p class="help-block" id="curPwd-error">*현재 비밀번호가 일치하지 않습니다.</p> </c:if>
			</div>
        </c:if>
			<div class="form-group">
				<label for="newPwd">새로운 비밀번호</label> 
                <input type="password"
						class="form-control" id="newPwd" name="newPwd" placeholder="새로운 비밀번호 (영문+숫자혼합 8자리 이상)">
						<p class="help-block" id="newPwd-error"></p>
			</div>
            
			<div class="form-group">
				<label for="Confirm">새로운 비밀번호 확인</label> 
                <input type="password"
						class="form-control" id="confirmNewPwd" name="confirmNewPwd" placeholder="새로운 비밀번호 확인">
						<p class="help-block" id="confirmNewPwd-error"></p>
			</div>
            <button type="submit" id="btn_submit" class="btn btn-primary btn-block">변경 하기</button>
		</form>
	</div>
	<script src="js/changePwdForm.js"></script>
</body>
</html>