<%--suppress ALL --%>
<%--suppress JSUnusedLocalSymbols --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${bidItem.bidName}</title>
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

    <style>
        #bidBtn_hr {
            margin-top: -5px;
            margin-bottom: -5px;
            padding: 0px !important;
        }

        #bidHelp, #wrongMsg {
            margin-top: 10px;
            margin-bottom: 5px;
            padding: 0px !important;
            color: #6c757d;
        }

        .align-vertical {
            display: flex;
            align-items: center;
        }

        .table-no-border td,
        .table-no-border th {
            border: none;
        }
    </style>
        <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
    
    
</head>
<body>
<div class="headerHTML"></div>
<br>
<div class="container text-center">
    <h1>${bidStatus}</h1>
</div>
<!--商品詳情-->
<div class="container">
    <div class="row mt-5">
        <div class="col-md-6">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${BidItem.bidItemId}&position=1"
                 alt="商品主圖" class="img-fluid">
        </div>
        
        <div class="col-md-6">
            <ul class="list-group">
                <li class="list-group-item">競標商品訂單編號：<span id="bidItemId">${BidOrder.bidOrderId}</span></li>
                <li class="list-group-item">競標商品編號：<span id="bidItemId">${BidOrder.bidItemId}</span></li>
                <li class="list-group-item">競標商品名稱：${BidItem.bidName}</li>
                <li class="list-group-item">賣家會員編號：${BidOrder.sellMbrId}</li>
                <li class="list-group-item">訂單日期：${BidOrder.orderDate}</li>
                <c:if test="${not empty BidOrder.payType}">
				    <li class="list-group-item">付款方式：
				    <span id="reservePrice">
		    			<c:choose>
			        		<c:when test="${BidOrder.payType eq 0}">信用卡</c:when>
					        <c:when test="${BidOrder.payType eq 1}">轉帳</c:when>
					        <c:when test="${BidOrder.payType eq 2}">虛擬錢包</c:when>
					    </c:choose>
					</span>
					</li>
				</c:if>
<%--                 <li class="list-group-item">付款資料：${BidOrder.payInfo}</li> --%>
                <li class="list-group-item">訂單金額：${BidOrder.amount}</li>
                <li class="list-group-item">訂單狀態：
    				<c:choose>
				        <c:when test="${BidOrder.orderStatus eq 0}">待付款</c:when>
				        <c:when test="${BidOrder.orderStatus eq 1}">待出貨</c:when>
				        <c:when test="${BidOrder.orderStatus eq 2}">待收貨</c:when>
				        <c:when test="${BidOrder.orderStatus eq 3}">訂單完成</c:when>
				        <c:when test="${BidOrder.orderStatus eq 4}">訂單不成立</c:when>
				    </c:choose>
                </li>
                <c:if test="${not empty BidOrder.receiveAddress}">
				    <li class="list-group-item">收件地址：<span id="reservePrice">${BidOrder.receiveAddress}</span></li>
				</c:if>
                <c:if test="${not empty BidOrder.receiveName}">
				    <li class="list-group-item">收件人姓名：<span id="reservePrice">${BidOrder.receiveName}</span></li>
				</c:if>
                <c:if test="${not empty BidOrder.receivePhone}">
				    <li class="list-group-item">收件手機：<span id="reservePrice">${BidOrder.receivePhone}</span></li>
				</c:if>
                <c:if test="${not empty BidOrder.remarks}">
				    <li class="list-group-item">備註：<span id="reservePrice">${BidOrder.remarks}</span></li>
				</c:if>
                <c:if test="${not empty BidOrder.buyStar}">
				    <li class="list-group-item">買家評價星數：<span id="reservePrice">${BidOrder.buyStar}</span></li>
				</c:if>
                <c:if test="${not empty BidOrder.buyerRatingDesc}">
				    <li class="list-group-item">買家評價內容：<span id="reservePrice">${BidOrder.buyerRatingDesc}</span></li>
				</c:if>
                <c:if test="${not empty BidOrder.sellStar}">
				    <li class="list-group-item">買家評價星數：<span id="reservePrice">${BidOrder.sellStar}</span></li>
				</c:if>
                <c:if test="${not empty BidOrder.sellerRatingDesc}">
				    <li class="list-group-item">買家評價內容：<span id="reservePrice">${BidOrder.sellerRatingDesc}</span></li>
				</c:if>
				<li class="list-group-item">
				    買家評價圖片:
				    <span id="imageContainer">
				        <img
				            id="bidOrderImage"
				            src="<%=request.getContextPath() %>/DBGifReader5?bidOrderId=${BidOrder.bidOrderId}&imgType=image"
				            alt="訂單評價"
				            class="img-fluid"
				            onerror="handleImageError()"
				        >
				    </span>
				</li>
            </ul>
        </div>
    </div>
</div>


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
    
    
    
    
    function handleImageError() {
        var bidOrderImage = document.getElementById("bidOrderImage");
        var imageContainer = document.getElementById("imageContainer");

        // 隱藏圖片元素
        bidOrderImage.style.display = "none";

        // 顯示替代文字
        var noImageText = document.createElement("span");
        noImageText.textContent = "無圖片";
        imageContainer.appendChild(noImageText);
    }
</script>

</body>
</html>
