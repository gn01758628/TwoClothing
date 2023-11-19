<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<style>
    body {
      font-family: 'Arial', sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f0f0f0;
    }

    header {
      background-color: #333;
      color: white;
      padding: 10px;
      text-align: center;
    }

    nav {
      background-color: #eee;
      padding: 10px;
      text-align: center;
    }

    nav a {
      margin: 0 10px;
      color: #333;
      text-decoration: none;
      font-weight: bold;
    }

    .form-container {
      display: flex;
      flex-wrap: wrap;
      justify-content: center;
      gap: 10px;
      margin-top: 20px;
    }

    .form-container form {
      background-color: #fff;
      border: 1px solid #ddd;
      padding: 15px;
      border-radius: 5px;
      text-align: center;
    }

    .form-container input[type="submit"] {
      background-color: #4CAF50;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 3px;
      cursor: pointer;
    }

    .form-container input[type="submit"]:hover {
      background-color: #45a049;
    }
    
 



    nav a:hover {
      color: #4CAF50;
    }
        body {
      font-family: 'Arial', sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f0f0f0;
      text-align: center;
    }

    button {
      margin: 10px;
      padding: 15px 20px;
      font-size: 16px;
      font-weight: bold;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    button:hover {
      background-color: #45a049;
    }
  </style>


</head>
<body>
  <nav>
    <button onclick="location.href='${pageContext.request.contextPath}/index.jsp'">回首頁</button>
	<a href="<%=request.getContextPath()%>/members/Members.do?action=memberProfile&mbrId=${user.mbrId}"><button>會員中心</button></a>    
	<button onclick="location.href='${pageContext.request.contextPath}/back_end/bidorder/testBidorder.jsp'">測試新增訂單</button>
  </nav>
	<div class="form-container">
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder">
	<input type="submit" value="買家競標訂單">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder0">
	<input type="submit" value="買家待付款">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder1">
	<input type="submit" value="買家待賣家出貨">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder2">
	<input type="submit" value="買家待收貨">
	</FORM>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder3">
	<input type="submit" value="買家交易完成">
	</FORM>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
	<input type="hidden" name="buyMbrId" value="${user.mbrId}">
	<input type="hidden" name="action" value="buyBidOrder4">
	<input type="submit" value="買家不成立">
	</FORM>
</div>
	

</body>
</html>