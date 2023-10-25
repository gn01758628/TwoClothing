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
/* 添加一些全局样式，设置字体和背景颜色 */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

/* 创建一个包含表单的容器 */
.form-container {
    max-width: 500px;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
}

/* 设置表单元素的样式 */
.form-container label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

.form-container input[type="text"],
.form-container select,
.form-container textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.form-container input[type="file"] {
    width: 100%;
    margin-bottom: 10px;
}

.form-container select {
    background-color: #f9f9f9;
}

.form-container textarea {
    resize: vertical;
}

.form-container input[type="submit"] {
    background-color: #007BFF;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

/* 添加一些响应式设计，适应不同屏幕尺寸 */
@media (max-width: 768px) {
    .form-container {
        max-width: 100%;
        padding: 10px;
    }
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


<form class="form_add" method="post" action="${pageContext.request.contextPath}/TwoClothing/Item/add" enctype="multipart/form-data">
<p>
	<h1>商品上傳</h1>
	<br>
	<label>商品名稱</label>
	<input type="text" name="itemName" maxlength = 20>
	<br>
	<label>商品新舊程度</label>
<!-- 	<input type="text" name="grade"> -->
	<select  name="grade">
		<option value="" selected>請選擇商品新舊程度</option>
		<option value="0">全新(沒使用過且包裝未拆,吊牌未剪)</option>
		<option value="1">9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option>
		<option value="2">9成新(有使用痕跡,但沒有瑕疵)</option>
		<option value="3">8成新(有使用痕跡,少量瑕疵)</option>
		<option value="4">5成新(有使用痕跡,明顯瑕疵)</option>
        <option value="5">破損商品(需要修補)</option>
	</select>
	<br>
	<label>尺寸</label>
<!-- 	<input type="text" name="size"> -->
	<select  name="size"> 
		<option value="9" selected>如果您的商品不是以下列選項來描述尺寸，請跳過此選擇</option>
		<option value="0">XS(含)以下</option>
		<option value="1">S</option>
		<option value="2">M</option>
		<option value="3">L</option>
		<option value="4">XL</option>
		<option value="5">2XL</option>
		<option value="6">3XL</option>
		<option value="7">4XL(含)以上</option>
	</select>
	<br>
	<label>類別</label>
	<select  name="tagId"> 
		<option value="1" selected>所有種類</option>
		<option value="2">上衣</option>
		<option value="3">褲子</option>
		<option value="4">飾品</option>
		<option value="5">短袖</option>
		<option value="6">長袖</option>
		<option value="7">短褲</option>
		<option value="8">長褲</option>
		<option value="9">男短袖</option>
		<option value="10">女短袖</option>
	</select>
	<br>
	<label>價格</label>
	<input type="text" name="price" autocomplete="on">
	<br>
	<label>數量</label>
	<input type="number" name="quantity" value="1" min="1">
	<br>
	<label>商品描述</label>
	<textarea name="detail"></textarea>
	<br>
    <div class="">
      <label for="image01" class="form-label">上傳商品的主圖片<span class="text-danger">*</span></label>
      <input class="form-control" type="file" id="image01" name="image01"
             accept="image/jpeg, image/png" required aria-describedby="image01Help">
      <div id="image01Help" class="form-text">每個商品都必須要有主圖片</div>
    </div>

    <div class="">
      <label for="image02" class="form-label">上傳商品的補充圖片</label>
      <input class="form-control" type="file" id="image02" name="image02"
             accept="image/jpeg, image/png">
    </div>
		
	
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
<p>
</form>	
    <a href="${pageContext.request.contextPath}/front_end/item/itemSellerSearch.jsp">查詢</a>

<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
<!--輸入錯誤訊息的資料(必須在引用標籤js檔之前宣告)-->
<script>
    const messages = [];
    <c:forEach var="errorMsgs" items="${errorMsgs}">
    messages.push("${errorMsgs}");
    </c:forEach>
</script>
<!--此頁面的js-->
<%-- <script src="${pageContext.request.contextPath}/js/chengHan/addBid.js" type="text/javascript"></script> --%>
<script>
//     const categoryData = [
//         <c:forEach var="tags" items="${categoryTags}" begin="1">
//         {id:${tags.tagId}, name: '${tags.categoryName}', parentId:${tags.superTagId}},
//         </c:forEach>
//     ];
</script>

<!--商品類別標籤的js-->
<%-- <script src="${pageContext.request.contextPath}/js/chengHan/addBidCategoryTags.js"></script> --%>

</body>
</html>