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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/chatroom.css">
</head>
<body>

<!-- char-area -->
<section class="message-area">
    <div class="container">
        <div class="row">
            <div class="chat-area">
                <!-- chat-list -->
                <div class="chatlist">
                    <div class="modal-dialog-scrollable">
                        <div class="modal-content">
                            <!-- 搜尋框 -->
                            <div class="chat-header mb-2">
                                <div class="msg-search">
                                    <input type="text" class="form-control" id="searchChat"
                                           placeholder="搜尋" aria-label="search">
                                </div>
                            </div>
                            <div class="modal-body">
                                <div class="chat-lists">
                                    <div class="chat-list">
                                        <a href="#" class="d-flex align-items-center">
                                            <!--放會員大頭貼-->
                                            <div class="flex-shrink-0">
                                                <img class="img-fluid mbrAvatar"
                                                     src="https://twstatic.newmobilelife.com/wp-content/uploads/2018/11/unnie1.jpg"
                                                     alt="user img"
                                                     style="height: 45px;border-radius: 50%;object-fit: cover">
                                                <!--未讀訊息-->
                                                <span class="position-absolute top-0 start-25 translate-middle badge rounded-pill bg-danger">
                                                    99+
                                                </span>
                                                <!--上線綠點-->
                                                <span class="active"></span>
                                            </div>
                                            <!-- 會員名稱 -->
                                            <div class="flex-grow-1 ms-3">
                                                <h3>♛Abigail♛</h3>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- chat-list -->

                <!-- chatbox -->
                <div class="chatbox">
                    <div class="modal-dialog-scrollable">
                        <div class="modal-content">
                            <div class="msg-head">
                                <div class="row">
                                    <div class="col-8">
                                        <div class="d-flex align-items-center ps-5">
                                            <!--切換視窗的箭頭-->
                                            <span class="chat-icon p-2">
                                                    <i class="fa-solid fa-circle-arrow-left fa-2xl"></i>
                                                </span>
                                            <div class="flex-shrink-0">
                                                <!--會員大頭貼-->
                                                <img class="img-fluid" id="mbrBigImg"
                                                     src="https://twstatic.newmobilelife.com/wp-content/uploads/2018/11/unnie1.jpg"
                                                     alt="user img"
                                                     style="height: 90px;border-radius: 50%;object-fit: cover">
                                            </div>
                                            <div class="flex-grow-1 ms-3">
                                                <!--會員名稱-->
                                                <h1 id="mbrBigName">♛Abigail♛</h1>
                                            </div>
                                            <!--當前對話框的對方會員ID-->
                                            <input type="hidden" id="currentPartnerId">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!--訊息-->
                            <div class="modal-body">
                                <div class="msg-body">
                                    <ul>
                                        <li class="sender">
                                            <p>哈囉你好嗎?</p>
                                            <span class="time">10:06 am</span>
                                        </li>
                                        <li class="repaly">
                                            <p>我不是很好</p>
                                            <span class="time">10:20 am</span>
                                        </li>
                                        <li class="sender">
                                            <p>喔是嗎?</p>
                                            <span class="time">10:06 am</span>
                                        </li>
                                        <li>
                                            <div class="divider">
                                                <h6>Today</h6>
                                            </div>
                                        </li>
                                        <li class="repaly">
                                            <p>人咧?</p>
                                            <span class="time">10:36 am</span>
                                        </li>

                                    </ul>
                                </div>
                            </div>

                            <!--輸出訊息框-->
                            <div class="send-box">
                                <div class="d-flex align-items-center">
                                    <textarea class="form-control flex-grow-1" placeholder="輸入訊息..."
                                              aria-label="Message content" rows="3" id="messageInput"></textarea>
                                    <div class="ms-2" style="flex: 0 0 auto; width: 20%;">
                                        <button class="btn btn-primary w-100" type="button" id="sendButton"><i
                                                class="fa-regular fa-paper-plane"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--chatbox-->
            </div>
        </div>
    </div>
</section>
<!-- char-area -->
<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>

<script>
    // Function Place
    // 刷新ChatList
    function refreshChatList(partnerIdList, onlineList, mbrInfoMap, targetId) {
        // 清空現有的聊天列表
        $('.chat-list').empty();
        // 有沒有指定對象,有的話放在第一個
        if (targetId && mbrInfoMap[targetId]) {
            const targetInfo = mbrInfoMap[targetId];
            const targetOnlineStatus = onlineList.includes(targetId) ? 'active' : 'not-active';
            createChatListItem(targetId, targetInfo, targetOnlineStatus);
        }
        // 遍歷 partnerIdList 並依序建立內容
        partnerIdList.forEach(function (partnerId) {
            if (partnerId !== targetId) {
                const info = mbrInfoMap[partnerId];
                const onlineStatus = onlineList.includes(partnerId) ? 'active' : 'not-active';
                createChatListItem(partnerId, info, onlineStatus);
            }
        })
    }

    // 生成ChatList內容
    function createChatListItem(id, info, onlineStatus) {
        const displayName = info[0] || info[1];
        const avatarURL = "${pageContext.request.contextPath}/DBGifReader5?mbrid=" + id + "&imgType=avatar";
        const listItem = '<a href="#" class="d-flex align-items-center" id="chatList' + id + '">' +
            '<div class="flex-shrink-0">' +
            '<img class="img-fluid mbrAvatar" src="' + avatarURL + '" alt="user img" ' +
            'style="height: 45px;border-radius: 50%;object-fit: cover">' +
            '<span class="position-absolute top-0 start-25 translate-middle badge rounded-pill bg-danger">' +
            '99+' +
            '</span>' +
            '<span class="' + onlineStatus + '"></span>' +
            '</div>' +
            '<div class="flex-grow-1 ms-3">' +
            '<h3>' + displayName + '</h3>' +
            '</div>' +
            '</a>';
        $('.chat-list').append(listItem);
    }

    // 綁定事件
    function addEventListnerOnChatList() {
        $(".chat-icon").click(function () {
            $(".chatbox").removeClass('showbox');
        });

        $('.chat-list').on('click', 'a', function () {
            $(".chatbox").addClass('showbox');

            $('.msg-body ul').empty();

            const clickedItem = $(this);
            const newAvatarURL = clickedItem.find('img.mbrAvatar').attr('src');
            const newDisplayName = clickedItem.find('h3').text();
            // 添加隱藏的Id
            let currentPartnerId = clickedItem.attr('id').match(/\d+/)[0];
            $("#currentPartnerId").val(currentPartnerId);

            // 替換大頭貼圖片的 src 和會員名稱
            $('#mbrBigImg').attr('src', newAvatarURL);
            $('#mbrBigName').text(newDisplayName);
        });
    }

</script>

<script>
    // servlet的註冊地址
    let myPoint = "/front/chatRoomWS/${mbrId}/${targetId}"
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
            console.log("WebSocket連線已建立!");
        };

        webSocket.onmessage = function (event) {
            let dataObj = JSON.parse(event.data);
            // 根據type來決定要做什事情
            if ("online" === dataObj.type) {
                //TODO 更新上限狀態
            } else if ("first" === dataObj.type) {
                let targetId = dataObj.targetId;
                const partnerIdList = JSON.parse(dataObj.partnerIdList);
                const onlineList = JSON.parse(dataObj.onlineList);
                const mbrInfoMap = JSON.parse(dataObj.mbrInfoMap);
                refreshChatList(partnerIdList, onlineList, mbrInfoMap, targetId);
                addEventListnerOnChatList();
                $('.chat-list a:first-child').click();
            }

        };

        webSocket.onerror = function () {

        };

        webSocket.onclose = function () {
            webSocket.close();
            console.log("WebSocket連線已中斷!");
        };

        // 發送按鈕
        $("#sendButton").click(function () {
            const messageInput = $("#messageInput");
            let content = messageInput.val();
            let receiverId = $("#currentPartnerId").val();
            let senderId = ${mbrId};
            let timestamp = Date.now();

            if (content === "") {
                messageInput.focus();
                return;
            }
            content = content.replace(/\n/g, "<br>");

            let jsonObj = {
                "senderId":senderId,
                "receiverId":receiverId,
                "content":content,
                "timestamp":timestamp
            };

            let messageArr = ["chat",JSON.stringify(jsonObj)];

            webSocket.send(JSON.stringify(messageArr));
            console.log(JSON.stringify(messageArr));
            messageInput.val("");
            messageInput.focus();
        });
    })
</script>

</body>
</html>
