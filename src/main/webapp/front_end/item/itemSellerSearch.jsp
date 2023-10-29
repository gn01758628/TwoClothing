<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>itemSellerSearch</title>
    <style>
    </style>
</head>
<body>
    <h1>商品查詢</h1>
    <a href="${pageContext.request.contextPath}/Item/searchAll?action=getAll">查詢所有商品</a>
    <br>

    <form class="form_search" method="post" action="/TwoClothing/Item/search">
        <label>商品名稱</label>
        <input type="text" name="itemNameSearch">
        <br>
        <label>商品價格(低~高)</label>
        <input type="text" name="itemPriceSearchStart"> ～ <input type="text" name="itemPriceSearchEnd">
<!--         <br> -->
<!--         <label>商品新舊程度</label> -->
<!--         <input type="text" name="itemGradeSearch"> -->
<!--         <br> -->
<!--         <label>商品尺寸</label> -->
<!--         <input type="text" name="itemSizeSearch"> -->
<!--         <br> -->

        <input type="hidden" name="choice" value="searchCondition">
        <input type="submit" value="查詢">
            <a href="${pageContext.request.contextPath}/front_end/item/ItemMain.jsp">回首頁</a>
        
    </form>


    
</body>
</html>