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
        /* 隱藏所有帳戶管理下的<ul> */
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
<h2><a href="${pageContext.request.contextPath}/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">會員中心</a></h2>
<h2 id="accountHeading">帳戶管理</h2>
<ul  class="account-menu">
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">個人資訊</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">物流設定</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId">我的收藏</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId">我的檢舉</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=add">虛擬錢包提款申請</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=search">虛擬錢包申請查詢</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/BalanceHistory/balance.check?choice=searchMbrId">虛擬錢包異動查詢</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/PointHistory/point.check?choice=search">點數異動查詢</a></li>
</ul>
			<h2>買家競標商品訂單</h2>
			<ul>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId=${user.mbrId}">待付款</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder1&buyMbrId=${user.mbrId}">未出貨</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder2&buyMbrId=${user.mbrId}">待收貨</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder3&buyMbrId=${user.mbrId}">訂單完成</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder4&buyMbrId=${user.mbrId}">訂單不成立</a></li>
			</ul>


<script>
var accountMenu = document.querySelector("ul.account-menu");

// 當鼠標移到"帳戶管理"時，保持<ul>可見
document.getElementById("accountHeading").addEventListener("mouseover", function () {
    accountMenu.style.display = "block";
});

// 當鼠標離開"帳戶管理"時，隱藏<ul>
document.getElementById("accountHeading").addEventListener("mouseout", function (event) {
    // 檢查鼠標是否真的離開了"帳戶管理"，而不是進入<ul>
    if (!accountMenu.contains(event.relatedTarget)) {
        accountMenu.style.display = "none";
    }
});

// 當鼠標離開<ul>時，隱藏<ul>
accountMenu.addEventListener("mouseout", function (event) {
    // 檢查鼠標是否真的離開了<ul>
    if (!accountMenu.contains(event.relatedTarget)) {
        accountMenu.style.display = "none";
    }
});
</script>
</body>
</html>