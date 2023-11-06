<%@ page import="com.twoclothing.model.aproduct.itemreport.*"%>
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
<title>ItemReport</title>
</head>
<body>
	<h1>商品檢舉清單</h1>
	<a href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId&mbrId=${mbrId}">查詢所有商品檢舉</a>

	<form method="post" action="${pageContext.request.contextPath}/front/itemreport">
		<h3>商品檢舉新增</h3>
		<h4>到時應刪除 > 帶商品及會員編號，轉回商品頁，放一顆檢舉按鈕放在商品頁</h4>
		<label>商品編號</label>
		<input type="text" name="itemId" maxlength=20>
		<br>
		<label>會員編號</label>
		<input type="text" name="mbrId">
		<br>
		<label>檢舉原因</label>
		<input type="text" name="description">
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="新增">
	</form>
	<hr>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請重新確認</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="page">
		<c:if test="${itemReportPageQty > 0}">
			<b><font color=red>第${currentPage}/${itemReportPageQty}頁</font></b>
		</c:if>
	</div>

	<table style="width: 80%; text-align: center;">
		<tr>
			<th>檢舉編號</th>
			<th>商品編號</th>
			<th>會員編號</th>
			<th>檢舉日期</th>
			<th>檢舉原因</th>
			<th>審核狀態</th>
			<th>審核日期</th>
			<th>審核結果</th>
			<th>備註</th>
		</tr>
		<c:forEach var="itemReport" items="${itemReportList}">
			<tr>
				<td>${itemReport.reportId}</td>
				<td>${itemReport.itemId}</td>
				<td>${itemReport.mbrId}</td>
				<td>${itemReport.reportDate}</td>
				<td>${itemReport.description}</td>
				<td>${rStatusMap[itemReport.rStatus]}</td>
				<td>${itemReport.auditDate}</td>
				<td>${resultMap[itemReport.result]}</td>
				<td>${note}</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/front/itemreport?action=${requestScope.action}&page=1&mbrId=${mbrId}">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/front/itemreport?action=${requestScope.action}&page=${currentPage - 1}&mbrId=${mbrId}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= itemReportPageQty}">
		<a href="${pageContext.request.contextPath}/front/itemreport?action=${requestScope.action}&page=${currentPage + 1}&mbrId=${mbrId}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != itemReportPageQty}">
		<a href="${pageContext.request.contextPath}/front/itemreport?action=${requestScope.action}&page=${itemReportPageQty}&mbrId=${mbrId}">至最後一頁</a>&nbsp;
	</c:if>

	<a href="${pageContext.request.contextPath}">回首頁</a>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="js/jQquery/jquery-3.7.1.min.js"></script>
</body>
</html>
