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
            flex-direction: column;
            align-items: center;
            margin: 50px;
            padding:30px;
            
        }

        .form-container {
            display: flex;
            justify-content: center;
            text-align: center;
        }

        .form-container input, select {
            padding: 10px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            display: block; 
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
	<h1>虛擬錢包提款申請審核</h1>
    <div class="form-container">
        <form class="pointForm"  method="post" action="${pageContext.request.contextPath}/WithdrawRequest/UpdateWithdrawRequest">

			<label>提款申請編號</label>
		    <input type="text" name="wrId" value="${withdrawRequest.wrId}" readonly> 
            <br>
		    <label>會員編號</label>
		    <input type="text" name="mbrId" value="${withdrawRequest.mbrId}" readonly> 
            <br>
            <label>提款金額</label>
            <input type="text" name="amount" value="${withdrawRequest.amount}" readonly>
            <br>
            <label>匯入帳號</label>
            <input type="text" name="mbrAccount" value="${withdrawRequest.mbrAccount}" readonly>
            <br>
            <label>申請日期</label>
            <input type="text" name="reqDate" value="${withdrawRequest.reqDate}" readonly>
            <br>
            <label>申請狀態</label>
            <select name="reqStatus">
            	<option value="${withdrawRequest.reqStatus}" selected >請選擇</option>
            	<option value="0" >待審核</option>
            	<option value="1">已通過</option>
            	<option value="2">未通過</option>
            </select>
<%--             <input type="text" name="reqStatus" value="${withdrawRequest.reqStatus}" > --%>
            <br>
            <label>員工編號</label><br>
            <input type="text" name="empId" value="${withdrawRequest.empId}">
            <br>
<!--             <label>審核日期</label><br> -->
            <input type="hidden" name="checkDate" value="${withdrawRequest.checkDate}" >

            <label>備註</label><br>
            <input type="text" name="note" value="${withdrawRequest.note}" >
            <br>
            
            <input type="hidden" name="choice" value="UpdateStatus">
            <input type="submit" value="審核送出">
        </form>
    </div>
</body>
</html>
