<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
//   Members members = (Members) request.getAttribute("members"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - listOneShipSetting.jsp</h3>
		 <h4><a href='${pageContext.request.contextPath}/MemberCentre.jsp'>會員中心</a>
	</td></tr>
</table>

<table>
	<tr>
		 <th>會員物流設定編號</th>
        <th>會員編號</th>
        <th>會員姓名</th>
        <th>收件人姓名</th>
        <th>收件人手機</th>
        <th>收件人地址</th>
	</tr>
	<tr>
		 <td>${ShipSetting.shipId}</td>
         <td>${ShipSetting.mbrId}</td>
         <td>${user.mbrName}</td>
         <td>${ShipSetting.receiveName}</td>
         <td>${ShipSetting.receivePhone}</td>
         <td>${ShipSetting.receiveAddress}</td>
	</tr>
</table>

</body>
</html>