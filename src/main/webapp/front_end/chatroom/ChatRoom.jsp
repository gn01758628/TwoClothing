<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>聊天室</title>
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


<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>

<script>
    // servlet的註冊地址
    let myPoint = "/front/chatRoomWS/${mbrId}"
    // 返回主機名+端口號
    let host = window.location.host;
    // 返回部分URL(端口號之後的全部路徑)
    //  EX:"http://www.example.com:8080/some/path"
    //  path = "/some/path"
    let path = window.location.pathname;
    // 獲取Context Path(應用程式的根路徑)
    let webCtx = path.substring(0, path.indexOf('/', 1));
    // 完整的WebSocket連接URL
    let endPointURL = "ws://" + window.location.host + webCtx + myPoint;

    $(document).ready(function () {
        // 建立WebSocket物件,傳入連接URL
        //  當建立並傳入URL時,瀏覽器會立即嘗試建立WebSocket連線
        //  連線建立後即可監聽webSocket的狀態(onopeon,onmessage,onerror,onclose)
        const webSocket = new WebSocket(endPointURL);

        webSocket.onopen = function () {
            console.log("Connect Success!");
        };

        webSocket.onmessage = function () {

        };

        webSocket.onerror = function () {

        };

        webSocket.onclose = function () {

        };



    })
</script>
</body>
</html>
