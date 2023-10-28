<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>虛擬錢包點數異動新增</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 30px;
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
    <div class="form-container">
        <form class="pointForm"  method="post" action="${pageContext.request.contextPath}/WithdrawRequest/AddWithdrawRequest">

		    <label>會員編號</label>
		    <input type="text" name="mbrId" > 
            <br>
            <label>提款金額</label>
            <input type="text" name="amount" >
            <br>
            <label>匯入帳號</label>
            <input type="text" name="mbrAccount" >
            <br>
<!--             <label>申請日期</label> -->
            <input type="hidden" name="reqDate">
            <br>
<!--             <label>申請狀態</label> -->
            <input type="hidden" name="reqStatus">
            <br>
<!--             <label>員工編號</label><br> -->
            <input type="hidden" name="empId">
            <br>
<!--             <label>審核日期</label><br> -->
            <input type="hidden" name="checkDate">
            <br>
            <label>備註</label><br>
            <input type="text" name="note">
            <br>
            
            <input type="hidden" name="choice" value="AddOne">
            <input type="submit" value="送出">
        </form>
    </div>
</body>
</html>
