<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員虛擬錢包提款申請</title>
    <style>

    </style>
</head>
<body>

    <div class="container">
    
    	<a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=add">提出申請</a>
    	<br>
    	<a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=search">會員查詢(前台)</a>
    	<br>
    	<a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw?choice=getAll">員工查詢(後台)</a>

    </div>

	
</body>
</html>
