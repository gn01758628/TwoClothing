<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link rel="stylesheet" href="">
	<title>新增一般商品</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <!--頁籤icon-->
    <link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">
    <!--bootstrap5 css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <!-- google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
    <style>
        *:not([class^="fa-"]) {
            font-family: 'Noto Sans TC', sans-serif !important;
        }
    </style>
    <!-- Font Awesome -->
    <script src="https://kit.fontawesome.com/716afdf889.js" crossorigin="anonymous"></script>
    <!--Sweet Alert-->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">

		<style>
		*{
			box-sizing: border-box;
		}
		div.main{
			display: flex;
		    flex-direction: column;
		    justify-content: center;
		    align-items: center;
		    height:100%;
		    background-color: #f9edf2;
		    
		}
		form.form_add{
			width: 82%;
			display: flex;
		    flex-direction: column;
		    justify-content: center;
		    align-items: center;
		    height: 600px;
		    margin:30px;
		}
		
		form.form_add div.main_container  {
		    margin: 0 auto;
		    width: 100%;
		    padding: 20px;
		    display: flex;
			flex-direction:row;
			justify-content: space-evenly;
    		align-items: stretch;
    		height: 100%;
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
		    flex-direction:column;
			justify-content: center;
		    align-items: center;
/* 		    border: 1px solid; */
		    width: 50%;

		}

		form.form_add div.main_container div.main_right div.rightimg{
			height: 30%;
			display:flex;
		    flex-direction:row;
			justify-content: space-around;
		    align-items: flex-start;
/* 			border: 1px solid green; */
		    width: 100%;


		}

		form.form_add div.main_container div.main_right div.rightimg > div{
			height: 100%;;
			width: 50%;
			display:flex;
		    flex-direction:column;
			justify-content: flex-start;
		    align-items: center;
/* 		    border: 1px solid yellow; */
		}


		form.form_add div.main_container div.main_right div.right_rightimg{
			display:flex;
		    flex-direction:row;
			justify-content: space-around;
		    align-items: center;
			height: 70%;
		    width: 100%;
/* 		    border: 1px solid red; */
			
		}

		form.form_add div.main_container div.main_right div.right_rightimg div{
		    width:50%; 
		    text-align: center;
		    height: 100%;
		}

		form.form_add div.main_container div.main_left{
			display:flex;
		    flex-direction:column;
			justify-content: space-evenly;
		    align-items: center;
		    border: 1px solid #561729;
		    padding: 10px;
		    border-radius: 20px;
		    background-color: white;

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
		    background-color: #561729;
		    color: white;
		    padding: 10px 20px;
		    border-radius: 10px;
		    cursor: pointer;
		    font-weight: bold;
    		margin: 20px;
		}
		input.input_submit:hover{
			background-color: #f9edf2;
			border:1px solid #561729;
			color:#561729;
		}
		
		/* 標題樣式 */
		div.main h1 {
		    font-size: 24px;
		    margin:20px;
		}
		
		.form-control {
		    width: 80%;
		    padding: 10px;
		    margin: 10px 0;
		    border: 1px solid #ccc;
		    border-radius: 5px;
			box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
			background-color: white !important;
		}
		
		.form-control[type=file] {
			width:200px;
		}
		
		div.right_rightimg img{
 			max-width:100%; 
			max-height:100%; 
		}
		
		input.form-control{
			width:430px;
		}
		
		div#errarea{
			padding: 10px;
    		border: 1px solid #561729;
    		border-radius: 20px;
		}
		div#errarea ul li{
			list-style-type: none;
		
		}
/*		.modal-content {
    background-color: rgb(249, 237, 242);
    color: #00302e;
}

.text-danger {
    margin-left: 5px;
    font-size: 20px;
}

.selectable::before {
    content: "🌟";
    padding-right: 5px;
}

.selectable:hover::before, .selectable:hover {
    content: "🔯";
    cursor: pointer;
}

.non-selectable::before {
    content: "🢂";
    padding-right: 5px;
    color: #561729;
}*/
		
		
		
	</style>
	<!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
	
</head>
<body>
<div class="headerHTML"></div>



<%-- <form class="form_add" method="post" action="${pageContext.request.contextPath}/Item/add" enctype="multipart/form-data"> --%>
<div class="main">
	<h1>一般商品資訊</h1>
	<form class="form_add" method="post" action="${pageContext.request.contextPath}/Item/add.check" enctype="multipart/form-data">
		<div class="main_container">
<c:if test="${not empty errorMsgs}">
<div id="errarea">
	<font style="color:#561729">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:#561729">${message}</li>
		</c:forEach>
	</ul>
</div>
</c:if>
			<div class="main_left">
<%-- value="${empty empVO ? 'MANAGER' : empVO.job}" --%>

				<input class="input_name" type="text" placeholder="商品名稱" name="itemName" maxlength = 20 value="${empty item ? '' : item.itemName}">


					<div class="mb-3">
                        <label for="categorySelect" class="form-label" ></label>
                        <!-- 顯示選擇的完整結構,但不往後傳 -->
                        <input type="text" class="form-control" id="categorySelect"
                               aria-describedby="categorySelectHelp" readonly placeholder="商品類別標籤" >
                        <!--儲存標籤的id傳給後端-->
                        <input type="hidden" id="selectedCategoryId" name="tagId">
                        <div id="categorySelectHelp" class="form-text"></div>
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

				<div class="inner_sel">
					<select class="sel_grade" name="grade">
						<option value="" disabled selected hidden>請選擇商品新舊程度</option>
<!-- 						<option value="0" >全新(沒使用過且包裝未拆,吊牌未剪)</option> -->
					    <option value="0" ${item.grade == 0 ? 'selected' : ''}>全新(沒使用過且包裝未拆,吊牌未剪)</option>						
						<option value="1" ${item.grade == 1 ? 'selected' : ''}>9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option>
						<option value="2" ${item.grade == 2 ? 'selected' : ''}>9成新(有使用痕跡,但沒有瑕疵)</option>
						<option value="3" ${item.grade == 3 ? 'selected' : ''}>8成新(有使用痕跡,少量瑕疵)</option>
						<option value="4" ${item.grade == 4 ? 'selected' : ''}>5成新(有使用痕跡,明顯瑕疵)</option>
						<option value="5" ${item.grade == 5 ? 'selected' : ''}>破損商品(需要修補)</option>
					</select>

					<select class="sel_size" name="size"> 
						<option value="" disabled selected hidden>尺寸</option>
						<option value="0" ${item.size == 0 ? 'selected' : ''}>XS(含)以下</option>
						<option value="1" ${item.size == 1 ? 'selected' : ''}>S</option>
						<option value="2" ${item.size == 2 ? 'selected' : ''}>M</option>
						<option value="3" ${item.size == 3 ? 'selected' : ''}>L</option>
						<option value="4" ${item.size == 4 ? 'selected' : ''}>XL</option>
						<option value="5" ${item.size == 5 ? 'selected' : ''}>2XL</option>
						<option value="6" ${item.size == 6 ? 'selected' : ''}>3XL</option>
						<option value="7" ${item.size == 7 ? 'selected' : ''}>4XL(含)以上</option>
						<option value="8" ${item.size == 8 ? 'selected' : ''}>其他</option>
					</select>
				</div>
				<div class="input_inner">
					<input class="input_price" type="text" placeholder="價格" name="price" autocomplete="on" value="${empty item ? '' : item.price}">

					<input class="input_num" type="number" placeholder="數量" name="quantity"  min="1" value="${empty item ? '' : item.quantity}">
				</div>
				<textarea placeholder="商品描述" name="detail"  ></textarea>

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
<div class="footerHTML"></div>
<!--bootstrap5 js-->
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
<!--Sweet Alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
<!--JS loader-->
<script>
    $(".headerHTML").load("${pageContext.request.contextPath}/headerHTML.html", function () {
        // 保證headerHTML加載完才載入header.js
        $.getScript("${pageContext.request.contextPath}/js/chengHan/header.js");
    });

    $(".footerHTML").load("${pageContext.request.contextPath}/footerHTML.html");
</script>

<!-- <!--bootstrap5 js-->
<%-- <script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script> --%>
<!-- <!--jQuery-->
<%-- <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script> --%>

<!--此頁面的js-->
<%-- <script src="${pageContext.request.contextPath}/js/chengHan/addBid.js" type="text/javascript"></script> --%>
<script>
const categoryData = [
    <c:forEach var="tags" items="${applicationScope.categoryTags}" begin="1">
    {id:${tags.tagId}, name: '${tags.categoryName}', parentId:${tags.superTagId}},
    </c:forEach>
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