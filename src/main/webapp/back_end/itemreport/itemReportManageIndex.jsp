<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/bootstrap5/bootstrap.min.css">
<title>ItemReportManageIndex</title>
</head>
<body>
	<h1>一般商品後台檢舉管理系統測試</h1>
	<a href="${pageContext.request.contextPath}/back/itemreport?action=getAll">查詢所有商品檢舉</a>
	<br>
	<br>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請重新確認</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post" action="${pageContext.request.contextPath}/back/itemreport">
		<label>商品編號</label>
		<input type="text" name="itemId" maxlength=20><br>
		
		<label>會員編號</label>
		<input type="text" name="mbrId"><br>

		<label>員工編號</label>
		<input type="text" name="empId"><br>
		
		<label>審核狀態</label>
		<select name="rStatus">
			<option value="">狀態</option>
			<option value="0">0</option>
			<option value="1">1</option>
		</select>
		<br>
		
		<label>審核結果</label>
		<select name="result">
			<option value="">結果</option>
			<option value="0">0</option>
			<option value="1">1</option>
		</select>
		<br>
		
		<input type="hidden" name="action" value="compositeQuery">
		<input type="submit" value="查詢">
	</form>
</body>
</html>
