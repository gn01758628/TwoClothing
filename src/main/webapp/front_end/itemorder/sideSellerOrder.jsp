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



			<h2>賣家一般商品訂單</h2>
			<ul>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller">所有訂單</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=0">待付款</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=1">待出貨</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=2">待收貨</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=3">訂單完成</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=4">訂單不成立</a></li>
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