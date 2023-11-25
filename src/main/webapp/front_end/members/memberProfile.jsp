<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>會員中心</title>
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
    <!--不是外部檔案也無所謂-->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">


<style>

	#avatarContainer {
        position: relative;
        top: -793px; 
        left: 400px;
        
    }

/* 	#avatar-icon { 
         position: absolute; 
/        top: 280px;  
         left: 730px; 
        
     } */
	#avatar-icon {
        position: relative;
        top: -820px; 
        left: 400px;
        
    }
	#shopImage01-icon {
        position: relative;
        top: -370px; 
        left: -60px;
        
    }
	#shopImage02-icon {
        position: relative;
        top: -220px; 
        left: -100px;
        
    }
 
	#shopImage01Form {
        position: relative;
        top: -335px; 
        left: 0px;
        
    }
	#shopImage02Form {
        position: relative;
        top: -189px; 
        left: 0px;
        
    }


	
    #avataruploadFormContainer {
        margin-top: 0px; 
    }

    #avatarsubmitButton,#shopImage01submitButton,#shopImage02submitButton {
        background-color: #4CAF50; 
        color: white; 
        padding: 10px; 
        border: none; 
        cursor: pointer; 
        position: relative;
        top: -44px; 
        left: 150px;
    }
    
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
    text-align: center;
}

#pswdHashForm {
    display: flex;
    flex-direction: column;
    align-items: center;
}

#pswdHash {
    margin: 10px 0;
    padding: 8px;
    width: 200px;
}

#pswdHashError {
    color: red;
    margin-bottom: 10px;
}

#pswdHashFormCancel {
    background-color: #ccc;
    padding: 8px 16px;
    border: none;
    cursor: pointer;
}

#pswdHashFormCancel:hover {
    background-color: #ddd;
}

#pswdHashForm input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    padding: 5px 10px;
    border: none;
    cursor: pointer;
}

#pswdHashForm input[type="submit"]:hover {
    background-color: #45a049;
}
.
</style>
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">



</head>
<body>
<!--放在最前面-->
<div class="headerHTML"></div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


<div id="hy_con">
<div id="con_lf" style=" width: 166px;">
<br>

<!--=============================================插入連結的地方-->


</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>

<p class="rh_title">帳戶資訊</p>





<div class="zh_num">錢包餘額：<span>${Members.balance}</span>元</div> 
<div class="zh_num">我的點數：<span>${Members.mbrPoint}</span>點</div>
<br></br>
<p class="rh_title">會員資訊</p>
<ul class="ul_zhxx">
<li class="li_bj" style="height: 44px;">會員編號：<span id="mbrId">${user.mbrId}</span></li>
<li  class="li_bj" style=" padding-bottom: 3px;">用戶名：<span id="mbrName">${Members.mbrName}</span>
<button class="editButton" onclick="editMemberData('mbrName')">修改</button></li>

<li  class="li_bj" style="height: 44px;">Email：<span id="mbrName">${user.email}</span>
<li style="height: 44px;" class="li_bj">會員密碼：<span id="mbrName">******</span>
	    <button id="pswdHashFormA">更改密碼</button>
	
	    <!-- 彈出的窗口 -->
	    <div id="passwordPopup">
	        <form method="post" id="pswdHashForm" class="UpdatePswdHash" >
	            <input type="password" id="pswdHash" name="pswdHash" placeholder="密碼">
	            <input type="hidden" id="mbrId" name="mbrId" value="${user.mbrId}">
	            <span id="pswdHashError" style="color: red;"></span>
	            <input type="hidden" name="action" value="members_UpdatePswdHash_1">
	            <input type="submit" value="確定">
	        </form>
	        <button id="pswdHashFormCancel">取消</button>
	    </div>
<br></br>
<li  class="li_bj" style="height: 44px;">買家評價：
		<span>
		<script>
// 	            document.write('<img style="width: 10px !important; height: 10px !important; margin-right: 0;" src="${pageContext.request.contextPath}/images/Snipaste.png" alt="Snipaste Image">'.repeat(buyStar) );
		   var buyStar = ${Members.buyStar};
        var buyRating = ${Members.buyRating};
        if (buyStar > 0) {
            var formattedStars = '☆'.repeat(buyRating > 0 ? (buyStar / buyRating) : 0);
            if (buyRating > 0) {
                var formattedNumber = (buyStar / buyRating).toFixed(1);
                formattedStars += ' (' + formattedNumber + ')';
            }
            document.write(formattedStars);
        } else {
            document.write('無評價');
        }
        </script>
        </span>

<li  class="li_bj" style="height: 44px;">賣家評價：
		<span >
		<script>
		 var sellStar = ${Members.sellStar};
	        var sellRating = ${Members.sellRating};
	        if (sellStar > 0) {
	            var formattedStars = '☆'.repeat(sellRating > 0 ? (sellStar / sellRating) : 0);
	            if (sellRating > 0) {
	                var formattedNumber = (sellStar / sellRating).toFixed(1);
	                formattedStars += ' (' + formattedNumber + ')';
	            }
	            document.write(formattedStars);
	        } else {
	            document.write('無評價');
	        }
        </script>
        </span>

</ul>


<br></br><br></br><br></br><br></br><br></br>
<div id="shopImage01Container"> 		
<p class="rh_title">商場照片</p>
	<label for="shopImage01Input">
		<img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg01" width="150px" height="150px" >
	</label>	

</div>
<div id="shopImage02Container"style="width: 150px; display: inline-block;" > 		

	<label for="shopImage02Input">
		<img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg02" width="150px" height="150px" >
	</label>	
	
</div>
<div id="avatarContainer" style="
    width: 150px;">
    <p class="rh_title" style="
    width: 150px;">大頭貼</p>
    <label for="avatarInput">
        <img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=avatar" width="150px" height="150px" />
    </label>
    <div id="avataruploadFormContainer" style="display: none;">
        <form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="avatarForm" enctype="multipart/form-data">
            <input type="file" id="avatarInput" name="avatar" accept="image/*" style="display: none;">
            <input type="hidden" name="mbrId" value="${user.mbrId}">
            <input type="hidden" name="action" value="members_UpdateImage">
            <input id="avatarsubmitButton" type="submit"  value="修改大頭貼"  >
        </form>
    </div>
     
</div>
<img src="${pageContext.request.contextPath}/images/camera.png" id="avatar-icon" class="camera-icon rounded-circle" style="width: 40px; height: 40px; ">
<img src="${pageContext.request.contextPath}/images/camera.png" id="shopImage01-icon" class="camera-icon rounded-circle" style="width: 40px; height: 40px; ">
<img src="${pageContext.request.contextPath}/images/camera.png" id="shopImage02-icon" class="camera-icon rounded-circle" style="width: 40px; height: 40px; ">
	<div id="shopImage01uploadFormContainer" style="display: none;">	
		<form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="shopImage01Form" enctype="multipart/form-data"  >
			<input type="file" id="shopImage01Input" name="shopImage01" accept="image/*" style="display: none;">
		    <input type="hidden" name="mbrId" value="${user.mbrId}">
		    <input type="hidden" name="action" value="members_UpdateImage"  >
		     <input id="shopImage01submitButton" type="submit" value="修改商場圖片01" >
		</form><!-- 修改商場圖片01 -->
		   
	</div>
	<div id="shopImage02uploadFormContainer" style="display: none;">	
		<form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="shopImage02Form" enctype="multipart/form-data"  > 
		    <input type="file" id="shopImage02Input" name="shopImage02" accept="image/*" style="display: none;">
		    <input type="hidden" name="mbrId" value="${user.mbrId}">
			<input type="hidden" name="action" value="members_UpdateImage"  >
		    <input id="shopImage02submitButton" type="submit" value="修改商場圖片02">
		</form><!-- 修改商場圖片02 -->
	</div>
</div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

</div>


<!--放在最後面-->
<div class="footerHTML"></div>
<script>
var contextPath = "${pageContext.request.contextPath}";	

  function editMemberData(field) {
    var currentValue = document.getElementById(field).innerHTML;
    var newValue = prompt('請輸入新的值：', currentValue);
    var mbrId = document.getElementById("mbrId").innerHTML;
    var action ='members_UpdateName';
    if (newValue !== null && newValue !== currentValue) {
    	
   	  var xhr = new XMLHttpRequest();
      xhr.open('POST', contextPath + "/members/Members.do?action="+action+"&mbrId="+mbrId, true);
      xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

      var data = {
    	 
         field: field,
         newValue: newValue,
         
         
      };

      xhr.send(JSON.stringify(data));
      
      document.getElementById(field).innerText = newValue;
//       alert('你輸入的新值為：' + newValue);
      // 在這裡添加將新值提交給後端的邏輯
    }
  }
//
   document.getElementById('avatar-icon').addEventListener('click', function() {
        // 觸發檔案選擇
        document.getElementById('avatarInput').click();
    });
	document.getElementById('avatarInput').addEventListener('change', function() {
	    // 處理選擇檔案後的邏輯，例如顯示檔案名稱或預覽圖片
	    var fileName = this.files[0].name;
	    console.log('選擇的檔案：', fileName);

	    // 顯示上傳表單
	    document.getElementById('shopImage02-icon').style.display = 'none';
	    document.getElementById('shopImage01-icon').style.display = 'none';
	    document.getElementById('avatar-icon').style.display = 'none';
	    document.getElementById('avataruploadFormContainer').style.display = 'block';
	});

	document.getElementById('avatarsubmitButton').addEventListener('click', function() {
	    // 觸發表單提交
	    document.getElementById('avatarForm').submit();
	});
//
	   document.getElementById('shopImage01-icon').addEventListener('click', function() {
	        // 觸發檔案選擇
	        document.getElementById('shopImage01Input').click();
	    });
	    document.getElementById('shopImage01Input').addEventListener('change', function() {
	        var fileName = this.files[0].name;
	        console.log('選擇的檔案：', fileName);

	        document.getElementById('shopImage02-icon').style.display = 'none';
		    document.getElementById('shopImage01-icon').style.display = 'none';
		    document.getElementById('avatar-icon').style.display = 'none';
	        document.getElementById('shopImage01uploadFormContainer').style.display = 'block';
	    });

	    document.getElementById('shopImage01submitButton').addEventListener('click', function() {
	        document.getElementById('shopImage01Form').submit();
	    });

	
//	
    document.getElementById('shopImage02-icon').addEventListener('click', function() {
        // 觸發檔案選擇
        document.getElementById('shopImage02Input').click();
    });
	document.getElementById('shopImage02Input').addEventListener('change', function() {
	    // 處理選擇檔案後的邏輯，例如顯示檔案名稱或預覽圖片
	    var fileName = this.files[0].name;
	    console.log('選擇的檔案：', fileName);

	    // 顯示上傳表單
	    document.getElementById('shopImage02-icon').style.display = 'none';
	    document.getElementById('shopImage01-icon').style.display = 'none';
	    document.getElementById('avatar-icon').style.display = 'none';
	    document.getElementById('shopImage02uploadFormContainer').style.display = 'block';
	});

	document.getElementById('shopImage02submitButton').addEventListener('click', function() {
	    // 觸發表單提交
	    document.getElementById('shopImage02Form').submit();
	});



	
    
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

    $(document).ready(function () {
        // 使用 AJAX 請求加載其他內容
        $.ajax({
            url: "${pageContext.request.contextPath}/front_end/members/sideMembers.jsp",
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

</body>
</html>

