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

div.leftMain {
	height: 100%;
	max-height: 100%;
	overflow: auto;
	padding: 0px 10px;
}

div.detailBox {
	/* border: 1px solid black; */
	/* height: 150px; */
	border-radius: 0.5rem;
	background-color: lightgrey;
	width: 1000px;
	padding: 30px 60px;
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
	width: 250px;
}

div.detailBox div.info {
	height: 100%;
	width: 300px;
	display: flex;
	flex-direction: column;
	padding: 25px 10px;
}

div.detailBox div.info p.item-name {
	font-size: 28px;
	margin: 0;
	height: 30%;
	margin-bottom: 5px;
}

div#detailBox div.info p.item-description {
	font-size: 16px;
	margin: 0;
	height: 70%;
}

div.detailBox table {
	border-collapse: separate;
	border-spacing: 10px;
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
}

div.rightMain {
	border: 1px solid black;
	padding: 40px;
	margin-left: 40px;
	width: 20%;
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

button.btn-primary {
	background-color: gray;
	border-color: black;
}

button.btn-primary:hover {
	background-color: black;
}

button.btn-primary:focus {
	background-color: gray;
	border-color: black;
}

button#btn_checkbox {
	
}

button.close_x {
	
}

label.Coupon input.coupon_radio:disabled+span {
	background-color: #FFFFFF;
	color: lightgray;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="leftMain">
		<div>您的購物車是空的!</div>
		<c:forEach var="item" items="${itemList}" varStatus="loop">
			<div class="detailBox">
				<input type="checkbox" class="checkboxLeft" checked
					onchange="countEverything()"> <img class="itemImg"
					src="https://dummyimage.com/600x400/000/333.png&text=image+test"
					alt="圖片">
				<div class="info">
					<p style="display: none" name="itemId" class="item-id"
						data-item-id="${item.itemId}">${item.itemId}</p>
					<p class="item-name">${item.itemName}</p>
					<p class="item-description">Lorem ipsum dolor sit amet
						consectetur, adipisicing elit. Nihil animi doloremque mollitia</p>
				</div>
				<table>
					<tr class="trth">
						<th style="width: 100px; max-width: 100px;">尺寸</th>
						<th style="width: 100px; max-width: 100px;">數量</th>
						<th style="width: 100px; max-width: 100px;">小計</th>
					</tr>
					<tr class="trtd">
						<td name="size" style="width: 100px; max-width: 100px;">${item.size}</td>
						<td style="width: 100px; max-width: 100px;"><input
							type="number" name="quantity" min="1" max="${item.quantity}" onchange="countEverything()"
							value="${quantities[loop.index]}"></td>
						<td name="price" style="width: 100px; max-width: 100px;"><input
							type="hidden" value="${item.price}"> <span>${item.price*quantities[loop.index]}</span>
							<input type="hidden" value="${item.quantity}"></td>
					</tr>
				</table>

				<div class="del-box">
					<button class="close_x">X</button>
				</div>
			</div>
		</c:forEach>
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
			</div>
			<div class="total">
				合計$<span>0</span>
			</div>
			<button name="pay">結帳</button>
		</div>
	</div>

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
						<label class="Coupon"> <input class="coupon_radio"
							id="coupon_radio_${loop.index}" type="radio" name="cpnName"
							id="Coupon" disabled> <input class="coupon_minAmount"
							id="coupon_minAmount_${loop.index}" type="hidden"
							name="minAmount" value="${coupon.minAmount}"> <input
							class="coupon_distype" id="coupon_distype_${loop.index}"
							type="hidden" name="disType" value="${coupon.disType}"> <input
							class="coupon_disvalue" id="coupon_disvalue_${loop.index}"
							type="hidden" name="disValue" value="${coupon.disValue}">
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



	<!--bootstrap5 js-->
	<script
		src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$("td[name='size']").each(function() {

				let status = $(this).text();
// 				console.log(status);
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
				case "9":
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
			});
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
	}
	
	$(document).ready(function() {	
		countEverything();
	});
	</script>
</body>
</html>