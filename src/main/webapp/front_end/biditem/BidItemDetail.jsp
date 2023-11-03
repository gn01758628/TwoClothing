<%--suppress ALL --%>
<%--suppress JSUnusedLocalSymbols --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>競標商品清單</title>
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
</head>
<body>

<div class="container text-center">
    <h1>${bidStatus}</h1>
</div>

<div class="container">
    <div class="row mt-5">
        <div class="col-md-6">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=1"
                 alt="商品主图" class="img-fluid">
        </div>
        <div class="col-md-6">
            <ul class="list-group">
                <li class="list-group-item">商品編號：${bidItem.bidItemId}</li>
                <li class="list-group-item">商品名稱：${bidItem.bidName}</li>
                <li class="list-group-item">商品新舊程度：${grade}</li>
                <li class="list-group-item">商品尺寸：${size}</li>
                <li class="list-group-item">商品詳述：${bidItem.detail}</li>
                <li class="list-group-item">商品類別：${categoryName}</li>
                <li class="list-group-item">所屬會員：${bidItem.mbrId}</li>
                <li class="list-group-item">起標價格：$${bidItem.startPrice}</li>
                <c:if test="${not empty bidItem.reservePrice}">
                    <li class="list-group-item">底標價格：$${bidItem.reservePrice}</li>
                </c:if>
                <c:if test="${not empty bidItem.directPrice}">
                    <li class="list-group-item">直購價：$${bidItem.directPrice}</li>
                </c:if>
                <c:if test="${not empty bidItem.startTime}">
                    <li class="list-group-item">競標開始時間：${bidItem.startTime}</li>
                </c:if>
                <c:if test="${not empty bidItem.endTime}">
                    <li class="list-group-item">競標結束時間：${bidItem.endTime}</li>
                </c:if>
                <c:if test="${not empty bidItem.empId}">
                    <li class="list-group-item">審核員工：${bidItem.empId}</li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<div class="container mt-5 mb-5">

</div>


<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>

<script>


</script>
</body>
</html>
