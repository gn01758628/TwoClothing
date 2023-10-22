<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>測試用HTML</title>
    <style>
        /* 样式用于模态框 */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.7);
        }
        .modal-content {
            background-color: #fff;
            margin: 15% auto;
            padding: 20px;
            width: 60%;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
<h1>測試用首頁</h1>

<a href='${pageContext.request.contextPath}/front_end/members/registerLogin.jsp'>註冊</a>

</body>
</html>
