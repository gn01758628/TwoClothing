<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html style="
    height: 1050px;
">
<head>
    <meta charset="UTF-8">
    <style>
        <!--
        样式部分保持不变

        -->
    </style>
    <title>Insert title here</title>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!--====圖片驗證點擊刷新===================				 -->
    <script type="text/javascript">
        function refresh() {
            var imgValidate = document.getElementById("imgValidate");
            var currentSrc = imgValidate.src;
            var newSrc = (currentSrc.indexOf("?") === -1) ? currentSrc + "?id=" + Math.random() : currentSrc.replace(/(\?id=\d+)/, "?id=" + Math.random());
            imgValidate.src = newSrc;
        }
    </script>
    <!-- 	圖片驗證	===================				 -->

    <style>
body {
  height: 100vh;
  margin: 0;
  background: radial-gradient(
    circle at top left,
    #3498db,
    transparent 70%
  ) top left,
  radial-gradient(
    circle at top right,
    #e74c3c,
    transparent 70%
  ) top right,
  radial-gradient(
    circle at bottom left,
    #2ecc71,
    transparent 70%
  ) bottom left,
  radial-gradient(
    circle at bottom right,
    #f39c12,
    transparent 70%
  ) bottom right;
  background-size: 50% 50%;
  background-repeat: no-repeat;
}






        * {
            font-family: 微軟正黑體;
        }

        body {
            background-color: white;
        }

        #email2, #pswdHash2, h3, #mbrName, #comfirm_password, #email, #pswdHash, #VerificationCode, #imgValidate {
            width: 200px;
            height: 20px;
            margin: 10px;
            color: #c47aa8;
        }

        h5 {
            margin: 20px;
            color: #c47aa8;
        }

        h5:hover {
            color: black;
        }

        #container1, #container2 {
            margin: 50px;
            padding: 10px;
            width: 230px;
            height: 450px;
            background-color: white;
            border-radius: 5px;
            border-top: 10px solid #c47aa8;
            box-shadow: 0 0px 70px rgba(0, 0, 0, 0.1);
            /*定位對齊*/
            position: relative;
            margin: auto;
            top: 100px;
            text-align: center;
        }

        #forgotPasswordModal {
            margin: 50px;
            padding: 10px;
            width: 230px;
            height: 200px;
            background-color: white;
            border-radius: 5px;
            border-top: 10px solid #c47aa8;
            box-shadow: 0 0px 70px rgba(0, 0, 0, 0.1);
            /*定位對齊*/
            position: relative;
            margin: auto;
            top: 100px;
            text-align: center;
        }

        .system_name {
            /*定位對齊*/
            position: relative;
            margin: auto;
            top: 100px;
            text-align: center;
        }

        .submit {
            color: white;
            background: #c47aa8;
            width: 200px;
            height: 30px;
            margin: 10px;
            padding: 5px;
            border-radius: 5px;
            border: 0px;
        }

        .submit:hover {
            background: #edca4c;
        }

        #container2 {
            visibility: hidden; /*剛開始消失*/
            height: 450px;
        }

        #forgotPasswordModal {
            visibility: hidden; /*剛開始消失*/
            height: 200px;
        }

        #copyright {
            text-align: center;
            color: #c47aa8;
            margin: -200px 0px 0px 0px;
            font-size: 14px;
        }

        input {
            padding: 5px;
            border: none;
            border: solid 1px #ccc;
            border-radius: 5px;
        }

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

        .register-form:valid {
            border-color: forestgreen;
        }

        .register-form:valid + .icon::after {
            content: '😃';
        }

        .register-form:invalid {
            border-color: firebrick;
        }

        .register-form:invalid + .icon::after {
            content: '😳';
        }

#togglePassword1 {
	position: absolute;
	right: 30px;
	top: 27%;
	transform: translateY(-50%);
	cursor: pointer;
}
#togglePassword2 {
	position: absolute;
	right: 30px;
	top: 37%;
	transform: translateY(-50%);
	cursor: pointer;
}
/*////////////////////////////////*/

  
    </style>

</head>
<body>


<div class="system_name">
    <h2>TwoClothing</h2>
    
</div>
<div class="login_page">
    <div id="container1">
        <div class="login">
            <h3>登入 Login</h3>
            <!-- ============================登入================================================ -->
            <form action="${pageContext.request.contextPath}/members/Members.do" class="login-form">
                <input type="text" id="email2" name="email2" placeholder="email"
                       required><span id="loginEemailError" style="color: red;"></span>
                <div class="tab"></div>
                <input type="password" id="pswdHash2" name="pswdHash2" placeholder="密碼" required><span id="loginPpswdHashError" style="color: red;"></span>
                <span id="togglePassword1" onclick="togglePasswordVisibility()">🔒</span>
                <div class="tab"></div>
                <label for="rememberMe">記住我</label>
                <input type="checkbox" id="rememberMe">
                <div class="tab"></div>
                <input type="submit" name="action" value="login" class="submit">
            </form>
            <!-- ============================登入================================================ -->
            <h5 onclick="show_hide()">註冊帳號</h5>
            <h5 id="forgotPasswordBtn" onclick="showForgotPasswordModal()">忘記密碼</h5>

        </div>
        <!-- login end -->
    </div>
    <!-- container1 end -->
</div>
<!-- login_page end -->

<div class="signup_page">
    <div id="container2">
        <div class="signup">
            <h3>註冊 Sign Up</h3>

            <!-- ============================註冊================================================ -->
            <form id="registrationForm"
                  action="${pageContext.request.contextPath}/members/Members.do" method="post"
                  class="register-form">


                <!-- 电子邮件输入字段 -->
                <input type="email" id="email" name="email" placeholder="email"
                       required> <span id="registerEmailError" style="color: red;"></span>

                <!-- 密码输入字段 -->
                <input type="password" id="pswdHash" name="pswdHash"
                       placeholder="密碼" required> <span id="registerPpswdHashError"
                                                          style="color: red;"></span>
			    <span id="togglePassword1" onclick="togglePasswordVisibility()">🔒</span>
			
                <!-- 确认密码输入字段 -->
                <input type="password" id="comfirm_password"
                       name="comfirm_password" placeholder="確認密碼" required> <span
                    id="comfirm_passwordError" style="color: red;"></span>
                <span id="togglePassword2" onclick="togglecomfirm_passwordInputVisibility()">🔒</span> 
                    
                <!-- 	圖片驗證	===================				 -->
                <input id="VerificationCode" type="text" name="VerificationCode" size=10 placeholder=驗證碼>
                <span id="VerificationCodeError" style="color: red;"></span>
                <%--點選圖片可進行驗證碼重新整理--%>
                <img name="imgValidate" id=imgValidate src="imgValidate.jsp" onclick="refresh()">
                <br>
                <!-- 	圖片驗證	===================				 -->
                <input type="submit" name="action" value="register">

            </form>
            <!-- ============================註冊================================================ -->
            <h5 onclick="show_hide()">登入帳號</h5>
        </div>
        <!-- signup end -->
    </div>
    <!-- container2 end -->
</div>
<!-- signup_page end -->
<!--忘記密碼==================================================================-->
<!--忘記密碼==================================================================-->
<!--忘記密碼==================================================================-->
<div id="forgotPasswordModal" class="modal" style="display: none;">
    <div class="modal-content">
        <h3>忘記密碼</h3>
        <form action="${pageContext.request.contextPath}/members/SendEmailServlet"
              onsubmit="sendForgotPasswordEmail(); return false;">
            <label for="email3">輸入你的EMAIL:</label>
            <input type="text" id="email3" name="email3" placeholder="Email">
            <!-- 	            <input type="hidden" name="action" value="forgotPasswordEmail" class="submit"> -->
            <input type="submit" name="action" value="寄出驗證信" class="submit">
            <h5 onclick="show_hide()">登入帳號</h5>
        </form>
    </div>
</div>


<div id="copyright">
    <h4>Copyright © 2018 RoseWang All rights reserved</h4>
    <!--因為js，會跑版-->
</div>


<script>
    var contextPath = "${pageContext.request.contextPath}";
    const form = document.getElementById('registrationForm');
    const emailInput = document.getElementById('email');
    const pswdHashInput = document.getElementById('pswdHash');
    const comfirm_passwordInput = document.getElementById('comfirm_password');
    var verificationCode = document.getElementById("VerificationCode").value;
    const comfirmPasswordInput = document.getElementById('comfirm_password');
    const emailError = document.getElementById('registerEmailError');
    const pswdHashError = document.getElementById('registerPpswdHashError');

    form.addEventListener('submit', function (event) {
        let isValid = true;

        // 电子邮件字段验证
        const emailPattern = /^[a-zA-Z0-9_!#$%&'\\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$/;
        if (!emailPattern.test(emailInput.value)) {
            emailError.textContent = '电子邮件地址格式不正确';
            isValid = false;
        } else {
            emailError.textContent = ''; // 清空错误消息
        }

        // 密码字段验证（包括密码复杂度）
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/;
        if (!passwordPattern.test(pswdHashInput.value)) {
            pswdHashError.textContent = '密码必须包含至少一个小写字母、一个大写字母和一个数字';
            isValid = false;
        } else {
            pswdHashError.textContent = ''; // 清空错误消息
        }

        if (pswdHashInput.value !== comfirm_passwordInput.value) {
            comfirm_passwordError.textContent = '两次输入的密码不匹配';
            isValid = false;
        } else {
            comfirm_passwordError.textContent = ''; // 清空错误消息
        }

        // 获取验证码的值并验证
        verificationCode = document.getElementById("VerificationCode").value;
        if (!verificationCode) {
            // 用户没有输入验证码
            document.getElementById("VerificationCodeError").textContent = "请输入验证码";
            isValid = false;
        }

        // 如果 isValid 为 false，则阻止表单提交
        if (!isValid) {
            event.preventDefault();
        }
    });


    //===============================================登入註冊切換===============================================

    // 		function show_hide() {
    // 			var login = document.getElementById("container1");
    // 			var signup = document.getElementById("container2");
    // 			var copyright = document.getElementById("copyright");
    // 			var location = null;
    // 			if (login.style.display === "none") {
    // 				login.style.display = "block"; //lonin出現
    // 				document.getElementById("email").value = "";
    // 				document.getElementById("pswdHash").value = "";
    // 				signup.style.display = "none"; //signup消失
    // 				copyright.style.margin = "200px 0px 0px 0px";
    // 			} else {
    // 				login.style.display = "none"; //login消失
    // 				signup.style.display = "block"; //signup出現
    // 				signup.style.visibility = "visible";
    // 				copyright.style.margin = "200px 0px 0px 0px";

    // 				document.getElementById("email").value = "";
    // 				document.getElementById("pswdHash").value = "";
    // 				document.getElementById("comfirm_password").value = "";
    // 			}
    // 		}

    function show_hide() {
        var login = document.getElementById("container1");
        var signup = document.getElementById("container2");
        var forgotPasswordModal = document.getElementById("forgotPasswordModal");
        var copyright = document.getElementById("copyright");

        if (login.style.display === "none") {
            login.style.display = "block"; // 登入出現
            document.getElementById("email").value = "";
            document.getElementById("pswdHash").value = "";
            signup.style.display = "none"; // 註冊消失
            forgotPasswordModal.style.display = "none"; // 忘記密碼消失
            copyright.style.margin = "200px 0px 0px 0px";
        } else {
            login.style.display = "none"; // 登入消失
            signup.style.display = "block"; // 註冊出現
            signup.style.visibility = "visible";
            forgotPasswordModal.style.display = "none"; // 忘記密碼消失
            copyright.style.margin = "200px 0px 0px 0px";

            document.getElementById("email").value = "";
            document.getElementById("pswdHash").value = "";
            document.getElementById("comfirm_password").value = "";
        }
    }

    //===============================================登入註冊切換===============================================
    //==================================================登入ajax================================================
    const loginForm = document.querySelector('.login-form');
    const loginEmailInput = document.getElementById('email2');
    const loginPasswordInput = document.getElementById('pswdHash2');

    loginForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const loginData = {
            email2: loginEmailInput.value,
            pswdHash2: loginPasswordInput.value,
            action: 'login'
        };

        $.ajax({
            type: "POST",
            url: contextPath + "/members/Members.do",
            data: loginData,
            dataType: "json",
            success: function (response) {

                if (response.success) {
                    // 登入成功跳轉到首頁
                    // TODO 目前是測試用首頁
                    window.location.href = contextPath + "/welcome.html";
                } else {
                    if (response.mbrStatus === 0) {

                        alert("請至信箱驗證");
                        window.location.href = contextPath + "/front_end/members/verificationEmail.jsp";
                    } else {
                        $("#loginPpswdHashError").text(response.errors.error);////
                    }
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("AJAX 錯誤：" + errorThrown);
            }
        });
    });
    //==================================================登入ajax================================================


    //==================================================註冊ajax================================================

    const VerificationCode = document.getElementById('VerificationCode');
    const imgValidate = document.getElementById('imgValidate');


    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const registerData = {
            email: emailInput.value,
            pswdHash: pswdHashInput.value,
            VerificationCode: VerificationCode.value,
            action: 'register'
        };

        $.ajax({
            type: "POST",
            url: contextPath + "/members/Members.do",
            data: registerData,
            dataType: "json",
            success: function (response) {
                try {
                    if (response.success) {

                        window.location.href = contextPath + "/front_end/members/registerLogin.jsp";
                    } else {
                        handleErrors(response.errors);
                    }
                } catch (error) {
                    alert("AJAX 响应解析错误：" + error);
                }
            },

            error: function (jqXHR, textStatus, errorThrown) {
                alert("AJAX 錯誤：" + errorThrown);
            }
        });
    });

    //==================================================註冊ajax================================================
    function handleErrors(errors) {
        if (errors) {
            if (errors.email) {
                $("#registerEmailError").text(errors.email);
            }
            if (errors.sessionCode) {
                $("#VerificationCodeError").text(errors.sessionCode);
            }
            // 如果有其他錯誤字段，可以類似處理
        }
    }


    $(document).ready(function () {
        // 在頁面載入時，檢查localStorage並填充email和"記住我"的勾選狀態
        var savedEmail = localStorage.getItem("savedEmail");
        var rememberMeChecked = localStorage.getItem("rememberMeChecked") === "true";

        if (savedEmail) {
            $("#email2").val(savedEmail);
        }

        $("#rememberMe").prop("checked", rememberMeChecked);

        $(".login-form").submit(function () {
            // 獲取使用者輸入的email和"記住我"的勾選狀態
            var email = $("#email2").val();
            var rememberMe = $("#rememberMe").is(":checked");

            // 保存email和"記住我"的勾選狀態到localStorage
            localStorage.setItem("savedEmail", email);
            localStorage.setItem("rememberMeChecked", rememberMe);
        });
    });


    function showForgotPasswordModal() {
        var login = document.getElementById("container1");
        var signup = document.getElementById("container2");
        var forgotPasswordModal = document.getElementById("forgotPasswordModal");
        var copyright = document.getElementById("copyright");

        login.style.display = "none";
        signup.style.display = "none";
        forgotPasswordModal.style.display = "block";
        forgotPasswordModal.style.visibility = "visible";
        copyright.style.margin = "200px 0px 0px 0px";
    }

    function closeModal() {
        var forgotPasswordModal = document.getElementById("forgotPasswordModal");
        forgotPasswordModal.style.display = "none";
    }

	function togglePasswordVisibility() {
		var password2Input = document.getElementById('pswdHash2');
		var password1Input = document.getElementById('pswdHash');
		var toggleIcon1 = document.getElementById('togglePassword1');

		if (password2Input.type === 'password'|| password1Input.type === 'password') {
			password2Input.type = 'text';
			password1Input.type = 'text';
			toggleIcon1.textContent = '👁️';
		} else {
			password2Input.type = 'password';
			password1Input.type = 'password';
			toggleIcon1.textContent = '🔒';
		}
	}
	function togglecomfirm_passwordInputVisibility() {
		var comfirm_passwordInput = document.getElementById('comfirm_password');
		var toggleIcon2 = document.getElementById('togglePassword2');

		if (comfirm_passwordInput.type === 'password') {
			comfirm_passwordInput.type = 'text';
			toggleIcon2.textContent = '👁️';
		} else {
			comfirm_passwordInput.type = 'password';
			toggleIcon2.textContent = '🔒';
		}
	}
	

</script>

</body>
</html>
