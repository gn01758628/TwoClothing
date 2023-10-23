<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="">
<title>ItemTracking</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>
<body>
	<h1>一般商品追蹤測試</h1>
	<a href="${pageContext.request.contextPath}/itemtracking?action=getAll">查詢所有商品追蹤</a>
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

	<form method="post"
		action="${pageContext.request.contextPath}/itemtracking">
		<h5>查詢單一會員所有商品追蹤</h5>
		<label>會員編號</label> <input type="text" name="mbrId"> <br>
		<input type="hidden" name="action" value="getAllByMbrId"> <input
			type="submit" value="查詢">
	</form>
	<br>
	<form method="post"
		action="${pageContext.request.contextPath}/itemtracking">
		<h5>查詢商品追蹤單一明細</h5>
		<label>商品編號</label> <input type="text" name="itemId" maxlength=20>
		<br> <label>會員編號</label> <input type="text" name="mbrId">
		<br> <input type="hidden" name="action" value="getByPK">
		<input type="submit" value="查詢">
	</form>
	<br>
	<form method="post"
		action="${pageContext.request.contextPath}/itemtracking">
		<h5>商品追蹤新增</h5>
		<label>商品編號</label> <input type="text" name="itemId" maxlength=20>
		<br> <label>會員編號</label> <input type="text" name="mbrId">
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="新增">
	</form>
	<br>
	<form method="post"
		action="${pageContext.request.contextPath}/itemtracking">
		<h5>商品追蹤移除</h5>
		<label>商品編號</label> <input type="text" name="itemId" maxlength=20>
		<br> <label>會員編號</label> <input type="text" name="mbrId">
		<br> <input type="hidden" name="action" value="delete"> <input
			type="submit" value="移除">
	</form>
</body>
</html>
