<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="">
<title>ItemTracking all list</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body>
	<h1>商品追蹤操作成功</h1>
	<table style="width: 50%; text-align: center;">
		<tr>
			<th>商品圖片</th>
			<th>商品編號</th>
			<th>會員編號</th>
			<th>紀錄時間</th>
		</tr>
		<tr>
			<td><img src=""></td>
			<td>${itemTracking.compositeKey.itemId}</td>
			<td>${itemTracking.compositeKey.mbrId}</td>
			<td>${itemTracking.trackingTime}</td>
		</tr>
	</table>

	<a href="${pageContext.request.contextPath}">回首頁</a>
</body>
</html>
