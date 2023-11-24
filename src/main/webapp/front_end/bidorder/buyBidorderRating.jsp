<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="headerHTML"></div>
	<script
		src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>
	<div id="hy_con">
		<div id="con_lf">
		<br><br>
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
				<p class="rh_title">買家評價</p>




    <div class="form-container">
		    <h2>買家評價</h2>
		    <h2>訂單編號: ${BidOrder.bidOrderId}</h2>
	
      <br>
        <form method="post" action="<%=request.getContextPath()%>/bidorder/BidOrder.do"  enctype="multipart/form-data">
            <label>買家評價星數：</label>
            <div class="stars" id="starRating">
                <span class="star" onclick="setRating(1)">☆</span>
                <span class="star" onclick="setRating(2)">☆</span>
                <span class="star" onclick="setRating(3)">☆</span>
                <span class="star" onclick="setRating(4)">☆</span>
                <span class="star" onclick="setRating(5)">☆</span>
            </div>
            <input type="hidden" name="buyStar" id="ratingValue" value="0">
            <br>
            <br>

            <label>買家評價內容：</label><br>
            <textarea name="buyerRatingDesc" rows="4" cols="50"></textarea>
 		<br><br>
            <label>上傳圖片：</label><br>
            <input type="file" name="image" id="imageInput" onchange="previewImage()">
            <div id="imagePreview"></div>
            <br>
          
			<input type="hidden" name="sellMbrId" value="${BidOrder.sellMbrId}">
			<input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
			<input type="hidden" name="buyMbrId" value="${user.mbrId}"> 
			<input type="hidden" name="action" value="buy_Rating_in"> 
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

    <script>
    function setRating(rating) {
        document.getElementById('ratingValue').value = rating;
        for (let i = 1; i <= 5; i++) {
            const star = document.getElementById('starRating').children[i - 1];
            star.classList.toggle('active', i <= rating);
        }
    }

        function previewImage() {
            const input = document.getElementById('imageInput');
            const preview = document.getElementById('imagePreview');

            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    preview.innerHTML = '<img src="' + e.target.result + '" alt="Image Preview" style="max-width:100%;">';
                };
                reader.readAsDataURL(input.files[0]);
            } else {
                preview.innerHTML = '';
            }
        }
        //插入左側連結
        $(document).ready(function () {
            // 使用 AJAX 請求加載其他內容
            $.ajax({
                url: "${pageContext.request.contextPath}/front_end/bidorder/sideBuyBidorder.jsp",
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
