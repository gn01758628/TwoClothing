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
		 <h3>員工資料 - listOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員信箱(帳號)</th>
		<th>會員密碼哈希值</th>
		<th>帳號狀態</th>
		<th>會員大頭貼</th>
		<th>會員賣家商場圖片01</th>
		<th>會員賣家商場圖片02</th>
		<th>會員點數</th>
		<th>會員虛擬錢包餘額</th>
		<th>買家評價總星數</th>
		<th>買家評價總人數</th>
		<th>賣家評價總星數</th>
		<th>賣家評價總人數</th>
		<th>會員最後登入時間</th>
		<th>賣家權限分數</th>
		<th>買家權限分數</th>
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