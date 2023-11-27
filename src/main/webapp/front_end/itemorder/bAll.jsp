<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>物流列表</title>
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
    <!--不是外部檔案也無所謂-->
    
    <style>
.itemOrderContainer {
    border: 1px solid #ddd; /* 加上框線 */
    border-radius: 8px; /* 圓角 */
    padding: 10px; /* 適度的內邊距 */
    margin-bottom: 20px; /* 底部間距 */
}

.orderDetailDiv {
    display: flex;
    flex-direction: column;
    gap: 10px; /* 元素間的垂直間距 */
}

.orderDetailItem {
    display: flex;
    width: 100%; /* 讓 orderDetailItem 與 orderDetailDiv 同寬 */
    border: 1px solid #ddd; /* 加上框線 */
    border-radius: 8px; /* 圓角 */
    padding: 10px; /* 適度的內邊距 */
}

.orderDetailItem img {
    width: 100px;
    height: 100px;
    border: 1px solid #ddd; /* 圖片加上框線 */
    border-radius: 8px; /* 圓角 */
    margin-right: 10px; /* 圖片右邊間距 */
}

.orderDetailText {
    display: flex;
    flex-direction: column;
    width: 100%; /* 讓 orderDetailText 與 orderDetailItem 同寬 */
}

.itemOrderContainer p.ms-auto {
    margin-left: auto;
}

    </style>
    
    
    
    
    
    
    <!--側邊連結css-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">

    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">


</head>
<body>
<div class="headerHTML"></div>

<div id="hy_con">
<div id="con_lf">
<!--=============================================插入連結的地方-->

</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>

<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 
<c:forEach var="entry" items="${itemOrderMap}">
    <c:set var="itemOrder" value="${entry.key}" />
    <c:set var="orderDetailsList" value="${entry.value}" />

	<div class="itemOrderContainer border border-3 p-3">
    <pre>賣家編號: ${itemOrder.sellMbrId} 訂單編號: ${itemOrder.orderId}</pre>
    <a href="your_destination_url"><!-- 添加這一行 -->
        <div class="orderDetailDiv">
            <div class="orderDetailItem">
                <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${orderDetail.compositeKey.itemId}&position=1" alt="商品的第一張圖片" class="mx-auto" loading="lazy" >
                <div class="orderDetailText">
                    <pre class="productName">商品名稱 : ${orderDetail.compositeKey.itemId}</pre>
                    <pre>數量 : ${orderDetail.quantity}</pre>
                    <pre>總價 : ${orderDetail.buyingPrice}</pre>
                </div>
            </div>
            <!-- 其他 orderDetailItem 的內容... -->
        </div>
    </a><!-- 添加這一行 -->
    <p class="ms-auto">訂單金額: ${itemOrder.finalAmount}</p>
     <!-- 新增的按鈕 -->
    <div class="d-flex justify-content-end mt-3">
        <button type="button" class="btn btn-primary mx-2">按鈕1</button>
        <button type="button" class="btn btn-primary mx-2">按鈕2</button>
        <button type="button" class="btn btn-primary mx-2">按鈕3</button>
    </div>
    
</div>



</c:forEach>




<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 



</div>
</div>
</div>



<br><br><br><br><br><br><br>
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
    
    $(document).ready(function () {
        // 使用 AJAX 請求加載其他內容
        $.ajax({
            url: "${pageContext.request.contextPath}/front_end/members/sideMembers.jsp",
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