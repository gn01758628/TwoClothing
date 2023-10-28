<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>虛擬錢包提款紀錄查詢</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            flex-direction: column;
            
        }

        .form-container {
            text-align: center;
        }

        .form-container input {
            padding: 10px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            display: block; 
            margin: 10px 0; 
        }
        

        .form-container button {
            padding: 10px 20px;
            background-color: #6d6a6a;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
    </style>
</head>
<body>
	<h1>虛擬錢包提款紀錄查詢</h1>
    <div class="form-container">
        <form class="pointForm"  method="post" action="${pageContext.request.contextPath}/WithdrawRequest/OneWithdrawRequest">
            <label>提款申請編號</label>
            <input type="text" name="wrId" value="">
            <br>
            <input type="hidden" name="choice" value="searchPK">
            <input type="submit" value="送出">
        </form>
        <form class="pointForm"  method="post" action="${pageContext.request.contextPath}/WithdrawRequest/OneWithdrawRequest">
            <label>會員編號</label>
            <input type="text" name="mbrId" value="">
            <br>
            <input type="hidden" name="choice" value="searchMbrId">
            <input type="submit" value="送出">
        </form>
<!--         員工編號、申請狀態 -->
    </div>
    <br>
    <a href="${pageContext.request.contextPath}/WithdrawRequest/searchAll?choice=getAll">查詢全部</a>
	
</body>
</html>
