<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html >
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap5/bootstrap.min.css" />
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>會員中心-帳戶資訊 www.bootstrapmb.com</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">

<style>

</style>

</head>
<body>

<script src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>
					
<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>
<script src="<%=request.getContextPath()%>/js/gordon/memberArea.js"></script>

<div id="head">

<div class="menu">
<ul>
<li ><a href='${pageContext.request.contextPath}/index.jsp'>回首頁</a></li>
<li class="menu_selected"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">個人資訊</a></li>
<li><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId=${user.mbrId}">買家訂單</a></li>
<li><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder0&sellMbrId=${user.mbrId}">賣家訂單</a></li>
</ul>
</div>
</div>
<div id="hy_con">
<div id="con_lf">
<h2>物流設定</h2>
<ul>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">個人資訊</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">物流設定</a></li>
</ul>
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<p class="rh_title">物流設定</p>



<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" name="form1">
<div class="row">
    
    <div class="col-md-6">
		<label class="control-label col-form-label"> 會員編號: </label> 
		<input type="text" name="mbrId" class="form-control"value="${param.mbrId}" readonly>
	</div>
    <div class="col-md-6">
		<label class="control-label col-form-label"> 收件人姓名 </label> 
		<input type="text" name="receiveName" class="form-control"value="${param.receiveName}">
	</div>
    <div class="col-md-6">
		<label class="control-label col-form-label"> 收件人手機: </label> 
		<input type="text" name="receivePhone" class="form-control"value="${param.receivePhone} ">
        <label style="color: deeppink;">${errorMsgs.receivePhone}</label>
	</div>
	<div class="twzipcode">
		<div class="row">
			<div class="col-md-6">
				<label class="control-label col-form-label"> 縣市 </label> <select
					data-role="county" name="county" class="form-select"></select>
			</div>
			<div class="col-md-6">
				<label class="control-label col-form-label"> 鄉鎮區 </label> <select
					data-role="district" name="district" class="form-select"></select>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<label class="control-label col-form-label"> 詳細地址 </label> <input
					type="text" name="address" class="form-control">
			</div>
		</div>
		<input type="hidden" name="zipcode" data-role="zipcode" />
	</div>
</div>	
<input type="hidden" name="action" value="update">
<input type="hidden" name="shipId" value="${param.shipId}">
<input type="hidden" name="mbrId" value="${user.mbrId}">
<input type="submit" value="送出修改">
</FORM>





</div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

</div>


</body>
</html>















































