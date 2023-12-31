<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${item.itemName}</title>
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
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
        }
        
        form.form_detail {
            padding: 10px;
            width: 100%;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            min-height: calc(100vh - 309px);
        }

        form.form_detail div.body_container {
            max-width: 800px;
            width: 100%;
            display: flex;
            min-height: 400px;
/*             max-height: 412px; */
        }

        form.form_detail div.downarea {
            border-top:4px solid white;
            max-width: 800px;
            width: 100%;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }

        form.form_detail div.downarea table {
            width: 100%;
            height: 100px;
            table-layout: fixed;
            border-spacing: 20px;
            border-collapse: separate;
        }

        form.form_detail div.downarea table tr td, th {
            padding: 2px 10px;
            flex:1;
            text-align: left;
        }

        form.form_detail div.downarea table tr th {
            border-bottom: 2px solid gray;            
        }

        form.form_detail div.body_container div.product-image {
            flex: 1;
            min-width: 50%;
            display: flex;
    		justify-content: right;
    		background-color: #f2f2f2;
        }

        form.form_detail div.body_container div.product-image img {
/*             border: 1px solid black; */
             max-width: 100%; 
             height: 100%;
            display: block;
        }

        form.form_detail div.body_container div.product-info {
            flex: 1;
            background-color: #f2f2f2;
            padding: 0px;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            position: relative;
        }
        
        form.form_detail div.body_container div.product-info ul {
            list-style-type: none;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            padding: 0px;
        }
        
        form.form_detail div.body_container div.product-info ul li {
            margin: 10px 4px;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            padding: 5px;
        }
        
        form.form_detail div.body_container div.product-info ul li h1 {
            font-size: 24px;
            margin: 0;
            text-align: center;
        }

        form.form_detail div.body_container div.product-info ul li p {
            font-size: 16px;
            display: inline-block;
            margin: 2px 0;
        }
        
        /* 收藏 */ 
        .heart-container {
        	--heart-color: rgb(255, 91, 137);
  			position: absolute;
  			left: 170px;
		  	width: 20px;
		  	height: 20px;
		  	transition: .3s;
		}
		
		@media screen and (max-width: 540px) {
			.heart-container {
	        	display: none;
			}
		}

		.heart-container .checkbox {
			position: absolute;
			width: 100%;
			height: 100%;
			opacity: 0;
			z-index: 20;
			cursor: pointer;
		}

		.heart-container .svg-container {
  			width: 100%;
  			height: 100%;
  			display: flex;
  			justify-content: center;
  			align-items: center;
		}

		.heart-container .svg-outline, .heart-container .svg-filled {
  			fill: var(--heart-color);
  			position: absolute;
		}

		.heart-container .svg-filled {
  			animation: keyframes-svg-filled 1s;
  			display: none;
		}

		.heart-container .svg-celebrate {
  			position: absolute;
  			animation: keyframes-svg-celebrate .5s;
  			animation-fill-mode: forwards;
  			display: none;
		  	stroke: var(--heart-color);
		  	fill: var(--heart-color);
		  	stroke-width: 2px;
		}

		.heart-container .checkbox:checked~.svg-container .svg-filled {
  			display: block;
		}

		.heart-container .checkbox:checked~.svg-container .svg-celebrate {
  			display: block;
		}

		@keyframes keyframes-svg-filled {
  			0% {
    			transform: scale(0);
  			}

  			25% {
    			transform: scale(1.2);
  			}

  			50% {
    			transform: scale(1);
    			filter: brightness(1.5);
  			}
		}

		@keyframes keyframes-svg-celebrate {
  			0% {
    			transform: scale(0);
  			}

  			50% {
    			opacity: 1;
    			filter: brightness(1.5);
  			}

  			100% {
    			transform: scale(1.4);
    			opacity: 0;
    			display: none;
  			}
		}
        
        .custom-popup-class {
        	background-color: #f2f2f2;
        	width: 250px;
        }
        
        /* 檢舉 */ 
        .report-container {
        	position: absolute;
  			left: 170px;
  			transition: transform 0.3s ease;
		}
        
        .report-container svg {
    		transform: scale(0.28);
		}
		
		@media screen and (max-width: 710px) {
			.report-container {
	        	display: none;
			}
		}
		
		.report-container:hover {
		    transform: scale(1.1);
		}
		
		.report-custom-popup-class {
        	background-color: #f2f2f2;
        }
        
        .modal-dialog {
        	width: 100%;
        }
        
        .modal-body {
  			display: flex;
        	align-items: center;
        	justify-content: center;
        }
        
        .title-description {
        	margin-top: 10px;
        }
        
        td.itemId {
        	padding-left: 19px;
        }
        
        .inputDescription {
        	margin-top: 10px;
        	margin-left: 16px;
        }
        
        .modal-footer button:hover, button:focus, button:active, button:visited {
        	outline: none;
	    	box-shadow: none !important;
        }
        
        form.form_detail div.body_container div.product-info ul li.li_num {
            margin-bottom: 0px;
            padding-bottom: 0px;
        }
        
        form.form_detail div.body_container div.product-info ul li.li_quantity {
            display: inline-block;
            margin-top: 0px;
            padding-top: 0px;

        }
        
        form.form_detail div.body_container div.product-info ul li p.p_quantity {
            font-size: 12px;
        }
        
        form.form_detail div.body_container div.product-info ul li.li_quantity p:nth-child(2) {
            margin-left: 5px;
        }

        form.form_detail div.body_container div.product-info ul li p.price {
            font-size: 20px;
        }

        li.li_num {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        li.li_num input {
            max-width: 73px;
            border-radius: 15px;
            padding: 4px 7px;
        }

        div.body_container div.product-info ul li div.description {
            margin-top: 20px;
        }

        div.body_container div.product-info ul input.buy-button {
            background-color: #561729;
            color: #fff;
            border: none;
            padding: 5px 10px;
            font-size: 14px;
            cursor: pointer;
            margin-top: 20px;
            border-radius: 15px;
        }

        div.body_container div.product-info ul button.buy-button:hover {
            background-color: #6e6b71;
        }
        
        a#contactSeller{
        	margin: 10px 0;
		    font-size: 12px;
		    text-decoration: none;
		    color: #561729;
        }
        
        div.toSeller {
            width: 0;
            height: 0;
            border-top: 100px solid transparent;
            border-right: 120px solid pink;
            display: flex;
            flex-direction: row;
            margin-left: auto;
            position: absolute;
		    bottom: 0;
		    right: 0;
        }
        
        div.toSeller:hover {
            border-right-color: #561729;
            color:black;
        }
        
        div.toSeller:hover a {
            color: white;
        }
        
        div.toSeller a {
			position: absolute;
		    bottom: 5px;
		    right: -113px;
		    width: 74px;
		    text-decoration: none;
		    margin-left: auto;
		    color: rgb(0, 0, 0);
		    padding: 2px;
		    font-size: 17px;
        }
        
/*         項目條 */
        button.carousel-indicators [data-bs-target] {
        	background-color: gray;
        }
        
        .carousel-inner {
        	height: 100%;
/*         	width:0; */
        }

/*         圖片容器 */ 
        .carousel-item {
        	height: 100%; 
/*   			width: 400px;  */
         }
         
         .carousel-item active { 
         	height: 100%; 
/* 	  		width: 400px;  */
         }
	</style>
	<!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
	

<!-- 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> -->
</head>
<body>
	<div class="headerHTML"></div>

	<form class="form_detail" method="post" action="${pageContext.request.contextPath}/ItemCart/cart" enctype="multipart/form-data">
	    <div class="body_container">
	        <div class="product-image">
	<%--             <img id="slider_img" src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="Product Image"> --%>
	<!--         	<button type="button" onclick="nextImg()">按</button> -->
	        	
				  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
				  <div class="carousel-indicators">
				    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
				    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
	<!-- 			    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
				  </div>
				  <div class="carousel-inner">
				    <div class="carousel-item active">
				      <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" class="d-block w-100" alt="...">
				    </div>
				    <div class="carousel-item">
				      <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=2" class="d-block w-100" alt="...">
				    </div>
				  </div>
				  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
				</div>
	        	
	        </div>
	        <div class="product-info">
	            <ul>
	                <input type="hidden" name="itemId" value="${item.itemId}">
	                <li style="display: flex; flex-direction: row; justify-content: center; align-items: center; margin: 10px 4px; padding: 5px; position: relative; width: 150px;">
		                <h1 name="itemName" value="${item.itemName}">${item.itemName}</h1>
	                    
	                    <div class="heart-container">
					        <input type="checkbox" class="checkbox" id="Give-It-An-Id">
				            <div class="svg-container">
				                <svg viewBox="0 0 24 24" class="svg-outline" xmlns="http://www.w3.org/2000/svg">
				                    <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Zm-3.585,18.4a2.973,2.973,0,0,1-3.83,0C4.947,16.006,2,11.87,2,8.967a4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,11,8.967a1,1,0,0,0,2,0,4.8,4.8,0,0,1,4.5-5.05A4.8,4.8,0,0,1,22,8.967C22,11.87,19.053,16.006,13.915,20.313Z">
				                    </path>
				                </svg>
				                <svg viewBox="0 0 24 24" class="svg-filled" xmlns="http://www.w3.org/2000/svg">
				                    <path d="M17.5,1.917a6.4,6.4,0,0,0-5.5,3.3,6.4,6.4,0,0,0-5.5-3.3A6.8,6.8,0,0,0,0,8.967c0,4.547,4.786,9.513,8.8,12.88a4.974,4.974,0,0,0,6.4,0C19.214,18.48,24,13.514,24,8.967A6.8,6.8,0,0,0,17.5,1.917Z">
				                    </path>
				                </svg>
				                <svg class="svg-celebrate" width="100" height="100" xmlns="http://www.w3.org/2000/svg">
				                    <polygon points="10,10 20,20"></polygon>
				                    <polygon points="10,50 20,50"></polygon>
				                    <polygon points="20,80 30,70"></polygon>
				                    <polygon points="90,10 80,20"></polygon>
				                    <polygon points="90,50 80,50"></polygon>
				                    <polygon points="80,80 70,70"></polygon>
				                </svg>
				            </div>
				        </div>
				        
				        <div class="report-container">
				        	<svg width="100" height="100" xmlns="http://www.w3.org/2000/svg">
        						<polygon points="50,20 85,80 15,80" style="fill: #f2f2f2; stroke: #ff6A6A; stroke-width: 5; stroke-linejoin: round;"/>
        						<text x="50" y="60" font-size="40" fill="#ff6A6A" text-anchor="middle" dominant-baseline="middle">!</text>
    							<rect x="13" y="15" width="75" height="70" fill="transparent" style="cursor: pointer;" onclick="showDetail(${item.itemId})"/>
    						</svg>
				        </div>
	                </li>
	                <li>
	                    <p class="price" name="price">$${item.price}</p>
	                </li>
	                <li class="li_num">
	                    <p>數量：</p><input type="number" name="quantity" id="input_num" min="1" value="1" max="${item.quantity}">
	                </li>
	                <li class="li_quantity">
	                    <p class="p_quantity">剩餘件數</p><p>${item.quantity}</p>
	                </li>
					<input type="hidden" name="mbrId"  value="2">
	            	<input type="hidden" name="itemId"  value="${item.itemId}">
					<input type="hidden" name="gotoCart" value="gotoCart">
	                <input type="button" class="buy-button" value="加入購物車">
	                <a href="${pageContext.request.contextPath}/front/chatroom/${item.mbrId}.check" id="contactSeller">聯絡賣家</a>
	            </ul>
	            <div class="toSeller">
	                    <a href="${pageContext.request.contextPath}/SellerHome/home?mbrId=${item.mbrId}">查看賣場</a>
	            </div>
	        </div>
	    </div>
	    <div class="downarea">
	    	<table>
	    		<tr>
	    			<th>Description</th>
	    			<th>Size</th>
	    			<th>Grade</th>
	    		</tr>
	            <tr>
	            	<td>${item.detail}</td>
	            	<td class="size" >${item.size}</td>
	            	<td class="grade" >${item.grade}</td>
	            </tr>
	        </table>
	    </div>
	</form>
<%-- 	<a href="${pageContext.request.contextPath}/ItemCart/cartlist?goto=cart&mbrId=2">查看購物車</a> --%>
	
	<div class="modal fade" id="itemReportModal" tabindex="-1" aria-labelledby="itemReportModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
	      		<div class="modal-header">
	      			<h5 class="modal-title" id="itemReportModalLabel">商品檢舉</h5>
	      		</div>
	      		
		      	<div class="modal-body">
			        <div class="card" style="width: 45rem;">
				        <div class="card-body">
					        <table>
								<tr>
									<td>商品編號</td>
									<td id="itemId" class="itemId"></td>
								</tr>
								<tr>
									<td>
										<div class="title-description">檢舉原因</div></td>
									<td>
										<input type="text" id="inputDescription" class="inputDescription" name="inputDescription" size="63"/>
									</td>
								</tr>
							</table>
				        </div>
		      		</div>
		      	</div>
		      	
		      	<div class="modal-footer">
		      		<button class="btn btn-secondary" id="insert" onclick="insertReport()">送出</button>
			    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
			    </div>
			</div>
		</div>
	</div>
	<div class="footerHTML"></div>
	
<%--     <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>	 --%>
<!-- 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script> -->
    
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

    <script>
	    $(document).ready(function() {
	    	var quantities;
			$(".size").each(function () {
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
					case "":
						$(this).text("其他");
						break;
				}
			});
	
			$(".grade").each(function () {
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
			});
		});
	    
		$(".buy-button").on("click",function(){
			quantities = $("#input_num").val();
			let url="${pageContext.request.contextPath}/ItemCart/cart?itemId=${item.itemId}&quantity="+quantities+"&gotoCart=gotoCart";
			fetch(url)
            .then(function(response){
            	return response.text();
            })
            .then(function(data){
            	Swal.fire({
            		  backdrop: false,
            		  title: data,
            		  confirmButtonText: "確認",
            		  icon: "success",
            		  iconColor: '#b0c4de',
            		});
            })
            .catch(function(error){
            	alert("加入購物車失敗！");
            })
			
			//購物車icon顯示數量
			let url2="${pageContext.request.contextPath}/ItemCart/cart?itemId=${item.itemId}&addCartNum=addCartNum";
			fetch(url2)
            .then(function(response){
            	return response.text();
            })
            .then(function(data){
            	
			var carNum= parseInt($("li.nav-item span.carNum").text());
			if(!carNum){
				carNum=0;
			}
				if(data == "true"){
					
				}else if(data == "false"){
					carNum += 1;
					$("li.nav-item span.carNum").text(carNum).show();
					if(carNum == 0){
						$("li.nav-item span.carNum").hide();
					}
				}
            })
            .catch(function(error){
//             	alert("");
            })
			
			
		})
		
		let isTracked = ${isItemTracked};
		if (isTracked) {
			$(".heart-container .checkbox").prop("checked", true);
    	}

		<% Integer mbrId = (Integer) session.getAttribute("mbrId"); %>
		$("#Give-It-An-Id").on("click", function () {
			if (${mbrId == null}) {
		        window.location.href = "${pageContext.request.contextPath}/front_end/members/registerLogin.jsp";
		        $(this).prop("checked", false);
		    }
			
			if (!isTracked) {
				insertItem();
            } else {
            	deleteItem();
            }
		});

		function insertItem() {
			var url = "${pageContext.request.contextPath}/itemtrackinglist.check?action=insert&itemId=${item.itemId}";
			
			$.ajax({
		        type: "POST",
		        url: url,
		        success: function (data) {
		        	console.log(data);
		        	isTracked = true;
		        },
		        error: function (xhr) {
		            console.log(xhr);
		        }
		    });
		}

		function deleteItem() {
			var url = "${pageContext.request.contextPath}/itemtrackinglist.check?action=delete&itemId=${item.itemId}";
			
			$.ajax({
		        type: "POST",
		        url: url,
		        success: function (data) {
		        	Swal.fire ({
		        		backdrop: false,
		        		title: "移除成功",
		        		timer: 900,
		        		showConfirmButton: false,
		        		customClass: {
		        			popup: 'custom-popup-class',
		        		}
		        	});
		            isTracked = false;
		        },
		        error: function (xhr) {
		        	console.log(xhr);
		        }
		    });
		}

		function showDetail(itemId) {
		    event.preventDefault();
		    
		    <c:if test="${mbrId == null}">
	        	window.location.href = "${pageContext.request.contextPath}/front_end/members/registerLogin.jsp";
	    	</c:if>
	    	
	    	<c:if test="${mbrId != null}">
		        $('#itemId').text(itemId);
		        let html = `<li class="list-group-item" id="report">An itemreport</li>`;
		        $('#itemReportModal').modal('show');
		    </c:if>
		}
		
		function insertReport() {
			if ($('#inputDescription').val() == "") {
				alert("請填寫原因");
				return;
			}
			
			var url = "${pageContext.request.contextPath}/front/itemreport?action=insert&itemId=${item.itemId}&description=" + $('#inputDescription').val();
						
			$.ajax({
				type: "POST",
				url: url,
				success: function (data) {
					$('#itemReportModal').modal('hide');
					
					Swal.fire({
						backdrop: false,
				        title: "檢舉成功",
				        text: "請至我的檢舉查看",
				        icon: "success",
				        timer: 2700,
		        		showConfirmButton: false,
				        customClass: {
				        	popup: 'report-custom-popup-class',
				        },
				        iconColor: '#b0c4de',
				        didClose: () => {
				            location.reload();
				        }
				    });
				},
				error: function (xhr) {
					console.log(xhr);
				}
			});
		}
		
// 		    const images = [
//         "${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1",
//         // 添加其他圖片的 URL
//         "${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=2",
//     ];

//     let currentImageIndex = 0;

//     function nextImg() {
//         currentImageIndex = (currentImageIndex + 1) % images.length;
//         document.getElementById("slider_img").src = images[currentImageIndex];
//     }
	</script>
</body>
</html>
