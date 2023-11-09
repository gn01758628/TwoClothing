<%@ page import="com.twoclothing.redismodel.itembrowsing.*"%>
<%@ page import="com.twoclothing.chi.controller.*"%>
<%@ page import="com.twoclothing.chi.service.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="">
<title>ItemBrowsing Redis</title>
</head>
<body>
	<h1>商品瀏覽紀錄</h1>
	<a href="${pageContext.request.contextPath}/itembrowsing.redis?action=getAllByMbrId&mbrId=${mbrId}">查詢所有商品瀏覽</a>

	<form method="post" action="${pageContext.request.contextPath}/itembrowsing.redis">
		<h3>商品瀏覽新增</h3>
		<h4>到時應刪除，變同移除作法 > 帶商品及會員編號，轉回商品頁，放一顆愛心按鈕放在商品頁，已追蹤成紅色，反之無色</h4>
		<label>商品編號</label>
		<input type="text" name="itemId" maxlength=20>
		<br>
		<label>會員編號</label>
		<input type="text" name="mbrId">
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="新增">
	</form>
	<hr>

	<table style="width: 80%; text-align: center;">
		<tr>
			<th>商品圖片</th>
			<th>商品編號</th>
			<th>會員編號</th>
		</tr>
		<c:forEach var="itemBrowsing" items="${itemBrowsingList}">
			<tr>
				<td><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${itemBrowsing.compositeKey.itemId}&position=1" class="img"></td>
				<td>${itemBrowsing.itemId}</td>
				<td>${itemBrowsing.mbrId}</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/itembrowsing.redis">
						<button class="btn_update" type="submit">模擬再看一次</button>
						<input type="hidden" name="itemId" value="${itemBrowsing.compositeKey.itemId}">
						<input type="hidden" name="mbrId" value="${itemBrowsing.compositeKey.mbrId}">
						<input type="hidden" name="action" value="update">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<a href="${pageContext.request.contextPath}">回首頁</a>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="js/jQquery/jquery-3.7.1.min.js"></script>
</body>
</html>
