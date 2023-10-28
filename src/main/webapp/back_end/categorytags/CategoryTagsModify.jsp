<%--suppress ELValidationInspection --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-Hant">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改商品類別標籤</title>
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


</head>
<body>

<div class="container mt-5">
    <form action="${pageContext.request.contextPath}/back/tags/update" method="post">
        <div class="mb-3">
            <label for="tagId" class="form-label">標籤編號</label>
            <input type="text" class="form-control" id="tagId" name="tagId" value="${param.tagId}" readonly>
        </div>
        <div class="mb-3">
            <label for="superTagId" class="form-label">父類別標籤</label>
            <select class="form-select" id="superTagId" name="superTagId" required>
                <c:forEach var="tags" items="${categoryTags}">
                    <option value="${tags.tagId}" <c:if test="${tags.tagId == currentSuperTagId}">selected</c:if>>
                            ${tags.categoryName}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="categoryName" class="form-label">標籤名稱</label>
            <input type="text" class="form-control" id="categoryName" name="categoryName" value="${currentCategoryName}" required>
        </div>
        <div class="d-grid gap-2">
            <button class="btn btn-primary" type="submit">修改標籤</button>
        </div>
    </form>
</div>

<!--bootstrap5-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>


</body>
</html>