<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
 
  
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有點數資料</h3>
		 <h4><a href="">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>點數異動明細編號</th>
		<th>會員編號</th>
		<th>一般商品訂單編號</th>
		<th>異動日期</th>
		<th>異動數值</th>
	
		
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="pointHistory" items="${PHList}">
		
		<tr>
			<td>${pointHistory.pointId}</td>
			<td>${pointHistory.mbrId}</td>
			<td>${pointHistory.orderId}</td>
			<td>${pointHistory.changeDate}</td>
			<td>${pointHistory.changeValue}</td>


		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>