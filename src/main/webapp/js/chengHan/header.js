$(document).ready(function () {
    // nav-top icon動畫
    const top_nav_link = $('#nav-top a.nav-link');
    top_nav_link.mouseenter(function () {
        $(this).find('i.fa-solid').addClass('fa-bounce');
    });

    top_nav_link.mouseleave(function () {
        $(this).find('i.fa-solid').removeClass('fa-bounce');
    });

    // 頁面進度條
    $(document).scroll(function () {
        const scrollHeight = $(document).height() - $(window).height();
        const scrollTop = $(window).scrollTop();
        const scrollProgress = (scrollTop / scrollHeight) * 100;
        $("#scroll-progress").css("width", scrollProgress + "%");
    });
    // 視窗滾動決定導覽列收縮
    let prevScrollPos = $(window).scrollTop();
    const scrollPositionThreshold = 400;
    let isNavCollapsed = false;

    $(window).scroll(function () {
        let currentScrollPos = $(window).scrollTop();
        if (prevScrollPos < currentScrollPos && currentScrollPos > scrollPositionThreshold) {
            if (!isNavCollapsed) {
                isNavCollapsed = true;
                $("#nav-top").slideUp(300);
            }
        } else {
            if (isNavCollapsed) {
                isNavCollapsed = false;
                $("#nav-top").slideDown(300);
            }
        }
        prevScrollPos = currentScrollPos;
    });
});