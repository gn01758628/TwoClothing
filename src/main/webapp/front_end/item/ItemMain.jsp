<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>一般商品</title>

    <style>
    </style>
</head>
<body>

    <div class="container">
    
    	<a href="${pageContext.request.contextPath}/Item/item?choice=addItem">賣家新增商品</a>
    	<br>
    	<a href="${pageContext.request.contextPath}/Item/item?choice=search">賣家商品查詢</a>
    	<br>
    	<a href="${pageContext.request.contextPath}/Item/item?choice=getAllList">前台商品列表</a>

    </div>

	
</body>
</html>
