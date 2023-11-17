<%@ page import="com.twoclothing.model.aproduct.itemtracking.*"%>
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
<title>ItemTracking</title>
</head>
<body>
	<h1>商品追蹤清單</h1>
	
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請重新確認</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId">查詢所有商品追蹤</a>

	<form method="post" action="${pageContext.request.contextPath}/itemtrackinglist.check">
		<h3>商品追蹤新增</h3>
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
	
	<div class="page">
		<c:if test="${itemTrackingPageQty > 0}">
			<b><font color=red>第${currentPage}/${itemTrackingPageQty}頁</font></b>
		</c:if>
	</div>

	<table style="width: 80%; text-align: center;">
		<tr>
			<th>商品圖片</th>
			<th>商品名稱</th>
			<th>價格</th>
		</tr>
		<c:forEach var="item" items="${itemTrackingList}">
			<tr>
				<td><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" class="img"></td>
				<td>${item.itemName}</td>
				<td>${item.price}</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/itemtrackinglist.check">
						<button class="btn_delete" type="submit">移除</button>
						<input type="hidden" name="itemId" value="${item.itemId}">
						<input type="hidden" name="mbrId" value="${sessionScope.mbrId}">
						<input type="hidden" name="action" value="delete">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= itemTrackingPageQty}">
		<a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != itemTrackingPageQty}">
		<a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId&page=${itemTrackingPageQty}">至最後一頁</a>&nbsp;
	</c:if>

	<a href="${pageContext.request.contextPath}">回首頁</a>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="js/jQquery/jquery-3.7.1.min.js"></script>
</body>
</html>
