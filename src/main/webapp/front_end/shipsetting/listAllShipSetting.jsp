<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.shipsetting.*" %>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <% 
     Object account = session.getAttribute("user");                  // 從 session內取出 (key) account的值
     if (account == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
       session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java) 
       response.sendRedirect(request.getContextPath()+"/front_end/members/registerLogin.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入 
       return; 
     } 
 %>  

<!DOCTYPE html>



<html>
<head>

<title>listAllShipSetting.jsp</title>

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
		<p><strong>會員編號:</strong> ${user.mbrId}</p>
		 <h3>所有物流設定 - listAllShipSetting.jsp</h3>
		 <h4><a href='${pageContext.request.contextPath}/MemberCentre.jsp'>會員中心</a>
</h4>
	</td></tr>
</table>

<table>
    <tr>
        <th>會員物流設定編號</th>
        <th>會員編號</th>
        <th>會員姓名</th>
        <th>收件人姓名</th>
        <th>收件人手機</th>
        <th>收件人地址</th>
    </tr>
    
<c:choose>
    <c:when test="${not empty ShipSetting}">
        <c:forEach var="ShipSetting" items="${ShipSetting}">
            <tr>
                <td>${ShipSetting.shipId}</td>
                <td>${ShipSetting.mbrId}</td>
                <td>${user.mbrName}</td>
                <td>${ShipSetting.receiveName}</td>
                <td>${ShipSetting.receivePhone}</td>
                <td>${ShipSetting.receiveAddress}</td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="修改">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                    </form>
                </td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="刪除">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="6">無資料</td>
        </tr>
    </c:otherwise>
</c:choose>
</table>
    <form method="post" action="<%=request.getContextPath()%>/front_end/shipsetting/addShipSetting.jsp" style="margin-bottom: 0px;">
         <input type="submit" value="新增">
         <input type="hidden" name="action" value="insert">
     </form>
</body>
</html>