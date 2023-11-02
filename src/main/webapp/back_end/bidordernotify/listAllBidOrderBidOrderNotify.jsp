<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.abid.bidorderratingimage.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
BidOrderRatingImageServiceImpl bidOrderRatingImageServiceImpl = new BidOrderRatingImageServiceImpl();
    List<BidOrderRatingImage> list = bidOrderRatingImageServiceImpl.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllMembers.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/bidorderratingimage/select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>圖片編號</th>
		<th>競標商品訂單編號</th>
		<th>圖片</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="bidOrderRatingImage" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">>
		
		<tr>
		<td>${bidOrderRatingImage.imageId}</td>
		<td>${bidOrderRatingImage.bidOrderId}</td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?imageId=${bidOrderRatingImage.imageId}&imgType=image" width=100px height=100px  ></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorderratingimage/BidOrderRatingImage.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="imageId"  value="${bidOrderRatingImage.imageId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorderratingimage/BidOrderRatingImage.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="imageId"  value="${bidOrderRatingImage.imageId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>