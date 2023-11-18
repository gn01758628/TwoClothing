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

</style>

</head>
<body>

					<script src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>
					
					<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>

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
<h2>���y�]�w</h2>
<ul>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�ӤH��T</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">���y�]�w</a></li>
</ul>
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<p class="rh_title">���y�]�w</p>





</div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

</div>
</body>
</html>















































<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap5/bootstrap.min.css" />
</head>
<body >

<script src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>

<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>
<table id="table-1">
	<tr><td>
		 <h3>���y��ƭק�</h3>
		 <h4><a href='${pageContext.request.contextPath}/MemberCentre.jsp'>�|������</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>



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
</body>


<script>
let twzipcode = new TWzipcode({
	"district" : {
		onChange : function(id) {
			console.log(this.nth(id).get());
		}
	}
});


var county = $(selector).twzipcode('get', 'county');

// ���o���� county �H�ζm���� district�]��^�}�C�^
var result = $(selector).twzipcode('get', 'county,district'); // �H , �r��ǤJ
var result = $(selector).twzipcode('get', [ 'county', 'district' ]); // �H�}�C�ǤJ

// Callback
$(selector).twzipcode('get', function(county, district, zipcode) {
	console.log(county); // ����
	console.log(district); // �m����
	console.log(zipcode); // �l���ϸ�
});
</script>
</html>