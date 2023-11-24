<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>聊天室</title>
    <!--頁籤icon-->
    <link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">
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
    <!--Sweet Alert-->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    <!--此頁面的css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/ChatRoom.css">
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">

</head>
<body>

<div class="headerHTML"></div>

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
                                    <input type="text" name="text" class="input" placeholder="搜尋" id="searchChat">
                                </div>
                            </div>
                            <div class="modal-body">
                                <div class="chat-lists">
                                    <!--動態生成chatlist-->
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
                                                     src=""
                                                     alt="user img"
                                                     style="height: 100px;border-radius: 50%;object-fit: cover"
                                                >
                                            </div>
                                            <div class="flex-grow-1 ms-3">
                                                <!--會員名稱-->
                                                <h1 id="mbrBigName"></h1>
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
                                    <!--動態生成chatbox-->
                                </div>
                            </div>

                            <button id="scrollToBottomBtn" type="button">
                                <i class="fa-solid fa-angles-down fa-beat"></i>
                            </button>

                            <!--輸出訊息框-->
                            <div class="send-box">
                                <div class="d-flex align-items-center">
                                    <textarea class="form-control flex-grow-11 m-1" placeholder="輸入訊息..."
                                              aria-label="Message content" rows="3" id="messageInput"></textarea>
                                    <div class="ms-2" style="flex: 0 0 auto; width: 20%;">
                                        <button id="sendButton">
                                            <div class="svg-wrapper-1">
                                                <div class="svg-wrapper">
                                                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                                                         width="24" height="24">
                                                        <path fill="none" d="M0 0h24v24H0z"></path>
                                                        <path fill="currentColor"
                                                              d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z"></path>
                                                    </svg>
                                                </div>
                                            </div>
                                            <span>Send</span>
                                        </button>

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

<div class="footerHTML"></div>

<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
<!--Sweet Alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
<!--JS loader-->
<script>
    $(".headerHTML").load("${pageContext.request.contextPath}/headerHTML.html", function () {
        // 保證headerHTML加載完才載入header.js
        $.getScript("${pageContext.request.contextPath}/js/chengHan/header.js");
    });

    $(".footerHTML").load("${pageContext.request.contextPath}/footerHTML.html");
</script>
<!--此頁面的js-->
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
    // 獲得當前用戶的Id
    let userId = Number(${mbrId});
    // 儲存當前對話框的Id
    let currentChatBoxId;

    $(document).ready(function () {
        /*******************************************
         *                WebSocket                *
         *******************************************/

            // 建立WebSocket物件,傳入連接URL
            //  當建立並傳入URL時,瀏覽器會立即嘗試建立WebSocket連線
            //  連線建立後即可監聽webSocket的狀態(onopeon,onmessage,onerror,onclose)
        const webSocket = new WebSocket(endPointURL);

        webSocket.onopen = function () {
            console.log("WebSocket連線已建立!");
        };

        webSocket.onmessage = function (event) {
            let dataObj = JSON.parse(event.data);
            // 接收訊息標籤:online
            // chatlist列表裡的會員有人上線
            if ("online" === dataObj.type) {
                updateOnlineStatus(dataObj.partnerId, true);
                return;
            }
            // 接收訊息標籤:offline
            // chatlist列表裡的會員有人上線
            if ("offline" === dataObj.type) {
                updateOnlineStatus(dataObj.partnerId, false);
                return;
            }
            // 接收訊息標籤:first
            // 用戶第一次進入聊天畫面
            if ("first" === dataObj.type) {
                let targetId = dataObj.targetId;
                const partnerIdList = JSON.parse(dataObj.partnerIdList);
                const onlineList = JSON.parse(dataObj.onlineList);
                const mbrInfoMap = JSON.parse(dataObj.mbrInfoMap);
                const unreadNumMap = JSON.parse(dataObj.unreadNumMap);
                const lastMessagesList = JSON.parse(dataObj.lastMessagesList)

                refreshChatList(partnerIdList, onlineList, mbrInfoMap, targetId, unreadNumMap, lastMessagesList);
                addEventListnerOnChatList(webSocket);
                $('.chat-lists .chat-list:first a').click();
                return;
            }
            // 接收訊息標籤:history
            // 收到chatbox所對應的歷史聊天訊息
            if ("history" === dataObj.type) {
                const historyList = JSON.parse(dataObj.historyList);
                createChatBoxContent(historyList, userId);
                return;
            }
            // 接收訊息標籤:chat
            // 有人發訊息給自己
            if ("chat" === dataObj.type) {
                const singleMsg = JSON.parse(dataObj.singleMsg);
                let partnerId = String(singleMsg.senderId);
                // 只有當自己目前對話框是對應的Id,才需要實時更新
                let currentPartnerId = $("#currentPartnerId").val();
                console.log(currentPartnerId);
                console.log(partnerId);
                console.log(userId);
                if (currentPartnerId == partnerId) {
                    // 自己對話就return
                    if (partnerId == userId) return;

                    addChatBoxSingleMsg(singleMsg, false);
                } else {
                    // chatList 對應未讀訊息數量更新
                    let currentId = "#chatList" + partnerId
                    const spanUnreadNum = $(currentId + " span.unreadNum");
                    let unreadNum = Number(spanUnreadNum.text());
                    spanUnreadNum.text(unreadNum + 1).show(100);
                }
                // 檢查chatlist是否有對應的用戶,沒有要新增
                const idArr = getAllPartnerId();
                if (!idArr.includes(partnerId)) {
                    createChatListItem(partnerId, JSON.parse(dataObj.mbrInfo), "active", false);
                    addEventListnerOnChatList(webSocket);
                    console.log($('.chat-lists .chat-list:first a'));
                    $('.chat-lists .chat-list:first').slideDown(300);
                }
                return;
            }

            // 接收訊息標籤:update
            // 發出的訊息被已讀
            if ("update" === dataObj.type) {
                let partnerId = dataObj.partnerId;
                // 只有當自己目前對話框是對應的Id,才需要實時更新
                let currentPartnerId = $("#currentPartnerId").val();
                if (currentPartnerId == partnerId) {
                    updateReadStatus();
                }
                return;
            }

            // 接收訊息標籤:updateChatList
            // 接收訊息更新chat-list
            if ("updateChatList" === dataObj.type) {
                let lastMessageInfo = JSON.parse(dataObj.lastMessageInfo);
                let messageTimestamp = formatMessageTime(lastMessageInfo.timestamp);
                let id = "#chatList" + lastMessageInfo.partnerId;
                $(id + " .last-message").text(lastMessageInfo.content);
                $(id + " .message-time").text(messageTimestamp);
            }
        };

        webSocket.onerror = function () {
            console.log("WebSocket連線出現錯誤!");
        };

        webSocket.onclose = function () {
            webSocket.close();
            console.log("WebSocket連線已中斷!");
        };

        /*******************************************
         *              Event listener             *
         *******************************************/

        const sendButton = $("#sendButton");
        const messageInput = $("#messageInput");

        // 發送按鈕,傳送對話訊息
        sendButton.click(function () {
            let content = messageInput.val();
            let receiverId = $("#currentPartnerId").val();
            let senderId = userId;
            let timestamp = Date.now();
            let mbrBigName = $("#mbrBigName").text();
            let id = "#chatList" + currentChatBoxId;

            if (mbrBigName === "") {
                Swal.fire({
                    title: "不存在聊天對象",
                    text: "請選擇聊天對象",
                    icon: "question"
                });
                messageInput.val("");
                return;
            }
            if (content === "") {
                messageInput.focus();
                return;
            }
            content = content.replace(/\n/g, "<br>");

            let jsonObj = {
                "senderId": senderId,
                "receiverId": receiverId,
                "content": content,
                "timestamp": timestamp
            };

            // 當前畫面也要更新
            addChatBoxSingleMsg(jsonObj, true);

            // 當前畫面對話列表的最後一條訊息也要更新
            $(id + " .last-message").text("你:" + messageInput.val());
            $(id + " .message-time").text(convertToHourAndMinutes());


            // 傳送訊息標籤:chat
            // 傳遞資料:訊息內容的JSON
            let messageArr = ["chat", JSON.stringify(jsonObj)];
            webSocket.send(JSON.stringify(messageArr));

            // 傳送訊息標籤:updateChatList
            // 傳遞資料:訊息內容的JSON
            let messageArr2 = ["updateChatList", JSON.stringify(jsonObj)];
            webSocket.send(JSON.stringify(messageArr2));

            messageInput.val("");
            messageInput.focus();
        });

        // 訊息輸入框鍵盤輸入
        messageInput.on("keydown", function (e) {
            // e.shiftKey檢查shift是否被按下
            if (e.key === "Enter" && !e.shiftKey) {
                sendButton.click();
                // 動畫效果
                $('#sendButton').addClass('sendButton-active');
                setTimeout(function () {
                    $('#sendButton').removeClass('sendButton-active');
                }, 150);
                e.preventDefault();
            }
        })

        // 訊息輸入框獲得焦點
        messageInput.on("focus", function () {
            // 動畫效果
            $('#sendButton').addClass('sendButton-hovered');

            // 將對方發送的訊息標記為已讀
            // 傳送訊息標籤:update
            // 傳遞資料:點擊的partnerId + 切換前的partnerId
            let messageArr = ["update", JSON.stringify([currentChatBoxId, null])];
            webSocket.send(JSON.stringify(messageArr));
        })

        // 訊息輸入框失去焦點
        messageInput.on("blur", function () {
            // 取消動畫效果
            $('#sendButton').removeClass('sendButton-hovered');
        })

        // chatlist搜尋
        $("#searchChat").on("keyup", function (e) {
            let value = $(this).val().toLowerCase();
            $(".chat-lists h3").each(function () {
                if ($(this).text().toLowerCase().indexOf(value) > -1) {
                    $(this).closest('.chat-list').fadeIn(200);
                } else {
                    $(this).closest('.chat-list').fadeOut(200);
                }
            });

            if (e.keyCode === 27) {
                $(this).val('');
                $(".chat-list").fadeIn(200);
            }
        });

        // 滾動視窗按鈕
        $('#scrollToBottomBtn').on('click', function () {
            let modalBody = $(".msg-body").closest('.modal-body');
            modalBody.animate({
                scrollTop: modalBody.prop('scrollHeight')
            }, 400);
        });

        // 監聽chatbox視窗滾動
        $(".msg-body").closest('.modal-body').scroll(function () {
            let scrollToBottomBtn = $('#scrollToBottomBtn');
            let modalBody = $(this);
            // 獲取 modal-body 的滾動高度、可視區域高度和總內容高度
            let scrollTop = modalBody.scrollTop();
            let innerHeight = modalBody.innerHeight();
            let scrollHeight = modalBody.prop('scrollHeight');
            // 計算滾動到底部的距離
            let distanceToBottom = scrollHeight - (scrollTop + innerHeight);
            // 如果距離底部的距離超過指定值，隱藏按鈕
            if (distanceToBottom > 5) { // 使用 5px 作為閾值，可以根據需要調整
                scrollToBottomBtn.slideDown(300);
            } else {
                scrollToBottomBtn.slideUp(300);
            }

        })
    })

    /*******************************************
     *              Function Place             *
     *******************************************/

// 刷新ChatList
    function refreshChatList(partnerIdList, onlineList, mbrInfoMap, targetId, unreadNumMap, lastMessagesList) {
        // 清空現有的聊天列表
        $('.chat-lists').empty();
        // 有沒有指定對象,有的話放在第一個
        if (targetId && mbrInfoMap[targetId]) {
            const targetInfo = mbrInfoMap[targetId];
            const targetOnlineStatus = onlineList.includes(Number(targetId)) ? 'active' : 'not-active';
            const targetUnreadNum = unreadNumMap[targetId] || 0;
            // 取得最後訊息內容
            const targetLastMessageInfo = lastMessagesList.find(lastMessage => lastMessage.partnerId == targetId);
            if (targetLastMessageInfo) {
                let targetLastMessage = targetLastMessageInfo.content;
                let targetLastMessageTime = formatMessageTime(targetLastMessageInfo.timestamp);
                let targetLastMessageIsSender = targetLastMessageInfo.isSender;
                createChatListItem(targetId, targetInfo, targetOnlineStatus, targetUnreadNum
                    , targetLastMessage, targetLastMessageTime, targetLastMessageIsSender, true);
            } else {
                let targetLastMessage = "";
                let targetLastMessageTime = "";
                let targetLastMessageIsSender = false;
                createChatListItem(targetId, targetInfo, targetOnlineStatus, targetUnreadNum
                    , targetLastMessage, targetLastMessageTime, targetLastMessageIsSender, true);
            }
        }
        // 遍歷 partnerIdList 並依序建立內容
        partnerIdList.forEach(function (partnerId) {
            if (partnerId != targetId) {
                const info = mbrInfoMap[partnerId];
                const onlineStatus = onlineList.includes(partnerId) ? "active" : "not-active";
                const unreadNum = unreadNumMap[partnerId] || 0;
                // 取得最後訊息內容
                const lastMessageInfo = lastMessagesList.find(lastMessage => lastMessage.partnerId == partnerId);
                if (lastMessageInfo) {
                    const lastMessage = lastMessageInfo.content;
                    const lastMessageTime = formatMessageTime(lastMessageInfo.timestamp);
                    const lastMessageIsSender = lastMessageInfo.isSender;
                    createChatListItem(partnerId, info, onlineStatus, unreadNum
                        , lastMessage, lastMessageTime, lastMessageIsSender, true);
                } else {
                    const lastMessage = "";
                    const lastMessageTime = "";
                    const lastMessageIsSender = false;
                    createChatListItem(partnerId, info, onlineStatus, unreadNum
                        , lastMessage, lastMessageTime, lastMessageIsSender, true);
                }
            }
        })
    }

    // 添加內容到ChatList
    function createChatListItem(id, info, onlineStatus, unreadNum, lastMessage, lastMessageTime, lastMessageIsSender, isFirst) {
        let displayName = info[0] || info[1];
        let avatarURL = "${pageContext.request.contextPath}/DBGifReader5?mbrid=" + id + "&imgType=avatar";
        let div = isFirst ? '<div class="chat-list">' : '<div class="chat-list" style="display: none">';
        unreadNum = isFirst ? unreadNum : '1';
        let spanUnread = '<span class="unreadNum position-absolute top-25 start-25 translate-middle badge rounded-pill bg-danger">';
        if (Number(unreadNum) <= 0) {
            spanUnread = '<span class="unreadNum position-absolute top-25 start-25 translate-middle badge rounded-pill bg-danger" style="display: none">'
        }
        lastMessage = lastMessageIsSender ? "你:" + lastMessage : lastMessage;
        const listItem = div +
            '<a href="javascript:void(0);" class="d-flex align-items-center" id="chatList' + id + '">' +
            '<div class="mbrInfo-1 flex-shrink-0">' +
            '<img class="img-fluid mbrAvatar" src="' + avatarURL + '" alt="user img"> ' +
            spanUnread +
            unreadNum +
            '</span>' +
            '<span class="' + onlineStatus + '"></span>' +
            '</div>' +
            '<div class="mbrInfo-2 flex-grow-1 ms-3">' +
            '<h3>' + displayName + '</h3>' +
            '<p class="last-message">' + lastMessage + '</p>' +
            '<span class="message-time">' + lastMessageTime + '</span>' +
            '</div>' +
            '</a>' +
            '</div>';
        if (isFirst) {
            $('.chat-lists').append(listItem);
        } else {
            $('.chat-lists').prepend(listItem);
        }
    }

    // 生成chatbox內容
    function createChatBoxContent(historyList, userId) {
        const msgBody = $(".msg-body");
        const ul = $("<ul>");
        let firstUnreadMsgElement = null;
        let badgeAdded = false;
        let lastDate = null;

        historyList.forEach(function (message, index) {
            let isSelf = message.senderId == userId;
            const li = $("<li>").addClass(isSelf ? "reply" : "sender");

            const pText = $("<p>").addClass("msg-content");
            pText.text(replaceBrWithNewline(message.content));
            li.append(pText);

            const divMsgInfo = $("<div>").addClass("msg-info");

            if (isSelf) {
                const spanRead = $("<span>")
                spanRead.text(message.isRead ? "已讀" : "未讀");
                spanRead.addClass("isRead");
                divMsgInfo.append(spanRead);
            } else if (!message.isRead && firstUnreadMsgElement === null) {
                // 抓到自己尚未讀取的第一條信息節點
                firstUnreadMsgElement = li;
                // 添加徽章
                const badgeLi = $("<li>").addClass("divider");
                const badgeSpan = $("<span>").addClass("time-divider").text("以下是您尚未讀取的訊息");
                badgeLi.append(badgeSpan);
                ul.append(badgeLi);
                badgeAdded = true;
            }

            const spanTime = $("<span>").addClass("time");
            spanTime.text(convertToAmPm(message.timestamp));
            divMsgInfo.append(spanTime);

            li.append(divMsgInfo);

            // 檢查是否需要添加日期徽章
            let messageDate = convertToDate(message.timestamp);
            if (lastDate !== messageDate && (index === 0 || lastDate !== null)) {
                const badgeLi = $("<li>").addClass("divider");
                const badgeSpan = $("<span>").addClass("time-divider").text(messageDate);
                badgeLi.append(badgeSpan);
                ul.append(badgeLi);
            }
            lastDate = messageDate;

            ul.append(li);
        })
        msgBody.empty().append(ul);

        const modalBody = msgBody.closest('.modal-body');
        // 如果存在未讀訊息,滾動到該訊息的位置,反之滾到底部
        if (firstUnreadMsgElement) {
            modalBody.scrollTop(firstUnreadMsgElement.prev().position().top);
        } else {
            modalBody.scrollTop(modalBody.prop('scrollHeight'));
        }
    }

    // 動態添加聊天訊息(對話框打開時)
    function addChatBoxSingleMsg(singleMsg, isSelf) {
        const msgBody = $(".msg-body");
        const ul = $("<ul>");
        const li = $("<li>").addClass(isSelf ? "reply" : "sender");
        const pText = $("<p>").addClass("msg-content");
        pText.text(replaceBrWithNewline(singleMsg.content));
        li.append(pText);
        const divMsgInfo = $("<div>").addClass("msg-info");
        if (isSelf) {
            const spanRead = $("<span>")
            spanRead.text("未讀");
            spanRead.addClass("isRead");
            divMsgInfo.append(spanRead);
        }
        const spanTime = $("<span>").addClass("time");
        spanTime.text(convertToAmPm(singleMsg.timestamp));
        divMsgInfo.append(spanTime);
        li.append(divMsgInfo);
        ul.append(li);
        msgBody.append(ul);

        // 滾動視窗
        const modalBody = msgBody.closest('.modal-body');
        modalBody.scrollTop(modalBody.prop('scrollHeight'));
    }

    // 更新chatlist列表上下線狀態
    function updateOnlineStatus(partnerId, isOnline) {
        let idString = "#chatList" + partnerId;
        if (isOnline) {
            const notActiveSpan = $(idString + ' span.not-active');
            notActiveSpan.toggleClass('not-active active');
        } else {
            const activeSpan = $(idString + ' span.active');
            activeSpan.toggleClass('not-active active');
        }
    }

    // 動態綁定點擊chatlist特定用戶事件
    function addEventListnerOnChatList(webSocket) {
        // 因為會重複呼叫,每次呼叫先解除之前的綁定
        $(".chat-icon").off('click');
        $('.chat-list').off('click', 'a');

        // showbox相關 = RWD視窗切換
        $(".chat-icon").click(function () {
            $(".chatbox").removeClass('showbox');
        });

        $('.chat-list').on('click', 'a', function () {
            // showbox相關 = RWD視窗切換
            $(".chatbox").addClass('showbox');

            // 清空chatbox
            $('.msg-body ul').empty();
            const clickedItem = $(this);
            const newAvatarURL = clickedItem.find('img.mbrAvatar').attr('src');
            const newDisplayName = clickedItem.find('h3').text();
            // 添加隱藏的Id
            let currentPartnerId = clickedItem.attr('id').match(/\d+/)[0];
            $("#currentPartnerId").val(currentPartnerId);

            // 替換chatbox的大頭貼圖片以及顯示名稱
            $('#mbrBigImg').attr('src', newAvatarURL);
            $('#mbrBigName').text(newDisplayName);

            // 未讀訊息歸0,並隱藏節點
            clickedItem.find('.unreadNum').text(0).hide(100);

            // 取得對應的歷史聊天紀錄
            // 傳送訊息標籤:history
            // 傳遞資料:點擊的partnerId
            let messageArr = ["history", currentPartnerId];
            webSocket.send(JSON.stringify(messageArr));

            // 將對方發送的訊息標記為已讀
            // 傳送訊息標籤:update
            // 傳遞資料:點擊的partnerId + 切換前的partnerId
            let messageArr2 = ["update", JSON.stringify([currentPartnerId, currentChatBoxId])];
            webSocket.send(JSON.stringify(messageArr2));

            // 將當前對話框的對方id儲存在全局變量
            currentChatBoxId = currentPartnerId;
        });
    }

    // 把對話框內的訊息更新為已讀
    function updateReadStatus() {
        const isRead = $(".msg-body .isRead");
        isRead.each(function () {
            $(this).text("已讀");
        })
    }

    // 返回chatlist所有partnerId的arr
    function getAllPartnerId() {
        const chatlistA = $(".chat-list a");
        let idArr = [];
        chatlistA.each(function (index, element) {
            idArr.push($(element).attr('id').replace(/\D/g, ''));
        })
        return idArr;
    }

    // 毫秒數轉 上午/下午 hh:mm
    function convertToAmPm(milliseconds) {
        let date = new Date(milliseconds);
        let hours = date.getHours();
        let minutes = date.getMinutes();
        let ampm = hours >= 12 ? '下午' : '上午';
        hours = hours % 12;
        // hours = 0 (false)
        hours = hours ? hours : 12;
        minutes = minutes < 10 ? '0' + minutes : minutes;
        return ampm + ' ' + hours + ':' + minutes;
    }

    // 毫秒數轉 hh:mm
    function convertToHourAndMinutes() {
        let currentDatetime = new Date();
        let hours = currentDatetime.getHours();
        let minutes = currentDatetime.getMinutes();
        hours = hours < 10 ? '0' + hours : hours;
        minutes = minutes < 10 ? '0' + minutes : minutes;
        return hours + ':' + minutes;
    }

    // 毫秒數轉 MM月DD日 週幾
    function convertToDate(milliseconds) {
        let date = new Date(milliseconds);
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let weekDay = ['週日', '週一', '週二', '週三', '週四', '週五', '週六'][date.getDay()];
        return month + '月' + day + '日 ' + weekDay;
    }

    // 毫秒數依據與當天日期的差距來返回不同的字串
    function formatMessageTime(timestamp) {
        let messageDate = new Date(timestamp);
        let now = new Date();
        let oneDay = 24 * 60 * 60 * 1000; // 毫秒数
        let oneYear = oneDay * 365;

        // 格式化時間 hh:mm
        const timeStr = messageDate.getHours().toString().padStart(2, '0') + ':' +
            messageDate.getMinutes().toString().padStart(2, '0');

        // 如果日期相同返回時間
        if (messageDate.toDateString() === now.toDateString()) {
            return timeStr;
        }

        // 相差一天返回 "昨天 + 時間"
        if (now - messageDate < oneDay * 2 && now.getDate() !== messageDate.getDate()) {
            return '昨天 ' + timeStr;
        }

        // 相差一天但不超過一年返回 mm/dd
        if (now - messageDate < oneYear && now.getFullYear() === messageDate.getFullYear()) {
            return messageDate.getMonth() + 1 + '/' + messageDate.getDate();
        }

        // 相差超過一年，返回 yyyy/mm/dd
        return messageDate.getFullYear() + '/' + (messageDate.getMonth() + 1) + '/' + messageDate.getDate();
    }

    // 將字串裡的<br>轉成跨行符號
    function replaceBrWithNewline(str) {
        return str.replace(/<br\s*\/?>/gi, '\n');
    }

</script>
</body>
</html>
