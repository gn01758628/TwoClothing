<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>建立競標訂單</title>
</head>
<body>
    <h2>建立競標訂單</h2>
    
    	<a href='${pageContext.request.contextPath}/index.jsp'>回首頁</a>
	
	<a href='${pageContext.request.contextPath}/MemberCentre.jsp'>會員中心</a>
	
    <form action="<%=request.getContextPath()%>/bidorder/BidOrder.do" method="post">
        <label for="bidItemId">競標項目ID：</label>
        <input type="text" name="bidItemId" required>
        <br>

        <label for="buyMbrId">買家會員ID：</label>
        <input type="text" name="buyMbrId" required>
        <br>

        <label for="sellMbrId">賣家會員ID：</label>
        <input type="text" name="sellMbrId" required>
        <br>

        <label for="amount">金額：</label>
        <input type="text" name="amount" required>
        <br>
        
<!--       <label for="orderDate">訂單日期 (yyyy-MM-dd HH:mm:ss)：</label>
        <input type="text" name="orderDate" required>
        <br>
         2023-10-10 13:40:00
 --> 
        
        <input type="hidden" name="action" value="add_BidOrder">
        <input type="submit" value="建立訂單">
    </form>
</body>
</html>
