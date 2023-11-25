<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>

<c:set var="now" value="<%= new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss.S\").format(new Date()) %>" />
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>領取優惠券</title>
    <!--頁籤icon-->
    <link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">
    <!--bootstrap5 css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <!-- google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
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
	<script src="https://cdn.tailwindcss.com"></script>

    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
	
</head>
<body>
<!--放在最前面-->
<div class="headerHTML"></div>

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
					    <c:when test="${allotedCoupon.status eq 9}">
							<button class="status${allotedCoupon.status} z-10 -mb-5 px-4 py-2 text-gray-50 bg-neutral-400 pointer-events-none" >
								已領過
						  	</button>
					    </c:when>
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
				  
					<p class="text-lg mt-1">剩餘${allotedCoupon.remainingQuantity}/${allotedCoupon.totalQuantity}</p>
					<p class="mb-1 text-sm text-sky-400">
						使用期限：<fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd HH:mm:ss" />~<br>&emsp;&emsp;&emsp;&emsp;&emsp;<fmt:formatDate value="${expireDate}" pattern="yyyy-MM-dd HH:mm:ss" />
					</p>
					<input class="allotDate" type="hidden" value="${allotedCoupon.allotDate}">
					<input class="expireDate" type="hidden" value="${allotedCoupon.expireDate}">
					<input class="cpnId" type="hidden" value="${allotedCoupon.cpnId}">
					<input class="index" type="hidden" value="${allotedCoupon.index}">
	
				</div>
		    </div>
		   
		</c:forEach>
	</div>
</div>








<!--放在最後面-->
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
</script>
<script>
	$(function(){
		let servletPath = '<%= servletPath %>';
		
		$('.allotedCoupon').on('click', '.status1', async function() {
			let allotedCoupon = $(this).closest('.allotedCoupon');
			
			let cpnId = allotedCoupon.find('.cpnId').val();
	        let index = allotedCoupon.find('.index').val(); 
	       
	        let response = await fetch(servletPath, {
				method: 'POST',
	    	    headers: {
	    	        'Content-Type': 'application/x-www-form-urlencoded'
	    	    },
	    	    body:'cpnId=' + encodeURIComponent(cpnId) + '&index=' + encodeURIComponent(index) +'&action=receive_Coupon' ,
	    	});
	    	
	    	if (!response.ok) {
	    		let errorText = await response.text();
	    		if (errorText === "2000" || errorText === "5000") {
	    			let waitTime = parseInt(errorText, 10);
	                let msg = waitTime === 2000 ? "點擊過快!" : "請勿在不同裝置搶票";
	                let timerInterval;
	                Swal.fire({
	                  title: msg,
	                  html: "將在<b></b>秒內關閉",
	                  timer: waitTime,
	                  timerProgressBar: true,
	                  didOpen: () => {
	                    Swal.showLoading();
	                    const timer = Swal.getPopup().querySelector("b");
	                    timerInterval = setInterval(() => {
	                      timer.textContent = Swal.getTimerLeft()/1000;
	                    }, 100);
	                  },
	                  willClose: () => {
	                    clearInterval(timerInterval);
	                  },allowOutsideClick: () => !Swal.isLoading()
	                });
	            } 
	    		return false;
	    	}
	    	Swal.fire({
                title: '成功',
                text: '領取成功',
                icon: 'success'
            }).then((result) => {
            	  /* Read more about isConfirmed, isDenied below */
            	  if (result.isConfirmed) {
            		  location.reload();
            	  }else{
            		  location.reload();
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
	            $(this).removeClass('status0 bg-neutral-400 pointer-events-none');
	            // 添加class
	            $(this).addClass('status1 bg-sky-400 hover:bg-sky-300');
	            $(this).empty();
	            $(this).append('可領取');
	        }
	    });
	    
	    $('.status1').each(function () {
	    	let allotedCoupon = $(this).closest('.allotedCoupon');
	        let expireDate = allotedCoupon.find('.expireDate').val(); // 找到 allotedCoupon 內的 .allotDate 的值
	        let countdown = allotedCoupon.find('.countdown'); // 找到 allotedCoupon 內的 .countdown 元素
	        let now = new Date();
	        let timeLeft = new Date(parseInt(expireDate)) - now;
	
	        if (timeLeft < 0) {
	        	// 移除class
	            $(this).removeClass('status1 bg-sky-400 hover:bg-sky-300 ');
	            // 添加class
	            $(this).addClass('status-1 bg-neutral-400 pointer-events-none');
	            $(this).empty();
	            $(this).append('已過期');
	        }
	    });
	}
</script>
<!--你們自己的js-->
<!--不是外部檔案也無所謂-->
</body>
</html>
