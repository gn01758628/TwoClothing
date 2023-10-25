<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap5/bootstrap.min.css">
<title>ItemTracking</title>
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
			<td><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${itemTracking.compositeKey.itemId}&position=1"></td>
			<td>${itemTracking.compositeKey.itemId}</td>
			<td>${itemTracking.compositeKey.mbrId}</td>
			<td>${itemTracking.trackingTime}</td>
		</tr>
	</table>

	<a href="${pageContext.request.contextPath}/front_end/itemtracking/itemTrackingIndex.jsp">測試其他功能</a>
	<a href="${pageContext.request.contextPath}">回首頁</a>
</body>
</html>
