<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員點數異動紀錄查詢</title>
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
        a{
        	display:inline-block;
        }
    </style>
</head>
<body>
<%-- //action="${pageContext.request.contextPath}/PointHistory/OnePointHistory" --%>
    <div class="form-container">
        <form class="pointForm"  method="post" action="${pageContext.request.contextPath}/PointHistory/OnePointHistory">
            <label>點數異動明細編號</label>
            <input type="text" name="pointId" value="">
<!--        <label>會員編號</label> -->
<!--        <input type="text" name="mbrId" value="1"> -->
            <!-- <br>
            <label>一般商品訂單編號</label>
            <input type="text" name="orderId" value="1">
            <br>
            <label>異動日期</label>
            <input type="text" name="changeDate" value="2023-10-01 09:00:00">
            <br>
            <label>異動數值</label><br>
            <input type="text" name="changeValue" value="50"> -->
            <br>
            
            <input type="hidden" name="choice" value="getOne_For_Display">
            <input type="submit" value="送出">
        </form>
    </div>
    <br>
    <a href="${pageContext.request.contextPath}/PointHistory/searchAll?choice=getAll">查詢全部</a>
	
</body>
</html>
