<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>填寫付款信息</title>
   
    <script>
        function updatePayInfoFields() {
            var payTypeSelect = document.getElementById("payType");
            var creditCardFields = document.getElementById("creditCardFields");
            var bankTransferFields = document.getElementById("bankTransferFields");
            var virtualWalletFields = document.getElementById("virtualWalletFields");
            
            var selectedPayType = payTypeSelect.value;
            
            // 隐藏所有的付款方式字段
            creditCardFields.style.display = "none";
            bankTransferFields.style.display = "none";
            virtualWalletFields.style.display = "none";
            
            if (selectedPayType === "0") {
                creditCardFields.style.display = "block";
            } else if (selectedPayType === "1") {
                bankTransferFields.style.display = "block";
            } else if (selectedPayType === "2") {
                virtualWalletFields.style.display = "block";
            }
        }
    </script>
</head>
<body>
    <h2>填写付款信息</h2>
    <form action="processPayment.jsp" method="post">
        <!-- 付款方式下拉选择框 -->
        <label for="payType">付款方式：</label>
        <select name="payType" id="payType" onchange="updatePayInfoFields()">
            <option value="0">信用卡</option>
            <option value="1">转帐</option>
            <option value="2">虚拟钱包</option>
        </select>
        <br><br>
        
        <!-- 信用卡输入字段 -->
        <div id="creditCardFields" style="display: none;">
            <label for="creditCardNumber">卡号：</label>
            <input type="text" name="creditCardNumber" id="creditCardNumber" required>
            <br><br>
            
            <label for="expirationDate">到期日：</label>
            <input type="text" name="expirationDate" id="expirationDate" required>
            <br><br>
            
            <label for="securityCode">安全码：</label>
            <input type="text" name="securityCode" id="securityCode" required>
            <br><br>
            
            <label for="cardHolderName">持卡人姓名：</label>
            <input type="text" name="cardHolderName" id="cardHolderName" required>
            <br><br>
        </div>
        
        <!-- 转帐输入字段 -->
        <div id="bankTransferFields" style="display: none;">
            <label for="bankName">银行：</label>
            <input type="text" name="bankName" id="bankName" required>
            <br><br>
            
            <label for="accountNumber">帐号：</label>
            <input type="text" name="accountNumber" id="accountNumber" required>
            <br><br>
        </div>
        
        <!-- 虚拟钱包输入字段 -->
        <div id="virtualWalletFields" style="display: none;">
            <label for="walletBalance">钱包余额：</label>
            <input type="text" name="walletBalance" id="walletBalance" required>
            <br><br>
        </div>
        
        <!-- 收件地址 -->
        <label for="receiveAddress">收件地址：</label>
        <input type="text" name="receiveAddress" id="receiveAddress" size="100" required>
        <br><br>
        
        <!-- 收件人姓名 -->
        <label for="receiveName">收件人姓名：</label>
        <input type="text" name="receiveName" id="receiveName" size="20" required>
        <br><br>
        
        <!-- 收件人手机号码 -->
        <label for="receivePhone">收件人手機號碼：</label>
        <input type="text" name="receivePhone" id="receivePhone" size="10" required>
        <br><br>
        
        <!-- 备注 -->
        <label for="remarks">備註：</label>
        <input type="text" name="remarks" id="remarks" size="200">
        <br><br>
        
        <!-- 提交按钮 -->
        <input type="submit" value="提交付款信息">
    </form>
</body>
</html>
