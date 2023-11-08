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
<title>ItemReportUpdate</title>
<style>
	td {
		display: inline-block;
	}
	
	td:nth-child(2) {
        padding-left: 10px;
    }
    
    td.note {
    	padding-left: 41.5px;
    }
</style>
</head>
<body>
	<h1>商品檢舉審核</h1>
	
	<c:if test="${not empty errorMsgs}">
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red; list-style-type: none">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<form method="post" action="${pageContext.request.contextPath}/back/itemreport">
		<table>
			<tr>
				<td>檢舉編號</td>
				<td>${itemReport.reportId}</td>
			</tr>
			<tr>
				<td>商品編號</td>
				<td>${itemReport.itemId}</td>
			</tr>
			<tr>
				<td>會員編號</td>
				<td>${itemReport.mbrId}</td>
			</tr>
			<tr>
				<td>員工編號</td>
				<td>${itemReport.empId}</td>
			</tr>
			<tr>
				<td>檢舉日期</td>
				<td>${itemReport.reportDate}</td>
			</tr>
			<tr>
				<td>檢舉原因</td>
				<td>${itemReport.description}</td>
			</tr>
			<tr>
				<td>審核狀態</td>
				<td>${rStatusMap[itemReport.rStatus]}</td>
			</tr>
			<tr>
				<td>審核日期</td>
				<td>${itemReport.auditDate}</td>
			</tr>
			<tr>
				<td>審核結果</td>
				<td>
					<select  name="result">
						<option value="-1">選擇處分</option>
						<option value="0">處分</option>
						<option value="1">不處分</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>備註</td>
				<td class="note"><input type="text" name="note" value="${itemReport.note}" size="100"/></td>
			</tr>
		</table>
		<br>
		
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="reportId" value="${itemReport.reportId}">
		<input type="hidden" name="empid" value="${itemReport.empId}">
		<input type="submit" value="送出">
		
		<button id="cancel" type="button">取消</button>
	</form>
	
	<script>
		document.getElementById('cancel').addEventListener('click', function() {
			window.location.replace('${pageContext.request.contextPath}/back/itemreport?action=getAll');
		});
	</script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="js/jQquery/jquery-3.7.1.min.js"></script>
</body>
</html>
