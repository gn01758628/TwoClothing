<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.twoclothing.tonyhsieh.*"%>
<%@ page import="com.twoclothing.model.employee.*"%>

<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  //Employee employee = (Employee) request.getAttribute("employee"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���u�s��</th>
		<th>���u�a�}</th>
		<th>���uEMAIL</th>
		<th>���u�q��</th>
		<th>���u�m�W</th>
		<th>���u����</th>
		<th>���u���A</th>
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