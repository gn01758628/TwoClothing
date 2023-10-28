<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>


<html>
<head>
<title>會員虛擬錢包提款申請查詢結果</title>

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
		 <h3>會員虛擬錢包提款申請查詢結果</h3>
		 <h4><a href="${pageContext.request.contextPath}/front_end/withdrawRequest/WRMain.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>提款申請編號</th>
		<th>會員編號</th>
		<th>提款金額</th>
		<th>匯入帳號</th>
		<th>申請日期</th>
		<th>申請狀態</th>
		<th>員工編號</th>
		<th>審核日期</th>
		<th>備註</th>
		<th>修改</th>
	
		
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
			     <input type="submit" value="修改">
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