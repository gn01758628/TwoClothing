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
    <title>訂單付款</title>
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
	 <style>
        /* 自訂分隔號樣式 */
        .input-group .separator {
        	border:none;
            padding: 0.375rem 0.75rem;
            background-color: #f8f9fa;
            color: #495057;
        }
        /* 自訂 input 框線樣式 */
        .form-control.card {
            border: 1px solid #6c757d;
            max-width:80px;
        }

        /* 增加 focus 時的框線顏色 */
        .form-control.card:focus {
            border-color: #007bff;
        }
    </style>

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
    
	<div style="color: red;" >付款金額：<span>${itemOrder.finalAmount}</span>元</div> <br>
    <form action="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check" id="form"method="post" >


		<div id="creditCardInfo">
		    <h4>信用卡資料：</h4>
		    <div class="container-sm">
		        <div class="row mt-5">
		            <div class="col-md-12 mx-auto">
		                <div class="form-group">
		                    <label for="creditCardNumber" class="form-label">信用卡卡號：</label>
		                    <div class="input-group align-items-center">
		                        <input type="text" name="cardPart1" class="form-control card" maxlength="4" required>
		                        <div class="separator">－</div>
		                        <input type="text" name="cardPart2" class="form-control card" maxlength="4" required>
		                        <div class="separator">－</div>
		                        <input type="text" name="cardPart3" class="form-control card" maxlength="4" required>
		                        <div class="separator">－</div>
		                        <input type="text" name="cardPart4" class="form-control card" maxlength="4" required>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		
		    <br>
		    
		    <div class="form-group">
			    <label for="expirationDate">有效期限</label>
			    <input type="text" id="expirationDate" name="expirationDate" placeholder="MM / YY" maxlength="4" required>
			</div>
		    
		    <div class="form-group">
			    <label for="cvv">CVV</label>
			    <input type="text" id="cvv" name="cvv" placeholder="請輸入 CVV" maxlength="3" onkeyup="validateCVV(this)" required>
			</div>
			
			
		</div>



		<br><br>
		
		<p class="rh_title">物流方式</p><br>
        <select name="shipSettingId">
            <option value="">請選擇物流方式或自行填寫</option>
            <c:forEach var="setting" items="${shipSettingList}">
                <option>收件人:${setting.receiveName},電話:${setting.receivePhone},地址:${setting.receiveAddress}</option>
            </c:forEach>
        </select>
        
        <br><br><br>
        
        <label  style="display: none;"  for="receiveAddress">收件地址：</label>
        <input type="text" name="receiveAddress" id="receiveAddress" size="80"  style="display: none;"  required>
        <span style="color: deeppink;">${errorMsgs.receiveAddress}</span>
        
        <br> <br>
        
        <label for="receiveName">收件人姓名：</label>
        <input type="text" name="receiveName" id="receiveName" size="20" required value="${receiveName}">
        <span style="color: deeppink;">${errorMsgs.receiveName}</span>
        
        <br><br>

        <div style=" position: relative; left: 400px; bottom:55px;">
	        <label  for="receivePhone">收件人手機號碼：</label>
	        <input type="text" name="receivePhone" id="receivePhone" size="10" required value="${receivePhone}">
	        <span style="color: deeppink;">${errorMsgs.receivePhone}</span>
        </div>
		<div class="twzipcode">
			<div class="row">
				<div class="col-md-6">
					<label class="control-label col-form-label"> 縣市 </label> 
					<select data-role="county" name="county" class="form-select"></select>
				</div>
				<div class="col-md-6">
					<label class="control-label col-form-label"> 鄉鎮市區 </label> 
					<select data-role="district" name="district" class="form-select"></select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<label class="control-label col-form-label"> 詳細地址 </label> 
					<input type="text" name="address" class="form-control" required>
				</div>
			</div>
			<input type="hidden" name="zipcode" data-role="zipcode" />
		</div>
		
        <br><br><br><br>
        
		<div style=" position: relative; bottom:55px;">
	    	<label  for="remarks">備註：</label>
	    	<textarea name="remarks" id="remarks" rows="4" cols="50">${itemOrder.remarks}</textarea>
        </div>
        
        <br><br>
    
        <input type="hidden" name="orderId" value="${itemOrder.orderId}">
        <input type="hidden" name="action" value="pay_And_Address">
        <input type="submit" style="margin:-40px 0 100px 0"  value="提交付款信息">
        

    </form>    
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
        
        
    //插入左側連結
    $(document).ready(function () {
        // 使用 AJAX 請求加載其他內容
        $.ajax({
            url: "${pageContext.request.contextPath}/front_end/itemorder/sideBuyerOrder.jsp",
            method: "GET",
            success: function (data) {
                $("#con_lf").html(data);
            },
            error: function (xhr, status, error) {
                console.error("Error loading content:", error);
            }
        });
        
        $("input.card").on("keyup", function(e) {
	        let str = $(this).val().replace(/\D/g, "");
	        $(this).val(str);
	
	        if (e.which == 8 && str.length === 0) {
	            var prevInput = $(this).prevAll("input.card").first();
	            if (prevInput.length > 0) {
	                prevInput.focus();
	            }
	        } else if (str.length == 4) {
	            var nextInput = $(this).nextAll("input.card").first();
	            if (nextInput.length > 0) {
	                nextInput.focus();
	            }
	        }
	    });
	
	    $("input.card").on("keydown", function(e) {
	        if ((e.which >= 48 && e.which <= 57) || e.which == 8) {
	            if (e.target.value.length === 0 && e.which == 8) {
	                var prevInput = $(this).prev("input.card");
	                if (prevInput.length > 0) {
	                    prevInput.focus();
	                }
	            }
	        } else {
	            e.preventDefault();
	        }
	    });
	    
	    document.getElementById('expirationDate').addEventListener('keyup', function(event) {
	        let value = this.value;
	        
	        value = value.replace(/\D/g, '');
	        
	        this.value = value;
	    });

	    document.getElementById('expirationDate').addEventListener('blur', function(event) {
	        let value = this.value;

	        if (value.length === 4 && !value.includes('/')) {
	            value = value.substring(0, 2) + '/' + value.substring(2);
	        }

	        this.value = value;
	    });
	    
		let twzipcode = new TWzipcode({
			"district" : {
				onChange : function(id) {
					console.log(this.nth(id).get());
				}
			}
		});
		
		
		
		
		// 獲得下拉選單元素								
        var select = document.querySelector('select[name="shipSettingId"]');

		var receiveAddress = "${receiveAddress}";
        var desiredName = "${itemOrder.receiveName}";
        var desiredPhone = "${itemOrder.receivePhone}";
        var desiredAddress = "${itemOrder.receiveAddress}";

        // 尋找選項中是否有匹配的值
        var optionToSelect = [...select.options].find(option => {
            var textContent = option.textContent;
            return textContent.includes(desiredName) && textContent.includes(desiredPhone) && textContent.includes(desiredAddress);
        });
		
		// 如果找到匹配的選項，將其設定為所選
		if (optionToSelect) {
		    optionToSelect.selected = true;
		    if(receiveAddress != null){
		    	// 創建並觸發一個change事件
			    var event = new Event('change');
			    setTimeout(function() {
			        select.dispatchEvent(event);
			    }, 100);
		    }
		 
		}
		
		
		
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
        
        
        document.getElementById('form').addEventListener('submit', function (event) {
        	
            let cardPart1 = document.querySelector('input[name="cardPart1"]');
            let cardPart2 = document.querySelector('input[name="cardPart2"]');
            let cardPart3 = document.querySelector('input[name="cardPart3"]');
            let cardPart4 = document.querySelector('input[name="cardPart4"]');
            let expirationDate = document.querySelector('input[name="expirationDate"]');
            let cvv = document.querySelector('input[name="cvv"]');
            
            
            if (!isValidFourDigit(cardPart1.value)) {
                event.preventDefault();
                cardPart1.focus();
                cardPart1.scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'nearest'});
                window.scrollBy(0, -400);
                setTimeout(function() {
	                alert('銀行卡號的第一部分必須是四位數');
                }, 500);
                return false;
            }
            if (!isValidFourDigit(cardPart2.value)) {
                event.preventDefault();
                cardPart2.focus();
                cardPart2.scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'nearest'});
                window.scrollBy(0, -400);
                setTimeout(function() {
	                alert('銀行卡號的第一部分必須是四位數');
                }, 500);
                return false;
            }
            if (!isValidFourDigit(cardPart3.value)) {
                event.preventDefault();
                cardPart3.focus(); 
                cardPart3.scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'nearest'});
                window.scrollBy(0, -400);
                setTimeout(function() {
	                alert('銀行卡號的第一部分必須是四位數');
                }, 1000);
                return false;
            }
            if (!isValidFourDigit(cardPart4.value)) {
                event.preventDefault();
                cardPart4.focus();
                cardPart4.scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'nearest'});
                window.scrollBy(0, -400);
                setTimeout(function() {
	                alert('銀行卡號的第一部分必須是四位數');
                }, 500);
                return false;
            }

            if (!isValidExpirationDate(expirationDate.value)) {
                event.preventDefault();
                expirationDate.focus();
                expirationDate.scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'nearest'});
                window.scrollBy(0, -400);
                setTimeout(function() {
	                alert('有效期限格式不正确');
                }, 500);
                return false;
            }

            if (!isValidCVV(cvv.value)) {
                event.preventDefault();
                cvv.focus();
                cvv.scrollIntoView({ behavior: 'smooth', block: 'start', inline: 'nearest'});
                window.scrollBy(0, -400);
                setTimeout(function() {
                	alert('CVV格式不正确');
                }, 500);
                return false;
            }

            
		});
        
        function isValidFourDigit(value) {
            return /^\d{4}$/.test(value);
        }

        function isValidExpirationDate(value) {
            var parts = value.split('/');

            if (parts.length !== 2) {
                return false; 
            }

            var month = parts[0];
            var year = parts[1];

            return /^\d{2}$/.test(month) && /^\d{2}$/.test(year) && parseInt(month, 10) <= 12;

        }

        function isValidCVV(value) {
            return /^\d{3}$/.test(value);
        }

    });
    
    
    function validateCVV(input) {
        // 使用正則表達式檢查是否只包含數字
        input.value = input.value.replace(/\D/g, '');
    }

</script>
</body>
</html>