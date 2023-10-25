<%--suppress ALL --%>
<%--suppress JSUnusedLocalSymbols --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>賣家競標商品清單</title>
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

<div class="container">
    <div class="row mt-5">
        <div class="col-md-6">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=1" alt="商品主图" class="img-fluid">
        </div>
        <div class="col-md-6">
            <ul class="list-group">
                <li class="list-group-item">${bidItem.bidItemId}</li>
                <li class="list-group-item">${bidItem.bidName}</li>
                <li class="list-group-item">${bidItem.grade}</li>
                <li class="list-group-item">${bidItem.size}</li>
                <li class="list-group-item">${bidItem.detail}</li>
                <li class="list-group-item">${bidItem.tagId}</li>
                <li class="list-group-item">${bidItem.mbrId}</li>
                <li class="list-group-item">${bidItem.startPrice}</li>
                <li class="list-group-item">${bidItem.reservePrice}</li>
                <li class="list-group-item">${bidItem.directPrice}</li>
                <li class="list-group-item">${bidItem.startTime}</li>
                <li class="list-group-item">${bidItem.endTime}</li>
                <li class="list-group-item">${bidItem.empId}</li>
            </ul>
        </div>
    </div>
</div>



<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>

<script>
    $(document).ready(function() {
        // 設置結束時間
        const endTime = new Date('2023-10-31T12:00:00').getTime();

        const timerElement = $('#timer');

        function updateCountdown() {
            const now = new Date().getTime();
            const timeRemaining = endTime - now;

            const days = Math.floor(timeRemaining / (1000 * 60 * 60 * 24));
            const hours = Math.floor((timeRemaining % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            const minutes = Math.floor((timeRemaining % (1000 * 60 * 60)) / (1000 * 60));
            const seconds = Math.floor((timeRemaining % (1000 * 60)) / 1000);

            timerElement.text(`${days} 天 ${hours} 小时 ${minutes} 分 ${seconds} 秒`);

            if (timeRemaining <= 0) {
                timerElement.text('已结束');
            }
        }

        // 更新倒计时
        setInterval(updateCountdown, 1000);
    });
</script>
</body>
</html>
