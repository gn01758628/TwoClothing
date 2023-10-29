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
                    <option value="2">得標</option>
                    <option value="3">流標</option>
                    <option value="4">刪除</option>
                    <option value="5">已下架</option>
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
    <h1>${bidItemList}</h1>
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
                        <tr>
                            <c:forEach var="bidItem" items="${bidItemList}">
                                <td class="text-center align-middle">${bidStatusMap[bidItem.bidStatus]}</td>
                                <td class="text-center align-middle">
                                    <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=1"
                                         alt="${bidItem.bidName}" width="100" class="img-thumbnail"></td>
                                <td class="text-center align-middle">${bidItem.bidName}</td>
                                <td class="text-center align-middle text-wrap">${bidItem.mbrId}<br>${membersMap[bidItem.bidItemId].email}<br>${membersMap[bidItem.bidItemId].mbrName}
                                </td>
                                <td class="text-center align-middle">${bidItem.empId}</td>
                                <td class="text-center align-middle">
                                    <a href="#" class="btn btn-outline-primary btn-sm mt-2 mb-2">商品詳情</a>
                                    <br>
                                    <a href="#" class="btn btn-outline-primary btn-sm mt-2 mb-2">下架商品</a>
                                </td>
                            </c:forEach>
                        </tr>
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
                alert("至少填写一项搜索条件");
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