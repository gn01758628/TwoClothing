<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.twoclothing.model.shipsetting.*" %>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.gordon.service.*"%>


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
<link rel="stylesheet" type="text/css"href="${pageContext.request.contextPath}/css/gordon/memberArea.css">


			    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
					
</head>
<body>
<!--放在最前面-->
<div class="headerHTML"></div>
<script src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>

<div id="hy_con">
<div id="con_lf">
<!--=============================================插入連結的地方-->
<!--=============================================插入連結的地方-->
<!--=============================================插入連結的地方-->
<!--=============================================插入連結的地方-->
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<p class="rh_title">結帳</p>
    
    
    
   
<div style="color: red;" >付款金額：<span>${param.amount}</span>元</div> <br>
    <form action="<%=request.getContextPath()%>/bidorder/BidOrder.do" id="from"method="post" onsubmit="return validateForm()">
        <label for="payType">付款方式：</label> 
        <select name="payType" id="payType" onchange="updatePayInfoFields()" required>
            <option value="">請選擇付款方式</option>
            <option value="0">信用卡</option>
            <option value="1">轉帳</option>
            <option value="2">虛擬錢包</option>
        </select>
        <br> <br>
        
		<div id="creditCardInfo" style="display: none;">
		<h2>信用卡資料：</h2>
			<label for="cardNumber">信用卡號碼</label>
		    <input type="text" id="cardNumber" name="cardNumber" placeholder="請輸入信用卡號碼" required>
		<br>
		    <label for="expirationDate">有效期限</label>
		    <input type="text" id="expirationDate" name="expirationDate" placeholder="MM / YY" required>
		<br>
		    <label for="cvv">CVV</label>
		    <input type="text" id="cvv" name="cvv" placeholder="請輸入 CVV" required>
		</div>

		<div id="bankAccountInfo" style="display: none;">
			<h2>商家銀行帳戶信息：</h2>
			<p>銀行名稱：ABC銀行</p>
			<p>帳號：123-456-789</p>
			<p>持戶人名稱：商家名稱</p>
			<p>匯款備註：請在匯款備註中提及訂單編號。</p>

		</div>

		
		<div id="virtualWalletFields" style="display: none;">
		<h2>錢包：</h2>
		    <label for="balance">錢包餘額：(${user.balance})元</label>
		    <input type="text" name="balance" id="balance" value="${param.amount}" disabled>
		    <span id="errorMsgsBalance" style="color: deeppink;">${errorMsgs.balance}</span>
		    <br>
		    <br>
		</div>
		<br> <br>
		<p class="rh_title">物流方式</p><br>
        <select name="shipSettingId">
            <option value="">請選擇物流方式或自行填寫</option>
            <c:forEach var="setting" items="${shipSetting}">
                <option>收件人:${setting.receiveName},電話:${setting.receivePhone},地址:${setting.receiveAddress}</option>
            </c:forEach>
        </select>
        <br><br><br>
        <label  style="display: none;"  for="receiveAddress">收件地址：</label>
        <input type="text" name="receiveAddress" id="receiveAddress" size="80"  style="display: none;"  required>
        <span style="color: deeppink;">${errorMsgs.receiveAddress}</span>
        <br> <br>
        <label for="receiveName">收件人姓名：</label>
        <input type="text" name="receiveName" id="receiveName" size="20" required>
        <span style="color: deeppink;">${errorMsgs.receiveName}</span>
        <br>
        <br>

        <div style=" position: relative; left: 400px; bottom:55px;">
        <label  for="receivePhone">收件人手機號碼：</label>
        <input type="text" name="receivePhone" id="receivePhone" size="10" required>
        <span style="color: deeppink;">${errorMsgs.receivePhone}</span>
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
        <br> <br>

        <br><br>
		<div style=" position: relative; bottom:55px;">
        <label  for="remarks">備註：</label>
       <textarea name="remarks" id="remarks" rows="4" cols="50"></textarea>
        </div>
        <br> <br>
    
        <input type="hidden" name=amount value="${param.amount}">
        <input type="hidden" name=bidOrderId value="${param.bidOrderId}">
        <input type="hidden" name=sellMbrId value="${param.sellMbrId}">
        <input type="hidden" name=bidItemId value="${param.bidItemId}">
        <input type="hidden" name="buyMbrId" value="${user.mbrId}">
        <input type="hidden" name="action" value="set_Pay_And_Address">
        <input type="submit"   value="提交付款信息">
        

        
     </div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

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

    <script type="text/javascript">
        // 獲得下拉選單元素
        var select = document.querySelector('select[name="shipSettingId"]');

        // 獲得其他表單字段元素
        var receiveAddress = document.querySelector('input[name="receiveAddress"]');
        var receiveName = document.querySelector('input[name="receiveName"]');
        var receivePhone = document.querySelector('input[name="receivePhone"]');
        // 監聽下拉選單的變化
        select.addEventListener('change', function() {
            // 獲得選擇的選項
            var selectedOption = select.options[select.selectedIndex];

            // 從選項中獲取資料，例如"收件人:姓名,電話:號碼,地址:地址"
            var data = selectedOption.textContent;

            // 使用正則表達式提取姓名、電話和地址
            var nameMatch = data.match(/收件人:(.*?),/);
            var phoneMatch = data.match(/電話:(.*?),/);
            var addressMatch = data.match(/地址:(.*)/);

            // 檢查是否包含姓名信息
            if (nameMatch) {
                receiveName.value = nameMatch[1];
            } else {
                receiveName.value = ''; // 如果未找到姓名，將字段設置為空
            }

            if (phoneMatch) {
                receivePhone.value = phoneMatch[1];
            } else {
                receivePhone.value = ''; // 如果未找到電話，將字段設置為空
            }

            if (addressMatch) {
                receiveAddress.value = addressMatch[1];
 //////////////////////////////////////////////////////////////////放值到表               
                var receiveAddressElement = document.querySelector('input[name="receiveAddress"]');
                var addressString = receiveAddressElement.value;
                console.log("收件地址的值:", addressString);
                
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
                //////////////////////////////////////////////////////////////////放值到表               


            } else {
                receiveAddress.value = ''; // 如果未找到地址，將字段設置為空
            }
        });
      
        
        
        function updatePayInfoFields() {
            var payTypeSelect = document.getElementById("payType");
            var virtualWalletFields = document.getElementById("virtualWalletFields");
            var bankAccountInfo = document.getElementById("bankAccountInfo");
            var creditCardInfo = document.getElementById("creditCardInfo");
            var errorMsgsBalance = document.getElementById("errorMsgsBalance");

            var selectedPayType = payTypeSelect.value;

            errorMsgsBalance.textContent = ""; 
            if (selectedPayType === "0") {
            	bankAccountInfo.style.display = "none";
            	virtualWalletFields.style.display = "none";
            	creditCardInfo.style.display = "block";
            } else if (selectedPayType === "1") {
            	virtualWalletFields.style.display = "none";
            	creditCardInfo.style.display = "none";
                bankAccountInfo.style.display = "block";
            } else if (selectedPayType === "2") {
            	creditCardInfo.style.display = "none";
                bankAccountInfo.style.display = "none";
                virtualWalletFields.style.display = "block";
            }
          
        }
   
        
        
        
        function validateForm() {
            var payTypeSelect = document.getElementById("payType");
            var selectedPayType = payTypeSelect.value;

            if (selectedPayType === "2") {
                var balanceField = document.getElementById("balance");
                var errorMsgsBalance = document.getElementById("errorMsgsBalance");
                var balanceValue = balanceField.value;

                if (balanceValue > parseFloat("${user.balance}")) {
                    errorMsgsBalance.textContent = "付款金額不能大於您的餘額";
                    balanceField.focus();
                    return false; // 防止表單提交
                }
            }

            return true;
        }

let twzipcode = new TWzipcode({
	"district" : {
		onChange : function(id) {
			console.log(this.nth(id).get());
		}
	}
});
//信用卡驗證
function validateCardNumber(cardNumber) {
  // 使用正則表達式檢查信用卡號碼
  const cardNumberRegex = /^\d{16}$/; // 16位數字
  return cardNumberRegex.test(cardNumber);
}


function validateCVV(cvv) {
	  // 使用正則表達式檢查 CVV
	  const cvvRegex = /^\d{3}$/; // 三位數字
	  return cvvRegex.test(cvv);
	}
document.getElementById('from').addEventListener('submit', function (event) {
	  const cardNumber = document.getElementById('cardNumber').value;
	  const cvv = document.getElementById('cvv').value;

	  // 進行整體驗證
	  if (!validateCardNumber(cardNumber)) {
	    alert('請輸入有效的信用卡號碼！');
	    event.preventDefault();
	  } else if (!validateCVV(cvv)) {
	    alert('請輸入有效的 CVV！');
	    event.preventDefault();
	  }
	});


// // 使用 EL 語法獲取後端的值
// var addressString = "${setting.receiveAddress}";

    //插入左側連結
    $(document).ready(function () {
        // 使用 AJAX 請求加載其他內容
        $.ajax({
            url: "${pageContext.request.contextPath}/front_end/bidorder/sideBuyBidorder.jsp",
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