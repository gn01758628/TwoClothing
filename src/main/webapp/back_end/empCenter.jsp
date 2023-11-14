<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-hant">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>員工中心</title>
	<!-- 引入 jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<link rel="stylesheet" href="/TwoClothing/css/chijung/empCenter.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
	
	
</head>

<body>
  <aside class="sidebar">
	<header>
         <img src="${pageContext.request.contextPath}/CJImageReader/Employee?id=${employee.empId}" id="profile-image" class="profile-image" >
         
         <div class="personal">
		    <p>員工編號:<span id="formatEmpId">${emp.formatEmpId}</span></p>
		    <p>員工姓名:<span id="empName">${emp.empName}</span></p>
		 </div>
		 
        <div class="profile-btn">
<!--           <button id="profile-edit"><a href="front_end/employee/select_page.jsp">上傳頭像</a></button> -->
          <button id="logout">登出</button>
        </div>
		 
	</header>
	
    <nav class="sidebar-nav">
      <ul>
        <li>
          <a class="drop-down-menu"><i class="icon ion-ios-color-filter-outline"></i> <span>員工管理</span></a>
          
          <ul class="nav-flyout">
          
            <li>
              <a href="/TwoClothing/back_end/employee/addEmp.jsp"><i class="ion-ios-color-filter-outline"></i>新增員工資料</a>
            </li>
            
            <li>
              <a href="/TwoClothing/back_end/employee/Employee.do?action=get_On_Duty"><i class="ion-ios-color-filter-outline"></i>查看在職員工資料</a>
            </li>
            
            <li>
              <a href="/TwoClothing/back_end/empmissions/EmpMissions.do"><i class="ion-ios-clock-outline"></i>在職員工權限修改</a>
            </li>
            
            <li>
              <a href="/TwoClothing/back_end/employee/Employee.do?action=get_Not_On_Duty"><i class=""></i>查看離職員工資料</a>
            </li>
            
          </ul>
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-settings"></i> <span class="">會員管理</span></a>
          
          <ul class="nav-flyout">
            <li>
              <a href=""><i class="ion-ios-alarm-outline"></i>查看會員資料</a>
            </li>
            <li>
              <a href=""><i class="ion-ios-camera-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-chatboxes-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-cog-outline"></i></a>
            </li>
            
          </ul>
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-briefcase-outline"></i> <span class="">最新消息管理</span></a>
          
          <ul class="nav-flyout">
            <li>
              <a href=""><i class="ion-ios-flame-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-lightbulb-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-location-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-locked-outline"></i></a>
            </li>
             <li>
              <a href=""><i class="ion-ios-navigate-outline"></i></a>
            </li>
          </ul>
          
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-analytics-outline"></i> <span class="">競標管理</span></a>
          
          <ul class="nav-flyout">
            <li>
              <a href=""><i class="ion-ios-timer-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-arrow-graph-down-left"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-partlysunny-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-timer-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-game-controller-a-outline"></i></a>
            </li>
          </ul>
          
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-paper-outline"></i> <span class="">商城管理</span></a>
          
          <ul class="nav-flyout">
            <li>
              <a href=""><i class="ion-ios-filing-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-information-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-paperplane-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-android-star-outline"></i></a>
            </li>
          </ul>
          
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-navigate-outline"></i> <span class="">商品檢舉管理</span></a>
          
          <ul class="nav-flyout">
            <li>
              <a href=""><i class="ion-ios-flame-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-lightbulb-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-location-outline"></i></a>
            </li>
            <li>
              <a href=""><i class="ion-ios-locked-outline"></i></a>
            </li>
             <li>
              <a href=""><i class="ion-ios-navigate-outline"></i></a>
            </li>
          </ul>
          
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-medical-outline"></i> <span class="">一般商品訂單管理</span></a>
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-medical-outline"></i> <span class="">競標商品訂單管理</span></a>
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-medical-outline"></i> <span class="">客服中心</span></a>
        </li>
        
        <li>
          <a href="" class="drop-down-menu"><i class="ion-ios-medical-outline"></i> <span class="">虛擬錢包提款審核</span></a>
        </li>
        
      </ul>
      
    </nav>
  </aside>
	<iframe id="content"></iframe>

<script >
$(document).ready(function() {
	
// 	$.ajax({
//         url: '/TwoClothing/EmployeeCenter.do', // 替換為後端API的實際URL
//         type: 'POST', // 請求的類型為POST
//         dataType: 'json',
//         data: {
//             action: 'center'
//         }, // 直接傳遞參數
//         success: function(emp) {
//             $('#formatEmpId').text(emp.formatEmpId);
//             $('#empName').text(emp.empName);
//             $('#profile-image').attr('src', "/TwoClothing/CJImageReader/Employee?id="+emp.empId).css('display', 'block');
//         },
//         error: function(xhr, textStatus, errorThrown) {
//             alert("帳號已登出,請重新登入");
//             window.location.href = xhr.responseText; 
//         }
//     });
	
	$('.sidebar a').click(function(event) {
        // 阻止默認的超鏈結行為
        event.preventDefault();
        
     // 驗證帳號是否還在登入狀態
//         $.get('/TwoClothing/EmployeeCenter.do', { action: 'check' })
//             .done(function(emp) {
//             })
//             .fail(function(xhr, textStatus, errorThrown) {
//                 alert("帳號已登出,請重新登入");
//                 window.location.href = xhr.responseText;
// 			});
        
	        // 獲取被點擊的 a 標籤的 href 屬性的值
	        var hrefValue = $(this).attr('href');
	        $('#content').attr('src', hrefValue);
        
    	});
	
	
	$('#logout').click(function() {
		$.ajax({
	        url: '/TwoClothing/EmployeeLogin.do', // 替換為後端API的實際URL
	        type: 'POST', // 請求的類型為POST
	        data: {
	            action: 'logout'
	        }, // 直接傳遞參數
	        success: function(url) {
	            // 請求成功的處理邏輯
	        	window.location.href = url;
	        }
	    });
    });
	
	const iframe = document.querySelector('iframe');

	iframe.onload = function() {
	  // 獲取 iframe 中載入的具體 URL
	  const iframeURL = iframe.contentWindow.location.href;
// 	  if (iframeURL.includes("")) {   之後都整合完畢 會改這個 這樣跳轉時iframe不會有畫面  過濾器那邊指定的跳轉頁面也要改成""
	  if (iframeURL.includes("/TwoClothing/empLogin.html")) {
		    window.location.href = "/TwoClothing/empLogin.html"; // 替換為實際的跳轉 URL
		    // 如果是目標 URL，父頁面進行跳轉
		  }
	};
	
});

</script>





</body>

</html>
