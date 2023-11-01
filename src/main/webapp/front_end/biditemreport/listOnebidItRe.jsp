<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.abid.biditemreport.*"%>

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
		<th>�v�аӫ~���|�s��</th>
		<th>�v�аӫ~�s��</th>
		<th>�|���s��</th>
		<th>���u�s��</th>
		<th>���|���</th>
		<th>���|���e</th>
		<th>�f�֪��A</th>
		<th>�f�֤��</th>
		<th>�f�ֵ��G</th>
		<th>�Ƶ�</th>
	</tr>
	<tr>
			<td>${BidItemReport.reportId}</td>
			<td>${BidItemReport.bidItemId}-${BidItemReport.bidItem.bidName}</td>
			<td>${BidItemReport.mbrId}-${BidItemReport.members.mbrName}</td>
			<td>${BidItemReport.empId}</td>
			<td>${BidItemReport.reportDate}</td>
			<td>${BidItemReport.bidDescription}</td>
			<td>
				<c:choose>
       			 <c:when test="${BidItemReport.bidStatus == 0}">${BidItemReport.bidStatus} - �ݼf��</c:when>
       			 <c:when test="${BidItemReport.bidStatus == 1}">${BidItemReport.bidStatus} - �w�f��</c:when>
       			 </c:choose>
			</td>
			<td>${BidItemReport.auditDate}</td>
			<td>
				 <c:choose>
       			 <c:when test="${BidItemReport.result == 0}">${BidItemReport.result} - �B��</c:when>
       			 <c:when test="${BidItemReport.result == 1}">${BidItemReport.result} - ���B��</c:when>
       			 </c:choose>
			</td>
			<td>${BidItemReport.note}</td>

	</tr>
</table>

</body>
</html>