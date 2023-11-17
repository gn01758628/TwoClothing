<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

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
<h2>物流設定</h2>
<ul>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">個人資訊</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">物流設定</a></li>
</ul>
</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<p class="rh_title">物流設定</p>
<table id="myTable"class="rh_tab2">
<thead>
    <tr>
        <th width="25%">收件人姓名</th>
        <th>收件人手機</th>
        <th>收件人地址</th>
        <th>修改</th>
        <th>刪除</th>
    </tr>
</thead>
<c:choose>
    <c:when test="${not empty ShipSetting}">
        <c:forEach var="ShipSetting" items="${ShipSetting}">
            <tr>
                <td width="25%">${ShipSetting.receiveName}</td>
                <td width="25%">${ShipSetting.receivePhone}</td>
                <td width="25%">${ShipSetting.receiveAddress}</td>
                <td width="25%">
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="修改">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                    </form>
                </td>
                <td width="25%">
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="刪除">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:when>

</c:choose>
</table>
    <form method="post" action="<%=request.getContextPath()%>/front_end/shipsetting/addShipSetting.jsp" style="margin-bottom: 0px;">
         <input type="submit" value="新增">
         <input type="hidden" name="action" value="insert">
     </form>

</div>
</div>
</div>

<div class="clear"></div>
<div id="footer">

</div>
</body>
</html>