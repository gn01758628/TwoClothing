	<%@ page language="java" contentType="text/html; charset=BIG5"
	    pageEncoding="BIG5"%>
	<!DOCTYPE html>
	<html lang="en">
	
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Change Password</title>
	    <style>
	        /* 為了演示，將樣式直接放在HTML中 */
	        #passwordPopup {
	            display: none;
	            position: fixed;
	            top: 50%;
	            left: 50%;
	            transform: translate(-50%, -50%);
	            padding: 20px;
	            background-color: #fff;
	            border: 1px solid #ccc;
	            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	            z-index: 1000;
	            text-align: center; /* 新增這一行 */
	        }
	
	        /* 新增的樣式 */
	        #passwordPopup input,
	        #passwordPopup button {
	            margin: 10px 0; /* 調整間距 */
	        }
	    </style>
	</head>
	
	<body>
	
	    <!-- 點擊更改密碼按鈕時觸發的事件 -->
	    
	    <button id="pswdHashFormA">更改密碼</button>
	
	    <!-- 彈出的窗口 -->
	    <div id="passwordPopup">
	        <form method="post" id="pswdHashForm" class="UpdatePswdHash" >
	            <input type="password" id="pswdHash" name="pswdHash" placeholder="密碼">
	            <input type="hidden" id="mbrId" name="mbrId" value="1">
	            <span id="pswdHashError" style="color: red;"></span>
	            <input type="hidden" name="action" value="members_UpdatePswdHash_1">
	            <input type="submit" value="確定">
	        </form>
	        <button id="pswdHashFormCancel">取消</button>
	    </div>
	
	    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	    <script>

    	    
    	    
	        $(document).ready(function () {
	            // 點擊更改密碼按鈕時觸發的事件
	            $('#pswdHashFormA').click(function () {
	                // 顯示彈出窗口
	                $('#passwordPopup').show();
	            });
	
	            // 點擊取消按鈕時觸發的事件
	            $('#pswdHashFormCancel').click(function () {
	                // 隱藏彈出窗口
	                $('#passwordPopup').hide();
	                // 清空輸入的密碼
	                $('#pswdHash').val('');
	                // 清空錯誤消息
	                $('#pswdHashError').text('');
	            });
		
	        	
	        	    
	            // 提交表單時觸發的事件
	            $('#pswdHashForm').submit(function (event) {
	                event.preventDefault();
	                // 獲取表單數據
	                var formData = $(this).serialize();
	
	                // 發送Ajax請求
	                $.ajax({
	                    type: 'POST',
	                    url: '${pageContext.request.contextPath}/members/Members.do',
	                    data: formData,
	                    dataType: 'json',
	                    success: function (response) {
	                        if (response.success) {
	                            if (response.location !== null && response.location !== undefined) {
	                                window.location.href = response.location;
	                            } else {
	                                window.location.href = '${pageContext.request.contextPath}/front_end/members/UpdatePswdHash.jsp';
	                            }
	                        } else {
	                            $('#pswdHashError').text(response.errors.error);
	                        }
	                    },
	                    error: function (jqXHR, textStatus, errorThrown) {
	                        alert('AJAX 錯誤：' + errorThrown);
	                        console.error('AJAX 錯誤:', textStatus, errorThrown);
	                        console.log('響應:', jqXHR.responseText);
	                    }
	                });
	            });
	        });
	    </script>
	
	</body>
	
	</html>
