<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="UTF-8"> -->
<!-- <link rel="stylesheet" href=""> -->
<!-- <title>itemSellerUpload</title> -->
<!-- <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous"> --> -->
<!-- <style> -->


<!-- </style> -->

<!-- </head> -->
<!-- <body> -->

<%-- <!-- --><c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if>  --%>


<!-- <form class="form_add" method="post" action="/TwoClothing/Item/itemUpload" enctype="multipart/form-data"> -->
<!-- 	<h1>商品上傳</h1> -->
<!-- 	<br> -->
<!-- 	<label>商品名稱</label><br> -->
<!-- 	<input type="text" name="itemName" maxlength = 20> -->
<!-- 	<br> -->
<!-- 	<label>商品新舊程度</label><br> -->
<!-- 	<select  name="grade"> -->
<!-- 		<option value="" selected>請選擇商品新舊程度</option> -->
<!-- 		<option value="0">全新(沒使用過且包裝未拆,吊牌未剪)</option> -->
<!-- 		<option value="1">9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option> -->
<!-- 		<option value="2">9成新(有使用痕跡,但沒有瑕疵)</option> -->
<!-- 		<option value="3">8成新(有使用痕跡,少量瑕疵)</option> -->
<!-- 		<option value="4">5成新(有使用痕跡,明顯瑕疵)</option> -->
<!--            <option value="5">破損商品(需要修補)</option> -->
<!-- 	</select> -->
<!-- 	<br> -->
<!-- 	<label>尺寸</label><br> -->
<!-- 	<select  name="size"> -->
<!-- 		<option value="9" selected>如果您的商品不是以下列選項來描述尺寸，請跳過此選擇</option> -->
<!-- 		<option value="0">XS(含)以下</option> -->
<!-- 		<option value="1">S</option> -->
<!-- 		<option value="2">M</option> -->
<!-- 		<option value="3">L</option> -->
<!-- 		<option value="4">XL</option> -->
<!-- 		<option value="5">2XL</option> -->
<!-- 		<option value="6">3XL</option> -->
<!-- 		<option value="7">4XL(含)以上</option> -->
<!-- 	</select> -->
<!-- <!-- 	<br> --> -->
<!-- <!-- 	<label>類別</label><br> --> -->
<!-- 	<input type="hidden" name="tagId" value="1" display="none"> -->
<!-- <!-- 	<br> --> -->
<!-- <!-- 	<label>員編</label><br> --> -->
<!-- 	<input type="hidden" name="mbrId" value="1" display="none"> -->
<!-- 	<br> -->
<!-- 	<label>價格</label><br> -->
<!-- 	<input type="text" name="price"> -->
<!-- <!-- 	<br> --> -->
<!-- <!-- 	<label>狀態</label><br> --> -->
<!-- 	<input type="hidden" name="itemStatus" value="0" display="none"> -->
<!-- 	<br> -->
<!-- 	<label>數量</label><br> -->
<!-- 	<input type="text" name="quantity" autocomplete="on"> -->
<!-- 	<br> -->
<!-- 	<label>商品描述</label><br> -->
<!-- 	<textarea name="detail"></textarea> -->
<!-- 	<br> -->
<!-- <!-- 	<div> --> -->
<!-- <!-- 	<label>上傳商品主圖片</label> --> -->
<!-- <!-- 	<input type="file" name="itemImg01" accept="image/jpeg, image/png"> --> -->
<!-- <!-- 	</div> --> -->
<!--     <br>	 -->
<!-- <input type="hidden" name="action" value="insert"> -->
<!-- <input type="submit" value="送出新增"> -->
<!-- </form>	 -->

<%--     <a href="${pageContext.request.contextPath}/front_end/item/itemSellerSearch.jsp">查詢</a> --%>

<!-- <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script> --> -->
<!-- </body> -->
<!-- </html> -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link rel="stylesheet" href="">
	<title>itemSellerUpload</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<style type="text/css">
	form {
		border: 1px solid black;
	 	text-align:center; 
	}
	
	label{
		text-align : center;
	}
	
	input{
		text-align : center;
	}
	</style>
</head>
<body>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<form class="form_add" method="post" action="/TwoClothing/Item/add" >
<p>
	<h1>商品上傳</h1>
	<br>
	<label>商品名稱</label>
	<input type="text" name="itemName" maxlength = 20>
	<br>
	<label>商品新舊程度</label>
	<input type="text" name="grade">
	<br>
	<label>尺寸</label>
	<input type="text" name="size">
	<br>
	<label>類別</label>
	<input type="text" name="tagId" value="1">
	<br>
	<label>員編</label>
	<input type="text" name="mbrId" value="1">
	<br>
	<label>價格</label>
	<input type="text" name="price">
	<br>
	<label>狀態</label>
	<input type="text" name="itemStatus" value="0">
	<br>
	<label>數量</label>
	<input type="text" name="quantity" autocomplete="on">
	<br>
	<label>商品描述</label>
	<textarea name="detail"></textarea>
	<br>
		
	
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
<p>
</form>	
    <a href="${pageContext.request.contextPath}/front_end/item/itemSellerSearch.jsp">查詢</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</body>
</html>