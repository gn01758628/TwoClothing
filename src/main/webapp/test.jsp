<%@page contentType="text/html;UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-Hant">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <title>Bootstrap5模板</title>
</head>

<body>
<h1>測試如何套用css跟js</h1>
<button class="btn btn-info">測試用按鈕</button>

<div class="container">
    <div class="row">
        <div class="col-2">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=1&position=1" alt="競標商品編號1的第一張圖片" class="img-fluid mx-auto">
        </div>
        <div class="col-2">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=1&position=2" alt="競標商品編號1的第二張圖片" class="img-fluid mx-auto">
        </div>
        <div class="col-2">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=1&position=1" alt="一般商品編號1的第一張圖片" class="img-fluid mx-auto">
        </div>
        <div class="col-2">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=1&position=2" alt="一般商品編號1的第二張圖片" class="img-fluid mx-auto">
        </div>
        <div class="col-2">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=99&position=2" alt="找不到圖片" class="img-fluid mx-auto">
        </div>
        <div class="col-2">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem" alt="沒有參數" class="img-fluid mx-auto">
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
</body>

</html>