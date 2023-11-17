$(document).ready(function () {
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
    const scrollPositionThreshold = 400;
    let isNavCollapsed = false;

    $(window).scroll(function () {
        let currentScrollPos = $(window).scrollTop();
        if (prevScrollPos < currentScrollPos && currentScrollPos > scrollPositionThreshold) {
            if (!isNavCollapsed) {
                isNavCollapsed = true;
                $("header .needSlide").slideUp(300);
            }
        } else {
            if (isNavCollapsed) {
                isNavCollapsed = false;
                $("header .needSlide").slideDown(300);
            }
        }
        prevScrollPos = currentScrollPos;
    });

    // 確保兩個搜尋框的輸入永遠相同
    const searchInput = $(".searchInput");
    searchInput.on("input", function () {
        let value = $(this).val();
        searchInput.val(value);
    })

});