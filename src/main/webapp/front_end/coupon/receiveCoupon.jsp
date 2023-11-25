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
<title>領取優惠券</title>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>                               
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>  
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@latest"></script><!-- 引入 SweetAlert2 -->


<style type="text/css">
body {
	margin: 2rem ;
}
</style>

</head>
<body>
<%
    String servletPath = request.getContextPath() + "/MemberCouponServlet.check";
%>
<div class="container">
	<div class="row  row-cols-auto">
		<c:forEach var="allotedCoupon" items="${allotedCouponList}">
		
			<c:if test="${not empty allotedCoupon.createDate}">
				<c:set var="createDateMillis" value="${allotedCoupon.createDate}" />
				<c:set var="createDate" value='<%= new java.util.Date((long)pageContext.getAttribute("createDateMillis")) %>' />
				</c:if>
	
			<c:if test="${not empty allotedCoupon.expireDate}">
			    <c:set var="expireDateMillis" value="${allotedCoupon.expireDate}" />
			    <c:set var="expireDate" value='<%= new java.util.Date((long)pageContext.getAttribute("expireDateMillis")) %>' />
			</c:if>
			
			<c:if test="${not empty allotedCoupon.allotDate}">
			    <c:set var="allotDateMillis" value="${allotedCoupon.allotDate}" />
			    <c:set var="allotDate" value='<%= new java.util.Date((long)pageContext.getAttribute("allotDateMillis")) %>' />
			</c:if>
			
			
			<div class="allotedCoupon col-lg-4 col-md-6 col-sm-12" style="display: grid; place-items: center;">
				<div class="overflow-hidden relative w-72 h-96 bg-zinc-100 rounded-2xl text-sky-400 flex flex-col justify-center items-center gap-6 mt-10 mb-10">
					<svg class="absolute -bottom-12 -right-12 h-60 w-60 -rotate-12 stroke-current opacity-30" height="100" preserveAspectRatio="xMidYMid meet" viewBox="0 0 100 100" width="100" x="0" xmlns="http://www.w3.org/2000/svg" y="0">
						<path class="svg-stroke-primary" d="M65.8,46.1V30.3a15.8,15.8,0,1,0-31.6,0V46.1M22.4,38.2H77.6l4,47.3H18.4Z" fill="none" stroke-linecap="round" stroke-linejoin="round" stroke-width="8">
					    </path>
					</svg>
				  
					<div class="flex flex-col items-center">
					    <p class="text-xl font-extrabold">${allotedCoupon.cpnName}</p>
					    <p class="relative text-xs inline-block after:absolute after:content-[''] after:ml-2 after:top-1/2 after:bg-sky-200 after:w-12 after:h-0.5   before:absolute before:content-[''] before:-ml-14 before:top-1/2 before:bg-sky-200 before:w-12 before:h-0.5">${allotedCoupon.minAmount}元可用</p>
					</div>
					<span class="-mt-3 mb-0 -skew-x-12 -skew-y-12 text-7xl font-extrabold">
					 	<c:if test="${allotedCoupon.disType eq 0}">
							$${allotedCoupon.disValue}
						</c:if>
					 	<c:if test="${allotedCoupon.disType eq 1}">
						    -${allotedCoupon.disValue}%<span class="text-3xl" >OFF</span></span>
						</c:if>
					</span>
				  
					<c:choose>
						<c:when test="${allotedCoupon.status eq 0}">
						  	<button class="status${allotedCoupon.status} z-10 -mb-5 px-4 py-2 text-gray-50 bg-neutral-400 pointer-events-none" >
								<p class="countdown" class="mb-0">距離發放還有：</p>
			                    <span class="end-date">發放時間：<fmt:formatDate value="${allotDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
							</button>
				    	</c:when>
					    <c:when test="${allotedCoupon.status eq 1}">
							<button class="status${allotedCoupon.status} z-10 -mb-5 px-4 py-2 bg-sky-400 text-gray-50 hover:bg-sky-300" >
								可領取
							</button>
					    </c:when>
					    <c:when test="${allotedCoupon.status eq 3}">
							<button class="status${allotedCoupon.status} z-10 -mb-5 px-4 py-2 text-gray-50 bg-neutral-400 pointer-events-none" >
								發放完畢
						  	</button>
					    </c:when>
					</c:choose>
				  
				  
				  
	<!-- 			已經領取 或 無法領取(發完了) 的狀態 移除bg-sky-400 hover:bg-sky-300 添加 bg-neutral-400 pointer-events-none -->
				  
					<p class="text-lg mt-1">剩餘${allotedCoupon.remainingQuantity}/${allotedCoupon.totalQuantity}</p>
					<p class="mb-1 text-sm text-sky-400">
						使用期限：<fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd HH:mm:ss" />~<br>&emsp;&emsp;&emsp;&emsp;&emsp;<fmt:formatDate value="${expireDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</p>
					<input class="allotDate" type="hidden" value="${allotedCoupon.allotDate}">
					<input class="cpnId" type="hidden" value="${allotedCoupon.cpnId}">
					<input class="index" type="hidden" value="${allotedCoupon.index}">
	
				</div>
		    </div>
		   
		</c:forEach>
	</div>
</div>






<script>
	$(function(){
		let servletPath = '<%= servletPath %>';
		
	    $('.status1').click(async function(){
	    	let allotedCoupon = $(this).closest('.allotedCoupon');
			
	        let cpnId = allotedCoupon.find('.cpnId').val();
	        let index = allotedCoupon.find('.index').val(); 
	       
	    	
	    	Swal.fire({
		        inputAttributes:{
		            autocapitalize: "off"
		        },
		        showCancelButton: true,
		        confirmButtonText: "確認",
		        cancelButtonText: "取消",
		        allowOutsideClick: false,
		        showLoaderOnConfirm: true,
    		    preConfirm: async () => {
	
    		    	let response = await fetch(servletPath, {
    		    	    method: 'POST',
    		    	    headers: {
    		    	        'Content-Type': 'application/x-www-form-urlencoded'
    		    	    },
    		    	    body:'cpnId=' + encodeURIComponent(cpnId) + '&index=' + encodeURIComponent(index) +'&action=receive_Coupon' ,
    		    	});

    		    	if (!response.ok) {
    		    	    const errorText = await response.text();
    		    	    Swal.showValidationMessage(errorText);
    		    	    return false;
    		    	}
    		    }
    	    }).then((result) => {
    	    	if (result.isConfirmed) {
    	        	// 成功後顯示成功訊息
    	            Swal.fire({
    	                title:'成功',
    	                text: '優惠券領取成功',
    	                icon: 'success'
    	            });
        		}
    	    });
    	    
	    });
	    
		// 倒數計時
        updateCountdowns();
        setInterval(updateCountdowns, 1000);
	});
	
	// 發放時間倒數計時
    function updateCountdowns() {
	    $('.status0').each(function () {
	    	let allotedCoupon = $(this).closest('.allotedCoupon');
	        let allotTime = allotedCoupon.find('.allotDate').val(); // 找到 allotedCoupon 內的 .allotDate 的值
	        let countdown = allotedCoupon.find('.countdown'); // 找到 allotedCoupon 內的 .countdown 元素
	        let now = new Date();
	        let timeLeft = new Date(parseInt(allotTime)) - now;
	
	        if (timeLeft > 0) {
	            let days = Math.floor(timeLeft / (1000 * 60 * 60 * 24));
	            let hours = Math.floor((timeLeft % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	            let minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));
	            let seconds = Math.floor((timeLeft % (1000 * 60)) / 1000);
	
	            let countdownText = "距離發放還有:" + days + "天" + hours + "時" + minutes + "分" + seconds + "秒開始";
	            countdown.text(countdownText); // 將倒數文字設置到 .countdown 元素中
	        } else {
	        	// 移除class
	            $(this).removeClass('status0');
	            // 添加class
	            $(this).addClass('status1');
	            $(this).empty();
	            $(this).append('可領取');
	        }
	    });
	}
</script>
</body>
</html>