<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
* {
	box-sizing: border-box;
}

div[name="block"] {
	display: inline-block;
	border: 1px solid #999;
	vertical-align: top;
	margin: 10px;
	padding: 10px;
}

.seller-div {
	border: 1px solid #000;
	margin-top: 10px;
	padding: 10px;
}
</style>
<title>賣家與商品選擇</title>
</head>
<body>
	<div>
		<a href="ItemOrderServlet.check?action=getMembersItems">獲取商場資料</a>
	</div>

	<form action="ItemOrderServlet.check" method="Post">
		<div>
			<div name="block" style="width:30%;">
				<div>
					<label>選擇買家編號：</label> <select id="buyer" name="buyer">
						<c:forEach items="${memberList}" var="memberItem">
							<option value="${memberItem.mbrId}">${memberItem.mbrName}</option>
						</c:forEach>
					</select>
					<input type="submit" name="action" value="buyer">
				</div>
				<div>
					<label>選擇賣家編號：</label> <select id="seller" name="seller">
						<c:forEach items="${memberList}" var="memberItem">
							<option value="${memberItem.mbrId}">${memberItem.mbrName}</option>
						</c:forEach>
					</select>
					<input type="submit" name="action" value="seller">
				</div>

				<div>
					<label>選擇商品：</label> <select id="item">
						<c:forEach items="${itemList}" var="itemItem">
							<option value="${itemItem.itemId}">${itemItem.itemName}</option>
						</c:forEach>
					</select>
				</div>

				<div>
					<label>選擇數量：</label> <select id="amount">
						<c:forEach var="i" begin="1" end="10">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>

				<button type="button" id="the_button">新增商品</button>
				<button type="button" id="the_clear">清空購物車</button>
				<button type="button" id="all_clear">清空所有購物車</button>
				<button type="button" id="checkout_button">生成訂單</button>
			</div>
			<div name="block" style="width:60%;">
				<div id="cart"></div>
			</div>
		</div>
	</form>

	<!--bootstrap5 js-->
	<script src="/TwoClothing/js/bootstrap5/popper.min.js"></script>
	<script src="/TwoClothing/js/bootstrap5/bootstrap.min.js"></script>
	<!--jQuery-->
	<script src="/TwoClothing/js/jQuery/jquery-3.7.1.min.js"></script>

	<script>
		$(document).ready(function() {
			// 當文件載入完成時執行
			var buyerSelect = document.getElementById('buyer');
			var cartDiv = document.getElementById('cart');
			// 初始化買家編號為1
			var currentBuyerId = 1;
			
			$("#the_button").click(function() {
				var buyerId = $("#buyer").val();
				var sellerId = $("#seller").val();
				var itemId = $("#item").val();
				var amount = $("#amount").val();
				
				var itemObject = {
					sellerId : sellerId,
					itemId : itemId,
					amount : amount
				};

				// 從localStorage中取出資料
				let existingData = localStorage.getItem('buyerMap');

				if (existingData) {
					// 如果localStorage中已經有資料
					let buyerMap = JSON.parse(existingData);

					// 檢查買家的購物清單中是否已經包含了相同的賣家和商品
					if (buyerMap[buyerId]) {
					    var existingItem = buyerMap[buyerId].find(function(item) {
					        return item.sellerId === sellerId && item.itemId === itemId;
					    });

					    if (existingItem) {
					        // 如果已經存在相同的賣家和商品，將其數量相加
					        let existingAmount = parseInt(existingItem.amount);
							let newAmount = parseInt(itemObject.amount);
					        existingItem.amount = (existingAmount + newAmount).toString()
					        
					    } else {
					        // 如果不存在相同的賣家和商品，將新項目加入
					        buyerMap[buyerId].push(itemObject);
					        // 對賣家的購物清單進行排序
					        buyerMap[buyerId].sort(function(a, b) {
					            // 先按照賣家編號升序排列
					            if (a.sellerId !== b.sellerId) {
					                return a.sellerId - b.sellerId;
					            }
					            // 如果賣家編號相同，再按照商品編號升序排列
					            return a.itemId - b.itemId;
					        });
					    }
					} else {
					    // 如果買家的購物清單中還沒有賣家，創建一個新的物件LIST
					    buyerMap[buyerId] = [itemObject];
					}

					// 更新localStorage中的資料
					localStorage.setItem('buyerMap',JSON.stringify(buyerMap));
				} else {
					// 如果localStorage中沒有資料，創建一個新的buyerMap並加入第一個物件
					var newBuyerMap = {};
					newBuyerMap[buyerId] = [ itemObject ];

					// 將buyerMap轉換為JSON字串並存儲在localStorage中
					localStorage.setItem('buyerMap',JSON.stringify(newBuyerMap));
				}
				displayCart();
			});

			// 清除 localStorage 的按鈕點擊事件
			$("#the_clear").click(function() {
				var buyerId = $("#buyer").val(); // 獲取要刪除的賣家的 ID
				
				// 從 localStorage 中取得資料
				var existingData = JSON.parse(localStorage.getItem('buyerMap'));
		
				// 如果 localStorage 中已經有資料並且要刪除的賣家存在於資料中
				if (existingData && existingData[buyerId]) {
					delete existingData[buyerId]; // 刪除特定賣家的資料
					localStorage.setItem('buyerMap',JSON.stringify(existingData)); // 更新 localStorage
					alert('買家 ' + buyerId+ ' 的購物清單已刪除！'); // 提示用戶
				} else {
					alert('找不到買家 ' + buyerId+ ' 的購物清單！'); // 如果資料不存在，提示用戶
				}
				displayCart();
			});

			$("#all_clear").click(function() {
				localStorage.clear(); // 清除 localStorage 中的所有資料
				displayCart();
				alert('已清除所有資料！'); // 提示用戶資料已清除
			});
			
			
			$("#checkout_button").click(function() {
				let existingData = localStorage.getItem('buyerMap');
				let buyerMap = JSON.parse(existingData);
			    // 獲取當前買家的購物清單
			    var currentBuyerCart = buyerMap[currentBuyerId] || [];

			    // 設置需要傳遞的資料，包括"action"參數
			    var requestData = {
			    	buyerId:currentBuyerId,
			        cartData: currentBuyerCart // 其他需要傳遞的資料
			    };

			    // 發送AJAX請求
			    $.ajax({
			        type: "POST",
			        url: "ItemOrderServlet.check?action=addOrder",  
			        data: JSON.stringify(requestData),
			        contentType: "application/json; charset=utf-8",
			        dataType: "json",
			        success: function(response) {
			            // 成功結帳後的處理邏輯，例如顯示成功消息、重置購物車等
			            alert("結帳成功！");
			            // 清空當前會員的購物車
			            buyerMap[currentBuyerId] = [];
			            // 更新localStorage中的資料
			            localStorage.setItem('buyerMap', JSON.stringify(buyerMap));
			            // 重新顯示購物車
			            displayCart();
			        },
			        error: function(xhr, status, error) {
			            console.log(xhr.responseText); // 这里会打印出详细的错误信息
			            alert("結帳失敗：" + error);
			        }
			    });
			});

			
			
		
		
			// 顯示購物清單的函數
			function displayCart() {
			    // 獲取當前買家的購物清單
			    let existingData = localStorage.getItem('buyerMap');
				let buyerMap = JSON.parse(existingData);
				
				let currentBuyerCart = (buyerMap && buyerMap[currentBuyerId]) || [];
			    // 清空 cartDiv 的內容
			    cartDiv.innerHTML = '';

			    // 顯示當前會員ID
			    var memberIdDiv = document.createElement('div');
			    memberIdDiv.textContent = "當前會員ID: " + currentBuyerId;
			    cartDiv.appendChild(memberIdDiv);

			    // 以賣家編號為鍵，儲存不同賣家的商品
			    var sellerItems = {};

			    // 遍歷購物清單，按照賣家編號分類商品
			    currentBuyerCart.forEach(function(item) {
			        var sellerId = item.sellerId;

			        // 如果這個賣家的商品還不存在，創建一個新的
			        if (!sellerItems[sellerId]) {
			            sellerItems[sellerId] = [];
			        }

			        // 將商品和數量加入對應賣家的商品列表中
			        var itemInfo = '商品編號: ' + item.itemId + ', 數量: ' + item.amount;

			        // 創建商品顯示的div元素
			        var itemElement = document.createElement('div');
			        itemElement.textContent = itemInfo;
			        itemElement.style.marginLeft = '20px'; // 設置左邊距為20像素（可以根據需要調整）

			        // 將商品div加入對應賣家的商品列表中
			        sellerItems[sellerId].push(itemElement);
			    });

			    // 將不同賣家的商品顯示在cartDiv中
			    Object.keys(sellerItems).forEach(function(sellerId) {
			        // 顯示賣家編號
			        var sellerDiv = document.createElement('div');
			        sellerDiv.className = "seller-div"; // 添加class
			        sellerDiv.textContent = "賣家編號: "+ sellerId;
			        
			        cartDiv.appendChild(sellerDiv);

			        // 顯示賣家的商品
			        sellerItems[sellerId].forEach(function(itemElement) {
			            cartDiv.appendChild(itemElement);
			        });
			    });
			}

		
		
			// 更新買家編號的事件監聽器
			buyerSelect.addEventListener('change', function(event) {
			    currentBuyerId = parseInt(event.target.value);
			    // 在選擇框改變時，將當前買家的購物清單顯示在 cartDiv 中
			    displayCart();
			});
		
		

			// 初始化時顯示當前買家的購物清單
			displayCart();

		});
	</script>
</body>
</html>
