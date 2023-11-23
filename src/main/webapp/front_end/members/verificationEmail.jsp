<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>verificationEmail</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<style>
 body {
    background-image: url('https://bpic.51yuansu.com/pic3/cover/02/51/35/59e6f3938ca55_610.jpg?x-oss-process=image/sharpen,100');
    background-size: cover; /* 保持圖片覆蓋整個背景 */
    background-repeat: no-repeat; /* 防止圖片重複 */
    background-position: center center; /* 將圖片置於中心 */
    /* 其他你可能需要的背景相關屬性 */
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
	color: #df5334;
}

h5 {
	margin: 20px;
	color: #a3a2a3;
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
	border-top: 10px solid #df5334;
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
	background: #df5334;
	width: 200px;
	height: 30px;
	margin: 10px;
	padding: 5px;
	border-radius: 5px;
	border: 0px;
}

.submit:hover {
	background: #db6937;
}

#copyright {
	text-align: center;
	color: #a3a2a3;
	margin: -200px 0px 0px 0px;
	font-size: 14px;
	
	
}

input {
	padding: 5px;
	border: none;
	border: solid 1px #ccc;
	border-radius: 5px;
}


</style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>


			<form action="${pageContext.request.contextPath}/members/Members.do"
				method="post">
				<input type="hidden" name="action" value="logout">
				<button type="submit">登出</button>
			</form>
	<div class="system_name">
		<h2>TwoClothing歡迎您</h2>
	</div>
	<div id="forgotPasswordModal" class="modal">
		<div class="modal-content">
			<h3>驗證信</h3>
			<form
				action="${pageContext.request.contextPath}/members/SendEmailServlet"
				method="post">
				<input type="hidden" name="action" value="verificationEmail" />
				<input type="text" name="email" value='${user.email}' >
				
				<input type="submit" value="寄出驗證信" class="submit" >

			</form>
		</div>
	</div>

<script>
$(document).ready(function () {
    // 當按鈕被點擊時觸發
    $('.submit').click(function (e) {
        // 防止表單自動提交
        e.preventDefault();

        // 使用SweetAlert2顯示提示框
        Swal.fire({
            title: '確認寄信?',
            text: '確認後將寄出驗證信。',
            icon: 'question',
            showCancelButton: true,
            confirmButtonText: '確認',
            cancelButtonText: '取消'
        }).then((result) => {
            // 如果使用者點擊了"確認"按鈕
            if (result.isConfirmed) {
                // 提交表單
                $(this).closest('form').submit();
            }
        });
    });
});
</script>


</body>
</html>