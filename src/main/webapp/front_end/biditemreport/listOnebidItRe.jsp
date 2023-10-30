<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.abid.biditemreport.*"%>

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
		<th>競標商品檢舉編號</th>
		<th>競標商品編號</th>
		<th>會員編號</th>
		<th>員工編號</th>
		<th>檢舉日期</th>
		<th>檢舉內容</th>
		<th>審核狀態</th>
		<th>審核日期</th>
		<th>審核結果</th>
		<th>備註</th>
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
       			 <c:when test="${BidItemReport.bidStatus == 0}">${BidItemReport.bidStatus} - 待審核</c:when>
       			 <c:when test="${BidItemReport.bidStatus == 1}">${BidItemReport.bidStatus} - 已審核</c:when>
       			 </c:choose>
			</td>
			<td>${BidItemReport.auditDate}</td>
			<td>
				 <c:choose>
       			 <c:when test="${BidItemReport.result == 0}">${BidItemReport.result} - 處分</c:when>
       			 <c:when test="${BidItemReport.result == 1}">${BidItemReport.result} - 不處分</c:when>
       			 </c:choose>
			</td>
			<td>${BidItemReport.note}</td>

	</tr>
</table>

</body>
</html>