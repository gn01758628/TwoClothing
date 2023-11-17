<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        form{
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

        div.downarea{
            border-top:4px solid white;
            max-width: 800px;
            width: 100%;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
        }

        div.downarea table{
            width: 100%;
            height: 100px;
            table-layout: fixed;
            border-spacing: 20px;
        }

        div.downarea table tr td, th{
            padding: 2px 10px;
            flex:1;
            text-align: left;
        }

        div.downarea table tr th{
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
        div.container div.product-info ul{
            list-style-type: none;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            padding: 0px;
        }
        div.container div.product-info ul li{
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

        div.container div.product-info ul li p {
            font-size: 16px;
            display: inline-block;
            margin: 2px 0;
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
			 $(".buy-button").on("click",function(){
			    let url="${pageContext.request.contextPath}/ItemCart/cart?itemId=${item.itemId}&mbrId=2&quantity=${item.quantity}&gotoCart=gotoCart";
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
