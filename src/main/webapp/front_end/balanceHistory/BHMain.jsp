<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>錢包異動</title>
    <style>

    </style>
</head>
<body>

    <div class="container">
    
    	<a href="${pageContext.request.contextPath}/BalanceHistory/balance?choice=add">新增</a>
    	<br>
    	<a href="${pageContext.request.contextPath}/BalanceHistory/balance?choice=searchMbrId">查詢</a>

    </div>

	
</body>
</html>
