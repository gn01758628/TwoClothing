<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="com.twoclothing.model.shipsetting.*" %>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.gordon.service.*"%>





<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>填寫付款信息</title>

   <script>
        function updatePayInfoFields() {
            var payTypeSelect = document.getElementById("payType");
            var virtualWalletFields = document.getElementById("virtualWalletFields");
            var balanceField = document.getElementById("balance");
            var errorMsgsBalance = document.getElementById("errorMsgsBalance");

            var selectedPayType = payTypeSelect.value;

            // 隐藏所有的付款方式字段
            errorMsgsBalance.textContent = ""; // 清空錯誤消息

          
        }
    </script>
</head>
<body>
    <h2>填写付款信息</h2>
    <form action="<%=request.getContextPath()%>/bidorder/BidOrder.do" method="post" onsubmit="return validateForm()">
        <!-- 付款方式下拉选择框 -->
        <label for="payType">付款方式：</label> 
        <select name="payType" id="payType" onchange="updatePayInfoFields()" required>
            <option value="">請選擇付款方式</option>
            <!-- 預設選項 -->
            <option value="0">信用卡</option>
            <option value="1">轉帳</option>
            <option value="2">虛擬錢包</option>
        </select>
        <br> <br>
		
		 <label>信用卡資料</label><input type="text"
			id="creditCard">


		<!-- 转帐输入字段 -->
		<div id="bankAccountInfo">
			<h2>商家銀行帳戶信息：</h2>
			<p>銀行名稱：ABC銀行</p>
			<p>帳號：123-456-789</p>
			<p>持戶人名稱：商家名稱</p>
			<p>匯款備註：請在匯款備註中提及訂單編號。</p>

		</div>


		<div id="virtualWalletFields">
		    <label for="balance">钱包余额：(${user.balance})元</label>
		    <input type="text" name="balance" id="balance" value="${param.amount}" readonly>
		    <span id="errorMsgsBalance" style="color: deeppink;">${errorMsgs.balance}</span>
		    <br>
		    <br>
		</div>

        <select name="shipSettingId">
            <option value="">請選擇物流方式或自行填寫</option>
            <!-- 預設選項 -->
            <c:forEach var="setting" items="${shipSetting}">
                <option>收件人:${setting.receiveName},電話:${setting.receivePhone},地址:${setting.receiveAddress}</option>
            </c:forEach>
        </select>
        <br>
        <!-- 收件地址 -->
        <label for="receiveAddress">收件地址：</label>
        <input type="text" name="receiveAddress" id="receiveAddress" size="100" required>
        <span style="color: deeppink;">${errorMsgs.receiveAddress}</span>
        <br> <br>

        <!-- 收件人姓名 -->
        <label for="receiveName">收件人姓名：</label>
        <input type="text" name="receiveName" id="receiveName" size="20" required>
        <span style="color: deeppink;">${errorMsgs.receiveName}</span>
        <br>
        <br>

        <!-- 收件人手机号码 -->
        <label for="receivePhone">收件人手機號碼：</label>
        <input type="text" name="receivePhone" id="receivePhone" size="10" required>
        <span style="color: deeppink;">${errorMsgs.receivePhone}</span>
        <br><br>

        <!-- 备注 -->
        <label for="remarks">備註：</label>
        <input type="text" name="remarks" id="remarks" size="200">
        <br> <br>
        <!-- 提交按钮 -->
    
        <input type="hidden" name=amount value="${param.amount}">
        <input type="hidden" name=bidOrderId value="${param.bidOrderId}">
        <input type="hidden" name=sellMbrId value="${param.sellMbrId}">
        <input type="hidden" name=bidItemId value="${param.bidItemId}">
        <input type="hidden" name="buyMbrId" value="${user.mbrId}">
        <input type="hidden" name="action" value="set_Pay_And_Address">
        <input type="submit"   value="提交付款信息">
        

        
        
     </form>  
        
        
        
        
        
        
        
        


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
            } else {
                receiveAddress.value = ''; // 如果未找到地址，將字段設置為空
            }
        });
        
        

        
        
        
        function validateForm() {
            var payTypeSelect = document.getElementById("payType");
            var selectedPayType = payTypeSelect.value;

            if (selectedPayType === "2") {
                var balanceField = document.getElementById("balance");
                var errorMsgsBalance = document.getElementById("errorMsgsBalance");
                var balanceValue = balanceField.value;

                if (balanceValue === "" || isNaN(balanceValue)) {
                    errorMsgsBalance.textContent = "請輸入有效的數字";
                    balanceField.focus();
                    return false; // 防止表單提交
                }
            }

            // 如果所有檢查通過，允許表單提交
            return true;
        }
    </script>
</body>
</html>