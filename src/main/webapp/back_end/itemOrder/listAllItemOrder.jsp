<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>所有優惠券</title>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>                                    <!-- ●●js  for jquery datatables 用 -->
<script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@latest"></script><!-- 引入 SweetAlert2 -->

<!-- ●● jquery datatables 設定 -->
<script>
	$(document).ready(function() {
		$('#example').DataTable({
			"lengthMenu": [5, 10, 20, 50, 100],
			"searching": true,  //搜尋功能, 預設是開啟
		    "paging": true,     //分頁功能, 預設是開啟
		    "ordering": true,   //排序功能, 預設是開啟
		    "language": {
		    	url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json" ,
		    	"search": "搜尋： (複合查詢請用空白隔開)"
		    },
		    "order": [[ 0, "asc" ]],
		    "columnDefs":[
		    	{
		    		targets:[9], orderable:false
		    	}
		    ]
		    
		});
	});
</script>
<style type="text/css">
body {
	margin: 2rem ;
}
</style>

</head>
<body>

<table id="example" class="display" style="width: 100%">
	<thead >
		<tr>
			<th>一般商品訂單編號</th>
			<th>買家編號</th>
			<th>賣家編號</th>
			<th>買家給評</th>
			<th>賣家給評</th>
			<th>成立日期</th>
			<th>付款方式</th>
			<th>結單金額</th>
			<th>訂單狀態</th>
			<th>收件人</th>
			<th>收件地址</th>
			<th>收件電話</th>
			
		</tr>
	</thead>
	<c:forEach var="itemOrder" items="${itemOrderList}">
		
		<tr>
			<td>${itemOrder.orderId}</td>
			<td>${itemOrder.buyMbrId}</td>
			<td>${itemOrder.sellMbrId}</td>
			<td>${itemOrder.buyStar}</td>
			<td>${itemOrder.sellStar}</td>
			<td>${itemOrder.orderDate}</td>
			<td>${itemOrder.payType}</td>
			<td>${itemOrder.finalAmount}</td>
			<td>${OrderStatusMap[itemOrder.orderStatus]}</td>
			<td>${itemOrder.receiveName}</td>
			<td>${itemOrder.receiveAddress}</td>
			<td>${itemOrder.receivePhone}</td>
		</tr>
	</c:forEach>
</table>
<script>
	
</script>
</body>
</html>