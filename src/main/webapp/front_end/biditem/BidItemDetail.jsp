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

    <style>
        #bidBtn_hr {
            margin-top: -5px;
            margin-bottom: -5px;
            padding: 0px !important;
        }

        #bidHelp {
            margin-top: 10px;
            margin-bottom: 5px;
            padding: 0px !important;
            color: #6c757d;
        }

        .align-vertical {
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>

<div class="container text-center">
    <h1>${bidStatus}</h1>
</div>
<!--商品詳情-->
<div class="container">
    <div class="row mt-5">
        <div class="col-md-6">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=1"
                 alt="商品主图" class="img-fluid">
        </div>
        <div class="col-md-6">
            <ul class="list-group">
                <li class="list-group-item">商品編號：<span id="bidItemId">${bidItem.bidItemId}</span></li>
                <li class="list-group-item">商品名稱：${bidItem.bidName}</li>
                <li class="list-group-item">商品新舊程度：${grade}</li>
                <li class="list-group-item">商品尺寸：${size}</li>
                <li class="list-group-item">商品詳述：${bidItem.detail}</li>
                <li class="list-group-item">商品類別：${categoryName}</li>
                <li class="list-group-item">所屬會員：${bidItem.mbrId}</li>
                <li class="list-group-item">起標價格：$<span id="startPrice">${bidItem.startPrice}</span></li>
                <c:if test="${not empty bidItem.reservePrice}">
                    <li class="list-group-item">底標價格：$<span id="reservePrice">${bidItem.reservePrice}</span></li>
                </c:if>
                <c:if test="${not empty bidItem.directPrice}">
                    <li class="list-group-item">直購價：$<span id="directPrice">${bidItem.directPrice}</span></li>
                </c:if>
                <c:if test="${not empty bidItem.startTime}">
                    <li class="list-group-item">競標開始時間：<span id="startPrice">${timeArr[0]}</span></li>
                </c:if>
                <c:if test="${not empty bidItem.endTime}">
                    <li class="list-group-item">競標結束時間：<span id="endTime">${timeArr[1]}</span></li>
                </c:if>
                <c:if test="${not empty bidItem.empId}">
                    <li class="list-group-item">審核員工：${bidItem.empId}</li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<!--出價狀況-->
<div class="container mt-5 mb-3 text-center">
    <div class="row">
        <h1>目前出價：<span id="currentBid" style="color: red">$${bidRecordList[0].bidAmount}</span></h1>
    </div>
</div>

<div class="container mt-5 mb-3 text-center">
    <div class="row">
        <h1>最高出價者：<span id="highestBider">${mbrMap[bidRecordList[0].mbrId]}</span></h1>
    </div>
</div>

<!--出價框-->
<div class="container mt-5" style="background-color:#fff8fb;">
    <div class="row justify-content-end">
        <div class="col-12 mt-3">
            <div class="row gx-2 justify-content-end">
                <div class="col-auto">
                    <div class="input-group">
                        <span class="input-group-text">$</span>
                        <input type="number" class="form-control" placeholder="請直接輸入出價金額"
                               id="bidAmountInp">
                    </div>
                </div>

                <div class="col-auto">
                    <button type="button" class="btn btn-warning rounded-pill" id="bidBtn">我要出價</button>
                </div>
            </div>
        </div>

        <div class="col-12" id="bidHelper">
            <div class="row justify-content-end">
                <div class="col-auto">
                    <div id="bidHelp">
                        不要亂來阿
                    </div>
                </div>
            </div>
        </div>

        <div class="col-12" id="bidBtn_hr">
            <hr>
        </div>

        <div class="col-12 mb-3">
            <div class="row justify-content-end">
                <div class="col-auto align-vertical">
                    <span>直購價：</span>
                </div>
                <div class="col-auto">
                    <button type="button" class="btn btn-danger rounded-pill" id="bidDirectBtn">立即結標</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!--出價紀錄-->
<div class="container">
    <div class="row">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th class="text-center align-middle">出價者</th>
                <th class="text-center align-middle">出價金額</th>
                <th class="text-center align-middle">出價時間</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bidRecord" items="${bidRecordList}">
                <tr>
                    <td class="text-center align-middle">${mbrMap[bidRecord.mbrId]}</td>
                    <td class="text-center align-middle">${bidRecord.bidAmount}</td>
                    <td class="text-center align-middle">${bidRecord.bidTime}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>

<script>

    $(document).ready(function () {
        const bidAmountInp = $("#bidAmountInp");
        const bidItemId = $("#bidItemId");
        const startPrice = $("#startPrice").text();
        const reservePrice = $("#reservePrice").text();
        const directPrice = $("#directPrice").text();

        // 出價框獲得焦點
        bidAmountInp.on("focus", function () {
            console.log(startPrice);
            console.log(reservePrice);
            console.log(directPrice);
        })
        // 出價請求
        $("#bidBtn").on("click", function (e) {
            if (bidAmountInp.val() <= 0) {
                console.log("數字有問題,等等處理");
            } else {
                // 金額正確,發送請求
                console.log("發送請求");
                $.post('${pageContext.request.contextPath}/front/biditem/anyone/bid', {
                    bidItemId: bidItemId.text(),
                    bidAmount: bidAmountInp.val()
                }, function (data) {
                    // 请求成功时执行的回调函数
                    console.log(data);
                })
            }
        })

    });

</script>
</body>
</html>
