<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Follow</title>
	<!-- 頁籤icon -->
	<link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">
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
		
		main.main ul.list > li {
			border: none;
		}
		
		main.main ul.list > li a div.image-container {
		    height: calc(85% - 30px);
		    min-height: 200px;
	    }
		
		main.main ul.list > li a div.image-container img {
			border-radius: 50%;
    		overflow: hidden;
			max-width: 85%;
			max-height: 85%;
		}
		
		main.main ul.list > li .product-info {
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    justify-content: center;
		    text-align: center;
		}
		
		main.main ul.list > li a div.product-info form {
    		padding-right: 10px;
    		padding-bottom: 20px;
		}
		
		.delete {
    		background-color: transparent;
		  	border: none;
		  	transform: translateY(-3.5px) !important;
		  	transform: scale(1.1);
		  	transition: .2s linear;
		}
		
		.delete:hover > .icon {
		  	transform: scale(1.2);
		}
		
		.delete:hover > .icon path {
		  	fill: rgb(168, 7, 7);
		}
    
		.btn {
 		    border: none;
		    padding: 0;
		    margin: 0;
		    height: 30px;
		    width: 50px;
		    font-size: 14px;
		    text-align: center;
	    }
	    
	    .btn:hover, .btn:focus, .btn:active, .btn:visited {
	    	outline: none;
	    	box-shadow: none !important;
	    }
	    
	    .page-container {
	    	position: absolute;
	    	left: 50%;
    		transform: translateX(-50%);
    		margin-top: 4px;
	    }
	    
	    .btn.page {
	    	color: black;
	    	width: 40px;
	    }
	    
	    .btn.page:hover {
	    	color: rgb(168, 7, 7);
	    }
	    
	    .btn.page.active {
    		color: rgb(168, 7, 7);
    		text-decoration: underline;
		}
		
		.circle-container {
			width: 65px;
			height: 65px;
			position: fixed;
		    top: 115px;
		    right: 23px;
		    display: flex;
		    align-items: center;
		    justify-content: center;
		  }
		  
		  .circle {
		  	width: 65px;
			height: 65px;
		  }
	</style>
	<!-- 導覽列css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!-- 頁尾css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
</head>
<body>
	<div class="headerHTML"></div>
	
	<h5>關注清單</h5>
	
	<main class="main">
		<ul class="list">	
			<c:if test="${empty followingList}">
				<div class="empty-list-container">
			        <p>暫無關注中的用戶</p>
			    </div>
			</c:if>
			
			<c:forEach var="members" items="${followingList}">
				<li>
					<a href="${pageContext.request.contextPath}/SellerHome/home?mbrId=${members.mbrId}">
						<div class="image-container">
					    	<img src="${pageContext.request.contextPath}/DBGifReader5?mbrid=${members.mbrId}&imgType=avatar" alt="avatar" class="img">
				    	</div>
				    	
	    				<div class="product-info">
						    <form method="post" action="${pageContext.request.contextPath}/follow.check">
						        <button class="delete" type="submit">
						        	<svg viewBox="0 0 15 17.5" height="16" width="14" xmlns="http://www.w3.org/2000/svg" class="icon">
						        		<path transform="translate(-2.5 -1.25)" d="M15,18.75H5A1.251,1.251,0,0,1,3.75,17.5V5H2.5V3.75h15V5H16.25V17.5A1.251,1.251,0,0,1,15,18.75ZM5,5V17.5H15V5Zm7.5,10H11.25V7.5H12.5V15ZM8.75,15H7.5V7.5H8.75V15ZM12.5,2.5h-5V1.25h5V2.5Z" id="Fill"></path>
						        	</svg>
						        </button>
						        <input type="hidden" name="mbrId" value="${sessionScope.mbrId}">
								<input type="hidden" name="followId" value="${members.mbrId}">
						        <input type="hidden" name="action" value="deletefromlist">
						    </form>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>

		<div class="page-container">
			<c:if test="${not empty followingList}">
				<a class="btn page" href="${pageContext.request.contextPath}/follow.check?action=getAllByMbrId&page=1">&lt;&lt;</a>
				    
				<c:forEach var="i" begin="1" end="${followPageQty}">
				    <c:choose>
				        <c:when test="${currentPage eq i}">
				            <a class="btn page active" href="#">${i}</a>
				        </c:when>
				        <c:otherwise>
				            <a class="btn page" href="${pageContext.request.contextPath}/follow.check?action=getAllByMbrId&page=${i}">${i}</a>
				        </c:otherwise>
				    </c:choose>
				</c:forEach>
				    
				<a class="btn page" href="${pageContext.request.contextPath}/follow.check?action=getAllByMbrId&page=${followPageQty}">&gt;&gt;</a>
			</c:if>
		</div>
	</main>
	
	<div class="circle-container" style="background-color: #f9edf2; border-radius: 50%; padding: 16px;">
	    <a href="${pageContext.request.contextPath}/members/Members.do?action=memberProfile&mbrId=${mbrId}">
	        <button class="btn circle">會員<br>中心</button>
	    </a>
	</div>

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
