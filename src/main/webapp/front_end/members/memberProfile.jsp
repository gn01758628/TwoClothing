<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
//   Members members = (Members) request.getAttribute("members"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>個人資料</title>

<style>

  h4 {
    color: red;
    display: inline;
  }
</style>

<style>
  table {
    width: 50%; /* 讓表格寬度填滿父元素 */
    border-collapse: collapse; /* 合併表格邊框 */
    margin-top: 5px;
    margin-bottom: 5px;
    margin: 0 auto; /* 水平置中 */
  }
  th, td {
    border: 1px solid #CCCCFF;
    padding: 10px; /* 調整內邊距 */
    text-align: center; /* 文本置中 */
  }
  th {
    background-color: #CCCCFF; /* 標題背景顏色 */
    font-weight: bold; /* 加粗字體 */
  }
  img {
    max-width: 100px;
    max-height: 100px;
  }
   table#table-1 {
    width: 50%; /* 與下面的表格大小相匹配 */
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">

	<tr><td>
		 
		 <h4><a href="${pageContext.request.contextPath}/MemberCentre.jsp">會員中心</a></h4>
	</td></tr>
</table>

<table>
  <tr style="display: none;">
    <th >會員編號</th>
    <td id="mbrId">${user.mbrId}</td>
	</tr>

  <tr>
    <th>會員姓名</th>
    <td id="mbrName">${user.mbrName}</td>
    <td><button class="editButton" onclick="editMemberData('mbrName')">修改</button></td>
  </tr>
  <tr>
    <th>會員信箱(帳號)</th>
    <td>${user.email}</td>
  </tr>
  <tr>
    <th>會員密碼</th>
    <td>**********</td>
  </tr>
  <tr>
    <th>會員大頭貼</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${user.mbrId}&imgType=avatar" width=100px height=100px  >
  	</td>
  	<td>
  		<form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="avatarForm" enctype="multipart/form-data"  style="display: none;">
            
            <input type="file" id="avatar" name="avatar" accept="image/*">
            <input type="hidden" name="mbrId" value="${user.mbrId}">
            <input type="hidden" name="action" value="members_UpdateImage"  >
            <input type="submit"  value="修改大頭貼"  >
        </form>
  		<button id="avatarFormA"onclick="showUploadAvatarForm()">修改大頭貼</button>
  		<button id="avatarFormCancel"onclick="cancelImage1Form()" style="display: none;">取消</button>
  	</td>
  </tr>
  <tr>
    <th>會員賣家商場圖片01</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${user.mbrId}&imgType=shopimg01" width=100px height=100px >
    
    </td>
    <td>
    	<form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="shopImage1Form" enctype="multipart/form-data"  style="display: none;">
		    <input type="file" name="shopImage01" accept="image/*">
		    
		    <input type="hidden" name="mbrId" value="${user.mbrId}">
		    <input type="hidden" name="action" value="members_UpdateImage"  >
		    <input type="submit" value="修改商場圖片01" >
		</form>
  		<button id="shopImage1FormA" onclick="showUploadAhopImage1Form()">修改商場圖片01</button>
  		<button id="shopImage1FormCancel"  onclick="cancelImage1Form()" style="display: none;">取消</button>
  	</td>
  </tr>
  <tr>
    <th>會員賣家商場圖片02</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${user.mbrId}&imgType=shopimg02" width=100px height=100px >
    
    </td>
    <td>
	    <form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="shopImage2Form" enctype="multipart/form-data"  style="display: none;"> 
		    <input type="file" name="shopImage02" accept="image/*">
		    <input type="hidden" name="mbrId" value="${user.mbrId}">
			<input type="hidden" name="action" value="members_UpdateImage"  >
		    <input type="submit" value="修改商場圖片02">
		</form>
  		<button id="shopImage2FormA" onclick="showUploadShopImage2Form()">修改商場圖片02</button>
  		<button id="shopImage2FormCancel" onclick="cancelImage1Form()" style="display: none;">取消</button>
  	</td>
  </tr>


</table> 

<script>
	var contextPath = "${pageContext.request.contextPath}";	
	
	
	function showUploadAvatarForm() {
	    var form = document.getElementById('avatarForm');
	    var avatarFormA = document.getElementById('avatarFormA');
	    var avatar = document.getElementById('avatarFormCancel');
	    form.style.display = 'block';  
	    avatar.style.display = 'block';  
	    avatarFormA.style.display = 'none';  
	}
	function showUploadAhopImage1Form() {
	    var form = document.getElementById('shopImage1Form');
	    var shopImage1FormA = document.getElementById('shopImage1FormA');
	    var shopImage1 = document.getElementById('shopImage1FormCancel');
	    form.style.display = 'block';  
	    shopImage1.style.display = 'block';  
	    shopImage1FormA.style.display = 'none';  
	}
	function showUploadShopImage2Form() {
	    var form = document.getElementById('shopImage2Form');
	    var shopImage1FormA = document.getElementById('shopImage2FormA');
	    var shopImage2 = document.getElementById('shopImage2FormCancel');
	    form.style.display = 'block';  
	    shopImage2.style.display = 'block';  
	    shopImage1FormA.style.display = 'none';  
	}
	
	function cancelImage1Form() {
	    var form = document.getElementById('avatarForm');
	    form.style.display = 'none';  
	    var form = document.getElementById('shopImage1Form');
	    form.style.display = 'none';  
	    var form = document.getElementById('shopImage2Form');
	    form.style.display = 'none';
	    var avatar = document.getElementById('avatarFormCancel');
	    avatar.style.display = 'none';
	    var shopImage1 = document.getElementById('shopImage1FormCancel');
	    shopImage1.style.display = 'none';
	    var shopImage2 = document.getElementById('shopImage2FormCancel');
	    shopImage2.style.display = 'none';
	    var avatarFormA = document.getElementById('avatarFormA');
	    avatarFormA.style.display = 'block'; 
	    var shopImage1FormA = document.getElementById('shopImage1FormA');
	    shopImage1FormA.style.display = 'block'; 
	    var shopImage1FormA = document.getElementById('shopImage2FormA');
	    shopImage1FormA.style.display = 'block'; 




	}




  function editMemberData(field) {
    var currentValue = document.getElementById(field).innerText;
    var newValue = prompt('請輸入新的值：', currentValue);
    var mbrId = document.getElementById("mbrId").innerText;
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
      alert('你輸入的新值為：' + newValue);
      // 在這裡添加將新值提交給後端的邏輯
    }
  }
  
//   function uploadImage(formId, imageType) {
// 	    var formData = new FormData();
// 	    var fileInput = document.querySelector(`#${formId} [name=${imageType}]`);

// 	    var mbrId = document.getElementById("mbrId").innerText;
// 	    var file = fileInput.files[0];
// 	    var action = 'members_UpdateImage';

// 	    if (file) {
// 	        formData.append('imageType', imageType);
// 	        formData.append('image', file);

// 	        var xhr = new XMLHttpRequest();
// 	        xhr.open('POST', contextPath + "/members/Members.do?action=" + action + "&mbrId=" + mbrId + "&fileInput=" + imageType, true);
// 	        xhr.onload = function () {
// 	            if (xhr.status === 200) {
// 	                alert('圖片上傳成功！');
// 	            } else {
// 	                alert('圖片上傳失敗！');
// 	            }
// 	        };

// 	        xhr.send(formData);
// 	    } else {
// 	        alert('請選擇一張圖片！');
// 	    }
// 	}

</script>


</body>
</html>