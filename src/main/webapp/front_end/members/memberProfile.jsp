<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>


<!DOCTYPE html>
<html >
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>會員中心-帳戶資訊 www.bootstrapmb.com</title>
<style type="">
*,body{ margin:0px; padding:0px; font-size:12px; font-family:Arial; color:#333;}
body{ background-color:#f5f5f5;}
ul,dl,dt,dd,p,h1,h2,h3,h4,h5,h6{ margin:0px; padding:0px;}
ul{ list-style:none;}
a{ color:#333; text-decoration:none;}
a:hover{ color:#f60;}
a img{ border:none;}
input{ vertical-align:middle;}
.clear{ clear:both; height:1px; line-height:1px;}
</style>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/Members.css">

<style>
	#avatarContainer {
        position: absolute;
        top: 169px; 
        left: 750px;
        
    }
	#shopImage02Container {
	    position: relative;
	    top: -189px;
	    left: 400px;
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
    padding: 8px 16px;
    border: none;
    cursor: pointer;
}

#pswdHashForm input[type="submit"]:hover {
    background-color: #45a049;
}
</style>

</head>
<body>


<div id="header">

<div class="menu">
<ul>
<li ><a href='${pageContext.request.contextPath}/index.jsp'>回首頁</a></li>
<li class="menu_selected"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">個人資訊</a></li>
<li><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder&buyMbrId=${user.mbrId}">買家訂單</a></li>
<li><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder&sellMbrId=${user.mbrId}">賣家訂單</a></li>
</ul>
</div>
</div>
<div id="hy_con">
<div id="con_lf">
<h2>帳戶管理</h2>
<ul>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">個人資訊</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">物流設定</a></li>

</ul>
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<div id="avatarContainer" >
    <p class="rh_title">大頭貼</p>
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
 		
<br></br><br></br>
<p class="rh_title">帳戶資訊</p>
<div class="zh_num">錢包餘額：<span>${Members.balance}</span>元</div> 
<div class="zh_num">我的點數：<span>${Members.mbrPoint}</span>點</div>
<br></br><br></br>
<p class="rh_title">會員資訊</p>
<ul class="ul_zhxx">
<li class="li_bj">會員編號：<span id="mbrId">${user.mbrId}</span></li>
<li  class="li_bj">用戶名：<span id="mbrName">${Members.mbrName}</span>
<button class="editButton" onclick="editMemberData('mbrName')">修改</button></li>

<li  class="li_bj">Email：<span id="mbrName">${user.email}</span>
<li  class="li_bj">會員密碼：<span id="mbrName">******</span>
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
<br></br>
<li  class="li_bj">買家評價：
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
<!-- 		<span >
		<script> 
             var buyStar = ${Members.buyStar};
             var buyRating = ${Members.buyRating};
          	 if (buyStar > 0) {
                var formattedNumber = (buyStar / buyRating).toFixed(1);
                 document.write(formattedNumber);
             } else {
                 document.write('無評價');
             }
        </script> 
        </span>-->
<li  class="li_bj">賣家評價：
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
<!-- 		<span > 
		<script>
			var sellStar = ${Members.sellStar};
	        var sellRating = ${Members.sellRating};
	        if (sellStar > 0) {
	            var formattedNumber = (sellStar / sellRating).toFixed(1);
	            document.write(formattedNumber);
	        } else {
	            document.write('無評價');
	        }
        </script>
        </span>-->
</ul>


<br></br><br></br><br></br><br></br><br></br>
<div id="shopImage01Container"> 		
<p class="rh_title">商場照片</p>
	<label for="shopImage01Input">
		<img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg01" width="150px" height="150px" >
	</label>	
	<div id="shopImage01uploadFormContainer" style="display: none;">	
		<form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="shopImage01Form" enctype="multipart/form-data"  >
			<input type="file" id="shopImage01Input" name="shopImage01" accept="image/*" style="display: none;">
		    <input type="hidden" name="mbrId" value="${user.mbrId}">
		    <input type="hidden" name="action" value="members_UpdateImage"  >
		     <input id="shopImage01submitButton" type="submit" value="修改商場圖片01" >
		</form>
		   
	</div>
</div>
<div id="shopImage02Container"> 		
<p class="rh_title">商場照片</p>
	<label for="shopImage02Input">
		<img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg02" width="150px" height="150px" >
	</label>	
	<div id="shopImage02uploadFormContainer" style="display: none;">	
		<form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="shopImage02Form" enctype="multipart/form-data"  > 
		    <input type="file" id="shopImage02Input" name="shopImage02" accept="image/*" style="display: none;">
		    <input type="hidden" name="mbrId" value="${user.mbrId}">
			<input type="hidden" name="action" value="members_UpdateImage"  >
		    <input id="shopImage02submitButton" type="submit" value="修改商場圖片02">
		</form>
	</div>
</div>
</div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

</div>
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

	document.getElementById('avatarInput').addEventListener('change', function() {
	    // 處理選擇檔案後的邏輯，例如顯示檔案名稱或預覽圖片
	    var fileName = this.files[0].name;
	    console.log('選擇的檔案：', fileName);

	    // 顯示上傳表單
	    document.getElementById('avataruploadFormContainer').style.display = 'block';
	});

	document.getElementById('avatarsubmitButton').addEventListener('click', function() {
	    // 觸發表單提交
	    document.getElementById('avatarForm').submit();
	});

	    document.getElementById('shopImage01Input').addEventListener('change', function() {
	        var fileName = this.files[0].name;
	        console.log('選擇的檔案：', fileName);

	        document.getElementById('shopImage01uploadFormContainer').style.display = 'block';
	    });

	    document.getElementById('shopImage01submitButton').addEventListener('click', function() {
	        document.getElementById('shopImage01Form').submit();
	    });

	
	
	document.getElementById('shopImage02Input').addEventListener('change', function() {
	    // 處理選擇檔案後的邏輯，例如顯示檔案名稱或預覽圖片
	    var fileName = this.files[0].name;
	    console.log('選擇的檔案：', fileName);

	    // 顯示上傳表單
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


	</script>

</body>
</html>

