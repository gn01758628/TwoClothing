<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>訂單評價</title>
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

    <style>
    	
        body {
            font-family: Arial, sans-serif;
            
        }
        .form-container {
            text-align: center;
            margin-top: 50px;
        }
        label {
            display: block;
            margin: 10px 0;
        }
        .stars {
            display: inline-block;
        }
        .star {
            font-size: 24px;
            cursor: pointer;
            color: #ccc;
            transition: color 0.2s;
        }
        .star:hover,
        .star.active {
            color: #ffcc00;
        }
        select, textarea, input[type="file"] {
            margin-bottom: 10px;
        }

		
		
    </style>
        <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
    
    
</head>
<body>
<!--放在最前面-->
<div class="headerHTML"></div>
	<script
		src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>

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
				<p class="rh_title">買家評價</p>





    <div class="form-container">
		    <h2>賣家評價</h2>
		    <h2>訂單編號: ${BidOrder.bidOrderId}</h2>
		
     
        <form method="post" action="<%=request.getContextPath()%>/bidorder/BidOrder.do"  enctype="multipart/form-data">
            <label>賣家評價星數：</label>
            <div class="stars" id="starRating">
                <span class="star" onclick="setRating(1)">☆</span>
                <span class="star" onclick="setRating(2)">☆</span>
                <span class="star" onclick="setRating(3)">☆</span>
                <span class="star" onclick="setRating(4)">☆</span>
                <span class="star" onclick="setRating(5)">☆</span>
            </div>
            <input type="hidden" name="sellStar" id="ratingValue" value="0">
            <br>
            <br>

            <label>賣家評價內容：</label>
            <textarea name="sellerRatingDesc" rows="4" cols="50"></textarea>
 
         
          
			<input type="hidden" name="buyMbrId" value="${BidOrder.buyMbrId}">
			<input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
			<input type="hidden" name="sellMbrId" value="${user.mbrId}"> 
			<input type="hidden" name="action" value="sell_Rating_in"> 
            <input type="submit" value="提交評價">
            
        </form >

    </div>
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
</script>
    
    <!-- 這裡可以加入你的 JavaScript 連結 -->
    <script>
        function setRating(rating) {
            document.getElementById('ratingValue').value = rating;
            for (let i = 1; i <= 5; i++) {
                const star = document.getElementById('starRating').children[i - 1];
                star.classList.toggle('active', i <= rating);
            }
        }

        
    </script>
</body>
</html>
