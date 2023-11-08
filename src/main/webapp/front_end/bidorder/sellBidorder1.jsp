<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

<%@ include file="sellBidorderBanner.jsp" %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
<body>
<table>
賣家待出貨
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
    <c:when test="${not empty BidOrder}">	
    
	<c:forEach var="BidOrder" items="${BidOrder}" >
		
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
			     <input type="submit" value="待出貨">
			     <input type="hidden" name="amount" value="${BidOrder.amount}">
			     <input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
			     <input type="hidden" name="sellMbrId" value="${BidOrder.sellMbrId}">
			     <input type="hidden" name="bidItemId" value="${BidOrder.bidItemId}">
			     <input type="hidden" name="buyMbrId" value="${BidOrder.buyMbrId}">
			     <input type="hidden" name="action"	value="shipped"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="取消交易">
			     <input type="hidden" name="amount" value="${BidOrder.amount}">
			     <input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
			     <input type="hidden" name="sellMbrId" value="${BidOrder.sellMbrId}">
			     <input type="hidden" name="bidItemId" value="${BidOrder.bidItemId}">
			     <input type="hidden" name="buyMbrId" value="${BidOrder.buyMbrId}">
			     <input type="hidden" name="action"	value="sell_Cancel_Order"></FORM>
			</td>


		</tr>
		
	</c:forEach>
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