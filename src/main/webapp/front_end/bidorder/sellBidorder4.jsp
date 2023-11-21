<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ include file="buyBidorderBanner.jsp"%>


<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap5/bootstrap.min.css" />
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>會員中心-帳戶資訊 www.bootstrapmb.com</title>
<style type="">
</style>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/gordon/memberArea.css">

<style>
</style>

</head>
<body>
	<script
		src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>

	<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>
	<script src="<%=request.getContextPath()%>/js/gordon/memberArea.js"></script>

	<div id="head">

		<div class="menu">
			<ul>
				<li><a href='${pageContext.request.contextPath}/index.jsp'>回首頁</a></li>
				<li><a
					href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">個人資訊</a></li>
				<li><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId=${user.mbrId}">買家訂單</a></li>
				<li class="menu_selected"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder0&sellMbrId=${user.mbrId}">賣家訂單</a></li>
			</ul>
		</div>
	</div>
	<div id="hy_con">
		<div id="con_lf">
			<h2>賣家競標商品訂單</h2>
			<ul>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder0&sellMbrId=${user.mbrId}">待付款</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder1&sellMbrId=${user.mbrId}">待出貨</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder2&sellMbrId=${user.mbrId}">待收貨</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder3&sellMbrId=${user.mbrId}">訂單完成</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder4&sellMbrId=${user.mbrId}">訂單不成立</a></li>
			</ul>
		</div>
		<div id="con_rh">
			<div class="con_rh_con">
				<br></br>
				<p class="rh_title">訂單不成立</p>
	
	
	
	
	
	<table id="myTable" class="rh_tab2 dd_tab">
		<thead>

	<tr>
		<th>競標商品訂單編號</th>
		<th>買家</th>
		<th>商品圖片</th>
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
	 </thead>
<c:choose>
    <c:when test="${not empty BidOrder}">	
    
	<c:forEach var="BidOrder" items="${BidOrder}" >
		
		<tr>
		<td>${BidOrder.bidOrderId}</td>
		<td>${BidOrder.buyMbrId}</td>
		<td><img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${BidOrder.bidItemId}&position=1"alt="競標商品編號1的第一張圖片" class="img-fluid mx-auto" width="50"height="50"></td>
		<td>${BidOrder.buyStar}</td>
		<td>${BidOrder.buyerRatingDesc}</td>
		<td>${BidOrder.sellStar}</td>
		<td>${BidOrder.sellerRatingDesc}</td>
		<td>${BidOrder.orderDate}</td>
		<td>
		    <c:choose>
		        <c:when test="${BidOrder.payType eq 0}">信用卡</c:when>
		        <c:when test="${BidOrder.payType eq 1}">轉帳</c:when>
		        <c:when test="${BidOrder.payType eq 2}">虛擬錢包</c:when>
		        <c:otherwise>未知支付方式</c:otherwise>
		    </c:choose>
		</td>
		<td>${BidOrder.amount}</td>
		<td>
			<c:choose>
		        <c:when test="${BidOrder.orderStatus eq 0}">待付款</c:when>
		        <c:when test="${BidOrder.orderStatus eq 1}">待出貨</c:when>
		        <c:when test="${BidOrder.orderStatus eq 2}">待收貨</c:when>
		        <c:when test="${BidOrder.orderStatus eq 3}">訂單完成</c:when>
		        <c:when test="${BidOrder.orderStatus eq 4}">訂單不成立</c:when>
		    </c:choose>
		</td>
		<td>${BidOrder.receiveAddress}</td>
		<td>${BidOrder.receiveName}</td>
		<td>${BidOrder.receivePhone}</td>
		<td>${BidOrder.remarks}</td>
			

	
		</tr>
		
	</c:forEach>
	 </c:when>
    
</c:choose>
 </table>
   			</div>
		</div>
	</div>

	<div class="clear"></div>
	<div id="footer"></div>
</body>
</html>