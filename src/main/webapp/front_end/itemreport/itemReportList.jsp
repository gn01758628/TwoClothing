<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ItemReport</title>
	<!--頁籤icon-->
	<link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">
	<!--bootstrap5 css-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
	<!-- google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
	<style>
        *:not([class^="fa-"]) {
            font-family: 'Noto Sans TC', sans-serif !important;
        }
    </style>
    <!-- Font Awesome -->
    <script src="https://kit.fontawesome.com/716afdf889.js" crossorigin="anonymous"></script>
    <!--Sweet Alert-->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    <!--css-->
	<link rel="stylesheet" href="">
	<style>
	</style>
	<!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
</head>
<body>
	<div class="headerHTML"></div>

	<h1>商品檢舉清單</h1>
	<a href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId&mbrId=${mbrId}">查詢所有商品檢舉</a>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請重新確認</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

<!-- 	<div class="page"> -->
<%-- 		<c:if test="${itemReportPageQty > 0}"> --%>
<%-- 			<b><font color=red>第${currentPage}/${itemReportPageQty}頁</font></b> --%>
<%-- 		</c:if> --%>
<!-- 	</div> -->

	<table style="width: 80%; text-align: center;">
		<tr>
			<th>檢舉編號</th>
			<th>商品名稱</th>
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
				<td>${itemNameMap[itemReport.itemId]}</td>
				<td>${itemReport.reportDate}</td>
				<td>${itemReport.description}</td>
				<td>${rStatusMap[itemReport.rStatus]}</td>
				<td>${itemReport.auditDate}</td>
				<td>${resultMap[itemReport.result]}</td>
				<td>${itemReport.note}</td>
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
	
	<div class="footerHTML"></div>

	<!--bootstrap5 js-->
	<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
    <!--jQuery-->
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <!--Sweet Alert-->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
	<!--JS loader-->
	<script>
	    $(".headerHTML").load("${pageContext.request.contextPath}/headerHTML.html", function () {
	        // 保證headerHTML加載完才載入header.js
	        $.getScript("${pageContext.request.contextPath}/js/chengHan/header.js");
	    });
	
	    $(".footerHTML").load("${pageContext.request.contextPath}/footerHTML.html");
	</script>
</body>
</html>
