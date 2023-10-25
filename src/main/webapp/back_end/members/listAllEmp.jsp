<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.members.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
    List<Members> list = membersServiceImpl.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
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
	<%@ include file="page1.file" %> 
	<c:forEach var="members" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">>
		
		<tr>
		<td>${members.mbrId}</td>
		<td>${members.mbrName}</td>
		<td>${members.email}</td>
		<td>${members.pswdHash}</td>
		<td>${members.mbrStatus}</td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${members.mbrId}" width=100px height=100px></td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader6?mbrid=${members.mbrId}" width=100px height=100px></td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader7?mbrid=${members.mbrId}" width=100px height=100px></td>
		<td>${members.mbrPoint}</td>
		<td>${members.balance}</td>
		<td>${members.buyStar}</td>
		<td>${members.buyRating}</td>
		<td>${members.sellStar}</td>
		<td>${members.sellRating}</td>
		<td>${members.lastLogin}</td>
		<td>${members.sellScore}</td>
		<td>${members.buyScore}</td>	
			<td>
			  <FORM METHOD="post" ACTION="Members.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mbrId"  value="${members.mbrId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="Members.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mbrId"  value="${members.mbrId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>