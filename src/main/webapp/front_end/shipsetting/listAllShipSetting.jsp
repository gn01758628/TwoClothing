<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html >
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>�|������-�b���T www.bootstrapmb.com</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">

<style>

</style>

</head>
<body>
<div id="head">

<div class="menu">
<ul>
<li ><a href='${pageContext.request.contextPath}/index.jsp'>�^����</a></li>
<li class="menu_selected"><a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�ӤH��T</a></li>
<li><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId=${user.mbrId}">�R�a�q��</a></li>
<li><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder0&sellMbrId=${user.mbrId}">��a�q��</a></li>
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
<table id="myTable"class="rh_tab2">
<thead>
    <tr>
        <th width="25%">����H�m�W</th>
        <th>����H���</th>
        <th>����H�a�}</th>
        <th>�ק�</th>
        <th>�R��</th>
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
                        <input type="submit" value="�ק�">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                    </form>
                </td>
                <td width="25%">
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="�R��">
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
         <input type="submit" value="�s�W">
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