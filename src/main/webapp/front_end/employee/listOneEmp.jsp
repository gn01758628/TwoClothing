<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.twoclothing.tonyhsieh.*"%>
<%@ page import="com.twoclothing.model.employee.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  //Employee employee = (Employee) request.getAttribute("employee"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
		 <h3>員工資料 - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工編號</th>
		<th>員工地址</th>
		<th>員工EMAIL</th>
		<th>員工電話</th>
		<th>員工姓名</th>
		<th>員工部門</th>
		<th>員工狀態</th>
	</tr>
	<tr>
			<td>${Employee.empId}</td>
			<td>${Employee.address}</td>
			<td>${Employee.email}</td>
			<td>${Employee.phone}</td>
			<td>${Employee.empName}</td>
			<td>${Employee.deptId}</td>
			<td>${Employee.empStatus}</td>  
<%-- 		<td>${empVO.deptno}-[${empVO.deptVO.dname}]</td> --%>
	</tr>
</table>

</body>
</html>