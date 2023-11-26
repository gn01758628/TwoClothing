<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ItemBrowsing</title>
	<!-- bootstrap5 css -->
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
    <!-- Sweet Alert -->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    <!-- css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/chi/list.css">
	<style>
		h5 {
			margin: 30px auto 0 35px;
		}
		
		.empty-list-container {
			color: #7A7A7A;
			font-size: 30px;
			margin-top: 45px;
			margin-left: 70px;
		}
	
		.not-found-container {
		    position: absolute;
		    height: 200px;
		    width: 200px;
		    left: 50%;
		    top: 50%;
		    transform: translate(-50%, -50%);
		    border-radius: 50%;
		    background-color: rgba(192, 192, 192, 0.3);
		    display: flex;
    		justify-content: center;
    		align-items: center;
		}
		
		.not-found {
			color: white;
			font-size: 30px;
			margin: 0px;
		}
		
		.name {
			min-width: 150px;
			max-width: 150px;
		}
	</style>
	<!-- 導覽列css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!-- 頁尾css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
</head>
<body>
	<div class="headerHTML"></div>

	<h5>一般商品瀏覽紀錄</h5>
	
	<main class="main">
		<ul class="list">
			<c:if test="${empty itemBrowsingList}">
				<div class="empty-list-container">
			        <p>暫無瀏覽紀錄</p>
			    </div>
			</c:if>
			
			<c:forEach var="item" items="${itemBrowsingList}">
				<li>
					<a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
						<div class="image-container">
					    	<img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" class="img">
				    	</div>
				    	
	    				<div class="product-info">
	    					<c:if test="${item.itemStatus eq 1}">
	    						<div class="not-found-container">
               						<p class="not-found">商品未上架</p>
               					</div>
            				</c:if>
						    <span class="name">${item.itemName}</span>
						    <span class="price">NT$ ${item.price}</span>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
	</main>

	<div class="footerHTML"></div>
	
	<!-- bootstrap5 js -->
	<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <!-- Sweet Alert -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
	<!-- JS loader -->
	<script>
	    $(".headerHTML").load("${pageContext.request.contextPath}/headerHTML.html", function () {
	        // 保證headerHTML加載完才載入header.js
	        $.getScript("${pageContext.request.contextPath}/js/chengHan/header.js");
	    });
	
	    $(".footerHTML").load("${pageContext.request.contextPath}/footerHTML.html");
	</script>
</body>
</html>
