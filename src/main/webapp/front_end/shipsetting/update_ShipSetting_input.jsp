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
    <!--你們自己的css-->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">


</head>
<body>
<div class="headerHTML"></div>

					
<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>


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
		<label class="control-label col-form-label"> 會員編號: </label> 
		<input type="text" name="mbrId" class="form-control"value="${param.mbrId}" readonly>
	</div>
    <div class="col-md-6">
		<label class="control-label col-form-label"> 收件人姓名 </label> 
		<input type="text" name="receiveName" class="form-control"value="${param.receiveName}">
	</div>
    <div class="col-md-6">
		<label class="control-label col-form-label"> 收件人手機: </label> 
		<input type="text" name="receivePhone" class="form-control"value="${param.receivePhone} ">
        <label style="color: deeppink;">${errorMsgs.receivePhone}</label>
	</div>
	<div class="twzipcode">
		<div class="row">
			<div class="col-md-6">
				<label class="control-label col-form-label"> 縣市 </label> <select
					data-role="county" name="county" class="form-select"></select>
			</div>
			<div class="col-md-6">
				<label class="control-label col-form-label"> 鄉鎮市區 </label> <select
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
</div>	
<input type="hidden" name="action" value="update">
<input type="hidden" name="shipId" value="${param.shipId}">
<input type="hidden" name="mbrId" value="${user.mbrId}">
<input type="submit" value="送出修改">
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
    
    
    
    
    // 使用 EL 語法獲取後端的值
    var addressString = "${param.receiveAddress}";
    // 找到市的位置
	var cityIndex = addressString.indexOf("市");
	var countyIndex = addressString.indexOf("縣");
	var cityOrCountyIndex = -1;
	
	// 如果 cityIndex 和 countyIndex 都非負，取第一個出現的索引
	if (cityIndex >= 0 && countyIndex >= 0) {
	    cityOrCountyIndex = Math.min(cityIndex, countyIndex);
	} else {
	    // 取非負的索引，如果有一個是負的就取另一個
	    cityOrCountyIndex = Math.max(cityIndex, countyIndex);
	}

 ////////////////////////////// 使用正則表達式匹配以市或縣結尾的數字部分
    var match = addressString.match(/(?:市|縣)(\d+)/);

    // 如果有匹配，取得匹配的結果
    var numberPart = match ? match[1] : null;

    var numberAsInt = parseInt(numberPart, 10);
 ///////////////////////////////////////// 在控制台上輸出結果
    
    // 定義可能的行政區域
    var regions = ["區", "鄉", "鎮", "市"];

    // 初始化變數
    var regionIndex = -1;

    // 尋找可能的行政區域
    for (var i = 0; i < regions.length; i++) {
        var index = addressString.indexOf(regions[i]);
        if (index !== -1 && (regionIndex === -1 || index < regionIndex) && index > cityOrCountyIndex) {
            regionIndex = index;
        }
    }

    // 提取行政區域名稱
    var districtOrTown = addressString.substring(cityOrCountyIndex + 1, regionIndex + 1);

    // 剩下的部分視為具體地址
    var addressPart = addressString.substring(regionIndex + 1);

    
   	twzipcode.nth(1).set(numberAsInt);

    $('input[name="address"]').val(addressPart);

    
    
    
    
    
    
    
    
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
</script>
</body>
</html>















































