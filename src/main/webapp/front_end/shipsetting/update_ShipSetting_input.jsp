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
    <!--�A�̦ۤv��css-->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">
    <!--�����Ccss-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--����css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">


</head>
<body>
<div class="headerHTML"></div>

					
<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>


<div id="hy_con">
<div id="con_lf">
<br>
<h2>�b��޲z</h2>
<ul>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�ӤH��T</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">���y�]�w</a></li>
</ul> 

<h2>�q��޲z</h2>
<ul>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId=${user.mbrId}">�R�a�q��</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder0&sellMbrId=${user.mbrId}">��a�q��</a></li>
</ul>
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<p class="rh_title">���y�]�w</p>



<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" name="form1">
<div class="row">
    
    <div class="col-md-6">
		<label class="control-label col-form-label"> �|���s��: </label> 
		<input type="text" name="mbrId" class="form-control"value="${param.mbrId}" readonly>
	</div>
    <div class="col-md-6">
		<label class="control-label col-form-label"> ����H�m�W </label> 
		<input type="text" name="receiveName" class="form-control"value="${param.receiveName}">
	</div>
    <div class="col-md-6">
		<label class="control-label col-form-label"> ����H���: </label> 
		<input type="text" name="receivePhone" class="form-control"value="${param.receivePhone} ">
        <label style="color: deeppink;">${errorMsgs.receivePhone}</label>
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
</div>	
<input type="hidden" name="action" value="update">
<input type="hidden" name="shipId" value="${param.shipId}">
<input type="hidden" name="mbrId" value="${user.mbrId}">
<input type="submit" value="�e�X�ק�">
</FORM>





</div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

</div>
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
        // �O��headerHTML�[�����~���Jheader.js
        $.getScript("${pageContext.request.contextPath}/js/chengHan/header.js");
    });

    $(".footerHTML").load("${pageContext.request.contextPath}/footerHTML.html");
</script>
</body>
</html>















































