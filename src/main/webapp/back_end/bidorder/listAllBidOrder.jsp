<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.abid.bidorder.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
BidOrderServiceImpl bidOrderServiceImpl = new BidOrderServiceImpl();
    List<BidOrder> list = bidOrderServiceImpl.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllMembers.jsp</title>

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
		 <h3>競標 - listAllBidOrderjsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/bidorder/select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>競標商品訂單編號</th>
		<th>競標商品編號</th>
		<th>買家會員編號</th>
		<th>賣家會員編號</th>
		<th>買家評價星數</th>
		<th>買家評價內容</th>
		<th>賣家評價星數</th>
		<th>賣家評價內容</th>
		<th>訂單日期</th>
		<th>付款方式</th>
		<th>付款資料</th>
		<th>訂單金額</th>
		<th>訂單狀態</th>
		<th>收件地址</th>
		<th>收件人姓名</th>
		<th>收件人手機</th>
		<th>備註</th>
	</tr>
	
<c:choose>
    <c:when test="${not empty list}">	
	<%@ include file="page1.file" %> 
	<c:forEach var="BidOrder" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">>
		
		<tr>
		<td>${BidOrder.bidOrderId}</td>
		<td>${BidOrder.bidItemId}</td>
		<td>${BidOrder.buyMbrId}</td>
		<td>${BidOrder.sellMbrId}</td>
		<td>${BidOrder.buyStar}</td>
		<td>${BidOrder.buyerRatingDesc}</td>
		<td>${BidOrder.sellStar}</td>
		<td>${BidOrder.sellerRatingDesc}</td>
		<td>${BidOrder.orderDate}</td>
		<td>${BidOrder.payType}</td>
		<td>${BidOrder.payInfo}</td>
		<td>${BidOrder.amount}</td>
		<td>${BidOrder.orderStatus}</td>
		<td>${BidOrder.receiveAddress}</td>
		<td>${BidOrder.receiveName}</td>
		<td>${BidOrder.receivePhone}</td>
		<td>${BidOrder.remarks}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="bidOrderId"  value="${BidOrder.bidOrderId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="bidOrderId"  value="${BidOrder.bidOrderId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
		</tr>
		
	</c:forEach>
	<%@ include file="page2.file" %>
	 </c:when>
    <c:otherwise>
        <tr>
            <td colspan="17">無資料</td>
        </tr>
    </c:otherwise>
</c:choose>


	
</table>

</body>
</html>