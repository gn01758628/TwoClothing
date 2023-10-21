<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        <!-- 样式部分保持不变 -->
    </style>
    <title>Insert title here</title>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
    <style> 
*{
  font-family:微軟正黑體;  
}

body{
  background-color: white;
}

#email2, #pswdHash2, h3, #mbrName, #comfirm_password,#email, #pswdHash{
  width: 200px;
  height: 20px;
  margin: 10px;
  color: #df5334;
}

h5{
  margin: 20px;
  color: #a3a2a3;
}

h5:hover{
  color: black;
}

#container1, #container2{
  margin: 50px;
  padding: 10px;
  width: 230px;
  height: 300px;
  background-color: white;
  border-radius: 5px;
  border-top: 10px solid #df5334;
  box-shadow: 0 0px 70px rgba(0, 0, 0, 0.1);
  
  /*定位對齊*/
  position:relative;   
  margin: auto;
  top: 100px;
  text-align:center;  
}

.system_name{
  /*定位對齊*/
  position:relative;   
  margin: auto;
  top: 100px;
  text-align:center; 
}

.submit{
  color: white;  
  background: #df5334;
  width: 200px;
  height: 30px;
  margin: 10px;
  padding: 5px;
  border-radius: 5px;
  border: 0px;
}

.submit:hover{
  background: #db6937;
}

#container2{
  visibility: hidden;   /*剛開始消失*/
  height: 350px;
}


#copyright{
  text-align: center;
  color: #a3a2a3;
  margin: -200px 0px 0px 0px;
  font-size: 14px;
}

input{
  padding: 5px;
  border: none; 
  border:solid 1px #ccc;
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
</style>
    
</head>
<body>
	<a href='/TwoClothing//index.jsp'>回首頁</a>
	
	
    <div class="system_name">
        <h2>○○系統</h2>
    </div>

    <div class="login_page">
        <div id="container1">
            <div class="login">
                <h3>登入 Login</h3>
                <form action="/TwoClothing/back_end/members/Members.do"  class="login-form">
                    <input type="text" id="email2" name="email2" placeholder="email" required>
                    <div class="tab"></div>
                    <input type="text" id="pswdHash2" name="pswdHash2" placeholder="密碼" required>
                    <div class="tab"></div>
                    <input type="submit" name="action" value="login" class="submit" >
                </form>
                <h5 onclick="show_hide()">註冊帳號</h5>
            </div><!-- login end -->
        </div><!-- container1 end -->
    </div><!-- login_page end -->

    <div class="signup_page">
        <div id="container2">
            <div class="signup">
                <h3>註冊 Sign Up</h3>


    <form id="registrationForm" action="/TwoClothing/back_end/members/Members.do" method="post">
    <!-- 姓名输入字段 -->
    <input type="text" id="mbrName" name="mbrName" placeholder="使用者全名" required pattern="[\u4e00-\u9fa5a-zA-Z0-9_]{2,10}">
    <span id="mbrNameError" style="color: red;"></span>

    <!-- 电子邮件输入字段 -->
    <input type="email" id="email" name="email" placeholder="email" required>
    <span id="emailError" style="color: red;"></span>

    <!-- 密码输入字段 -->
    <input type="password" id="pswdHash" name="pswdHash" placeholder="密碼" required>
    <span id="pswdHashError" style="color: red;"></span>

    <!-- 确认密码输入字段 -->
    <input type="password" id="comfirm_password" name="comfirm_password" placeholder="確認密碼" required>
    <span id="comfirm_passwordError" style="color: red;"></span>

    <input type="submit" name="action" value="register">
</form>
                <h5 onclick="show_hide()">登入帳號</h5>
            </div><!-- signup end -->
        </div><!-- container2 end -->
    </div><!-- signup_page end -->

    <div id="copyright">
        <h4>Copyright © 2018 RoseWang All rights reserved</h4><!--因為js，會跑版-->
    </div>

    <script>
    const form = document.getElementById('registrationForm');
    const mbrNameInput = document.getElementById('mbrName');
    const emailInput = document.getElementById('email');
    const pswdHashInput = document.getElementById('pswdHash');
    const comfirmPasswordInput = document.getElementById('comfirm_password');
    const mbrNameError = document.getElementById('mbrNameError');
    const emailError = document.getElementById('emailError');
    const pswdHashError = document.getElementById('pswdHashError');

    form.addEventListener('submit', function (event) {
        let isValid = true;

        // 姓名字段验证
        if (!mbrNameInput.checkValidity()) {
            mbrNameError.textContent = '姓名: 只能是中、英文字母、数字和_ , 且长度必需在2到10之间';
            isValid = false;
        } else {
            mbrNameError.textContent = ''; // 清空错误消息
        }

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

        // 如果 isValid 为 false，则阻止表单提交
        if (!isValid) {
            event.preventDefault();
        }
    });
//     ==========================================
        function show_hide() {
            var login = document.getElementById("container1");
            var signup = document.getElementById("container2");
            var copyright = document.getElementById("copyright");

            if (login.style.display === "none") {
                login.style.display = "block"; //lonin出現
                document.getElementById("email").value = "";
                document.getElementById("pswdHash").value = "";
                signup.style.display = "none"; //signup消失
                copyright.style.margin = "200px 0px 0px 0px";
            } else {
                login.style.display = "none"; //login消失
                signup.style.display = "block"; //signup出現
                signup.style.visibility = "visible";
                copyright.style.margin = "200px 0px 0px 0px";

                document.getElementById("mbrName").value = "";
                document.getElementById("email").value = "";
                document.getElementById("pswdHash").value = "";
                document.getElementById("comfirm_password").value = "";
            }
        }
        
        //======================ajax===========
        	 const loginForm =  document.querySelector('.login-form');
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
                url: "/TwoClothing/back_end/members/Members.do",
                data: loginData,
                dataType: "json",
                success: function (response) {
                    if (response.success) {
                        window.location.href = "/TwoClothing/index.jsp";
                    } else {
                        if (response.errors) {
                            alert("帳號密碼錯誤" );
                        }
                    }
                }
            });
        });
    </script>
</body>
</html>
