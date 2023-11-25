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
    <!--此頁面的CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/BidItemManage.css">
<body>

<!--商品詳情模態框-->
<div class="modal fade" id="bidItemInfoModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title bidNameModal"></h2>
            </div>
            <div class="modal-body infoModal mb-3">
                <div class="container pt-5">
                    <div class="row">
                        <div class="col-md-5">
                            <!-- 圖片 -->
                            <div class="imgContainer">
                                <div>
                                    <img src="#"
                                         class="img-fluid smallIMG" alt="商品主圖">
                                </div>
                                <div>
                                    <img src="#"
                                         class="img-fluid smallIMG" alt="商品附圖">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-7">
                            <!-- 商品資訊 -->
                            <div class="product-info">
                                <dl class="row">
                                    <dt class="col-sm-3">會員編號</dt>
                                    <dd class="col-sm-9" id="memberId"></dd>

                                    <dt class="col-sm-3">會員帳號</dt>
                                    <dd class="col-sm-9" id="memberEmail"></dd>

                                    <dt class="col-sm-3">會員名稱</dt>
                                    <dd class="col-sm-9" id="memberName"></dd>

                                    <dt class="col-sm-3">新舊程度</dt>
                                    <dd class="col-sm-9" id="gradeName"></dd>

                                    <dt class="col-sm-3">商品尺寸</dt>
                                    <dd class="col-sm-9" id="sizeName"></dd>

                                    <dt class="col-sm-3">商品類別</dt>
                                    <dd class="col-sm-9" id="tagName"></dd>

                                    <dt class="col-sm-3">起標價</dt>
                                    <dd class="col-sm-9 modalPrice" id="startPrice"></dd>

                                    <dt class="col-sm-3">底標價</dt>
                                    <dd class="col-sm-9 modalPrice" id="reservePrice"></dd>

                                    <dt class="col-sm-3">直標價</dt>
                                    <dd class="col-sm-9 modalPrice" id="directPrice"></dd>

                                    <dt class="col-sm-3">開始時間</dt>
                                    <dd class="col-sm-9" id="startTime"></dd>

                                    <dt class="col-sm-3">結束時間</dt>
                                    <dd class="col-sm-9" id="endTime"></dd>
                                </dl>
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
                    <table class="table table-hover">
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
                let isDoubleIMG = data.isDoubleIMG;
                // 模態框節點
                $(".bidNameModal").text(data.bidName);
                $("#memberId").text(data.mbrId);
                $("#memberEmail").text(data.mbrEmail)
                $("#memberName").text(data.mbrName)
                $("#gradeName").text(data.grade);
                $("#sizeName").text(data.size);
                $("#tagName").text(data.tag);
                $("#startPrice").text(transformPrice(data.startPrice));
                $("#reservePrice").text(transformPrice(data.reservePrice));
                $("#directPrice").text(transformPrice(data.directPrice));
                $("#startTime").text(transformTime(data.startTime));
                $("#endTime").text(transformTime(data.endTime));
                toggleImages(!isDoubleIMG,bidItemId,"${pageContext.request.contextPath}");
            })
        })
    })

    function toggleImages(hasTwoImages, bidItemId, contextPath) {
        const imgContainer = $('.imgContainer');

        // 清除現有的圖片
        imgContainer.empty();

        // 構建第一個圖片的 URL
        let imgSrc1 = contextPath + "/ReadItemIMG/biditem?id=" + bidItemId + "&position=1";

        // 始終添加第一個圖片
        imgContainer.append('<div><img src="' + imgSrc1 + '" class="img-fluid smallIMG" alt="商品主圖"></div>');

        // 如果需要第二個圖片，則添加
        if (hasTwoImages) {
            // 構建第二個圖片的 URL
            let imgSrc2 = contextPath + "/ReadItemIMG/biditem?id=" + bidItemId + "&position=2";

            imgContainer.append('<div><img src="' + imgSrc2 + '" class="img-fluid smallIMG" alt="商品附圖"></div>');
        }
    }

    function transformPrice(price) {
        if (price === "0") {
            return "無";
        } else {
            return "$" + parseInt(price).toLocaleString();
        }
    }

    function transformTime(timeString) {
        if (timeString === "0") {
            return "無";
        } else {
            let date = new Date(parseInt(timeString));
            let year = date.getFullYear();
            let month = (date.getMonth() + 1).toString().padStart(2, '0');
            let day = date.getDate().toString().padStart(2, '0');
            let hours = date.getHours().toString().padStart(2, '0');
            let minutes = date.getMinutes().toString().padStart(2, '0');
            return year + "/" + month + "/" + day + " " + hours + ":" + minutes;
        }
    }
</script>

</body>
</html>