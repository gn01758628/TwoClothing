<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${item.itemName}</title>
	<style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
        }
        form {
            padding: 10px;
            width: 100%;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
        }

        div.container {
            max-width: 800px;
            width: 100%;
            display: flex;
            min-height: 400px;
            max-height: 412px;
        }

        div.downarea {
            border-top:4px solid white;
            max-width: 800px;
            width: 100%;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }

        div.downarea table {
            width: 100%;
            height: 100px;
            table-layout: fixed;
            border-spacing: 20px;
            border-collapse: separate;      
        }

        div.downarea table tr td, th {
            padding: 2px 10px;
            flex:1;
            text-align: left;
        }

        div.downarea table tr th {
            border-bottom: 2px solid gray;            
        }

        div.container div.product-image {
            flex: 1;
            min-width: 50%;
            display: flex;
    		justify-content: right;
    		background-color: #f2f2f2;
        }

        div.container div.product-image img {
/*             border: 1px solid black; */
             max-width: 100%; 
             height: 100%;
            display: block;
        }

        div.container div.product-info {
            flex: 1;
            background-color: #f2f2f2;
            padding: 0px;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            position: relative;
        }
        
        div.container div.product-info ul {
            list-style-type: none;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            padding: 0px;
        }
        
        div.container div.product-info ul li {
            margin: 10px 4px;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            padding: 5px;
        }
        

        div.container div.product-info ul li h1 {
            font-size: 24px;
            margin: 0;
        }        
        
        div.container div.product-info ul li h1 span {
    		margin-right: 50px;
		}

        div.container div.product-info ul li p {
            font-size: 16px;
            display: inline-block;
            margin: 2px 0;
        }
        
        /* 收藏 */ 
        .heart-container {
        	--heart-color: rgb(255, 91, 137);
  			position: relative;
		  	width: 20px;
		  	height: 20px;
		  	transition: .3s;
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
  			display: block
		}

		.heart-container .checkbox:checked~.svg-container .svg-celebrate {
  			display: block
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
		
		.message {
			display: none;
            color: gray;
            position: relative;
    		top: 100%; /* 設置相對於父元素的垂直位置，你可以調整這個數值 */
    		transform: translateY(-50%); /* 垂直置中 */
    		margin-left: auto;
        }

        div.container div.product-info ul li.li_num{
            margin-bottom: 0px;
            padding-bottom: 0px;
        }
        div.container div.product-info ul li.li_quantity{
            display: inline-block;
            margin-top: 0px;
            padding-top: 0px;

        }
        div.container div.product-info ul li p.p_quantity{
            font-size: 12px;

        }
        div.container div.product-info ul li.li_quantity p:nth-child(2){
            margin-left: 5px;
        }

        div.container div.product-info ul li p.price {
            font-size: 20px;
        }

        li.li_num{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        li.li_num input{
            max-width: 73px;
            border-radius: 15px;
            padding: 4px 7px;
        }

        div.container div.product-info ul li div.description {
            margin-top: 20px;
        }

        div.container div.product-info ul input.buy-button {
            background-color: #1a191f;
            color: #fff;
            border: none;
            padding: 5px 10px;
            font-size: 14px;
            cursor: pointer;
            margin-top: 20px;
            border-radius: 15px;
        }

        div.container div.product-info ul button.buy-button:hover {
            background-color: #6e6b71;
        }
        
        div.toSeller{
            width: 0;
            height: 0;
            border-top: 100px solid transparent;
            border-right: 120px solid rgb(243, 170, 107);
            display: flex;
            flex-direction: row;
            margin-left: auto;
        }
        
        div.toSeller:hover{
            border-right-color: rgb(249, 194, 145);

        }
        
        div.toSeller:hover a{
            color: white;

        }
        
        div.toSeller a{
            position: absolute;
            bottom: 5px;
            right: 5px;
            text-decoration: none;
            margin-left: auto;
            color: rgb(0, 0, 0);
            padding: 2px;
            font-size: 17px;
        }
        
/*         項目條 */
        button.carousel-indicators [data-bs-target]{
        	background-color: gray;
        }
        
        .carousel-inner{
        	height: 100%;
/*         	width:0; */
        }

/*         圖片容器 */ 
        .carousel-item{
        	height: 100%; 
/*   			width: 400px;  */
         }
         
         .carousel-item active{ 
         	height: 100%; 
/* 	  		width: 400px;  */
         } 
    </style>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<form class="form_detail" method="post" action="${pageContext.request.contextPath}/ItemCart/cart" enctype="multipart/form-data">

    <div class="container">
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
                    <input type="hidden" name="itemId" value="${item.itemId}"></h1>
                <li>
                    <h1 name="itemName" value="${item.itemName}">${item.itemName}</h1>
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
        
       	<span class="message" id="successMessage">移除成功</span>
	<form class="form_detail" method="post" action="${pageContext.request.contextPath}/ItemCart/cart" enctype="multipart/form-data">
	    <div class="container">
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
	                    <input type="hidden" name="itemId" value="${item.itemId}"></h1>
	                <li style="display: flex; flex-direction: row; justify-content: center; align-items: center; margin: 10px 4px; padding: 5px;">
	                    <h1 name="itemName" value="${item.itemName}">
	                    	<span>${item.itemName}</span>
	                    </h1>
	                    
	                    <div class="heart-container" title="Like">
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
	<a href="${pageContext.request.contextPath}/ItemCart/cartlist?goto=cart&mbrId=2">查看購物車</a>
		
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    	<script>
	    	$(document).ready(function() {
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
	        var quantityValue;
	    	$("#input_num").on("blur", function() {
	    		quantityValue = $("#input_num").val();
	    		
	    	});
	    	console.log(quantityValue);
			 $(".buy-button").on("click",function(){
			    let url="${pageContext.request.contextPath}/ItemCart/cart?itemId=${item.itemId}&mbrId=2&quantity="+quantityValue+"&gotoCart=gotoCart";
			    console.log(url);
			    fetch(url)
	            .then(function(response){
	            return response.text();
	            })
	            .then(function(data){
	            console.log(data);
	            })
	            .catch(function(error){
	            console.log(error);
	            })
		})
		
		$("#Give-It-An-Id").on("click", function () {
		    $(this).toggleClass("active");
		    
		    if ($(this).hasClass("active")) {
	            insertItem();
	        } else {
	            deleteItem();
	        }
		});
			 
		function insertItem() {
			var itemId = "${item.itemId}";
			var url = "${pageContext.request.contextPath}/itemtrackinglist.check?action=insert&itemId=" + itemId;
			
			$.ajax({
		        type: "POST",
		        url: url,
		        success: function (data) {
		            console.log(data);
		        },
		        error: function (xhr) {
		            if (xhr.status === 403) {
		                window.location.href = "${pageContext.request.contextPath}/front_end/members/registerLogin.jsp";
		            } else {
		                console.log(xhr);
		            }
		        }
		    });
		}
		
		function deleteItem() {
			var itemId = "${item.itemId}";
			var url = "${pageContext.request.contextPath}/itemtrackinglist.check?action=delete&itemId=" + itemId;
			
			$.ajax({
		        type: "POST",
		        url: url,
		        success: function (data) {
		        	$("#successMessage").show().delay(2000).fadeOut();
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
