<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href='${pageContext.request.contextPath}/index.jsp'>回首頁</a>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder">
	<input type="submit" value="買家競標訂單">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="confirmPayment">
	<input type="submit" value="買家待付款">
	</FORM>
<!--	
  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="sellMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="sellBidOrder">
	<input type="submit" value="賣家競標訂單">
	</FORM>
-->			

</body>
</html>