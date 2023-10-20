<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>新增競標商品</title>
    <!--bootstrap5 css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <!-- google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
    <!--我的css-->
    <style>

        ul, ul li {
            list-style: none;
        }

        ul {
            margin-top: 10px;
        }

        .selectable::before {
            content: "🌟";
            padding-right: 5px;
        }

        .selectable:hover::before, .selectable:hover {
            content: "🔯";
            cursor: pointer;
        }

        .non-selectable::before {
            content: "🢂";
            padding-right: 5px;
            color: #561729;
        }

        .non-selectable:hover::before, .non-selectable:hover {
            content: "🢆";
            padding-right: 0;
            cursor: not-allowed;
        }

        .modal-content {
            background-color: rgb(249, 237, 242);
            color: #00302e;
        }

        .fixed-button {
            position: sticky;
            bottom: 20px;
            right: 10px;
            float: right;
            z-index: 1000;
        }

        .text-danger {
            margin-left: 5px;
            font-size: 20px;
        }

    </style>
</head>

<body style="background-color:#fff8fb">

<div class="container">
    <div class="row justify-content-center my-5">
        <div class="col-12">
            <form action="${pageContext.request.contextPath}/servlet/front/biditem" method="post"
                  enctype="multipart/form-data">

                <div class="mb-4 border p-4 rounded-3" style="background-color:white">
                    <h2 class="mb-4">商品資訊</h2>
                    <div class="mb-3">
                        <label for="bidname" class="form-label">商品標題<span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="bidname" aria-describedby="bidnameHelp"
                               name="bidname" required maxlength="40">
                        <div id="bidnameHelp" class="form-text">精心挑選標題，提升您的商品曝光率(最大字數40)</div>
                    </div>

                    <div class="mb-3">
                        <label for="categorySelect" class="form-label">商品類別標籤<span
                                class="text-danger">*</span></label>
                        <!-- 顯示選擇的完整結構,但不往後傳 -->
                        <input type="text" class="form-control" id="categorySelect"
                               aria-describedby="categorySelectHelp" readonly>
                        <!--儲存標籤的id傳給後端-->
                        <input type="hidden" id="selectedCategoryId" name="tagId">
                        <div id="categorySelectHelp" class="form-text">選擇適當的標籤，讓更多人能找到您的商品</div>
                    </div>
                    <div class="modal fade" id="categoryModal" tabindex="-1" role="dialog"
                         aria-labelledby="categoryModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="categoryModalLabel">選擇商品類別標籤</h5>
                                </div>
                                <div class="modal-body" id="categoryTree">
                                    <!-- 類別標籤樹狀結構在這裡生成 -->
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="grade" class="form-label">商品新舊程度<span class="text-danger">*</span></label>
                        <select class="form-select" id="grade" aria-describedby="gradeHelp" name="grade" required>
                            <option value="" selected>請選擇商品新舊程度</option>
                            <option value="0">全新(沒使用過且包裝未拆,吊牌未剪)</option>
                            <option value="1">9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option>
                            <option value="2">9成新(有使用痕跡,但沒有瑕疵)</option>
                            <option value="3">8成新(有使用痕跡,少量瑕疵)</option>
                            <option value="4">5成新(有使用痕跡,明顯瑕疵)</option>
                            <option value="5">破損商品(需要修補)</option>
                        </select>
                        <div id="gradeHelp" class="form-text">請依選項說明選擇商品新舊成度</div>
                    </div>

                    <div class="mb-3">
                        <label for="size" class="form-label">商品尺寸</label>
                        <select class="form-select" id="size" name="size">
                            <option value="9" selected>如果您的商品不是以下列選項來描述尺寸，請跳過此選擇</option>
                            <option value="0">XS(含)以下</option>
                            <option value="1">S</option>
                            <option value="2">M</option>
                            <option value="3">L</option>
                            <option value="4">XL</option>
                            <option value="5">2XL</option>
                            <option value="6">3XL</option>
                            <option value="7">4XL(含)以上</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="fakedetail" class="form-label">商品簡述</label>
                        <textarea class="form-control" id="fakedetail" aria-describedby="fakedetailHelp"
                                  maxlength="250" rows="4"></textarea>
                        <input type="hidden" name="detail" id="detail">
                        <div id="fakedetaillHelp" class="form-text">
                            您可以在此處提供商品的詳細尺寸或其他相關資訊，我們將保留您輸入的格式，以便在商品頁面顯示
                            <br>
                            字數上限為250，空格和換行符也計入字數
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="image01" class="form-label">上傳商品的主圖片<span
                                class="text-danger">*</span></label>
                        <input class="form-control" type="file" id="image01" name="image01"
                               accept="image/jpeg, image/png" required aria-describedby="image01Help">
                        <div id="image01Help" class="form-text">請至少選擇一張商品圖片</div>
                    </div>

                    <div class="mb-3">
                        <label for="image02" class="form-label">上傳商品的補充圖片</label>
                        <input class="form-control" type="file" id="image02" name="image02"
                               accept="image/jpeg, image/png">
                    </div>

                </div>

                <div class="mb-4 border p-4 rounded-3" style="background-color:white">
                    <h2 class="mb-4">銷售資訊</h2>
                    <div class="mb-3">
                        <label for="startprice" class="form-labe">起標價格<span
                                class="text-danger">*</span></label>
                        <input type="text" class="form-control money" id="startprice" name="startPrice" required>
                    </div>
                    <div class="mb-3">
                        <label for="reserverprice" class="form-label">拍賣底價</label>
                        <input type="text" class="form-control money" id="reserverprice" name="reserverPrice"
                               aria-describedby="reserverpriceHelp">
                        <div id="reserverpriceHelp" class="form-text">
                            如果出價未超過您所設定的底價，競標將在截止時自動流標
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="directprice" class="form-label">立即結標價</label>
                        <input type="text" class="form-control money" id="directprice" name="directPrice"
                               aria-describedby="directpriceHelp">
                        <div id="directpriceHelp" class="form-text">
                            一旦有人出價達到您所設定的價格，競標將立即結束並成交
                        </div>
                    </div>
                </div>

                <div class="mb-4 border p-4 rounded-3" style="background-color:white">
                    <h2 class="mb-4">上架時間</h2>

                    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                        <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                        <label class="btn btn-outline-primary" for="btnradio1">通過後立刻刊登</label>

                        <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btnradio2">預約刊登</label>
                    </div>
                    <div class="form-text mb-4" id="timeDesc"></div>

                    <div class="mt-3 col-3" id="datePickerContainer" style="display: none;">
                        <input type="date" class="form-control" id="starttime" name="starttime">
                        <label for="starttime"
                               class="form-label mt-2">可預約刊日期的範圍：<br>今日後的2到10天之間</label>
                    </div>
                </div>

                <button type="submit" class="btn btn-success btn-lg fixed-button rounded-3">確定提出申請</button>
            </form>
        </div>
    </div>
</div>

<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
<!--我的js-->
<script src="${pageContext.request.contextPath}/js/chengHan/addBid.js" type="text/javascript"></script>

<!--輸入資料結構(必須在引用標籤js檔之前宣告)-->
<script>
    const categoryData = [
        {id: 2, name: "類別1", parentId: 1},
        {id: 3, name: "類別2", parentId: 1},
        {id: 4, name: "類別3", parentId: 1},
        {id: 5, name: "子類別1.1", parentId: 2},
        {id: 6, name: "子類別2.1", parentId: 3},
        {id: 7, name: "子類別3.1", parentId: 4},
        {id: 8, name: "子類別1.1.1", parentId: 5},
        {id: 9, name: "子類別1.1.2", parentId: 5},
        {id: 10, name: "子類別2.1.1", parentId: 6},
        {id: 11, name: "子類別3.1.1", parentId: 7},
        {id: 12, name: "子類別1.1.2.1", parentId: 9},
        {id: 13, name: "子類別2.1.1.1", parentId: 10},
        {id: 14, name: "子類別2.1.1.2", parentId: 10},
        {id: 15, name: "子類別1.2", parentId: 2},
    ];
</script>

<!--商品類別標籤的js-->
<script src="${pageContext.request.contextPath}/js/chengHan/addBidCategoryTags.js"></script>
</body>
</html>