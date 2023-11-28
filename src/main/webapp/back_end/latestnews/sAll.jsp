<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>物流列表</title>
    <!--頁籤icon-->
    <link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">
    <!--bootstrap5 css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <!-- google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
    <!-- 引入 CKEditor 5 classic樣板 -->
	<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>
	<!-- 引入 CKEditor 5 classic樣板中文語系 -->
	<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/translations/zh.js"></script>
    <style>
        *:not([class^="fa-"]) {
            font-family: 'Noto Sans TC', sans-serif !important;
        }
    </style>
    <!-- Font Awesome -->
    <script src="https://kit.fontawesome.com/716afdf889.js" crossorigin="anonymous"></script>
    <!--Sweet Alert-->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    <!--你們自己的css-->
    <!--不是外部檔案也無所謂-->
    
    <style>
.itemOrderContainer {
    border: 1px solid #ddd; /* 加上框線 */
    border-radius: 8px; /* 圓角 */
    padding: 10px; /* 適度的內邊距 */
    margin-bottom: 20px; /* 底部間距 */
}

.orderDetailDiv {
    display: flex;
    flex-direction: column;
    gap: 10px; /* 元素間的垂直間距 */
}

.orderDetailItem {
    display: flex;
    width: 100%; /* 讓 orderDetailItem 與 orderDetailDiv 同寬 */
    border: 1px solid #ddd; /* 加上框線 */
    border-radius: 8px; /* 圓角 */
    padding: 10px; /* 適度的內邊距 */
}

.orderDetailItem img {
    width: 100px;
    height: 100px;
    border: 1px solid #ddd; /* 圖片加上框線 */
    border-radius: 8px; /* 圓角 */
    margin-right: 10px; /* 圖片右邊間距 */
}

.orderDetailText {
    display: flex;
    flex-direction: column;
    width: 100%; /* 讓 orderDetailText 與 orderDetailItem 同寬 */
}

.itemOrderContainer p.ms-auto {
    right: 0px;
    width:auto;
}

    </style>
    
    
    
    
    
    
    <!--側邊連結css-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">

    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">


</head>
<body>
<div class="headerHTML"></div>

<div id="hy_con">
<div id="con_lf">
<!--=============================================插入連結的地方-->

</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>

<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 
<c:forEach var="entry" items="${itemOrderMap}">
    <c:set var="itemOrder" value="${entry.key}" />
    <c:set var="orderDetailsList" value="${entry.value}" />
	
	<div class="status${itemOrder.orderStatus} itemOrderContainer border border-3 p-3">
	    <h5>訂單編號: ${itemOrder.orderId}</h5>
	    <h5>訂單狀態:${OrderStatusMap[itemOrder.orderStatus]}</h5>
<!-- 		為了不破壞排版  所以一起放在form裡面 -->
	    <form method="post" action="${pageContext.request.contextPath}/front_end/itemorder/ItemOrderServlet.check" style="margin-bottom: 0px;">
            <input type="hidden" name="orderId" value="${itemOrder.orderId}">
            <input type="hidden" name="action" value="turn_To_Details" >
		    <div class="d-flex flex-column justify-content-end mt-3">
			    <button type="submit" class="btn btn-primary" style="margin-bottom:10px;">查看訂單詳情</button>
			</div>
		</form>
		    
		    
		    
		<c:forEach var="orderDetails" items="${orderDetailsList}">
		
		    <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${orderDetails.compositeKey.itemId}"><!-- 添加這一行 -->
		        <div class="orderDetailDiv">
		            <div class="orderDetailItem">
		                <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${orderDetails.compositeKey.itemId}&position=1" alt="商品的第一張圖片" class="mx-auto" loading="lazy" >
		                <div class="orderDetailText">
		                    <pre class="productName">商品名稱 : ${itemMap[orderDetails.compositeKey.itemId].itemName}</pre>
		                    <pre>數量 : ${orderDetails.quantity}</pre>
		                   
							<c:choose>
							    <c:when test="${orderDetails.discountPrice eq 0}">
				                    <pre>總價 : ${orderDetails.price}</pre>
							    </c:when>
							    <c:otherwise>
				                    <pre style="text-decoration: line-through;">總價 : ${orderDetails.price * orderDetails.quantity}</pre>
				                    <pre>折扣價 : ${orderDetails.buyingPrice}</pre>
							    </c:otherwise>
							</c:choose>
														
		                </div>
		            </div>
		        </div>
		    </a>
		    
		</c:forEach>
		    <p class="ms-auto">訂單金額: ${itemOrder.finalAmount} 元</p>
		    
		    <div class="d-flex justify-content-end mt-3">
		    
			    
		    	
		    	<c:choose>
				    <c:when test="${itemOrder.orderStatus eq 1}">
				        <form method="post" action="${pageContext.request.contextPath}/front_end/itemorder/ItemOrderServlet.check" style="margin-bottom: 0px;">
			                <input type="hidden" name="orderId" value="${itemOrder.orderId}">
			                <input type="hidden" name="action" value="updateOrder" >
			                <button type="submit" class="btn btn-primary mx-2">出貨</button>
						</form>
				    </c:when>
				    <c:when test="${itemOrder.orderStatus eq 3}">
				        <form method="post" action="${pageContext.request.contextPath}/front_end/itemorder/ItemOrderServlet.check" style="margin-bottom: 0px;">
			                <input type="hidden" name="orderId" value="${itemOrder.orderId}">
			                <input type="hidden" name="action" value="turn_To_Assign_Rating" >
			                <button type="submit" class="btn btn-primary mx-2">評價訂單</button>
						</form>
				    </c:when>
				</c:choose>
					
				<c:if test="${itemOrder.orderStatus le 2}">
					<form method="post" action="${pageContext.request.contextPath}/front_end/itemorder/ItemOrderServlet.check" style="margin-bottom: 0px;">
			                <input type="hidden" name="orderId" value="${itemOrder.orderId}">
			                <input type="hidden" name="action" value="cancelOrder" >
			                <button type="submit" class="btn btn-primary mx-2">取消訂單</button>
					</form>
				</c:if>
				
				
		    </div>
	
	</div>
	
</c:forEach>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" >
      	
		<div class="modalHTML"></div>


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>










<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 



</div>
</div>
</div>



<br><br><br><br><br><br><br>
<div class="footerHTML"></div>
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
    $(".modalHTML").load("${pageContext.request.contextPath}/back_end/latestnews/addLatestNews2.html");
    $(document).ready(function () {
        // 使用 AJAX 請求加載其他內容
        $.ajax({
            url: "${pageContext.request.contextPath}/front_end/itemorder/sideSellerOrder.jsp",
            method: "GET",
            success: function (data) {
                $("#con_lf").html(data);
            },
            error: function (xhr, status, error) {
                console.error("Error loading content:", error);
            }
        });
    });
</script>

</body>
</html>