<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>


<html>
<head>
<title>�|���������]���ڥӽЬd�ߵ��G</title>

<style>
	body {
		display:flex;
		justy-content:center;
 		flex-direction: column;
 		align-items: center;
	}
  table#table-1 {
	background-color: #BEBEBE;
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
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	
	
  }
  table, th, td {
    border: 1px solid #BEBEBE;
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
		 <h3>�|���������]���ڥӽЬd�ߵ��G</h3>
		 <h4><a href="${pageContext.request.contextPath}/front_end/withdrawRequest/WRMain.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���ڥӽнs��</th>
		<th>�|���s��</th>
		<th>���ڪ��B</th>
		<th>�פJ�b��</th>
		<th>�ӽФ��</th>
		<th>�ӽЪ��A</th>
		<th>���u�s��</th>
		<th>�f�֤��</th>
		<th>�Ƶ�</th>
		<th>�ק�</th>
	
		
	</tr>
<%-- 	<%@ include file="page1.file" %>  --%>
	<c:forEach var="withdrawRequest" items="${WRList}">
		
		<tr>
			<td>${withdrawRequest.wrId}</td>
			<td>${withdrawRequest.mbrId}</td>
			<td>${withdrawRequest.amount}</td>
			<td>${withdrawRequest.mbrAccount}</td>
			<td>${withdrawRequest.reqDate}</td>
			<td>${withdrawRequest.reqStatus}</td>
			<td>${withdrawRequest.empId}</td>
			<td>${withdrawRequest.checkDate}</td>
			<td>${withdrawRequest.note}</td>
			<td>
			  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/WithdrawRequest/Update" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="wrId"  value="${withdrawRequest.wrId}">
			     <input type="hidden" name="choice"	value="getStatusList">
			  </FORM>
			</td>
		</tr>

	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>