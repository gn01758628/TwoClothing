<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>聊天室</title>
    <!--頁籤icon-->
    <link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">
    <!--bootstrap5 css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <!-- google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
    <style>
        *:not([class^="fa-"]) {
            font-family: 'Noto Sans TC', sans-serif !important;
        }
    </style>
    <!-- Font Awesome -->
    <script src="https://kit.fontawesome.com/716afdf889.js" crossorigin="anonymous"></script>
    <!--Sweet Alert-->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    <!--你們自己的css-->
<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/css/gordon/memberArea.css">
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">

<style>
</style>

</head>
<body>
<div class="headerHTML"></div>


	<div id="hy_con">
		<div id="con_lf">
		<br>
			<h2>帳戶管理</h2>
				<ul>
					<li class="lf_li1"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">個人資訊</a></li>
					<li class="lf_li1"><a href="<%=request.getContextPath()%>/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">物流設定</a></li>
				</ul> 
			<h2>買家競標商品訂單</h2>
			<ul>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId=${user.mbrId}">待付款</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder1&buyMbrId=${user.mbrId}">未出貨</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder2&buyMbrId=${user.mbrId}">待收貨</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder3&buyMbrId=${user.mbrId}">訂單完成</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder4&buyMbrId=${user.mbrId}">訂單不成立</a></li>
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
				<th>賣家</th>
				<th>商品圖片</th>
				<th>訂單日期</th>
				<th>訂單金額</th>
				<th>訂單狀態</th>
				<th>訂單詳情</th>
				<th>評價訂單</th>  
<!--  				<c:forEach var="BidOrder" items="${BidOrder}">
					<c:choose>
						<c:when test="${empty BidOrder.buyStar}">
							<th>評價訂單</th>
						</c:when>
					</c:choose>
				</c:forEach>   -->
			</tr>
			</thead>
			<c:choose>
				<c:when test="${not empty BidOrder}">

					<c:forEach var="BidOrder" items="${BidOrder}">

						<tr>
						<td>${BidOrder.bidOrderId}</td>
						<td>${BidOrder.sellMbrId}</td>
						<td><a href="${pageContext.request.contextPath}/front/biditem/anyone/detail?bidItemId=${BidOrder.bidItemId}" target="_blank">
						    <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${BidOrder.bidItemId}&position=1" alt="競標商品編號1的第一張圖片" class="img-fluid mx-auto" width="50" height="50">
						</a></td>
						<td>${BidOrder.orderDate}</td>
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

						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="訂單詳情"> 
								<input type="hidden" name="bidOrderId"value="${BidOrder.bidOrderId}"> 
								<input type="hidden" name="bidItemId" value="${BidOrder.bidItemId}">
								<input type="hidden" name="buyMbrId" value="${user.mbrId}">
								<input type="hidden" name="action" value="bidOrderBidItem">
							</FORM>
						</td>
						<c:choose>
	                        <c:when test="${empty BidOrder.buyStar and empty BidOrder.buyerRatingDesc}">
								<td>
		                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" style="margin-bottom: 0px;">
		                                <input type="submit" value="訂單評價"> 
		                                <input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
		                                <input type="hidden" name="buyMbrId" value="${user.mbrId}"> 
		                                <input type="hidden" name="action" value="buy_Bidorder_Rating">
		                            </FORM>
								</td>		
	                        </c:when>
	                    </c:choose>
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
<div class="footerHTML"></div>
<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
<!--Sweet Alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
<!--JS loader-->
<script>
    $(".headerHTML").load("${pageContext.request.contextPath}/headerHTML.html", function () {
        // 保證headerHTML加載完才載入header.js
        $.getScript("${pageContext.request.contextPath}/js/chengHan/header.js");
    });

    $(".footerHTML").load("${pageContext.request.contextPath}/footerHTML.html");
</script>

</body>
</html>