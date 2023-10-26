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
        padding: 8px;
        text-align: left;
    }

    input[type="text"] {
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

<style>
/*   table#table-1 { */
/* 	background-color: #CCCCFF; */
/*     border: 2px solid black; */
/*     text-align: center; */
/*   } */
/*   table#table-1 h4 { */
/*     color: red; */
/*     display: block; */
/*     margin-bottom: 1px; */
/*   } */
/*   h4 { */
/*     color: blue; */
/*     display: inline; */
/*   } */
</style>

<style>
/*   table { */
/* 	width: 450px; */
/* 	background-color: white; */
/* 	margin-top: 1px; */
/* 	margin-bottom: 1px; */
/*   } */
/*   table, th, td { */
/*     border: 0px solid #CCCCFF; */
/*   } */
/*   th, td { */
/*     padding: 1px; */
/*   } */
</style>

</head>
<body bgcolor='white'>

<!-- <table id="table-1"> -->
<!-- 	<tr><td> -->
<!-- 		 <h3>員工資料修改 - update_emp_input.jsp</h3> -->
<!-- 		 <h4><a href="itemListCompositeQuery.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->
<!-- </table> -->

<h3>資料修改:</h3>

<%-- <%-- 錯誤表列 --%> 
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="/TwoClothing/Item/Update">
<table>
	<tr>

<%-- 		<td><%=item.getItemId()%></td> --%>
	</tr>
		<td>商品</td>
		<td><input type="TEXT" name="itemId" value="${item.itemId}" size="45"/></td>
	<tr>
		<td>商品名稱</td>
		<td><input type="TEXT" name="itemName" value="${item.itemName}" size="45"/></td>
	</tr>
	<tr>
		<td>商品價格</td>
		<td><input type="TEXT" name="price"   value="${item.price}" size="45"/></td>
	</tr>
	<tr>
		<td>新舊</td>
		<td>
<%-- 		<input type="TEXT" name="grade" type="text" value="${item.grade}"></td>  --%>
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
<%-- 		<input type="TEXT" name="size"   value="${item.size}" size="45"/> --%>
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
		<td>數量</td>
		<td><input type="TEXT" name="quantity"  value="${item.quantity}" size="45"/></td>
	</tr>
	<tr>
		<td>描述</td>
		<td><input type="TEXT" name="detail"  value="${item.detail}" size="45"/></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ItemId" value="${item.itemId}">
<input type="submit" value="送出修改"></FORM>
</body>
</html>