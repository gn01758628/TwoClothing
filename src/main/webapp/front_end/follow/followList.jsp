<%@ page import="com.twoclothing.model.follow.*"%>
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
<title>Follow</title>
</head>
<body>
	<h1>會員關注清單</h1>
	
	<a href="${pageContext.request.contextPath}/follow?action=getAllByMbrId&mbrId=${mbrId}">查詢所有關注</a>

	<form method="post" action="${pageContext.request.contextPath}/follow">
		<h3>會員關注新增</h3>
		<h4>到時應刪除，變同移除作法 > 帶會員及關注會員編號，轉回商品頁，放一顆愛心按鈕放在商品頁，已追蹤成紅色，反之無色</h4>
		<label>會員編號</label>
		<input type="text" name="mbrId" maxlength=20>
		<br>
		<label>關注會員編號</label>
		<input type="text" name="followId">
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="新增">
	</form>
	<hr>
	
	<div class="page">
		<c:if test="${followPageQty > 0}">
			<b><font color=red>第${currentPage}/${followPageQty}頁</font></b>
		</c:if>
	</div>

	<table style="width: 80%; text-align: center;">
		<tr>
			<th>會員編號</th>
			<th>關注會員編號</th>
		</tr>
		<c:forEach var="follow" items="${followList}">
			<tr>
				<td>${follow.compositeKey.mbrId}</td>
				<td>${follow.compositeKey.followId}</td>
				<td>
					<form method="post" action="${pageContext.request.contextPath}/follow">
						<button class="btn_delete" type="submit">移除</button>
						<input type="hidden" name="mbrId" value="${follow.compositeKey.mbrId}">
						<input type="hidden" name="followId" value="${follow.compositeKey.followId}">
						<input type="hidden" name="action" value="delete">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/follow?action=getAllByMbrId&page=1&mbrId=${mbrId}">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/follow?action=getAllByMbrId&page=${currentPage - 1}&mbrId=${mbrId}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= followPageQty}">
		<a href="${pageContext.request.contextPath}/follow?action=getAllByMbrId&page=${currentPage + 1}&mbrId=${mbrId}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != followPageQty}">
		<a href="${pageContext.request.contextPath}/follow?action=getAllByMbrId&page=${followPageQty}&mbrId=${mbrId}">至最後一頁</a>&nbsp;
	</c:if>

	<a href="${pageContext.request.contextPath}">回首頁</a>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="js/jQquery/jquery-3.7.1.min.js"></script>
</body>
</html>
