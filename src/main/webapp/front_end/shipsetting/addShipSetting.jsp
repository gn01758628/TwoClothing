<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>聊天室</title>
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

<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>會員中心-帳戶資訊 www.bootstrapmb.com</title>
</head>

<body>
<!--放在最前面-->
<div class="headerHTML"></div>


<div id="hy_con">
<div id="con_lf">
<!--=============================================插入連結的地方-->
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<p class="rh_title">物流設定</p>




<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" name="form1">
<div class="row">
    <div class="col-md-6">
        <label class="control-label col-form-label"> 姓名 </label> 
        <input type="text" name="receiveName" class="form-control">
    </div>
    <div class="col-md-6">
        <label class="control-label col-form-label"> 手機 </label> 
        <input type="text" name="receivePhone" class="form-control">
        <label style="color: deeppink;">${errorMsgs.receivePhone}</label>
    </div>
</div>

	<div class="twzipcode">
		<div class="row">
			<div class="col-md-6">
				<label class="control-label col-form-label"> 縣市 </label> <select
					data-role="county" name="county" class="form-select"></select>
			</div>
			<div class="col-md-6">
				<label class="control-label col-form-label"> 鄉鎮區 </label> <select
					data-role="district" name="district" class="form-select"></select>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<label class="control-label col-form-label"> 詳細地址 </label> <input
					type="text" name="address" class="form-control">
			</div>
		</div>
		<input type="hidden" name="zipcode" data-role="zipcode" />
	</div>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="mbrId" value="${user.mbrId}">
<input type="submit" value="送出新增">
</FORM>



</div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

</div>
<br><br><br><br><br><br><br>

<!--放在最後面-->
<div class="footerHTML"></div>
<script>
let twzipcode = new TWzipcode({
	"district" : {
		onChange : function(id) {
			console.log(this.nth(id).get());
		}
	}
});
</script>
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
    
    
    $(document).ready(function () {
        // 使用 AJAX 請求加載其他內容
        $.ajax({
            url: "${pageContext.request.contextPath}/front_end/members/sideMembers.jsp",
            method: "GET",
            success: function (data) {
                $("#con_lf").html(data);
            },
            error: function (xhr, status, error) {
                console.error("Error loading content:", error);
            }
        });
    });
    
//     $(document).ready(function () {
//         // 檢查本地存儲中是否有 headerHTML，如果有，則使用它，否則請求載入
//         var storedHeader = localStorage.getItem('headerHTML');
//         if (storedHeader) {
//             $("#headerContainer").html(storedHeader);
//         } else {
//             loadAndStoreHTML('headerHTML', '#headerContainer');
//         }

//         // 檢查本地存儲中是否有 footerHTML，如果有，則使用它，否則請求載入
//         var storedFooter = localStorage.getItem('footerHTML');
//         if (storedFooter) {
//             $("#footerContainer").html(storedFooter);
//         } else {
//             loadAndStoreHTML('footerHTML', '#footerContainer');
//         }

//         // 函數：載入 HTML 並存儲到本地存儲
//         function loadAndStoreHTML(filename, container) {
//             $.ajax({
//                 url: "${pageContext.request.contextPath}/" + filename + ".html",
//                 method: "GET",
//                 success: function (data) {
//                     $(container).html(data);

//                     // 存儲 HTML 到本地存儲
//                     localStorage.setItem(filename, data);
//                 },
//                 error: function (xhr, status, error) {
//                     console.error("Error loading " + filename + ":", error);
//                 }
//             });
//         }
//     });
    
</script>
</body>
</html>

