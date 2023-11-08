<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品確認</title>
   

<style>
    *{
        box-sizing: border-box;
    }

    div.container{
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin: 40px;
        width: calc(100% - 80px);
    }

    div.container div.item_check{
        border: 1px solid black;
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        padding: 10px;
        margin:10px 0px;
    }

    div.container div.item_check div.imgContainer{
        height: 50px;
        width: 50px;
    }

    img.itemImg{
        height: 100%;
        width: 100%;
    }

    div.container div.item_check table{
        flex-grow: 2;
        
    }
    
    div.container div.item_check table tr.trtd td{
        text-align: center;
    }

    div.under_detail{
        border: 1px solid blue;
        display: flex;
        flex-direction: row;
        justify-content: center;
        justify-content: space-between;
        margin-top: 50px;
        position: relative;
        
    }

    div.under_detail div.underLeft{
        width: 40%;
        padding: 0 10px;
    }

    div.under_detail div.underLeft p{
        /* border: 1px solid yellowgreen; */
        display: flex;
        flex-direction: column;
        margin: 10px 0px;
    }

    div.under_detail div.underLeft p label{
        margin: 10px 0px;
    }

    div.under_detail div.underRight{
        border: 1px solid orange;
        text-align:right;
        padding-right: 60px;
        position: absolute;
        bottom: 10px;
        right: 0px;
        width: 50%;
    }
    div.under_detail div.underRight table.under_table {
        border: 1px solid orchid;
        display: flex;
        flex-direction: row;

        
    }

    div.under_detail div.underRight table.under_table tbody{
        display: flex;
        flex-direction: row;
        margin-left: auto;
        margin-right: 0; 
        width: 40%;
    }

    div.under_detail div.underRight table.under_table tr{
        display: flex;
        flex-direction: column;
        justify-content: center;
        width: 100%;
    }

    div.under_detail div.underRight div, button{
        margin: 5px 0;
        font-size: 16px;
    }

    div.under_detail div.underRight button{
        width: 50%;
        margin-top: 20px;
    }


</style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1>商品確認</h1>
    <div class="container">
    	<c:forEach var="item" items="${itemList}" varStatus="loop">
	        <div class="item_check">
	            <div class="imgContainer">
	            <img class="itemImg" src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="Product Image">
	            </div>
	            <div class="info">
	            	<input type="hidden" name="itemId" value="${item.itemId}" class="itemid">
	                <p class="item-name">${item.itemName}</p>
	            </div>
	            <table>
	                <tr class="trth">
	                    <th style="width: 100px;max-width: 100px;">尺寸</th>
	                    <th style="width: 100px;max-width: 100px;">數量</th>
	                    <th style="width: 100px;max-width: 100px;">單價</th>
	                    <th style="width: 100px;max-width: 100px;">小計</th>
	                </tr>
	                <tr class="trtd">
	                    <td class="size">${item.size}</td>
	                    <td class="quantity">${quantities[loop.index]}</td>
	                    <td>${item.price}</td>
	                    <td>${item.price*quantities[loop.index]}
	                    	<input type="hidden" class="subTotal" value="${item.price*quantities[loop.index]}">
	                    </td>
	                </tr>
	            </table>
	        </div>
	     </c:forEach>
        <div class="under_detail">
            <div class="underLeft">
                <p>付款方式：
                    <label for="creditcard"><input type="radio" name="payMethod" id="creditcard" value="0">信用卡</label>
                    <label for="translation"><input type="radio" name="payMethod" id="translation" value="1">轉帳</label>
                    <label for="wallet"><input type="radio" name="payMethod" id="wallet" value="2">虛擬錢包</label>	                    
                </p>
                <hr>
                <p class="information">收件資訊：
                    <label for="">收件人</label>
                    <input id="receiveName" type="text" value="${shipSetting.receiveName}">
                    <label for="">連絡電話</label>
                    <input id="receivePhone" type="text" value="${shipSetting.receivePhone}">
                    <label for="">送貨地址</label>
                    <input id="receiveAddress" type="text" value="${shipSetting.receiveAddress}">
                </p>

            </div>
            <div class="underRight">
                <table class="under_table">
                    <tbody>
                        <tr>
                            <th>總金額：</th>
                            <th>本次訂單折：</th>
                            <th>合計：</th>
                        </tr>
                        <tr>
                            <td class="add_price">
                            	<span></span>
                            	<input type="hidden" value="0" name="addPrice">
                            </td>
                            <td class="count">$${cartCount}
                            	<input type="hidden" value="${cartCount}" name="cartCount">
                            </td>	
                            <td class="total">
                            	<span></span>
                            	<input type="hidden" value="0" name="totalPay">
                            </td>
                        </tr>
                    </tbody>
                </table>
                <button type="submit" class="order">下訂單</button>
            </div>
        </div>
    </div>
    
    <script>
    	//存商品id、購買數量
		const eachitemId = [];
   		const eachquantities = [];
   		
   		//存比例扣
   		var output = [];
   		
   		var selectedOption='';
   		
   		$(document).ready(function() {
   			
   			//顯示尺寸
			$(".trtd .size").each(function () {
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
   			
   			
   			//將小計相加為總計(未扣折扣)
   			var total = 0;
   			$(".subTotal").each(function(){
   				total += parseInt($(this).val());
   			});
    		$(".add_price span").text("$" + total);
    		$(".add_price input").val(total);
   		
	   		//計算總額(有折扣)
	   		let totalPay = total - $(".count input").val();
	   		$(".total input").val(totalPay);
	   		$(".total span").text("$"+totalPay);
	   		
	   		//依小計計算折扣比例
	   		let subval = $(".trtd input.subTotal").map(function() {
	   		    return $(this).val();
	   		}).get();
	   		let mytotal = $(".add_price input").val();
	   		let discount = $(".count input").val();
	   		
	   		if(subval.length && discount){
	   			output = subval.map((data)=>(discount*data/mytotal));
	   			for(let i = 1; i<output.length; i++){
	   				output[0] += (output[i]-Math.floor(output[i]));
	   				output[i] = Math.floor(output[i]);
	   			}
	   			output[0] = Math.floor(output[0]);
	   		}
	   		console.log(output);
	   			
	   		//取得付款方式
		   	$('input[type="radio"]').change(function() {
		        if ($(this).is(':checked')) {
		          selectedOption = $(this).val();
		          console.log('已選中的付款方式：', selectedOption);
		        }
		   	});
		   	 
		   	 
			//取得商品id與對應的數量
	   	    $(".item_check").each(function() {
	   	        let itemId = $(this).find(".itemid").val();
	   	        let quantity = $(this).find(".quantity").text();
	   			eachitemId.push(itemId);
	   			eachquantities.push(quantity);	   		
	   	    });	
			
   		});
   		
   		//下訂單按鈕將資料傳輸
   		$(".order").click(function(){
			if(!($("input[type='radio']:checked").length)){
				alert("請選擇付款方式");
				return;	
			}
	   		let cartData={
	   				itemId : eachitemId,
	   				quantity : eachquantities,
	   				payment : selectedOption,
	   				receiveName : $("#receiveName").val(),
	   				receivePhone : $("#receivePhone").val(),
	   				receiveAddress : $("#receiveAddress").val(),
	   				mytotal : $(".add_price input").val(),
	   				count : $(".count input").val(),
	   				eachCount : output,
	   				totalPay : ($(".add_price input").val()) - ($(".count input").val())
	   		};
	   		console.log(cartData);
   			
	   		let formDataUrlEncoded = new URLSearchParams(cartData);
	   		
            fetch("${pageContext.request.contextPath}/ItemCart/toOrder", {
                method: "post",
                body: formDataUrlEncoded
            })
            .then(function (response) {
            	return response.text();
            })
            .then(function (data) {
            	console.log(data);
            });
   		});
   		
    </script>  
</body>
</html>