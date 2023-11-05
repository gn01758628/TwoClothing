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
</head>
<body>

<div class="container mt-5 ">
    <div class="text-center mb-4">
        <p>根據你已知的資訊來幫助搜索，請提供你所掌握的資訊：</p>
    </div>
    <form action="${pageContext.request.contextPath}/back/biditem/find" method="GET" id="searchForm">
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
                                    <a href="#" class="btn btn-outline-primary btn-sm mt-2 mb-2">商品詳情</a>
                                    <br>
                                    <c:if test="${bidItem.bidStatus == 0}">
                                        <input type="hidden" value="${bidItem.bidName}">
                                        <button class="btn btn-outline-success btn-sm mt-2 mb-2 btn_agree">批准上架
                                        </button>
                                        <button class="btn btn-outline-danger btn-sm mt-2 mb-2 btn_reject"
                                                data-bs-toggle="modal" data-bs-target="#rejectReasons">拒絕上架
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
        <div class="modal fade" id="rejectReasons" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">簡單明瞭地解釋為何拒絕上架</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form class="was-validated">
                            <div class="mb-3">
                                <label for="rejectReasons_text" class="form-label"></label>
                                <textarea class="form-control is-invalid" id="rejectReasons_text"
                                          placeholder="提供拒絕上架的理由，以幫助用戶明白我們的決定。"
                                          required maxlength="50"></textarea>
                                <div class="invalid-feedback" style="display: none">
                                    請注意，必須提供拒絕上架的理由才能繼續操作
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="btn_reject_cancel">
                            取消
                        </button>
                        <button type="submit" class="btn btn-danger" id="btn_reject_sure">拒絕上架</button>
                    </div>
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

<script>
    $(function () {
        const textarea_rejectReasons = $("#rejectReasons_text");
        // 批准上架
        $(".btn_agree").on("click", function () {
            let bidItemId = $(this).next().next().val();
            let bidItemName = $(this).prev().val();
            const td_empName = $(this).closest("tr").find(".td-empName");
            const td_bidStatus = $(this).closest("tr").find(".td-bidStatus");
            const btn_agree = $(this);
            const btn_reject = $(this).next();
            if (window.confirm("確定要批准上架嗎?\n" + "商品名稱：" + bidItemName)) {
                // jQuery Ajax Post request
                $.post('${pageContext.request.contextPath}/back/biditem/vent', {
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
        // 拒絕上架
        $(".btn_reject").on("click", function () {
            let bidItemId = $(this).next().val();
            let bidItemName = $(this).prev().prev().val();
            const td_empName = $(this).closest("tr").find(".td-empName");
            const td_bidStatus = $(this).closest("tr").find(".td-bidStatus");
            const btn_reject = $(this);
            const btn_agree = $(this).prev();
            const btn_reject_sure = $("#btn_reject_sure");
            //取消上次綁定的事件
            btn_reject_sure.off();
            // 模態框的確認按鈕
            btn_reject_sure.on("click", function () {
                if (textarea_rejectReasons.val() !== "") {
                    if (window.confirm("確定要拒絕上架嗎?\n" + "商品名稱：" + bidItemName)) {
                        // jQuery Ajax Post request
                        $.post('${pageContext.request.contextPath}/back/biditem/vent', {
                            result: "reject",
                            id: bidItemId,
                            message: textarea_rejectReasons.val()
                        }, function (data) {
                            td_empName.text(data);
                            td_bidStatus.text("被下架");
                            btn_agree.remove();
                            btn_reject.remove();
                            $("#btn_reject_cancel").click();
                        })
                    } else {
                        $("#btn_reject_cancel").click();
                    }
                } else {
                    $(".invalid-feedback").slideDown(400);
                }
            })
            // 模態框的取消按鈕
            $("#btn_reject_cancel").on("click", function () {
                $(".invalid-feedback").hide();
                textarea_rejectReasons.val("");
            })
        });
    });
</script>

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
                alert("至少填寫一項搜尋條件");
                event.preventDefault();
            }

            // 2. 如果有會員編號，則必須是有效數字
            if (mbrIdValue !== "" && !/^[1-9]\d*$/.test(mbrIdValue)) {
                alert("會員編號必須是有效數字");
                event.preventDefault();
            }

            // 3. 如果有競標商品編號，則必須是有效數字
            if (bidItemIdValue !== "" && !/^[1-9]\d*$/.test(bidItemIdValue)) {
                alert("競標商品編號必須是有效數字");
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

</body>
</html>