<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>賣家競標商品清單</title>
    <!--bootstrap5 css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <!-- google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
</head>
<body>

<div class="container mt-3">
    <div class="row">
        <div class="col-md-6">
            <form>
                <div class="mb-3 input-group">
                    <label for="searchKeyword" class="form-label"></label>
                    <input type="text" class="form-control" id="searchKeyword" placeholder="輸入關鍵字...">
                    <button class="btn btn-primary" type="button">搜尋</button>
                </div>
            </form>
        </div>
        <div class="col-md-6 text-end">
            <a href="${pageContext.request.contextPath}/front/biditem/add" class="btn btn-outline-success">新增商品</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th class="text-center align-middle">狀態</th>
                    <th class="text-center align-middle">商品縮圖</th>
                    <th class="text-center align-middle">商品名稱</th>
                    <th class="text-center align-middle">金額</th>
                    <th class="text-center align-middle">競標開始/結束時間</th>
                    <th class="text-center align-middle">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bidItem" items="${allBidItemByMbrid}">
                    <tr>
                        <td class="text-center align-middle">${statusMap[bidItem.bidStatus]}</td>
                        <td class="text-center align-middle"><img
                                src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=1"
                                alt="${bidItem.bidName}" width="100"></td>
                        <td class="text-center align-middle">${bidItem.bidName}</td>
                        <td class="text-center align-middle">$${bidItem.startPrice}</td>
                        <td class="text-center align-middle">${bidItem.startTime}<br>${bidItem.endTime}</td>
                        <td class="text-center align-middle">
                            <a href="${pageContext.request.contextPath}/front/biditem/detail?bidItemId=${bidItem.bidItemId}" class="btn btn-outline-primary btn-sm mt-2 mb-2">商品詳情</a>
                            <br>
                            <a href="#" class="btn btn-outline-primary btn-sm mt-2 mb-2">按鈕2</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div>
                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>


<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
</body>
</html>
