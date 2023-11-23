// 頁面動畫,搜尋按鈕,登出警告
$(document).ready(function () {
    // 重新包裝頁面
    const bodyHTML = $("body");
    const footerHTML = $(".footerHTML");
    bodyHTML.wrapInner('<div id="content-wrap"></div>');
    $(".headerHTML").prependTo("body");
    footerHTML.appendTo("body");
    $("body, html").css({
        height: '100%',
        margin: '0'
    });
    $("#content-wrap").css({
        flex: '1'
    });
    footerHTML.css({
        flexShrink: '0'
    });
    bodyHTML.css({
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100vh'
    });
    bodyHTML.css({
        marginTop: '92px'
    });

    adjustMarginTop();
    $(window).resize(adjustMarginTop);
    function adjustMarginTop() {
        if (window.matchMedia('(max-width: 767.2px)').matches) {
            bodyHTML.css('marginTop', '184px');
        } else if (window.matchMedia('(max-width: 991.2px)').matches) {
            bodyHTML.css('marginTop', '124px');
        } else {
            bodyHTML.css('marginTop', '92px');
        }
    }

    // 頁面進度條
    $(document).scroll(function () {
        const scrollHeight = $(document).height() - $(window).height();
        const scrollTop = $(window).scrollTop();
        const scrollProgress = (scrollTop / scrollHeight) * 100;
        $("#scroll-progress").css("width", scrollProgress + "%");
    });
    // 按鈕動畫(滑鼠移入移出)
    const nav_item = $(".nav-item");

    nav_item.mouseenter(function () {
        $(this).find(".linkIcon").addClass("fa-bounce");
    })

    nav_item.mouseleave(function () {
        $(this).find(".linkIcon").removeClass("fa-bounce");
    })

    // 視窗滾動決定導覽列收縮
    let prevScrollPos = $(window).scrollTop();

    $(window).scroll(function () {
        let currentScrollPos = $(window).scrollTop();
        if (prevScrollPos < currentScrollPos) {
            $("header .needSlide").slideUp(300);
        } else {
            $("header .needSlide").slideDown(300);
        }
        prevScrollPos = currentScrollPos;
    });

    // 確保兩個搜尋框的輸入永遠相同
    const searchInput = $(".searchInput");
    searchInput.on("input", function () {
        let value = $(this).val();
        searchInput.val(value);
    })

    // 綁定搜尋按鈕
    const searchButton = $(".searchButton");
    searchButton.click(function () {
        const input = $(".searchInput");
        let inputValue = input.val().trim();
        if (inputValue !== "") {
            // 搜尋AJAX請求
            $.get('/TwoClothing/headerHelper/searchItem', {
                name: inputValue
            }, function (data) {
                if (JSON.parse(data)) {
                    // 有結果就將頁面導向,結果展示頁面
                    window.location.href = '/TwoClothing/front_end/keywordResult.html';
                } else {
                    Swal.fire({
                        title: "對不起，沒有找到符合條件的商品",
                        text: "我們這裡仍有眾多商品等待您的發掘",
                        imageUrl: "/TwoClothing/images/ItemNotFound.png",
                        imageWidth: 500,
                        imageHeight: 500,
                        imageAlt: "Custom image",
                        confirmButtonColor: "#561729",
                        confirmButtonText: "嘗試其他關鍵詞"
                    });
                }
            })
        }
    })

    // 輸入框的鍵盤監聽
    searchInput.on("keydown", function (event) {
        let keyCode = event.keyCode || event.which;
        // Enter鍵
        if (keyCode === 13) {
            searchButton.click();
        }
        // Esc鍵
        if (keyCode === 27) {
            searchInput.val("");
        }
    });

    // 登出按鈕alert
    $("#logoutButton").click(function (e) {
        e.preventDefault();
        Swal.fire({
            title: "您确定要登出吗？",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "登出",
            cancelButtonText: "取消"
        }).then((result) => {
            if (result.isConfirmed) {
                $(this).closest("form").submit();
            }
        });
    })

});

// 頁面Ajax
$(document).ready(function () {
    confirmLogin();

    // 5分鐘定期檢查
    let checkInterval = 5 * 60 * 1000;
    setInterval(function () {
        confirmLogin();
    }, checkInterval);
})

// 防止上一頁下一頁緩存 (pageshow = 頁面導航事件)
$(window).on("pageshow", function (event) {
    if (event.originalEvent.persisted) {
        confirmLogin();
    }
});

// 確認是否有登入
function confirmLogin() {
    const mbrStatus = $(".HeadMbrStatus");
    const mbrStatus_A = mbrStatus.closest("a");
    const memberDrop = $(".HeadMemberDrop");
    // 判斷有沒有登入,動態改變會員下拉式選單的內容
    // jQuery Ajax Get request
    $.get('/TwoClothing/headerHelper/loginValidate', function (data) {
        if (!data.isLogin) {
            // 沒有登入
            memberDrop.remove();
            mbrStatus.text(data.mbrStatus)
            mbrStatus_A.attr("href", "/TwoClothing/front_end/members/registerLogin.jsp")
            // 沒登入移除緩存資料
            notLogin();
            return;
        }
        if (data.isLogin) {
            // 有登入
            const mbrPoint = $(".HeadMbrPoint");
            const balance = $(".HeadBalance");
            const mbrAccount = $(".HeadMbrAccount");
            const avatar = $(".avatar");
            const memberCenter = $(".memberCenter");
            mbrStatus.text(data.mbrStatus);
            mbrPoint.text(data.mbrPoint);
            balance.text(formatToMoney(data.balance));
            mbrAccount.text(data.mbrAccount);
            avatar.attr("src", "/TwoClothing/DBGifReader5?mbrid=" + data.mbrId + "&imgType=avatar");
            memberCenter.attr("href", "/TwoClothing/members/Members.do?action=memberProfile&mbrId=" + data.mbrId);
            // 有登入才去抓其他資料
            alreadyLogin(data.mbrId);
        }
    })
}

// 登入後Ajax請求會員資料
function alreadyLogin(mbrId) {
    // 購物車相關節點
    const shoppingCar_A = $(".shoppingCar");
    shoppingCar_A.attr("href", "/TwoClothing/ItemCart/cartlist.check?goto=cart&mbrId=" + mbrId);
    const carNum_Span = $(".carNum");
    // 系統通知相關節點
    const notice_A = $(".notice");
    // TODO 瀏覽系統通知畫面還沒做
    notice_A.attr("href", "#");
    const noticeNum_Span = $(".noticeNum");
    // 即時通相關節點
    const messageNum_Span = $(".messageNum");
    // Ajax請求會員相關數據
    $.get("/TwoClothing/headerHelper/searchInfo", function (data) {
        // 購物車數量
        if (data.carNum > 0) {
            carNum_Span.text(data.carNum);
            carNum_Span.show();
        } else {
            carNum_Span.text("");
            carNum_Span.hide();
        }
        // 系統通知未讀數量
        if (data.noticeNum > 0) {
            noticeNum_Span.text(data.noticeNum);
            noticeNum_Span.show();
        } else {
            noticeNum_Span.text("");
            noticeNum_Span.hide();
        }
        // 即時通未讀數量
        if (data.messageNum > 0) {
            messageNum_Span.text(data.messageNum);
            messageNum_Span.show();
        } else {
            messageNum_Span.text("");
            messageNum_Span.hide();
        }
    });
    // 更新賣東西選項的連結
    $(".beSeller").removeAttr("href");
    const beSellerDrop = $(".beSellerDrop");
    // 更新系統通知的連結
    $(".noticeList").attr("href", "/TwoClothing/front_end/notice/noticeList.html");
}

// 移除緩存資料
function notLogin() {
    // 購物車數量
    const carNum_Span = $(".carNum");
    carNum_Span.text("");
    carNum_Span.hide();
    // 系統通知數量
    const noticeNum_Span = $(".noticeNum");
    noticeNum_Span.text("");
    noticeNum_Span.hide();
    // 即時通未讀數量
    const messageNum_Span = $(".messageNum");
    messageNum_Span.text("");
    messageNum_Span.hide();
    // 更新賣東西選項的連結
    const beSeller_A = $(".beSeller");
    beSeller_A.attr("href", "/TwoClothing/front_end/members/registerLogin.jsp");
    const beSellerDrop = $(".beSellerDrop");
    beSellerDrop.hide();
    // 更新系統通知的連結
    $(".noticeList").attr("href", "/TwoClothing/front_end/members/registerLogin.jsp");

}

// 數字轉 $xxx,xxx,xxx
function formatToMoney(number) {
    const formatter = new Intl.NumberFormat('zh-TW', {
        maximumFractionDigits: 0,
    });
    return '$' + formatter.format(number);
}