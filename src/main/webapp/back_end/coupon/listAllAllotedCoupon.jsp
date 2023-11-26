<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>


<c:set var="now" value="<%= new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss.S\").format(new Date()) %>" />



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
			"pageLength": 10,
			"searching": true,  //搜尋功能, 預設是開啟
		    "paging": true,     //分頁功能, 預設是開啟
		    "ordering": true,   //排序功能, 預設是開啟
		    "language": {
		    	url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json" ,
		    	"search": "搜尋： (複合查詢請用空白隔開)"
		    },
		    "order": [[ 8, "asc" ],[ 0, "asc" ],[ 1, "desc" ]],
		    "aoColumnDefs": [
                { "sType": "custom-status", "aTargets": [8] },    //指定列號使用自定義排序
            ]
		    
		});
		// 自定义排序函数
	    jQuery.extend(jQuery.fn.dataTableExt.oSort, {
	        'custom-status-asc': function (a, b) {
	            return compareStatus(a, b);
	        },
	        'custom-status-desc': function (a, b) {
	            return compareStatus(b, a);
	        }
	    });

	    // 比较状态的函数
	    function compareStatus(statusA, statusB) {
	        // 定义状态的排序顺序
	        const order = {
	            "尚未發放": 1,
	            "發放中": 2,
	            "發放完了": 3,
	            "使用期限已到": 4,
	            "停止發放": 5
	        };

	        // 获取状态的排序值
	        const orderA = order[statusA] || 0;
	        const orderB = order[statusB] || 0;

	        // 比较排序值
	        return orderA - orderB;
	    }
	    
	});
</script>
<style type="text/css">
body {
	margin: 2rem ;
}
</style>

</head>
<body>
<%
    String servletPath = request.getContextPath() + "/back_end/coupon/CouponServlet.do";
%>
<table id="example" class="display" style="width: 100%">
	<thead >
		<tr>
			<th>優惠卷編號</th>
			<th>發放批次</th>
			<th>優惠卷名稱</th>
			<th>發放時間</th>
			<th>發放總量</th>
			<th>已領取數量</th>
			<th>剩餘數量</th>
			<th>失效時間</th>
			<th>當前狀態</th>
			<th>終止發放</th>
			
		</tr>
	</thead>
	<c:forEach var="allotedCoupon" items="${allotedCouponList}">
		
	    <!-- 使用SimpleDateFormat格式化日期 -->
	    
		<c:if test="${not empty allotedCoupon.allotDate}">
		    <c:set var="allotDateMillis" value="${allotedCoupon.allotDate}" />
		    <c:set var="allotDate" value='<%= new java.util.Date((long)pageContext.getAttribute("allotDateMillis")) %>' />
		</c:if>

		<c:if test="${not empty allotedCoupon.expireDate}">
		    <c:set var="expireDateMillis" value="${allotedCoupon.expireDate}" />
		    <c:set var="expireDate" value='<%= new java.util.Date((long)pageContext.getAttribute("expireDateMillis")) %>' />
		</c:if>
		

	
	    <tr>
	        <td>${allotedCoupon.cpnId}</td>
	        <td>${allotedCoupon.index}</td>
	        <td>${allotedCoupon.cpnName}</td>
	        <td><fmt:formatDate value="${allotDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	        <td>${allotedCoupon.totalQuantity}</td>
	        <td>${allotedCoupon.totalQuantity - allotedCoupon.remainingQuantity}</td>
	        <td>${allotedCoupon.remainingQuantity}</td>
	        <td><fmt:formatDate value="${expireDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	        <td>${AllotedCouponStatusMap[allotedCoupon.status]}</td>
	        
	        <td>
	            <c:choose>
	                <c:when test="${allotedCoupon.status eq 0 or allotedCoupon.status eq 1}">
	                    <input class="stop_Issuing_Coupon_btn" type="submit" value="停止發放">
	                </c:when>
	            </c:choose>
	        </td>
	    </tr>
	</c:forEach>
</table>
<script>
	$(function(){
			let servletPath = '<%= servletPath %>';
			let allotedCouponStatusMap = {
		        <c:forEach var="entry" items="${AllotedCouponStatusMap}">
		            '${entry.key}': '${entry.value}',
		        </c:forEach>
			};
			
		    $('.stop_Issuing_Coupon_btn').click(async function(){
		    	let tr = $(this).closest('tr');

		        // 在 tr 元素中找到名為 expireDate 的元素
		        let cpnId = tr.find('td:eq(0)').text();
		        let index = tr.find('td:eq(1)').text(); 
		        
		        
		        Swal.fire({
		        	  title: "確定停止發放嗎?",
		        	  icon: "warning",
		        	  showCancelButton: true,
		        	  confirmButtonColor: "#3085d6",
		        	  cancelButtonColor: "#d33",
		        	  confirmButtonText: "確認",
		        	  cancelButtonText: "取消",
	        	  	  preConfirm: async () => {
		    		    	let response = await fetch(servletPath, {
		    		    	    method: 'POST',
		    		    	    headers: {
		    		    	        'Content-Type': 'application/x-www-form-urlencoded'
		    		    	    },
		    		    	    body:'cpnId=' + encodeURIComponent(cpnId) + '&index=' + encodeURIComponent(index) +'&action=stop_Issuing_Coupon',
		    		    	});
		
		    		    	if (!response.ok) {
		    		    	    let errorText = await response.text();
		    		    	    tr.find('td:eq(8)').text(allotedCouponStatusMap[errorText]);
		    		    	    tr.find('td:eq(9)').empty();
		    		    	    Swal.showValidationMessage('該優惠券狀態:'+allotedCouponStatusMap[errorText]);
		    		    	    return false;
		    		    	}
		    		    	tr.find('td:eq(8)').text(allotedCouponStatusMap[await response.text()]);
		    		    	tr.find('td:eq(9)').empty();
		        		    Swal.fire({
		    	                title: '成功',
		    	                text: '優惠券停止發放',
		    	                icon: 'success'
		    	            });
	    	          }
	        	});
		    });
		});
</script>
</body>
</html>