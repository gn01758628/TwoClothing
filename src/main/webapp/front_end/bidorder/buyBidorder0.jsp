<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

<%@ include file="buyBidorderBanner.jsp" %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css" />
<style type="text/css">
  
</style>

</head>
<body>
<h1 style="color: red;">買家待付款</h1>
	<table id="myTable">
		<thead>
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
				<th>結帳</th>
				<th>取消訂單</th>
	</tr>
	</thead>
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
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="結帳"> 
								<input type="hidden"name="amount" value="${BidOrder.amount}"> 
								<input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
								<input type="hidden" name="sellMbrId" value="${BidOrder.sellMbrId}"> 
								<input type="hidden"name="bidItemId" value="${BidOrder.bidItemId}"> 
								<input type="hidden" name="buyMbrId" value="${user.mbrId}"> 
								<input	type="hidden" name="action" value="pay_And_Address">
							</FORM>
						</td>

						<td>
							<FORM METHOD="post"ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do"style="margin-bottom: 0px;">
								<input type="submit" value="取消訂單"> 
								<input type="hidden"name="amount" value="${BidOrder.amount}"> 
								<input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
								<input type="hidden" name="sellMbrId"value="${BidOrder.sellMbrId}"> 
								<input type="hidden"name="bidItemId" value="${BidOrder.bidItemId}"> 
								<input type="hidden" name="buyMbrId" value="${user.mbrId}"> 
								<input type="hidden" name="action" value="buy_Cancel_Order_No_Pay">
							</FORM>
						</td>
						
		</tr>
		
	</c:forEach>
	 </c:when>
  
</c:choose>


	
</table>
<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#myTable').DataTable({

				columnDefs: [
        		    {
        		        targets: -1,
        		        className: 'dt-body-right'
        		    }
        		  ]
			});

		});
	</script>
</body>
</html>