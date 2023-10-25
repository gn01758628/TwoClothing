<%@ page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap5/bootstrap.min.css">
<title>ItemTracking all list</title>
</head>
<body>
	<h1>商品追蹤清單</h1>

	<c:if test="${itemTrackingPageQty > 0}">
		<b><font color=red>第${currentPage}/${itemTrackingPageQty}頁</font></b>
	</c:if>

	<table style="width: 50%; text-align: center;">
		<tr>
			<th>商品圖片</th>
			<th>商品編號</th>
			<th>會員編號</th>
			<th>紀錄時間</th>
		</tr>
		<c:forEach var="itemTracking" items="${itemTrackingList}">
			<tr>
				<td><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${itemTracking.compositeKey.itemId}&position=1"></td>
				<td>${itemTracking.compositeKey.itemId}</td>
				<td>${itemTracking.compositeKey.mbrId}</td>
				<td>${itemTracking.trackingTime}</td>
			</tr>
		</c:forEach>
	</table>
	<%
	System.out.println(request.getAttribute("action") + "———————————————————————————————————");
	%>
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/itemtracking?action=${requestScope.action}&page=1&mbrId=${mbrId}">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/itemtracking?action=${requestScope.action}&page=${currentPage - 1}&mbrId=${mbrId}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= itemTrackingPageQty}">
		<a href="${pageContext.request.contextPath}/itemtracking?action=${requestScope.action}&page=${currentPage + 1}&mbrId=${mbrId}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != itemTrackingPageQty}">
		<a href="${pageContext.request.contextPath}/itemtracking?action=${requestScope.action}&page=${itemTrackingPageQty}&mbrId=${mbrId}">至最後一頁</a>&nbsp;
	</c:if>

	<a href="${pageContext.request.contextPath}/front_end/itemtracking/itemTrackingIndex.jsp">測試其他功能</a>
	<a href="${pageContext.request.contextPath}">回首頁</a>
</body>
</html>
