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
    <style>
        *:not([class^="fa-"]) {
            font-family: 'Noto Sans TC', sans-serif !important;
        }
    </style>
    <!-- Font Awesome -->
    <script src="https://kit.fontawesome.com/716afdf889.js" crossorigin="anonymous"></script>
    <!--Sweet Alert-->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
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
            <a href="${pageContext.request.contextPath}/front/biditem/personal/add.check"
               class="btn btn-outline-success">新增商品</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th class="text-center align-middle">狀態</th>
                    <th class="text-center align-middle">商品縮圖</th>
                    <th class="text-center align-middle">商品名稱</th>
                    <th class="text-center align-middle">起標金額</th>
                    <th class="text-center align-middle">最高出價</th>
                    <th class="text-center align-middle">競標開始/結束時間</th>
                    <th class="text-center align-middle">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="bidItem" items="${bidItemList}">
                    <tr>
                        <td class="text-center align-middle bidStatus">${bidStatusMap[bidItem.bidStatus]}</td>
                        <td class="text-center align-middle"><img
                                src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=1"
                                alt="${bidItem.bidName}" width="100"></td>
                        <td class="text-center align-middle bidItemName">${bidItem.bidName}</td>
                        <td class="text-center align-middle startPrice">${bidItem.startPrice}</td>
                        <td class="text-center align-middle highestPrice">${currentBidMap[bidItem.bidItemId]}</td>
                        <td class="text-center align-middle">${timeMap[bidItem.bidItemId][0]}<br>${timeMap[bidItem.bidItemId][1]}
                        </td>
                        <td class="text-center align-middle">
                            <a href="${pageContext.request.contextPath}/front/biditem/anyone/detail?bidItemId=${bidItem.bidItemId}"
                               class="btn btn-outline-primary btn-sm mt-2 mb-2">商品詳情</a>
                            <br>
                            <c:if test="${bidItem.bidStatus == 4}">
                                <button class="btn btn-outline-danger btn-sm mt-2 mb-2 endEarly">提前結束</button>
                                <input style="display: none" value="${bidItem.bidItemId}">
                            </c:if>
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
<!--Sweet Alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>

<script>
    $(document).ready(function () {
        // 格式化起標金額
        $(".startPrice").each(function () {
            let value = parseFloat($(this).text());
            if (!isNaN(value)) {
                let formattedValue = "$" + value.toLocaleString();
                $(this).text(formattedValue);
            }
        });
        // 格式化最高出價
        $(".highestPrice").each(function () {
            let text = $(this).text();

            if (text.trim().length > 0) {
                $(this).text("$" + text);
            }
        });
        // 提前結束按鈕
        $(".endEarly").click(function () {
            let bidItemId = $(this).next().val();
            const highestPrice_Td = $(this).closest("tr").find(".highestPrice");
            let highestPrice = highestPrice_Td.text();
            let bidItemName = $(this).closest("tr").find(".bidItemName").text();
            const bidStatus_Td = $(this).closest("tr").find(".bidStatus");
            const endEarly_Btn = $(this);

            if (highestPrice == "") {
                Swal.fire({
                    title: "確定要提前結束嗎?\n商品名稱：" + bidItemName,
                    html: '一旦執行此操作，將無法撤回!!<br>目前無人出價，此商品將直接流標',
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#d33",
                    cancelButtonColor: "#3085d6",
                    confirmButtonText: "流標",
                    cancelButtonText: "取消"
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire("操作成功!", "此競標商品已提前流標", "success");
                        endEarly_Btn.remove();
                        bidStatus_Td.text("流標");
                        endEarlyAjax("流標", bidItemId);
                    }
                });
            } else {
                Swal.fire({
                    title: "確定要提前結束嗎?\n商品名稱：" + bidItemName,
                    html: '一旦執行此操作，將無法撤回!!<br>目前最高出價' + highestPrice + '<br>請選擇提前結標或是流標',
                    icon: "warning",
                    showDenyButton: true,
                    showCancelButton: true,
                    confirmButtonColor: "#d33",
                    denyButtonColor: "#7066E0",
                    cancelButtonColor: "#3085d6",
                    confirmButtonText: "流標",
                    denyButtonText: "結標",
                    cancelButtonText: "取消"
                }).then((result) => {
                    if (result.isConfirmed) {
                        Swal.fire("操作成功!", "此競標商品已提前流標", "success");
                        endEarly_Btn.remove();
                        bidStatus_Td.text("流標");
                        endEarlyAjax("流標", bidItemId);
                    } else if (result.isDenied) {
                        Swal.fire("操作成功!", "此競標商品已提前結標", "success");
                        endEarly_Btn.remove();
                        bidStatus_Td.text("結標");
                        endEarlyAjax("結標", bidItemId);
                    }
                });
            }
        })
    })

    function endEarlyAjax(type, bidItemId) {
        if (type === "流標") {
            console.log("流標請求");
            console.log(bidItemId);
            // jQuery Ajax Post request
            $.post('${pageContext.request.contextPath}/front/biditem/personal/endEarly', {
                bidItemId: bidItemId,
                action: type
            });
            return;
        }
        if (type === "結標") {
            console.log("結標請求");
            console.log(bidItemId);
            // jQuery Ajax Post request
            $.post('${pageContext.request.contextPath}/front/biditem/personal/endEarly', {
                bidItemId: bidItemId,
                action: type
            });
            return;
        }
    }
</script>
</body>
</html>
