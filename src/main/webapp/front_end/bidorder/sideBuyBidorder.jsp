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

		
				
	
    </style>
</head>
<body>



<br><br>
<h2><a href="${pageContext.request.contextPath}/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�|������</a></h2>
<h2 id="accountHeading">�b��޲z</h2>
<ul  class="account-menu">
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�ӤH��T</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">���y�]�w</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId">�ڪ�����</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId">�ڪ����|</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=add">�������]���ڥӽ�</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=search">�������]�ӽЬd��</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/BalanceHistory/balance.check?choice=searchMbrId">�������]���ʬd��</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/PointHistory/point.check?choice=search">�I�Ʋ��ʬd��</a></li>
</ul>
			<h2>�R�a�v�аӫ~�q��</h2>
			<ul>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId=${user.mbrId}">�ݥI��</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder1&buyMbrId=${user.mbrId}">���X�f</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder2&buyMbrId=${user.mbrId}">�ݦ��f</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder3&buyMbrId=${user.mbrId}">�q�槹��</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder4&buyMbrId=${user.mbrId}">�q�椣����</a></li>
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
</script>
</body>
</html>