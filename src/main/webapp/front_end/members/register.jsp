<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>接收注册信息</title>
    <style >
     .form__input:valid {
            border-color: forestgreen;
        }
        .form__input:valid + .icon::after {
            content: '😃';
        }
        .form__input:invalid {
            border-color: firebrick;
        }
        
        .form__input:invalid + .icon::after {
            content: '😳';
        }
        </style>
</head>
<body>


<a href='/TwoClothing/index.jsp'>回首頁</a>
<br>
<br>


<div id="myModal" class="modal">
    <div class="modal-content">
        <!-- 注册表单 -->
        <h2>用户注册</h2>
        <form action="/TwoClothing/back_end/members/Members.do" method="post">
            <b>电子邮件：</b>
            <input type="text" id="email" name="email" required><font color=red>${errorMsgs.email}</font><br><br> 
            <b>密码：</b>     
            <input type="password"  id="pswdHash" name="pswdHash" placeholder="請輸入密碼" class="form__input" pattern=".{6,}" required><span class="icon"></span><font color=red>${errorMsgs.pswdHash}</font><br><br>
<%--              <input type="text" id="pswdHash" name="pswdHash" required ><font color=red>${errorMsgs.pswdHash}</font><br><br> --%>
            <b>确认密码：</b>
            <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
            <div id="passwordError" style="color: red;"></div>
            <input type="submit" name="action" value="insert">
        </form>

    </div>
</div>
<script >

var password = document.getElementById("pswdHash");
var confirmPassword = document.getElementById("confirmPassword");
var passwordError = document.getElementById("passwordError");

function checkPasswords() {
    if (password.value !== confirmPassword.value) {
        passwordError.innerHTML = "密码不匹配";
    } else {
        passwordError.innerHTML = "";
    }
}

password.addEventListener("input", checkPasswords);
confirmPassword.addEventListener("input", checkPasswords);

</script>

</body>
</html>
