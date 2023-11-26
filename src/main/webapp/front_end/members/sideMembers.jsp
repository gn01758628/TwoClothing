<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
    <style>
        /* ���éҦ��b��޲z�U��<ul> */
        ul.account-menu {
            display: none;
        }
        ul.bidOrder-menu {
            display: none;
        }
        ul.order-menu {
            display: none;
        }


    </style>
</head>
<body>



<br><br>

<h2><a href="${pageContext.request.contextPath}/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�|������</a></h2>

<h2 id="accountHeading">�b��޲z</h2>
<ul  class="account-menu">
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�ӤH��T</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">���y�]�w</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/MemberCouponServlet.check?action=get_Member_Coupon">�ڪ��u�f��</a></li>
	<li class="lf_li1"><a href="${pageContext.request.contextPath}/follow.check?action=getAllByMbrId">���`�M��</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/blacklist.check?action=getAllByMbrId">�¦W��</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=add">�������]���ڥӽ�</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=search">�������]�ӽЬd��</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/BalanceHistory/balance.check?choice=searchMbrId">�������]���ʬd��</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/PointHistory/point.check?choice=search">�I�Ʋ��ʬd��</a></li>
</ul>

  



<h2 id="bidOrderHeading">�v�Ь���</h2>
<ul class="bidOrder-menu">
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front_end/biditem/BidItemParticipate.html">�ѻP�����v��</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front_end/biditem/BidItemViewHistory.html">�v�аӫ~�s������</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0">�R�a�q��</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder0">��a�q��</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/front/biditemreport?action=getAllByMbrId">�v�����|</a></li>
</ul>


<h2 id="orderHeading">�@��ӫ~����</h2>
<ul class="order-menu">
<li class="lf_li1"><a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId">�ڪ�����</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/itembrowsing.redis?action=getAllByMbrId">�s������</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front_end/itemorder/itemorder.check?action=buyer">�R�a�q��</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front_end/itemorder/itemorder.check?action=seller">��a�q��</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId">�ڪ����|</a></li>
</ul>

<script>
var accountMenu = document.querySelector("ul.account-menu");

// ���в���"�b��޲z"�ɡA�O��<ul>�i��
document.getElementById("accountHeading").addEventListener("mouseover", function () {
    accountMenu.style.display = "block";
});

// �������}"�b��޲z"�ɡA����<ul>
document.getElementById("accountHeading").addEventListener("mouseout", function (event) {
    // �ˬd���ЬO�_�u�����}�F"�b��޲z"�A�Ӥ��O�i�J<ul>
    if (!accountMenu.contains(event.relatedTarget)) {
        accountMenu.style.display = "none";
    }
});

// �������}<ul>�ɡA����<ul>
accountMenu.addEventListener("mouseout", function (event) {
    // �ˬd���ЬO�_�u�����}�F<ul>
    if (!accountMenu.contains(event.relatedTarget)) {
        accountMenu.style.display = "none";
    }
});






var bidOrderMenu = document.querySelector("ul.bidOrder-menu");

// ���в���"�b��޲z"�ɡA�O��<ul>�i��
document.getElementById("bidOrderHeading").addEventListener("mouseover", function () {
	bidOrderMenu.style.display = "block";
});

// �������}"�b��޲z"�ɡA����<ul>
document.getElementById("bidOrderHeading").addEventListener("mouseout", function (event) {
    // �ˬd���ЬO�_�u�����}�F"�b��޲z"�A�Ӥ��O�i�J<ul>
    if (!bidOrderMenu.contains(event.relatedTarget)) {
    	bidOrderMenu.style.display = "none";
    }
});

// �������}<ul>�ɡA����<ul>
bidOrderMenu.addEventListener("mouseout", function (event) {
    // �ˬd���ЬO�_�u�����}�F<ul>
    if (!bidOrderMenu.contains(event.relatedTarget)) {
    	bidOrderMenu.style.display = "none";
    }
});











var orderMenu = document.querySelector("ul.order-menu");

// ���в���"�b��޲z"�ɡA�O��<ul>�i��
document.getElementById("orderHeading").addEventListener("mouseover", function () {
	orderMenu.style.display = "block";
});

// �������}"�b��޲z"�ɡA����<ul>
document.getElementById("orderHeading").addEventListener("mouseout", function (event) {
    // �ˬd���ЬO�_�u�����}�F"�b��޲z"�A�Ӥ��O�i�J<ul>
    if (!orderMenu.contains(event.relatedTarget)) {
    	orderMenu.style.display = "none";
    }
});

// �������}<ul>�ɡA����<ul>
orderMenu.addEventListener("mouseout", function (event) {
    // �ˬd���ЬO�_�u�����}�F<ul>
    if (!orderMenu.contains(event.relatedTarget)) {
    	orderMenu.style.display = "none";
    }
});
</script>




</body>
</html>