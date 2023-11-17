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

		<style>
		*{
			box-sizing: border-box;
		}
		div.main{
			display: flex;
		    flex-direction: column;
		    justify-content: center;
		    align-items: center;
		    height:90%;
		}
		form.form_add{
			width: 72%;
			position: relative;	
		}
		div.main_container{
			display: flex;
			flex-direction:row;

		}
		
		form.form_add div.main_container  {
		    margin: 0 auto;
		    width: 1000px;
		    padding: 20px;
		}
/* 		類別標籤的input */
		div.mb-3 {		      
    		margin-bottom: 0rem!important;
     		height:64px;
     		display: flex;
		    flex-direction: column;
		    justify-content: center;
		    align-items: center;
     		   
		}

		form.form_add div.main_container div.main_right{
			display:flex;
		    flex-direction:row;
			justify-content: center;
		    align-items: center;
		}

		form.form_add div.main_container div.main_right div.rightimg{
			height: 100%;
			display:flex;
		    flex-direction:column;
			justify-content: center;
		    align-items: center;
			padding: 0px 14px

		}

		form.form_add div.main_container div.main_right div.rightimg > div{
			height: 100%;
			display:flex;
		    flex-direction:column;
			justify-content: center;
		    align-items: center;
		}


		form.form_add div.main_container div.main_right div.right_rightimg{
			display:flex;
		    flex-direction:column;
			justify-content: center;
		    align-items: center;
			height: 440px;
			width: 200px;
			
		}

		form.form_add div.main_container div.main_right div.right_rightimg div{
/* 			border: 1px solid black; */
		    /* height: 100%; */
		    width: 162px;
		    margin: 10px 0px;
		    max-height: 195px;
		    text-align: center;
		}
		
/* 		form.form_add div.main_container div.main_right div.right_rightimg div img{ */
/* 			height: 100%; */
/* 			width: 100%; */
/* 		} */

		form.form_add div.main_container div.main_left{
			display:flex;
		    flex-direction:column;
			justify-content: space-between;
		    align-items: center

		}
		form.form_add div.main_container div.main_left div.inner_sel{
			display:flex;
		    flex-direction:row;
		}

		form.form_add div.main_container div.main_left div.input_inner{
			display:flex;
		    flex-direction:row;
		}
		
		
		form.form_add div.main_container  div.main_left 
		input.input_name, select.sel_tagid{
			width: 430px;
		    padding: 10px;
		    margin: 10px ;
		    border: 1px solid #ccc;
		    border-radius: 5px;
			box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
		}

		form.form_add div.main_container  div.main_left 
		select.sel_grade, select.sel_size, input.input_price, input.input_num{
			width: 210px;
		    padding: 10px;
		    margin: 10px 5px;
			
		    border: 1px solid #ccc;
		    border-radius: 5px;
			box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);

		}

		form.form_add div.main_container  div.main_left textarea{
			width: 430px;
			margin: 10px;
			padding: 10px;
			border: 1px solid #ccc;
		    border-radius: 5px;
			box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
		}
		
		/* 調整提交按鈕的樣式 */
		input.input_submit {
		    background-color: #2860f9c8;
		    color: #fff;
		    padding: 10px 20px;
		    border: none;
		    border-radius: 5px;
		    cursor: pointer;
		    font-weight: bold;
			position: absolute;
    		right: 30px;
		}
		
		/* 標題樣式 */
		h1 {
		    font-size: 24px;
			text-align: left;
    		width: 1000px;
    		padding: 75px;
    		padding-bottom: 0px;
		}
		
		.form-control {
		    width: 80%;
		    padding: 10px;
		    margin: 10px 0;
		    border: 1px solid #ccc;
		    border-radius: 5px;
			box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
		}
		
		.form-control[type=file] {
			width:200px;
		}
		
		img{
			width:175px;
			max-width:100%;
			max-height:100%;
		}
		
		input.form-control{
			width:430px;
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


<%-- <form class="form_add" method="post" action="${pageContext.request.contextPath}/Item/add" enctype="multipart/form-data"> --%>
<div class="main">
	<h1>商品上傳</h1>
	<form class="form_add" method="post" action="${pageContext.request.contextPath}/Item/add" enctype="multipart/form-data">
		<div class="main_container">
			<div class="main_left">

				<input class="input_name" type="text" placeholder="商品名稱" name="itemName" maxlength = 20>


					<div class="mb-3">
                        <label for="categorySelect" class="form-label" ></label>
                        <!-- 顯示選擇的完整結構,但不往後傳 -->
                        <input type="text" class="form-control" id="categorySelect"
                               aria-describedby="categorySelectHelp" readonly placeholder="商品類別標籤">
                        <!--儲存標籤的id傳給後端-->
                        <input type="hidden" id="selectedCategoryId" name="tagId">
<!--                         <div id="categorySelectHelp" class="form-text">選擇適當的標籤，讓更多人能找到您的商品</div> -->
                    </div>
                    <div class="modal fade" id="categoryModal" tabindex="-1" role="dialog"
                         aria-labelledby="categoryModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="categoryModalLabel">選擇商品類別標籤</h5>
                                </div>
                                <div class="modal-body" id="categoryTree">
                                    <!-- 類別標籤樹狀結構在這裡生成 -->
                                </div>
                            </div>
                        </div>
                    </div>



<!-- 				<select class="sel_tagid" name="tagId">  -->
<!-- 					<option value="1" disabled selected>所有種類</option > -->
<!-- 						<optgroup value="2" label="上衣"> -->
<!-- 							<optgroup value="5" label="&nbsp;&nbsp;&nbsp;&nbsp;短袖"> -->
<!-- 								<option value="9">&nbsp;&nbsp;&nbsp;男短袖</option> -->
<!-- 								<option value="10">&nbsp;&nbsp;&nbsp;女短袖</option> -->
<!-- 							</optgroup> -->
<!-- 						<option value="6">&nbsp;&nbsp;&nbsp;&nbsp;長袖</option> -->
<!-- 						</optgroup> -->
						
<!-- 						<optgroup value="3" label="褲子"> -->
<!-- 							<option value="7">短褲</option> -->
<!-- 							<option value="8">長褲</option> -->
<!-- 						</optgroup > -->
<!-- 						<option value="4">飾品</option> -->
<!-- 				</select> -->

				<div class="inner_sel">
					<select class="sel_grade" name="grade">
						<option value="" disabled selected>請選擇商品新舊程度</option>
						<option value="0">全新(沒使用過且包裝未拆,吊牌未剪)</option>
						<option value="1">9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option>
						<option value="2">9成新(有使用痕跡,但沒有瑕疵)</option>
						<option value="3">8成新(有使用痕跡,少量瑕疵)</option>
						<option value="4">5成新(有使用痕跡,明顯瑕疵)</option>
						<option value="5">破損商品(需要修補)</option>
					</select>

					<select class="sel_size" name="size"> 
						<option value="" disabled selected>尺寸</option>
						<option value="8">其他</option>
						<option value="0">XS(含)以下</option>
						<option value="1">S</option>
						<option value="2">M</option>
						<option value="3">L</option>
						<option value="4">XL</option>
						<option value="5">2XL</option>
						<option value="6">3XL</option>
						<option value="7">4XL(含)以上</option>
					</select>
				</div>
				<div class="input_inner">
					<input class="input_price" type="text" placeholder="價格" name="price" autocomplete="on">

					<input class="input_num" type="number" placeholder="數量" name="quantity"  min="1">
				</div>
				<textarea placeholder="商品描述" name="detail"></textarea>

			</div>
			<div class="main_right">
				<div class="rightimg">
					<div>
					<label for="image01" class="form-label">上傳商品的主圖片<span class="text-danger">*</span></label>
					<div id="image01Help" class="form-text">每個商品都必須要有主圖片</div>
					<input class="form-control" type="file" id="image01" name="image01"
							accept="image/jpeg, image/png"  aria-describedby="image01Help">
					</div>

					<div>
					<label for="image02" class="form-label2">上傳商品的補充圖片</label>
					<input class="form-control" type="file" id="image02" name="image02"
							accept="image/jpeg, image/png">
					</div>
				</div>

				<div class="right_rightimg">
					<div id="preview_img01"></div>
					<div id="preview_img02"></div>
				</div>
			</div>
		</div>
	<input type="hidden" name="addRoad" value="add">
	<input class="input_submit" type="submit" value="送出新增">
	</form>	
</div>
<%--     <a href="${pageContext.request.contextPath}/front_end/item/itemSellerSearch.jsp">查詢</a> --%>

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
    const categoryData = [
    	
        {id:2, name: '上衣', parentId:1},
        {id:3, name: '庫子', parentId:1},
        {id:4, name: '長袖', parentId:2},
        {id:5, name: '長褲', parentId:3},
    	
    	
//         <c:forEach var="tags" items="${categoryTags}" begin="1">
//         {id:${tags.tagId}, name: '${tags.categoryName}', parentId:${tags.superTagId}},
//         </c:forEach>
    ];
</script>

<!--商品類別標籤的js-->
<script src="${pageContext.request.contextPath}/js/chengHan/addBidCategoryTags.js"></script>


	<script>
	
	$(document).ready(function () {
	    $("#image01").on("change", function () {
	        var preview_img = $("#preview_img01");
	        preview_img.empty(); 
	        
	        var files = this.files;

	        for (var i = 0; i < files.length; i++) {
	            var reader = new FileReader();
	            var file = files[i];

	            reader.onload = function (e) {
	                var img = $("<img>").attr("src", e.target.result);
	                preview_img.append(img);
	            };

	            reader.readAsDataURL(file);
	        }
	    });
	    $("#image02").on("change", function () {
	        var preview_img = $("#preview_img02");
	        preview_img.empty(); 
	        
	        var files = this.files;

	        for (var i = 0; i < files.length; i++) {
	            var reader = new FileReader();
	            var file = files[i];

	            reader.onload = function (e) {
	                var img = $("<img>").attr("src", e.target.result);
	                preview_img.append(img);
	            };

	            reader.readAsDataURL(file);
	        }
	    });
	});
	
	</script>


</body>
</html>