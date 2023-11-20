<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!--bootstrap5 css-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
	<style>
		* {
			box-sizing: border-box;
		}
		
		body {
			display: flex;
			justify-content: center;
			flex-direction: row;
			margin: 10px;
			height: calc(100vh - 20px);
		}
		
		form.form_cart{
			display:flex;
			flex-direction: row;
		    justify-content: center;
		    max-width: 1300px;
		    width: 1300px;
		    border: 1px solid;
		}
		
		div.leftMain {
			height: 100%;
			max-height: 100%;
			overflow: auto;
			padding: 0px 10px;
			border:1px solid red; 
			width:875px;
			display:flex;
			flex-direction: column;
/* 		    justify-content: center; */
		    align-items: center;
		}
		
		div.detailBox {
			/* border: 1px solid black; */
			/* height: 150px; */
			border-radius: 1rem;
			background-color: lightgrey;
			max-width:850px;
			width: 850px;
			padding: 30px 35px;
			display: flex;
			justify-content: space-around;
			align-items: center;
			margin-bottom: 10px;
		}
		
		div.detailBox>* {
			padding: 0px 10px;
		}
		
		div.detailBox input[type='checkbox'] {
			
		}
		
		div.detailBox img.itemImg {
			width: 120px;
/* 		    height: 100px; */
		    margin-left: 30px;
		}
		
		div.detailBox div.info {
			height: 100%;
			width: 300px;
			display: flex;
			flex-direction: column;
			padding: 25px 10px;
		}
		
		div.detailBox div.info p.item-name {
			font-size: 22px;
			margin: 0;
			height: 30%;
			margin-bottom: 5px;
			text-align: center;
		}
		
		div#detailBox div.info p.item-description {
			font-size: 16px;
			margin: 0;
			height: 70%;
		}
		
		div.detailBox table {
			border-collapse: separate;
			border-spacing: 10px;
			width: 50%;
		}
		
		div.detailBox table tr.trth th {
			border-bottom: 1px solid black;
			padding-bottom: 5px;
			text-align: center;
		}
		
		div.detailBox table tr.trtd {
			text-align: center;
		}
		
		div.detailBox table tr.trtd input[type='number'] {
			padding: 5px;
			width: 50px;
			background: transparent;
			border: 0px;
			outline: none;
			text-align: right;
		}
		
		div.detailBox div.del-box button {
			padding: 5px;
			cursor: pointer;
			background-color: rgba(0, 0, 0, 0);
			border: 0px;
		}
		
		button.close_x img{
			width:35px;
			height:35px;
		}
		
		div.rightMain {
			border: 1px solid black;
			padding: 25px;
			margin-left: 40px;
			width: 300px;
			display: flex;
			justify-content: center;
			background-color: lightgrey;
			border-radius: 0.5rem;
			margin-bottom: 10px;
			position: sticky;
			top: 8px;
			align-items: center;
		}
		
		div.rightMain div.rightInner {
			/*             border: 1px solid red; */
			/* 	position: fixed; */
			bottom: 20%;
			width: 100%;
		}
		
		div.rightMain div.rightInner>label {
			/*             border: 1px solid blue; */
			font-size: 20px;
			display: inline-block;
			margin-bottom: 20px;
			border-bottom: 1px solid gray;
			width: 100%;
			padding-bottom: 20px;
		}
		
		div.rightMain div.rightInner>div {
			border: 1px solid black;
			font-size: 20px;
			text-align: center;
			margin: 20px 0px;
			margin-bottom: 34px;
		}
		
		div.rightMain div.rightInner button {
			width: 100%;
			height: 35px;
		
		}
		
		
		div.rightMain div.rightInner input.btn-primary{
			width: 100%;
			height: 35px;
		}
		
		
		.btn-primary {
			background-color: gray;
			border-color: black;
		}
		
		.btn-primary:hover {
			background-color: black;
		}
		
		.btn-primary:focus {
			background-color: gray;
			border-color: black;
		}
		
		
		label.Coupon input.coupon_radio:disabled+span {
			background-color: #FFFFFF;
			color: lightgray;
		}
		img.cart_empty{
			width:540px;
			height:540px;
 			display: none;
		}
		.display_none{
			display: block;
		}
	
	</style>
</head>
<body>
	<form class="form_cart" method="post" action="${pageContext.request.contextPath}/ItemCart/toPay" enctype="multipart/form-data">
		<div class="leftMain">
<!-- 			<div>您的購物車是空的!</div> -->
			<c:forEach var="item" items="${itemList}" varStatus="loop">
				<div class="detailBox">
					<input type="checkbox" name="itemIdCheck" class="checkboxLeft" checked
						onchange="countEverything()" value="${item.itemId}"> <img class="itemImg"
						src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1"
						alt="Product Image">
					<div class="info">
						<p style="display: none" name="itemId" class="item-id"
							data-item-id="${item.itemId}">${item.itemId}</p>
						<p class="item-name">${item.itemName}</p>
					</div>
					<table>
						<tr class="trth">
							<th style="width: 100px; max-width: 100px;">尺寸</th>
							<th style="width: 100px; max-width: 100px;">數量</th>
							<th style="width: 100px; max-width: 100px;">小計</th>
						</tr>
						<tr class="trtd">
							<td name="size" style="width: 100px; max-width: 100px;">${item.size}</td>
							<td style="width: 100px; max-width: 100px;">
								<input type="number" name="quantity" min="1" max="${item.quantity}" onchange="countEverything()" value="${quantities[loop.index]}">
							</td>
							<td name="price" style="width: 100px; max-width: 100px;">
								<input type="hidden" value="${item.price}">
								<span>${item.price*quantities[loop.index]}</span>
								<input type="hidden" value="${item.quantity}">
							</td>
						</tr>
					</table>
	
					<div class="del-box">
						<button class="close_x"><img src="${pageContext.request.contextPath}/images/cart/garbage1.png"></button>
					</div>
				</div>
			</c:forEach>
			<img class="cart_empty" src="${pageContext.request.contextPath}/images/cart/Shopping.png">
					
		</div>
		<div class="rightMain">
			<div class="rightInner">
				<label class="checkPoint">
					<input type="checkbox" name="mbrPoint" id="point" onchange="countEverything()">
					使用會員點數 <span>${mbrPoint}</span>點
				</label>
				<button class="choice_cou" type="button" class="btn btn-primary"
					data-bs-toggle="modal" data-bs-target="#exampleModal">選取優惠券</button>
				<div class="count">
					本次訂單折$<span>0</span>
					<input type="hidden" name="cartCount" value="0">
				</div>
				<div class="total" name="cartTotal">
					合計$<span>0</span>
				</div>
				<input type="hidden" name="toPay" value="toPay">
				<input class="btn-primary" type="submit" value="去買單">
<!-- 				<button name="pay">去買單</button> -->
			</div>
		</div>
	</form>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">您擁有的優惠券</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<c:forEach var="coupon" items="${couponList}" varStatus="loop">
						<label class="Coupon"> 
							<input class="coupon_radio" id="coupon_radio_${loop.index}" type="radio" name="cpnName" id="Coupon" disabled> 
							<input class="coupon_minAmount" id="coupon_minAmount_${loop.index}" type="hidden" name="minAmount" value="${coupon.minAmount}"> 
							<input class="coupon_distype" id="coupon_distype_${loop.index}" type="hidden" name="disType" value="${coupon.disType}"> 
							<input class="coupon_disvalue" id="coupon_disvalue_${loop.index}" type="hidden" name="disValue" value="${coupon.disValue}">
							<span>${coupon.cpnName}</span>
						</label>
						<br>
					</c:forEach>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal" onclick="countEverything()">確定</button>
					<!-- 					<button id="btn_checkbox" type="button" class="btn btn-primary">確定</button> -->
				</div>
			</div>
		</div>
	</div>


	<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>	

	<!--bootstrap5 js-->
	<script
		src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
		
	<script>
		$(document).ready(function() {
			$("td[name='size']").each(function() {

				let status = $(this).text();
				switch (status) {
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
	</script>
	<script>
		$(document).ready(function() {
			$(".close_x").click(function() {
				// 找到包含 "X" 按鈕的父元素，即 detailBox
				var $detailBox = $(this).closest(".detailBox");

				// 從父元素中找到包含 itemId 的元素
				var $itemIdElement = $detailBox.find(".item-id");

				// 從該元素中獲取 itemId 的值
				var itemId = $itemIdElement.data("item-id");
				
				 let url="${pageContext.request.contextPath}/ItemCart/cart?itemId=" + itemId + "&delCart=delCart&mbrId=2";
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

				// 然後刪除整個 detailBox 元素
				$detailBox.remove();
				    

				//購物車是否有商品，若無則顯示購物圖片
				var leftMain = $(".leftMain");
				if (leftMain.find('.detailBox').length === 0) {
				    leftMain.find('.cart_empty').show();
				} else {
				    leftMain.find('.cart_empty').hide();
				}
  
				
			});
			
		});
	</script>
	
	<script>
	//會員在購物車更改數量時Redis也要更改
		$("input[name='quantity']").on("blur", function() {
		  	let $currentDetailBox = $(this).closest(".detailBox");
		  	let itemId = $currentDetailBox.find(".item-id").data("item-id");
		 	let quantity = $(this).val();
			console.log("itemId:"+itemId);
			console.log("quantity:"+quantity);
			let url="${pageContext.request.contextPath}/ItemCart/cart?itemId=" + itemId + "&updateCart=updateCart&mbrId=2&quantity="+quantity+"";
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
		});
	</script>

	<script>	
	function countEverything(){
		// 0 total = 0
	    var total = 0;
		// 1 Item
		$(".detailBox").each(function(idx){
			let quantity = ($(".detailBox").eq(idx)).find("input[name='quantity']").val();
			let price = ($(".detailBox").eq(idx)).find("td[name='price'] input").val();
			let subtotal = quantity * price;
			($(".detailBox").eq(idx)).find("td[name='price'] span").text(subtotal);
			
			if(($(".detailBox").eq(idx)).find(".checkboxLeft").is(":checked")){
				total += subtotal;
			}
		});
		
		const originalTotal = total;
		
		// 2 Coupon
		 $(".coupon_radio").each(function(index) {
           	$("#coupon_radio_"+index).removeAttr("disabled");
            let minAmount = $("#coupon_minAmount_"+index).val();
            if ((total < minAmount) || (total == 0)) {
            	$("#coupon_radio_"+index).attr("disabled", "disabled");
            	$("#coupon_radio_"+index).prop("checked", false);
            }
        });
		
     	let distypeVal = 0;
        let disValue = 0;
        
		if($(".coupon_radio:checked").length){
			distypeVal = $(".coupon_radio:checked").closest(".Coupon").find(".coupon_distype").val(); // 找到該優惠券的折扣種
			disValue = $(".coupon_radio:checked").closest(".Coupon").find(".coupon_disvalue").val(); // 找到該優惠券的折扣金額或%數
		}; 
        
        if(distypeVal){
        	total *= (1-(disValue/100));
        	total = Math.round(total);
        } else {
        	total -= disValue;
        }

		// 3 Member Point
		if($('input[name="mbrPoint"]').is(":checked")){
			var mbrPoint = parseInt($(".checkPoint span").text());
		}else{
			var mbrPoint = 0;
		}
		total -= mbrPoint;
		if(total < 0){
			total = 0;
		}
		// 4 Total Show on HTML
		$(".total span").text(total);
		$(".count span").text(originalTotal - total);
		$(".count input").val(originalTotal - total);
	}
	
	$(document).ready(function() {	
		countEverything();
		
		//購物車是否有商品，若無則顯示購物圖片
		var leftMain = $(".leftMain");
		if (leftMain.find('.detailBox').length === 0) {
		    leftMain.find('.cart_empty').show();
		} else {
		    leftMain.find('.cart_empty').hide();
		}
	});

	
	</script>
	
</body>
</html>