<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="">
<title>itemSellerUpload</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<form method="post" action="/TwoClothing/Item" >
	<h1>商品上傳</h1>
	<br>
	<label>商品名稱</label>
	<input type="text" name="itemName" maxlength = 20>
	<br>
	<label>商品新舊程度</label>
	<input type="text" name="grade">
	<br>
	<label>尺寸</label>
	<input type="text" name="size">
	<br>
	<label>類別</label>
	<input type="text" name="tagId" value="1">
	<br>
	<label>員編</label>
	<input type="text" name="mbrId" value="1">
	<br>
	<label>價格</label>
	<input type="text" name="price">
	<br>
	<label>狀態</label>
	<input type="text" name="itemStatus" value="0">
	<br>
	<label>數量</label>
	<input type="text" name="quantity" autocomplete="on">
	<br>
	<label>商品描述</label>
	<textarea name="detail"></textarea>
	<br>
		
	
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</form>	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>