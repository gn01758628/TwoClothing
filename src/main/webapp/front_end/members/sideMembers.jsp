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
        ul.order-menu {
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
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/MemberCouponServlet.check?action=get_Member_Coupon">我的優惠券</a></li>
	<li class="lf_li1"><a href="${pageContext.request.contextPath}/follow.check?action=getAllByMbrId">關注清單</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/blacklist.check?action=getAllByMbrId">黑名單</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=add">虛擬錢包提款申請</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=search">虛擬錢包申請查詢</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/BalanceHistory/balance.check?choice=searchMbrId">虛擬錢包異動查詢</a></li>
    <li class="lf_li1"><a href="${pageContext.request.contextPath}/PointHistory/point.check?choice=search">點數異動查詢</a></li>
</ul>

  



<h2 id="bidOrderHeading">競標相關</h2>
<ul class="bidOrder-menu">
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front_end/biditem/BidItemParticipate.html">參與中的競標</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front_end/biditem/BidItemViewHistory.html">競標商品瀏覽紀錄</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0">買家訂單</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder0">賣家訂單</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/front/biditemreport?action=getAllByMbrId">競標檢舉</a></li>
</ul>


<h2 id="orderHeading">一般商品相關</h2>
<ul class="order-menu">
<li class="lf_li1"><a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId">我的收藏</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/itembrowsing.redis?action=getAllByMbrId">瀏覽紀錄</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front_end/itemorder/itemorder.check?action=buyer">買家訂單</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front_end/itemorder/itemorder.check?action=seller">賣家訂單</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId">我的檢舉</a></li>
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






var bidOrderMenu = document.querySelector("ul.bidOrder-menu");

// 當鼠標移到"帳戶管理"時，保持<ul>可見
document.getElementById("bidOrderHeading").addEventListener("mouseover", function () {
	bidOrderMenu.style.display = "block";
});

// 當鼠標離開"帳戶管理"時，隱藏<ul>
document.getElementById("bidOrderHeading").addEventListener("mouseout", function (event) {
    // 檢查鼠標是否真的離開了"帳戶管理"，而不是進入<ul>
    if (!bidOrderMenu.contains(event.relatedTarget)) {
    	bidOrderMenu.style.display = "none";
    }
});

// 當鼠標離開<ul>時，隱藏<ul>
bidOrderMenu.addEventListener("mouseout", function (event) {
    // 檢查鼠標是否真的離開了<ul>
    if (!bidOrderMenu.contains(event.relatedTarget)) {
    	bidOrderMenu.style.display = "none";
    }
});











var orderMenu = document.querySelector("ul.order-menu");

// 當鼠標移到"帳戶管理"時，保持<ul>可見
document.getElementById("orderHeading").addEventListener("mouseover", function () {
	orderMenu.style.display = "block";
});

// 當鼠標離開"帳戶管理"時，隱藏<ul>
document.getElementById("orderHeading").addEventListener("mouseout", function (event) {
    // 檢查鼠標是否真的離開了"帳戶管理"，而不是進入<ul>
    if (!orderMenu.contains(event.relatedTarget)) {
    	orderMenu.style.display = "none";
    }
});

// 當鼠標離開<ul>時，隱藏<ul>
orderMenu.addEventListener("mouseout", function (event) {
    // 檢查鼠標是否真的離開了<ul>
    if (!orderMenu.contains(event.relatedTarget)) {
    	orderMenu.style.display = "none";
    }
});
</script>




</body>
</html>