<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
  .form-container {
    display: flex;
    flex-direction: row;
    gap: 10px; /* 表单之间的间隔 */
  }
</style>

</head>
<body>
	<a href='${pageContext.request.contextPath}/index.jsp'>回首頁</a>
	
	<a href='${pageContext.request.contextPath}/MemberCentre.jsp'>會員中心</a>
	
	<a href='${pageContext.request.contextPath}/back_end/bidorder/testBidorder.jsp'>測試新增訂單</a>
<div class="form-container">
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder">
	<input type="submit" value="買家競標訂單">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder0">
	<input type="submit" value="待付款">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder1">
	<input type="submit" value="待出貨">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder2">
	<input type="submit" value="待收貨">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder3">
	<input type="submit" value="交易完成">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder4">
	<input type="submit" value="不成立">
	</FORM>
</div>
<!--	
  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="sellMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="sellBidOrder">
	<input type="submit" value="賣家競標訂單">
	</FORM>
-->			

</body>
</html>