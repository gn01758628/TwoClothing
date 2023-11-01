<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%-- <% //見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件) --%>
<!-- //    EmpVO empVO = (EmpVO) request.getAttribute("empVO"); -->
<%-- %> --%>
<%-- --<%= empVO==null %>--${empVO.deptno}-- <!-- line 100 --> --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料修改</title>
	<style>
	    body {
	        font-family: Arial, sans-serif;
	        background-color: #f0f0f0;
	        margin: 0;
	        padding: 0;
	    }
	
	    h3 {
	        color: #333;
	    }
	
	    table {
	        border-collapse: collapse;
	        width: 80%;
	        max-width: 600px;
	        margin: 0 auto;
	        background-color: #fff;
	        border: 1px solid #ccc;
	    }
	
	    table tr:nth-child(odd) {
	        background-color: #f2f2f2;
	    }
	
	    table th, table td {
	    	width: 0%;
	        padding: 8px;
	        text-align: left;
	    }
	
	    input[type="text"], select {
	        width: 100%;
	        padding: 8px;
	    }
	
	    input[type="submit"] {
	        background-color: #007bff;
	        color: #fff;
	        padding: 10px 20px;
	        border: none;
	        cursor: pointer;
	    }
	
	    input[type="submit"]:hover {
	        background-color: #0056b3;
	    }
	
	    a {
	        text-decoration: none;
	        color: #007bff;
	    }
	
	    a:hover {
	        text-decoration: underline;
	    }
	</style>


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body bgcolor='white'>

	<h3>資料修改:</h3>
	
	<FORM METHOD="post" ACTION="/TwoClothing/Item/Update">
	<table>
		<tr>
			<td>商品</td>
			<td><input type="TEXT" name="itemId" value="${item.itemId}"></td>
		</tr>
		<tr>
			<td>商品名稱</td>
			<td><input type="TEXT" name="itemName" value="${item.itemName}" size="20"/></td>
		</tr>
		<tr>
			<td>商品價格</td>
			<td><input type="TEXT" name="price"   value="${item.price}"></td>
		</tr>
		<tr>
			<td>新舊</td>
			<td>
			<select  name="grade">
				<option value="${item.grade}" selected>${item.grade}</option><span>請選擇商品新舊程度</span>
				<option value="0">全新(沒使用過且包裝未拆,吊牌未剪)</option>
				<option value="1">9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option>
				<option value="2">9成新(有使用痕跡,但沒有瑕疵)</option>
				<option value="3">8成新(有使用痕跡,少量瑕疵)</option>
				<option value="4">5成新(有使用痕跡,明顯瑕疵)</option>
		        <option value="5">破損商品(需要修補)</option>
			</select>
		</tr>
		<tr>
			<td>尺寸</td>
			<td>
				<select  name="size">
					<option value="${item.size}" selected>${item.size}</option> 
					<option value="9" >如果您的商品不是以下列選項來描述尺寸，請跳過此選擇</option>
					<option value="0">XS(含)以下</option>
					<option value="1">S</option>
					<option value="2">M</option>
					<option value="3">L</option>
					<option value="4">XL</option>
					<option value="5">2XL</option>
					<option value="6">3XL</option>
					<option value="7">4XL(含)以上</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>商品狀態</td>
			<td>
				<select name="itemStatus">
					<option value="${item.itemStatus}" selected>${item.itemStatus}</option>
					<option value="1">下架</option>
					<option value="2">刪除</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>數量</td>
			<td><input type="TEXT" name="quantity"  value="${item.quantity}"></td>
		</tr>
		<tr>
			<td>描述</td>
			<td><input type="TEXT" name="detail"  value="${item.detail}" size="200"/></td>
		</tr>
	
	
	</table>
	<br>
	<input type="hidden" name="forUpdate" value="update">
	<input type="hidden" name="ItemId" value="${item.itemId}">
	<input type="submit" value="送出修改"></FORM>
	<script>
	
		$(document).ready(function() {
			$("select[name='size']").each(function () {
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("XS(含以下)");
					break;
					case "1":
						$(this).text("S");
					break;
					case "2":
						$(this).text("M");
					break;
					case "3":
						$(this).text("L");
					break;
					case "4":
						$(this).text("XL");
					break;
					case "5":
						$(this).text("2XL");
					break;
					case "6":
						$(this).text("3XL");
					break;
					case "7":
						$(this).text("4XL含以上");
					break;
				}
			})
		})
		
		$(document).ready(function() {
			$("select[name='grade']").each(function () {
				console.log($("select[name='grade']"));
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("全新");
					break;
					case "1":
						$(this).text("9成5新(未使用，但包裝已拆，吊牌已剪)");
					break;
					case "2":
						$(this).text("9成新(已使用過，但無瑕疵)");
					break;
					case "3":
						$(this).text("8成新(已使用。少量瑕疵)");
					break;
					case "4":
						$(this).text("5成新(明顯瑕疵)");
					break;
					case "5":
						$(this).text("破損商品(需要修補)");
					break;
		
				}
			})
		})
		$(document).ready(function() {
			$("select[name='itemStatus']").each(function () {
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("上架");
					break;
					case "1":
						$(this).text("下架");
					break;
					case "2":
						$(this).text("刪除");
					break;
				}
			})
		})
	
	</script>


</body>
</html>