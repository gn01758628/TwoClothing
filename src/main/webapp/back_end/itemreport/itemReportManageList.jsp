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
			<th>員工編號</th>
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
				<td>${itemReport.empId}</td>
				<td>${itemReport.reportDate}</td>
				<td>${itemReport.description}</td>
				<td>${rStatusMap[itemReport.rStatus]}</td>
				<td>${itemReport.auditDate}</td>
				<td>${resultMap[itemReport.result]}</td>
				<td>${note}</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red; list-style-type: none">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/back/itemreport?action=${requestScope.action}&page=1&itemId=${itemId}&mbrId=${mbrId}&empId=${empId}&rStatus=${rStatus}&result=${result}">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/back/itemreport?action=${requestScope.action}&page=${currentPage - 1}&itemId=${itemId}&mbrId=${mbrId}&empId=${empId}&rStatus=${rStatus}&result=${result}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= itemReportPageQty}">
		<a href="${pageContext.request.contextPath}/back/itemreport?action=${requestScope.action}&page=${currentPage + 1}&itemId=${itemId}&mbrId=${mbrId}&empId=${empId}&rStatus=${rStatus}&result=${result}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != itemReportPageQty}">
		<a href="${pageContext.request.contextPath}/back/itemreport?action=${requestScope.action}&page=${itemReportPageQty}&itemId=${itemId}&mbrId=${mbrId}&empId=${empId}&rStatus=${rStatus}&result=${result}">至最後一頁</a>&nbsp;
	</c:if>

	<a href="${pageContext.request.contextPath}">回首頁</a>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="js/jQquery/jquery-3.7.1.min.js"></script>
</body>
</html>
