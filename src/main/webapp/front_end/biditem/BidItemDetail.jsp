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
<div class="container mt-5 p-3" style="background-color:#fff8fb;">
    <div class="row justify-content-end">
        <div class="col-12">
            <div class="row gx-2 justify-content-end">
                <div class="col-auto">
                    <div class="input-group">
                        <span class="input-group-text">$</span>
                        <input type="number" class="form-control" placeholder="請直接輸入出價金額"
                               id="bidAmountInp">
                    </div>
                </div>

                <div class="col-auto">
                    <button type="button" style="display: none" data-bs-toggle="modal" data-bs-target="#staticBackdrop"
                            id="fakeBtn">立即結標
                    </button>
                    <button type="button" class="btn btn-warning rounded-pill" id="bidBtn">我要出價</button>
                </div>
            </div>
        </div>

        <div class="col-12" id="bidHelper">
            <div class="row justify-content-end">
                <div class="col-auto">
                    <div id="bidHelp" style="display: none;">
                        最低出價金額$<span id="minRequest"></span>，全站競標出價增額皆為5%
                    </div>
                </div>
            </div>
        </div>

        <div class="col-12" id="wrongHelper">
            <div class="row justify-content-end">
                <div class="col-auto">
                    <div id="wrongMsg" class="h6" style="color: red; display: none"></div>
                </div>
            </div>
        </div>

        <c:if test="${not empty bidItem.directPrice}">
            <div class="col-12" id="bidBtn_hr">
                <hr>
            </div>
            <div class="col-12">
                <div class="row justify-content-end">
                    <div class="col-auto align-vertical">
                        <span>直購價：<span class="h3" id="directPriceHelp" style="color: red"></span></span>
                    </div>
                    <div class="col-auto">
                        <button type="button" class="btn btn-danger rounded-pill" data-bs-toggle="modal"
                                data-bs-target="#staticBackdrop" id="bidDirectBtn">立即結標
                        </button>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!-- 確認出價模態框 -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">出價確認</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>是否確定出價？出價成功後將無法取消。</p>
                <div class="table-responsive">
                    <table class="table table-hover table-no-border">
                        <tbody>
                        <tr>
                            <th scope="row" class="w-25">下標帳號</th>
                            <td>直接從session取帳號名稱</td>
                        </tr>
                        <tr>
                            <th scope="row" class="w-25">出價方式</th>
                            <td id="bidType"></td>
                        </tr>
                        <tr>
                            <th scope="row" class="w-25">出價金額</th>
                            <td id="bidAmount2"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="commitBid">確定出價</button>
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
    // 數字轉 $xxx,xxx,xxx
    function formatToMoney(number) {
        const formatter = new Intl.NumberFormat('zh-TW', {
            maximumFractionDigits: 0,
        });
        return '$' + formatter.format(number);
    }

    $(document).ready(function () {
        // 取得價格資訊
        let startPrice = $("#startPrice").text();
        let reservePrice = $("#reservePrice").text();
        let directPrice = $("#directPrice").text();
        let currentBid = Number($("#currentBid").text().replace(/[\$,]/g, ''));
        // 計算最低出價金額
        let minRequestAmount = currentBid == 0 ? Math.round(Number(startPrice) * 1.05) : Math.round(currentBid * 1.05);
        $("#minRequest").text(minRequestAmount);
        // 直購金額格式化
        const directPriceHelp = $("#directPriceHelp").text(formatToMoney(directPrice));
        // 商品編號&要操作的節點
        let bidItemId = $("#bidItemId").text();
        const bidAmountInp = $("#bidAmountInp");
        const bidHelp = $("#bidHelp");
        const wrongMsg = $("#wrongMsg");
        const fakeBtn = $("#fakeBtn");
        const bidType = $("#bidType");
        const bidAmount2 = $("#bidAmount2");

        // 出價框獲得焦點
        bidAmountInp.on("focus", function () {
            bidHelp.slideDown(300);
        })
        // 出價框失去焦點
        bidAmountInp.on("blur", function () {
            bidHelp.slideUp(300);
        })

        // 我要出價按鈕
        $("#bidBtn").on("click", function (e) {
            // 判斷輸入的金額
            if (bidAmountInp.val() < minRequestAmount) {
                wrongMsg.text("出價不能小於最低出價" + formatToMoney(minRequestAmount));
                wrongMsg.slideDown(300);
                return;
            }
            // 有直購價
            if (Number(directPrice) !== 0) {
                if (Number(bidAmountInp.val()) >= Number(directPrice)) {
                    bidAmountInp.val(directPrice);
                    bidType.text("立即結標");
                    bidAmount2.text(formatToMoney(Number(bidAmountInp.val())));
                    fakeBtn.click();
                    return;
                }
            }
            // 無直購價
            bidType.text("直接出價");
            bidAmount2.text(formatToMoney(Number(bidAmountInp.val())));
            fakeBtn.click();
        })

        // 立即結標按鈕
        $("#bidDirectBtn").on("click", function () {
            bidType.text("立即結標");
            bidAmountInp.val(directPrice);
            bidAmount2.text(formatToMoney(Number(directPrice)));
        })

        // 確認出價按鈕
        $("#commitBid").on("click", function () {
            // 金額正確,發送請求
            console.log("發送請求");
            $.post('${pageContext.request.contextPath}/front/biditem/anyone/bid', {
                bidItemId: bidItemId,
                bidAmount: bidAmountInp.val(),
                currentBid: currentBid,
                bidType: bidType.text()
            }, function (data) {
                if (data === "1") {
                    console.log("123");
                    alert("您已成功出價，將刷新頁面，以便您觀察最新出價狀況");
                    location.reload();
                }
                if (data === "2") {
                    console.log("456");
                    alert("恭喜！您已成功以直購價提前結標。請瀏覽您的訂單並繼續後續流程。");
                    location.reload();
                    // TODO 結標頁面更新
                }
            })
        })


        // 價格框按鍵反應
        $('#bidAmountInp').keydown(function (e) {
            // 輸入框為空時不能輸入0
            if ((e.keyCode == 48 || e.keyCode == 96) && $(this).val().length === 0) {
                e.preventDefault();
                return;
            }
            // 允許數字鍵、退格键、Enter键
            if (!(
                // 數字鍵
                (e.keyCode >= 48 && e.keyCode <= 57) ||
                // 鍵盤數字區的按鍵
                (e.keyCode >= 96 && e.keyCode <= 105) ||
                // 退格鍵
                e.keyCode == 8 ||
                // Enter
                e.keyCode == 13 ||
                // home、end、左右箭頭
                (e.keyCode >= 35 && e.keyCode <= 39)
            )) e.preventDefault();

            if ((e.keyCode >= 48 && e.keyCode <= 57) || (e.keyCode >= 96 && e.keyCode <= 105)) wrongMsg.slideUp(300);

            if (e.keyCode == 13) $("#bidBtn").click();
        });
    });


</script>
</body>
</html>
