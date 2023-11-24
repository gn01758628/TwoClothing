<%--suppress ELValidationInspection --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-Hant">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>商品類別標籤清單</title>
    <!--bootstrap5-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <!--google fonts-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">
    <style>
        *:not([class^="fa-"]) {
            font-family: 'Noto Sans TC', sans-serif !important;
        }
    </style>
    <!--Font Awesome-->
    <script src="https://kit.fontawesome.com/716afdf889.js" crossorigin="anonymous"></script>
    <!--此頁面的CSS-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/CategoryTagsList.css">
</head>
<body>

<div class="container mt-5">
    <table class="table">
        <thead>
        <tr>
            <th class="text-center align-middle sort-header">標籤編號 </th>
            <th class="text-center align-middle">
                父類別標籤
                <i class="fa-solid fa-ellipsis-vertical"></i>
                <div class="category-dropdown">
                    <a href="#" id="showAll">全部顯示</a>
                    <c:forEach var="tags" items="${haveSubTagsList}">
                        <a href="#" class="dropdown-item">${tags.categoryName}</a>
                    </c:forEach>
                </div>
            </th>
            <th class="text-center align-middle">類別名稱</th>
            <th class="text-center align-middle sort-header">員工編號 </th>
            <th class="text-center align-middle">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tags" items="${applicationScope.categoryTags}" begin="1">
            <tr class="tags_data">
                <td class="text-center align-middle">${tags.tagId}</td>
                <td class="text-center align-middle">${tagsName[tags.superTagId]}</td>
                <td class="text-center align-middle">${tags.categoryName}</td>
                <td class="text-center align-middle">${tags.empId}：${empName[tags.empId]}</td>
                <td class="text-center align-middle">
                    <a href="${pageContext.request.contextPath}/back_end/servlet/categoryTags/modify?tagId=${tags.tagId}"
                       class="btn btn-success btn-sm mt-2 mb-2">修改</a>
                    <button type="button" class="btn btn-info btn-sm mt-2 mb-2">詳情</button>
                </td>
            </tr>
            <tr class="tags_path" style="display: none">
                <td class="text-center align-middle" colspan="5"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container">
    <div class="row">
        <a href="${pageContext.request.contextPath}/back_end/categorytags/CategoryTagsAdd.jsp"
           class="btn btn-primary btn-lg">新增標籤</a>
    </div>
</div>


<!--bootstrap5-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>

<script>
    const categoryData = [
        {id: 1, name: "所有種類", parentId: ""},
        <c:forEach var="tags" items="${applicationScope.categoryTags}" begin="1">
        {id:${tags.tagId}, name: '${tags.categoryName}', parentId:${tags.superTagId}},
        </c:forEach>
    ];

    // 根據選項的ID,獲得完整的路徑
    function getFullCategoryName(categoryId, data) {
        const category = data.find((item) => item.id === categoryId);
        if (category.parentId !== "") {
            const parentCategory = getFullCategoryName(category.parentId, data);
            return parentCategory + '　➽　' + category.name;
        }
        return category.name;
    }

    $(document).ready(function () {

        $(".tags_data button").click(function () {
            let tagId = Number($(this).closest("tr").find('td:first').text());
            const tags_path = $(this).closest("tr").next("tr")
            tags_path.find("td").text(getFullCategoryName(tagId, categoryData));
            tags_path.toggle();
        })


        // 排序功能
        const tagsInfo = $(".tags_path");
        // 添加排序圖標
        $('.sort-header').append('<i class="fa-solid fa-sort" aria-hidden="true"></i>')

        // 綁定點擊事件
        $('.sort-header').on('click', function () {
            tagsInfo.hide();
            let icon = $(this).find('i');
            let table = $(this).parents('table').eq(0);
            let rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()));

            this.asc = !this.asc;
            rows = rows.reverse();
            console.log(rows);

            if (!this.asc) {
                icon.removeClass('fa-solid fa-sort');
                icon.removeClass('fa-solid fa-sort-up').addClass('fa-solid fa-sort-down');
            } else {
                icon.removeClass('fa-solid fa-sort');
                icon.removeClass('fa-solid fa-sort-down').addClass('fa-solid fa-sort-up');
            }

            for (let i = 0; i < rows.length; i++) {
                table.append(rows[i]);
            }
        });

        function comparer(index) {
            return function (a, b) {
                let valA = getCellValue(a, index), valB = getCellValue(b, index);

                // 標籤編號和員工編號的特殊處理
                if (index === 0 || index === 3) { // 假設標籤編號是第0列，員工編號是第3列
                    valA = parseInt(valA);
                    valB = parseInt(valB);
                }

                // 進行比較
                return (valA < valB) ? -1 : (valA > valB) ? 1 : 0;
            };
        }

        function getCellValue(row, index) {
            return $(row).children('td').eq(index).text();
        }

        // 绑定点击事件到“全部显示”链接
        $('#showAll').click(function(e) {
            e.preventDefault();
            tagsInfo.hide();
            $('.tags_data').show();
        });

        // 點擊選項進行過濾
        $('.category-dropdown .dropdown-item').click(function (e) {
            e.preventDefault();
            tagsInfo.hide();
            let selectedCategory = $(this).text();
            $('tr.tags_data').each(function () {
                if ($(this).find('td:eq(1)').text() === selectedCategory || selectedCategory === '全部') {
                    $(this).show();
                } else {
                    $(this).hide();
                }
            });
        });

    })
</script>

</body>
</html>