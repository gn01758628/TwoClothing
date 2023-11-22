<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<%
    String servletPath = request.getContextPath() + "/back_end/coupon/CouponServlet.do";
%>
<table id="example" class="display" style="width: 100%">
	<thead >
		<tr>
			<th>優惠卷編號</th>
			<th>優惠卷名稱</th>
			<th>優惠卷使用日期</th>
			<th>優惠卷失效日期</th>
			<th>折扣類型</th>
			<th>折抵數值</th>
			<th>最低金額條件</th>
			<th>當前狀態</th>
			<th>員工編號</th>
			<th>新增發放</th>
			
		</tr>
	</thead>
	<c:forEach var="coupon" items="${couponList}">
		
		<tr>
			<td>${coupon.cpnId}</td>
			<td>${coupon.cpnName}</td>
			<td>${coupon.createDate}</td>
			<td>${coupon.expireDate}</td>
			<td>${couponDisTypeMap[coupon.disType]}</td>
			<td>${coupon.disValue}</td>
			<td>${coupon.minAmount}</td>
			
			<c:choose>
		        <c:when test="${coupon.createDate gt now}">
		                <td>尚未生效</td>
		        </c:when>
		        <c:when test="${not empty coupon.expireDate and coupon.expireDate lt now}">
				    <td>已失效</td>
				</c:when>
		        <c:otherwise>
		                <td>生效中</td>
		        </c:otherwise>
		    </c:choose>
			
			<td>${coupon.empId}</td>
			

			<td>
			     <input id="allot_Coupon_btn" type="submit" value="新增發放項目">
			     
<!-- 			  <FORM METHOD="post" ACTION="" style="margin-bottom: 0px;"> -->
<!-- 			     <input type="submit" value="新增發放項目"> -->
<%-- 			     <input type="hidden" name="cpnId"  value="${coupon.cpnId}"> --%>
<%-- 			     <input type="hidden" name="cpnName"  value="${coupon.cpnName}"> --%>
<!-- 		      </FORM> -->
			</td>
		</tr>
	</c:forEach>
</table>
<script>
	$(function(){
			let servletPath = '<%= servletPath %>';
			
		    $('#allot_Coupon_btn').click(async function(){
		    	
		    	
		        Swal.fire({
		        	  html:
		        		  '<input type="datetime-local" name="allotDate" id="swal-input1" class="swal2-input" style="width:70%;">' +
		                  '<input type="number" name="disvalue"  min="0" id="swal-input2" class="swal2-input" style="width:70%;">',
		    		  inputAttributes: {
		    		    autocapitalize: "off"
		    		  },
		    		  showCancelButton: true,
		    		  confirmButtonText: "確認",
		    		  cancelButtonText: "取消",
		    		  showLoaderOnConfirm: true,
		    		  preConfirm: async (pswdhash) => {
	
// 		    	            if (!pswdhash) {
// 		    	                Swal.showValidationMessage("密碼不能為空");
// 		    	                return false;
// 		    	            }
	
// 		    	            // 發送請求到後端進行密碼驗證
// 		    	            const response = await fetch(servletPath, {
// 		    	                method: 'POST',
// 		    	                headers: {
// 		    	                    'Content-Type': 'application/x-www-form-urlencoded'
// 		    	                },
// 		    	                body: 'pswdhash='+pswdhash+'&empId='+empId+'&action=change_Password',
// 		    	            });
	
// 		    	            if (!response.ok) {
// 		    	                const errorText = await response.text();
// 		    	                Swal.showValidationMessage('密碼驗證失敗:'+errorText);
// 		    	                return false;
// 		    	            }
		    	            
// 		    	            return pswdhash;
		    	        },
		    	        allowOutsideClick: () => !Swal.isLoading()
		    	    });
	
		    	    if (isConfirmed) {
// 		    	        // 在這裡處理密碼驗證成功後的操作
// 		    	        Swal.fire({
// 		    	            title: "密碼驗證成功",
// 		    	            text: '你輸入的密碼是：'+pswdhash,
// 		    	            icon: "success"
// 		    	        });
		    	    }
		    });
		});
</script>
</body>
</html>