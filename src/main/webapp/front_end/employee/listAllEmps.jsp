<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/main/main.css"> --%>
<style>
body {
	padding-left: 20px;
}
table, th, td {
  border:1px solid black;
}
th, td {
	height: 50px;
	width: 100px;
}

</style>
<title>List Emps</title>
</head>
<body>
	<h1>員工列表</h1>
	<c:if test="${empPageQty > 0}">
  		<b><font color=red>第${currentPage}/${empPageQty}頁</font></b>
	</c:if>
	<br>

	<table style="width:50%; text-align:center;">
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>員工EMAIL</th>
			<th>員工電話</th>
			<th>員工地址</th>
			
		<c:forEach var="emp" items="${empList}">
			<tr>
				<td>${emp.empid}</td>
				<td>${emp.empname}</td>
				<td>${emp.email}</td>
				<td>${emp.phone}</td>
				<td>${emp.address}</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/emp/emp.do?action=getAll&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage - 1 != 0}">
		<a href="${pageContext.request.contextPath}/emp/emp.do?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage + 1 <= empPageQty}">
		<a href="${pageContext.request.contextPath}/emp/emp.do?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${currentPage != empPageQty}">
		<a href="${pageContext.request.contextPath}/emp/emp.do?action=getAll&page=${empPageQty}">至最後一頁</a>&nbsp;
	</c:if>
	<br>

	<br><br>
	
	<a href="${pageContext.request.contextPath}/fornt_end/index.jsp">回首頁</a>	
</body>
</html>