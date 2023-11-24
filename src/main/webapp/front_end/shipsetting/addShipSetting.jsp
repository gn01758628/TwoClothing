<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>��ѫ�</title>
    <!--����icon-->
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

<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">
    <!--�����Ccss-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--����css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�|������-�b���T www.bootstrapmb.com</title>
</head>

<body>
<!--��b�̫e��-->
<div class="headerHTML"></div>


<div id="hy_con">
<div id="con_lf">
<!--=============================================���J�s�����a��-->
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<p class="rh_title">���y�]�w</p>




<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" name="form1">
<div class="row">
    <div class="col-md-6">
        <label class="control-label col-form-label"> �m�W </label> 
        <input type="text" name="receiveName" class="form-control">
    </div>
    <div class="col-md-6">
        <label class="control-label col-form-label"> ��� </label> 
        <input type="text" name="receivePhone" class="form-control">
        <label style="color: deeppink;">${errorMsgs.receivePhone}</label>
    </div>
</div>

	<div class="twzipcode">
		<div class="row">
			<div class="col-md-6">
				<label class="control-label col-form-label"> ���� </label> <select
					data-role="county" name="county" class="form-select"></select>
			</div>
			<div class="col-md-6">
				<label class="control-label col-form-label"> �m��� </label> <select
					data-role="district" name="district" class="form-select"></select>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<label class="control-label col-form-label"> �ԲӦa�} </label> <input
					type="text" name="address" class="form-control">
			</div>
		</div>
		<input type="hidden" name="zipcode" data-role="zipcode" />
	</div>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="mbrId" value="${user.mbrId}">
<input type="submit" value="�e�X�s�W">
</FORM>



</div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

</div>
<br><br><br><br><br><br><br>

<!--��b�̫᭱-->
<div class="footerHTML"></div>
<script>
let twzipcode = new TWzipcode({
	"district" : {
		onChange : function(id) {
			console.log(this.nth(id).get());
		}
	}
});
</script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
<!--jQuery-->
<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
<!--Sweet Alert-->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
<!--JS loader-->
<script>
    $(".headerHTML").load("${pageContext.request.contextPath}/headerHTML.html", function () {
        // �O��headerHTML�[�����~���Jheader.js
        $.getScript("${pageContext.request.contextPath}/js/chengHan/header.js");
    });

    $(".footerHTML").load("${pageContext.request.contextPath}/footerHTML.html");
    
    
    $(document).ready(function () {
        // �ϥ� AJAX �ШD�[����L���e
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
    
//     $(document).ready(function () {
//         // �ˬd���a�s�x���O�_�� headerHTML�A�p�G���A�h�ϥΥ��A�_�h�ШD���J
//         var storedHeader = localStorage.getItem('headerHTML');
//         if (storedHeader) {
//             $("#headerContainer").html(storedHeader);
//         } else {
//             loadAndStoreHTML('headerHTML', '#headerContainer');
//         }

//         // �ˬd���a�s�x���O�_�� footerHTML�A�p�G���A�h�ϥΥ��A�_�h�ШD���J
//         var storedFooter = localStorage.getItem('footerHTML');
//         if (storedFooter) {
//             $("#footerContainer").html(storedFooter);
//         } else {
//             loadAndStoreHTML('footerHTML', '#footerContainer');
//         }

//         // ��ơG���J HTML �æs�x�쥻�a�s�x
//         function loadAndStoreHTML(filename, container) {
//             $.ajax({
//                 url: "${pageContext.request.contextPath}/" + filename + ".html",
//                 method: "GET",
//                 success: function (data) {
//                     $(container).html(data);

//                     // �s�x HTML �쥻�a�s�x
//                     localStorage.setItem(filename, data);
//                 },
//                 error: function (xhr, status, error) {
//                     console.error("Error loading " + filename + ":", error);
//                 }
//             });
//         }
//     });
    
</script>
</body>
</html>

