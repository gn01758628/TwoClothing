<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
//   Members members = (Members) request.getAttribute("members"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
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
		 <h4><a href="select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���m�W</th>
		<th>�|���H�c(�b��)</th>
		<th>�|���K�X���ƭ�</th>
		<th>�b�����A</th>
		<th>�|���j�Y�K</th>
		<th>�|����a�ӳ��Ϥ�01</th>
		<th>�|����a�ӳ��Ϥ�02</th>
		<th>�|���I��</th>
		<th>�|���������]�l�B</th>
		<th>�R�a�����`�P��</th>
		<th>�R�a�����`�H��</th>
		<th>��a�����`�P��</th>
		<th>��a�����`�H��</th>
		<th>�|���̫�n�J�ɶ�</th>
		<th>��a�v������</th>
		<th>�R�a�v������</th>
	</tr>
	<tr>
		<td>${Members.mbrId}</td>
		<td>${Members.mbrName}</td>
		<td>${Members.email}</td>
		<td>${Members.pswdHash}</td>
		<td>${Members.mbrStatus}</td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}" width=100px height=100px></td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader6?mbrid=${Members.mbrId}" width=100px height=100px></td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader7?mbrid=${Members.mbrId}" width=100px height=100px></td>
		<td>${Members.mbrPoint}</td>
		<td>${Members.balance}</td>
		<td>${Members.buyStar}</td>
		<td>${Members.buyRating}</td>
		<td>${Members.sellStar}</td>
		<td>${Members.sellRating}</td>
		<td>${Members.lastLogin}</td>
		<td>${Members.sellScore}</td>
		<td>${Members.buyScore}</td>	
	</tr>
</table>

</body>
</html>