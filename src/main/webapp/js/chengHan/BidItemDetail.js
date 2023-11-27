let intervalId;

$(document).ready(function () {
    if (needFormat) $(".currentBid").text(formatToMoney($(".currentBid").text()));
    if (!isLeagal) $("#staticBackdrop").remove();
    // 取得價格資訊
    let directPrice = $("#directPrice").text();
    let currentBid = Number($(".currentBid").text().replace(/[\$,]/g, ''));
    // 計算最低出價金額
    let minRequestAmount = Math.round(currentBid * 1.03);
    $("#minRequest").text(minRequestAmount);
    // 直購金額格式化
    const directPriceHelp = $("#directPriceHelp").text(formatToMoney(directPrice));
    // 商品編號&要操作的節點
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
        // 判斷商品狀態
        if (!isLeagal) {
            Swal.fire("此商品並非上架狀態", "非上架狀態的商品無法操作", "error");
            return;
        }
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
    $("#bidDirectBtn").on("click", function (e) {
        // 判斷商品狀態
        if (!isLeagal) {
            Swal.fire("此商品並非上架狀態", "非上架狀態的商品無法操作", "error");
            e.preventDefault();
            return;
        }
        bidType.text("立即結標");
        bidAmountInp.val(directPrice);
        bidAmount2.text(formatToMoney(Number(directPrice)));
    })

    // 確認出價按鈕
    $("#commitBid").on("click", function () {
        const cancelBid = $("#cancelBid");
        // 金額正確,發送請求
        $.post(contextPath + '/front/biditem/anyone/bid.check', {
            bidItemId: bidItemId,
            bidAmount: bidAmountInp.val(),
            currentBid: currentBid,
            bidType: bidType.text()
        }, function (data) {
            if (data === "0") {
                Swal.fire("您不能對自己的商品出價。", "請對其他商品進行投標，感謝配合。", "error");
                bidAmountInp.val(0);
                cancelBid.click();
                return;
            }
            if (data === "1") {
                Swal.fire("您的出價金額有誤。", "此次出價無效。請重新出價，感謝配合。", "error");
                bidAmountInp.val(0);
                cancelBid.click();
                return;
            }
            if (data === "2") {
                Swal.fire({
                    title: "您已成功出價。",
                    text: "將刷新頁面，以便您觀察最新出價狀況。",
                    icon: "success",
                }).then(() => {
                    location.reload();
                    return;
                });
            }
            if (data === "3") {
                Swal.fire({
                    title: "恭喜直購價得標！",
                    text: "請瀏覽您的訂單並繼續後續流程。",
                    icon: "success",
                }).then(() => {
                    location.reload();
                    return;
                });
            }
        }).fail(function (xhr) {
            if (xhr.status === 403) {
                window.location.href = contextPath + "/front_end/members/registerLogin.jsp";
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

    // 倒數計時
    let endTime = parseDateTime(endTimeStr);

    // 倒計時
    intervalId = setInterval(function () {
        updateCountdown(endTime);
    }, 1000);

    // 縮圖點擊
    let firstThumbnailSrc = $('.thumbnail-right img').first().attr('src');
    $('.bigIMG').attr('src', firstThumbnailSrc);


    $('.smallIMG').click(function () {
        let imgSrc = $(this).attr('src');
        $('.bigIMG').attr('src', imgSrc);
    });

    $('.thumbnail-right img').first().click();

});

// 提取時間字串中的數字部分
function parseDateTime(str) {
    let parts = str.match(/\d+/g);
    return new Date(parts[0], parts[1] - 1, parts[2], parts[3], parts[4], parts[5]);
}

// 倒數計時
function updateCountdown(endTime) {
    let now = new Date();
    let timeLeft = endTime - now; // 時間差以毫秒為單位

    // 檢查是否已經過了結束時間
    if (timeLeft < 0) {
        clearInterval(intervalId); // 停止計時器
        $('#countdown').empty();
        $('#countdown').text("此競標已結束");
        return;
    }

    // 計算天、時、分、秒
    let days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
    let hours = Math.floor((timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    let minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
    let seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);

    // 更新HTML內容
    $('#days').text(days);
    $('#hours').text(hours);
    $('#minutes').text(minutes);
    $('#seconds').text(seconds);
}

// 數字轉 $xxx,xxx,xxx
function formatToMoney(number) {
    const formatter = new Intl.NumberFormat('zh-TW', {
        maximumFractionDigits: 0,
    });
    return '$' + formatter.format(number);
}