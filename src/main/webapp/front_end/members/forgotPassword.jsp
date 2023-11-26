<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!--頁籤icon-->
    <link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">

<title>忘記密碼</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


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

#email3, #pswdHash, h3, #mbrName, #comfirm_password, #email, #pswdHash,
	#VerificationCode, #imgValidate {
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

#forgotPasswordModal {
	margin: 50px;
	padding: 10px;
	width: 230px;
	height: 250px;
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
	top: 47%;
	transform: translateY(-50%);
	cursor: pointer;
}
</style>
</head>
<body>


	<div class="system_name">
		<h2>TwoClothing歡迎您</h2>
	</div>
	<div id="forgotPasswordModal" class="modal">
		<div class="modal-content">
			<h3>更改密碼</h3>
			<!--  
			<form id="form" action="${pageContext.request.contextPath}/members/Members.do"
				method="post" onsubmit="sendForgotPasswordEmail(); return false;" >
			-->	
			<form id="form" action="${pageContext.request.contextPath}/members/Members.do"
				method="post"  >

				<input type="password"id="pswdHash" name="pswdHash" placeholder="密碼" required> 
				<span id="togglePassword1" onclick="togglePasswordVisibility()">🔒</span>
				<input type="password" id="comfirm_password" name="comfirm_password"placeholder="確認密碼" required>
				<span id="togglePassword2" onclick="togglecomfirm_passwordInputVisibility()">🔒</span> 
				<span id="registerPpswdHashError" style="color: red;"></span>
				<br>
				<span id="comfirm_passwordError" style="color: red;"></span>
				<input type="hidden" name="email" value='<%= request.getParameter("email") %>' />
				<input type="hidden" name="action" value="forgotPassword" />
				<input type="submit" value="更改密碼" class="submit">
				
			</form>
		</div>
	</div>

	<div id="copyright">
		<h4>Copyright © 2018 RoseWang All rights reserved</h4>
		<!--因為js，會跑版-->
	</div>
	<script type="text/javascript">
	

	
	
		var copyright = document.getElementById("copyright");
		copyright.style.margin = "200px 0px 0px 0px";

		function togglePasswordVisibility() {
			var passwordInput = document.getElementById('pswdHash');
			var toggleIcon1 = document.getElementById('togglePassword1');

			if (passwordInput.type === 'password') {
				passwordInput.type = 'text';
				toggleIcon1.textContent = '👁️';
			} else {
				passwordInput.type = 'password';
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

		const emailInput = document.getElementById('email3');
		const pswdHashInput = document.getElementById('pswdHash');
		const comfirm_passwordInput = document
				.getElementById('comfirm_password');
		const form = document.getElementById('form');		
		
		form.addEventListener('submit', function(event) {
		    let isValid = true;
		    
			const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/;
			if (!passwordPattern.test(pswdHashInput.value)) {
			    registerPpswdHashError.textContent = '密码必须包含至少一个小写字母、一个大写字母和一个数字';
			    isValid = false;
			} else {
			    registerPpswdHashError.textContent = ''; // 清空错误消息
			}

			if (pswdHashInput.value !== comfirm_passwordInput.value) {
			    comfirm_passwordError.textContent = '两次输入的密码不匹配';
			    isValid = false;
			} else {
			    comfirm_passwordError.textContent = ''; // 清空错误消息
			}
		    if (!isValid) {
		        event.preventDefault();
		    }
		});
		
	</script>
</body>
</html>
