<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>



<ul>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/members/Members.do?action=memberProfile&mbrId=${user.mbrId}">�ӤH��T</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/shipsetting/Shipsetting.do?action=getAll_For_MbrId&mbrId=${user.mbrId}">���y�]�w</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId">�ڪ�����</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId">�ڪ����|</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=add">�������]���ڥӽ�</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/WithdrawRequest/withdraw.check?choice=search">�|���������]�ӽЬd��</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/BalanceHistory/balance.check?choice=searchMbrId">�������]���ʬd��</a></li>
<li class="lf_li1"><a href="${pageContext.request.contextPath}/PointHistory/point.check?choice=search">�I�Ʋ��ʬd��</a></li>
</ul> 

<h2>�q��޲z</h2>
<ul>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=buyBidOrder0&buyMbrId=${user.mbrId}">�R�a�q��</a></li>
<li class="lf_li1"><a href="<%=request.getContextPath()%>/bidorder/BidOrder.do?action=sellBidOrder0&sellMbrId=${user.mbrId}">��a�q��</a></li>
</ul>






</body>
</html>