<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>賣家競標商品清單</title>
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
    <style>
        /* 大標題 */
        .bigTitle {
            display: block;
            background-color: #ffc107;
            color: #fff;
            font-size: 30px;
            padding: 10px 20px;
            border-radius: 50px;
            box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
            font-weight: bold;
            letter-spacing: 1px;
            margin-bottom: 50px;
            position: relative;
            top: 50%;
            transform: translateY(-50%);
            transition: all 0.3s ease;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
        }

        .bigTitle {
            background-image: linear-gradient(to right, #ee90aa, #f3c4d2);
        }

        .bigTitle:hover {
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.25);
            background-image: linear-gradient(to right, #f3c4d2, #ee90aa);
        }

        .bigTitle:before,
        .bigTitle:after {
            content: '★';
            color: #561729;
            font-size: 36px;
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
        }

        .bigTitle:before {
            left: -45px;
        }

        .bigTitle:after {
            right: -45px;
        }
        /*大標題*/


        /* 整體背景色 */
        body {
            background-color: #fff8fb;
        }

        /* 表格樣式 */
        #myTable {
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加陰影 */
            border-collapse: separate;
            border-spacing: 0;
            width: 100%;
            background-color: #fff; /* 設置表格背景為白色 */
        }

        #myTable th {
            background-color: #f8f8f8; /* 設置表頭背景為淺灰色 */
            color: #333; /* 表頭文字顏色 */
        }

        #myTable td, #myTable th {
            border: 1px solid #ddd; /* 增加邊框 */
            padding: 8px;
            text-align: center;
        }

        /* 每個表格行的懸停效果 */
        #myTable tr:hover {
            background-color: #f1f1f1; /* 滑鼠懸停時的背景色 */
        }

        /* 金額顯示 */
        .startPrice, .highestPrice {
            color: #e74c3c; /* 紅色字體 */
            font-weight: bold;
        }

        /* 狀態下拉選單樣式 */
        .status-dropdown {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 150px;
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
            z-index: 1;
        }

        .status-dropdown a {
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            color: #333;
        }

        .status-dropdown a:hover {
            background-color: #f1f1f1;
        }

        .sort-header:hover .status-dropdown {
            display: block;
        }

        /* 搜尋輸入框樣式 */
        #searchKeyword {
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        /* 新增商品按鈕樣式 */
        .btn-success {
            background-color: #5cb85c;
            border-color: #4cae4c;
            color: #fff;
        }

        .btn-success:hover {
            background-color: #449d44;
            border-color: #398439;
        }


    </style>
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
</head>
<body>

<!--放在最前面-->
<div class="headerHTML"></div>

<!--標題-->
<div class="container">
    <div class="row">
        <div class="col text-center">
            <h2 class="bigTitle">我的競標商品</h2>
        </div>
    </div>
</div>

<div class="container mt-3">
    <div class="row">
        <div class="col-md-6">
            <form>
                <div class="mb-3">
                    <input type="text" class="form-control" id="searchKeyword" placeholder="搜尋商品名稱...">
                </div>
            </form>
        </div>
        <div class="col-md-6 text-end">
            <a href="${pageContext.request.contextPath}/front/biditem/personal/add.check"
               class="btn btn-success">申請競標案</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-striped table-hover" id="myTable">
                <thead>
                <tr>
                    <th class="text-center align-middle sort-header">
                        狀態
                        <div class="status-dropdown">
                            <a href="#" data-status="All">全部</a>
                            <a href="#" data-status="待審核">待審核</a>
                            <a href="#" data-status="結標">已過審</a>
                            <a href="#" data-status="上架中">上架中</a>
                        </div>
                    </th>

                    <th class="text-center align-middle">商品縮圖</th>
                    <th class="text-center align-middle">商品名稱 </th>
                    <th class="text-center align-middle sort-header">起標金額  </th>
                    <th class="text-center align-middle sort-header">最高出價  </th>
                    <th class="text-center align-middle sort-header">競標開始/結束時間  </th>
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
                               class="btn btn-primary btn-sm mt-2 mb-2">商品詳情</a>
                            <br>
                            <c:if test="${bidItem.bidStatus == 4}">
                                <button class="btn btn-danger btn-sm mt-2 mb-2 endEarly">提前結束</button>
                                <input style="display: none" value="${bidItem.bidItemId}">
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!--放在最後面-->
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
        if (type === "流標" || type === "結標") {
            // jQuery Ajax Post request
            $.post('${pageContext.request.contextPath}/front/biditem/personal/endEarly', {
                bidItemId: bidItemId,
                action: type
            });
        }
    }
</script>
<!--表格排序-->
<script>
    $(document).ready(function () {
        $('.sort-header').append('<i class="fa-solid fa-sort" aria-hidden="true"></i>');

        $('.sort-header').on('click', function () {
            let icon = $(this).find('i');
            let table = $(this).parents('table').eq(0);
            let rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index(), this.asc));

            this.asc = !this.asc;

            if (this.asc) {
                icon.removeClass('fa-solid fa-sort');
                icon.removeClass('fa-solid fa-sort-up').addClass('fa-solid fa-sort-down');
                rows = rows.reverse();
            } else {
                icon.removeClass('fa-solid fa-sort');
                icon.removeClass('fa-solid fa-sort-down').addClass('fa-solid fa-sort-up');
            }

            for (var i = 0; i < rows.length; i++) {
                table.append(rows[i]);
            }
        });


        // index表示要排序的列的索引，asc表示排序方向（升序或降序）。
        function comparer(index, asc) {
            // 返回一個函數，該函數接受兩個參數：a和b，代表表格中的兩行。
            return function (a, b) {
                // 獲取兩行在指定列的值。
                let valA = getCellValue(a, index), valB = getCellValue(b, index);

                // 這裡是根據此當前這個表格來做得內容判斷
                // 判斷內容是什麼來決定如何排序
                if (valA.includes('$')) {
                    valA = parseFloat(valA.replace(/[\$,]/g, ''));
                    valB = parseFloat(valB.replace(/[\$,]/g, ''));
                } else if (Date.parse(valA)) {
                    valA = Date.parse(valA.split('<br>')[1]);
                    valB = Date.parse(valB.split('<br>')[1]);
                }

                // 返回比較結果：-1代表a小於b，1代表a大於b，0代表相等。
                return (valA < valB) ? -1 : (valA > valB) ? 1 : 0;
            };
        }

        function getCellValue(row, index) {
            return $(row).children('td').eq(index).html();
        }


        $('.status-dropdown a').click(function(e) {
            e.preventDefault();
            var status = $(this).data('status');

            $('tbody tr').each(function() {
                var rowStatus = $(this).find('.bidStatus').text().trim();
                if(status == 'All' || rowStatus == status) {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
        });

        $('#searchKeyword').on('input', function() {
            let value = $(this).val().trim().toLowerCase();

            $('#myTable tbody tr').each(function() {
                var rowName = $(this).find('td:nth-child(3)').text().toLowerCase();
                if(rowName.includes(value) || value === '') {
                    $(this).fadeIn(400);
                } else {
                    $(this).fadeOut(400);
                }
            });
        });
    });

</script>
</body>
</html>
