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
			<th>商品名稱</th>
			<th>商品價格</th>
		</tr>
		<c:forEach var="item" items="${itemBrowsingList}">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
						<img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" class="img">
					</a>
				</td>
				<td>${item.itemId}</td>
				<td>${item.itemName}</td>
				<td>${item.price}</td>
			</tr>
		</c:forEach>
	</table>

	<a href="${pageContext.request.contextPath}">回首頁</a>

	<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
</body>
</html>
