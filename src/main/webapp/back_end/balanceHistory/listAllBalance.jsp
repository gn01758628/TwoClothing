<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<html>
<head>
<title>虛擬錢包異動資料</title>

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
		 <h3>虛擬錢包異動資料</h3>
		 <h4><a href="${pageContext.request.contextPath}/back_end/balanceHistory/BHMain.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>錢包異動明細編號</th>
		<th>會員編號</th>
		<th>一般商品訂單編號</th>
		<th>競標商品訂單編號</th>
		<th>提款申請編號</th>
		<th>異動日期</th>
		<th>異動數值</th>
	
		
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="balanceHistory" items="${BHList}">
		
		<tr>
			<td>${balanceHistory.balanceId}</td>
			<td>${balanceHistory.mbrId}</td>
			<td>${balanceHistory.orderId}</td>
			<td>${balanceHistory.bidOrderId}</td>
			<td>${balanceHistory.wrId}</td>
			<td>${balanceHistory.changeDate}</td>
			<td>${balanceHistory.changeValue}</td>
		</tr>
	</c:forEach>
		<tr>
			<td>${balanceHistory.balanceId}</td>
			<td>${balanceHistory.mbrId}</td>
			<td>${balanceHistory.orderId}</td>
			<td>${balanceHistory.bidOrderId}</td>
			<td>${balanceHistory.wrId}</td>
			<td>${balanceHistory.changeDate}</td>
			<td>${balanceHistory.changeValue}</td>
		</tr>
	
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>