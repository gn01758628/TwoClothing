<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.bundle.min.js"></script>
  
   	<style>
	
	.itemOrderDiv{
		border: 2px solid blue;
	}
	.orderDetailItem{
		border: 1px solid deeppink;
	}
	img{
		max-width:250px;
	}
	</style>



<title>待出貨訂單</title>
</head>
<body>
<main> 
<div class="b-example-divider"></div>

  <div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
    <a href="itemorder.check?action= " class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
      <svg class="bi me-2" width="30" height="24"><use xlink:href="#bootstrap"/></svg>
      <span class="fs-5 fw-semibold">返回購物頁面</span>
    </a>
    
        <div class="collapse show" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="itemorder.check?action=seller&seller=${seller}" class="link-dark rounded">所有訂單</a></li>
            <li><a href="itemorder.check?action=seller0&seller=${seller}" class="link-dark rounded">待付款</a></li>
            <li><a href="itemorder.check?action=seller1&seller=${seller}" class="link-dark rounded">待出貨</a></li>
            <li><a href="itemorder.check?action=seller2&seller=${seller}" class="link-dark rounded">已出貨</a></li>
            <li><a href="itemorder.check?action=seller3&seller=${seller}" class="link-dark rounded">已完成</a></li>
            <li><a href="itemorder.check?action=seller4&seller=${seller}" class="link-dark rounded">已取消訂單</a></li>
          </ul>
        </div>
    
  </div>
<div>
<h1>會員編號: ${seller} </h1>
<h2>待出貨訂單</h2>
<c:forEach var="entry" items="${itemOrderMap}">
    <c:set var="itemOrder" value="${entry.key}" />
    <c:set var="orderDetailsList" value="${entry.value}" />

    <!-- 输出 itemOrder 的属性 -->
    <div class="itemOrderDiv">
        <pre>買家編號: ${itemOrder.buyMbrId}
訂單編號: ${itemOrder.orderId}</pre>    
    

	    <!-- 迭代 orderDetailsList -->
	    <div class="orderDetailDiv">
	        <c:forEach var="orderDetail" items="${orderDetailsList}">
	            <!-- 输出 orderDetail 的属性 -->
	            <div class="orderDetailItem">
	            	<img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${orderDetail.compositeKey.itemId}&position=1" alt="商品的第一張圖片" class="mx-auto">
	                <pre>商品編號 : ${orderDetail.compositeKey.itemId}  數量 : ${orderDetail.quantity}  總價 : ${orderDetail.buyingPrice}</pre>
	            </div>
	        </c:forEach>
	    </div>
    	<p>訂單金額: ${itemOrder.finalAmount}</p>
    	
    	 <button onclick="updateOrder(${itemOrder.orderId}, this)">通知已出貨</button>
    	  <!-- 取消訂單的按鈕 -->
        <button onclick="cancelOrder(${itemOrder.orderId}, this)">取消訂單</button>
        
    </div>
    
</c:forEach>
</div>
</main>	

<script>
function updateOrder(orderId, buttonElement) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "itemorder.check?action=updateOrder", true); // 替换成你的Servlet的URL
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // 请求成功，可以在这里处理返回的数据
            alert("已收到出貨通知！");
            // 移除当前订单元素
            var orderDiv = buttonElement.closest('.itemOrderDiv');
            if (orderDiv) {
                orderDiv.remove();
            }
        }
    };
    // 发送订单编号到Servlet
    xhr.send("orderId=" + orderId);
}
    
    function cancelOrder(orderId, buttonElement) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "itemorder.check?action=cancelOrder", true); // 替换成取消订单的Servlet的URL
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function() {
            if (xhr.readyState == 4 && xhr.status == 200) {
                // 请求成功，可以在这里处理返回的数据
                alert("已取消訂單！");
                // 移除当前订单元素
                var orderDiv = buttonElement.closest('.itemOrderDiv');
                if (orderDiv) {
                    orderDiv.remove();
                }
            }
        };
        // 发送订单编号到Servlet
        xhr.send("orderId=" + orderId);
    }
</script>


</body>
</html>
