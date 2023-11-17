<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>


<!DOCTYPE html>
<html >
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�|������-�b���T www.bootstrapmb.com</title>
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
<li ><a href='${pageContext.request.contextPath}/index.jsp'>�^����</a></li>
<li class="menu_selected"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�ӤH��T</a></li>
<li><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder&buyMbrId=${user.mbrId}">�R�a�q��</a></li>
<li><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder&sellMbrId=${user.mbrId}">��a�q��</a></li>
</ul>
</div>
</div>
<div id="hy_con">
<div id="con_lf">
<h2>�b��޲z</h2>
<ul>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�ӤH��T</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">���y�]�w</a></li>

</ul>
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<div id="avatarContainer" >
    <p class="rh_title">�j�Y�K</p>
    <label for="avatarInput">
        <img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=avatar" width="150px" height="150px" />
    </label>
    <div id="avataruploadFormContainer" style="display: none;">
        <form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="avatarForm" enctype="multipart/form-data">
            <input type="file" id="avatarInput" name="avatar" accept="image/*" style="display: none;">
            <input type="hidden" name="mbrId" value="${user.mbrId}">
            <input type="hidden" name="action" value="members_UpdateImage">
            <input id="avatarsubmitButton" type="submit"  value="�ק�j�Y�K"  >
        </form>
    </div>
     
</div>
 		
<br></br><br></br>
<p class="rh_title">�b���T</p>
<div class="zh_num">���]�l�B�G<span>${Members.balance}</span>��</div> 
<div class="zh_num">�ڪ��I�ơG<span>${Members.mbrPoint}</span>�I</div>
<br></br><br></br>
<p class="rh_title">�|����T</p>
<ul class="ul_zhxx">
<li class="li_bj">�|���s���G<span id="mbrId">${user.mbrId}</span></li>
<li  class="li_bj">�Τ�W�G<span id="mbrName">${Members.mbrName}</span>
<button class="editButton" onclick="editMemberData('mbrName')">�ק�</button></li>

<li  class="li_bj">Email�G<span id="mbrName">${user.email}</span>
<li  class="li_bj">�|���K�X�G<span id="mbrName">******</span>
	    <button id="pswdHashFormA">���K�X</button>
	
	    <!-- �u�X�����f -->
	    <div id="passwordPopup">
	        <form method="post" id="pswdHashForm" class="UpdatePswdHash" >
	            <input type="password" id="pswdHash" name="pswdHash" placeholder="�K�X">
	            <input type="hidden" id="mbrId" name="mbrId" value="1">
	            <span id="pswdHashError" style="color: red;"></span>
	            <input type="hidden" name="action" value="members_UpdatePswdHash_1">
	            <input type="submit" value="�T�w">
	        </form>
	        <button id="pswdHashFormCancel">����</button>
	    </div>
<br></br>
<li  class="li_bj">�R�a�����G
		<span>
		<script>
// 	            document.write('<img style="width: 10px !important; height: 10px !important; margin-right: 0;" src="${pageContext.request.contextPath}/images/Snipaste.png" alt="Snipaste Image">'.repeat(buyStar) );
		   var buyStar = ${Members.buyStar};
        var buyRating = ${Members.buyRating};
        if (buyStar > 0) {
            var formattedStars = '��'.repeat(buyRating > 0 ? (buyStar / buyRating) : 0);
            if (buyRating > 0) {
                var formattedNumber = (buyStar / buyRating).toFixed(1);
                formattedStars += ' (' + formattedNumber + ')';
            }
            document.write(formattedStars);
        } else {
            document.write('�L����');
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
                 document.write('�L����');
             }
        </script> 
        </span>-->
<li  class="li_bj">��a�����G
		<span >
		<script>
		 var sellStar = ${Members.sellStar};
	        var sellRating = ${Members.sellRating};
	        if (sellStar > 0) {
	            var formattedStars = '��'.repeat(sellRating > 0 ? (sellStar / sellRating) : 0);
	            if (sellRating > 0) {
	                var formattedNumber = (sellStar / sellRating).toFixed(1);
	                formattedStars += ' (' + formattedNumber + ')';
	            }
	            document.write(formattedStars);
	        } else {
	            document.write('�L����');
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
	            document.write('�L����');
	        }
        </script>
        </span>-->
</ul>


<br></br><br></br><br></br><br></br><br></br>
<div id="shopImage01Container"> 		
<p class="rh_title">�ӳ��Ӥ�</p>
	<label for="shopImage01Input">
		<img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg01" width="150px" height="150px" >
	</label>	
	<div id="shopImage01uploadFormContainer" style="display: none;">	
		<form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="shopImage01Form" enctype="multipart/form-data"  >
			<input type="file" id="shopImage01Input" name="shopImage01" accept="image/*" style="display: none;">
		    <input type="hidden" name="mbrId" value="${user.mbrId}">
		    <input type="hidden" name="action" value="members_UpdateImage"  >
		     <input id="shopImage01submitButton" type="submit" value="�ק�ӳ��Ϥ�01" >
		</form>
		   
	</div>
</div>
<div id="shopImage02Container"> 		
<p class="rh_title">�ӳ��Ӥ�</p>
	<label for="shopImage02Input">
		<img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg02" width="150px" height="150px" >
	</label>	
	<div id="shopImage02uploadFormContainer" style="display: none;">	
		<form METHOD="post" action="${pageContext.request.contextPath}/members/Members.do" id="shopImage02Form" enctype="multipart/form-data"  > 
		    <input type="file" id="shopImage02Input" name="shopImage02" accept="image/*" style="display: none;">
		    <input type="hidden" name="mbrId" value="${user.mbrId}">
			<input type="hidden" name="action" value="members_UpdateImage"  >
		    <input id="shopImage02submitButton" type="submit" value="�ק�ӳ��Ϥ�02">
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
    var newValue = prompt('�п�J�s���ȡG', currentValue);
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
//       alert('�A��J���s�Ȭ��G' + newValue);
      // �b�o�̲K�[�N�s�ȴ��浹��ݪ��޿�
    }
  }

	document.getElementById('avatarInput').addEventListener('change', function() {
	    // �B�z����ɮ׫᪺�޿�A�Ҧp����ɮצW�٩ιw���Ϥ�
	    var fileName = this.files[0].name;
	    console.log('��ܪ��ɮסG', fileName);

	    // ��ܤW�Ǫ��
	    document.getElementById('avataruploadFormContainer').style.display = 'block';
	});

	document.getElementById('avatarsubmitButton').addEventListener('click', function() {
	    // Ĳ�o��洣��
	    document.getElementById('avatarForm').submit();
	});

	    document.getElementById('shopImage01Input').addEventListener('change', function() {
	        var fileName = this.files[0].name;
	        console.log('��ܪ��ɮסG', fileName);

	        document.getElementById('shopImage01uploadFormContainer').style.display = 'block';
	    });

	    document.getElementById('shopImage01submitButton').addEventListener('click', function() {
	        document.getElementById('shopImage01Form').submit();
	    });

	
	
	document.getElementById('shopImage02Input').addEventListener('change', function() {
	    // �B�z����ɮ׫᪺�޿�A�Ҧp����ɮצW�٩ιw���Ϥ�
	    var fileName = this.files[0].name;
	    console.log('��ܪ��ɮסG', fileName);

	    // ��ܤW�Ǫ��
	    document.getElementById('shopImage02uploadFormContainer').style.display = 'block';
	});

	document.getElementById('shopImage02submitButton').addEventListener('click', function() {
	    // Ĳ�o��洣��
	    document.getElementById('shopImage02Form').submit();
	});



	
    
    $(document).ready(function () {
        // �I�����K�X���s��Ĳ�o���ƥ�
        $('#pswdHashFormA').click(function () {
            // ��ܼu�X���f
            $('#passwordPopup').show();
        });

        // �I���������s��Ĳ�o���ƥ�
        $('#pswdHashFormCancel').click(function () {
            // ���üu�X���f
            $('#passwordPopup').hide();
            // �M�ſ�J���K�X
            $('#pswdHash').val('');
            // �M�ſ��~����
            $('#pswdHashError').text('');
        });

    	
    	    
        // �������Ĳ�o���ƥ�
        $('#pswdHashForm').submit(function (event) {
            event.preventDefault();
            // ������ƾ�
            var formData = $(this).serialize();

            // �o�eAjax�ШD
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
                    alert('AJAX ���~�G' + errorThrown);
                    console.error('AJAX ���~:', textStatus, errorThrown);
                    console.log('�T��:', jqXHR.responseText);
                }
            });
        });
    });


	</script>

</body>
</html>

