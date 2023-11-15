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
            height: 100%;
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
        }

        div.container div.product-image img {
            border: 1px solid black;
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
                    <input type="hidden" name="itemId" value="${item.itemId}"></h1>
                <li>
                    <h1 name="itemName" value="${item.itemName}">${item.itemName}</h1>
                </li>
                <li>
                    <p class="price" name="price">$${item.price}</p>
                </li>
<!--                 <li> -->
<%--                     <p>尺寸：</p><p name="size">${item.size}</p> --%>
<!--                 </li> -->
<!--                 <li> -->
<%--                     <p>新舊程度：</p><p name="grade">${item.grade}</p> --%>
<!--                 </li> -->
                <li class="li_num">
                    <p>數量：</p><input type="number" name="quantity" id="input_num" min="1" value="1" max="${item.quantity}">
                </li>
                <li class="li_quantity">
                    <p class="p_quantity">剩餘件數</p><p>${item.quantity}</p>
                </li>
<!--                 <li> -->
<!--                     <div class="description"> -->
<%--                         <p>${item.detail}</p> --%>
<!--                     </div> -->
<!--                 </li> -->
<!--                 <li> -->
<!--                     <div> -->
<%--                         <a href="${pageContext.request.contextPath}/SellerHome/home?mbrId=${item.mbrId}">前往此賣家商城查看更多商品===></a> --%>
<!--                     </div> -->
<!--                 </li> -->
            
<!--                 <button class="buy-button">加入購物車</button> -->
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
                    <td>${item.size}</td>
                    <td>${item.grade}</td>
                </tr>
            </table>
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
				});
	
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
	</script>
</body>
</html>
