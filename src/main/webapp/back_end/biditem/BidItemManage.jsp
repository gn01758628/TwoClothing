<%--suppress ELValidationInspection --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>後台競標商品查詢頁面</title>
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
    <!--Font Awesome-->
    <script src="https://kit.fontawesome.com/716afdf889.js" crossorigin="anonymous"></script>
    <!--Sweet Alert-->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    <style>

        .infoModal .product-info > div {
            display: flex;
            align-items: baseline; /* 確保內容在基線對齊 */
        }

        .infoModal .info-value {
            flex-grow: 1; /* 允許元素增長填充可用空間 */
            text-align: left; /* 文本向左對齊 */
            margin-left: 10px; /* 與左側標籤的間距 */
        }


        .infoModal .smallIMG {
            max-height: 150px; /* 縮略圖的最大高度 */
            transition: transform 0.3s ease, box-shadow 0.3s ease, border-color 0.3s ease; /* 平滑過渡效果 */
            border-radius: 50px; /* 輕微的邊角圓滑化 */
            border: 2px solid transparent; /* 初始時透明的框線 */
        }

        /* 滑鼠懸停在小圖上時的樣式 */
        .infoModal .smallIMG:hover {
            transform: scale(1.05); /* 輕微放大 */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15); /* 增加陰影 */
            border-color: #007bff; /* 改變框線顏色 */
        }

        /* 滑鼠點擊小圖時的樣式 */
        .infoModal .smallIMG:active {
            transform: scale(0.95); /* 輕微縮小 */
            box-shadow: 0 3px 6px rgba(0, 0, 0, 0.2); /* 增加陰影 */
        }

        /* 縮略圖容器的設定 */
        .infoModal .thumbnails-container {
            display: flex;
            justify-content: center; /* 縮略圖在容器中央對齊 */
            gap: 50px; /* 縮略圖之間的間隙 */
        }

        /* 縮略圖的對齊設定 */
        .infoModal .thumbnail-left {
            margin-right: auto; /* 左邊縮略圖向右邊界靠攏 */
        }

        .infoModal .thumbnail-right {
            margin-left: auto; /* 右邊縮略圖向左邊界靠攏 */
        }
    </style>
</head>
<body>

<!--商品詳情模態框-->
<div class="modal fade" id="bidItemInfoModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body infoModal mb-3">
                <div class="container pt-5">
                    <div class="row">
                        <div class="col-md-5">
                            <!-- 圖片 -->
                            <div class="thumbnails-container">
                                <div class="thumbnail-right">
                                    <img src="${pageContext.request.contextPath}/images/clothing/clothing-1.jpg"
                                         class="img-fluid smallIMG" alt="商品主圖">
                                </div>
                                <div class="thumbnail-left">
                                    <img src="${pageContext.request.contextPath}/images/clothing/clothing-2.jpg"
                                         class="img-fluid smallIMG" alt="商品附圖">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-7">
                            <!-- 商品資訊 -->
                            <div class="product-info">
                                <div class="row mb-3">
                                    <div class="col-md-8 d-flex align-items-center">
                                        <h2>商品名稱</h2>
                                    </div>
                                </div>


                                <div class="mb-3 d-flex align-items-baseline">
                                    <div class="fw-bold min-width">新舊程度：</div>
                                    <div class="info-value">新舊程度</div>
                                    <div class="fw-bold min-width">商品尺寸：</div>
                                    <div class="info-value">商品尺寸</div>
                                </div>

                                <div class="mb-3 d-flex align-items-baseline">
                                    <div class="fw-bold min-width">商品詳述：</div>
                                    <div class="info-value">
                                        <pre>商品詳述</pre>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
            </div>
        </div>
    </div>
</div>

<div class="container mt-5 ">
    <div class="text-center mb-4">
        <p>根據你已知的資訊來幫助搜索，請提供你所掌握的資訊：</p>
    </div>
    <form action="${pageContext.request.contextPath}/back_end/servlet/biditem/find" method="GET" id="searchForm">
        <div class="row justify-content-center">
            <div class="col-md-4 mb-3">
                <label for="bidItemId" class="form-label">競標商品編號</label>
                <input type="text" class="form-control" id="bidItemId" name="bidItemId">
            </div>
            <div class="col-md-4 mb-3">
                <label for="bidName" class="form-label">競標商品名稱</label>
                <input type="text" class="form-control" id="bidName" name="bidName">
            </div>
            <div class="col-md-4 mb-3">
                <label for="bidStatus" class="form-label">競標商品狀態</label>
                <select class="form-select" id="bidStatus" name="bidStatus">
                    <option value="" selected>選擇狀態</option>
                    <option value="0">待審核</option>
                    <option value="1">已過審</option>
                    <option value="2">結標</option>
                    <option value="3">流標</option>
                    <option value="4">上架中</option>
                    <option value="5">刪除</option>
                    <option value="6">被下架</option>
                </select>
            </div>
            <div class="col-md-3 mb-3">
                <label for="mbrId" class="form-label">會員編號</label>
                <input type="text" class="form-control" id="mbrId" name="mbrId">
            </div>
            <div class="col-md-3 mb-3">
                <label for="email" class="form-label">會員帳號</label>
                <input type="text" class="form-control" id="email" name="email">
            </div>
            <div class="col-md-3 mb-3">
                <label for="mbrName" class="form-label">會員姓名</label>
                <input type="text" class="form-control" id="mbrName" name="mbrName">
            </div>
            <div class="col-md-3 mb-3">
                <label for="empId" class="form-label">審核人員</label>
                <select class="form-select" id="empId" name="empId">
                    <option value="" selected>選擇員工</option>
                    <c:forEach var="employee" items="${employeeList}">
                        <option value="${employee.empId}">${employee.empId}：${employee.empName}</option>
                    </c:forEach>>
                </select>
            </div>
        </div>

        <div class="d-grid col-12 mt-3">
            <button type="submit" class="btn btn-primary">搜尋</button>
        </div>
    </form>

    <hr class="my-4">

</div>


<c:if test="${bidItemList != null}">
    <c:if test="${empty bidItemList}">
        <div class="container mt-3 text-center">
            <h1>沒有符合條件的資料</h1>
        </div>
    </c:if>
    <c:if test="${bidItemList[0] != null}">
        <div class="container mt-5 ">
            <div class="row">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th class="text-center align-middle">狀態</th>
                            <th class="text-center align-middle">商品縮圖</th>
                            <th class="text-center align-middle">商品名稱</th>
                            <th class="text-center align-middle">所屬會員編號</th>
                            <th class="text-center align-middle">審核員工</th>
                            <th class="text-center align-middle">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="bidItem" items="${bidItemList}">
                            <tr>
                                <td class="text-center align-middle td-bidStatus">${bidStatusMap[bidItem.bidStatus]}</td>
                                <td class="text-center align-middle">
                                    <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=1"
                                         alt="${bidItem.bidName}" width="100" class="img-thumbnail"></td>
                                <td class="text-center align-middle">${bidItem.bidName}</td>
                                <td class="text-center align-middle text-wrap">${bidItem.mbrId}<br>${membersMap[bidItem.bidItemId].email}<br>${membersMap[bidItem.bidItemId].mbrName}
                                </td>
                                <td class="text-center align-middle td-empName">${employeeMap[bidItem.bidItemId].empName}</td>
                                <td class="text-center align-middle">
                                    <a href="#" class="btn btn-primary btn-sm mt-2 mb-2 bidItemInfo"
                                       data-bid-item-id="${bidItem.bidItemId}"
                                       data-bs-toggle="modal"
                                       data-bs-target="#bidItemInfoModal">
                                        商品詳情
                                    </a>
                                    <br>
                                    <c:if test="${bidItem.bidStatus == 0}">
                                        <input type="hidden" value="${bidItem.bidName}">
                                        <button class="btn btn-success btn-sm mt-2 mb-2 btn_agree">批准上架
                                        </button>
                                        <button class="btn btn-danger btn-sm mt-2 mb-2 btn_reject">拒絕上架
                                        </button>
                                        <input type="hidden" value="${bidItem.bidItemId}">
                                    </c:if>
                                    <c:if test="${bidItem.bidStatus == 4}">
                                        <input type="hidden" value="${bidItem.bidName}">
                                        <button class="btn btn-danger btn-sm mt-2 mb-2 btn_reject_enforce">強制下架
                                        </button>
                                        <input type="hidden" value="${bidItem.bidItemId}">
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
</c:if>

<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
<!--Sweet Alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>

<!--審核操作-->
<script>
    $(function () {
        // 批准上架
        $(".btn_agree").on("click", function () {
            let bidItemId = $(this).next().next().val();
            let bidItemName = $(this).prev().val();
            const td_empName = $(this).closest("tr").find(".td-empName");
            const td_bidStatus = $(this).closest("tr").find(".td-bidStatus");
            const btn_agree = $(this);
            const btn_reject = $(this).next();
            Swal.fire({
                title: "確定要批准上架嗎?\n" + "商品名稱：" + bidItemName,
                text: "一旦執行此操作，將無法撤回!",
                icon: "warning",
                showCancelButton: true,
                allowOutsideClick: false,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "批准上架",
                cancelButtonText: "取消"
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        title: "操作成功!",
                        text: "此競標商品已通過審核",
                        icon: "success"
                    });
                    // jQuery Ajax Post request
                    $.post('${pageContext.request.contextPath}/back_end/servlet/biditem/vent', {
                        result: "agree",
                        id: bidItemId,
                        message: ""
                    }, function (data) {
                        td_empName.text(data);
                        td_bidStatus.text("已過審");
                        btn_agree.remove();
                        btn_reject.remove();
                    })
                }
            });
        });
        // 拒絕上架
        $(".btn_reject").on("click", function () {
            let bidItemId = $(this).next().val();
            let bidItemName = $(this).prev().prev().val();
            const td_empName = $(this).closest("tr").find(".td-empName");
            const td_bidStatus = $(this).closest("tr").find(".td-bidStatus");
            const btn_reject = $(this);
            const btn_agree = $(this).prev();

            Swal.fire({
                title: "確定要拒絕上架嗎?\n" + "商品名稱：" + bidItemName,
                text: "一旦執行此操作，將無法撤回!",
                icon: "warning",
                showCancelButton: true,
                allowOutsideClick: false,
                confirmButtonColor: "#d33",
                cancelButtonColor: "#3085d6",
                confirmButtonText: "拒絕上架",
                cancelButtonText: "取消"
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        input: "textarea",
                        width: 600,
                        confirmButtonColor: "#d33",
                        cancelButtonColor: "#3085d6",
                        confirmButtonText: "拒絕上架",
                        cancelButtonText: "取消",
                        inputLabel: "簡單明瞭地敘述為何拒絕上架",
                        inputPlaceholder: "提供拒絕上架的理由，以幫助用戶明白我們的決定...",
                        inputAttributes: {
                            "aria-label": "Type your message here",
                            "required": "true"
                        },
                        showCancelButton: true,
                        inputValidator: (value) => {
                            if (!value) {
                                return '請注意，必須提供拒絕上架的理由才能繼續操作!'
                            }
                        }
                    }).then((result) => {
                        if (result.isConfirmed) {
                            Swal.fire({
                                title: "操作成功!",
                                text: "此競標商品已被拒絕上架",
                                icon: "success"
                            });

                            // jQuery Ajax Post request
                            $.post('${pageContext.request.contextPath}/back_end/servlet/biditem/vent', {
                                result: "reject",
                                id: bidItemId,
                                message: result.value
                            }, function (data) {
                                td_empName.text(data);
                                td_bidStatus.text("被下架");
                                btn_agree.remove();
                                btn_reject.remove();
                            })
                        }
                    });
                }
            });
        });
        // 強制下架
        $(".btn_reject_enforce").on("click", function () {
            let bidItemId = $(this).next().val();
            let bidItemName = $(this).prev().val();
            const td_empName = $(this).closest("tr").find(".td-empName");
            const td_bidStatus = $(this).closest("tr").find(".td-bidStatus");
            const btn_reject_enforce = $(this);

            Swal.fire({
                title: "確定要強制上架嗎?\n" + "商品名稱：" + bidItemName,
                text: "一旦執行此操作，將無法撤回!",
                icon: "warning",
                showCancelButton: true,
                allowOutsideClick: false,
                confirmButtonColor: "#d33",
                cancelButtonColor: "#3085d6",
                confirmButtonText: "強制下架",
                cancelButtonText: "取消"
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        input: "textarea",
                        width: 600,
                        confirmButtonColor: "#d33",
                        cancelButtonColor: "#3085d6",
                        confirmButtonText: "強制下架",
                        cancelButtonText: "取消",
                        inputLabel: "簡單明瞭地敘述為何強制下架",
                        inputPlaceholder: "提供下架的理由，以幫助用戶明白我們的決定...",
                        inputAttributes: {
                            "aria-label": "Type your message here",
                            "required": "true"
                        },
                        showCancelButton: true,
                        inputValidator: (value) => {
                            if (!value) {
                                return '請注意，必須提供強制下架的理由才能繼續操作!'
                            }
                        }
                    }).then((result) => {
                        if (result.isConfirmed) {
                            Swal.fire({
                                title: "操作成功!",
                                text: "此競標商品已被強制下架",
                                icon: "success"
                            });

                            // jQuery Ajax Post request
                            $.post('${pageContext.request.contextPath}/back_end/servlet/biditem/vent', {
                                result: "rejectEnforce",
                                id: bidItemId,
                                message: result.value
                            }, function (data) {
                                td_empName.text(data);
                                td_bidStatus.text("被下架");
                                btn_reject_enforce.remove();
                            })
                        }
                    });
                }
            });

        })
    });
</script>
<!--儲存搜尋條件-->
<script>
    $(document).ready(function () {
        $("#searchForm").submit(function (event) {
            // 獲取表單的每一個輸入值
            const bidNameValue = $("#bidName").val();
            const mbrNameValue = $("#mbrName").val();
            const emailValue = $("#email").val();
            const mbrIdValue = $("#mbrId").val();
            const bidItemIdValue = $("#bidItemId").val();
            const bidStatusValue = $("#bidStatus").val();
            const empIdValue = $("#empId").val();

            // 利用物件儲存表單輸入值
            const formData = {
                bidNameValue,
                mbrNameValue,
                emailValue,
                mbrIdValue,
                bidItemIdValue,
                bidStatusValue,
                empIdValue
            };

            // 將物件轉化為JSON並儲存在sessionStorage(瀏覽器關閉就消失)
            sessionStorage.setItem("formData", JSON.stringify(formData));


            // 1. 不可以全部为空
            if (
                bidNameValue === "" &&
                mbrNameValue === "" &&
                emailValue === "" &&
                mbrIdValue === "" &&
                bidItemIdValue === "" &&
                bidStatusValue === "" &&
                empIdValue === ""
            ) {
                Swal.fire("搜尋條件出現錯誤", "至少填寫一項搜尋條件", "error");
                event.preventDefault();
            }

            // 2. 如果有會員編號，則必須是有效數字
            if (mbrIdValue !== "" && !/^[1-9]\d*$/.test(mbrIdValue)) {
                Swal.fire("搜尋條件出現錯誤", "會員編號必須是有效數字", "error");
                event.preventDefault();
            }

            // 3. 如果有競標商品編號，則必須是有效數字
            if (bidItemIdValue !== "" && !/^[1-9]\d*$/.test(bidItemIdValue)) {
                Swal.fire("搜尋條件出現錯誤", "商品編號必須是有效數字", "error");
                event.preventDefault();
            }
        });

        // 檢查sessionStorage是否存在formData
        const storedFormData = sessionStorage.getItem("formData");
        //  Js的if判斷式不一定要塞boolean
        //      false、null、undefined、空字串、數字0或-0、NaN
        //      判斷式裡的東西等於上面,就被視為false,其餘true
        //  if (storedFormData) 與  if (storedFormData !== null) 等價
        if (storedFormData) {
            const formData = JSON.parse(storedFormData);
            $("#bidName").val(formData.bidNameValue);
            $("#mbrName").val(formData.mbrNameValue);
            $("#email").val(formData.emailValue);
            $("#mbrId").val(formData.mbrIdValue);
            $("#bidItemId").val(formData.bidItemIdValue);
            $("#bidStatus").val(formData.bidStatusValue);
            $("#empId").val(formData.empIdValue);
        }
    });
</script>
<!--查看商品詳情-->
<script>
    $(document).ready(function () {
        const bidItemInfo_BTN = $(".bidItemInfo");
        bidItemInfo_BTN.click(function (e) {
            e.preventDefault();
            let bidItemId = $(this).data('bid-item-id');
            // Ajax請求商品詳情
            $.get("${pageContext.request.contextPath}/back_end/servlet/biditem/findGiven", {
                bidItemId: bidItemId,
            }, function (data) {
                const bidItem = JSON.parse(data.bidItem);
                let startTime = data.startTime;
                let endTime = data.endTime;
                let mbrName = data.mbrName;
                let mbrEmail = data.mbrEmail;
                let mbrId = data.mbrId;
                let isDoubleIMG = data.isDoubleIMG;
            })
        })
    })
</script>

</body>
</html>