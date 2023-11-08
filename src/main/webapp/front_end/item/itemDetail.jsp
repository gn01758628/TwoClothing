<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品詳情</title>
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            /* font-family: Arial, sans-serif; */
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        div.container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            display: flex;
        }

        div.container div.product-image {
            border: 1px solid blue;
            flex: 1;
        }

        div.container div.product-image img {
            border: 1px solid red;
            max-width: 100%;
            height: 100%;
            display: block;
        }

        div.container div.product-info {
            flex: 1;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        div.container div.product-info ul{
            list-style-type: none;
        }
        

        div.container div.product-info ul li h1 {
            font-size: 24px;
            margin-top: 0;
        }

        div.container div.product-info ul li p {
            font-size: 16px;
            display: inline-block;
        }

        div.container div.product-info ul li input#input_num{
            max-width: 80px;
        }

        div.container div.product-info ul li p.price {
            font-size: 20px;
            color: #fd4444;
        }

        div.container div.product-info ul li div.description {
            margin-top: 20px;
        }

        div.container div.product-info ul button.buy-button {
            background-color: #1a191f;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 18px;
            cursor: pointer;
            margin-top: 20px
        }

        div.container div.product-info ul button.buy-button:hover {
            background-color: #6e6b71;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<!-- https://dummyimage.com/550x650/fcfcfc/0011ff -->
<body>
<form class="form_detail" method="post" action="${pageContext.request.contextPath}/ItemCart/cart" enctype="multipart/form-data">

    <div class="container">
        <div class="product-image">
            <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="Product Image">
        </div>
        <div class="product-info">
            <ul>
                <li>
                    <input type="hidden" name="itemId" value="${item.itemId}"></h1>
                </li>
                <li>
                    <h1 name="itemName" value="${item.itemName}">${item.itemName}</h1>
                </li>
                <li>
                    <p>尺寸：</p><p name="size">${item.size}</p>
                </li>
                <li>
                    <p>新舊程度：</p><p name="grade">${item.grade}</p>
                </li>
                <li>
                    <p>數量：</p><input type="number" name="quantity" id="input_num" min="1" value="1">
                </li>
                <li>
                    <p>剩餘件數：</p><p>${item.quantity}</p>
                </li>
                <li>
                    <p class="price" name="price">$${item.price}</p>
                </li>
                <li>
                    <div class="description">
                        <p>${item.detail}</p>
                    </div>
                </li>
                <li>
                    <div>
                        <a href="">前往此賣家商城查看更多商品===></a>
                    </div>
                </li>
            
<!--                 <button class="buy-button">加入購物車</button> -->
				<input type="hidden" name="mbrId"  value="2">
            	<input type="hidden" name="itemId"  value="${item.itemId}">
				<input type="hidden" name="gotoCart" value="gotoCart">
                <input type="button" class="buy-button" value="加入購物車">
            </ul>
        </div>
    </div>
</form>
<a href="${pageContext.request.contextPath}/ItemCart/cartlist?goto=cart&mbrId=2">查看購物車</a>
    
    	<script>
			$(document).ready(function() {
				$("p[name='size']").each(function () {
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
				})
			})
			$(document).ready(function() {
				$("p[name='grade']").each(function () {
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
				})
			})
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
		</script>
</body>
</html>
