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



			<h2>��a�@��ӫ~�q��</h2>
			<ul>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller">�Ҧ��q��</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=0">�ݥI��</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=1">�ݥX�f</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=2">�ݦ��f</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=3">�q�槹��</a></li>
				<li class="lf_li1"><a
					href="<%=request.getContextPath()%>/front_end/itemorder/itemorder.check?action=seller&status=4">�q�椣����</a></li>
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