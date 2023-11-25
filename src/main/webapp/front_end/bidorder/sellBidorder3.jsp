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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/gordon/memberArea.css">


    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">

</head>
<body>

<!--放在最前面-->
<div class="headerHTML"></div>
	<script src="<%=request.getContextPath()%>/js/gordon/memberArea.js"></script>


	<div id="hy_con">
		<div id="con_lf">

		</div>
		<div id="con_rh">
			<div class="con_rh_con">
				<br></br>
				<p class="rh_title">交易完成</p>
	
	
	
	
	
	<table id="myTable" class="rh_tab2 dd_tab">
		<thead>

			<tr>
				<th>競標商品訂單編號</th>
				<th>買家</th>
				<th>商品圖片</th>
				<th>訂單日期</th>
				<th>訂單金額</th>
				<th>訂單狀態</th>
				<th>訂單詳情</th>
				<th>評價訂單</th>  	
			</tr>
		<thead>
			<c:choose>
				<c:when test="${not empty BidOrder}">

					<c:forEach var="BidOrder" items="${BidOrder}">

						<tr>
							<td>${BidOrder.bidOrderId}</td>
							<td>${BidOrder.buyMbrId}</td>
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
									<input type="hidden" name="action" value="bidOrderBidItem">
								</FORM>
							</td>
						  	<c:choose>
		                        <c:when test="${empty BidOrder.sellStar and empty BidOrder.sellerRatingDesc}">
									<td>
			                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" style="margin-bottom: 0px;">
			                                <input type="submit" value="訂單評價"> 
			                                <input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
			                                <input type="hidden" name="sellMbrId" value="${user.mbrId}"> 
			                                <input type="hidden" name="action" value="sell_Bidorder_Rating">
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
	<!--放在最後面-->
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
    
    //插入左側連結
    $(document).ready(function () {
        // 使用 AJAX 請求加載其他內容
        $.ajax({
            url: "${pageContext.request.contextPath}/front_end/bidorder/sideSellBidorder.jsp",
            method: "GET",
            success: function (data) {
                $("#con_lf").html(data);
            },
            error: function (xhr, status, error) {
                console.error("Error loading content:", error);
            }
        });
    });
</script>
</body>
</html>