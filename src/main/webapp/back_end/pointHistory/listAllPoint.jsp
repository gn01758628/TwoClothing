<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>


<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

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
		 <h3>�Ҧ��I�Ƹ��</h3>
		 <h4><a href="">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�I�Ʋ��ʩ��ӽs��</th>
		<th>�|���s��</th>
		<th>�@��ӫ~�q��s��</th>
		<th>���ʤ��</th>
		<th>���ʼƭ�</th>
	
		
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