<%--suppress ELValidationInspection --%>
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
<div class="container mt-4">
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
    </div>
</div>


<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
</body>
</html>
